#include <stdio.h>
#include <stdlib.h>
#define MAX 9999

int NodeNum, EdgeNum;
//다익스탈 알고리즘 사용
typedef struct Node {
	int u, v, w;
}Node;

Node edge[10001];
int weight[201][201];

int distance[201];
int temp[201];
int flag[201];


int ans[201][201];

void init(int s) {

	for (int i = 0; i <= NodeNum; i++) {
		distance[i] = MAX;
		temp[i] = 0;
		flag[i] = 0;
	}
	distance[s] = 0;

}

void relax(int u, int v) {	//이완
	if (distance[v] > distance[u] + weight[u][v]) {
		distance[v] = distance[u] + weight[u][v];
		temp[v] = u;
	}
}

int main() {

	scanf("%d %d", &NodeNum, &EdgeNum);

	for (int i = 0; i < EdgeNum; i++) {
		scanf("%d %d %d", &edge[i].u, &edge[i].v, &edge[i].w);
	}

	for (int i = 0; i < 201; i++) {
		for (int j = 0; j < 201; j++) {
			weight[i][j] = MAX;
			//weight= 무한

		}
	}

	for (int i = 0; i < EdgeNum; i++) {
		weight[edge[i].u][edge[i].v] = edge[i].w;
		weight[edge[i].v][edge[i].u] = edge[i].w;
	}

	for (int a = 1; a <= NodeNum; a++) {

		int min;
		int p = 0;

		init(a);

		for (int i = 1; i <= NodeNum; i++) {
			min = MAX;

			for (int j = 1; j <= NodeNum; j++) {
				if (min > distance[j] && flag[j] == 0) {

					min = distance[j];
					p = j;


				}
			}

			flag[p] = 1;


			for (int j = 1; j <= NodeNum; j++) {

				relax(p, j);
			}
		}

		for (int i = 0; i <= NodeNum; i++) {
			ans[i][a] = temp[i];
		}

	}

	for (int i = 1; i <= NodeNum; i++) {
		for (int j = 1; j <= NodeNum; j++) {
			if (i == j) {
				printf("- ");
			}
			else {
				printf("%d ", ans[i][j]);
			}
		}
		printf("\n");
	}

}