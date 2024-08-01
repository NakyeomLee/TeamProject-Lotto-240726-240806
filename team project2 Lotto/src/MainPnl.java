import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

// 메인 창
// 작업자 : 이재민
// 기본 구성요소 : 콤보박스, 텍스트, DialogPnl을 호출하는 버튼


public class MainPnl extends JFrame {
	private int lottoPlayCount = 1;
	private FontHolder fontHolder = new FontHolder(); // 폰트 활용을 위한 class 참조
	private List<List<List<JCheckBox>>> saveCheckBox = new ArrayList<>();
	
	public MainPnl() {
		super("인생 역전 로또");
		
		setSize(500, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		setResizable(false);

		JPanel centerPanel = new JPanel();
		getContentPane().add(centerPanel);

		centerPanel.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		centerPanel.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));

		JLayeredPane layeredPane = new JLayeredPane();

		JLabel gifLabel = new JLabel("");

		ImageIcon gifIcon = new ImageIcon(MainPnl.class.getResource("/image/background5.gif"));
		gifLabel.setIcon(gifIcon);
		gifLabel.setBounds(0, 0, gifIcon.getIconWidth(), gifIcon.getIconHeight());
		layeredPane.add(gifLabel, Integer.valueOf(1));

		Timer timer = new Timer(50, new ActionListener() {
			int count = 0;

			@Override
			public void actionPerformed(ActionEvent e) {
				count++;
				if (count == 18) {
					ImageIcon jpgIcon = new ImageIcon(MainPnl.class.getResource("/image/background.jpg"));
					gifLabel.setIcon(jpgIcon);
					((Timer) e.getSource()).stop();
				}
			}
		});

		timer.start();

		centerPanel.add(layeredPane);

		JPanel panel_1 = new JPanel();
		centerPanel.add(panel_1, BorderLayout.NORTH);

		JLabel lblLottoCount = new JLabel("알아서 사세요.  ");
		lblLottoCount.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		panel_1.add(lblLottoCount);

		String[] items = { "로또 수량을 선택하세요.", "1", "2", "3", "4", "5" };
		JComboBox<String> combo = new JComboBox<>(items);
		combo.setFont(fontHolder.getUseFont(Font.BOLD, 18));
		panel_1.add(combo);

		JPanel southPanel = new JPanel();
		getContentPane().add(southPanel, BorderLayout.SOUTH);

		JButton btnNewButton = new JButton("로또 사러 가기!");
		btnNewButton.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		southPanel.add(btnNewButton);

		JPanel northPanel = new JPanel();
		getContentPane().add(northPanel, BorderLayout.NORTH);
		northPanel.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel = new JLabel("제 " + lottoPlayCount + "회 인생 역전 로또");
		lblNewLabel.setFont(fontHolder.getUseFont(Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(JLabel.CENTER);

		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (combo.getSelectedIndex() != 0) {
					String lottoCount = combo.getItemAt(combo.getSelectedIndex());
					int lottoCountInteger = Integer.parseInt(lottoCount);
					DialogPnl dialogPnl = new DialogPnl(lottoCountInteger, lottoPlayCount, MainPnl.this, saveCheckBox);
					dialogPnl.getAgainButton().addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							lottoPlayCount++;
							dialogPnl.dispose();
						}
					});
					dialogPnl.setVisible(true);
					lblNewLabel.setText("제 " + lottoPlayCount + "회 인생 역전 로또");
					revalidate();
					repaint();
				} else {
					JOptionPane.showMessageDialog(MainPnl.this, "숫자를 선택해주세요.");
				}
			}
		});

		northPanel.add(lblNewLabel);

		JPanel panel_2 = new JPanel();
		northPanel.add(panel_2, BorderLayout.NORTH);
		panel_2.setLayout(new BorderLayout(0, 0));

		JLabel lblNewLabel_1 = new JLabel("인생 역전 로또   ");
		lblNewLabel_1.setFont(fontHolder.getUseFont(Font.BOLD, 20));
		lblNewLabel_1.setHorizontalAlignment(JLabel.RIGHT);
		panel_2.add(lblNewLabel_1);

	}

	public static void main(String[] args) {
		new MainPnl().setVisible(true);
	}
}
