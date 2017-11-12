public class Test {
    public static void main(String[]args){
        Performance A=new SingPerformance();
        A.performance("A");
        Performance B=new DancePerformance();
        B.performance("B");
        Performance C=new GayPerformance();
        C.performance("C");
        System.out.println("搞基真酷！！！");
        A = new GayPerformance();
        A.performance("A");
        B = new GayPerformance();
        A.performance("B");
        C = new GayPerformance();
        A.performance("C");

    }

}
