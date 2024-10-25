import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        HomePage homePage = new HomePage();
        for (int i = 0; i < q; i++) {
            homePage.control(br.readLine());
        }
        homePage.print();
    }

    static class HomePage {
        private int idx = -1;
        private Page backPage = null;
        private Page frontPage = null;

        void control(String query) {
            if (query.charAt(0) == 'B') {
                goBack();
            } else if (query.charAt(0) == 'F') {
                goFront();
            } else if (query.charAt(0) == 'C') {
                compress();
            } else {
                access(Integer.parseInt(query.split(" ")[1]));
            }
        }

        void goBack() {
            if (backPage == null) {
                return;
            }

            if (frontPage != null && frontPage.now == idx) {
                frontPage.cnt++;
            } else {
                frontPage = new Page(idx, frontPage);
            }
            idx = backPage.now;
            if (--backPage.cnt == 0) {
                backPage = backPage.nextPage;
            }
        }

        void goFront() {
            if (frontPage == null) {
                return;
            }
            if (backPage != null && backPage.now == idx) {
                backPage.cnt++;
            } else {
                backPage = new Page(idx, backPage);
            }
            idx = frontPage.now;
            if (--frontPage.cnt == 0) {
                frontPage = frontPage.nextPage;
            }
        }

        void access(int i) {
            frontPage = null;
            if (idx != -1) {
                if (backPage != null && backPage.now == idx) {
                    backPage.cnt++;

                } else {
                    backPage = new Page(idx, backPage);
                }
            }
            idx = i;
        }

        void compress() {
            Page tempPage = backPage;
            while (tempPage != null) {
                tempPage.cnt = 1;
                tempPage = tempPage.nextPage;
            }
        }

        void print() {
            System.out.println(idx);
            if (backPage == null) {
                System.out.println(-1);
            } else {
                backPage.printPage();
            }
            if (frontPage == null) {
                System.out.println(-1);
            } else {
                frontPage.printPage();
            }
        }

    }

    static class Page {
        private int now;
        private int cnt;
        private Page nextPage;

        public Page(int now, Page nextPage) {
            this.now = now;
            this.cnt = 1;
            this.nextPage = nextPage;
        }

        public void printPage() {
            StringBuilder sb = new StringBuilder();
            Page tempPage = this;
            while (tempPage != null) {
                sb.append((tempPage.now + " ").repeat(tempPage.cnt));
                tempPage = tempPage.nextPage;
            }
            System.out.println(sb);
        }
    }

}