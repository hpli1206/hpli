package view;


import dao.Paper;
import dao.PaperSystem;
import mypring.MySpring;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class PapersSystemFram extends SuperFram{

    private static PaperSystem paperSystem = (MySpring.getBean("dao.PaperSystem"));
    private HashMap<String, Paper> hashMappaper=paperSystem.getHashMapPapers();//试卷缓存
    private HashMap<String, Paper> checkpaper=new HashMap<>();//检查试卷缓存
    private static String key=null;//索引
    private static int  text=0;

    //主窗口
    private static JFrame jFrame = new JFrame("题库系统");
    Class clazz;

    {//先添加进bean
        try {
            clazz = Class.forName("mypring.MySpring");
            Method method = clazz.getDeclaredMethod("getHashbean");
            method.setAccessible(true);
            HashMap hashMap= (HashMap) method.invoke(new MySpring());
            hashMap.put("javax.swing.JFrame",jFrame);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    //三大区域
    private JPanel papersPanel=new JPanel();
    private JPanel underPanel =new JPanel();
    private JPanel rightPanel=new JPanel();

    //papers区域
     private JPanel questionPanel=new JPanel();
        private JTextArea questionsTextArea=new JTextArea("\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t");
        private JScrollPane questionsScrollPane =new JScrollPane(questionsTextArea);//滚动条
        private JButton question1=new JButton("以下a哪个是java的基本数据类型？<br>A.int<br>B.Float<br>C.Boolean<br>D.String<ar>A<ar>1.jpg");
        private JButton question2=new JButton();
        private JButton question3=new JButton();
        private JButton question4=new JButton();

    //下方区域
    private JButton prevButton =new JButton("上一页");
    private JLabel nowLabel =new JLabel("第一页");
    private JButton nextButton =new JButton("下一页");
    private JTextField questionTextFile=new JTextField();
    private JButton checkButton=new JButton("查询");
    private JButton recheckButton=new JButton("查询返回");

    private JButton addButton=new JButton("添加");
    private JButton delekButton=new JButton("删除");
    private JButton upkButton=new JButton("修改");
    private JButton exitkButton=new JButton("退出");
    private JButton reLoginButton=new JButton("重新登陆");


    //右方区域
    private JPanel picturesPanel=new JPanel();
    private JLabel picture1=new JLabel();
    private JLabel picture2=new JLabel();
    private JLabel picture3=new JLabel();
    private JLabel picture4=new JLabel();


    @Override
    protected void setSLC() {
        jFrame.setBounds(400,200,1120,680);
        papersPanel.setBounds(10,10,850,450);papersPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));papersPanel.setBackground(Color.white);
        underPanel.setBounds(10,470,850,170);underPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        rightPanel.setBounds(870,10,230,630);rightPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

        //papers区域
        questionsTextArea.setFont(new Font("黑体",Font.TYPE1_FONT,24));questionsTextArea.setEnabled(false);questionsScrollPane.setBounds(0,0,850,450);
        question1.setBounds(10,10,1900,90);question1.setFont(new Font("宋体",Font.TYPE1_FONT,32));question1.setBorder(new EtchedBorder(EtchedBorder.LOWERED));question1.setBackground(Color.CYAN);
        question2.setBounds(10,110,1900,90);question2.setFont(new Font("黑体",Font.TYPE1_FONT,32));question2.setBorder(new EtchedBorder(EtchedBorder.LOWERED));question2.setBackground(Color.CYAN);
        question3.setBounds(10,210,1900,90);question3.setFont(new Font("黑体",Font.TYPE1_FONT,32));question3.setBorder(new EtchedBorder(EtchedBorder.LOWERED));question3.setBackground(Color.CYAN);
        question4.setBounds(10,310,1900,90);question4.setFont(new Font("黑体",Font.TYPE1_FONT,32));question4.setBorder(new EtchedBorder(EtchedBorder.LOWERED));question4.setBackground(Color.CYAN);
        //下方区域
        prevButton.setBounds(100,10,200,40);prevButton.setFont(new Font("宋体",Font.ITALIC,24));
        nowLabel.setBounds(300,10,240,40);nowLabel.setFont(new Font("黑体",Font.ITALIC,24));nowLabel.setHorizontalAlignment(JTextField.CENTER);
        nextButton.setBounds(540,10,200,40);nextButton.setFont(new Font("宋体",Font.ITALIC,24));
        addButton.setBounds(100,60,200,40);addButton.setFont(new Font("宋体",Font.ITALIC,24));
        delekButton.setBounds(320,60,200,40);delekButton.setFont(new Font("宋体",Font.ITALIC,24));
        upkButton.setBounds(540,60,200,40);upkButton.setFont(new Font("宋体",Font.ITALIC,24));
        questionTextFile.setBounds(100,110,420,40);questionTextFile.setFont(new Font("宋体",Font.ITALIC,24));
        checkButton.setBounds(540,110,200,40);checkButton.setFont(new Font("宋体",Font.ITALIC,24));
        recheckButton.setBounds(750,110,90,40);recheckButton.setFont(new Font("宋体",Font.ITALIC,14));

        //右边区域（图片）
        picture1.setBounds(10,10,210,145);picture1.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        picture2.setBounds(10,165,210,145);picture2.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        picture3.setBounds(10,320,210,145);picture3.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        picture4.setBounds(10,475,210,145);picture4.setBorder(new EtchedBorder(EtchedBorder.LOWERED));

    }

    @Override
    protected void setService() {
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //弹出新框
               String s= JOptionPane.showInputDialog("请以：题干<br>A....<br>B....<br>C....<br>D....<ar>答案<ar>照片名字");
               if (s!=null) {
                   //查找字符出现次数并且判断
                   PaperSystem paperSystem=MySpring.getBean("dao.PaperSystem");
                   int[] count=paperSystem.getStringCount(s);
                   if ( count[0]== 4 && (count[1] == 1 || count[1] == 2)) {//题目正确
                       paperSystem.addData(s);
                   }else{
                       JOptionPane.showMessageDialog(PapersSystemFram.this,"输入数据有误，请按照规则重新输入！！！！");
                   }
               }
            }
        });
        delekButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int k=JOptionPane.showConfirmDialog(null,"即将删除数据，确认保存吗？");
                JOptionPane.setRootFrame(new JFrame());
                if(k==0) {
                    hashMappaper.remove(key);//删除此题
                    //确认后修改缓存，把缓存的重新写入文件中
                    PaperSystem paperSystem = MySpring.getBean("dao.PaperSystem");
                    paperSystem.saveData();
                }
            }
        });
        upkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paper nowpaper=hashMappaper.get(key.split("<ar>")[0]);
                //把对象传过去等修改,
                 //UpFram upFram=MySpring.getBean("view.UpFram");//不能利用反射，后面警告框对象会累加
                 UpFram upFram=new UpFram();
                 upFram.getpaper(nowpaper);//可接收也可不接收
                 upFram.init();
            }
        });
        prevButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text>0) text--;
                PapersSystemFram.this.setDelekButtonUpButton(false);
                PapersSystemFram.this.setQuestionColor();//选择前设置颜色相同
            }
        });
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (text<hashMappaper.size()/4)text++;
                PapersSystemFram.this.setDelekButtonUpButton(false);
                PapersSystemFram.this.setQuestionColor();//选择前设置颜色相同
            }
        });
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取查询字符
                String checkstr=questionTextFile.getText();
                if (checkstr!=null){
                    HashMap<String, Paper> temp=new HashMap<>();//检查出来的与总的兑换
                    for(Paper paper:hashMappaper.values()){
                        int cheL=(paper.getTitle()).length();
                        int recheL=((paper.getTitle()).replace(checkstr,"")).length();
                        if (cheL-recheL>0){//证明此对象含有信息
                            temp.put(paper.getTitle(),new Paper(paper.getTitle(),paper.getAnswer(),paper.getIcon()));
                        }
                    }
                    //替换hashmap
                    checkpaper=hashMappaper;
                    hashMappaper=temp;
                    addButton.setEnabled(false);
                    //查一次后防止再次查询，把查询按钮设置为不可用，查询返回按钮可用
                    recheckButton.setEnabled(true);
                    questionTextFile.setEnabled(false);
                    checkButton.setEnabled(false);
                }
                PapersSystemFram.this.setQuestionColor();//选择前设置颜色相同
                PapersSystemFram.this.setDelekButtonUpButton(false);
            }
        });
        recheckButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //替换回来试卷
                hashMappaper=checkpaper;
                checkButton.setEnabled(true);
                questionTextFile.setEnabled(true);
                addButton.setEnabled(true);
                recheckButton.setEnabled(false);
                PapersSystemFram.this.setQuestionColor();//选择前设置颜色相同
                PapersSystemFram.this.setDelekButtonUpButton(false);
            }
        });

        ActionListener actionListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PapersSystemFram.this.setQuestionColor();//选择前设置颜色相同
                PapersSystemFram.this.setDelekButtonUpButton(true);
                JButton btn= (JButton)e.getSource();
                btn.setBackground(Color.YELLOW);
                key=btn.getText();
            }
        };


        question1.addActionListener(actionListener);
        question2.addActionListener(actionListener);
        question3.addActionListener(actionListener);
        question4.addActionListener(actionListener);
        ////设置一个线程，此线程一直运行，刷新
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(true) {
                    try {
                        Thread.sleep(800);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    PapersSystemFram.this.setQuestionPanelInit();
                }
            }
        }).start();
    }

    @Override
    protected void addSS() {
        //papers
        papersPanel.setLayout(null);
        papersPanel.add(questionsScrollPane);
        questionsTextArea.add(question1);
        questionsTextArea.add(question2);
        questionsTextArea.add(question3);
        questionsTextArea.add(question4);
        //下方
        underPanel.setLayout(null);
        underPanel.add(prevButton);
        underPanel.add(nowLabel);
        underPanel.add(nextButton);
        underPanel.add(addButton);
        underPanel.add(delekButton);
        underPanel.add(upkButton);
        underPanel.add(questionTextFile);
        underPanel.add(checkButton);
        underPanel.add(recheckButton);

        //右边
        rightPanel.setLayout(null);
        rightPanel.add(picture1);
        rightPanel.add(picture2);
        rightPanel.add(picture3);
        rightPanel.add(picture4);

        //集成所有面板
        jFrame.setLayout(null);
        jFrame.add(papersPanel);
        jFrame.add(underPanel);
        jFrame.add(rightPanel);
        System.out.println("---");
    }

    @Override
    public void init() {
        this.setSLC();
        this.addSS();
        this.setService();
        this.setDelekButtonUpButton(false);
        this.recheckButton.setEnabled(false);
        this.setQuestionPanelInit();
        jFrame.setResizable(false);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);

    }
    //设置删除修改可用状态
    private void setDelekButtonUpButton(boolean f){
        delekButton.setEnabled(f);
        upkButton.setEnabled(f);
    }

    //设置初始化数据
    public void setQuestionPanelInit(){
        ArrayList<Paper> arrayList=new ArrayList<>();//试卷缓存 副
        //遍历paper
        for (Paper paper:hashMappaper.values()) {
            arrayList.add(paper);
        }
        //第1页
        if (text*4<hashMappaper.size()&&arrayList.get(0 + text * 4) != null) {
            question1.setText((arrayList.get(0 + text * 4)).getTitle());
            this.showpicture((arrayList.get(0 + text * 4)).getTitle(),picture1);
        }else {
            question1.setText(null);
            this.showpicture(null,picture1);
        }
        if (text*4+1<hashMappaper.size()&&arrayList.get(1 + text * 4) != null) {
            question2.setText((arrayList.get(1 + text * 4)).getTitle());
            this.showpicture((arrayList.get(1+ text * 4)).getTitle(),picture2);
        }else{
            question2.setText(null);
            this.showpicture(null,picture2);
        }
        if (text*4+2<hashMappaper.size()&&arrayList.get(2 + text * 4) != null) {
            question3.setText((arrayList.get(2 + text * 4)).getTitle());
            this.showpicture((arrayList.get(2 + text * 4)).getTitle(),picture3);
        }else{
            question3.setText(null);
            this.showpicture(null,picture3);
        }
        if (text*4+3<hashMappaper.size()&&arrayList.get(3 + text * 4) != null) {
            question4.setText((arrayList.get(3 + text * 4)).getTitle());
            this.showpicture((arrayList.get(3 + text * 4)).getTitle(),picture4);
        }else{
            question4.setText(null);
            this.showpicture(null,picture4);
        }


        nowLabel.setText("第"+(text+1)+"页");
    }

    public void showpicture(String key,JLabel jPicture){
        Paper p=hashMappaper.get(key);
        String pic=null;
        if(p!=null) { pic= p.getIcon();}
        if (pic != null) {
                ImageIcon imageIcon = new ImageIcon("src//hamu//" + pic);
                imageIcon.setImage(imageIcon.getImage().getScaledInstance(210, 145, Image.SCALE_DEFAULT));
                jPicture.setIcon(imageIcon);
        } else {
                jPicture.setIcon(null);
        }

    }
    public  void setQuestionColor(){
        question1.setBackground(Color.CYAN);
        question2.setBackground(Color.CYAN);
        question3.setBackground(Color.CYAN);
        question4.setBackground(Color.CYAN);
    }
}
