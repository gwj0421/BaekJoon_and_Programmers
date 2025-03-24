import java.util.*;

class Solution {
    public int[][] solution(int[][] originData, String ext, int val_ext, String sort_by) {
        List<Data> database = new ArrayList<>();
        for (int[] detail : originData) {
            Data data = new Data(detail);
            if (data.isApply(ext, val_ext)) {
                database.add(data);
            }
        }
        database.sort(Comparator.comparing(it -> it.getInstance(sort_by)));
        return database.stream().map(Data::transform).toArray(int[][]::new);
    }
}

class Data {
    private int code;
    private int date;
    private int maximum;
    private int remain;

    public Data(int[] data) {
        this.code = data[0];
        this.date = data[1];
        this.maximum = data[2];
        this.remain = data[3];
    }

    public int getInstance(String target) {
        switch (target) {
            case "code":
                return code;
            case "date":
                return date;
            case "maximum":
                return maximum;
            case "remain":
                return remain;
        }
        throw new IllegalArgumentException();
    }

    public boolean isApply(String ext, int val_ext) {
        return getInstance(ext) < val_ext;
    }

    public int[] transform() {
        return new int[]{code, date, maximum, remain};
    }

}