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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

// 작업자 : 이나겸
// 기본 구성요소 : 로또번호를 정하는 창, 로또의 결과값이 나오는 창, 결과를 출력하는 창
// 로또번호를 정하는 창은 메인에서 1 ~ 5사이의 값을 받아와서 해당 개수만큼 구현해야 함.

public class DialogPnl extends JDialog {

	int ballCount = 0;
	private JButton againButton; // 결과 확인 창에서 쓰이는 다시하기 버튼

	private FontHolder fontHolder = new FontHolder(); // 폰트 활용을 위한 class 참조

	private List<Integer> intList;// 재민수정
	private List<List<Integer>> saveList = new ArrayList<>();// 재민수정
	private List<JCheckBox> checkNumList;// 재민수정
	private int buyLottoPageCount = 1;// 재민수정
	private List<String> buyLottoPageCountList = new ArrayList<>(); // 재민수정
	private int lottoCountmultiple = 0;

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
			setFont(fontHolder.getUseFont(Font.BOLD, 20));
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
	public DialogPnl(int lottoCount, int lottoPlayCount, JFrame mainPnl, List<List<List<JCheckBox>>> saveCheckBox) {

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

		// 로또 구매 창 패널에 들어갈 요소들

		JPanel northPanel = new JPanel();
		buyLottoPanel.add(northPanel, new BorderLayout().NORTH);
		northPanel.setLayout(new BorderLayout());

		JLabel textLabel = new JLabel("인생 역전 로또   "); // "인생 역전 로또" 레이블
		textLabel.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		northPanel.add(textLabel, "East");

		JPanel spacePanel = new JPanel(); // spaceLabel과 allAutoButton이 포함될 패널
		northPanel.add(spacePanel, "West");

		JLabel spaceLabel = new JLabel("   "); // 창 벽과 전체 자동 버튼의 사이를 띄우기 위한 레이블
		spacePanel.add(spaceLabel);

		JButton allAutoButton = new JButton("전체 자동"); // 전체 자동 버튼
		allAutoButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		spacePanel.add(allAutoButton);

		JButton beforeSameButton = new JButton("이전 회차 동일 적용"); // 이전 회차 동일 적용 버튼
		beforeSameButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		spacePanel.add(beforeSameButton);
		if (lottoPlayCount == 1) {
			beforeSameButton.setVisible(false);
		}
		
		List<String> round = new ArrayList<>();
		
		String[] numbers = new String[lottoPlayCount-1];
		for (int i = 0; i < lottoPlayCount-1; i++) {
			round.add((i+1)+"회차");
			numbers[i] = round.get(i);
		}
		
		JComboBox<String> beforeLottoNum = new JComboBox<>(numbers);
		if (lottoPlayCount == 1) {
			beforeLottoNum.setVisible(false);
		}
		beforeLottoNum.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		spacePanel.add(beforeLottoNum);

		JButton allCancelButton = new JButton("전체 취소"); // 전체 취소 버튼
		allCancelButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		spacePanel.add(allCancelButton);

		JPanel westPanel = new JPanel(); // 창 벽과 패널의 사이를 띄우기 위한 패널(서쪽)
		buyLottoPanel.add(westPanel, new BorderLayout().WEST);
		westPanel.setPreferredSize(new Dimension(50, 500));

		JPanel eastPanel = new JPanel(); // 창 벽과 패널의 사이를 띄우기 위한 패널(동쪽)
		buyLottoPanel.add(eastPanel, new BorderLayout().EAST);
		eastPanel.setPreferredSize(new Dimension(50, 500));

		// 사용자가 체크박스로 선택한 번호를 담는 List (결과 확인 창에서 사용)
		List<List<JCheckBox>> resultShow = new ArrayList<>();

		// 메인창에서 사용자가 선택한 개수에 따라 로또 1 ~ 5개 펼쳐질 패널(밑바탕)
		JPanel firstPageCenterPanel = new JPanel();
		buyLottoPanel.add(firstPageCenterPanel, new BorderLayout().CENTER);

		JPanel buyLottoCenterPanel = new JPanel();
		CardLayout buyLottoCenterCardLayout = new CardLayout();
		buyLottoCenterPanel.setLayout(buyLottoCenterCardLayout);
		buyLottoPanel.add(buyLottoCenterPanel, new BorderLayout().CENTER);
		buyLottoCenterPanel.add(firstPageCenterPanel, "BuyPnl1");
		buyLottoPageCountList.add("BuyPnl1");

		List<List<Integer>> findBtnList = new ArrayList<>();
		List<JLabel> labelCollection = new ArrayList<>();
		List<Timer> timerCollection = new ArrayList<>();

		functionList.beforeBtnFunction(beforeSameButton, saveCheckBox, resultShow, findBtnList, labelCollection, beforeLottoNum, timerCollection);

		// 메인 창에서 사용자가 선택한 로또 개수(lottoCount)대로 includeNumChoicePanel 나타냄
		for (int i = 0; i < Integer.valueOf(lottoCount); i++) {

			firstPageCenterPanel.setLayout(new GridLayout(0, lottoCount, 50, 10)); // GridLayout

			// 번호 선택 패널, 자동 수동 반자동 버튼들이 있는 패널이 포함될 패널
			JPanel includeNumChoicePanel = new JPanel();
			includeNumChoicePanel.setLayout(new BorderLayout());

			// 자동 수동 반자동 버튼들이 있는 패널
			JPanel includeButtonsPanel = new JPanel();
			includeNumChoicePanel.add(includeButtonsPanel, "North");

			// 번호 선택 체크박스들이 있는 번호 선택 패널
			JPanel numChoicePanel = new JPanel();
			numChoicePanel.setLayout(new GridLayout(0, 5, 10, 10)); // GridLayout
			includeNumChoicePanel.add(numChoicePanel, "Center");

			// printOorX 레이블이 포함될 패널
			JPanel printOXPnl = new JPanel();
			printOXPnl.setLayout(new BorderLayout());
			includeNumChoicePanel.add(printOXPnl, "South");

			JButton sameNumberButton = new JButton("위의 번호로 모두 선택"); // 위의 번호로 모두 선택 버튼
			sameNumberButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
			printOXPnl.add(sameNumberButton, "North");

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
				checkNumBox.setFont(fontHolder.getUseFont(Font.BOLD, 15));
				checkNumBox.setEnabled(false); // 체크박스 비활성화
				checkNumList.add(checkNumBox); // 번호 선택 체크박스를 List에 add
				numChoicePanel.add(checkNumBox); // 번호 선택 체크박스를 패널에 add
			}
			resultShow.add(checkNumList); // 사용자가 선택한 번호를 담는 List에 번호 선택 체크박스를 담은 List를 add

			JButton autoButton = new JButton("자동"); // 자동 버튼
			autoButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
			JButton selfButton = new JButton("수동"); // 수동 버튼
			selfButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
			JButton halfAutoButton = new JButton("반자동"); // 반자동 버튼
			halfAutoButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));

			includeButtonsPanel.add(autoButton);
			includeButtonsPanel.add(selfButton);
			includeButtonsPanel.add(halfAutoButton);

			firstPageCenterPanel.add(includeNumChoicePanel);

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
			// 위의 번호로 모두 선택 버튼을 눌렀을 때 기능 메소드
			functionList.unityCheckBox(checkNumList, resultShow, findBtnList, sameNumberButton, labelCollection, timerCollection, timer);
			// 전체 취소 버튼을 눌렀을 때 기능 메소드
