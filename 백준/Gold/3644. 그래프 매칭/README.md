# [Gold III] 그래프 매칭 - 3644 

[문제 링크](https://www.acmicpc.net/problem/3644) 

### 성능 요약

메모리: 23652 KB, 시간: 220 ms

### 분류

임의 정밀도 / 큰 수 연산, 다이나믹 프로그래밍

### 제출 일자

2024년 11월 23일 21:20:01

### 문제 설명

<p>그래프 이론에서 매칭(또는 독립 간선 집합)은 공통 정점을 가지지 않는 간선의 집합을 말한다.</p>

<p>그래프 G = (V,E)가 주어졌을 때, M이 G의 매칭이 되기 위해서는 M이 E의 부분 집합이면서, M에 포함되는 간선이 같은 정점을 공유하지 않아야 한다.</p>

<p>사이클 그래프 C<sub>n</sub>, n ≥ 3 은 단순 무방향 그래프이고, 정점의 집합은 {1,...,n}, 간선의 집합 E(C<sub>n</sub>) = {{a,b} | |a-b| ≡ 1 mod n}이다. 또, 2-정규 그래프이며, 간선의 수는 n개 이다. C<sub>3</sub>, C<sub>4</sub>, C<sub>5</sub>, C<sub>6</sub>은 아래 그림에 나와있다.</p>

<p><img alt="" src="https://www.acmicpc.net/upload/images/c1.png" style="height:167px; width:627px"></p>

<p>사이클 그래프 C<sub>n</sub>에서 매칭의 수를 구하는 프로그램을 작성하시오.</p>

<p><img alt="" src="https://www.acmicpc.net/upload/images/c2.png" style="height:117px; width:613px"></p>

<p>위의 그림은 C<sub>4</sub>의 모든 매칭을 나타낸 그림이다. 매칭에 해당하는 간선은 초록색으로 표시되어 있다.</p>

### 입력 

 <p>입력은 여러 개의 테스트 케이스로 이루어져 있고, 양의 정수 n으로 이루어져 있다. (3 ≤ n ≤ 10000)</p>

### 출력 

 <p>각 테스트 케이스에 대해서 C<sub>n</sub>의 매칭의 개수를 출력한다.</p>

