####  TreeMap底层数据结构：TreeMap底层数据结构是红黑树;
#####  红黑树特性：

 1. 每个节点都只能是红色或者黑色；
 2. 根节点是黑色；
 3. 每个叶节点（NLL节点，空节点）是黑色的；
 4. 如果一个节点是红色的，那么它的两个子节点都是黑的。也就是说在一条路径上不能出现相邻的两个红色节点；
 5. 从任一节点到其每个叶子的所有路径都包含相同数目的黑色节点；

#####  特点：

 - [ ] TreeMap是非线程安全的 ：但是可以用如下如下方式设置为线程安全：

```java
   Map m=Collections.synchronizedSortedMap(new TreeMap()); 来实现线程安全；
```

 - [ ] TreeMap是用键进行排序的，默认采用升序排序；通过Comparable或Comparator来排序；
     TreeMap是SortedMap接口基于红黑树的实现。此类保证了映射按照升序排列关键字，根据使用的构造方法不同，可能会按照键的类的自然顺序进行排序，或者按照创建时所提供的比较器（自定义）进行排序；
     
 - [ ] 允许值重复，不允许键重复；
 - [ ] 键不可以为null（如果比较器对null做了处理，就可以为null），值可以为null；

#####   TreeMap的应用：

 - [ ] 自定义排序：
 

```java
  Map<Integer,Integer> map = new TreeMap<>();
        map.put(1, null);
        map.put(10, 1);
        map.put(3, 2);
        map.put(2, 3);
        
        for(Map.Entry<Integer, Integer> entry:map.entrySet())
        {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }

       //输出结果
       // 1:null
       // 2:3
       // 3:2
       // 10:1
```

 - [ ] Comparable 排序：只要自定义类实现了Comparable接口，就可以实现排序；
 

```java

    public class Person1 implements Comparable<Person1>
{
    private int age;
    private String name;
 
    public Person1(String name, int age)
    {
        this.name = name;
        this.age = age;
    }
 
 
    @Override
    public int compareTo(Person1 o)
    {
        return this.age-o.age;
    }
 
    @Override 
    public String toString()
    {
        return name+":"+age;
    }
}
 public class TreeMapDemo{
    public static void main(String [] args){
        Map<Person1,Integer> map = new TreeMap<>();
        Person1 person1 = new Person1("zzh",18);
        Person1 person2 = new Person1("jj",17);
        Person1 person3 = new Person1("qq",19);
        map.put(person1, 1);
        map.put(person2, 2);
        map.put(person3, 3);
        for(Entry<Person1, Integer> entry:map.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());
        }

     }
 }    


  //测试结果：
  //jj:17:2
  //zzh:18:1
  //qq:19:3

```

 - [ ] Comparator 排序：只要在创建TreeMap的构造函数中声明一个Comparator接口的实现

  

```java

   public final class Person2
{
    private int age;
    private String name;
 
    public Person2(String name, int age)
    {
        this.name = name;
        this.age = age;
    }
 
    @Override 
    public String toString()
    {
        return name+":"+age;
    }
  
}

  public class TreeMapDemo{
    public static void main(String [] args){

      Map<Person2,Integer> map2 = new TreeMap<>(new Comparator<Person2>(){
            @Override
            public int compare(Person2 o1, Person2 o2)
            {
                if(o1 == null || o2 == null)
                    return 0;
                return o1.getAge()-o2.getAge();
            }
        });
        Person2 p1 = new Person2("zzh",18);
        Person2 p2 = new Person2("jj",17);
        Person2 p3 = new Person2("qq",19);
        map2.put(p1, 1);
        map2.put(p2, 2);
        map2.put(p3, 3);
        for(Entry<Person2, Integer> entry:map2.entrySet())
        {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
   }
}
   
   //处理结果：
   //jj:17:2
   //zzh:18:1
   //qq:19:3
 
```

####  HashMap与TreeMap的区别：
#####   实现方式

 - [ ] HashMap:基于哈希表实现。使用HashMap要求添加的键类明确定义了HashCode()和equals()方法（可以重写这两个方法），为了优化HashMap空间的使用，你可以调优初始容量和负载因子。
 - [ ] TreeMap：基于红黑树实现。TreeMap没有调优选项，因为该树总处于平衡状态；
#####  用途：
 - [ ] HashMap:适用于在Map中插入，删除和定位元素；
 - [ ] TreeMap:适用于按照自然顺序或者自定义顺序遍历键（key）；
 - [ ] HashMap通常比TreeMap快一点（数和哈希表的数据结构使然），建议多使用HashMap,在排序的时候在使用TreeMap;

####   TreeSet:TreeSet实现了对TreeMap的封装， 应用方法都一样；值都为new Object();  键不能重复；
#####   TreeSet排序与TreeMap一样，下面只给出Comparator比较器排序方式；

```java
   package collection.Map;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Created with IntelliJ IDEA
 * @Description:
 * @Package: collection.Map
 * @author: FLy-Fly-Zhang
 * @Date: 2019/2/27
 * @Time: 19:38
 */
class Preson{
    int val;
    String s;
}

public class TreeMapDemo {
    public static void main(String[] args) {
        //Comparator排序
        TreeMap<Preson,Integer> treeMap=new TreeMap<>(new Comparator<Preson>() {
            @Override
            public int compare(Preson o1, Preson o2) {
                return 0;
            }
        });
      
        //迭代器和别的map集合用法一样
        Iterator<Map.Entry<Preson,Integer>> iterator=treeMap.entrySet().iterator();
        Iterator iterator1=treeMap.keySet().iterator();
        Iterator iterator2=treeMap.values().iterator();
    }
}

```

#####  两者主要不同是应用场景不同，TreeSet的应用场景是实现key值去重外加对key值进行排序；
