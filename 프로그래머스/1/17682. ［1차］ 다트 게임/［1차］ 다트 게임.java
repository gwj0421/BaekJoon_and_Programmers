import java.util.*;

class Solution {
    public int solution(String dartResult) {
        Stack<Integer> dartPoint = new Stack<>();
        int num = 0;
        for (String dart : dartResult.split("")) {
            try {
                num = num * 10 + Integer.parseInt(dart);
            } catch (IllegalArgumentException e) {
                if (dart.equals("S")) {
                    dartPoint.push(num);
                }
                if (dart.equals("D")) {
                    dartPoint.push(num * num);
                }
                if (dart.equals("T")) {
                    dartPoint.push(num * num * num);
                }
                if (dart.equals("#")) {
                    dartPoint.add(dartPoint.pop() * (-1));
                }
                if (dart.equals("*")) {
                    if (dartPoint.size() == 1) {
                        dartPoint.add(dartPoint.pop() * 2);
                    } else {
                        int a = dartPoint.pop() * 2;
                        int b = dartPoint.pop() * 2;
                        dartPoint.add(b);
                        dartPoint.add(a);
                    }
                }
                num = 0;

            }
        }
        int answer = 0;
        while (!dartPoint.isEmpty()) {
            answer += dartPoint.pop();
        }
        return answer;
    }
}