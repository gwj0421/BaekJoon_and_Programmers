import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static final int SIZE = 26;
    static int cnt = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line;
        while ((line = br.readLine()) != null) {

            Trie trie = new Trie();	//트라이 알고리즘 적용

            int N = Integer.parseInt(line);	//입력할 문자열 수

            String[] str = new String[N];

            for (int i = 0; i < N; i++) {

                str[i] = br.readLine();
                trie.insert(str[i]);	//문자열을 노드에 입력
            }

            for (int i = 0; i < SIZE; i++) {

                if (trie.root.children[i] != null) {

                    trie.check(trie.root.children[i], 1);
                }
            }

            System.out.printf("%.2f", (double) cnt / N);
            System.out.println();

            cnt = 0;
        }


    }
}

class Trie {

    TrieNode root;

    Trie() {

        root = new TrieNode();
    }

    private int toNumber(char c) {

        return c - 'a';
    }

    void insert(String key) {

        int length = key.length();
        TrieNode currentNode = root;

        for (int i = 0; i < length; i++) {
        	//자식노드가 문자임으로 문자의 정수값을 기준으로 노드를 생성
            int next = toNumber(key.charAt(i));

            if (currentNode.children[next] == null) {
            	//자식노드의 구조를 만듬
                currentNode.children[next] = new TrieNode();
                currentNode.nChild++;
            }

            //노드를 따라감
            currentNode = currentNode.children[next];
        }
        //사전에 넣을 하나의 문자열이 끝났을 때 끝맺임
        currentNode.isTerminal = true;
    }

    void check(TrieNode node, int ret) {

        if (node.isTerminal) {
        	//문자열이 끝나면 +
            Main.cnt += ret;
        }

        if (node.nChild >= 2) {
        	//자식이 2개이상일때+
            ret++;
        }

        if (node.isTerminal && node.nChild == 1) {

            ret++;
        }

        for (int i = 0; i < Main.SIZE; i++) {

            if (node.children[i] != null) {

                check(node.children[i], ret);
            }
        }

    }
}

class TrieNode {
	//문자별로 자식노드를 만들기 위해 노드 배열 생성
    TrieNode[] children = new TrieNode[Main.SIZE];
    //입력한 문자열의 끝일때 true
    boolean isTerminal;
    //자식의 수
    int nChild = 0;

    TrieNode() {
    	//초기 노드는 끝이 없고
        isTerminal = false;
        //노드 초기화
        for (int i = 0; i < Main.SIZE; i++) {

            children[i] = null;
        }
    }
}