import java.util.*;

class Solution {
    private static final int MAX_CNT_PER_GENRES = 2;

    class Music {
        private final String genre;
        private final int genrePlay;
        private final int play;
        private final int index;

        public Music(String genre, int genrePlay, int play, int index) {
            this.genre = genre;
            this.genrePlay = genrePlay;
            this.play = play;
            this.index = index;
        }

        public String getGenre() {
            return genre;
        }

        public int getGenrePlay() {
            return genrePlay;
        }

        public int getPlay() {
            return play;
        }

        public int getIndex() {
            return index;
        }
    }

    public int[] solution(String[] genres, int[] plays) {
        Map<String, Integer> genreMap = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            genreMap.put(genres[i], genreMap.getOrDefault(genres[i], 0) + plays[i]);
        }
        List<Map.Entry<String, Integer>> entry = new ArrayList<>(genreMap.entrySet());
        entry.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        List<Music> album = new ArrayList<>();
        for (int i = 0; i < genres.length; i++) {
            album.add(new Music(genres[i], genreMap.get(genres[i]), plays[i], i));
        }
        album.sort((o1, o2) -> {
            if (o1.getGenrePlay() > o2.getGenrePlay() ||
                    (o1.getGenrePlay() == o2.genrePlay && o1.getPlay() > o2.getPlay()) ||
                    (o1.getGenrePlay() == o2.genrePlay && o1.getPlay() == o2.getPlay() && o1.getIndex() < o2.getIndex())) {
                return -1;
            }
            return 1;
        });

        Map<String, Integer> cntPerGenre = new HashMap<>();
        for (String genre : genreMap.keySet()) {
            cntPerGenre.put(genre, 0);
        }

        List<Integer> answer = new ArrayList<>();
        for (Music music : album) {
            if (cntPerGenre.get(music.getGenre()) < MAX_CNT_PER_GENRES) {
                answer.add(music.getIndex());
                cntPerGenre.put(music.getGenre(), cntPerGenre.getOrDefault(music.getGenre(), 0) + 1);
            }
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}