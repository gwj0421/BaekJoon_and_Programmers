import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Meat {
    private int weight;
    private int price;

    public Meat(int weight, int price) {
        this.weight = weight;
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public int getPrice() {
        return price;
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
//        System.setIn(new FileInputStream("input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        Meat[] meats = new Meat[n];
        int total = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int amount = Integer.parseInt(st.nextToken());
            int price = Integer.parseInt(st.nextToken());
            total += amount;
            meats[i] = new Meat(amount, price);
        }
        if (total < m) {
            System.out.println(-1);
            return;
        }
        Arrays.sort(meats, (o1, o2) -> {
            if (o1.getPrice() == o2.getPrice()) {
                return o2.getWeight() - o1.getWeight();
            }
            return o1.getPrice() - o2.getPrice();
        });

        int prefPrice = 0;
        int weightSum = 0;
        int priceSum = 0;
        int minValue = Integer.MAX_VALUE;
        for (Meat meat : meats) {
            weightSum += meat.getWeight();
            if (prefPrice != meat.getPrice()) {
                priceSum = prefPrice = meat.getPrice();
            } else {
                priceSum += meat.getPrice();
            }
            if (weightSum >= m) {
                minValue = Math.min(minValue, priceSum);
            }
        }
        System.out.println(minValue);
    }
}