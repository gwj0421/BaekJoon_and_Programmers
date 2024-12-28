import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int p, n;
        int[] moneys;
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            p = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            moneys = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            MoneySplit moneySplit = new MoneySplit(p, n, moneys);
            moneySplit.activate();
        }
    }

    static class MoneySplit {
        private final int p;
        private final int n;
        private List<Wallet> order;

        public MoneySplit(int p, int n, int[] moneys) {
            this.p = p;
            this.n = n;
            List<Wallet> order = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                order.add(new Wallet(i, moneys[i]));
            }
            Collections.sort(order);
            this.order = order;
        }

        // 0 0 0 0 0 +34
        // 6 6 6 6 4 +6
        // 7 7 7 7 4 +2
        // 8 8 7 7 4 +0
        public void activate() {
            int canPayCnt = n;
            int nextPayCnt = canPayCnt;
            int totalRemainMoney = p;
            int splitMoney;
            while (totalRemainMoney > 0) {
                if (canPayCnt < 1) {
                    System.out.println("IMPOSSIBLE");
                    return;
                }
                splitMoney = totalRemainMoney / canPayCnt;
                if (splitMoney == 0) {
                    for (int i = 0; i < totalRemainMoney; i++) {
                        order.get(i).payMoney(1);
                    }
                    break;
                }
                for (int i = 0; i < canPayCnt; i++) {
                    if (!order.get(i).canPay(splitMoney)) {
                        totalRemainMoney -= order.get(i).getRemain();
                        order.get(i).payMoney(order.get(i).getRemain());
                        nextPayCnt--;
                        continue;
                    }
                    order.get(i).payMoney(splitMoney);
                    totalRemainMoney -= splitMoney;
                }
                canPayCnt = nextPayCnt;
            }
            System.out.println(order.stream()
                    .sorted(Comparator.comparingInt(it -> it.getIdx()))
                    .map(String::valueOf).collect(Collectors.joining(" ")));
        }
    }

    static class Wallet implements Comparable<Wallet> {
        private final int idx;
        private final int money;
        private int remain;

        public Wallet(int idx, int money) {
            this.idx = idx;
            this.money = money;
            this.remain = money;
        }

        public int getIdx() {
            return idx;
        }

        public int getMoney() {
            return money;
        }

        public int getRemain() {
            return remain;
        }

        public boolean canPay(int price) {
            return this.remain >= price;
        }

        public void payMoney(int price) {
            this.remain -= price;
        }


        @Override
        public String toString() {
            return "" + (this.money - this.remain);
        }

        @Override
        public int compareTo(Wallet o) {
            if (this.money == o.getMoney()) {
                return Integer.compare(this.idx, o.getIdx());
            }
            return Integer.compare(o.getMoney(), this.money);
        }
    }

}