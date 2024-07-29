import java.awt.CardLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

// 작업자 : 이나겸
// 기본 구성요소 : 로또번호를 정하는 창, 로또의 결과값이 나오는 창, 결과를 출력하는 창
// 로또번호를 정하는 창은 메인에서 1 ~ 5사이의 값을 받아와서 해당 개수만큼 구현해야 함.

public class DialogPnl extends JDialog{
	public DialogPnl(int lottoCount, JFrame mainPnl) {
		
		FunctionList functionList = new FunctionList();

		setModal(true); // 다이얼로그 창 닫기 전까지 다른 동작 불가
		
		CardLayout cardLayout = new CardLayout();

		JPanel pnlCenter = new JPanel(); // 모든 패널이 다 포함되는 패널(토대, 밑바탕)
		pnlCenter.setLayout(cardLayout); // 토대가 되는 패널은 card Layout
		JPanel pnlBuyLotto = new JPanel(); // 로또 구매 창 패널
		pnlBuyLotto.setLayout(null); // 로또 구매 창 패널은 Absolute Layout
		JPanel pnlNumberCheck = new JPanel(); // 당첨 숫자 확인 창 패널
		pnlNumberCheck.setLayout(null); // 당첨 숫자 확인 패널은 Absolute Layout
		JPanel pnlResultCheck = new JPanel(); // 결과 확인 창 패널
		pnlResultCheck.setLayout(null); // 결과 확인 패널은 Absolute Layout

		// 로또 구매 창 패널에 들어갈 요소들
		JLabel lblText = new JLabel("인생역전 로또"); // 인생 역전 로또 레이블
		lblText.setLocation(318, 15);
		lblText.setSize(143, 50);

		JPanel pnlInclude = new JPanel(); // 번호 선택 패널, 자동 수동 반자동 버튼들 포함될 패널
		pnlInclude.setLocation(96, 77);
		pnlInclude.setSize(261, 287);
		pnlInclude.setLayout(null); // Absolute Layout

		JPanel pnlNumberChoice = new JPanel(); // 번호 선택 패널 (메인 창에서 선택한 숫자에 따라 패널 개수가 늘어나야함)
		pnlNumberChoice.setLocation(17, 74);
		pnlNumberChoice.setSize(227, 198);
		pnlNumberChoice.setLayout(null); // 번호 선택 패널은 일단 Absolute Layout인데 추후에 변경될 수 있음

		JButton btnOuto = new JButton("자동"); // 자동 버튼
		btnOuto.setFont(new Font("굴림", Font.PLAIN, 12));
		btnOuto.setLocation(17, 28);
		btnOuto.setSize(66, 31);
		JButton btnSelf = new JButton("수동"); // 수동 버튼
		btnSelf.setFont(new Font("굴림", Font.PLAIN, 12));
		btnSelf.setLocation(89, 28);
		btnSelf.setSize(66, 31);
		JButton btnHalfOuto = new JButton("반자동"); // 반자동 버튼
		btnHalfOuto.setFont(new Font("굴림", Font.PLAIN, 12));
		btnHalfOuto.setLocation(167, 28);
		btnHalfOuto.setSize(77, 31);

		JButton btnNumberSend = new JButton("번호 제출"); // 번호 제출 버튼
		btnNumberSend.setLocation(156, 379);
		btnNumberSend.setSize(158, 50);

		// 당첨 숫자 확인 창 패널에 들어갈 요소들
		// 당첨 번호 레이블은 나중에 로또 구매 창 패널에서 받아온 값으로 변경
		JLabel lblLoading = new JLabel("결과 추첨 중..."); // 결과 추첨 중 레이블
		lblLoading.setLocation(72, 345);
		lblLoading.setSize(194, 50);
		JLabel lblWinNum1 = new JLabel("1"); // 첫번째 당첨 번호 레이블
		lblWinNum1.setLocation(17, 287);
		lblWinNum1.setSize(40, 40);
		JLabel lblWinNum2 = new JLabel("2"); // 두번째 당첨 번호 레이블
		lblWinNum2.setLocation(72, 287);
		lblWinNum2.setSize(40, 40);
		JLabel lblWinNum3 = new JLabel("3"); // 세번째 당첨 번호 레이블
		lblWinNum3.setLocation(129, 287);
		lblWinNum3.setSize(40, 40);
		JLabel lblWinNum4 = new JLabel("4"); // 네번째 당첨 번호 레이블
		lblWinNum4.setLocation(194, 287);
		lblWinNum4.setSize(40, 40);
		JLabel lblWinNum5 = new JLabel("5"); // 다섯번째 당첨 번호 레이블
		lblWinNum5.setLocation(263, 287);
		lblWinNum5.setSize(40, 40);
		JLabel lblWinNum6 = new JLabel("6"); // 여섯번째 당첨 번호 레이블
		lblWinNum6.setLocation(320, 287);
		lblWinNum6.setSize(40, 40);
		JLabel lblBonusNum = new JLabel("7"); // 보너스 번호 레이블
		lblBonusNum.setLocation(408, 287);
		lblBonusNum.setSize(40, 40);

		// 결과 확인 버튼은 카드레이아웃 확인때문에 임의로 넣어놨는데 없애도 됨
		JButton btnResult = new JButton("결과 확인"); // 결과 확인 버튼
		btnResult.setFont(new Font("굴림", Font.PLAIN, 15));
		btnResult.setLocation(320, 352);
		btnResult.setSize(99, 40);

		// 결과 확인 패널에 들어갈 요소들
		// 값을 받아와서 넣어야 하는 레이블은 파라미터를 비워놨음
		JLabel lblText2 = new JLabel("인생역전 로또 제 n회 결과"); // n이 다시하기 버튼을 누를때마다 바뀌어야함
//		JLabel lblText2 = new JLabel("인생역전 로또 제 " + n + "회 결과"); // 나중에 n을 바꿔줌(n을 count로 받아서)
		JLabel lblCount = new JLabel(); // 로또 개수 표시 레이블 (1, 2, 3, 4, 5)
		JLabel lblChoiceNum1 = new JLabel(); // 사용자가 선택한 첫번째 번호 레이블
		JLabel lblChoiceNum2 = new JLabel(); // 사용자가 선택한 두번째 번호 레이블
		JLabel lblChoiceNum3 = new JLabel(); // 사용자가 선택한 세번째 번호 레이블
		JLabel lblChoiceNum4 = new JLabel(); // 사용자가 선택한 네번째 번호 레이블
		JLabel lblChoiceNum5 = new JLabel(); // 사용자가 선택한 다섯번째 번호 레이블
		JLabel lblChoiceNum6 = new JLabel(); // 사용자가 선택한 여섯번째 번호 레이블
		JLabel lblFall = new JLabel("낙첨"); // 낙첨 레이블
		JLabel lblWin = new JLabel("n등 당첨"); // n등 당첨 레이블, n은 바뀔 수 있게
//		JLabel lblWin = new JLabel(n + "등 당첨"); // 나중에 n을 바꿔줌(n을 count로 받아서)

		JButton btnAgain = new JButton("다시하기"); // 다시하기 버튼
		btnAgain.setLocation(17, 353);
		btnAgain.setSize(172, 50);
		JButton btnClose = new JButton("종료"); // 종료 버튼
		btnClose.setLocation(274, 353);
		btnClose.setSize(172, 50);

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
		pnlNumberCheck.add(btnResult);

		// 결과 확인 패널에 들어갈 요소들
		pnlResultCheck.add(lblText2);
		pnlResultCheck.add(lblCount);
		pnlResultCheck.add(lblChoiceNum1);
		pnlResultCheck.add(lblChoiceNum2);
		pnlResultCheck.add(lblChoiceNum3);
		pnlResultCheck.add(lblChoiceNum4);
		pnlResultCheck.add(lblChoiceNum5);
		pnlResultCheck.add(lblChoiceNum6);
		pnlResultCheck.add(lblFall);
		pnlResultCheck.add(lblWin);
		pnlResultCheck.add(btnAgain);
		pnlResultCheck.add(btnClose);

		pnlCenter.add(pnlBuyLotto, "BuyLotto");
		pnlCenter.add(pnlNumberCheck, "NumberCheck");
		pnlCenter.add(pnlResultCheck, "ResultCheck");

		pack(); // 다이얼로그 창 크기가 알아서 조절되게

		getContentPane().add(pnlCenter); // 패널에 컴포넌트들을 붙임

		// 번호 제출 버튼 누르면 당첨 번호 확인 패널로 넘어감
		btnNumberSend.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pnlCenter, "NumberCheck");
			}
		});

		// 다시하기 버튼 누르면 메인 창(기본 창)으로 넘어감
		btnAgain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// 다이얼로그 창 닫고 기본창(메인창)이 보여지도록

			}
		});

		// 종료 버튼 누르면 다이얼로그창, 기본 창 다 종료됨
		btnClose.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// 다이얼로그 창과 기본창이 한 번에 닫아질 수 있도록
				// 아니면 다이얼로그 창만 닫아질 수 있도록
				dispose();
			}
		});

		// 결과 확인 버튼 누르면 결과 확인 패널로 넘어감
		// 이 버튼은 필요없으면 나중에 없애면 됨
		btnResult.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(pnlCenter, "ResultCheck");
			}
		});

		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

//	public static void main(String[] args) {
//		new DialogPnl(int lottoCount, JFrame mainPnl).setVisible(true);
//	}
}