<h1 align="center">
  LOTTO (GUI 프로젝트)
</h1>
<p align="center">로또 구매 프로그램</p>
<p align="center">
  복권 구매 경험이 없는 사용자를 위한 가상 로또 시뮬레이션<br>
  '동행 복권 로또 6/45'의 등위 결정 방법과 동일한 방식 적용
</p>

---

## key Features

<br>

- 기능들이 실행되는 큰 틀은 메인 창과 다이얼로그 창으로 구성
- In-Memory 방식으로 데이터가 GUI에서 생성, 저장되며 프로그램 종료시 (메인창 종료시) 데이터도 삭제됨

<br>

<메인 창>

- 로또 구매 수량 선택 
- 로또 구매 회차 및 보유 금액 확인 : 이전 회차들의 로또 구매 수량에 따라 보유 금액 차감 

<br>

<div align="center">
  <img src="https://github.com/user-attachments/assets/9751a83e-2438-48cf-9c31-82125a7e0944" width="400">
  <img src="https://github.com/user-attachments/assets/52ec4ac9-f4e1-455f-a598-03eea8b10a9b" width="400">
</div>

<br>

<다이얼로그 창>

- 메인 창에서 선택한 로또 수량대로 나타남
- (ex. 1장, 2장, 5장)

<br>

<div align="center">
  <img src="https://github.com/user-attachments/assets/083bd5d9-08db-4a33-adda-242941e30b86" width="300">
  <img src="https://github.com/user-attachments/assets/2875d622-f411-4884-822d-7ddaed38b2d8" width="375">
  <img src="https://github.com/user-attachments/assets/d4dfb1df-5df0-434c-a519-bfb7f38205e5" width="800">
</div>

<br>

- 자동 (전체 자동, 개별 자동), 수동, 반자동으로 로또 번호 선택 기능 
- 로또별 번호 선택 완료 여부 확인 가능 (O, X)

<br>

<div align="center">
  <img src="https://github.com/user-attachments/assets/63dd55ba-b745-4494-be25-066429bc9626" width="800">
</div>

<br>

- 위의 번호로 전체 적용 : 한 장의 로또에 번호를 선택하고 나머지 로또에도 같은 번호를 적용할 수 있는 기능
- 이전 회차 동일 적용 : 지난 회차에 선택한 번호를 동일 적용할 수 있는 기능
- 지불 예정 금액 확인 : 선택한 로또 수량으로 금액 계산

<br>

<div align="center">
  <img src="https://github.com/user-attachments/assets/5437fc23-ab04-4a10-b03c-c1b97280834f" width="800">
  <img src="https://github.com/user-attachments/assets/ed9c8362-9cce-4a06-993b-8932e4fcfddc" width="800">
</div>

<br>

- Timer를 활용한 당첨 번호 애니메이션
- Timer 객체로 이벤트 스케쥴링을 구현 후, 랜덤 생성된 당첨 번호가 화면상에 일정한 주기로 렌더링 되는 리스너 구현

<br>

<div align="center">
  <img src="https://github.com/user-attachments/assets/719321f0-8cd9-4c9a-b650-e66fa5e62107" width="500">
</div>

<br>

- 로또 결과 확인
- 자동, 수동으로 선택한 숫자를 점선과 실선을 이용해서 나타내고 당첨 숫자와 일치하는 번호는 색을 다르게해서 직관적으로<br>
  파악할 수 있게 함
- 당첨된 목록만 확인할 수 있는 기능

<br>

<div align="center">
  <img src="https://github.com/user-attachments/assets/c3da2124-4ca6-453d-be29-baf0ca53d6ce" width="400">
  <img src="https://github.com/user-attachments/assets/6f1da1d9-e690-478a-a662-b830d72a0851" width="400">
</div>

<br>

<h2>Built With</h2>
<br>

<div>
  <img src="https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white">
  <img src="https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white">
  <img src="https://img.shields.io/badge/git-%23F05032?logo=git&logoColor=black">  
</div>
<br>

## Data Flow

<br>

<div align="center">
  <img src="https://github.com/user-attachments/assets/126a279e-b62f-41bb-81b0-e4858b809a44" width="1000">
</div>

<br>

## Development setup 

<br>

- 작업 프로젝트 생성, 공유 repository 생성 등 역할 분배하여 개발환경 구축
- Git 브랜치 관리 전략 : master 브랜치 하나로만 프로젝트 진행

## collaboration process

<br>

<div align="center">
  <img src="https://github.com/user-attachments/assets/67fd7ac0-a821-4cb2-828d-921dfb62c21d" width="500">
</div>

<br>

- 각 요소들의 설계 초안을 ppts 파일을 이용하여 부가 설명 진행

<br>

<div align="center">
  <img src="https://github.com/user-attachments/assets/43652033-c673-4fa3-a43b-8ad1a1997b46" width="800">
</div>

<br><br>

---

<br>

## 자주 묻는 질문

<h3>Git 브랜치 전략으로 master 브랜치 하나로만 진행하기를 채택한 이유는?</h3>

- 프로그램을 수시로 실행하면서 기능 확인을 해야하기 때문에 지속적인 배포가 필요하다고 판단
- 서로 작성하는 내용에 최대한 간섭 및 충돌이 일어나지 않도록 각자 클래스를 나누어 작성하고<br>
  본인 것이 아닌 팀원이 작성하고 있는 영역의 코드를 변경을 해야하는 경우에는 먼저 알리고 상호 합의하에 내용을 변경하거나<br>
  각자 백업본을 만들어서 같은 클래스에 동시 작업 가능하게 함

<br>

---

## Authors

* GitHub  [@이나겸](https://github.com/NakyeomLee)
  
