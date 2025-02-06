class Solution {
    public int solution(int[] wallet, int[] bill) {
        int answer = 0;
        check(bill);
        while(!canIn(wallet,bill)){
            bill[1] /=2;
            check(bill);
            answer++;
        }
        return answer;
    }
    
    public void check(int[] paper){
        if(paper[0] > paper[1]){
            int tmp = paper[0];
            paper[0] = paper[1];
            paper[1]=tmp;
        }
    }
    
    public boolean canIn(int[] wallet,int[] bill){
        return (bill[0]<=wallet[0] && bill[1]<=wallet[1]) || (bill[0]<=wallet[1]&& bill[1]<=wallet[0]);
    }
}