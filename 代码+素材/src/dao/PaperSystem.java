package dao;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.HashMap;
public class PaperSystem {//用于修改题库的系统
    private static Class clazz;
    private static HashMap<String,Paper> papers;
    private static String saveDataName="src//filedata//Paper.txt";//保存文件路径名字
    static{
        try {
            clazz = Class.forName("dao.PaperDao");//获取类
            Method methodpapers=clazz.getDeclaredMethod("getHashPaper");//类的私有方法
            methodpapers.setAccessible(true);//设置为可用
            papers = (HashMap<String,Paper>) methodpapers.invoke(clazz.newInstance());//运行获取题库
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public  HashMap<String,Paper>  getHashMapPapers(){
        return papers;
    }
    public  void saveData(){
        BufferedWriter bufferedWriter=null;
        try {
            bufferedWriter=new BufferedWriter(new FileWriter(saveDataName));
            StringBuilder stringBuilder=new StringBuilder();
            for (Paper paper:papers.values()) {
                stringBuilder.append(paper.getTitle());
                stringBuilder.append("<ar>");
                stringBuilder.append(paper.getAnswer());
                stringBuilder.append("<ar>");
                stringBuilder.append(paper.getIcon());
                stringBuilder.append("\n");
            }
            bufferedWriter.write(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedWriter!=null){
                try {
                    bufferedWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

    }
    public  void addData(String str){

        BufferedWriter bufferedWriter=null;
        try {
            bufferedWriter=new BufferedWriter(new FileWriter(saveDataName,true));
            String[] pap= str.split("<ar>");
            //更新缓存
            if (pap.length==3) {
                papers.put(pap[0], new Paper(pap[0], pap[1], pap[2]));
                System.out.println(3);
            }
            if (pap.length==2) {
                papers.put(pap[0], new Paper(pap[0], pap[1]));
                System.out.println(2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bufferedWriter!=null){
                try {
                    bufferedWriter.close();
                    //确保流关闭后再打开进行保存
                    this.saveData();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //返回<br>、<ar>个数
    public  int[] getStringCount(String str){
        int count[]=new int[2];
        String str1=str.replace("<br>",""); //将字符串中i替换为空,创建新的字符串
        count[0]=(str.length()-str1.length())/4;
        String str2=str.replace("<ar>","");
        count[1]=(str.length()-str2.length())/4;
        return count;
    }

}
