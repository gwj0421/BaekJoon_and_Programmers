enum Week {
    SUN("SUN",0),MON("MON",1),TUE("TUE",2),WED("WED",3),THU("THU",4),FRI("FRI",5), SAT("SAT",6), EMPTY("EMPTY", -1),;
    private static final Week[] MONTH_START_WEEK_INFO = new Week[]{
            null, Week.FRI, Week.MON, Week.TUE, Week.FRI,
            Week.SUN, Week.WED, Week.FRI, Week.MON,
            Week.THU, Week.SAT, Week.TUE, Week.THU
    };
    private String name;
    private int info;

    Week(String name, int info) {
        this.name = name;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public int getInfo() {
        return info;
    }

    public static String searchWeek(int a, int b) {
        int day = (MONTH_START_WEEK_INFO[a].getInfo() + b - 1) % 7;
        return getWeek(day).getName();
    }

    private static Week getWeek(int day) {
        for (Week value : values()) {
            if (value.getInfo() == day) {
                return value;
            }
        }
        return Week.EMPTY;
    }
}

class Solution {
    public String solution(int a, int b) {
        return Week.searchWeek(a,b);
    }
}