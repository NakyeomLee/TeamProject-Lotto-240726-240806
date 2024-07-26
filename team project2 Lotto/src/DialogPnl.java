import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

// 작업자 : 이나겸
// 기본 구성요소 : 로또번호를 정하는 창, 로또의 결과값이 나오는 창, 결과를 출력하는 창
// 로또번호를 정하는 창은 메인에서 1 ~ 5사이의 값을 받아와서 해당 개수만큼 구현해야 함.

public class DialogPnl extends JFrame {
	private int lottoCount; // 메인 화면에서 선택할 로또 개수

	public DialogPnl() {

//		setModal(true); // 다이얼로그 창 닫기 전까지 다른 동작 불가

		CardLayout cardLayout = new CardLayout();

		System.out.println("실행");
		
		JPanel pnlCenter = new JPanel(); // 모든 패널이 다 포함되는 패널(토대, 밑바탕)
		pnlCenter.setLayout(cardLayout);
		JPanel pnlBuyLotto = new JPanel(); // 로또 구매 창 패널
		pnlBuyLotto.setLayout(null); // 로또 구매 창 패널은 Absolute Layout
		JPanel pnlNumberCheck = new JPanel(); // 당첨 숫자 확인 창 패널
		pnlNumberCheck.setLayout(null); // 당첨 숫자 확인 패널은 Absolute Layout
		JPanel pnlResultCheck = new JPanel(); // 결과 확인 창 패널
		pnlResultCheck.setLayout(null); // 결과 확인 패널은 Absolute Layout

		// 로또 구매 창 패널에 들어갈 요소들
		JLabel lblText = new JLabel("인생역전 로또"); // 인생 역전 로또 레이블
		
		JPanel pnlInclude = new JPanel(); // 번호 선택 패널, 자동수동반자동 버튼들 포함될 패널
		pnlInclude.setLayout(null); // Absolute Layout
		
		JPanel pnlNumberChoice = new JPanel(); // 번호 선택 패널 (메인 창에서 선택한 숫자에 따라 패널 개수가 늘어나야함)
		pnlNumberChoice.setLayout(null); // 번호 선택 패널은 일단 Absolute Layout인데 추후에 변경될 수 있음

		JButton btnOuto = new JButton(); // 자동 버튼
		JButton btnSelf = new JButton(); // 수동 버튼
		JButton btnHalfOuto = new JButton(); // 반자동 버튼
		
		JButton btnNumberSend = new JButton(); // 번호 제출 버튼

		// 당첨 숫자 확인 창 패널에 들어갈 요소들
		JLabel lblLoading = new JLabel("결과 추첨 중..."); // 결과 추첨 중 레이블
		JLabel lblWinNum1 = new JLabel(); // 첫번째 당첨 번호 레이블
		JLabel lblWinNum2 = new JLabel(); // 두번째 당첨 번호 레이블
		JLabel lblWinNum3 = new JLabel(); // 세번째 당첨 번호 레이블
		JLabel lblWinNum4 = new JLabel(); // 네번째 당첨 번호 레이블
		JLabel lblWinNum5 = new JLabel(); // 다섯번째 당첨 번호 레이블
		JLabel lblWinNum6 = new JLabel(); // 여섯번째 당첨 번호 레이블
		JLabel lblBonusNum = new JLabel(); // 보너스 번호 레이블

		// 결과 확인 패널에 들어갈 요소들
		// 값을 받아와서 넣어야 하는 레이블은 파라미터를 비워놨음
		JLabel lblText2 = new JLabel("인생역전 로또 제 n회 결과"); // n이 다시하기 버튼을 누를때마다 바뀌어야함
		JLabel lblCount = new JLabel(); // 로또 개수 표시 레이블 (1, 2, 3, 4, 5)
		JLabel lblChoiceNum1 = new JLabel(); // 사용자가 선택한 첫번째 번호 레이블
		JLabel lblChoiceNum2 = new JLabel(); // 사용자가 선택한 두번째 번호 레이블
		JLabel lblChoiceNum3 = new JLabel(); // 사용자가 선택한 세번째 번호 레이블
		JLabel lblChoiceNum4 = new JLabel(); // 사용자가 선택한 네번째 번호 레이블
		JLabel lblChoiceNum5 = new JLabel(); // 사용자가 선택한 다섯번째 번호 레이블
		JLabel lblChoiceNum6 = new JLabel(); // 사용자가 선택한 여섯번째 번호 레이블
		JLabel lblFall = new JLabel("낙첨"); // 낙첨 레이블
		JLabel lblWin = new JLabel(); // n등 당첨 레이블

		JButton btnAgain = new JButton(); // 다시하기 버튼
		JButton btnClose = new JButton(); // 종료 버튼

		// 로또 구매 창 패널에 요소들 add
		pnlBuyLotto.add(lblText);
		pnlBuyLotto.add(pnlInclude);
		pnlInclude.add(pnlNumberChoice);
		pnlInclude.add(btnOuto);
		pnlInclude.add(btnSelf);
		pnlInclude.add(btnHalfOuto);
		pnlBuyLotto.add(btnNumberSend);

		// 당첨 숫자 확인 창 패널에 요소들 add
		pnlNumberCheck.add(lblLoading);
		pnlNumberCheck.add(lblWinNum1);
		pnlNumberCheck.add(lblWinNum2);
		pnlNumberCheck.add(lblWinNum3);
		pnlNumberCheck.add(lblWinNum4);
		pnlNumberCheck.add(lblWinNum5);
		pnlNumberCheck.add(lblWinNum6);
		pnlNumberCheck.add(lblBonusNum);

		// 결과 확인 패널에 들어갈 요소들
		pnlNumberChoice.add(lblText2);
		pnlNumberChoice.add(lblCount);
		pnlNumberChoice.add(lblChoiceNum1);
		pnlNumberChoice.add(lblChoiceNum2);
		pnlNumberChoice.add(lblChoiceNum3);
		pnlNumberChoice.add(lblChoiceNum4);
		pnlNumberChoice.add(lblChoiceNum5);
		pnlNumberChoice.add(lblChoiceNum6);
		pnlNumberChoice.add(lblFall);
		pnlNumberChoice.add(lblWin);
		pnlNumberChoice.add(btnAgain);
		pnlNumberChoice.add(btnClose);
		
		pnlCenter.add(pnlBuyLotto);
		pnlCenter.add(pnlNumberCheck);
		pnlCenter.add(pnlResultCheck);
		
		pack(); // 다이얼로그 창 크기가 알아서 조절되게
		
		getContentPane().add(pnlCenter); // 패널에 컴포넌트들을 붙임
		
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new DialogPnl().setVisible(true);
	}
}