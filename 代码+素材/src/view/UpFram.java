package view;

import dao.Paper;
import dao.PaperSystem;
import mypring.MySpring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class UpFram extends  SuperFram {
    private JFrame jFrame=new JFrame("题目修改");
    private JPanel jPanel =new JPanel();
    private JLabel jLabel=new JLabel("答案:");
    private JLabel jLabelpicture=new JLabel("图片名字:");
    private JTextArea jTextArea=new JTextArea();//题目
    private JTextField jTextField=new JTextField();//答案
    private JTextField jTextFieldpicture=new JTextField();//图片名字

    private JScrollPane jScrollPane=new JScrollPane(jTextArea);
    private JButton jsave=new JButton("保存");
    private JButton jexit=new JButton("退出");

    private static String question=null;


    @Override
    protected void setSLC() {
        jFrame.setBounds(480,270,960,540);
        jPanel.setBounds(0,0,960,500);
        jScrollPane.setBounds(10,10,700,480);
        jTextArea.setFont(new Font("宋体",Font.TYPE1_FONT,32));
        jLabel.setBounds(720,50,80,50);jLabel.setFont(new Font("黑体",Font.ITALIC,18));jLabel.setHorizontalAlignment(JTextField.CENTER);
        jTextField.setBounds(800,50,100,50);jTextField.setFont(new Font("宋体",Font.ITALIC,18));
        jLabelpicture.setBounds(720,150,80,50);jLabelpicture.setFont(new Font("黑体",Font.ITALIC,16));//jLabelpicture.setHorizontalAlignment(JTextField.CENTER);
        jTextFieldpicture.setBounds(800,150,100,50);jTextFieldpicture.setFont(new Font("宋体",Font.ITALIC,18));
        jsave.setBounds(770,250,120,50);
        jexit.setBounds(770,350,120,50);
    }

    @Override
    protected void setService() {


    }

    @Override
    protected void addSS() {
    jPanel.setLayout(null);
    jPanel.add(jScrollPane);
    jPanel.add(jLabel);
    jPanel.add(jLabelpicture);
    jPanel.add(jTextField);
    jPanel.add(jTextFieldpicture);
    jPanel.add(jsave);
    jPanel.add(jexit);
    jFrame.setLayout(null);
    jFrame.add(jPanel);

    }

    @Override
    public void init() {
        this.setSLC();
        this.addSS();
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE );
        jFrame.setVisible(true);
    }

    //////////////得到修改对象
    public Paper getpaper(Paper paper1){
        //此paper可能是检查的结果，要替换到原来总的paper
        HashMap<String,Paper> p=new PaperSystem().getHashMapPapers();
        String thiskey=paper1.getTitle();//存储旧键值
        Paper paper= p.get(thiskey);
        //先显示出题目答案图片等信息
        jTextArea.setText(paper.getTitle());
        jTextField.setText(paper.getAnswer());
        jTextFieldpicture.setText(paper.getIcon());
        //如果点击保存后

        jsave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int k=JOptionPane.showConfirmDialog(null,"即将修改数据，确认保存吗？");
                JOptionPane.setRootFrame(new JFrame());
                     if(k==0) {
                         paper.setTitle(jTextArea.getText());
                         paper.setAnswer(jTextField.getText());
                         paper.setIcon(jTextFieldpicture.getText());
                         //确认后修改缓存，把缓存的重新写入文件中,注意更改键值
                         p.put(paper.getTitle(),p.remove(thiskey));//删除旧键,添加新的

                         PaperSystem paperSystem = MySpring.getBean("dao.PaperSystem");
                         paperSystem.saveData();

                     }
            }
        });

        jexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jFrame.setVisible(false);
            }
        });
        return paper;

    }
}
