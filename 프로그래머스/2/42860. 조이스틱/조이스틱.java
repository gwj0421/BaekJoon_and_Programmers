class Solution {
    class joystick {
        private final String name;

        public joystick(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public int makeName() {
            int controlCnt = 0;
            controlCnt += calculateAlphabet();
            controlCnt += calculateMove();
            return controlCnt;
        }

        private int calculateAlphabet() {
            int moveAlphabet = 0;
            for (int i = 0; i < getName().length(); i++) {
                char target = getName().charAt(i);
                if (target != 'A') {
                    moveAlphabet += Math.min((int) target - 65, 90 - (int) target + 1);
                }
            }
            return moveAlphabet;
        }

        private int calculateMove() {
            int length = getName().length();
            int moveCnt = length - 1;
            for (int i = 0; i < getName().length(); i++) {
                int j = i + 1;
                while (j < length && getName().charAt(j) == 'A') {
                    j++;
                }
                moveCnt = Math.min(moveCnt, i * 2 + length - j);
                moveCnt = Math.min(moveCnt, (length - j) * 2 + i);
            }
            return moveCnt;
        }

    }

    public int solution(String name) {
        joystick joystick = new joystick(name);
        return joystick.makeName();
    }
}