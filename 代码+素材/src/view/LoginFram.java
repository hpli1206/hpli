package view;

import dao.UserDao;
import mypring.MySpring;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFram extends SuperFram {
    private JFrame jFrame=new JFrame("考试登陆");
    private JPanel jPanel=new JPanel();
    private JLabel titleLabel=new JLabel("考 试 系 统 面 板");
    private JLabel accountLabel=new JLabel("账户:");
    private JTextField accountField=new JTextField();
    private JLabel passwordLabel =new JLabel("密码:");
    private JPasswordField passwordField=new JPasswordField();
    private JButton loginButton=new JButton("登陆");
    private JButton exitButton=new JButton("退出");
    private JRadioButton teacherButton=new JRadioButton("老师");
    private JRadioButton studentButton=new JRadioButton("学生");
    private ButtonGroup ts=new ButtonGroup();
    //设置登陆模式
    private static String str;


    @Override
    protected void setSLC() {
        //设置组件jPanel排版模式为自定义
        jPanel.setLayout(null);
        //设置窗口+组件相对位置
        jFrame.setBounds(480,270,960,540);
        titleLabel.setBounds(200,40,560,60);titleLabel.setFont(new Font("宋体",Font.TYPE1_FONT,59));
        accountLabel.setBounds(160,180,100,40);accountLabel.setFont(new Font("宋体",Font.ITALIC,32));
        accountField.setBounds(290,180,400,40);accountField.setFont(new Font("宋体",Font.ITALIC,32));
        passwordLabel.setBounds(160,240,100,40);passwordLabel.setFont(new Font("宋体",Font.ITALIC,32));
        passwordField.setBounds(290,240,400,40);passwordField.setFont(new Font("宋体",Font.ITALIC,32));
        loginButton.setBounds(320,330,100,40);loginButton.setFont(new Font("宋体",Font.ITALIC,32));
        exitButton.setBounds(520,330,100,40);exitButton.setFont(new Font("宋体",Font.ITALIC,32));
        teacherButton.setBounds(350,400,100,40);teacherButton.setFont(new Font("黑体",Font.TYPE1_FONT,24));
        studentButton.setBounds(490,400,100,40);studentButton.setFont(new Font("黑体",Font.TYPE1_FONT,24));
        //设置窗口状态
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);


    }

    @Override
    protected void setService() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) { //如果点击登陆，获取文本框内的密码和数据库进行比对
                if(str.equals("学生")){
                    String result= UserDao.getResult(accountField.getText(), new String(passwordField.getPassword()));
                    if (result=="登陆成功"){//弹出新窗口，登陆窗口隐藏
                        jFrame.setVisible(false);
                        //反射创建对象
                        ExamFrame examFrame= MySpring.getBean("view.ExamFrame");
                        examFrame.setName(accountField.getText());
                        System.out.println(examFrame.getName());
                        examFrame.init();
                        //正常创建对象
                        //new ExamFrame(accountField.getText()).init();
                    }else{
                        JOptionPane.showMessageDialog(LoginFram.this,result);
                    }
                }
                if(str.equals("老师")){
                    String result= UserDao.getResult(accountField.getText(), new String(passwordField.getPassword()));
                    if (result=="登陆成功"){//弹出新窗口，登陆窗口隐藏
                        //老师窗口
                        jFrame.setVisible(false);
                        PapersSystemFram papersSystemFram=MySpring.getBean("view.PapersSystemFram");
                        papersSystemFram.init();
                    }else{
                        JOptionPane.showMessageDialog(LoginFram.this,result);
                    }

                }
            }

        });
        ActionListener actionListener=new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ts=((JRadioButton)(e.getSource())).getText();
              if (ts!=null){
                  str=ts;//老师或者学生
                  //打开登陆退出按钮可用状态
                  LoginFram.this.enableButton(true);
              }

            }
        };
        teacherButton.addActionListener(actionListener);
        studentButton.addActionListener(actionListener);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }
    @Override
    protected void addSS()
    {
        ts.add(teacherButton);
        ts.add(studentButton);
        jPanel.add(titleLabel);
        jPanel.add(accountLabel);
        jPanel.add(accountField);
        jPanel.add(passwordLabel);
        jPanel.add(passwordField);
        jPanel.add(loginButton);
        jPanel.add(exitButton);
        jPanel.add(teacherButton);
        jPanel.add(studentButton);
        jFrame.add(jPanel);
    }

    @Override
    public void init() {
        this.setSLC();
        this.addSS();
        this.setService();
        this.enableButton(false);
        //配置好组件位置后，设置窗口可见

        jFrame.setVisible(true);

    }

    //设计方法，登陆面板按钮的可用状态
    public void enableButton(boolean falg){
        loginButton.setEnabled(falg);
    }


}
