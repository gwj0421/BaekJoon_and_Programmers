#include <stdio.h>
#include <stdlib.h>

typedef struct Node {
	char data;
	struct Node* left;
	struct Node* right;
}node;

//노드생성 함수
node* CreateNode(char a) {
	node* newNode = (node*)malloc(sizeof(node));

	newNode->left = NULL;   //왼쪽 자식
	newNode->right = NULL;  //오른쪽 자식
	newNode->data = a;  //root가 되는 값

	return newNode;
}

void preorder(node* t) { //선순위
	if (t == NULL)
		return;

	printf("%c", t->data);
	preorder(t->left);
	preorder(t->right);
}

void inorder(node* t) {
	if (t == NULL)
		return;

	inorder(t->left);
	printf("%c", t->data);
	inorder(t->right);
}

void postorder(node* t) {
	if (t == NULL)
		return;

	postorder(t->left);
	postorder(t->right);
	printf("%c", t->data);
}

int main() {
	node* root[50];
	int num;
	char a, b, c, arr[81][2];

	scanf("%d", &num);

	for (int i = 0; i < num; i++) {
		//해당 노드의 정보를 배열에 입력
		scanf(" %c %c %c", &a, &b, &c);
		//arr[i][0]은 왼쪽자식 arr[i][1]은 오른쪽 자식이다
		arr[i][0] = b;    arr[i][1] = c;
		//num개의 노드 생성
		root[i] = CreateNode(a);
	}

	for (int i = 0; i < num; i++) {
		for (int j = 0; j < num; j++) {
			//다음의 root를 정하기 위해
			//배열에 저장된 값과 노드의 데이터(root->data)가 같을 때 그 노드를 왼쪽 자식노드로 저장 
			if (arr[i][0] == root[j]->data)
				root[i]->left = root[j];
			//arr배열의 1번째 열에 저장된 값은 노드의 오른쪽 자식 노드가 된다.
			//배열에 저장된 값과 노드의 데이터(root->data)가 같을 때 그 노드를 오른쪽 자식노드로 저장 
			if (arr[i][1] == root[j]->data)
				root[i]->right = root[j];
		}
	}
	//처음부터 시작
	preorder(root[0]);
	printf("\n");
	inorder(root[0]);
	printf("\n");
	postorder(root[0]);

	return 0;
}
