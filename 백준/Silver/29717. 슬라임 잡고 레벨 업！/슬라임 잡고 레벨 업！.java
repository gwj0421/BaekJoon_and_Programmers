import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            long cnt = Long.parseLong(br.readLine());
            System.out.println(SylvStory.calculateLevel(cnt));
        }
    }

    static class SylvStory {
        // 레벨   1   2 3   4
        // 경험치  0 1 1 0 4 3
        // 처치량  0 1 2 3 4 5
        // 요구량  2   4 6   8
        // 모은 경험치 = (1+죽인 갯수) * (죽인 갯수/2)
        // 레벨 2 달성을 위한 경험치 = 2      = 2
        // 레벨 3 달성을 위한 경험치 = 2+4    = 6
        // 레벨 4 달성을 위한 경험치 = 2+4+6  = 12
        // 레벨 5 달성을 위한 경험치 = 2+4+6+8 = 20
        // 레벨 6 달성을 위한 경험치 = 2+4+6+8+10 = 30
        // 레벨 7 달성을 위한 경험치 = 2+4+6+8+10+12 = 42
        // 레벨 8 달성을 위한 경험치 = 2+4+6+8+10+12+14 = 56
        // 레벨 x 달성을 위한 경험치 = ~ = x*x-x

        // 1처치 = 1경험치
        // 2처치 = 3경험치(1+2)
        // 3처치 = 6경험치(1+2+3)
        // 4처치 = 10경험치(1+2+3+4)
        // 5처치 = 15(1+2+3+4+5)

        public static long calculateLevel(long cnt) {
            long playerExp = getTotalExp(cnt);
            int s = 1;
            int e = 707106782;
            int ans = 0;
            while (s <= e) {
                int mid = (s + e) / 2;
                if (getExp(mid) <= playerExp) {
                    s = mid + 1;
                    ans = mid;
                } else {
                    e = mid - 1;
                }
            }
            return ans;
        }

        public static long getTotalExp(long killCnt) {
            if (killCnt % 2 == 0) {
                return (1 + killCnt) * (killCnt / 2);
            }
            return (1 + killCnt) * (killCnt / 2) + ((1 + killCnt) / 2);
        }

        private static long getExp(long level) {
            return level * level - level;
        }
    }

}