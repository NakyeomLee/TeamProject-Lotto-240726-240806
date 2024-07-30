import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridLayout;

// 작업자 : 이나겸
// 기본 구성요소 : 로또번호를 정하는 창, 로또의 결과값이 나오는 창, 결과를 출력하는 창
// 로또번호를 정하는 창은 메인에서 1 ~ 5사이의 값을 받아와서 해당 개수만큼 구현해야 함.

public class DialogPnl extends JDialog {

	int ballCount = 0;
	private JButton againButton;

	class BallLabel extends JLabel {

		public BallLabel(int number, List<Integer> findBtnList, List<String> winnerList, List<Integer> intList) {

			FunctionList functionList = new FunctionList();
			if (ballCount <= 13) {
				if (ballCount == 6 || ballCount == 13) {
					setIcon(new ImageIcon(MainPnl.class.getResource("/image/BonusBall.png")));// 레이블에 이미지 불러옴
				} else {
					setIcon(new ImageIcon(MainPnl.class.getResource("/image/ball2.png")));// 레이블에 이미지 불러옴
				}
			} else {

				if (functionList.check2ndPlace(winnerList, intList)) {
					makeLabel(number, findBtnList, winnerList);
					int bonusNum = Integer.parseInt(winnerList.get(6));
					if (number == bonusNum) {
						setIcon(new ImageIcon(MainPnl.class.getResource("/image/BonusBall.png")));
					}
				} else {
					makeLabel(number, findBtnList, winnerList);
				}
			}

			setHorizontalTextPosition(JLabel.CENTER); // 레이블의 텍스트 가로 부분을 가운데로 고정
			setVerticalTextPosition(JLabel.CENTER);// 레이블의 텍스트 세로 부분을 가운데로 고정
			setFont(new Font("Serif", Font.BOLD, 20)); // 폰트 설정
			setForeground(Color.WHITE); // 글자색깔 설정
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
			

			if (findBtnList.get(0) == 1) {
				if (sortWinnerList.contains(number)) {
					setIcon(new ImageIcon(MainPnl.class.getResource("/image/ballAutoCorrect.png")));// 레이블에 이미지
																									// 불러옴
				} else {
					setIcon(new ImageIcon(MainPnl.class.getResource("/image/ballAutoWrong.png")));// 레이블에 이미지
																									// 불러옴
				}
			} else if (findBtnList.get(0) == 2) {
				if (sortWinnerList.contains(number)) {
					setIcon(new ImageIcon(MainPnl.class.getResource("/image/ballSelfCorrect.png")));// 레이블에 이미지
																									// 불러옴
				} else {
					setIcon(new ImageIcon(MainPnl.class.getResource("/image/ballSelfWrong.png")));// 레이블에 이미지
																									// 불러옴
				}

			} else if (findBtnList.get(0) == 3) {
				List<Integer> semiAutoList = new ArrayList<>();

				for (int i = 1; i < findBtnList.size(); i++) {
					semiAutoList.add(findBtnList.get(i));
				}
				if (semiAutoList.contains(number) && sortWinnerList.contains(number)) {
					setIcon(new ImageIcon(MainPnl.class.getResource("/image/ballAutoCorrect.png")));// 레이블에 이미지
																									// 불러옴
				} else if (semiAutoList.contains(number) && !sortWinnerList.contains(number)) {
					setIcon(new ImageIcon(MainPnl.class.getResource("/image/ballAutoWrong.png")));// 레이블에 이미지
																									// 불러옴

				} else if (!semiAutoList.contains(number) && sortWinnerList.contains(number)) {
					setIcon(new ImageIcon(MainPnl.class.getResource("/image/ballSelfCorrect.png")));// 레이블에 이미지
																									// 불러옴
				} else if (!semiAutoList.contains(number) && !sortWinnerList.contains(number)) {
					setIcon(new ImageIcon(MainPnl.class.getResource("/image/ballSelfWrong.png")));// 레이블에 이미지
																									// 불러옴

				}
			}
		}
	}

