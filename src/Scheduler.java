import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class Scheduler {

    private ArrayList<Thread> threads;

    public Scheduler(ArrayList<Thread> p) {
        threads = p;
    }

    public String makePlan() {

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (threads.size() > 0) {

            sb.append("Поток №");
            sb.append(threads.get(i).getName());
            sb.append(", приоритет ");
            sb.append(threads.get(i).getPriority());
            sb.append(", процессов ");
            sb.append(threads.get(i).getProcesses().size());
            sb.append("\n");
            sb.append(makePlanProcess(threads.get(i).getProcesses(), getTimeProcess(threads.get(i))));
            if (threads.get(i).getProcesses().size() > 0) {
                sb.append("Oсталось процессов ");
                sb.append(threads.get(i).getProcesses().size());
                sb.append("\n");
                i++;
            } else {
                sb.append("Процесс завершен\n");
                threads.remove(i);
            }

            if (i >= threads.size()) {
                i = 0;
            }
            sb.append("\n");
        }
        return sb.toString();

    }

    private String makePlanProcess(ArrayList<Process> threads, int timeProcess) {

        int timeP = timeProcess;
        StringBuilder sb = new StringBuilder();
        int i = 0;

        int timeT = timeP / threads.size();

        while (threads.size() > 0 && timeP > 0) {

            timeP -= timeT;

            if (threads.get(i).getOstTime() > timeT) {

                threads.get(i).setOstTime(threads.get(i).getOstTime() - timeT);
                sb.append("Процесс №");
                sb.append(threads.get(i).getName());
                sb.append(", осталось времени ");
                sb.append(threads.get(i).getOstTime());
                sb.append(", время ");
                sb.append(threads.get(i).getTime());
                sb.append("\n");
                i++;

            } else {

                sb.append("Процесс №");
                sb.append(threads.get(i).getName());
                sb.append(", поток завершен");
                sb.append(", время ");
                sb.append(threads.get(i).getTime());
                sb.append("\n");
                threads.remove(i);

            }

            if (i >= threads.size()) {
                i = 0;
            }

        }
        return sb.toString();

    }

    private int getTimeProcess(Thread pr) {
        int t = 100 + pr.getPriority() * 100;
        return t;

    }

}