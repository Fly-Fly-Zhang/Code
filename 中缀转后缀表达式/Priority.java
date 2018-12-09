package tulun.work;

/**
 * @Created with IntelliJ IDEA
 * @Description:
 * @Package: tulun.work
 * @author: FLy-Fly-Zhang
 * @Date: 2018/12/3
 * @Time: 22:42
 */
public class Priority {
    public static final int ADDANDSUB=1;
    public static final int RIDEANDDIVIDE=3;
    public static final int BRACKETLEFT=5;
    public static final int BRACKETRIGTH=0;
    public static final char ADD='+';
    public static final char SUB='-';
    public static final char RIDE='*';
    public static final char DIVIDE='/';
    public static final char BLEFT='(';
    public static final char BRIGTH=')';

    public static String  setAddandsub(StrStack lastStack,char ch,String strLast){

        if(lastStack.getElemSer()>Priority.ADDANDSUB){ //栈内高于栈外，出栈；
            while(lastStack.getTop()!=0 && lastStack.getElemSer()>Priority.ADDANDSUB){ //栈内高于栈外一直出栈；
                strLast = strLast + lastStack.getElemVal(); //加入后缀表达式；
                lastStack.pop();//出栈

            }
            lastStack.push(String.valueOf(ch), Priority.ADDANDSUB + 1); //入栈
        }else{  //栈外高于栈内直接进栈；
            lastStack.push(String.valueOf(ch),Priority.ADDANDSUB+1);
        }
        return strLast;
    }
    public static String setRideanddivide(StrStack lastStack,char ch,String strLast){
        if(  lastStack.getElemSer()>Priority.RIDEANDDIVIDE){ //栈内高于栈外，出栈；
            while(lastStack.getTop()!=0 &&lastStack.getElemSer()>Priority.RIDEANDDIVIDE) {
                strLast = strLast + lastStack.getElemVal(); //加入后缀表达式；
                lastStack.pop();//出栈
            }
            lastStack.push(String.valueOf(ch), Priority.RIDEANDDIVIDE + 1); //入栈
        }else{  //栈外高于栈内直接进栈；
            lastStack.push(String.valueOf(ch),Priority.RIDEANDDIVIDE+1);
        }
        return strLast;
    }

    public int getResult(char opera,int num1,int num2){
        if(opera=='+'){
            return num1+num2;
        }else if(opera=='-'){
            return num2-num1;
        }else if(opera=='*'){
            return num1*num2;
        }else {
            return num2/num1;
        }
    }

}
