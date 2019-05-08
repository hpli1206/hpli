package view;

import dao.Paper;
import dao.PaperDao;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ExamFrame extends SuperFram {
    //时间线程标记
    private boolean fl=true;
    private int times=75;
    private int hours=times/60;
    private int min=times%60;
    private int s=0;
    Thread thread;

    //存储学生试卷、答案集合、总题数、当前题号、已答题数、未答题数；与考试学生名字、倒计时（min）
    private String name=null;
    private int total=7;
    String[] answers=new String[total];
    private int count;
    private int num=total;

    ArrayList<Paper> papers= PaperDao.getPapers(total);//试卷


    //创建当前窗口组件属性
    private JFrame jFrame=new JFrame("考试开始，请答题！！！");
    private JPanel questionPanel=new JPanel();
    private JPanel optionPanel=new JPanel();
    private JPanel rightPanel =new JPanel();
    //题目面板
    private JTextArea questArea=new JTextArea();//文本域
    private JScrollPane questionScrollPane=new JScrollPane(questArea);//滚动条
    //按钮面板
    private JButton aButton=new JButton("A");
    private JButton bButton=new JButton("B");
    private JButton cButton=new JButton("C");
    private JButton dButton=new JButton("D");
    private JButton prevButton=new JButton("上一题");
    private JButton submitButton=new JButton("提交");
    private JButton nextButton=new JButton("下一题");
    //右边面板
    private JLabel pictureLabel=new JLabel();
    //总题目数
    private JLabel totalLabel=new JLabel("总题目数:");
    private JTextField totalField=new JTextField("0");
    //当前题号
    private JLabel nowLabel=new JLabel("当前题号:");
    private JTextField nowField=new JTextField("0");
    //已答题
    private JLabel numLabel=new JLabel("已答题数:");
    private JTextField numField=new JTextField("0");
    //未答
    private JLabel unnumLabel=new JLabel("未答题数:");
    private JTextField unnumField=new JTextField("0");
    //时间
    private JLabel timeLabel=new JLabel("剩余时间:");
    private JLabel realtimeLabel=new JLabel("00:00:00");
    public ExamFrame(){

    }
    public ExamFrame(String name){
        this.name=name;
    }

    public void setName(String name){
        this.name=name;
    }

    @Override
    protected void setSLC() {
        //设置窗口属性
        jFrame.setBounds(400,200,1120,680);

        //题目面板
        questionPanel.setBounds(10,10,750,400);questionPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));questionPanel.setBackground(Color.white);
        optionPanel.setBounds(10,420,750,220);optionPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        rightPanel.setBounds(770,10,330,630);rightPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        questArea.setFont(new Font("黑体",Font.TYPE1_FONT,24));questArea.setEnabled(false);questionScrollPane.setBounds(0,0,750,400);
        //按钮面板
        aButton.setBounds(80,30,120,45);aButton.setFont(new Font("宋体",Font.ITALIC,32));
        bButton.setBounds(230,30,120,45);bButton.setFont(new Font("宋体",Font.ITALIC,32));
        cButton.setBounds(380,30,120,45);cButton.setFont(new Font("宋体",Font.ITALIC,32));
        dButton.setBounds(530,30,120,45);dButton.setFont(new Font("宋体",Font.ITALIC,32));
        prevButton.setBounds(140,125,120,45);prevButton.setFont(new Font("宋体",Font.ITALIC,24));
        submitButton.setBounds(310,125,120,45);submitButton.setFont(new Font("宋体",Font.ITALIC,24));submitButton.setForeground(Color.red);
        nextButton.setBounds(480,125,120,45);nextButton.setFont(new Font("宋体",Font.ITALIC,24));

        //右边面板
        pictureLabel.setBounds(10,10,310,240);pictureLabel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        totalLabel.setBounds(20,280,120,45);totalLabel.setFont(new Font("宋体",Font.ITALIC,24));
        totalField.setBounds(130,280,180,45);totalField.setFont(new Font("宋体",Font.ITALIC,24));totalField.setHorizontalAlignment(JTextField.CENTER);totalField.setEnabled(false);
        nowLabel.setBounds(20,340,120,45);nowLabel.setFont(new Font("宋体",Font.ITALIC,24));
        nowField.setBounds(130,340,180,45);nowField.setFont(new Font("宋体",Font.ITALIC,24));nowField.setHorizontalAlignment(JTextField.CENTER);nowField.setEnabled(false);
        numLabel.setBounds(20,400,120,45);numLabel.setFont(new Font("宋体",Font.ITALIC,24));
        numField.setBounds(130,400,180,45);numField.setFont(new Font("宋体",Font.ITALIC,24));numField.setHorizontalAlignment(JTextField.CENTER);numField.setEnabled(false);
        unnumLabel.setBounds(20,460,120,45);unnumLabel.setFont(new Font("宋体",Font.ITALIC,24));
        unnumField.setBounds(130,460,180,45);unnumField.setFont(new Font("宋体",Font.ITALIC,24));unnumField.setHorizontalAlignment(JTextField.CENTER);unnumField.setEnabled(false);
        timeLabel.setBounds(50,520,180,45);timeLabel.setFont(new Font("黑体",Font.BOLD,32));timeLabel.setForeground(Color.GREEN);
        realtimeLabel.setBounds(100,560,180,45);realtimeLabel.setFont(new Font("宋体",Font.BOLD,32));realtimeLabel.setForeground(Color.RED);




    }

    @Override
    protected void setService() {//监听按钮

        //提交
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fl=false;
                //设置遮挡效果
                questArea.setText("遮挡中。。。。");
                int action= JOptionPane.showConfirmDialog(ExamFrame.this,"是否确认提交！！！");
                if (action==0){//确认提交，与真实答案进行比对，得出分数
                   float result=ExamFrame.this.getresult();
                   questArea.setText(name+"同学本次考试得分为："+result+"分！");
                   nextButton.setEnabled(false);
                   prevButton.setEnabled(false);
                   ExamFrame.this.setButtoneEnable(false);
                   submitButton.setEnabled(false);

                }else {
                   fl=true;
                   ExamFrame.this.descTime(fl);
                    questArea.setText(count+"."+papers.get(count-1).getTitle().replace("<br>","\n      "));

                }
            }
        });

        //上一题
        prevButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ExamFrame.this.setButtoneEnable(true);nextButton.setEnabled(true);
                ExamFrame.this.setButtoneDefaultColor();
                if (count>1){
                    ExamFrame.this.setQuestion("prev");
                }
                if (count==1){prevButton.setEnabled(false);}
                ExamFrame.this.reButton();
            }
        });
        //下一题
        nextButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExamFrame.this.setButtoneEnable(true);prevButton.setEnabled(true);
                ExamFrame.this.setButtoneDefaultColor();
                if (count<total){
                    ExamFrame.this.setQuestion("next");
                    if(count==total){
                        nextButton.setEnabled(false);
                    }
                }
                ExamFrame.this.reButton();

            }
        });

        ActionListener actionListener =new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ExamFrame.this.setButtoneDefaultColor();
                ((JButton)e.getSource()).setBackground(Color.CYAN);
                answers[count-1] =((JButton)e.getSource()).getText();
                for (int i = 0; i < answers.length; i++) {
                    System.out.println(answers[i]);
                }
                ExamFrame.this.setQuestion(" ");

            }
        };
        aButton.addActionListener(actionListener);
        bButton.addActionListener(actionListener);
        cButton.addActionListener(actionListener);
        dButton.addActionListener(actionListener);
    }

    @Override
    protected void addSS() {
        //题目面板添加
        questionPanel.setLayout(null);
        questionPanel.add(questionScrollPane);

        //按钮面板添加
        optionPanel.setLayout(null);
        optionPanel.add(aButton);
        optionPanel.add(bButton);
        optionPanel.add(cButton);
        optionPanel.add(dButton);
        optionPanel.add(prevButton);
        optionPanel.add(submitButton);
        optionPanel.add(nextButton);

        //右边面板添加
        rightPanel.setLayout(null);
        rightPanel.add(pictureLabel);
        rightPanel.add(totalLabel);
        rightPanel.add(totalField);
        rightPanel.add(nowLabel);
        rightPanel.add(nowField);
        rightPanel.add(numLabel);
        rightPanel.add(numField);
        rightPanel.add(unnumLabel);
        rightPanel.add(unnumField);
        rightPanel.add(timeLabel);
        rightPanel.add(realtimeLabel);



        jFrame.setLayout(null);
        jFrame.add(questionPanel);
        jFrame.add(optionPanel);
        jFrame.add(rightPanel);
        jFrame.setResizable(false);

    }

    @Override
    public void init() {
        this.setSLC();
        this.addSS();
        this.setService();
        //配置好组件位置后，设置窗口可见
        this.setQuestion("next");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
        this.descTime(true);
    }

    //==========================================================创建方法

    //时间线程
    private void descTime(boolean flag){
        thread=new Thread(new Runnable() {
            @Override
            public void run() {
                while(fl){
                    try {
                        Thread.sleep(1000);
                        s--;
                        if (s<0){
                            s=59;
                            min--;
                            if (min<0){
                                min=59;
                                hours--;
                            }
                        }

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }finally {
                        StringBuilder stringBuilder=new StringBuilder();
                        if (hours>=0&&hours<10){
                            stringBuilder.append(0);stringBuilder.append(hours);
                        }else {
                            stringBuilder.append(hours);
                        }
                        stringBuilder.append(":");
                        if (min>=0&&min<10){
                            stringBuilder.append(0);stringBuilder.append(min);
                        }else {
                            stringBuilder.append(min);
                        }
                        stringBuilder.append(":");
                        if (s>=0&&s<10){
                            stringBuilder.append(0);stringBuilder.append(s);
                        }else {
                            stringBuilder.append(s);
                        }
                        System.out.println(stringBuilder.toString());
                        ExamFrame.this.realtimeLabel.setText(stringBuilder.toString());
                    }

                    if (hours==min&min==s&&s==0){//考试时间到,按钮不可用，自动提交试卷
                        ExamFrame.this.setButtoneEnable(false);
                        nextButton.setEnabled(false);
                        prevButton.setEnabled(false);
                        JOptionPane.showMessageDialog(ExamFrame.this,"考试时间已到，请按确定查看分数！");
                        float result=ExamFrame.this.getresult();
                        questArea.setText(name+"同学本次考试得分为："+result+"分！");
                        fl=false;
                    }

                }
            }
        });
        thread.start();

    }


    //比对分数
    private  float getresult(){
        float result=100;
        for (int i = 0; i <answers.length ; i++) {
            if (!(answers[i]!=null&&answers[i].equals(papers.get(i).getAnswer().toUpperCase())))result-=100/total;
        }
        return result;

    }

    //展示上一次选择
    private  void  reButton( ){
            ExamFrame.this.setButtoneDefaultColor();
            if(answers[count-1]!=null&&answers[count-1].equals("A")){aButton.setBackground(Color.CYAN);}
            if(answers[count-1]!=null&&answers[count-1].equals("B")){bButton.setBackground(Color.CYAN);}
            if(answers[count-1]!=null&&answers[count-1].equals("C")){cButton.setBackground(Color.CYAN);}
            if(answers[count-1]!=null&&answers[count-1].equals("D")){dButton.setBackground(Color.CYAN);}

    }

    //展示当前题目
    private void setQuestion(String pn){
        switch (pn){
            case "prev":

                questArea.setText(--count+"."+papers.get(count-1).getTitle().replace("<br>","\n      "));
                break;
            case "next":
                questArea.setText(count+ 1+ "."+papers.get(count++).getTitle().replace("<br>","\n      "));
                 break;
            default:

        }
        totalField.setText(String.valueOf(total));
        nowField.setText(String.valueOf(count));
        for (String s:answers){
            if(s==null){
                num--;
            }
        }
        numField.setText(String.valueOf(num));
        unnumField.setText(String.valueOf(total-num));
        num=total;
        String pic=papers.get(count-1).getIcon();
        if (pic!=null){
            ImageIcon imageIcon= new ImageIcon("src//hamu//"+pic);
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(310,240,Image.SCALE_DEFAULT));
            pictureLabel.setIcon(imageIcon);
        }else{
            pictureLabel.setIcon(null);

        }
    }
    //设置按钮可用
    private  void setButtoneEnable(boolean flag){
        aButton.setEnabled(flag);
        bButton.setEnabled(flag);
        cButton.setEnabled(flag);
        dButton.setEnabled(flag);



    }

    //设置按钮默认颜色
    private void setButtoneDefaultColor(){
        aButton.setBackground(null);
        bButton.setBackground(null);
        cButton.setBackground(null);
        dButton.setBackground(null);

    }

}
