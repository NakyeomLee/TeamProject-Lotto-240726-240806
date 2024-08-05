import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneLayout;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;

import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import java.awt.SystemColor;

// 작업자 : 이나겸
// 기본 구성요소 : 로또번호를 정하는 창, 로또의 결과값이 나오는 창, 결과를 출력하는 창
// 로또번호를 정하는 창은 메인에서 1 ~ 5사이의 값을 받아와서 해당 개수만큼 구현해야 함.

public class DialogPnl extends JDialog {

	private FontHolder fontHolder = new FontHolder(); // 폰트 활용을 위한 class 참조
	
	int ballCount = 0;
	private int nowMoney; // 로또 구매 창에서 활용할 현재 보유 금액
	private int buyLottoPageCount = 1;

	private JButton againButton; // 결과 확인 창에서 쓰이는 다시하기 버튼

	private List<Integer> intList;
	private List<JCheckBox> checkNumList;
	private List<List<Integer>> saveList = new ArrayList<>();
	private List<String> buyLottoPageCountList = new ArrayList<>();
	private List<JPanel> pnlList = new ArrayList<>();
	private List<JLabel> winLabelCollection = new ArrayList<>();

	class BallLabel extends JLabel {

		public BallLabel(int number, List<Integer> findBtnList, List<String> winnerList, List<Integer> intList) {

			FunctionList functionList = new FunctionList();

			if (ballCount <= 13) {

				if (ballCount == 6 || ballCount == 13) {
					setIcon(new ImageIcon(MainPnl.class.getResource("/image/BonusBall.png"))); // 레이블에 이미지 불러옴

				} else {
					setIcon(new ImageIcon(MainPnl.class.getResource("/image/ball2.png"))); // 레이블에 이미지 불러옴
				}

			} else {

				if (functionList.check2ndPlace(winnerList, intList)) {
					makeLabel(number, findBtnList, winnerList);

					int bonusNum = Integer.parseInt(winnerList.get(6));

					if (number == bonusNum) {
						setIcon(new ImageIcon(MainPnl.class.getResource("/image/BonusBall.png"))); // 레이블에 이미지 불러옴
					}

				} else {
					makeLabel(number, findBtnList, winnerList);
				}
			}

			setHorizontalTextPosition(JLabel.CENTER); // 레이블의 텍스트 가로 부분을 가운데로 고정
			setVerticalTextPosition(JLabel.CENTER);// 레이블의 텍스트 세로 부분을 가운데로 고정
			setFont(fontHolder.getUseFont(Font.BOLD, 20)); // 폰트 설정
			setForeground(Color.WHITE); // 글자 색깔 설정
			setText(String.valueOf(number)); // 글자 설정
			setHorizontalAlignment(SwingConstants.CENTER); // 레이블 자체의 수평 위치를 중간으로
			setVerticalAlignment(SwingConstants.CENTER); // 레이블 자체의 수 위치를 중간으로
			ballCount++;
		}

		private void makeLabel(int number, List<Integer> findBtnList, List<String> winnerList) {
			List<Integer> sortWinnerList = new ArrayList<>();

			for (int i = 0; i < 6; i++) {
				sortWinnerList.add(Integer.parseInt(winnerList.get(i)));
			}

			// 로또 구매 창에서 자동 버튼 선택
			if (findBtnList.get(0) == 1) {

				if (sortWinnerList.contains(number)) {
					setIcon(new ImageIcon(MainPnl.class.getResource("/image/ballAutoCorrect.png"))); // 레이블에 이미지 불러옴

				} else {
					setIcon(new ImageIcon(MainPnl.class.getResource("/image/ballAutoWrong.png"))); // 레이블에 이미지 불러옴

				}

				// 로또 구매 창에서 수동 버튼 선택
			} else if (findBtnList.get(0) == 2) {

				if (sortWinnerList.contains(number)) {
					setIcon(new ImageIcon(MainPnl.class.getResource("/image/ballSelfCorrect.png"))); // 레이블에 이미지 불러옴

				} else {
					setIcon(new ImageIcon(MainPnl.class.getResource("/image/ballSelfWrong.png"))); // 레이블에 이미지 불러옴

				}

				// 로또 구매 창에서 반자동 버튼 선택
			} else if (findBtnList.get(0) == 3) {

				List<Integer> semiAutoList = new ArrayList<>();

				for (int i = 1; i < findBtnList.size(); i++) {
					semiAutoList.add(findBtnList.get(i));
				}

				// 반자동 선택 중 자동 선택 번호와 당첨 번호가 일치함
				if (semiAutoList.contains(number) && sortWinnerList.contains(number)) {
					setIcon(new ImageIcon(MainPnl.class.getResource("/image/ballAutoCorrect.png"))); // 레이블에 이미지 불러옴

					// 반자동 선택 중 자동 선택 번호와 당첨 번호가 일치하지 않음
				} else if (semiAutoList.contains(number) && !sortWinnerList.contains(number)) {
					setIcon(new ImageIcon(MainPnl.class.getResource("/image/ballAutoWrong.png"))); // 레이블에 이미지 불러옴

					// 반자동 선택 중 수동 선택 번호와 당첨 번호가 일치함
				} else if (!semiAutoList.contains(number) && sortWinnerList.contains(number)) {
					setIcon(new ImageIcon(MainPnl.class.getResource("/image/ballSelfCorrect.png"))); // 레이블에 이미지 불러옴

					// 반자동 선택 중 수동 선택 번호와 당첨 번호가 일치하지 않음
				} else if (!semiAutoList.contains(number) && !sortWinnerList.contains(number)) {
					setIcon(new ImageIcon(MainPnl.class.getResource("/image/ballSelfWrong.png"))); // 레이블에 이미지 불러옴
				}
			}
		}
	}

