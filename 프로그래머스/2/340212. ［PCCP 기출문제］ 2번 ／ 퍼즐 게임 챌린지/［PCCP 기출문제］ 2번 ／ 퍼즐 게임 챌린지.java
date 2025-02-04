class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int answer = 0;
        int st=1,en=300000;
        while(st<=en){
            int md = (st+en)/2;
            if(canSolveEverthing(md,diffs,times,limit)){
                en = md -1;
            } else{
                st = md + 1;
            }
        }
        System.out.println(st + " " + en);
        return st;
    }
    
    public boolean canSolveEverthing(int level, int[] diffs,int[] times,long limit){
        long time=0;
        for(int i=0;i<diffs.length;i++){
            if(diffs[i] <= level){
                time += times[i];
            } else{
                time += (times[i] + times[i-1]) * (diffs[i]-level) + times[i];
            }
            if(time > limit){
                return false;
            }
        }
        return true;
    }
}