import java.util.LinkedList;
import java.util.Queue;

class Solution {
    class Truck {
        private final int weight;
        private int pos;

        public Truck(int weight) {
            this.weight = weight;
            this.pos = 1;
        }

        public int getWeight() {
            return weight;
        }

        public int getPos() {
            return pos;
        }

        public void move() {
            this.pos++;
        }
    }

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int time = 0;
        Queue<Truck> waitingTruck = initWaitingTruck(truck_weights);
        Queue<Truck> onBridge = new LinkedList<>();
        int onBridgeWeight = 0;

        while (!waitingTruck.isEmpty() || !onBridge.isEmpty()) {
            time++;
            if (onBridge.isEmpty()) {
                Truck truck = waitingTruck.poll();
                onBridge.add(truck);
                onBridgeWeight += truck.getWeight();
                continue;
            }

            for (Truck truckOnBridge : onBridge) {
                truckOnBridge.move();
            }

            if (onBridge.peek().getPos() > bridge_length) {
                Truck outTruck = onBridge.poll();
                onBridgeWeight -= outTruck.getWeight();
            }

            if (!waitingTruck.isEmpty() && waitingTruck.peek().getWeight() + onBridgeWeight <= weight) {
                Truck truck = waitingTruck.poll();
                onBridgeWeight += truck.getWeight();
                onBridge.offer(truck);
            }

        }
        return time;
    }

    private Queue<Truck> initWaitingTruck(int[] truck_weights) {
        Queue<Truck> result = new LinkedList<>();
        for (int truckWeight : truck_weights) {
            result.offer(new Truck(truckWeight));
        }
        return result;
    }

}