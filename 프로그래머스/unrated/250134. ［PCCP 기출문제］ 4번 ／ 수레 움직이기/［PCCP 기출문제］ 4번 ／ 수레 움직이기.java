import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    private static final int[] CONTROL_Y = {0, 0, 1, -1};
    private static final int[] CONTROL_X = {1, -1, 0, 0};
    private int row;
    private int col;

    class Wagon {
        private final int[] pos;
        private final int[] end;
        private final int[][] board;

        public Wagon(int[] pos, int[] end, int[][] board) {
            this.pos = pos;
            this.end = end;
            this.board = board;
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
    }

    public int solution(int[][] maze) {
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
                    return Math.max(red.getBoard()[rEnd[0]][rEnd[1]] - 1, blue.getBoard()[bEnd[0]][bEnd[1]] - 1);
                } else {
                    moveOnlyBlue(red, blue, maze, needVisited);
                }

            } else {
                if (blue.isEnd()) {
                    moveOnlyRed(red, blue, maze, needVisited);
                } else {
                    moveBoth(red, blue, maze, needVisited);
                }
            }
        }
        return 0;
    }

    private void moveOnlyRed(Wagon red, Wagon blue, int[][] maze, Queue<Wagon[]> needVisited) {
        for (int i = 0; i < 4; i++) {
            int[] rNextPos = red.getNext(i);
            if (isIn(rNextPos) && canMove(red.getBoard(), maze, rNextPos) && !isDuplicated(rNextPos, blue.getPos())) {
                needVisited.add(new Wagon[]{red.move(rNextPos), blue});
            }
        }
    }

    private void moveOnlyBlue(Wagon red, Wagon blue, int[][] maze, Queue<Wagon[]> needVisited) {
        for (int i = 0; i < 4; i++) {
            int[] bNextPos = blue.getNext(i);
            if (isIn(bNextPos) && canMove(blue.getBoard(), maze, bNextPos) && !isDuplicated(bNextPos, red.getPos())) {
                needVisited.add(new Wagon[]{red, blue.move(bNextPos)});
            }
        }
    }

    private void moveBoth(Wagon red, Wagon blue, int[][] maze, Queue<Wagon[]> needVisited) {
        moveOneByOne(red, blue, maze, needVisited, true);
        moveOneByOne(blue, red, maze, needVisited, false);
    }

    private void moveOneByOne(Wagon first, Wagon second, int[][] maze, Queue<Wagon[]> needVisited, boolean isNaturalOrder) {
        for (int i = 0; i < 4; i++) {
            int[] firstNextPos = first.getNext(i);
            if (isIn(firstNextPos) && canMove(first.getBoard(), maze, firstNextPos) && !isDuplicated(firstNextPos, second.getPos())) {
                for (int j = 0; j < 4; j++) {
                    int[] secondNextPos = second.getNext(j);
                    if (isIn(secondNextPos) && canMove(second.getBoard(), maze, secondNextPos) && !isDuplicated(secondNextPos, firstNextPos)) {
                        if (isNaturalOrder) {
                            needVisited.add(new Wagon[]{first.move(firstNextPos), second.move(secondNextPos)});
                        } else {
                            needVisited.add(new Wagon[]{second.move(secondNextPos), first.move(firstNextPos)});
                        }

                    }
                }
            }
        }
    }

    private boolean isIn(int[] pos) {
        return -1 < pos[0] && pos[0] < row && -1 < pos[1] && pos[1] < col;
    }

    private boolean canMove(int[][] board, int[][] maze, int[] pos) {
        return board[pos[0]][pos[1]] == 0 && maze[pos[0]][pos[1]] != 5;
    }

    private boolean isDuplicated(int[] pos1, int[] pos2) {
        return pos1[0] == pos2[0] && pos1[1] == pos2[1];
    }


}
