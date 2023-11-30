import java.util.LinkedList;
import java.util.Queue;

class Solution {

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        Queue<Integer> onBridge = new LinkedList<>();
        int onBridgeWeight = 0;
        for (int truck : truck_weights) {
            while (true) {
                if (onBridge.isEmpty()) {
                    onBridge.offer(truck);
                    onBridgeWeight += truck;
                    time++;
                    break;
                } else if (onBridge.size() == bridge_length) {
                    onBridgeWeight -= onBridge.poll();
                } else {
                    if (onBridgeWeight + truck > weight) {
                        onBridge.offer(0);
                        time++;
                    } else {
                        onBridge.offer(truck);
                        onBridgeWeight += truck;
                        time++;
                        break;
                    }
                }
            }
        }
        return time + bridge_length;
    }

}