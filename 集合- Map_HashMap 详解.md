##    [HashMap源码详解（增删改查）请点击](https://blog.csdn.net/Fly_Fly_Zhang/article/details/87902090)
####   Map接口:

 - [ ] 数据结构：哈希表；
 - [ ] 哈希表：通过关键码来映射到值的一个数据结构；
 - [ ] 哈希函数：键与值映射的一个映射关系；
#####  哈希函数寻址方法：有多种，不局限于这两种
 - [ ] 直接寻址法：f(X)=kx+b（k,b都是常数）f(x)为插入位置；
 - [ ] 除留余数法：f(x)=x%k  (k<=m ,m为存储位置长度)；
#####  哈希冲突：m!=n =>>    f(m)==f（n）;也就是说通过某种寻址方法，x不一样，但是f(x)一种，这样造成了一个位置有多个元素的问题，这就叫哈希冲突；
#####   哈希冲突解决方案：
 - [ ] 链地址法：每个键值位置用链表相连：（源码主要用链表）
 - [ ] 探测法：分为线性探测（+1）查找；随机探测（跳n个元素查找），就是说，如果发生冲突，那么就通过+1或者+n看此时位置上有没有键，如果没有则存放在此位置，如果有继续寻找；

####   HashMap  
####  特点：

 - [ ] 键不可以重复，值可以重复；如果重复，后面的覆盖前面的
 - [ ] 数据无序（插入不有序）
 - [ ] 键值可以为null，但是只能有一个；
#####   常用方法：

 - [ ] int size() :map 中存储键值对的个数；
 - [ ] boolean isEmpty(): 判断集合是否为空；
 - [ ] boolean containsKey(Object key):判断键是否存在；
 - [ ] void putAll(Map<? extends K , ? extends V> m ) : 将map集合添加到该集合中
 - [ ] void clear(): 清空集合；
 - [ ] get() :通过键获取值 ：hashMap.put("lisi","2"); 输入李四，获取2；无法通过值找键
 - [ ] remove(): 删除元素==>>删除键
####   Map集合的三种遍历方式（通过Iterator迭代器实现）
#####   一， 通过键值对遍历：先将HashMap实例转换成set实例（map.entry）

```java
      HashMap<String,String> hashMap=new HashMap();
      Iterator<Map.Entry<String,String>> iterator= hashMap.entrySet().iterator();
      while(iterator.hasNext()){
            Map.Entry<String,String> next=iterator.next();
            String key=next.getKey();
            String value=next.getValue();
            System.out.print(key+" "+value+" ");
        }
      
  
```

#####   二，通过键进行遍历：仅仅对键进行访问；hashMap.keySet().iterator();

```java
    HashMap<String,String> hashMap=new HashMap();
    Iterator<String> iterator1=hashMap.keySet().iterator();
    while(iterator1.hasNext()){
            System.out.print("  key "+iterator1.next());
        }
```

#####  三，通过值进行遍历：hashMap.values().iterator();

```java
   HashMap<String,String> hashMap=new HashMap();
   Iterator<String>  iterator2=hashMap.values().iterator();
   while(iterator2.hasNext()){
            System.out.print("  value "+iterator2.next());
        }
```

####  HashMap应用场景：一般用于统计海量数据出现的个数
#####  例：10万个数据，数据范围在1-1000,统计每个数据出现的次数

```java
    public static void totalNum(){

        ArrayList<Integer> arrayList=new ArrayList<>(100000);
        //如果能确定集合大小
        Random random=new Random();
        //产生十万数据；
        for (int i = 0; i <100000 ; i++) {
            arrayList.add(random.nextInt(1000)+1);
        }
        //做统计：
        HashMap<Integer,Integer>  hashMap1=new HashMap<>();
        Iterator<Integer> iterator=arrayList.iterator();
        while(iterator.hasNext()){
            Integer val=iterator.next();
            if(hashMap1.containsKey(val)){//判断集合是否包含
                hashMap1.put(val,hashMap1.get(val)+1); //如果包含，键值不变，value值+1计数
            }else{
                hashMap1.put(val,1);//不包含，说明键值第一次出现，将其添加进集合
            }

        }
    }

```

