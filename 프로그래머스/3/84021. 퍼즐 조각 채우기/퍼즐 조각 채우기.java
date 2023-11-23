import java.util.*;

class Solution {
    class Piece {
        private final int blockCnt;
        private Stack<boolean[][]> shapes;
        private boolean[][] hole;

        public Piece(boolean[][] original, int blockCnt, boolean isHole) {
            this.blockCnt = blockCnt;
            if (isHole) {
                this.hole = original;
            } else {
                this.shapes = rotate(original);
            }
        }

        public int getBlockCnt() {
            return blockCnt;
        }

        public Stack<boolean[][]> getShapes() {
            return shapes;
        }

        public boolean[][] getHole() {
            return hole;
        }

        private Stack<boolean[][]> rotate(boolean[][] original) {
            Stack<boolean[][]> rotated = new Stack<>();
            rotated.push(original);

            for (int i = 0; i < 3; i++) {
                boolean[][] before = rotated.peek();
                int row = before.length;
                int col = before[0].length;
                boolean[][] after = new boolean[col][row];

                for (int j = 0; j < row; j++) {
                    for (int k = 0; k < col; k++) {
                        if (before[j][k]) {
                            after[k][row - 1 - j] = true;
                        }
                    }
                }
                rotated.push(after);
            }
            return rotated;
        }

    }

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        List<Piece> pieceList = bfs(table, 1, false);
        List<Piece> holeList = bfs(game_board, 0, true);
        for (Piece hole : holeList) {
            for (Piece piece : pieceList) {
                boolean isMatch = false;
                if (hole.getBlockCnt() == piece.getBlockCnt()) {
                    for (int i = 0; i < piece.getShapes().size(); i++) {
                        if (Arrays.deepEquals(hole.getHole(), piece.getShapes().get(i))) {
                            answer += piece.getBlockCnt();
                            isMatch = true;
                            pieceList.remove(piece);
                            break;
                        }
                    }
                }
                if (isMatch) {
                    break;
                }
            }
        }
        return answer;
    }

    private List<Piece> bfs(int[][] arr, int targetNum, boolean isHole) {
        int row = arr.length;
        int col = arr[0].length;
        int[] controlY = {0, 0, 1, -1};
        int[] controlX = {1, -1, 0, 0};
        List<Piece> result = new ArrayList<>();

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (arr[i][j] == targetNum) {
                    arr[i][j] = -1;
                    Queue<int[]> needVisited = new LinkedList<>();
                    List<int[]> piecePos = new ArrayList<>();
                    int[] first = new int[]{i, j};
                    int[] last = new int[]{i, j};
                    int blockCnt = 1;
                    needVisited.add(new int[]{i, j});
                    piecePos.add(new int[]{i, j});
                    while (!needVisited.isEmpty()) {
                        int[] now = needVisited.poll();
                        for (int k = 0; k < 4; k++) {
                            int nextY = now[0] + controlY[k];
                            int nextX = now[1] + controlX[k];
                            if (-1 < nextY && nextY < row && -1 < nextX && nextX < col) {
                                if (arr[nextY][nextX] == targetNum) {
                                    needVisited.add(new int[]{nextY, nextX});
                                    piecePos.add(new int[]{nextY, nextX});
                                    arr[nextY][nextX] = -1;
                                    blockCnt++;

                                    first[1] = Math.min(first[1], nextX);
                                    last[0] = Math.max(last[0], nextY);
                                    last[1] = Math.max(last[1], nextX);
                                }
                            }
                        }
                    }

                    int tempRow = last[0] - first[0] + 1;
                    int tempCol = last[1] - first[1] + 1;
                    boolean[][] tempPiece = new boolean[tempRow][tempCol];

                    for (int[] pos : piecePos) {
                        tempPiece[pos[0] - first[0]][pos[1] - first[1]] = true;
                    }
                    result.add(new Piece(tempPiece, blockCnt, isHole));
                }
            }
        }

        return result;
    }

}