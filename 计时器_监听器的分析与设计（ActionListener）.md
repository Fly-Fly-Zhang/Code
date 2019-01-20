####  回调：回调是一种常见的设计模式，在这种模式中，可以指出某个特定事件发生时应采取的动作；
#####   功能类分析：

 - 在java.swing包中有一个timer类，可以使用它在到达给定的时间间隔时发出通告或者说警告。在构造定时器时，需要设置一个时间间隔，并告知当达到时间间隔时需要做些什么。
 - 如何告知呢？ java的类库采用的是面向对象方法，可以将某个类的对象传递给定时器，然后定时器调用这个对象的方法。由于对象可以携带一些附加信息，所以传递一个对象比传递一个函数要灵活的多。
 - 不过定时器需要知道调用哪个方法，那么我们可以使用接口给它提供一个规范。timer类会固定调用ActionListener接口的actionPerformed()方法。 那么我们确定功能类的时候，可以让它实现ActionListener接口。
 - 需要注意的是actionPerformed()方法的ActionEvent参数，这个参数提供了事件的相关信息。
####   需要用到的函数解释
######   javax.swing.JOptionPane类
 - [ ]  static void showMessageDialog(Component parent,Object message)
         显示一个包含一条消息和Ok按钮的对话框，这个对话框将位于其parent组件的中央，如果parent 为null，对话框显示在中央。
###### javax.swing.Timer类         
 - [ ] Timer(int  interval,ActionListener list)
       构造一个定时器，传入毫秒时间，传入一个ActionListener接口的引用（多态的运用）
 - [ ] void static （）
        启动定时器，一旦启动将调用监听的actionperformed函数。
######   java.awt.ToolKit类
 - [ ] static Toolkit getDefaultToolKit()
        获得默认的工具箱。工具箱包含有关的GUI环境的信息。 
 - [ ] void beep()
 发出一声铃响

#####   System.exit(status)与return的区别

 1. System.exit(0)和System.exit(1)有什么区别？
     System.exit(status)：终止当前正在运行的虚拟机。里面的参数为0表明正常退出；非零则相反，一般放到catch块中进行处理。如果发生异常调用它来终止程序。
2. 在什么情况下执行System.exit()不会抛出异常？如果抛出了SecurityException异常，一般是因为什么原因？
       抛出SecurityException是因为你调用了不允许的操作。比如在Applet中操作本地文     件，或者在RMI程序中操作不允许的文件。Sun对于这个有专门的文章和解决方法对于java程序。
       运行System.exit()会终止JVM，所以Servlet和Applet中都不应该显示调用这个方法。。
3. 如果我想中止当前运行的java程序，除了执行System.exit()还能用什么方法？
    如果在main方法中System.exit(0)与return 没有区别，都是终止程序。 
    如果是别的方法,那System.exit(0)直接终止程序,就算后面有代码也不执行了 
而return则返回至调用该方法的地方,如果后面还有代码则继续执行。

```
package sum;
import java.awt.*;
import java.awt.event.*;
import javax.swing.Timer;
import java.util.Date;
import javax.swing.*;

/**
 * @Created with IntelliJ IDEA
 * @Description:
 * @Package: sum
 * @author: FLy-Fly-Zhang
 * @Date: 2019/1/20
 * @Time: 15:12
 */
class TimePrinter  implements ActionListener {
    public void actionPerformed(ActionEvent evnt){
        System.out.println("At the tone,the Time is:"+new Date());  //当警告响起时，此时时间为
        Toolkit.getDefaultToolkit().beep();  //获得默认的工具箱，得到GUI环境的信息并发出一声铃响；

    }
}
public class TimerTest {

    public static void main(String [] args){
        ActionListener listener=new TimePrinter();
        Timer t=new Timer(10000,listener); //构造一个定时器，
        t.start(); //启动定时器；
        JOptionPane.showMessageDialog(null,"Quit program?"); //终止程序窗口
        System.exit(0); //终止当前正在运行的虚拟机。里面的参数为0表明正常退出；非零则相反，一般放到catch块中进行处理。如果发生异常调用它来终止程序。
    }

```

