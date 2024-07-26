import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

// 메인 창
// 작업자 : 이재민
// 기본 구성요소 : 콤보박스, 텍스트, DialogPnl을 호출하는 버튼
public class MainPnl extends JFrame {
	private int lottoPlayCount = 1;
	
	
	public MainPnl() {
		super("인생역전 로또");
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setResizable(false);

		JPanel centerPanel = new JPanel();
		getContentPane().add(centerPanel);

		
//		JLabel lblNewLabel_1 = new JLabel("New label");
//		centerPanel.add(lblNewLabel_1);
		
		centerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		centerPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblPicture = new JLabel("");
		lblPicture.setIcon(new ImageIcon(MainPnl.class.getResource("/image/background.jpg")));
		lblPicture.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblPicture, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		centerPanel.add(panel_1, BorderLayout.NORTH);
		
		JLabel lblLottoCount = new JLabel("로또 수량을 선택하세요. ");
		panel_1.add(lblLottoCount);
		
		
//		String[] items = { "로또 수량을 선택하세요.", "1", "2", "3", "4", "5" };
//		JComboBox combo = new JComboBox();
//		panel_1.add(combo);

		String[] items = { "로또 수량을 선택하세요.", "1", "2", "3", "4", "5" };
		JComboBox<String> combo = new JComboBox<>(items);
		panel_1.add(combo);

		JPanel southPanel = new JPanel();
		getContentPane().add(southPanel, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("로또 사러 가기!");
		southPanel.add(btnNewButton);

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (combo.getSelectedIndex() != 0) {
					String lottoCount = combo.getItemAt(combo.getSelectedIndex());
					int lottoCountInteger = Integer.parseInt(lottoCount);
					System.out.println(lottoCountInteger);
					DialogPnl dialogPnl = new DialogPnl();
				}
			}
		});

		JPanel northPanel = new JPanel();
		getContentPane().add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("제 " + lottoPlayCount + "회 인생 역전 로또");
		lblNewLabel.setHorizontalAlignment(JLabel.CENTER);


		northPanel.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		northPanel.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("인생역전 로또!");
		lblNewLabel_1.setHorizontalAlignment(JLabel.RIGHT);
		panel_2.add(lblNewLabel_1);

	}

	public static void main(String[] args) {
		new MainPnl().setVisible(true);
	}
}
