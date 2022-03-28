#include<stdio.h>
int save, temp[1024], tr[1024], L[11] = { 0,1,3,7,15,31,63,127,255,511,1023 };
//L배열은 이진완전트리를 일렬로 나열했을떄 층별 첫번째 숫자의 순서
int w;	//w는 층

void Level(int layer) {
	if (layer > w)return;
	if (layer == w - 1) {
		tr[L[layer]] = temp[save++];
		L[layer] = L[layer] + 1;
		return;
	}
	Level(layer + 1);	//왼쪽 재귀

	tr[L[layer]] = temp[save++];	//저장
	L[layer] = L[layer] + 1;
	Level(layer + 1);	//오른쪽 재귀

	return;
}

int main() {
	scanf("%d", &w);

	for (int i = 0; i < L[w]; i++) {
		scanf("%d", &temp[i]);
	}

	Level(0);

	int t = 0, n = 1;	//최종 w(층)까지 반복하여 출력
	for (int i = 0; i < w; i++) {
		for (int j = 0; j < n; j++)
		{
			printf("%d ", tr[t]);
			t++;
		}
		n *= 2;
		//이진완전트리임으로 층이 내려갈수록 2배로 늘어난다
		printf("\n");
	}
	return 0;
}
