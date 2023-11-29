import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private static final int[] CONTROL_Y = {0, 0, 1, -1};
    private static final int[] CONTROL_X = {1, -1, 0, 0};
    private int row, col;

    class Wagon {
        private final int moveCnt = 0;
        private final int[] pos;
        private final int[] end;
        private final int[][] board;

        public Wagon(int[] pos, int[] end, int[][] board) {
            this.pos = pos;
            this.end = end;
            this.board = board;
        }

        public int getMoveCnt() {
            return moveCnt;
        }

        public int[] getPos() {
            return pos;
        }

        public int[] getEnd() {
            return end;
        }

        public int[][] getBoard() {
            return board;
        }

        public Wagon move(int[] nextPos) {
            int[][] nextBoard = new int[getBoard().length][];
            for (int i = 0; i < getBoard().length; i++) {
                nextBoard[i] = Arrays.copyOf(getBoard()[i], getBoard()[i].length);
            }
            nextBoard[nextPos[0]][nextPos[1]] = getBoard()[getPos()[0]][getPos()[1]] + 1;
            return new Wagon(nextPos, getEnd(), nextBoard);
        }

        public boolean isDuplicated(int[] other) {
            return getPos()[0] == other[0] && getPos()[1] == other[1];
        }

        public int[] getNext(int direction) {
            return new int[]{getPos()[0] + CONTROL_Y[direction], getPos()[1] + CONTROL_X[direction]};
        }

        public boolean isEnd() {
            return getPos()[0] == getEnd()[0] && getPos()[1] == getEnd()[1];
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("pos: ").append(Arrays.toString(pos)).append("\n");
            sb.append("end: ").append(Arrays.toString(end)).append("\n");
            sb.append("board:\n");

            for (int[] row : board) {
                sb.append(Arrays.toString(row)).append("\n");
            }

            return sb.toString();
        }
    }

    public int solution(int[][] maze) {
        int answer = 0;
        row = maze.length;
        col = maze[0].length;
        int[] rStart = new int[2];
        int[] bStart = new int[2];
        int[] rEnd = new int[2];
        int[] bEnd = new int[2];

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 1) {
                    rStart = new int[]{i, j};
                } else if (maze[i][j] == 2) {
                    bStart = new int[]{i, j};
                } else if (maze[i][j] == 3) {
                    rEnd = new int[]{i, j};
                } else if (maze[i][j] == 4) {
                    bEnd = new int[]{i, j};
                }
            }
        }

        Queue<Wagon[]> needVisited = new LinkedList<>();
        int[][] initRedBoard = new int[row][col];
        int[][] initBlueBoard = new int[row][col];
        initRedBoard[rStart[0]][rStart[1]] = 1;
        initBlueBoard[bStart[0]][bStart[1]] = 1;
        needVisited.add(new Wagon[]{new Wagon(rStart, rEnd, initRedBoard), new Wagon(bStart, bEnd, initBlueBoard)});

        while (!needVisited.isEmpty()) {
            Wagon[] now = needVisited.poll();
            Wagon red = now[0];
            Wagon blue = now[1];
            if (red.isEnd()) {
                if (blue.isEnd()) {
//                    System.out.println(1);
//                    System.out.println(red);
//                    System.out.println(blue);
                    return Math.max(red.getBoard()[rEnd[0]][rEnd[1]]-1, blue.getBoard()[bEnd[0]][bEnd[1]]-1);
                } else {
//                    System.out.println(2);
//                    System.out.println(red);
//                    System.out.println(blue);
                    for (int i = 0; i < 4; i++) {
                        int[] bNextPos = blue.getNext(i);
                        if (isIn(bNextPos) && canMove(blue.getBoard(),maze, bNextPos) && !isDuplicated(bNextPos, red.getPos())) {
                            needVisited.add(new Wagon[]{red, blue.move(bNextPos)});
                        }
                    }
                }

            } else {
                if (blue.isEnd()) {
//                    System.out.println(3);
//                    System.out.println(red);
//                    System.out.println(blue);
                    for (int i = 0; i < 4; i++) {
                        int[] rNextPos = red.getNext(i);
                        if (isIn(rNextPos) && canMove(red.getBoard(),maze, rNextPos) && !isDuplicated(rNextPos, blue.getPos())) {
                            needVisited.add(new Wagon[]{red.move(rNextPos), blue});
                        }
                    }
                } else {
//                    System.out.println(4);
//                    System.out.println(red);
//                    System.out.println(blue);
                    for (int i = 0; i < 4; i++) {
                        int[] rNextPos = red.getNext(i);
                        if (isIn(rNextPos) && canMove(red.getBoard(),maze, rNextPos) && !isDuplicated(rNextPos, blue.getPos())) {
                            for (int j = 0; j < 4; j++) {
                                int[] bNextPos = blue.getNext(j);
                                if (isIn(bNextPos) && canMove(blue.getBoard(),maze, bNextPos) && !isDuplicated(bNextPos, rNextPos)) {
                                    needVisited.add(new Wagon[]{red.move(rNextPos), blue.move(bNextPos)});
                                }
                            }
                        }
                    }

                    for (int i = 0; i < 4; i++) {
                        int[] bNextPos = blue.getNext(i);
                        if (isIn(bNextPos) && canMove(blue.getBoard(),maze, bNextPos) && !isDuplicated(bNextPos, red.getPos())) {
                            for (int j = 0; j < 4; j++) {
                                int[] rNextPos = red.getNext(j);
                                if (isIn(rNextPos) && canMove(red.getBoard(),maze, rNextPos) && !isDuplicated(rNextPos, bNextPos)) {
                                    needVisited.add(new Wagon[]{red.move(rNextPos), blue.move(bNextPos)});
                                }
                            }
                        }
                    }
                }
            }


        }
        return 0;
    }

    private boolean isIn(int[] pos) {
        return -1 < pos[0] && pos[0] < row && -1 < pos[1] && pos[1] < col;
    }

    private boolean canMove(int[][] board,int[][] maze, int[] pos) {
        return board[pos[0]][pos[1]] == 0 && maze[pos[0]][pos[1]] != 5;
    }

    private boolean isDuplicated(int[] pos1, int[] pos2) {
        return pos1[0] == pos2[0] && pos1[1] == pos2[1];
    }


}
