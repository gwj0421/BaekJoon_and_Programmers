import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
//        File file = new File("./input.txt");
//
//        Scanner sc = new Scanner(file);
//        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            char[][] map = new char[h][w];
            boolean[] keys = new boolean[26];
            List<Coord> keyCoordinates = new ArrayList<>();
            List<Coord> doorCoordinates = new ArrayList<>();
            List<Coord> documentCoordinates = new ArrayList<>();

            for (int j = 0; j < h; j++) {
                String inputLine = br.readLine();
                for (int k = 0; k < w; k++) {
                    char pos = inputLine.charAt(k);
                    if (Character.isAlphabetic(pos)) {
                        if (Character.isUpperCase(pos)) {
                            doorCoordinates.add(new Coord(j, k));
                        } else {
                            keyCoordinates.add(new Coord(j, k));
                        }
                    } else if (pos == '$') {
                        documentCoordinates.add(new Coord(j, k));
                    }
                    map[j][k] = pos;
                }
            }
            String alreadyHaveKeys = br.readLine();
            if (!alreadyHaveKeys.equals("0")) {
                for (char key : alreadyHaveKeys.toCharArray()) {
                    keys[key - 97] = true;
                }
            }
            Building building = new Building(h, w, map, keys, keyCoordinates, doorCoordinates, documentCoordinates);
            building.search();
        }
    }

    static class Building {
        private final int h;
        private final int w;
        private char[][] map;
        private boolean[] keys;
        private List<Coord> keyCoordinates;
        private List<Coord> doorCoordinates;
        private List<Coord> documentCoordinates;

        public Building(int h, int w, char[][] map, boolean[] keys, List<Coord> keyCoordinates, List<Coord> doorCoordinates, List<Coord> documentCoordinates) {
            this.h = h;
            this.w = w;
            this.map = map;
            this.keys = keys;
            this.keyCoordinates = keyCoordinates;
            this.doorCoordinates = doorCoordinates;
            this.documentCoordinates = documentCoordinates;
        }

        public int getH() {
            return h;
        }

        public int getW() {
            return w;
        }

        public char[][] getMap() {
            return map;
        }

        public List<Coord> getKeyCoordinates() {
            return keyCoordinates;
        }

        public List<Coord> getDoorCoordinates() {
            return doorCoordinates;
        }

        public List<Coord> getDocumentCoordinates() {
            return documentCoordinates;
        }

        public void setKeyCoordinates(List<Coord> keyCoordinates) {
            this.keyCoordinates = keyCoordinates;
        }

        public void setDoorCoordinates(List<Coord> doorCoordinates) {
            this.doorCoordinates = doorCoordinates;
        }

        public void search() {
            openDoor();
            while (farmingKey()) {
                openDoor();
            }

            int documentCnt = 0;
            for (Coord documentCoordinate : getDocumentCoordinates()) {
                if (moveEdge(documentCoordinate)) {
                    documentCnt++;
                }
            }
            System.out.println(documentCnt);
        }

        private void openDoor() {
            List<Coord> nextDoorCoordinates = new ArrayList<>();
            for (Coord doorCoordinate : getDoorCoordinates()) {
                if (keys[map[doorCoordinate.getY()][doorCoordinate.getX()] - 'A']) {
                    map[doorCoordinate.getY()][doorCoordinate.getX()] = '.';
                } else {
                    nextDoorCoordinates.add(doorCoordinate);
                }
            }
            setDoorCoordinates(nextDoorCoordinates);
        }

        private boolean farmingKey() {
            List<Coord> nextKeyCoordinates = new ArrayList<>();
            for (Coord keyCoordinate : getKeyCoordinates()) {
                if (moveEdge(keyCoordinate)) {
                    keys[map[keyCoordinate.getY()][keyCoordinate.getX()] - 'a'] = true;
                } else {
                    nextKeyCoordinates.add(keyCoordinate);
                }
            }
            if (getKeyCoordinates().size() != nextKeyCoordinates.size()) {
                setKeyCoordinates(nextKeyCoordinates);
                return true;
            }
            return false;

        }

        private boolean moveEdge(Coord start) {
            Queue<Coord> needVisit = new LinkedList<>();
            needVisit.add(start);
            boolean[][] visit = new boolean[getH()][getW()];
            visit[start.getY()][start.getX()] = true;

            while (!needVisit.isEmpty()) {
                Coord now = needVisit.poll();
                if (now.isEdge(getH(), getW())) {
                    return true;
                }
                for (int i = 0; i < 4; i++) {
                    int nextY = now.getNextY(i);
                    int nextX = now.getNextX(i);
                    if (-1 < nextY && nextY < getH() && -1 < nextX && nextX < getW()) {
                        if (!visit[nextY][nextX] && ((getMap()[nextY][nextX] == '.' || getMap()[nextY][nextX] == '$') || Character.isLowerCase(getMap()[nextY][nextX]))) {
                            needVisit.add(new Coord(nextY, nextX));
                            visit[nextY][nextX] = true;
                        }
                    }
                }
            }
            return false;
        }
    }

    private static List<Coord> openDoor(char[][] map, List<Coord> doorCoordinates, boolean[] keys) {
        List<Coord> nextDoorCoordinates = new ArrayList<>();
        for (Coord doorCoordinate : doorCoordinates) {
            if (keys[(map[doorCoordinate.getY()][doorCoordinate.getX()] - 65)]) {
                map[doorCoordinate.getY()][doorCoordinate.getX()] = '.';
            } else {
                nextDoorCoordinates.add(doorCoordinate);
            }
        }
        return nextDoorCoordinates;
    }

    static class Coord {
        private static final int[][] MOVE_PATTERN = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        private final int y;
        private final int x;

        public Coord(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public int getNextY(int patternIdx) {
            return getY() + MOVE_PATTERN[patternIdx][0];
        }

        public int getNextX(int patternIdx) {
            return getX() + MOVE_PATTERN[patternIdx][1];
        }

        public boolean isEdge(int h, int w) {
            return getY() == 0 || getY() == h - 1 || getX() == 0 || getX() == w - 1;
        }
    }
}
