package dao;

import java.io.*;
import java.util.HashMap;

public class UserDao {
    private UserDao(){}
    private static HashMap<String,User> users=new HashMap<>();
    static {
        BufferedReader bufferedReader=null;
        try {
            bufferedReader=new BufferedReader(new FileReader("src//filedata//User.txt"));
            String readUsers=bufferedReader.readLine();
            while (readUsers!=null){
                String[] up=readUsers.split("&");
                users.put(up[0],new User(up[0],up[1]));
                readUsers=bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(bufferedReader!=null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static String getResult(String account,String password){
        if(users.get(account).getAccount().equals(account)){
            if (users.get(account).getPassword().equals(password)){
                return "登陆成功";
            }
        }
        return "用户名或密码错误";
    }
}
