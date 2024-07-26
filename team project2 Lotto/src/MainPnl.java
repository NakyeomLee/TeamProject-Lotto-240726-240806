import javax.swing.JFrame;
// 메인 창
// 작업자 : 이재민
// 기본 구성요소 : 콤보박스, 텍스트, DialogPnl을 호출하는 버튼
public class MainPnl extends JFrame {
	
	public MainPnl() {
		
		
		setSize(700, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		new MainPnl().setVisible(true);
	}
}
