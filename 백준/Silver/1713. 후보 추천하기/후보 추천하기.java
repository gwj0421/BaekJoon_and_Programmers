import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Main {
    private static final boolean DEBUG_MODE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        int n = Integer.parseInt(br.readLine());
        int workSize = Integer.parseInt(br.readLine());
        int[] workContent = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        LRU lru = new LRU(n, workSize, workContent);
        lru.activate();
    }

    static class LRU {
        private final int n;
        private final int workSize;
        private final int[] workContent;

        public LRU(int n, int workSize, int[] workContent) {
            this.n = n;
            this.workSize = workSize;
            this.workContent = workContent;
        }

        public void activate() {
            PriorityQueue<PhotoFrame> pq = new PriorityQueue<>();
            for (int i = 0; i < workSize; i++) {
                if (!isInPhotoFrame(workContent[i], pq)) {
                    if (pq.size() == n) {
                        pq.poll();
                    }
                    pq.add(new PhotoFrame(i, workContent[i]));
                }
                if (DEBUG_MODE) {
                    System.out.println(pq);
                }
            }
            System.out.println(pq.stream().map(PhotoFrame::getStudentIdx)
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" ")));
        }

        private boolean isInPhotoFrame(int studentIdx, PriorityQueue<PhotoFrame> pq) {
            for (PhotoFrame photoFrame : pq) {
                if (photoFrame.studentIdx == studentIdx) {
                    pq.remove(photoFrame);
                    photoFrame.recommendCnt++;
                    pq.offer(photoFrame);
                    return true;
                }
            }
            return false;
        }
    }

    static class PhotoFrame implements Comparable<PhotoFrame> {
        private final int recommendIdx;
        private final int studentIdx;
        private int recommendCnt;

        public PhotoFrame(int recommendIdx, int studentIdx) {
            this.recommendIdx = recommendIdx;
            this.studentIdx = studentIdx;
            this.recommendCnt = 1;
        }

        public int getRecommendIdx() {
            return recommendIdx;
        }

        public int getStudentIdx() {
            return studentIdx;
        }

        public int getRecommendCnt() {
            return recommendCnt;
        }

        @Override
        public String toString() {
            return "PhotoFrame{" +
                    "recommendIdx=" + recommendIdx +
                    ", studentIdx=" + studentIdx +
                    ", recommendCnt=" + recommendCnt +
                    '}';
        }

        @Override
        public int compareTo(PhotoFrame o) {
            if (this.recommendCnt == o.getRecommendCnt()) {
                return Integer.compare(this.recommendIdx, o.getRecommendIdx());
            }
            return Integer.compare(this.recommendCnt, o.getRecommendCnt());
        }
    }
}