	public DialogPnl(int lottoCount, int lottoPlayCount, JFrame mainPnl) {

		FunctionList functionList = new FunctionList();

		setModal(true); // 다이얼로그 창 닫기 전까지 다른 동작 불가

		CardLayout cardLayout = new CardLayout();

		JPanel centerPanel = new JPanel(); // 다이얼로그 창에서 모든 페이지가 다 포함되는 패널(밑바탕)
		centerPanel.setLayout(cardLayout);

// 로또 구매 창------------------------------------------------------------------------------------------------

		JPanel buyLottoPanel = new JPanel(); // 로또 구매 창 패널
		buyLottoPanel.setLayout(new BorderLayout());

		// 로또 구매 창 패널에 들어갈 요소들
		JLabel textLabel = new JLabel("인생역전 로또"); // "인생 역전 로또" 레이블
		buyLottoPanel.add(textLabel, new BorderLayout().NORTH);
		textLabel.setHorizontalAlignment(JLabel.RIGHT); // 북쪽에 위치한 레이블이 항상 맨 오른쪽에 위치

		JPanel westPanel = new JPanel(); // 창 벽과 패널의 사이를 띄우기 위한 패널(서쪽)
		buyLottoPanel.add(westPanel, new BorderLayout().WEST);
		westPanel.setPreferredSize(new Dimension(50, 500));

		JPanel eastPanel = new JPanel(); // 창 벽과 패널의 사이를 띄우기 위한 패널(동쪽)
		buyLottoPanel.add(eastPanel, new BorderLayout().EAST);
		eastPanel.setPreferredSize(new Dimension(50, 500));

		List<List<JCheckBox>> resultShow = new ArrayList<>(); // 결과 확인 창에서 쓰일 List(사용자가 선택한 번호를 담음)

		JPanel firstPageCenterPanel = new JPanel(); // 메인창에서 사용자가 선택한 수량에 따라 로또 1 ~ 5개 펼쳐질 패널(밑바탕)
		buyLottoPanel.add(firstPageCenterPanel, new BorderLayout().CENTER);

		List<List<Integer>> findBtnList = new ArrayList<>();

		// 메인 창에서 사용자가 선택한 로또 개수대로 번호 선택 패널 나타냄
		for (int i = 0; i < Integer.valueOf(lottoCount); i++) {

			firstPageCenterPanel.setLayout(new GridLayout(0, lottoCount, 50, 10)); // GridLayout

			JPanel includeNumChoicePanel = new JPanel(); // 번호 선택 패널, 자동 수동 반자동 버튼들이 들어있는 패널이 포함될 패널
			includeNumChoicePanel.setLayout(new BorderLayout());

			JPanel includeButtonsPanel = new JPanel(); // 자동 수동 반자동 버튼들 포함될 패널

			JPanel numChoicePanel = new JPanel(); // 번호 선택 패널
			numChoicePanel.setLayout(new GridLayout(0, 5, 10, 10)); // GridLayout

			List<JCheckBox> checkNumList = new ArrayList<>(); // 번호 선택 체크박스를 담은 List

			// 사용자가 선택할 번호 체크박스 (1 ~ 45)
			for (int j = 1; j <= 45; j++) {
				JCheckBox checkNumBox = new JCheckBox(String.valueOf(j));
				checkNumBox.setEnabled(false);
				checkNumList.add(checkNumBox); // 번호 선택 체크박스를 List에 담고
				numChoicePanel.add(checkNumBox); // 번호 선택 체크박스를 패널에도 담고
			}
			resultShow.add(checkNumList); // 사용자가 선택한 번호를 담는 List에 사용자가 선택한 번호 체크박스를 담는 List를 add

			JButton autoButton = new JButton("자동"); // 자동 버튼
			JButton selfButton = new JButton("수동"); // 수동 버튼
			JButton halfAutoButton = new JButton("반자동"); // 반자동 버튼

			includeButtonsPanel.add(autoButton);
			includeButtonsPanel.add(selfButton);
			includeButtonsPanel.add(halfAutoButton);

			includeNumChoicePanel.add(includeButtonsPanel, "North");

			includeNumChoicePanel.add(numChoicePanel, "Center");

			firstPageCenterPanel.add(includeNumChoicePanel);

			List<Integer> findBtn = new ArrayList<>();
			findBtnList.add(findBtn);

			functionList.autoOrSemiAutoBtnFuntion(autoButton, checkNumList, "auto", findBtn);
			functionList.autoOrSemiAutoBtnFuntion(selfButton, checkNumList, "self", findBtn);
			functionList.autoOrSemiAutoBtnFuntion(halfAutoButton, checkNumList, "semiAuto", findBtn);

			functionList.checkLimit(checkNumList);

			firstPageCenterPanel.revalidate(); // 레이아웃을 다시 계산
			firstPageCenterPanel.repaint(); // 바뀐 사항 새로 그려 줌
		}

		JPanel includeSendButtonPanel = new JPanel(); // 번호 제출 버튼이 포함될 패널
		buyLottoPanel.add(includeSendButtonPanel, new BorderLayout().SOUTH);

		JButton sendButton = new JButton("번호 제출"); // 번호 제출 버튼
		includeSendButtonPanel.add(sendButton);

// 당첨 숫자 확인 창------------------------------------------------------------------------------------------------

		List<String> result = functionList.resultLottoNumber(); // 당첨 번호를 넣을 List
		
		JPanel numberCheckPanel = new JPanel(); // 당첨 숫자 확인 창 패널
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

		JLabel loadingLabel = new JLabel("결과 추첨 중...");
		numberCheckBtnPanel.add(loadingLabel);

		JButton resultCheck = new JButton("결과 확인");
		numberCheckBtnPanel.add(resultCheck);

		BallLabel winNumLabel1 = new BallLabel(Integer.parseInt(result.get(0)), null, null, null);

		BallLabel winNumLabel2 = new BallLabel(Integer.parseInt(result.get(1)), null, null, null);

		BallLabel winNumLabel3 = new BallLabel(Integer.parseInt(result.get(2)), null, null, null);

		BallLabel winNumLabel4 = new BallLabel(Integer.parseInt(result.get(3)), null, null, null);

		BallLabel winNumLabel5 = new BallLabel(Integer.parseInt(result.get(4)), null, null, null);

		BallLabel winNumLabel6 = new BallLabel(Integer.parseInt(result.get(5)), null, null, null);

		JLabel plusLabel = new JLabel();
		plusLabel.setIcon(new ImageIcon(MainPnl.class.getResource("/image/plus.png")));// 레이블에 이미지 불러옴

		BallLabel bonusNumLabel = new BallLabel(Integer.parseInt(result.get(6)), null, null, null);

		getContentPane().add(centerPanel);
		numberCheckBallPanel.add(winNumLabel1);
		numberCheckBallPanel.add(winNumLabel2);
		numberCheckBallPanel.add(winNumLabel3);
		numberCheckBallPanel.add(winNumLabel4);
		numberCheckBallPanel.add(winNumLabel5);
		numberCheckBallPanel.add(winNumLabel6);
		numberCheckBallPanel.add(plusLabel);
		numberCheckBallPanel.add(bonusNumLabel);

		resultCheck.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(centerPanel, "ResultCheck");
			}
		});

