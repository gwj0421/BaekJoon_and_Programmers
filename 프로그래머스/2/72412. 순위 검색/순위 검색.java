import java.util.*;

class Solution {
    public int[] solution(String[] infos, String[] queries) {
        return new Algorithm(infos, queries).solve();
    }
}

class Algorithm {
    private final String[] infos;
    private final String[] queries;
    private int[] answer;
    private Map<String, String> codeMap;
    private Map<String, List<Integer>> repository;

    public Algorithm(String[] infos, String[] queries) {
        this.infos = infos;
        this.queries = queries;
        this.answer = new int[queries.length];
        this.codeMap = initCodeMap();
        this.repository = initRepository();
    }

    private Map<String, String> initCodeMap() {
        Map<String, String> codeMap = new HashMap<>();
        codeMap.putAll(Map.of("cpp", "0", "java", "1", "python", "2"));
        codeMap.putAll(Map.of("backend", "0", "frontend", "1"));
        codeMap.putAll(Map.of("junior", "0", "senior", "1"));
        codeMap.putAll(Map.of("chicken", "0", "pizza", "1"));
        codeMap.put("-", "-");
        return codeMap;
    }

    private Map<String, List<Integer>> initRepository() {
        Map<String, List<Integer>> repository = new HashMap<>();
        Arrays.sort(infos, Comparator.comparingInt(it -> Integer.parseInt(it.split(" ")[4])));
        for (String info : infos) {
            Data data = Data.map(false, info, codeMap);
            if (repository.containsKey(data.getCode())) {
                repository.get(data.getCode()).add(data.getScore());
            } else {
                repository.put(data.getCode(), new ArrayList<>(List.of(data.getScore())));
            }
        }
        return repository;
    }

    public int[] solve() {
        for (int i = 0; i < queries.length; i++) {
            dfs(0, "", i, Data.map(true, queries[i], codeMap));
        }
        return answer;
    }

    private void dfs(int depth, String code, int queryIdx, Data queryData) {
        if (depth == 4) {
            if (repository.containsKey(code)) {
                answer[queryIdx] += (repository.get(code).size() - binarySearch(repository.get(code), queryData.getScore()));
            }
            return;
        }
        if (queryData.getCode().charAt(depth) == '-') {
            dfs(depth + 1, code + "0", queryIdx, queryData);
            dfs(depth + 1, code + "1", queryIdx, queryData);
            if (depth == 0) {
                dfs(depth + 1, code + "2", queryIdx, queryData);
            }
        } else {
            dfs(depth + 1, code + queryData.getCode().charAt(depth), queryIdx, queryData);
        }
    }

    private int binarySearch(List<Integer> target, int value) {
        int s = 0;
        int e = target.size() - 1;
        while (s <= e) {
            int m = (s + e) / 2;
            if (target.get(m) < value) {
                s = m + 1;
            } else {
                e = m - 1;
            }
        }
        return s;
    }
}

class Data {
    private String code;
    private int score;

    private Data(String code, int score) {
        this.code = code;
        this.score = score;
    }

    public String getCode() {
        return code;
    }

    public int getScore() {
        return score;
    }

    public static Data map(boolean isQuery, String input, Map<String, String> codeMap) {
        String[] split = input.split(" ");
        StringBuilder code = new StringBuilder();
        int score;
        if (isQuery) {
            code.append(codeMap.get(split[0])).append(codeMap.get(split[2])).append(codeMap.get(split[4])).append(codeMap.get(split[6]));
            score = Integer.parseInt(split[7]);
        } else {
            code.append(codeMap.get(split[0])).append(codeMap.get(split[1])).append(codeMap.get(split[2])).append(codeMap.get(split[3]));
            score = Integer.parseInt(split[4]);
        }
        return new Data(code.toString(), score);
    }
}

class Main {
    public static void main(String[] args) {
        System.out.println(
                new Solution().solution(new String[]{"java backend junior pizza 150", "python frontend senior chicken 210", "python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80", "python backend senior chicken 50"},
                        new String[]{"java and backend and junior and pizza 100", "python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250", "- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150"})
        );
        ;
    }
}