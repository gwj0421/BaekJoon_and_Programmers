import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		char[] temp = br.readLine().toCharArray();
		int count = 0;
		for (int i = 0; i < temp.length; i++) {
			if (temp[i] == 'c' && i < temp.length - 1) {
				if (temp[i + 1] == '=' || temp[i + 1] == '-')
					i++;
			} else if (temp[i] == 'd' && i < temp.length - 1) {
				if (temp[i + 1] == '-')
					i++;
				else if (temp[i + 1] == 'z' && i < temp.length - 2) {
					if (temp[i + 2] == '=')
						i += 2;
				}
			} else if (temp[i] == 'l' && i < temp.length - 1) {
				if (temp[i + 1] == 'j')
					i++;
			} else if (temp[i] == 'n' && i < temp.length - 1) {
				if (temp[i + 1] == 'j')
					i++;
			} else if (temp[i] == 's' && i < temp.length - 1) {
				if (temp[i + 1] == '=')
					i++;
			} else if (temp[i] == 'z' && i < temp.length - 1) {
				if (temp[i + 1] == '=')
					i++;
			}
			count++;
		}
		System.out.println(count);
	}
}
