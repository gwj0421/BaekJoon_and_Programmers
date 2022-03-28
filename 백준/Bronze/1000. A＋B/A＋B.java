import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception //문자열이 아닌값을 입력했을 때 예외처리
{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));//선언
        StringTokenizer st = new StringTokenizer(br.readLine());
//readLine()은 개형문자를 만날때까지 읽어옴

        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
//토큰 순서대로 입력한 것을 int값으로 변환

        System.out.println(A + B);
    }
}