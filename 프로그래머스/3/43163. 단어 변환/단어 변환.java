import java.util.*;

class Solution {
    class Word {
        private final String word;
        private final int convertCnt;

        public Word(String word, int convertCnt) {
            this.word = word;
            this.convertCnt = convertCnt;
        }

        public String getWord() {
            return word;
        }

        public int getConvertCnt() {
            return convertCnt;
        }
    }

    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        Map<String, List<String>> wordsMap = makeNetwork(words);

        if (wordsMap.containsKey(target)) {
            Queue<Word> needVisited = new LinkedList<>();
            Set<String> visited = new HashSet<>();
            needVisited.add(new Word(target, 0));
            visited.add(target);

            while (!needVisited.isEmpty()) {
                Word nowWord = needVisited.poll();
                if (checkSimilar(nowWord.getWord(), begin)) {
                    return nowWord.getConvertCnt() + 1;
                }
                if (wordsMap.containsKey(nowWord.getWord())) {
                    for (String nextWord : wordsMap.get(nowWord.getWord())) {
                        if (!visited.contains(nextWord)) {
                            needVisited.add(new Word(nextWord, nowWord.getConvertCnt() + 1));
                            visited.add(nextWord);
                        }
                    }
                }

            }
        }

        return answer;
    }

    private Map<String, List<String>> makeNetwork(String[] words) {
        Map<String, List<String>> wordsMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            wordsMap.put(words[i], new ArrayList<>());
        }
        for (int i = 0; i < words.length; i++) {
            for (int j = i + 1; j < words.length; j++) {
                if (i != j && checkSimilar(words[i], words[j])) {
                    wordsMap.get(words[i]).add(words[j]);
                    wordsMap.get(words[j]).add(words[i]);
                }
            }
        }
        return wordsMap;
    }

    private boolean checkSimilar(String word1, String word2) {
        int diffCnt = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)) {
                diffCnt++;
            }
        }
        return diffCnt > 1 ? false : true;
    }
}