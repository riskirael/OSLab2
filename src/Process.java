import java.util.ArrayList;

public class Process {

    final int time;
    public static int ostTime;
    private String name;

    public Process() {
        time = (int) (Math.random() * 200) + 1;
        ostTime = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public int getTime() {
        return time;
    }

    public static int getOstTime() {
        return ostTime;
    }

    public void setOstTime(int t) {
        ostTime = t;
    }

}