# [Gold V] 문자열 게임 - 27980 

[문제 링크](https://www.acmicpc.net/problem/27980) 

### 성능 요약

메모리: 114748 KB, 시간: 968 ms

### 분류

다이나믹 프로그래밍, 문자열

### 제출 일자

2024년 11월 8일 20:13:35

### 문제 설명

<p>건덕이는 문자가 일렬로 적혀있는 보드에서 게임을 하고 있다. 보드에는 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N$</span></mjx-container> 개의 알파벳 대문자가 나란히 적혀있다. 건덕이는 또 다른 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D440 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>M</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$M$</span></mjx-container> 자리 영어 단어를 가지고 게임을 진행한다.</p>

<p>우선 보드의 한 지점에서 첫 번째 문자가 보드의 문자와 일치하는지 확인한다. L(왼쪽) 또는 R(오른쪽) 방향으로 이동한 후에 다음 문자와 보드의 문자가 일치하는지 확인한다. 일치할 경우 점수를 1점 얻는다. 단, 이동한 후 보드 바깥으로 벗어날 수 없다. 건덕이가 가지고 있는 단어의 끝에 도달하면 게임을 종료한다.</p>

<p>예를 들어, 보드에 "KONKUK" 이라는 문자가 적혀있고, "KONDUCK" 이라는 단어로 게임을 시작한다면, 2번째 문자부터 RRRRLL 순으로 이동한다면 마지막 1개의 문자만이 일치해 점수를 1점 얻는다. 최대 점수를 얻으려면, 1번째 문자부터 RRRRLR 순으로 이동하면 된다. 이 경우 "KONU"가 일치하여 점수를 4점 얻는다.</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/30145f35-994c-4f87-b19a-677060970ba8/-/preview/"></p>

<p>보드와 가지고 있는 단어가 주어졌을 때, 건덕이가 얻을 수 있는 최대 점수를 구하는 프로그램을 작성해보자.</p>

### 입력 

 <p>첫째 줄에 보드의 길이와 건덕이가 가지고 있는 문자열의 길이를 나타내는 정수 <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mi class="mjx-i"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi><mjx-mo class="mjx-n"><mjx-c class="mjx-c2C"></mjx-c></mjx-mo><mjx-mi class="mjx-i" space="2"><mjx-c class="mjx-c1D440 TEX-I"></mjx-c></mjx-mi></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mi>N</mi><mo>,</mo><mi>M</mi></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$N, M$</span></mjx-container>이 공백으로 구분되어 주어진다. <mjx-container class="MathJax" jax="CHTML" style="font-size: 109%; position: relative;"><mjx-math class="MJX-TEX" aria-hidden="true"><mjx-mo class="mjx-n"><mjx-c class="mjx-c28"></mjx-c></mjx-mo><mjx-mn class="mjx-n"><mjx-c class="mjx-c32"></mjx-c></mjx-mn><mjx-mo class="mjx-n" space="4"><mjx-c class="mjx-c2264"></mjx-c></mjx-mo><mjx-mi class="mjx-i" space="4"><mjx-c class="mjx-c1D441 TEX-I"></mjx-c></mjx-mi><mjx-mo class="mjx-n"><mjx-c class="mjx-c2C"></mjx-c></mjx-mo><mjx-mi class="mjx-i" space="2"><mjx-c class="mjx-c1D440 TEX-I"></mjx-c></mjx-mi><mjx-mo class="mjx-n" space="4"><mjx-c class="mjx-c2264"></mjx-c></mjx-mo><mjx-mn class="mjx-n" space="4"><mjx-c class="mjx-c35"></mjx-c></mjx-mn><mjx-mstyle><mjx-mspace style="width: 0.167em;"></mjx-mspace></mjx-mstyle><mjx-mn class="mjx-n"><mjx-c class="mjx-c30"></mjx-c><mjx-c class="mjx-c30"></mjx-c><mjx-c class="mjx-c30"></mjx-c></mjx-mn><mjx-mo class="mjx-n"><mjx-c class="mjx-c29"></mjx-c></mjx-mo></mjx-math><mjx-assistive-mml unselectable="on" display="inline"><math xmlns="http://www.w3.org/1998/Math/MathML"><mo stretchy="false">(</mo><mn>2</mn><mo>≤</mo><mi>N</mi><mo>,</mo><mi>M</mi><mo>≤</mo><mn>5</mn><mstyle scriptlevel="0"><mspace width="0.167em"></mspace></mstyle><mn>000</mn><mo stretchy="false">)</mo></math></mjx-assistive-mml><span aria-hidden="true" class="no-mathjax mjx-copytext">$( 2 \le N, M \le 5\,000 )$</span> </mjx-container></p>

<p>둘째 줄에는 보드의 문자가 순서대로 주어진다.</p>

<p>셋째 줄에는 가지고 있는 문자열이 주어진다.</p>

<p>이 때, 두 문자열은 모두 알파벳 대문자로만 구성되어 있다.</p>

### 출력 

 <p>건덕이가 얻을 수 있는 최대 점수를 출력한다.</p>

