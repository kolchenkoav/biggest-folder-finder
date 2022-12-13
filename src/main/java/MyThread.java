public class MyThread extends Thread {
    private int theadNumber;

    public MyThread(int theadNumber) {
        this.theadNumber = theadNumber;
    }

    @Override
    public void run() {
        for (;;) {
            System.out.println(theadNumber);
        }
    }
}
