import java.util.LinkedHashMap;
import java.util.Map;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        Map<Integer, Integer> paperCnt = new LinkedHashMap<>();
        for (int citation : citations) {
            paperCnt.put(citation, paperCnt.getOrDefault(citation, 0) + 1);

        }
        int maxValue = getMax(citations);
        int[] hIndex = mapToArray(paperCnt,maxValue);

        for (int i = maxValue; i > 0; i--) {
            hIndex[i-1] += hIndex[i];
        }

        for (int i = maxValue; i > -1; i--) {
            if (hIndex[i] >= i && hIndex[0] - hIndex[i] <= i) {
                answer = i;
                break;
            }
        }
        return answer;
    }

    private int getMax(int[] arr) {
        int maxValue = -1;
        for (int i : arr) {
            if (maxValue < i) {
                maxValue = i;
            }
        }
        return maxValue;
    }

    private int[] mapToArray(Map<Integer, Integer> map,int cnt) {
        int[] arr = new int[cnt+1];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            arr[entry.getKey()] = entry.getValue();
        }
        return arr;
    }
}