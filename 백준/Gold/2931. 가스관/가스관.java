import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Main {
    private static final boolean DEBUG_MODE = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br;
        if (DEBUG_MODE) {
            br = new BufferedReader(new FileReader("./input.txt"));
        } else {
            br = new BufferedReader(new InputStreamReader(System.in));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] damagedBoard = new char[n][];
        for (int i = 0; i < n; i++) {
            damagedBoard[i] = br.readLine().toCharArray();
        }

        PipeRestoreAlgorithm pipeRestoreAlgorithm = new PipeRestoreAlgorithm(n, m, damagedBoard);
        pipeRestoreAlgorithm.restore();
    }
}

class PipeRestoreAlgorithm {
    // 동쪽에 붙힐수 있는 파이프 : - , + , 3 , 4
    // 서쪽에 붙힐수 있는 파이프 : - , + , 1 , 2
    // 남쪽에 붙힐수 있는 파이프 : | , + , 2 , 3
    // 북쪽에 붙힐수 있는 파이프 : | , + , 1 , 4
    private static final Map<Character, Set<Character>> CAN_PIPE =
            Map.of(
                    'E', Set.of('-', '+', '3', '4'),
                    'W', Set.of('-', '+', '1', '2'),
                    'S', Set.of('|', '+', '2', '3'),
                    'N', Set.of('|', '+', '1', '4')
            );
    private final int n;
    private final int m;
    private final char[][] damagedBoard;

    public PipeRestoreAlgorithm(int n, int m, char[][] damagedBoard) {
        this.n = n;
        this.m = m;
        this.damagedBoard = damagedBoard;
    }

    public void restore() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                Optional<Character> result = checkDamaged(i, j);
                if (result.isPresent()) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(i + 1).append(" ").append(j + 1).append(" ").append(result.get());
                    System.out.println(sb);
                    return;
                }
            }
        }
    }

    private Optional<Character> checkDamaged(int i, int j) {
        if (checkEmpty(i, j)) {
            if (checkCross(i, j)) {
                return Optional.of('+');
            }
            if (checkVertical(i, j)) {
                return Optional.of('|');
            }
            if (checkHorizontal(i, j)) {
                return Optional.of('-');
            }
            if (checkForm1(i, j)) {
                return Optional.of('1');
            }
            if (checkForm2(i, j)) {
                return Optional.of('2');
            }
            if (checkForm3(i, j)) {
                return Optional.of('3');
            }
            if (checkForm4(i, j)) {
                return Optional.of('4');
            }
        }
        return Optional.empty();
    }

    private boolean checkEmpty(int i, int j) {
        return damagedBoard[i][j] == '.';
    }

    private boolean checkCross(int i, int j) {
        return canPipeInEast(i, j) && canPipeInWest(i, j) && canPipeSouth(i, j) && canPipeInNorth(i, j);
    }

    private boolean checkVertical(int i, int j) {
        return canPipeInNorth(i, j) && canPipeSouth(i, j);
    }

    private boolean checkHorizontal(int i, int j) {
        return canPipeInEast(i, j) && canPipeInWest(i, j);
    }

    private boolean checkForm1(int i, int j) {
        return canPipeInEast(i, j) && canPipeSouth(i, j);
    }

    private boolean checkForm2(int i, int j) {
        return canPipeInEast(i, j) && canPipeInNorth(i, j);
    }

    private boolean checkForm3(int i, int j) {
        return canPipeInNorth(i, j) && canPipeInWest(i, j);
    }

    private boolean checkForm4(int i, int j) {
        return canPipeInWest(i, j) && canPipeSouth(i, j);
    }

    private boolean canPipeInEast(int i, int j) {
        return j < m - 1 && CAN_PIPE.get('E').contains(damagedBoard[i][j + 1]);
    }

    private boolean canPipeInWest(int i, int j) {
        return 0 < j && CAN_PIPE.get('W').contains(damagedBoard[i][j - 1]);
    }

    private boolean canPipeSouth(int i, int j) {
        return i < n - 1 && CAN_PIPE.get('S').contains(damagedBoard[i + 1][j]);
    }

    private boolean canPipeInNorth(int i, int j) {
        return 0 < i && CAN_PIPE.get('N').contains(damagedBoard[i - 1][j]);
    }
}