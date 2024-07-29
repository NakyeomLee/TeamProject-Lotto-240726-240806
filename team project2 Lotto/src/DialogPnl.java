import java.awt.BorderLayout;
import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.GridLayout;

// 작업자 : 이나겸
// 기본 구성요소 : 로또번호를 정하는 창, 로또의 결과값이 나오는 창, 결과를 출력하는 창
// 로또번호를 정하는 창은 메인에서 1 ~ 5사이의 값을 받아와서 해당 개수만큼 구현해야 함.

public class DialogPnl extends JDialog {
	public DialogPnl(int lottoCount, JFrame mainPnl) {

		FunctionList functionList = new FunctionList();
		List<String> result = functionList.resultLottoNumber();

		setModal(true); // 다이얼로그 창 닫기 전까지 다른 동작 불가

		CardLayout cardLayout = new CardLayout();
//		BorderLayout borderLayout = new BorderLayout();

		JPanel centerPanel = new JPanel(); // 모든 패널이 다 포함되는 패널(밑바탕)
		centerPanel.setLayout(cardLayout);

		JPanel buyLottoPanel = new JPanel(); // 로또 구매 창 패널
//		buyLottoPanel.setLayout(borderLayout);

		JPanel numberCheckPanel = new JPanel(); // 당첨 숫자 확인 창 패널
//		numberCheckPanel.setLayout(null);

		JPanel resultCheckPanel = new JPanel(); // 결과 확인 창 패널
//		resultCheckPanel.setLayout(null);

		// 로또 구매 창 패널에 들어갈 요소들
		JLabel textLabel = new JLabel("인생역전 로또"); // 인생 역전 로또 레이블
		buyLottoPanel.add(textLabel);

		for (int i = 0; i < Integer.valueOf(lottoCount); i++) {
			JPanel includeNumChoicePanel = new JPanel(); // 번호 선택 패널, 자동 수동 반자동 버튼들이 들어있는 패널이 포함될 패널
//			includeNumChoicePanel.setLayout(null);

			JPanel includeButtonsPanel = new JPanel(); // 자동 수동 반자동 버튼들 포함될 패널

			JPanel numChoicePanel = new JPanel(); // 번호 선택 패널
			numChoicePanel.setLayout(new GridLayout(0, 5, 5, 5)); // GridLayout

			// 사용자가 선택할 번호 체크박스 (1 ~ 45)
			List<JCheckBox> checkNumList = new ArrayList<>();

			for (int j = 1; j <= 45; j++) {
				JCheckBox checkNumBox = new JCheckBox(String.valueOf(j));
				checkNumList.add(checkNumBox);
				numChoicePanel.add(checkNumBox);
			}

			JButton outoButton = new JButton("자동"); // 자동 버튼
			JButton selfButton = new JButton("수동"); // 수동 버튼
			JButton halfOutoButton = new JButton("반자동"); // 반자동 버튼

			// 로또 구매 창 패널에 요소들 add
			buyLottoPanel.add(includeNumChoicePanel);
			includeNumChoicePanel.add(includeButtonsPanel);
			includeNumChoicePanel.add(numChoicePanel);
			includeButtonsPanel.add(outoButton);
			includeButtonsPanel.add(selfButton);
			includeButtonsPanel.add(halfOutoButton);
		}

		JPanel includeSendButtonPanel = new JPanel(); // 번호 제출 버튼이 포함될 패널

		JButton sendButton = new JButton("번호 제출"); // 번호 제출 버튼

		buyLottoPanel.add(includeSendButtonPanel);
		includeSendButtonPanel.add(sendButton);

		// 당첨 숫자 확인 창 패널에 들어갈 요소들
		JLabel loadingLabel = new JLabel("결과 추첨 중..."); // 결과 추첨 중 레이블
		JLabel winNumLabel1 = new JLabel(result.get(0)); // 첫번째 당첨 번호 레이블
		JLabel winNumLabel2 = new JLabel(result.get(1)); // 두번째 당첨 번호 레이블
		JLabel winNumLabel3 = new JLabel(result.get(2)); // 세번째 당첨 번호 레이블
		JLabel winNumLabel4 = new JLabel(result.get(3)); // 네번째 당첨 번호 레이블
		JLabel winNumLabel5 = new JLabel(result.get(4)); // 다섯번째 당첨 번호 레이블
		JLabel winNumLabel6 = new JLabel(result.get(5)); // 여섯번째 당첨 번호 레이블
		JLabel bonusNumLabel = new JLabel(result.get(6)); // 보너스 번호 레이블

		// 결과 확인 패널에 들어갈 요소들
		// 값을 받아와서 넣어야 하는 레이블은 파라미터를 비워놨음
		JLabel text2Label = new JLabel("인생역전 로또 제 n회 결과"); // n이 다시하기 버튼을 누를 때마다 바뀌어야함
//		JLabel text2Label = new JLabel("인생역전 로또 제 " + n + "회 결과"); // 나중에 n을 바꿔줌(n을 count로 받아서)
		JLabel countLabel = new JLabel(); // 로또 개수 표시 레이블 (1, 2, 3, 4, 5)
		JLabel choiceNumLabel1 = new JLabel(); // 사용자가 선택한 첫번째 번호 레이블
		JLabel choiceNumLabel2 = new JLabel(); // 사용자가 선택한 두번째 번호 레이블
		JLabel choiceNumLabel3 = new JLabel(); // 사용자가 선택한 세번째 번호 레이블
		JLabel choiceNumLabel4 = new JLabel(); // 사용자가 선택한 네번째 번호 레이블
		JLabel choiceNumLabel5 = new JLabel(); // 사용자가 선택한 다섯번째 번호 레이블
		JLabel choiceNumLabel6 = new JLabel(); // 사용자가 선택한 여섯번째 번호 레이블
		JLabel fallLabel = new JLabel("낙첨"); // 낙첨 레이블
		JLabel winLabel = new JLabel("n등 당첨"); // n등 당첨 레이블, n은 바뀔 수 있게
//		JLabel winLabel = new JLabel(n + "등 당첨"); // 나중에 n을 바꿔줌(n을 count로 받아서)

		JButton againButton = new JButton("다시하기"); // 다시하기 버튼
		JButton closeButton = new JButton("종료"); // 종료 버튼

		// 당첨 숫자 확인 창 패널에 요소들 add
		numberCheckPanel.add(loadingLabel);
		numberCheckPanel.add(winNumLabel1);
		numberCheckPanel.add(winNumLabel2);
		numberCheckPanel.add(winNumLabel3);
		numberCheckPanel.add(winNumLabel4);
		numberCheckPanel.add(winNumLabel5);
		numberCheckPanel.add(winNumLabel6);
		numberCheckPanel.add(bonusNumLabel);

		// 결과 확인 패널에 들어갈 요소들 add
		resultCheckPanel.add(text2Label);
		resultCheckPanel.add(countLabel);
		resultCheckPanel.add(choiceNumLabel1);
		resultCheckPanel.add(choiceNumLabel2);
		resultCheckPanel.add(choiceNumLabel3);
		resultCheckPanel.add(choiceNumLabel4);
		resultCheckPanel.add(choiceNumLabel5);
		resultCheckPanel.add(choiceNumLabel6);
		resultCheckPanel.add(fallLabel);
		resultCheckPanel.add(againButton);
		resultCheckPanel.add(closeButton);

		centerPanel.add(buyLottoPanel, "BuyLotto");
		centerPanel.add(numberCheckPanel, "NumberCheck");
		centerPanel.add(resultCheckPanel, "ResultCheck");

		getContentPane().add(centerPanel); // 패널에 컴포넌트들을 붙임

		// 번호 제출 버튼 누르면 당첨 번호 확인 패널로 넘어감
		sendButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(centerPanel, "NumberCheck");

				functionList.changeToLabelVisible(winNumLabel1, winNumLabel2, winNumLabel3, winNumLabel4, winNumLabel5,
						winNumLabel6, bonusNumLabel);
			}
		});

		// 다시하기 버튼 누르면 다이얼로그 창 닫히고 메인 창(기본 창)으로 넘어감
		againButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		// 종료 버튼 누르면 다이얼로그 창 닫힘
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		pack();
		setSize(500, 500);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}
}