//			functionList

			firstPageCenterPanel.revalidate(); // 레이아웃을 다시 계산
			firstPageCenterPanel.repaint(); // 바뀐 사항 새로 그려 줌
		}

		JPanel includeSendButtonPanel = new JPanel(); // 번호 제출 버튼이 포함될 패널
		buyLottoPanel.add(includeSendButtonPanel, new BorderLayout().SOUTH);

		JButton preButton = new JButton("이전 장"); // 이전 장 버튼
		preButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		includeSendButtonPanel.add(preButton);

		JButton sendButton = new JButton("번호 제출"); // 번호 제출 버튼
		sendButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		includeSendButtonPanel.add(sendButton);

		JButton nextButton = new JButton("다음 장"); // 다음 장 버튼
		nextButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		includeSendButtonPanel.add(nextButton);

		// 페이지 수 표시 레이블
		JLabel pageCountLabel = new JLabel("<" + String.valueOf(buyLottoPageCount) + ">");
		pageCountLabel.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		includeSendButtonPanel.add(pageCountLabel);

		// 이전 장 버튼 눌렀을 때
		// (로또를 여러 장 구매했을 경우 이전 장으로 넘기기)
		preButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (buyLottoPageCount > 1) {
					buyLottoPageCount--;
					pageCountLabel.setText("<" + String.valueOf(buyLottoPageCount) + ">");
					buyLottoCenterCardLayout.show(buyLottoCenterPanel, "BuyPnl" + buyLottoPageCount);
					
//					beforeSameButton.setEnabled(true);
				} 
			}
		});

		// 다음 장 버튼 눌렀을 때
		// (로또를 여러 장 구매했을 경우 다음 장으로 넘기기)
		nextButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				lottoCountmultiple += lottoCountmultiple;

				if (buyLottoPageCount < 10) {
					buyLottoPageCount++;
					if (buyLottoPageCountList.contains("BuyPnl" + (buyLottoPageCount + 1))) {
					} else {
						for (int i = 0; i < lottoCount; i++) {

							// 체크박스들 중 체크가 되어있는 것들(사용자가 선택한 번호들)만 넣은 List
							intList = functionList.returnCheckBoxListToIntegerList(resultShow.get(i));
							Collections.sort(intList); // 순서대로 정렬
							if (intList.size() != 0) {
								saveList.add(intList);
							}
						}

						JPanel pageCenterPanel = new JPanel();
						buyLottoCenterPanel.add(pageCenterPanel, "BuyPnl" + buyLottoPageCount);
						buyLottoPageCountList.add("BuyPnl" + buyLottoPageCount);
						// 메인 창에서 사용자가 선택한 로또 개수(lottoCount)대로 includeNumChoicePanel 나타냄

						for (int i = 0; i < Integer.valueOf(lottoCount); i++) {

							pageCenterPanel.setLayout(new GridLayout(0, lottoCount, 50, 10)); // GridLayout

							// 번호 선택 패널, 자동 수동 반자동 버튼들이 있는 패널이 포함될 패널
							JPanel includeNumChoicePanel = new JPanel();
							includeNumChoicePanel.setLayout(new BorderLayout());

							// 자동 수동 반자동 버튼들이 있는 패널
							JPanel includeButtonsPanel = new JPanel();
							includeNumChoicePanel.add(includeButtonsPanel, "North");

							// 번호 선택 체크박스들이 있는 번호 선택 패널
							JPanel numChoicePanel = new JPanel();
							numChoicePanel.setLayout(new GridLayout(0, 5, 10, 10)); // GridLayout
							includeNumChoicePanel.add(numChoicePanel, "Center");

							// printOorX 레이블이 포함될 패널
							JPanel printOXPnl = new JPanel();
							printOXPnl.setLayout(new BorderLayout());
							includeNumChoicePanel.add(printOXPnl, "South");

							JButton sameNumberButton = new JButton("위의 번호로 모두 선택"); // 위의 번호로 모두 선택 버튼
							sameNumberButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
//							sameNumberButton.setEnabled(false); // 버튼 비활성화
							printOXPnl.add(sameNumberButton, "North");

							JLabel printOorX = new JLabel("X"); // 로또 번호 선택 완료 여부 나타내는 레이블
							labelCollection.add(printOorX);
							printOorX.setFont(fontHolder.getUseFont(Font.BOLD, 35));
							printOorX.setHorizontalAlignment(JLabel.CENTER); // 텍스트 가운데 정렬
							printOXPnl.add(printOorX, "Center");

							sameNumberButton.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {

								}
							});

							// 번호 선택 체크박스를 담은 List
							checkNumList = new ArrayList<>();

							// 사용자가 선택할 번호 체크박스 (1 ~ 45)
							for (int j = 1; j <= 45; j++) {
								JCheckBox checkNumBox = new JCheckBox(String.valueOf(j));
								checkNumBox.setFont(fontHolder.getUseFont(Font.BOLD, 15));
								checkNumBox.setEnabled(false); // 체크박스 비활성화
								checkNumList.add(checkNumBox); // 번호 선택 체크박스를 List에 add
								numChoicePanel.add(checkNumBox); // 번호 선택 체크박스를 패널에 add
							}
							resultShow.add(checkNumList); // 사용자가 선택한 번호를 담는 List에 번호 선택 체크박스를 담은 List를 add

							JButton autoButton = new JButton("자동"); // 자동 버튼
							autoButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
							JButton selfButton = new JButton("수동"); // 수동 버튼
							selfButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
							JButton halfAutoButton = new JButton("반자동"); // 반자동 버튼
							halfAutoButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));

							includeButtonsPanel.add(autoButton);
							includeButtonsPanel.add(selfButton);
							includeButtonsPanel.add(halfAutoButton);

							pageCenterPanel.add(includeNumChoicePanel);

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
					}

					pageCountLabel.setText("<" + String.valueOf(buyLottoPageCount) + ">");