	// 다이얼로그 창 생성자
	public DialogPnl(int lottoCount, int lottoPlayCount, JFrame mainPnl, List<List<List<JCheckBox>>> saveCheckBox,
			int money) {

		FunctionList functionList = new FunctionList();

		CardLayout cardLayout = new CardLayout();

		setModal(true); // 다이얼로그 창 닫기 전까지 다른 동작 불가

		// 다이얼로그 창에서 모든 페이지가 다 포함되는 패널(밑바탕)
		JPanel centerPanel = new JPanel();
		centerPanel.setLayout(cardLayout); // CardLayout

// <로또 구매 창>------------------------------------------------------------------------------------------------

		// 로또 구매 창 패널 (다이얼로그 창의 첫번째 페이지)
		JPanel buyLottoPanel = new JPanel();
		buyLottoPanel.setLayout(new BorderLayout());

		// buttonsPanel, paymentMoneyLabel 포함될 패널
		JPanel buyNorthPanel = new JPanel();
		buyLottoPanel.add(buyNorthPanel, new BorderLayout().NORTH);
		buyNorthPanel.setLayout(new BorderLayout());

		// 전체 자동, 전체 취소, 이전 회차 동일 적용, 콤보 박스 포함될 패널
		JPanel buttonsPanel = new JPanel();
		buyNorthPanel.add(buttonsPanel, "West");

		JPanel spacePanel = new JPanel(); // 창 벽과 버튼의 사이를 띄워놓기 위한 간격용 패널
		buttonsPanel.add(spacePanel);

		JButton allAutoButton = new JButton("전체 자동"); // 전체 자동 버튼
		allAutoButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		allAutoButton.setBackground(new Color(235, 255, 255));
		spacePanel.add(allAutoButton);

		JButton allCancelButton = new JButton("전체 취소"); // 전체 취소 버튼
		allCancelButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		allCancelButton.setBackground(new Color(255, 216, 216));
		spacePanel.add(allCancelButton);

		JButton beforeSameButton = new JButton("이전 회차 동일 적용"); // 이전 회차 동일 적용 버튼
		beforeSameButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		beforeSameButton.setBackground(new Color(235, 255, 255));
		spacePanel.add(beforeSameButton);
		if (lottoPlayCount == 1) {
			beforeSameButton.setVisible(false);
		}

		List<String> round = new ArrayList<>();

		String[] numbers = new String[lottoPlayCount - 1];
		for (int i = lottoPlayCount - 1, j = 0; i > 0; i--) {
			round.add((i) + "회차");
			numbers[j] = round.get(j);
			j++;
		}

		// 이전 회차 동일 적용 버튼을 누르기 전 몇 회차를 적용시킬지 알려 줄 콤보박스
		JComboBox<String> beforeLottoNum = new JComboBox<>(numbers);
		if (lottoPlayCount == 1) {
			beforeLottoNum.setVisible(false);
		}
		beforeLottoNum.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		spacePanel.add(beforeLottoNum);

		JPanel westSpacePanel = new JPanel(); // 창 벽과 패널의 사이를 띄우기 위한 간격용 패널(서쪽)
		westSpacePanel.setPreferredSize(new Dimension(50, 500));
		buyLottoPanel.add(westSpacePanel, new BorderLayout().WEST);

		JPanel eastSpacePanel = new JPanel(); // 창 벽과 패널의 사이를 띄우기 위한 간격용 패널(동쪽)
		eastSpacePanel.setPreferredSize(new Dimension(50, 500));
		buyLottoPanel.add(eastSpacePanel, new BorderLayout().EAST);

		// 사용자가 체크박스로 선택한 번호를 담는 List (결과 확인 창에서 사용)
		List<List<JCheckBox>> resultShow = new ArrayList<>();

		// 메인창에서 사용자가 선택한 개수에 따라 로또 1 ~ 5개 펼쳐질 패널(밑바탕)
		JPanel buyCenterPanel = new JPanel();
		buyLottoPanel.add(buyCenterPanel, new BorderLayout().CENTER);

		// 로또를 여러 장 구매할 경우 이전 장, 다음 장 버튼을 이용해 로또를 볼 수 있는 패널
		JPanel buyLottoCenterPanel = new JPanel();
		CardLayout buyLottoCenterCardLayout = new CardLayout();
		buyLottoCenterPanel.setLayout(buyLottoCenterCardLayout); // CardLayout
		buyLottoPanel.add(buyLottoCenterPanel, new BorderLayout().CENTER);
		buyLottoCenterPanel.add(buyCenterPanel, "BuyPnl1");
		buyLottoPageCountList.add("BuyPnl1");

		List<List<Integer>> findBtnList = new ArrayList<>();
		List<JLabel> labelCollection = new ArrayList<>();
		List<Timer> timerCollection = new ArrayList<>();

		functionList.beforeBtnFunction(beforeSameButton, saveCheckBox, resultShow, findBtnList, labelCollection,
				beforeLottoNum, timerCollection);

		// 지불 예정 금액 표시 레이블
		JLabel paymentMoneyLabel = new JLabel("지불 예정 금액 : " + resultShow.size() * 1000 + "원  (1게임 = 1000원)");
		paymentMoneyLabel.setFont(fontHolder.getUseFont(Font.BOLD, 23));
		buyNorthPanel.add(paymentMoneyLabel, "North");
		paymentMoneyLabel.setHorizontalAlignment(JLabel.CENTER); // 텍스트 가운데 정렬

		// 메인 창에서 사용자가 선택한 로또 개수(lottoCount)대로 includeNumChoicePanel 나타냄
		for (int i = 0; i < Integer.valueOf(lottoCount); i++) {

			buyCenterPanel.setLayout(new GridLayout(0, lottoCount, 50, 10)); // GridLayout

			JPanel centerPanel2 = new JPanel(); // 테두리와 체크박스 사이를 띄워놓기 위한 간격용 패널(밑바탕)
			centerPanel2.setLayout(new BorderLayout());
			centerPanel2.setBorder(new LineBorder(new Color(140, 172, 178)));
			buyCenterPanel.add(centerPanel2);

			JPanel westPanel2 = new JPanel(); // 테두리와 체크박스 사이를 띄워놓기 위한 간격용 패널(서쪽)
			westPanel2.setPreferredSize(new Dimension(20, 0));
			centerPanel2.add(westPanel2, "West");

			JPanel eastPanel2 = new JPanel(); // 테두리와 체크박스 사이를 띄워놓기 위한 간격용 패널(동쪽)
			eastPanel2.setPreferredSize(new Dimension(20, 0));
			centerPanel2.add(eastPanel2, "East");

			// 번호 선택 패널, 자동 수동 반자동 버튼들이 있는 패널이 포함될 패널
			JPanel includeNumChoicePanel = new JPanel();
			includeNumChoicePanel.setLayout(new BorderLayout());
			centerPanel2.add(includeNumChoicePanel, "Center");

			// 자동 수동 반자동 버튼들이 있는 패널
			JPanel includeButtonsPanel = new JPanel();
			includeNumChoicePanel.add(includeButtonsPanel, "North");

			// 번호 선택 체크박스들이 있는 번호 선택 패널
			JPanel numChoicePanel = new JPanel();
			numChoicePanel.setLayout(new GridLayout(0, 5, 10, 10)); // GridLayout
			includeNumChoicePanel.add(numChoicePanel, "Center");

			// sameNumberButton 버튼, printOorX 레이블이 포함될 패널
			JPanel printOXPnl = new JPanel();
			printOXPnl.setLayout(new BorderLayout());
			includeNumChoicePanel.add(printOXPnl, "South");

			JPanel includeButtonPanel = new JPanel(); // sameNumberButton 버튼이 포함될 패널
			printOXPnl.add(includeButtonPanel, "North");

			JButton sameNumberButton = new JButton("위의 번호로 전체 적용"); // 위의 번호로 전체 적용 버튼
			sameNumberButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
			sameNumberButton.setBackground(new Color(235, 255, 255));
			includeButtonPanel.add(sameNumberButton);

			JLabel printOorX = new JLabel("X"); // 로또 번호 선택 완료 여부 나타내는 레이블
			labelCollection.add(printOorX);
			printOorX.setFont(fontHolder.getUseFont(Font.BOLD, 35));
			printOorX.setHorizontalAlignment(JLabel.CENTER); // 텍스트 가운데 정렬
			printOXPnl.add(printOorX, "Center");

			// 번호 선택 체크박스를 담은 List
			List<JCheckBox> checkNumList = new ArrayList<>();

			// 사용자가 선택할 번호 체크박스 (1 ~ 45)
			for (int j = 1; j <= 45; j++) {
				JCheckBox checkNumBox = new JCheckBox(String.valueOf(j));
				checkNumBox.setFont(fontHolder.getUseFont(Font.BOLD, 15)); // 체크박스 폰트
				checkNumBox.setBorderPainted(true); // 체크박스 테두리 적용 (테두리 색 없이 그림자 진것처럼 나타남)
				checkNumBox.setEnabled(false); // 체크박스 비활성화
				checkNumList.add(checkNumBox); // 번호 선택 체크박스를 List에 add
				numChoicePanel.add(checkNumBox); // 번호 선택 체크박스를 패널에 add
			}
			resultShow.add(checkNumList); // 사용자가 선택한 번호를 담는 List에 번호 선택 체크박스를 담은 List를 add

			JButton autoButton = new JButton("자동"); // 자동 버튼
			autoButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
			autoButton.setBackground(new Color(235, 255, 255));

			JButton selfButton = new JButton("수동"); // 수동 버튼
			selfButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
			selfButton.setBackground(new Color(235, 255, 255));

			JButton halfAutoButton = new JButton("반자동"); // 반자동 버튼
			halfAutoButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
			halfAutoButton.setBackground(new Color(235, 255, 255));

			includeButtonsPanel.add(autoButton);
			includeButtonsPanel.add(selfButton);
			includeButtonsPanel.add(halfAutoButton);

			// 자동 수동 반자동 버튼들을 담는 List
			List<Integer> findBtn = new ArrayList<>();
			findBtnList.add(findBtn);

			Timer timer = functionList.makeTimer(checkNumList, printOorX);
			timerCollection.add(timer);

			// 자동 버튼을 눌렀을때 기능 메소드
			functionList.autoOrSemiAutoBtnFuntion(timer, autoButton, checkNumList, "auto", findBtn, printOorX);

			// 수동 버튼을 눌렀을 때 기능 메소드
			functionList.autoOrSemiAutoBtnFuntion(timer, selfButton, checkNumList, "self", findBtn, printOorX);

			// 반자동 버튼을 눌렀을 때 기능 메소드
			functionList.autoOrSemiAutoBtnFuntion(timer, halfAutoButton, checkNumList, "semiAuto", findBtn, printOorX);

			// 위의 번호로 전체 적용 버튼을 눌렀을 때 기능 메소드
			functionList.unityCheckBox(checkNumList, resultShow, findBtnList, sameNumberButton, labelCollection,
					timerCollection, timer);

			// 전체 자동 버튼을 눌렀을 때
			allAutoButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					for (int i = 0; i < resultShow.size(); i++) {
						functionList.autoChoose(timerCollection.get(i), resultShow.get(i), findBtnList.get(i),
								labelCollection.get(i));
					}
				}
			});

			paymentMoneyLabel.setText("지불 예정 금액 : " + resultShow.size() * 1000 + "원  (1게임 = 1000원)");

			// 전체 취소 버튼을 눌렀을 때 기능 메소드
			functionList.cancelAll(allCancelButton, resultShow, timerCollection, labelCollection);

			buyCenterPanel.revalidate(); // 레이아웃을 다시 계산
			buyCenterPanel.repaint(); // 바뀐 사항 새로 그려 줌
		}

		JPanel southPanel = new JPanel();
		southPanel.setLayout(new BorderLayout());
		buyLottoPanel.add(southPanel, new BorderLayout().SOUTH);

		JLabel moneyLabel = new JLabel("  보유금액 : " + money + " 원");
		moneyLabel.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		southPanel.add(moneyLabel, "West");

		// 이전 장 다음 장 번호제출 버튼, 페이지 표시 레이블이 포함된 패널
		JPanel includeSendButtonPanel = new JPanel();
		southPanel.add(includeSendButtonPanel, "Center");

		JButton preButton = new JButton("이전 장"); // 이전 장 버튼
		preButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		preButton.setBackground(new Color(235, 255, 255));
		includeSendButtonPanel.add(preButton);

		JButton sendButton = new JButton("번호 제출"); // 번호 제출 버튼
		sendButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		sendButton.setBackground(new Color(255, 216, 216));
		includeSendButtonPanel.add(sendButton);

		JButton nextButton = new JButton("다음 장"); // 다음 장 버튼
		nextButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		nextButton.setBackground(new Color(235, 255, 255));
		includeSendButtonPanel.add(nextButton);

		// 페이지 수 표시 레이블
		JLabel pageCountLabel = new JLabel("<" + String.valueOf(buyLottoPageCount) + ">");
		pageCountLabel.setPreferredSize(new Dimension(70, 50));
		pageCountLabel.setFont(fontHolder.getUseFont(Font.BOLD, 25));
		pageCountLabel.setHorizontalAlignment(JLabel.CENTER); // 레이블의 텍스트 중간 정렬
		southPanel.add(pageCountLabel, "East");

		// 이전 장 버튼 눌렀을 때
		// (로또를 여러 장 구매했을 경우 이전 장, 다음 장으로 넘기기 가능)
		preButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (buyLottoPageCount > 1) {
					buyLottoPageCount--;
					pageCountLabel.setText("<" + String.valueOf(buyLottoPageCount) + ">  ");
					buyLottoCenterCardLayout.show(buyLottoCenterPanel, "BuyPnl" + buyLottoPageCount);
				}
			}
		});

		// 다음 장 버튼 눌렀을 때
		// (로또를 여러 장 구매했을 경우 이전 장, 다음 장으로 넘기기 가능)
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int checkNextPageMake = 0;

				if (buyLottoPageCount < 10) {
					buyLottoPageCount++;

					if (buyLottoPageCountList.contains("BuyPnl" + (buyLottoPageCount))) {
						buyLottoCenterCardLayout.show(buyLottoCenterPanel, "BuyPnl" + buyLottoPageCount);

					} else {
						checkNextPageMake = JOptionPane.showConfirmDialog(DialogPnl.this, "로또를 더 구매하시겠습니까?");

						if (checkNextPageMake == 0) {
							JPanel pageCenterPanel = new JPanel();
							buyLottoCenterPanel.add(pageCenterPanel, "BuyPnl" + buyLottoPageCount);
							buyLottoPageCountList.add("BuyPnl" + buyLottoPageCount);

							// 메인 창에서 사용자가 선택한 로또 개수(lottoCount)대로 includeNumChoicePanel 나타냄
							for (int i = 0; i < Integer.valueOf(lottoCount); i++) {

								pageCenterPanel.setLayout(new GridLayout(0, lottoCount, 50, 10)); // GridLayout

								JPanel centerPanel2 = new JPanel(); // 테두리와 체크박스 사이를 띄워놓기 위한 간격용 패널(밑바탕)
								centerPanel2.setLayout(new BorderLayout());
								centerPanel2.setBorder(new LineBorder(new Color(140, 172, 178)));
								pageCenterPanel.add(centerPanel2);

								JPanel westPanel2 = new JPanel(); // 테두리와 체크박스 사이를 띄워놓기 위한 간격용 패널(서쪽)
								westPanel2.setPreferredSize(new Dimension(20, 0));
								centerPanel2.add(westPanel2, "West");

								JPanel eastPanel2 = new JPanel(); // 테두리와 체크박스 사이를 띄워놓기 위한 간격용 패널(동쪽)
								eastPanel2.setPreferredSize(new Dimension(20, 0));
								centerPanel2.add(eastPanel2, "East");

								// 번호 선택 패널, 자동 수동 반자동 버튼들이 있는 패널이 포함될 패널
								JPanel includeNumChoicePanel = new JPanel();
								includeNumChoicePanel.setLayout(new BorderLayout());
								centerPanel2.add(includeNumChoicePanel, "Center");

								// 자동 수동 반자동 버튼들이 있는 패널
								JPanel includeButtonsPanel = new JPanel();
								includeNumChoicePanel.add(includeButtonsPanel, "North");

								// 번호 선택 체크박스들이 있는 번호 선택 패널
								JPanel numChoicePanel = new JPanel();
								numChoicePanel.setLayout(new GridLayout(0, 5, 10, 10)); // GridLayout
								includeNumChoicePanel.add(numChoicePanel, "Center");

								// sameNumberButton 버튼, printOorX 레이블이 포함될 패널
								JPanel printOXPnl = new JPanel();
								printOXPnl.setLayout(new BorderLayout());
								includeNumChoicePanel.add(printOXPnl, "South");

								// sameNumberButton 버튼이 포함될 패널
								JPanel includeButtonPanel = new JPanel();
								printOXPnl.add(includeButtonPanel, "North");

								// 위의 번호로 전체 적용 버튼
								JButton sameNumberButton = new JButton("위의 번호로 전체 적용");
								sameNumberButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
								sameNumberButton.setBackground(new Color(235, 255, 255));
								includeButtonPanel.add(sameNumberButton);

								// 로또 번호 선택 완료 여부 나타내는 레이블
								JLabel printOorX = new JLabel("X");
								labelCollection.add(printOorX);
								printOorX.setFont(fontHolder.getUseFont(Font.BOLD, 35));
								printOorX.setHorizontalAlignment(JLabel.CENTER); // 텍스트 가운데 정렬
								printOXPnl.add(printOorX, "Center");

								// 번호 선택 체크박스를 담은 List
								checkNumList = new ArrayList<>();

								// 사용자가 선택할 번호 체크박스 (1 ~ 45)
								for (int j = 1; j <= 45; j++) {
									JCheckBox checkNumBox = new JCheckBox(String.valueOf(j));
									checkNumBox.setFont(fontHolder.getUseFont(Font.BOLD, 15));
									checkNumBox.setEnabled(false); // 체크박스 비활성화
									checkNumBox.setBorderPainted(true); // 체크박스 테두리 적용
									checkNumList.add(checkNumBox); // 번호 선택 체크박스를 List에 add
									numChoicePanel.add(checkNumBox); // 번호 선택 체크박스를 패널에 add
								}
								resultShow.add(checkNumList); // 사용자가 선택한 번호를 담는 List에 번호 선택 체크박스를 담은 List를 add

								JButton autoButton = new JButton("자동"); // 자동 버튼
								autoButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
								autoButton.setBackground(new Color(235, 255, 255));

								JButton selfButton = new JButton("수동"); // 수동 버튼
								selfButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
								selfButton.setBackground(new Color(235, 255, 255));

								JButton halfAutoButton = new JButton("반자동"); // 반자동 버튼
								halfAutoButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
								halfAutoButton.setBackground(new Color(235, 255, 255));

								includeButtonsPanel.add(autoButton);
								includeButtonsPanel.add(selfButton);
								includeButtonsPanel.add(halfAutoButton);

								// 자동 수동 반자동 버튼들을 담는 List
								List<Integer> findBtn = new ArrayList<>();
								findBtnList.add(findBtn);

								Timer timer = functionList.makeTimer(checkNumList, printOorX);
								timerCollection.add(timer);

								// 자동 버튼을 눌렀을때 기능 메소드
								functionList.autoOrSemiAutoBtnFuntion(timer, autoButton, checkNumList, "auto", findBtn,
										printOorX);

								// 수동 버튼을 눌렀을 때 기능 메소드
								functionList.autoOrSemiAutoBtnFuntion(timer, selfButton, checkNumList, "self", findBtn,
										printOorX);

								// 반자동 버튼을 눌렀을 때 기능 메소드
								functionList.autoOrSemiAutoBtnFuntion(timer, halfAutoButton, checkNumList, "semiAuto",
										findBtn, printOorX);

								// 위의 번호로 모두 선택 버튼을 눌렀을 때 기능 메소드
								functionList.unityCheckBox(checkNumList, resultShow, findBtnList, sameNumberButton,
										labelCollection, timerCollection, timer);

								pageCenterPanel.revalidate(); // 레이아웃을 다시 계산
								pageCenterPanel.repaint(); // 바뀐 사항 새로 그려 줌
							}
						} else {
							buyLottoPageCount--;
						}
					}
					if (checkNextPageMake == 0) {

						pageCountLabel.setText("<" + String.valueOf(buyLottoPageCount) + ">  ");
						buyLottoCenterCardLayout.show(buyLottoCenterPanel, "BuyPnl" + buyLottoPageCount);
					}

				} else {
					JOptionPane.showMessageDialog(DialogPnl.this, "최대 10페이지까지만 가능합니다."); // 다이얼로그 메세지 창
				}
				paymentMoneyLabel.setText("지불 예정 금액 : " + resultShow.size() * 1000 + "원  (1게임 = 1000원)");
			}
		});

