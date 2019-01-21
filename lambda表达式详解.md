######  lambda表达式是一个可传递的代码块，可以在以后执行一次或者多次。 lambda表达式是闭包的； 
####  lambda表达式的语法：

 

 1. 参数，箭头（->）以及一个表达式。
  
 


```
（String first，String second）-> first.length()-second.length()
```


 2. 如果代码要完成的计算无法放在一个表达式中，可以跟写函数一样，把这些代码放在{}中

```
（String first,String second）->{
            if(  first.length()<second.length()  )  
                     return -1
            else if(  first.length()>second.length()  )
                     return 1;         
            else   return 0;
           }           
```

 3. 如果没有参数，仍然需要提供空括号，就像无参数函数一样；
   

```
 （）-> System.out.println("HelloWorld")
 
```

 4. 如果可以推导出一个lambda表达式的参数类型，可以忽略其类型；
  

```
      //编译器可以推导出两个参数的类型为字符串
     Comparator<String>  com= (first ,second)-> first.length()-second.length()  
     
                                                   
```

 5. 如果方法只有一个参数，而且这个参数的类型可以推导得出，那么可以省略小括号。
  

```
Timer t=new Timer(10000,  event ->
        System.out.println("间隔时间为"+new Date(0) )   )
        t.start();
//  下面为普通的写法
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

 6. 无需指定lambda表达式的返回类型。他的返回式类型总可以由上下文推到得出；
 
 7. 注意：如果一个lambda表达式只在某些分支返回一些值，而另一些分支不返回，那么这是不合法的；
####  函数式接口：对于只有一个抽象方法的接口，需要这个接口的对象时，我们以前可能通过匿名内部类来实现，现在我们可以通过lambda表达式来实现。
######  注意：并不是所有的接口都有抽象方法，因为接口完全有可能重新声明Object类的方法；这些声明会让方法不再是抽象的。在java1.8版本后，接口是可以声明非抽象方法的。
 - [ ] java.util.function 包中有一个非常有用的接口

```
public interface Predicate<T>{  //判断
       boolean test(T t);
}
```

 - [ ] ArrayList类有一个removeIf方法，他的参数就是一个Predicate。这个接口就是专门用来传递lambda表达式的。

```
      list.removeIf( e -> e==null ) ;//判断e是不是等于null,如果是移除；
```
####   方法引用：有时，可能已经有现成的方法可以完成你想要传递到其他代码中的动作。

 - [ ]  Object::instanceMethod  //实例方法（类中调用）
 - [ ] class::staticMethod  //静态方法
     

```
//两两作用相等
Timer t=new Timer(10000, event - >System.out.println(event) );
Timer t=new Timer(10000, System.out::println);


int num=（x,y）->Math.pow(x,y);
int num=  Math::pow;
```

 - [ ] class::instanceMethod   //实例方法 （类外调用）
 

```
(x,y)->x.compareToIgnoreCase(y);
String::compareToIgnoreCase;
```

 - [ ]  注意：如果有多个同名的方法，也就是发生了重载，那么编译器就会根据你调用的时传入的参数类型联系上下文进行查找
 - [ ] 可以在方法引用中使用this参数和super参数
 

```

this::equals 
x->this.equals(x)

```
####   构造器引用：构造器和方法引用类似，不过方法名为new。至于用哪个构造器，取决于你传入的参数；

```
Person  p=new Person();
Person p= Person::new;
```

 - [ ] 可以用数组建立构造器引用：

```
x-> new int [x]  
int [] ::new
```

 - [ ] 不能构造泛型类型的数组。数组构造器对于克服这个限制很有用。表达式 new T[ n ] 会产生错误，因为这会改为 new Object [n]  

####   变量作用域：lambda表达式可以捕获外围作用域中变量的值，要确保捕获的值是明确定义的，只能引用值而不能改变值。之所以有这个限制是有原因的，如果在lambda表达式中改变变量，并发执行多个动作时就会不安全。另外如果引用变量，而这个变量可能在外部发生变化，这也是不合法的。

 1. [ ] lambda表达式中捕获的变量必须实际上是最终变量。最终变量是指在它初始化之后就不会为它在赋新值。
 2. [ ] lambda表达式中声明一个与局部变量同名的参数也是不合法的。
 3. [ ] 方法中不能有同名变量，左移lambda表达式也不能有同名变量。
 4. [ ] lambda表达式中使用this关键字，是指创建这个lambda表达式的方法的this，也就是说lambda表达式总是嵌套在方法内，所以this参照函数的this；
######     如下例，this.toString()  ----   this调用的是Application对象的this。
```
public class Application{
          public void init(){
             ActionListener  listener = event -> {
                   System.out.println( this.toString() );
                   }
           }
}                   
```
####   处理lambda表达式
#####   lambda表达式的重点是延迟执行，如果需要直接执行，无需把它包装在lambda表达式当中。之所以希望以后执行原因如下：
 5. [ ] 在一个单独的线程中运行代码；
 6. [ ] 多次运行代码；
 7. [ ] 在算法的适当位置运行代码（排序中的比较操作）；
 8. [ ] 发生某种情况是运行代码（点击按钮，数据到达）；
 9. [ ] 在必要时运行代码；
#####   如果设计自己的接口，其中只有一个抽象方法，可以用  @functionalInterface 注解来标记这个接口。当然，没有也是可以的。
1. 优点1：如果无意增加了另一个非抽象方法，编译器会产生错误信息；
2. 优点2：javadoc页里会指出你的接口是一个函数式接口；

