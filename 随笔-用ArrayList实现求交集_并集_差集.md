####   题目：a {1,2,3,4}    ---- b {3,4,5,6}

 - [ ] 并集:{1,2,3,3,4,4,5,6}
 - [ ] 交集{3，4}
 - [ ] 差集:a与b的差集{1,2}
 

```java
public class ArrayListDemo {
       /*
         * @Description : 本质为两个集合的合并
         * @param arrayList1
         * @param arrayList2
         * @return :   java.util.ArrayList
         * @exception :
         * @date :   2019/2/21 18:08
         */
    public static  ArrayList unionSet(ArrayList arrayList1, ArrayList arrayList2){
         arrayList1.addAll(arrayList2); //得到并集,有重复元素的并集

         return arrayList1;
    }

       /*
        * @Description : 存在交集方法一
        * @param arrayList1
        * @param arrayList2
        * @return :   java.util.ArrayList
        * @exception :
        * @date :   2019/2/21 18:27
        */
    public static  ArrayList intersection1(ArrayList arrayList1,ArrayList arrayList2){
        arrayList1.retainAll(arrayList2);
        //两个集合取交集并将交集元素赋值给arrayList1;
        // 如果arrayList1发生变化则返回true;但如果两个集合相同，返回false;
        return arrayList1;
    }
       /*
        * @Description : 存在交集方法二
        * @param arrayList1
        * @param arrayList2
        * @return :   java.util.ArrayList
        * @exception :
        * @date :   2019/2/21 18:27
        */
    public static  ArrayList intersection2(ArrayList arrayList1,ArrayList arrayList2){
        ArrayList arrayList=new ArrayList();
        for(int i=0 ; i < arrayList1.size() ;i++ ){
            if(arrayList2.contains(arrayList1.get(i))){
            //如果arrayList2包含arrayList1中的元素，那么此元素为交集元素；
                arrayList.add(arrayList1.get(i));
            }
        }
        return arrayList;
    }

        /*
         * @Description : 求差集方法一
         * @param arrayList1
         * @param arrayList2
         * @return :   java.util.ArrayList
         * @exception :  
         * @date :   2019/2/21 19:53
         */
    public static  ArrayList differenceSet1(ArrayList arrayList1,ArrayList arrayList2){
        
        ArrayList arrayList=new ArrayList();
        for (int i = 0; i <arrayList1.size() ; i++) {
            if(!arrayList2.contains(arrayList1.get(i))){
                //arrayList2不包含arrayList1的元素，那么就是arrayList1的特有元素
                arrayList.add(arrayList1.get(i));
            }
        }
        return arrayList;
    }
    
     /*
         * @Description : 求差集方法二
         * @param arrayList1
         * @param arrayList2
         * @return :   java.util.ArrayList
         * @exception :  
         * @date :   2019/2/21 19:53
         */
    public static  ArrayList differenceSet2(ArrayList arrayList1,ArrayList arrayList2){
       arrayList1.removeAll(arrayList2);
       //list1集合与list2集合先找到交集，然后在list1中删除交集 ，
       //然后将删除交集后的list1重新赋给list1
        return arrayList1;
    }
        
    public static void main(String[] args) {
        ArrayList<Integer> arrayList2 = new ArrayList<Integer>();
        ArrayList<Integer>  arrayList1=new ArrayList<>();
        //添加元素 boolean add(E e)
        arrayList1.add(1);
        arrayList1.add(2);
        arrayList1.add(3);
        arrayList1.add(4);
        arrayList1.add(3);
        arrayList2.add(4);
        arrayList2.add(5);
        arrayList2.add(6);
        System.out.println(unionSet(arrayList1,arrayList2));
        System.out.println(intersection(arrayList1,arrayList2));
        System.out.println(differenceSet(arrayList1,arrayList2));
   }
}
     
```

