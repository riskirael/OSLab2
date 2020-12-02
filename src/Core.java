import java.util.ArrayList;
public class Core {

    public static void main(String[] args) {
        ArrayList<Thread> threads = new ArrayList<Thread>();
        int k = (int) (Math.random() * 4) + 1;
        for (int i = 0; i < k; i++) {
            threads.add(new Thread());
            int n = i + 1;
            threads.get(i).setName(n + "");
        }
        Scheduler s = new Scheduler(threads);
        System.out.println(s.makePlan());
    }
}
