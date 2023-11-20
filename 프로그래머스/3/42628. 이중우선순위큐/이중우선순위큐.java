import java.util.PriorityQueue;

class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (String operation : operations) {
            if (operation.charAt(0) == 'I') {
                minHeap.add(Integer.parseInt(operation.substring(2)));
                maxHeap.add(Integer.parseInt(operation.substring(2)));
            } else {
                if (operation.charAt(2) == '1') {
                    Integer removeTarget = maxHeap.poll();
                    if (minHeap.contains(removeTarget)) {
                        minHeap.remove(removeTarget);
                    }
                } else {
                    Integer removeTarget = minHeap.poll();
                    if (maxHeap.contains(removeTarget)) {
                        maxHeap.remove(removeTarget);
                    }
                }
            }
        }

        return new int[]{(maxHeap.size() > 1 ? maxHeap.poll() : 0), (minHeap.size() > 1 ? minHeap.poll() : 0)};
    }
}