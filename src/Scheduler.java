import java.util.ArrayList;

public class Scheduler {

    private ArrayList<Process> processes;

    public Scheduler(ArrayList<Process> p) {
        processes = p;
    }

    public String makePlan() {

        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (processes.size() > 0) {

            sb.append("Процесс №");
            sb.append(processes.get(i).getName());
            sb.append(", приоритет ");
            sb.append(processes.get(i).getPriority());
            sb.append(", потоков ");
            sb.append(processes.get(i).getThreads().size());
            sb.append("\n");
            sb.append(makePlanThreads(processes.get(i).getThreads(), getTimeProcess(processes.get(i))));
            if (processes.get(i).getThreads().size() > 0) {
                sb.append("Oсталось потоков ");
                sb.append(processes.get(i).getThreads().size());
                sb.append("\n");
                i++;
            } else {
                sb.append("Процесс завершен\n");
                processes.remove(i);
            }

            if (i >= processes.size()) {
                i = 0;
            }
            sb.append("\n");
        }
        return sb.toString();

    }

    private String makePlanThreads(ArrayList<Thread> threads, int timeProcess) {

        int timeP = timeProcess;
        StringBuilder sb = new StringBuilder();
        int i = 0;

        int timeT = timeP / threads.size();

        while (threads.size() > 0 && timeP > 0) {

            timeP -= timeT;

            if (threads.get(i).getOstTime() > timeT) {

                threads.get(i).setOstTime(threads.get(i).getOstTime() - timeT);
                sb.append("Поток №");
                sb.append(threads.get(i).getName());
                sb.append(", осталось времени ");
                sb.append(threads.get(i).getOstTime());
                sb.append(", время ");
                sb.append(threads.get(i).getTime());
                sb.append("\n");
                i++;

            } else {

                sb.append("Поток №");
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

    private int getTimeProcess(Process pr) {
        int t = 100 + pr.getPriority() * 100;
        return t;

    }

}