//					beforeSameButton.setEnabled(true);
					buyLottoCenterCardLayout.show(buyLottoCenterPanel, "BuyPnl" + buyLottoPageCount);

				} else {
					JOptionPane.showMessageDialog(DialogPnl.this, "최대 10페이지까지만 가능해요");

				}

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

		// "결과 추첨 중..." 레이블
		// 당첨 숫자가 다 나오고 나면 레이블에 적힌 텍스트가 바뀜
		JLabel loadingLabel = new JLabel("결과 추첨 중...");
		loadingLabel.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		numberCheckBtnPanel.add(loadingLabel);

		JButton resultCheckButton = new JButton("결과 확인"); // 결과 확인 버튼
		resultCheckButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		numberCheckBtnPanel.add(resultCheckButton);

		JButton skipButton = new JButton("SKIP"); // SKIP 버튼
		skipButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		numberCheckBtnPanel.add(skipButton);

		BallLabel winNumLabel1 = new BallLabel(Integer.parseInt(result.get(0)), null, null, null);

		BallLabel winNumLabel2 = new BallLabel(Integer.parseInt(result.get(1)), null, null, null);

		BallLabel winNumLabel3 = new BallLabel(Integer.parseInt(result.get(2)), null, null, null);

		BallLabel winNumLabel4 = new BallLabel(Integer.parseInt(result.get(3)), null, null, null);

		BallLabel winNumLabel5 = new BallLabel(Integer.parseInt(result.get(4)), null, null, null);

		BallLabel winNumLabel6 = new BallLabel(Integer.parseInt(result.get(5)), null, null, null);

		JLabel plusLabel = new JLabel();
		plusLabel.setIcon(new ImageIcon(MainPnl.class.getResource("/image/plus.png")));// 레이블에 이미지 불러옴

		BallLabel bonusNumLabel = new BallLabel(Integer.parseInt(result.get(6)), null, null, null);

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

		// 결과 확인 패널에 들어갈 요소들

		// 결과 확인 창에서 다시하기 버튼을 누르면 이 레이블에 포함된 숫자가 바뀜 (lottoPlayCount 이용)
		JLabel text2Label = new JLabel("인생역전 로또 제 " + lottoPlayCount + "회 결과");
		text2Label.setFont(fontHolder.getUseFont(Font.BOLD, 25));
		resultCheckPanel.add(text2Label, new BorderLayout().NORTH);
		text2Label.setHorizontalAlignment(JLabel.CENTER); // 레이블이 항상 북쪽의 중간에 위치

		// winNumPanel, includeLabelsPanel이 포함될 패널
		JPanel thirdPageCenterPanel = new JPanel();
		thirdPageCenterPanel.setLayout(new GridLayout(6, 1));
		resultCheckPanel.add(thirdPageCenterPanel);

		// 당첨 숫자 레이블들 포함될 패널
		JPanel winNumPanel = new JPanel();
		thirdPageCenterPanel.add(winNumPanel);

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

		// 버튼들 포함될 패널
		JPanel includeButtonsPanel2 = new JPanel();
		resultCheckPanel.add(includeButtonsPanel2, new BorderLayout().SOUTH);

		againButton = new JButton("다시하기"); // 다시하기 버튼
		againButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		JButton closeButton = new JButton("종료"); // 종료 버튼
		closeButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));

		includeButtonsPanel2.add(againButton);
		includeButtonsPanel2.add(closeButton);

