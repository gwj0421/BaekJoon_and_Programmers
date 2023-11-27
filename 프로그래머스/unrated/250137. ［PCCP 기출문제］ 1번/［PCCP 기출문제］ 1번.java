class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int maxHealth = health;
        int attackIdx = 0;
        int time = 0;
        int successStack = 0;
        while (attackIdx < attacks.length) {
            if (attacks[attackIdx][0] != time) {
                successStack++;
                health += bandage[1];
                if (health > maxHealth) {
                    health = maxHealth;
                }
                if (successStack == bandage[0]) {
                    health += bandage[2];
                    if (health > maxHealth) {
                        health = maxHealth;
                    }
                    successStack = 0;
                }
            } else {
                health -= attacks[attackIdx][1];
                if (health < 1) {
                    return -1;
                }
                successStack = 0;
                attackIdx++;
            }
            time++;
        }

        return health;
    }
}