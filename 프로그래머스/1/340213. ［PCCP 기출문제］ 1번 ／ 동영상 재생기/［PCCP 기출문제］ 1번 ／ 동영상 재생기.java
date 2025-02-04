class Solution {
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        Time videoInfo = new Time(video_len);
        Time now = new Time(pos);
        Time openingStart = new Time(op_start);
        Time openingEnd = new Time(op_end);
        if(now.isIn(openingStart,openingEnd)){
                    now.setTime(openingEnd.getTime());
                }
        for (String command : commands){
            if(command.equals("prev")){
                now.prev();
            } else if(command.equals("next")){
                now.next(videoInfo);
            }
            if(now.isIn(openingStart,openingEnd)){
                    now.setTime(openingEnd.getTime());
                }
        }
        return now.convertTime();
    }
}

class Time {
    private int time;
    public Time(String input){
        String[] converted = input.split(":");
        this.time = Integer.parseInt(converted[0])*60 + Integer.parseInt(converted[1]);
    }
    
    public int getTime(){
        return time;
    }
    
    public void setTime(int time){
        this.time = time;
    }
    
    public String convertTime(){
        String min = "";
        String sec = "";
        if(time/60 < 10){
            min += "0";
        }
        if(time%60 < 10){
            sec += "0";
        }
        return min + time/60 + ":" + sec + time%60;
    }
    
    public void prev(){
        if(time - 10 < 0){
            setTime(0);
            return;
        }
        setTime(time-10);
    }
    
    public void next(Time videoInfo){
        if(time + 10 > videoInfo.getTime()){
            setTime(videoInfo.getTime());
            return;
        }
        setTime(time+10);
    }
    
    public boolean isIn(Time start,Time end){
        return start.getTime() <= time && time <= end.getTime();
    }
}