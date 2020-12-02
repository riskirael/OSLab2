import java.util.ArrayList;

public class Thread {

    private ArrayList<Process> process;

    private String name;

    private int priority;

    public Thread() {

        int k = (int) (Math.random() * 4) + 1;
        process = new ArrayList<Process>();
        for (int i = 0; i < k; i++) {
            process.add(new Process());
            int n = i + 1;
            process.get(i).setName(n + "");
        }

        k = (int) (Math.random() * 1000);
        if (k < 250) {
            priority = 1;
        } else if (k < 500) {
            priority = 2;
        } else if (k < 750) {
            priority = 3;
        } else {
            priority = 4;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public ArrayList<Process> getProcesses() {
        return process;
    }

    public void setThreads(ArrayList<Process> p) {
        process = p;
    }

    public int getPriority() {
        return priority;
    }

}
