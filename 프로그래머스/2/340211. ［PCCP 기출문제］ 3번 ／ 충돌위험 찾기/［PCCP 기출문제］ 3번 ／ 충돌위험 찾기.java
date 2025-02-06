import java.util.*;

class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;

        List<Queue<int[]>> robots = new ArrayList<>();
        int y, x, ey, ex;
        for (int[] route : routes) {
            Queue<int[]> robotRoute = new LinkedList<>();
            robotRoute.add(points[route[0]-1].clone());
            for (int i = 1; i < route.length; i++) {
                y = points[route[i - 1]-1][0];
                x = points[route[i - 1]-1][1];
                ey = points[route[i]-1][0];
                ex = points[route[i]-1][1];
                while (y != ey) {
                    if (y < ey) {
                        y++;
                    } else {
                        y--;
                    }
                    robotRoute.add(new int[]{y, x});
                }
                while (x != ex) {
                    if (x < ex) {
                        x++;
                    } else {
                        x--;
                    }
                    robotRoute.add(new int[]{y, x});
                }

            }

            robots.add(robotRoute);
        }

        boolean isFinish;
        int pos;
        Map<Integer, Integer> cnt;
        while (true) {
            isFinish = false;
            cnt = new HashMap<>();
            for (Queue<int[]> robot : robots) {
                if (!robot.isEmpty()) {
                    isFinish = true;
                    int[] robotPos = robot.poll();
                    pos = robotPos[0] * 100 + robotPos[1];
                    if (cnt.containsKey(pos)) {
                        if (cnt.get(pos) == 1) {
                            answer++;
                        }
                        cnt.put(pos, cnt.get(pos) + 1);
                    } else {
                        cnt.put(pos, 1);
                    }
                }
            }
            if (!isFinish) {
                break;
            }
        }

        return answer;
    }
}