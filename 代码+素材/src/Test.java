import mypring.MySpring;
import view.LoginFram;
public class Test {
    public static void main(String[] args) {
       LoginFram loginFram= MySpring.getBean("view.LoginFram");
       loginFram.init();

    }
}