// --------------------------------------------------------------------------------------------------

		centerPanel.add(buyLottoPanel, "BuyLotto");
		centerPanel.add(numberCheckPanel, "NumberCheck");
		centerPanel.add(resultCheckPanel, "ResultCheck");

		getContentPane().add(centerPanel); // 패널에 컴포넌트들을 붙임

		// 로또 구매 창에서 번호 제출 버튼을 눌렀을 때
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// 로또 구매 창에서 사용자가 로또 당 체크 박스를 6개 선택하고 번호 제출 버튼을 눌렀을 때
				if (functionList.checkAllSelected(resultShow)) {

					setSize(750, 650);

					functionList.saveCheckBoxNum(saveCheckBox, resultShow);

					// 번호 제출 버튼을 누르면 당첨 숫자 확인 창으로 넘어감
					cardLayout.show(centerPanel, "NumberCheck");

					// 당첨 숫자 확인 창에서 당첨 숫자들이 순차적으로 나타나게 하는 메소드
					functionList.changeToLabelVisible(loadingLabel, winNumLabel1, winNumLabel2, winNumLabel3,
							winNumLabel4, winNumLabel5, winNumLabel6, plusLabel, bonusNumLabel, resultCheckButton,
							skipButton);

					// 결과 확인 창에서 로또 개수(lottoCount)에 따라 includeLabelsPanel이 보여짐(1 ~ 5개)
					for (int i = 0; i < lottoCount; i++) {

						// 로또 개수, 사용자 선택 번호, 당첨 결과 레이블들 포함될 패널
						JPanel includeLabelsPanel = new JPanel();
						includeLabelsPanel.setLayout(new FlowLayout());
						thirdPageCenterPanel.add(includeLabelsPanel);

						// 체크박스들 중 체크가 되어있는 것들(사용자가 선택한 번호들)만 넣은 List
						intList = functionList.returnCheckBoxListToIntegerList(resultShow.get(i));
						Collections.sort(intList); // 순서대로 정렬

						saveList.add(intList);// 재민수정

						// 로또 개수 표시 레이블 (1, 2, 3, 4, 5)
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
						winLabel.setPreferredSize(new Dimension(100, 50));
						winLabel.setHorizontalTextPosition(JLabel.CENTER);
						winLabel.setHorizontalAlignment(SwingConstants.CENTER);
						winLabel.setFont(fontHolder.getUseFont(Font.BOLD, 15));
						includeLabelsPanel.add(winLabel);

						// winLabel에 글자를 바꿔줄 메소드
						functionList.setLabelTextToResult(winLabel, intList, result);

						thirdPageCenterPanel.revalidate(); // 레이아웃을 다시 계산
						thirdPageCenterPanel.repaint(); // 바뀐 사항을 다시 그려줌
					}

				} else {
					// 로또 구매 창에서 사용자가 로또 당 체크 박스를 6개 선택하지 않고 번호 제출 버튼을 눌렀을 때 뜰 메세지창
					JOptionPane.showMessageDialog(DialogPnl.this, "각 로또 당 숫자를 6개 선택해주세요.");
				}
			}

			// autoOrSemiautoLabel의 크기, 텍스트 정렬, 폰트 지정, 패널에 add 메소드
			private void autoOrSemiautoLabelDeco(JPanel includeLabelsPanel, JLabel autoOrSemiautoLabel) {
				autoOrSemiautoLabel.setPreferredSize(new Dimension(50, 40));
				autoOrSemiautoLabel.setHorizontalTextPosition(JLabel.CENTER);
				autoOrSemiautoLabel.setHorizontalAlignment(SwingConstants.CENTER);
				autoOrSemiautoLabel.setFont(fontHolder.getUseFont(Font.BOLD, 18));
				includeLabelsPanel.add(autoOrSemiautoLabel);
			}
		});

		// 종료 버튼 누르면 다이얼로그 창, 기본 창 같이 닫힘
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // 기본 창 닫힘
			}
		});

		allAutoButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < resultShow.size(); i++) {
					functionList.autoChoose(timerCollection.get(i), resultShow.get(i), findBtnList.get(i),
							labelCollection.get(i));
				}
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
}