// 결과 확인창------------------------------------------------------------------------------------------------

		JPanel resultCheckPanel = new JPanel(); // 결과 확인 창 패널
		resultCheckPanel.setLayout(new BorderLayout());

		// 결과 확인 패널에 들어갈 요소들
		// 값을 받아와서 넣어야 하는 레이블은 파라미터를 비워놨음
		JLabel text2Label = new JLabel("인생역전 로또 제 " + lottoPlayCount + "회 결과");
		resultCheckPanel.add(text2Label, new BorderLayout().NORTH);
		text2Label.setHorizontalAlignment(JLabel.CENTER); // 북쪽에 위치한 레이블이 항상 중간에 위치

		JPanel thirdPageCenterPanel = new JPanel(); // winNumPanel, includeLabelsPanel이 포함될 패널
		thirdPageCenterPanel.setLayout(new GridLayout(6, 1));
		resultCheckPanel.add(thirdPageCenterPanel);

		JPanel winNumPanel = new JPanel(); // 당첨 숫자 레이블들 포함될 패널
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

		JPanel includeButtonsPanel2 = new JPanel(); // 버튼들 포함될 패널
		resultCheckPanel.add(includeButtonsPanel2, new BorderLayout().SOUTH);

		againButton = new JButton("다시하기"); // 다시하기 버튼
		JButton closeButton = new JButton("종료"); // 종료 버튼

		includeButtonsPanel2.add(againButton);
		includeButtonsPanel2.add(closeButton);

