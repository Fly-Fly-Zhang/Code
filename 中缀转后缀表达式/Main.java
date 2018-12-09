package tulun.work;

        import java.util.Scanner;

/**
 * @Created with IntelliJ IDEA
 * @Description:
 * @Package: tulun.work
 * @author: FLy-Fly-Zhang
 * @Date: 2018/12/4
 * @Time: 0:39
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("-------------请输入运算公式-------------");
        String s;//="2+3*5-4*(5-3)";
        Scanner scan=new Scanner(System.in);
        s=scan.nextLine();
        Operate operate=new Operate(s);
        operate.start();

    }
}