// <당첨 숫자 확인 창>------------------------------------------------------------------------------------------------

		// 당첨 번호를 넣을 List
		List<String> winningNumbers = functionList.resultLottoNumber();
		List<Integer> sortArr = new ArrayList<>();
		String bonusNumber = winningNumbers.get(winningNumbers.size() - 1);

		for (int i = 0; i < winningNumbers.size() - 1; i++) {
			sortArr.add(Integer.valueOf(winningNumbers.get(i)));
		}
		Collections.sort(sortArr);

		List<String> result = new ArrayList<>();
		for (int i = 0; i < sortArr.size(); i++) {
			result.add(String.valueOf(sortArr.get(i)));
		}
		result.add(bonusNumber);

		// 당첨 숫자 확인 창 패널 (다이얼로그 창의 두번째 페이지)
		JPanel numberCheckPanel = new JPanel();
		numberCheckPanel.setLayout(new BorderLayout(0, 0));

		JPanel numberCheckCenterPanel = new JPanel();
		numberCheckPanel.add(numberCheckCenterPanel, BorderLayout.CENTER);
		numberCheckCenterPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblLottoRaffle = new JLabel();
		lblLottoRaffle.setIcon(new ImageIcon(DialogPnl.class.getResource("/image/lottoRaffle.gif")));

		lblLottoRaffle.setHorizontalAlignment(JLabel.CENTER);
		numberCheckCenterPanel.add(lblLottoRaffle);

		JPanel numberCheckSouthPanel = new JPanel();
		numberCheckPanel.add(numberCheckSouthPanel, BorderLayout.SOUTH);
		numberCheckSouthPanel.setLayout(new GridLayout(2, 0, 5, 5));

		JPanel numberCheckBallPanel = new JPanel();
		numberCheckSouthPanel.add(numberCheckBallPanel);

		JPanel numberCheckBtnPanel = new JPanel();
		numberCheckSouthPanel.add(numberCheckBtnPanel);

		// '결과 추첨 중...' 레이블
		// 당첨 숫자가 다 나오고 나면 레이블에 적힌 텍스트가 바뀜
		JLabel loadingLabel = new JLabel("결과 추첨 중...");
		loadingLabel.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		numberCheckBtnPanel.add(loadingLabel);

		JButton resultCheckButton = new JButton("결과 확인"); // 결과 확인 버튼
		resultCheckButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		resultCheckButton.setBackground(new Color(235, 255, 255));
		numberCheckBtnPanel.add(resultCheckButton);

		JButton skipButton = new JButton("SKIP"); // SKIP 버튼
		skipButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		skipButton.setBackground(new Color(235, 255, 255));
		numberCheckBtnPanel.add(skipButton);

		BallLabel winNumLabel1 = new BallLabel(Integer.parseInt(winningNumbers.get(0)), null, null, null);

		BallLabel winNumLabel2 = new BallLabel(Integer.parseInt(winningNumbers.get(1)), null, null, null);

		BallLabel winNumLabel3 = new BallLabel(Integer.parseInt(winningNumbers.get(2)), null, null, null);

		BallLabel winNumLabel4 = new BallLabel(Integer.parseInt(winningNumbers.get(3)), null, null, null);

		BallLabel winNumLabel5 = new BallLabel(Integer.parseInt(winningNumbers.get(4)), null, null, null);

		BallLabel winNumLabel6 = new BallLabel(Integer.parseInt(winningNumbers.get(5)), null, null, null);

		JLabel plusLabel = new JLabel();
		plusLabel.setIcon(new ImageIcon(MainPnl.class.getResource("/image/plus.png")));// 레이블에 이미지 불러옴

		BallLabel bonusNumLabel = new BallLabel(Integer.parseInt(winningNumbers.get(6)), null, null, null);

		getContentPane().add(centerPanel); // 패널에 컴포넌트들을 붙임
		numberCheckBallPanel.add(winNumLabel1);
		numberCheckBallPanel.add(winNumLabel2);
		numberCheckBallPanel.add(winNumLabel3);
		numberCheckBallPanel.add(winNumLabel4);
		numberCheckBallPanel.add(winNumLabel5);
		numberCheckBallPanel.add(winNumLabel6);
		numberCheckBallPanel.add(plusLabel);
		numberCheckBallPanel.add(bonusNumLabel);

		// SKIP 버튼을 누르면 결과 확인 창으로 넘어감
		skipButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(centerPanel, "ResultCheck");
			}
		});

		// 결과 확인 버튼을 누르면 결과 확인 창으로 넘어감
		resultCheckButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(centerPanel, "ResultCheck");
			}
		});