// ------------------------------------------------------------------------------------------------

		centerPanel.add(buyLottoPanel, "BuyLotto");
		centerPanel.add(numberCheckPanel, "NumberCheck");
		centerPanel.add(resultCheckPanel, "ResultCheck");

		getContentPane().add(centerPanel); // 패널에 컴포넌트들을 붙임

		// 번호 제출 버튼을 눌렀을 때
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				if (functionList.checkAllSelected(resultShow)) {

					setSize(650, 550);

					cardLayout.show(centerPanel, "NumberCheck"); // 번호 제출 버튼을 누르면 당첨 숫자 확인 창으로 넘어감

					//
					functionList.changeToLabelVisible(loadingLabel, winNumLabel1, winNumLabel2, winNumLabel3,
							winNumLabel4, winNumLabel5, winNumLabel6, plusLabel, bonusNumLabel, resultCheck);

					// 사용자가 메인창에서 선택한 로또 개수에 따라 includeLabelsPanel이 보여짐(1 ~ 5개)
					for (int i = 0; i < lottoCount; i++) {

						JPanel includeLabelsPanel = new JPanel(); // 로또 개수, 사용자 선택 번호, 당첨 결과 레이블들 포함될 패널
						includeLabelsPanel.setLayout(new FlowLayout());
						thirdPageCenterPanel.add(includeLabelsPanel);

						List<Integer> intList = functionList.returnCheckBoxListToIntegerList(resultShow.get(i));
						Collections.sort(intList);

						JLabel countLabel = new JLabel(String.valueOf(i + 1)); // 로또 개수 표시 레이블 (1, 2, 3, 4, 5)
						includeLabelsPanel.add(countLabel);

						// 사용자가 로또 당 선택한 자동 수동 반자동 표시 레이블
						if (findBtnList.get(i).get(0) == 1) {
							JLabel autoOrSemiautoLabel = new JLabel("자동");
							includeLabelsPanel.add(autoOrSemiautoLabel);

						} else if (findBtnList.get(i).get(0) == 2) {
							JLabel autoOrSemiautoLabel = new JLabel("수동");
							includeLabelsPanel.add(autoOrSemiautoLabel);

						} else {
							JLabel autoOrSemiautoLabel = new JLabel("반자동");
							includeLabelsPanel.add(autoOrSemiautoLabel);
						}
						for (int j = 0; j < intList.size(); j++) {

							BallLabel resultCheckLable = new BallLabel(intList.get(j), findBtnList.get(i), result,
									intList);
							includeLabelsPanel.add(resultCheckLable);

						}

						JLabel winLabel = new JLabel(); // n등 당첨, 낙첨 레이블
						includeLabelsPanel.add(winLabel);

						functionList.setLabelTextToResult(winLabel, intList, result); // winLabel에 글자를 바꿔줄 메소드

						thirdPageCenterPanel.revalidate(); // 레이아웃을 다시 계산
						thirdPageCenterPanel.repaint(); // 바뀐 사항을 다시 그려줌
					}

				} else {
					JOptionPane.showMessageDialog(DialogPnl.this, "각 로또 당 숫자를 6개 선택해주세요.");
				}
			}
		});

		// 종료 버튼 누르면 다이얼로그 창, 기본 창 같이 닫힘
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0); // 기본 창 닫힘
			}
		});

		pack(); // 다이얼로그 창 크기 알아서 조절 되도록
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	// 필드로 만들어둔 AgainButton의 getter setter
	public JButton getAgainButton() {
		return againButton;
	}

	public void setAgainButton(JButton againButton) {
		this.againButton = againButton;
	}
}