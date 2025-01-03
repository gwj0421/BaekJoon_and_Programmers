import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    private static final boolean DEBUG_MODE = false;

    //    5
//            2
//            5
//            1
//            2
//            4
//            5
//            1
//            3
    // 3 1
    // 1 2
    // 8 3
    // 5 4
    // 2 5
    // 4 2
    // 6
    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Lecture> schedule = new PriorityQueue<>();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            schedule.add(new Lecture(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
//        for (Lecture lecture : schedule) {
//            System.out.println(lecture);
//        }

        PriorityQueue<Classroom> classrooms = new PriorityQueue<>();

        int ans[] = new int[n + 1];
        int classroomCnt = 0;
        while (!schedule.isEmpty()) {
            Lecture lecture = schedule.poll();
            if (classrooms.isEmpty() || lecture.getSt() < classrooms.peek().getTime()) {
                classrooms.add(new Classroom(++classroomCnt, lecture.getEn()));
                ans[lecture.idx] = classroomCnt;
                continue;
            }
            Classroom classroom = classrooms.poll();
            ans[lecture.idx] = classroom.idx;
            classroom.register(lecture);
            classrooms.add(classroom);
        }
        System.out.println(classroomCnt);
        for (int i = 1; i <= n; i++) {
            System.out.println(ans[i]);
        }
    }

    static class Classroom implements Comparable<Classroom> {
        private final int idx;
        private int time;

        public Classroom(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }

        public int getTime() {
            return time;
        }

        public void register(Lecture lecture) {
            this.time = lecture.getEn();
        }

        @Override
        public int compareTo(Classroom o) {
            return Integer.compare(this.time, o.getTime());
        }
    }

    static class Lecture implements Comparable<Lecture> {
        private final int idx;
        private final int st;
        private final int en;

        public Lecture(int idx, int st, int en) {
            this.idx = idx;
            this.st = st;
            this.en = en;
        }

        public int getIdx() {
            return idx;
        }

        public int getSt() {
            return st;
        }

        public int getEn() {
            return en;
        }

        @Override
        public String toString() {
            return "Lecture{" +
                    "idx=" + idx +
                    ", st=" + st +
                    ", en=" + en +
                    '}';
        }

        @Override
        public int compareTo(Lecture o) {
            return Integer.compare(this.st, o.getSt());
        }

    }

}