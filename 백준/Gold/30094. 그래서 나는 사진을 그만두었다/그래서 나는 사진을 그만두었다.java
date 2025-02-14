import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Main {
    private static final boolean DEBUG_MODE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        int n = Integer.parseInt(br.readLine());
        List<Student> students = new ArrayList<>();
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int ci = Integer.parseInt(st.nextToken());
            int ai = Integer.parseInt(st.nextToken());
            students.add(new Student(i, ci, ai));
        }
        Algorithm algorithm = new Algorithm(n, students);
        algorithm.printResult();
        // 999999 * 200000000
    }

}

class Algorithm {
    private static final long MOD = 998_244_353;
    private final int n;
    private final List<Student> students;
    private final List<Student> minOrderStudents;
    private final List<Student> maxOrderStudents;

    public Algorithm(int n, List<Student> students) {
        this.n = n;
        this.students = students;
        List<Student> minOrderStudents = new ArrayList<>(students);
        List<Student> maxOrderStudents = new ArrayList<>(students);
        minOrderStudents.sort((o1, o2) -> Integer.compare(o2.getM(), o1.getM()));
        maxOrderStudents.sort(Comparator.comparingInt(Student::getM));
        this.minOrderStudents = minOrderStudents;
        this.maxOrderStudents = maxOrderStudents;
    }

    public void printResult() {
        System.out.println(getPictureScore(minOrderStudents) + " " + getCnt(minOrderStudents));
        System.out.println(minOrderStudents.stream().map(it -> String.valueOf(it.getOriginalIdx() + 1)).collect(Collectors.joining(" ")));
        System.out.println(getPictureScore(maxOrderStudents) + " " + getCnt(maxOrderStudents));
        System.out.println(maxOrderStudents.stream().map(it -> String.valueOf(it.getOriginalIdx() + 1)).collect(Collectors.joining(" ")));
    }

    private long getPictureScore(List<Student> students) {
        long pictureScore = 0;
        for (int i = 0; i < n; i++) {
            pictureScore = (pictureScore + students.get(i).getCharacterScore(i, n));
        }
        return pictureScore;
    }

    private long getCnt(List<Student> students) {
        Map<Integer, Integer> mGroup = getMGroup(students);
        long cnt = 1;
        for (Integer value : mGroup.values()) {
            cnt = (cnt * factorial(value)) % MOD;
        }
        return cnt;
    }

    private Map<Integer, Integer> getMGroup(List<Student> students) {
        Map<Integer, Integer> mGroup = new HashMap<>();
        for (Student student : students) {
            if (mGroup.containsKey(student.getM())) {
                mGroup.put(student.getM(), mGroup.get(student.getM()) + 1);
            } else {
                mGroup.put(student.getM(), 1);
            }
        }
        return mGroup;
    }

    private long factorial(long value) {
        if (value == 1) {
            return 1;
        }
        return factorial(value - 1) * value % MOD;
    }
}

class Student {
    private final int originalIdx;
    private final int c;
    private final int a;

    public Student(int originalIdx, int c, int a) {
        this.originalIdx = originalIdx;
        this.c = c;
        this.a = a;
    }

    public int getOriginalIdx() {
        return originalIdx;
    }

    public int getM() {
        return (c - a);
    }

    public long getCharacterScore(long idx, long n) {
        return (long) getM() * idx + (long) a * (n - 1);
    }
}