// <결과 확인 창>------------------------------------------------------------------------------------------------

		// 결과 확인 창 패널 (다이얼로그 창의 세번째 페이지)
		JPanel resultCheckPanel = new JPanel();
		resultCheckPanel.setLayout(new BorderLayout());

		// 몇 회 결과인지 표시하는 레이블, 이전 다음 버튼 포함될 패널
		JPanel resultNorthPanel = new JPanel();
		resultNorthPanel.setLayout(new BorderLayout());
		resultCheckPanel.add(resultNorthPanel, new BorderLayout().NORTH);

		// 결과 확인 창에서 다시하기 버튼을 누르면 이 레이블에 포함된 숫자가 바뀜 (lottoPlayCount 이용)
		JLabel resultTitleLabel = new JLabel("인생역전 로또 제 " + lottoPlayCount + "회 결과");
		resultTitleLabel.setFont(fontHolder.getUseFont(Font.BOLD, 25));
		resultNorthPanel.add(resultTitleLabel);
		resultTitleLabel.setHorizontalAlignment(JLabel.CENTER); // 레이블의 텍스트 중간 정렬

		// winNumPanel, includeLabelsPanel이 포함될 패널
		JPanel resultCenterPanel = new JPanel();
		resultCenterPanel.setLayout(new BorderLayout());
		resultCheckPanel.add(resultCenterPanel, "Center");

		JScrollPane resultScrollPanel = new JScrollPane(); // 스크롤 바
		resultScrollPanel.setLayout(new ScrollPaneLayout());

		JPanel resultContainPanel = new JPanel();
		resultContainPanel.setLayout(new GridLayout(resultShow.size(), 0, 5, 5));
		// 실제로 스크롤되는 컨텐츠 포함 (스크롤 하는 영역을 제한하고 스크롤 할 수 있게 해줌)
		resultScrollPanel.setViewportView(resultContainPanel);

		// 마우스 휠 리스너를 이용한 스크롤 속도 조정
		resultScrollPanel.addMouseWheelListener(new MouseWheelListener() {
			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				int scrollAmount = e.getScrollAmount();
				int scrollSpeed = 9; // 스크롤 속도 배수 조정 (9배)
				if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
					JScrollBar verticalBar = resultScrollPanel.getVerticalScrollBar();
					int scroll = e.getUnitsToScroll() * verticalBar.getUnitIncrement() * scrollSpeed;
					verticalBar.setValue(verticalBar.getValue() + scroll);
				}
			}
		});

		// 당첨 숫자 레이블들 포함될 패널
		JPanel winNumPanel = new JPanel();
		resultCenterPanel.add(winNumPanel, "North");
		resultCenterPanel.add(resultScrollPanel, "Center");
		resultScrollPanel.setViewportView(resultContainPanel);

		BallLabel winNum1 = new BallLabel(Integer.parseInt(result.get(0)), null, null, null);

		BallLabel winNum2 = new BallLabel(Integer.parseInt(result.get(1)), null, null, null);

		BallLabel winNum3 = new BallLabel(Integer.parseInt(result.get(2)), null, null, null);

		BallLabel winNum4 = new BallLabel(Integer.parseInt(result.get(3)), null, null, null);

		BallLabel winNum5 = new BallLabel(Integer.parseInt(result.get(4)), null, null, null);

		BallLabel winNum6 = new BallLabel(Integer.parseInt(result.get(5)), null, null, null);

		BallLabel bonusNum = new BallLabel(Integer.parseInt(result.get(6)), null, null, null);

		winNumPanel.add(winNum1);
		winNumPanel.add(winNum2);
		winNumPanel.add(winNum3);
		winNumPanel.add(winNum4);
		winNumPanel.add(winNum5);
		winNumPanel.add(winNum6);
		winNumPanel.add(bonusNum);

		// 다시하기 종료 버튼, 공 테두리 설명 레이블 포함될 패널
		JPanel resultSouthPanel = new JPanel();
		resultSouthPanel.setLayout(new BorderLayout());
		resultCheckPanel.add(resultSouthPanel, new BorderLayout().SOUTH);

		JPanel spacePanel2 = new JPanel(); // 창 벽과 당첨된 목록만 확인 버튼 사이 공간을 만들기 위한 패널
		resultSouthPanel.add(spacePanel2, "West");

		JButton printWinPnl = new JButton("당첨된 목록만 확인"); // 당첨된 목록만 확인 버튼
		printWinPnl.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		printWinPnl.setBackground(new Color(235, 255, 255));
		spacePanel2.add(printWinPnl);
		functionList.printWinNumPnlBtnFunction(printWinPnl, resultContainPanel, pnlList, winLabelCollection);

		JPanel includeButtonsPanel2 = new JPanel(); // 버튼들 포함될 패널
		resultSouthPanel.add(includeButtonsPanel2, "Center");

		againButton = new JButton("다시하기"); // 다시하기 버튼
		againButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		againButton.setBackground(new Color(235, 255, 255));
		includeButtonsPanel2.add(againButton);

		JButton closeButton = new JButton("종료"); // 종료 버튼
		closeButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		closeButton.setBackground(new Color(235, 255, 255));
		includeButtonsPanel2.add(closeButton);

		JLabel infoLabel = new JLabel("점선 : 자동, 실선 : 수동   "); // 공 테두리 설명 레이블
		infoLabel.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		resultSouthPanel.add(infoLabel, "East");

		// 종료 버튼 누르면 다이얼로그 창, 기본 창 같이 닫힘
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // 기본 창 닫힘
			}
		});

