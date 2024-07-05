import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
//        File file = new File("./backjoon/input.txt");
//
//        Scanner sc = new Scanner(file);
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int w = sc.nextInt();
        int l = sc.nextInt();
        Queue<Integer> road = new LinkedList<>();
        Queue<Integer> bus = new LinkedList<>();

        int roadWeight = 0;
        int time = 0;
        for (int i = 0; i < n; i++) {
            bus.add(sc.nextInt());
        }
        for (int i = 0; i < w; i++) {
            road.add(-1);
        }
        while (!bus.isEmpty()) {
            int busWeight = road.poll();
            if (busWeight != -1) {
                roadWeight -= busWeight;
            }
            if (roadWeight + bus.peek() <= l) {
                int startBusWeight = bus.poll();
                roadWeight += startBusWeight;
                road.add(startBusWeight);
            } else {
                road.add(-1);
            }
            time++;
        }
        int remainTime = 0;
        int temp = 1;
        for (int i : road) {
            if (i != -1) {
                remainTime = temp;
            }
            temp++;
        }
        time += remainTime;
        System.out.println(time);

    }
}
