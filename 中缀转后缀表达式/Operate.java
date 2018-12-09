package tulun.work;

/**
 * @Created with IntelliJ IDEA
 * @Description:  运算系统
 * @Package: tulun.work
 * @author: FLy-Fly-Zhang
 * @Date: 2018/12/3
 * @Time: 23:16
 */
public class Operate {
    private String strLast="";
    private String midStr;
    private char[] midChar;
    private char[] lastChar;
    private StrStack lastStack=new StrStack();
    private StrStack OpSum=new StrStack();
    public Operate(String str){
        this.midStr=str;
        this.midChar=midStr.toCharArray();
    }
    private void strLastSum (String strLast) throws NullPointerException{
        double num1=0;
        double num2=0;
        double sum=0;
        lastChar=strLast.toCharArray();
        if(lastChar[0]=='+'|| lastChar[0]=='-'|| lastChar[0]=='*' ||lastChar[0]=='/' || lastChar[1]=='+'|| lastChar[1]=='-'|| lastChar[1]=='*' ||lastChar[1]=='/'  ){
            throw new NullPointerException("后缀表达式输入错误");
        }
        for(int i=0 ; i<lastChar.length ; i++){
            switch(lastChar[i]){
                case Priority.ADD:
                    num1=Double.parseDouble(OpSum.getElemVal());
                    OpSum.pop();
                    num2=Double.parseDouble(OpSum.getElemVal());
                    OpSum.pop();
                    OpSum.push(String.valueOf(num1+num2),0);  //运算结果入栈
                    break;
                case Priority.SUB:
                    num1=Double.parseDouble(OpSum.getElemVal());
                    OpSum.pop();
                    num2=Double.parseDouble(OpSum.getElemVal());
                    OpSum.pop();
                    OpSum.push(String.valueOf(num2-num1),0);
                    break;
                case Priority.RIDE:
                    num1=Double.parseDouble(OpSum.getElemVal());
                    OpSum.pop();
                    num2=Double.parseDouble(OpSum.getElemVal());
                    OpSum.pop();
                    OpSum.push(String.valueOf(num1*num2),0);
                    break;
                case Priority.DIVIDE:
                    num1=Double.parseDouble(OpSum.getElemVal());
                    OpSum.pop();
                    num2=Double.parseDouble(OpSum.getElemVal());
                    OpSum.pop();
                    OpSum.push(String.valueOf(num2/num1),0);
                    break;
                default: //非运算符直接入栈
                   OpSum.push(String.valueOf(lastChar[i]),0);
            }
        }
        System.out.println("运算结果为："+OpSum.getElemVal());
    }
    private void strLast(char [] midChar) throws NullPointerException{  //中缀转后缀
        if(midChar[0]=='+'|| midChar[0]=='-'|| midChar[0]=='*' || midChar[0]=='/' || midChar[0]==')'){
            throw new NullPointerException("中缀表达式输入错误");
        }
        for(int i=0 ; i<midChar.length ; i++){
            switch(midChar[i]){
                case Priority.ADD:
                    if(lastStack.getTop()==0){
                        lastStack.push(String.valueOf(midChar[i]),Priority.ADDANDSUB+1); //相同优先级，栈内高于栈外
                    }else {
                        strLast=Priority.setAddandsub(lastStack,midChar[i],strLast);
                    }
                    break;
                case Priority.SUB:
                    if(lastStack.getTop()==0){
                        lastStack.push(String.valueOf(midChar[i]),Priority.ADDANDSUB+1); //相同优先级，栈内高于栈外
                    }else{
                        strLast=Priority.setAddandsub(lastStack,midChar[i],strLast);
                    }
                    break;
                case Priority.RIDE:
                    if(lastStack.getTop()==0){
                        lastStack.push(String.valueOf(midChar[i]),Priority.RIDEANDDIVIDE+1); //相同优先级，栈内高于栈外
                    }else {
                        strLast=Priority.setRideanddivide(lastStack, midChar[i], strLast);
                    }
                    break;
                case Priority.DIVIDE:
                    if(lastStack.getTop()==0){
                        lastStack.push(String.valueOf(midChar[i]),Priority.RIDEANDDIVIDE+1); //相同优先级，栈内高于栈外
                    }else{
                        strLast=Priority.setRideanddivide(lastStack,midChar[i],strLast);
                    }
                    break;
                case Priority.BLEFT:
                        lastStack.push(String.valueOf(midChar[i]),0); //左括号优先级最高，直接进栈
                    break;
                case Priority.BRIGTH:
                    if(lastStack.getTop()==0){
                        throw new UnsupportedOperationException("中缀表达式输入错误");
                    }
                    while(lastStack.getElemSer()!=0){
                        strLast=strLast+ lastStack.getElemVal(); //加入后缀表达式；
                        lastStack.pop();//出栈
                    }
                    lastStack.pop();//左括号出栈
                    break;
                default: //非运算符直接相加
                    strLast=strLast+ String.valueOf(midChar[i]);
            }
        }
        while(lastStack.getTop()!=0){
            strLast=strLast+ lastStack.getElemVal(); //加入后缀表达式；
            lastStack.pop();//出栈
        }
    }
    public void start( ){
        strLast(midChar);
        System.out.println("后缀表达式为： "+strLast);
        strLastSum(strLast);
    }


}
