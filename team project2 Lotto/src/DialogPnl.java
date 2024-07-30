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

		public BallLabel(int number) {
			if (ballCount == 6) {
				setIcon(new ImageIcon(MainPnl.class.getResource("/image/BonusBall.png")));// 레이블에 이미지 불러옴
			} else {
				setIcon(new ImageIcon(MainPnl.class.getResource("/image/ball3.png")));// 레이블에 이미지 불러옴
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

			functionList.autoOrSemiAutoBtnFuntion(autoButton, checkNumList, "auto");
			functionList.autoOrSemiAutoBtnFuntion(selfButton, checkNumList, "self");
			functionList.autoOrSemiAutoBtnFuntion(halfAutoButton, checkNumList, "semiAuto");
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

		JLabel lblNewLabel = new JLabel("New label");
		numberCheckCenterPanel.add(lblNewLabel);

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

		BallLabel winNumLabel1 = new BallLabel(Integer.parseInt(result.get(0)));

		BallLabel winNumLabel2 = new BallLabel(Integer.parseInt(result.get(1)));

		BallLabel winNumLabel3 = new BallLabel(Integer.parseInt(result.get(2)));

		BallLabel winNumLabel4 = new BallLabel(Integer.parseInt(result.get(3)));

		BallLabel winNumLabel5 = new BallLabel(Integer.parseInt(result.get(4)));

		BallLabel winNumLabel6 = new BallLabel(Integer.parseInt(result.get(5)));

		BallLabel bonusNumLabel = new BallLabel(Integer.parseInt(result.get(6)));

		getContentPane().add(centerPanel);
		numberCheckBallPanel.add(winNumLabel1);
		numberCheckBallPanel.add(winNumLabel2);
		numberCheckBallPanel.add(winNumLabel3);
		numberCheckBallPanel.add(winNumLabel4);
		numberCheckBallPanel.add(winNumLabel5);
		numberCheckBallPanel.add(winNumLabel6);
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

		JLabel winNum1 = new JLabel(result.get(0)); // 첫번째 당첨 번호 레이블

		JLabel winNum2 = new JLabel(result.get(1)); // 두번째 당첨 번호 레이블

		JLabel winNum3 = new JLabel(result.get(2)); // 세번째 당첨 번호 레이블

		JLabel winNum4 = new JLabel(result.get(3)); // 네번째 당첨 번호 레이블

		JLabel winNum5 = new JLabel(result.get(4)); // 다섯번째 당첨 번호 레이블

		JLabel winNum6 = new JLabel(result.get(5)); // 여섯번째 당첨 번호 레이블

		JLabel bonusNum = new JLabel(result.get(6)); // 보너스 번호 레이블

		winNumPanel.add(winNum1);
		winNumPanel.add(winNum2);
		winNumPanel.add(winNum3);
		winNumPanel.add(winNum4);
		winNumPanel.add(winNum5);
		winNumPanel.add(winNum6);
		winNumPanel.add(bonusNum);

		JPanel includeButtonsPanel2 = new JPanel(); // 버튼들 포함될 패널
		resultCheckPanel.add(includeButtonsPanel2, new BorderLayout().SOUTH);

		againButton = new JButton("다시하기");
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

				cardLayout.show(centerPanel, "NumberCheck"); // 번호 제출 버튼을 누르면 당첨 숫자 확인 창으로 넘어감

				//
				functionList.changeToLabelVisible(loadingLabel, winNumLabel1, winNumLabel2, winNumLabel3, winNumLabel4,
						winNumLabel5, winNumLabel6, bonusNumLabel, resultCheck);

				// 사용자가 메인창에서 선택한 로또 개수에 따라 includeLabelsPanel이 보여짐(1 ~ 5개)
				for (int i = 0; i < lottoCount; i++) {

					JPanel includeLabelsPanel = new JPanel(); // 로또 개수, 사용자 선택 번호, 당첨 결과 레이블들 포함될 패널
					includeLabelsPanel.setLayout(new FlowLayout());
					thirdPageCenterPanel.add(includeLabelsPanel);

					List<Integer> intList = functionList.returnCheckBoxListToIntegerList(resultShow.get(i));
					Collections.sort(intList);

					JLabel countLabel = new JLabel(String.valueOf(i + 1)); // 로또 개수 표시 레이블 (1, 2, 3, 4, 5)
					includeLabelsPanel.add(countLabel);

					// 각 로또 별 사용자가 선택한 번호를 보여줄 레이블
					for (int j = 0; j < intList.size(); j++) {
						JLabel resultCheckLable = new JLabel(String.valueOf(intList.get(j)));
						includeLabelsPanel.add(resultCheckLable);
					}

					JLabel winLabel = new JLabel(); // n등 당첨, 낙첨 레이블
					includeLabelsPanel.add(winLabel);

					functionList.setLabelTextToResult(winLabel, intList, result); // winLabel에 글자를 바꿔줄 메소드

					thirdPageCenterPanel.revalidate(); // 레이아웃을 다시 계산
					thirdPageCenterPanel.repaint(); // 바뀐 사항을 다시 그려줌
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

	public JButton getAgainButton() {
		return againButton;
	}

	public void setAgainButton(JButton againButton) {
		this.againButton = againButton;
	}
}