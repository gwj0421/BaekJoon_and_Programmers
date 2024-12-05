import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        Board before = new Board(br);
        Board after = new Board(br);

        before.compareAnotherBoard(after);
    }


    static class Board {
        private final int h;
        private final int w;
        private final int coinCnt;
        private final char[][] content;

        public Board(BufferedReader br) {
            try {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int h = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                char[][] content = new char[h][];
                int coinCnt = 0;
                char[] inputLine;
                for (int i = 0; i < h; i++) {
                    inputLine = br.readLine().toCharArray();
                    for (int j = 0; j < w; j++) {
                        if (inputLine[j] == 'O') {
                            coinCnt++;
                        }
                    }
                    content[i] = inputLine;
                }

                this.h = h;
                this.w = w;
                this.coinCnt = coinCnt;
                this.content = content;
            } catch (IOException e) {
                throw new IllegalArgumentException();
            }
        }

        public void compareAnotherBoard(Board anotherBoard) {
            System.out.println(getMoveCnt(anotherBoard));
        }

        private int getMoveCnt(Board afterBoard) {
            int maxSameCoinPosCnt = 0;

            // init move
            for (int i = -h + 1; i < afterBoard.h; i++) {
                for (int j = -w + 1; j < afterBoard.w; j++) {
                    maxSameCoinPosCnt = Math.max(maxSameCoinPosCnt, getSameCoinPosCnt(i, j, afterBoard));
//                    System.out.println(i + " " + j);
//                    System.out.println(getSameCoinPosCnt(i, j, afterBoard));

                }
            }
            return coinCnt - maxSameCoinPosCnt;
        }

        private int getSameCoinPosCnt(int moveY, int moveX, Board afterBoard) {
            int sameCoinPosCnt = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (this.content[i][j] == 'O'
                            && -1 < i + moveY && i + moveY < afterBoard.h
                            && -1 < j + moveX && j + moveX < afterBoard.w
                            && afterBoard.content[i + moveY][j + moveX] == 'O') {
                        sameCoinPosCnt++;
                    }
                }
            }
            return sameCoinPosCnt;
        }
    }
}


