import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String quadTree = sc.next();
        QuadTreeDecryption quadTreeDecryption = new QuadTreeDecryption(n, quadTree);
        quadTreeDecryption.decryption();
    }
}

class QuadTreeDecryption {
    private final int n;
    private final String original;
    private int[][] img;
    private Queue<Character> order;

    public QuadTreeDecryption(int n, String original) {
        this.n = n;
        this.original = original;
        this.img = new int[n][n / 8];
        Queue<Character> order = new LinkedList<>();
        for (int i = 0; i < original.length(); i++) {
            order.add(original.charAt(i));
        }
        this.order = order;
    }

    public void decryption() {
        System.out.println("#define quadtree_width " + n);
        System.out.println("#define quadtree_height " + n);
        System.out.println("static char quadtree_bits[] = {");
        decryption(0, 0, n);
        StringBuilder sb;
        String tmp;
        for (int i = 0; i < n; i++) {
            sb = new StringBuilder();
            for (int j = 0; j < n / 8; j++) {
                tmp = Integer.toHexString(img[i][j]);
                if (tmp.length() < 2) {
                    tmp = "0" + tmp;
                }
                sb.append("0x" + tmp + ",");
            }
            System.out.println(sb);
        }
        System.out.println("};");
    }

    private void decryption(int y1, int x1, int size) {
        if (order.isEmpty()) {
            return;
        }
        char orderType = order.poll();
        if (orderType == 'Q') {
            decryption(y1, x1, size / 2);
            decryption(y1, x1 + size / 2, size / 2);
            decryption(y1 + size / 2, x1, size / 2);
            decryption(y1 + size / 2, x1 + size / 2, size / 2);
        } else if (orderType == 'B') {
            for (int i = y1; i < y1 + size; i++) {
                for (int j = x1; j < x1 + size; j++) {
                    img[i][j / 8] += Math.pow(2, j % 8);
                }
            }
        }


    }
}