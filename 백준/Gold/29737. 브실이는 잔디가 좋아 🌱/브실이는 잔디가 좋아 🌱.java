import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

class Info {
    int size, freeze, startDate, failCount;
    String name;

    public Info(int size, int freeze, int startDate, int failCount, String name) {
        this.size = size;
        this.freeze = freeze;
        this.startDate = startDate;
        this.failCount = failCount;
        this.name = name;
    }
}

public class Main {

    static int N, W;

    // Custom comparator for sorting
    static class InfoComparator implements Comparator<Info> {
        public int compare(Info a, Info b) {
            if (a.size != b.size) return b.size - a.size;
            if (a.freeze != b.freeze) return a.freeze - b.freeze;
            if (a.startDate != b.startDate) return a.startDate - b.startDate;
            if (a.failCount != b.failCount) return a.failCount - b.failCount;
            return a.name.compareTo(b.name);
        }
    }

    // Update the best user result
    static void changeBest(Info userRes, Info now) {
        if (userRes.size > now.size) return;
        if (userRes.size < now.size) {
            userRes.size = now.size;
            userRes.freeze = now.freeze;
            userRes.startDate = now.startDate;
            userRes.failCount = now.failCount;
        } else {
            if (userRes.freeze > now.freeze) {
                userRes.freeze = now.freeze;
                userRes.startDate = now.startDate;
                userRes.failCount = now.failCount;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        W = sc.nextInt();

        List<Info> res = new ArrayList<>();

        for (int ord = 0; ord < N; ord++) {
            String name = sc.next();

            Info userRes = new Info(0, 0, 0, 0, name);

            String[] board = new String[7];
            for (int i = 0; i < 7; i++) {
                board[i] = sc.next();
            }

            int size = 0;
            int freeze = 0;
            int startDate = 0;

            int dateCount = 0;
            for (int j = 0; j < W; j++) {
                for (int i = 0; i < 7; i++) {
                    char cell = board[i].charAt(j);
                    if (cell == 'O') {
                        if (size == 0) startDate = dateCount;
                        size++;
                        changeBest(userRes, new Info(size, freeze, startDate, userRes.failCount, name));
                    } else if (cell == 'F' && size != 0) {
                        freeze++;
                    } else if (cell == 'X') {
                        userRes.failCount++;
                        size = 0;
                        freeze = 0;
                    }
                    dateCount++;
                }
            }
            res.add(userRes);
        }

        // Sort results using custom comparator
        res.sort(new InfoComparator());

        int rank = 1;
        for (int i = 0; i < res.size(); i++) {
            if (i != 0) {
                Info curr = res.get(i);
                Info prev = res.get(i - 1);
                if (curr.failCount != prev.failCount ||
                        curr.size != prev.size ||
                        curr.startDate != prev.startDate ||
                        curr.freeze != prev.freeze) {
                    rank++;
                }
            }
            System.out.println(rank + ". " + res.get(i).name);
        }
    }
}