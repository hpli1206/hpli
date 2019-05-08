package mypring;
import java.util.HashMap;
public  class MySpring {
    //存储所有对象
    private static HashMap<String,Object> hashbean=new HashMap<>();
    public static  <T>T getBean(String className){
        T obj=null;
        try {
            obj= (T)hashbean.get(className);
            if(obj==null){  //判断原map里是否有对象
                //通过类名获取Class
                Class clazz=Class.forName(className);
                //反射产生一个对象
                obj=(T)clazz.newInstance();
                hashbean.put(className,obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    private HashMap<String,Object> getHashbean(){
        return  hashbean;
    }

}
