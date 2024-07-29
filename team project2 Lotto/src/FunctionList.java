import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class FunctionList extends JFrame {

//	public FunctionList() {
//		JPanel pnl = new JPanel();
//		List<Integer> list = resultLottoNumber();
//		List<JLabel> lblList = new ArrayList<>();
//		for (int i = 0; i < 7; i++) {
//			JLabel lbl = new JLabel(String.valueOf(list.get(i)));
//			lbl.setVisible(false);
//			lblList.add(lbl);
//			pnl.add(lbl);
//		}
//		JButton btn1 = new JButton("자동");
//		JButton btn2 = new JButton("수동");
//		JButton btn3 = new JButton("반자동");
//		pnl.add(btn1);
//		pnl.add(btn2);
//		pnl.add(btn3);
//		List<JCheckBox> checkBoxList = new ArrayList<>();
//		for (int i = 0; i < 45; i++) {
//			JCheckBox checkBox = new JCheckBox(String.valueOf(i));
//			checkBox.setEnabled(false);
//			checkBoxList.add(checkBox);
//			pnl.add(checkBox);
//		}
//		
//		autoOrSemiAutoBtnFuntion(btn1, checkBoxList, "auto");
//		autoOrSemiAutoBtnFuntion(btn2, checkBoxList, "self");
//		autoOrSemiAutoBtnFuntion(btn3, checkBoxList, "semiAuto");
//		
//		changeToLabelVisible(lblList.get(0), lblList.get(1), lblList.get(2), lblList.get(3),
//				lblList.get(4), lblList.get(5), lblList.get(6));
//
//
//		add(pnl);
//		setSize(500, 500);
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//
//	}

	// 실행시 7개의 중복되지 않는 번호를 list로 반환합니다.
	// 마지막 번호는 보너스 번호로 활용하시면 되며 보너스 번호는 출력메세지를 따로 구분하여 작성해주세요.
	public List<Integer> resultLottoNumber() {

		List<Integer> result = new ArrayList<>();

		while (result.size() < 8) {
			Random random = new Random();
			Integer num = random.nextInt(45) + 1;
			if (!result.contains(num)) {
				result.add(num);
			}
		}
		return result;
	}

	// 순차적으로 번호를 보여줄 수 있도록 visible이 false인 Label들을 true로 1초마다 바꾸어주는 메소드 입니다.
	// resultLottoNumber()메소드를 활용하여 7개의 Label을 구성한 뒤
	// 해당 7개의 Label을 순서대로 괄호 안에 넣어주세요.
	// 해당 7개 Label의 setVisible은 반드시 false로 작성하셔야 합니다.
	// 반환 값은 작성 중 문제 발생으로 void로 변경하게 되었으니 참조 해주세요.
	public void changeToLabelVisible(JLabel lbl1, JLabel lbl2, JLabel lbl3, JLabel lbl4, JLabel lbl5, JLabel lbl6,
			JLabel lbl7) {
		List<JLabel> list = new ArrayList<>(Arrays.asList(lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7));

		Timer timer = new Timer(1000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				for (JLabel lbl : list) {

					if (!lbl.isVisible()) {
						lbl.setVisible(true);
						break;
					}
					boolean allVisible = true;
					for (JLabel lbl1 : list) {
						if (!lbl1.isVisible()) {
							allVisible = false;
							break;
						}
					}
					if (allVisible) {
						((Timer) arg0.getSource()).stop();
					}
				}
			}
		});
		timer.start();
	}

	// 자동 , 반자동, 수동 버튼을 눌렀을 때의 구현 기능 메소드 입니다.
	// 괄호 안에는 자동 버튼, List, 옵션을 넣습니다. (List는 체크박스들을 가지고 있어야 합니다.)
	// 옵션은 String(문자열)로 작성하시면 되고, auto를 입력하시면 자동버튼의 기능,
	// semiAuto를 입력하시면 반자동버튼의 기능을 가집니다.
	// self를 입력하면 수동버튼 기능을 가집니다.
	public void autoOrSemiAutoBtnFuntion(JButton btn, List<JCheckBox> checkBoxList, String option) {

		if (option.equals("auto")) {

			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					changecheckBoxEnableToTrue(checkBoxList);
					for (int i = 0; i < checkBoxList.size(); i++) {
						checkBoxList.get(i).setSelected(false);
					}
					selectCheckBox(checkBoxList, 0);
				}
			});

		} else if (option.equals("semiAuto")) {
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					changecheckBoxEnableToTrue(checkBoxList);
					int count = 0;
					for (int i = 0; i < checkBoxList.size(); i++) {
						if (checkBoxList.get(i).isSelected()) {
							count++;
						}
					}
					selectCheckBox(checkBoxList, count);
				}
			});

		} else if (option.equals("self")) {
			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					changecheckBoxEnableToTrue(checkBoxList);

					for (int i = 0; i < checkBoxList.size(); i++) {
						checkBoxList.get(i).setSelected(false);
					}
				}
			});
		}
	}

	// 체크박스를 선택하는 기능 중 중복된 내용을 메소드화
	private void selectCheckBox(List<JCheckBox> checkBoxList, int count) {
		while (count < 6) {
			Random random = new Random();
			int index = random.nextInt(45);
			if (!checkBoxList.get(index).isSelected()) {
				checkBoxList.get(index).setSelected(true);
				count++;
			}
		}
	}

	// 체크박스를 선택하는 기능 중 중복된 내용을 메소드화
	private void changecheckBoxEnableToTrue(List<JCheckBox> checkBoxList) {
		for (int i = 0; i < checkBoxList.size(); i++) {
			checkBoxList.get(i).setEnabled(true);
		}
	}

//	public static void main(String[] args) {
//		new FunctionList().setVisible(true);
//
//		
//		List<Integer> list = f.resultLottoNumber();
//		System.out.println(list);
//
//	}
}