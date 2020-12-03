import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;

public class Scheduler {

    private ArrayList<Thread> threads;
    private ArrayList<Process> processes;

    public Scheduler(ArrayList<Process> p) {
        processes = p;
    }

    public String makePlan(int timeProcess) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        int timeP = timeProcess;
        int timeT = timeP / threads.size();
        while (threads.size() > 0 && timeP > 0) {
            timeP -= timeT;
            if (processes.get(i).getOstTime() > timeT) {
                processes.get(i).setOstTime(processes.get(i).getOstTime() - timeT);
                sb.append("Процесс №");
                sb.append(processes.get(i).getName());
                sb.append(", осталось времени ");
                sb.append(processes.get(i).getOstTime());
                sb.append(", время ");
                sb.append(processes.get(i).getTime());
                sb.append("\n");
                i++;

            } else {
                sb.append("Процесс №");
                sb.append(processes.get(i).getName());
                sb.append(", поток завершен");
                sb.append(", время ");
                sb.append(processes.get(i).getTime());
                sb.append("\n");
                processes.remove(i);
            }
            if (i >= processes.size()) {
                i = 0;
            }
        }
        return sb.toString();
    }

    private String makePlanThread(ArrayList<Process> processes, int timeProcess) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (processes.size() > 0) {

            sb.append("Процесс №");
            sb.append(processes.get(i).getName());
            sb.append(", приоритет ");
            sb.append(threads.get(i).getPriority());
            sb.append(", потоков ");
            sb.append(threads.get(i).getProcesses().size());
            sb.append("\n");
            sb.append(makePlanThread(threads.get(i).getProcesses(), getTimeProcess(threads.get(i))));
            if (threads.get(i).getProcesses().size() > 0) {
                sb.append("Oсталось потоков ");
                sb.append(threads.get(i).getProcesses().size());
                sb.append("\n");
                i++;
            } else {
                sb.append("Поток завершен\n");
                threads.remove(i);
            }

            if (i >= threads.size()) {
                i = 0;
            }
            sb.append("\n");
        }
        return sb.toString();

    }

    private int getTimeProcess(Thread pr) {
        int t = 100 + pr.getPriority() * 100;
        return t;

    }

}