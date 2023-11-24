import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = n;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i < n + 1; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] wire : wires) {
            map.get(wire[0]).add(wire[1]);
            map.get(wire[1]).add(wire[0]);
        }
        for (int[] wire : wires) {
            Queue<Integer> needVisited = new LinkedList<>();
            needVisited.add(1);
            Set<Integer> visited = new HashSet<>();
            visited.add(1);
            while (!needVisited.isEmpty()) {
                int nowNode = needVisited.poll();
                for (int nextNode : map.get(nowNode)) {
                    if ((nowNode == wire[0] && nextNode == wire[1]) ||
                            (nowNode == wire[1] && nextNode == wire[0]) ||
                            (visited.contains(nextNode))
                    ) {
                        continue;
                    }
                    visited.add(nextNode);
                    needVisited.add(nextNode);
                }
            }

            answer = Math.min(answer, Math.abs(n - visited.size() * 2));
        }

        return answer;
    }

    //[[1,2],[2,7],[3,7],[3,4],[4,5],[6,7]]
    private int find(int[] arr, int num) {
        if (arr[num] != num) {
            return find(arr, arr[num]);
        } else {
            return num;
        }
    }

    private int getDiffCnt(int[] arr) {
        int headCnt = 0;
        int otherCnt = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == 1) {
                headCnt++;
            } else {
                otherCnt++;
            }
        }
        return Math.abs(headCnt - otherCnt);
    }
}