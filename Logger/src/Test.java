public class Test {
    
    public static void main(String[] args) throws Exception {
        new Test();
    }

    public Test() {
        int number = 4;
        Logger.log("This is a test message. The number is %d.", number);
        test();
    }

    public void test() {
        int number = 2;
        Logger.log("This is a test message. The number is %d.", number);
    }

}
