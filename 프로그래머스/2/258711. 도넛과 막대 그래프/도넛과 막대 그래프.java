import java.io.IOException;
import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        Map<Integer, Queue<Integer>> relation = new HashMap<>();
        boolean[] isReceived = new boolean[1000001];
        for (int[] edge : edges) {
            if (relation.containsKey(edge[0])) {
                relation.get(edge[0]).add(edge[1]);
            } else {
                relation.put(edge[0], new LinkedList<>(List.of(edge[1])));
            }
            isReceived[edge[1]] = true;
            if (!relation.containsKey(edge[1])) {
                relation.put(edge[1], new LinkedList<>());
            }
        }
        int removeNode = -1;
        for (Map.Entry<Integer, Queue<Integer>> entry : relation.entrySet()) {
            if (entry.getValue().size() >= 2 && !isReceived[entry.getKey()]) {
                removeNode = entry.getKey();
            }
        }
        int totalCnt = relation.get(removeNode).size();
        relation.remove(removeNode);
        int[] answer = new int[]{removeNode, 0, 0, 0};
        boolean[] isVisit = new boolean[1000001];
        for (Map.Entry<Integer, Queue<Integer>> entry : relation.entrySet()) {
            if (!isVisit[entry.getKey()]) {
                isVisit[entry.getKey()] = true;
                int nodeCnt = 1;
                int edgeCnt = 0;
                Queue<Integer> route = new LinkedList<>();
                route.add(entry.getKey());
                while (!route.isEmpty()) {
                    int node = route.poll();
                    while (!relation.get(node).isEmpty()) {
                        int nextNode = relation.get(node).poll();
                        edgeCnt++;
                        if (!isVisit[nextNode]) {
                            nodeCnt++;
                            isVisit[nextNode] = true;
                            route.add(nextNode);
                        }
                    }
                }
                int targetShape = getShapeIdx(nodeCnt, edgeCnt);
                if (targetShape != 1) {
                    answer[targetShape]++;
                    totalCnt--;
                }
            }
        }
        answer[1] = totalCnt;
        return answer;
    }

    private int getShapeIdx(int nodeCnt, int edgeCnt) {
        if (nodeCnt == edgeCnt) {
            return 1;
        } else if (nodeCnt-1 == edgeCnt) {
            return 2;
        } else if (nodeCnt+1== edgeCnt) {
            return 3;
        }
        throw new IllegalArgumentException("잘못된 입력");
    }
}