// --------------------------------------------------------------------------------------------------------------

		centerPanel.add(buyLottoPanel, "BuyLotto");
		centerPanel.add(numberCheckPanel, "NumberCheck");
		centerPanel.add(resultCheckPanel, "ResultCheck");

		getContentPane().add(centerPanel); // 패널에 컴포넌트들을 붙임

		// 로또 구매 창에서 번호 제출 버튼을 눌렀을 때
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				nowMoney = functionList.payMoney(money, resultShow);
				
				// 로또 구매 창에서 사용자가 로또 당 체크 박스를 6개 선택하고 번호 제출 버튼을 눌렀을 때
				if (nowMoney < 0) {
					JOptionPane.showMessageDialog(DialogPnl.this, "보유 금액이 부족하여 구매할 수 없습니다. 창을 닫고 처음부터 다시 시도해주세요.");

				} else if (functionList.checkAllSelected(resultShow)) {

					setSize(750, 650);

					functionList.saveCheckBoxNum(saveCheckBox, resultShow);

					// 번호 제출 버튼을 누르면 당첨 숫자 확인 창으로 넘어감
					cardLayout.show(centerPanel, "NumberCheck");

					// 당첨 숫자 확인 창에서 당첨 숫자들이 순차적으로 나타나게 하는 메소드
					functionList.changeToLabelVisible(loadingLabel, winNumLabel1, winNumLabel2, winNumLabel3,
							winNumLabel4, winNumLabel5, winNumLabel6, plusLabel, bonusNumLabel, resultCheckButton,
							skipButton);

					resultContainPanel.setLayout(new GridLayout(resultShow.size(), 0, 5, 5));// 재민수정2

					// 결과 확인 창에서 로또 개수(lottoCount)에 따라 includeLabelsPanel이 보여짐(1 ~ 5개)
					for (int i = 0; i < resultShow.size(); i++) {

						// 로또 개수, 사용자 선택 번호, 당첨 결과 레이블들 포함될 패널
						JPanel includeLabelsPanel = new JPanel();
						includeLabelsPanel.setLayout(new FlowLayout());
						resultContainPanel.add(includeLabelsPanel);// 재민수정2
						pnlList.add(includeLabelsPanel);

						// 체크박스들 중 체크가 되어있는 것들(사용자가 선택한 번호들)만 넣은 List
						intList = functionList.returnCheckBoxListToIntegerList(resultShow.get(i));
						Collections.sort(intList); // 순서대로 정렬

						saveList.add(intList);

						// 로또 개수 표시 레이블
						JLabel countLabel = new JLabel(String.valueOf(i + 1) + ". ");
						countLabel.setFont(fontHolder.getUseFont(Font.BOLD, 18));
						includeLabelsPanel.add(countLabel);

						// 사용자가 로또 당 선택한 자동 수동 반자동 표시 레이블
						if (findBtnList.get(i).get(0) == 1) {
							JLabel autoOrSemiautoLabel = new JLabel("자동");
							autoOrSemiautoLabelDeco(includeLabelsPanel, autoOrSemiautoLabel);

						} else if (findBtnList.get(i).get(0) == 2) {
							JLabel autoOrSemiautoLabel = new JLabel("수동");
							autoOrSemiautoLabelDeco(includeLabelsPanel, autoOrSemiautoLabel);

						} else {
							JLabel autoOrSemiautoLabel = new JLabel("반자동");
							autoOrSemiautoLabelDeco(includeLabelsPanel, autoOrSemiautoLabel);
						}

						for (int j = 0; j < saveList.get(i).size(); j++) {
							BallLabel resultCheckLable = new BallLabel(saveList.get(i).get(j), findBtnList.get(i),
									result, saveList.get(i));
							includeLabelsPanel.add(resultCheckLable);
						}

						// n등 당첨, 낙첨 레이블 (당첨 여부에 따라 Label의 텍스트가 바뀜)
						JLabel winLabel = new JLabel();
						winLabelCollection.add(winLabel);
						winLabel.setPreferredSize(new Dimension(100, 50));
						winLabel.setHorizontalTextPosition(JLabel.CENTER);
						winLabel.setHorizontalAlignment(SwingConstants.CENTER);
						winLabel.setFont(fontHolder.getUseFont(Font.BOLD, 15));
						includeLabelsPanel.add(winLabel);

						// winLabel에 글자를 바꿔줄 메소드
						functionList.setLabelTextToResult(winLabel, intList, result);

						resultContainPanel.revalidate(); // 레이아웃을 다시 계산
						resultContainPanel.repaint(); // 바뀐 사항을 다시 그려줌
					}

				} else {
					// 로또 구매 창에서 사용자가 로또 당 체크 박스를 6개 선택하지 않고 번호 제출 버튼을 눌렀을 때 뜰 메세지창
					int xlblNum = 0;
					for (int j = 0; j < labelCollection.size(); j++) {
						if (labelCollection.get(j).getText().equals("X")) {
							xlblNum = j;
						}
					}
					int count = 0;
					while (xlblNum >= 0) {
						xlblNum -= lottoCount;
						count++;
					}
					String message = count + "페이지에 번호가 제대로 선택되지않은 로또가 있습니다.";
					JOptionPane.showMessageDialog(DialogPnl.this, message); // 다이얼로그 메세지 창
				}
			}

			// autoOrSemiautoLabel의 크기, 텍스트 정렬, 폰트 지정, 패널에 add 메소드
			private void autoOrSemiautoLabelDeco(JPanel includeLabelsPanel, JLabel autoOrSemiautoLabel) {
				autoOrSemiautoLabel.setPreferredSize(new Dimension(50, 40)); // 레이블 크기 설정 (고정하기 위해 일부러 설정)
				autoOrSemiautoLabel.setHorizontalTextPosition(JLabel.CENTER); // 레이블의 텍스트 수평 가운데 정렬
				autoOrSemiautoLabel.setHorizontalAlignment(SwingConstants.CENTER); // 레이블의 텍스트 수직 가운데 정렬
				autoOrSemiautoLabel.setFont(fontHolder.getUseFont(Font.BOLD, 18)); // 폰트 설정
				includeLabelsPanel.add(autoOrSemiautoLabel);
			}
		});

		pack(); // 다이얼로그 창 크기 알아서 조절 되도록
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	// 필드로 만들어둔 다시하기 버튼(AgainButton)의 getter setter
	public JButton getAgainButton() {
		return againButton;
	}

	public void setAgainButton(JButton againButton) {
		this.againButton = againButton;
	}

	// nowMoney(로또 구매 창에서 활용할 현재 보유 금액)의 getter
	public int getNowMoney() {
		return nowMoney;
	}
}