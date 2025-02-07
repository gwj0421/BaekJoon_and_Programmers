class Solution {
    public int solution(int[] mats, String[][] park) {
        int answer = 0;
        //Arrays.sort(mats);
        int n = park.length;
        int m = park[0].length;
        boolean can;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(park[i][j].equals("-1")){
                    for(int mat:mats){
                        if(i+mat-1<n&&j+mat-1<m){
                            can=true;
                            for(int k=i;k<i+mat;k++){
                                for(int l=j;l<j+mat;l++){
                                    if(!park[k][l].equals("-1")){
                                        can=false;
                                        break;
                                    }
                                }
                                if(!can){
                                    break;
                                }
                            }
                            if(can){
                                
                                answer = Math.max(answer,mat);
                            }
                        }
                    }
                    
                }
            }
        }
        if(answer == 0){
            return -1;
        }
        return answer;
    }
}