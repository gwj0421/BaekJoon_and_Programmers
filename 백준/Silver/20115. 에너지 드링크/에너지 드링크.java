import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static final boolean DEBUG_MODE = false;
    // 10 + 20/2 = 20
    // 2 3 6 9 10
    // 3 6 9 11
    // 6 9 12.5
    // 9 15.5
    // 20
    // 2 , 3 , 6 , 9

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        List<EnergyDrink> drinks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            drinks.add(new EnergyDrink(i, Integer.parseInt(st.nextToken())));
        }

        drinks.sort(Comparator.comparingDouble(EnergyDrink::getVolume));
        double totalEnergyDrinkVolume = drinks.get(n - 1).getVolume();
        for (int i = 0; i < n-1; i++) {
            totalEnergyDrinkVolume += drinks.get(i).getVolume() / 2;
        }
        if (totalEnergyDrinkVolume == Math.floor(totalEnergyDrinkVolume)) {
            System.out.println(String.valueOf((long) totalEnergyDrinkVolume));
        } else {
            System.out.println(String.valueOf(totalEnergyDrinkVolume));
        }
    }

    static class EnergyDrink {
        private int idx;
        private double volume;

        public EnergyDrink(int idx, double volume) {
            this.idx = idx;
            this.volume = volume;
        }

        public double getVolume() {
            return volume;
        }
    }
}