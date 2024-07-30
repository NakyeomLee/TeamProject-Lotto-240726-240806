import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class FunctionList extends JFrame {

	// 실행시 7개의 중복되지 않는 번호를 list로 반환합니다.
	// 마지막 번호는 보너스 번호로 활용하시면 되며 보너스 번호는 출력메세지를 따로 구분하여 작성해주세요.
	public List<String> resultLottoNumber() {

		List<Integer> list = new ArrayList<>();
		List<String> result = new ArrayList<>();

		while (list.size() < 8) {
			Random random = new Random();
			Integer num = random.nextInt(45) + 1;
			if (!list.contains(num)) {
				list.add(num);
			}
		}

		for (int i = 0; i < list.size(); i++) {
			result.add(String.valueOf(list.get(i)));
		}
		return result;
	}

	// 순차적으로 번호를 보여줄 수 있도록 visible이 false인 Label들을 true로 1초마다 바꾸어주는 메소드 입니다.
	// resultLottoNumber()메소드를 활용하여 7개의 Label을 구성한 뒤
	// 첫 Label에는 결과메세지를 출력할 Label을 넣어주고,
	// 이후 순차적으로 보여줄 7개의 Label을 순서대로 괄호 안에 넣어주세요.
	// 마지막에는 모든 Label이 출력된 후 visible을 true로 변환시킬 버튼을 넣어주세요.
	// 반환 값은 작성 중 문제 발생으로 void로 변경하게 되었으니 참조 해주세요.
	public void changeToLabelVisible(JLabel textLbl, JLabel lbl1, JLabel lbl2, JLabel lbl3, JLabel lbl4, JLabel lbl5,
			JLabel lbl6, JLabel plusLbl, JLabel bonusLbl, JButton btn) {
		List<JLabel> list = new ArrayList<>(Arrays.asList(lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, plusLbl, bonusLbl));
		btn.setVisible(false);
		for (JLabel lbl : list) {
			lbl.setVisible(false);
		}

		Timer timer = new Timer(700, new ActionListener() {

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
						textLbl.setText("결과를 확인해주세요");
						btn.setVisible(true);
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
	public void autoOrSemiAutoBtnFuntion(JButton btn, List<JCheckBox> checkBoxList, String option, List<Integer> findBtn) {

		if (option.equals("auto")) {

			btn.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					changecheckBoxEnableToTrue(checkBoxList);
					for (int i = 0; i < checkBoxList.size(); i++) {
						checkBoxList.get(i).setSelected(false);
					}
					
					List<Integer> list = selectCheckBox(checkBoxList, 0);
					listComposition(findBtn, list, 1);
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
					List<Integer> list = selectCheckBox(checkBoxList, count);
					listComposition(findBtn, list, 3);
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
					findBtn.clear();
					findBtn.add(2);
				}
			});
		}
	}

	// 해당 메소드를 실행 시키면, 해당 메소드 파라미터 값으로 던져준 체크박스 리스트의 체크된 개수가 6개 이상일시
	// 더 이상 선택할 수 없도록 체크박스를 비활성화 시킵니다.
	public void checkLimit(List<JCheckBox> checkBoxList) {
		Timer timer = new Timer(1, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int count = 0;
				for (JCheckBox box : checkBoxList) {
					if (box.isSelected()) {
						count++;
					}
				}
				if (count == 6) {
					for (JCheckBox box : checkBoxList) {
						box.setEnabled(false);
					}
				}
			}
		});
		timer.start();
	}

	// 체크박스 리스트를 집어넣으면, 체크가 되어 있는 체크박스들의 숫자만 추려서
	// Integer형의 새로운 리스트로 반환하는 메소드입니다.
	public List<Integer> returnCheckBoxListToIntegerList(List<JCheckBox> list) {
		List<Integer> result = new ArrayList<>();

		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).isSelected()) {
				result.add(i + 1);
			}
		}
		return result;
	}

	// 로또 결과와 사용자의 입력값에 따라 Label의 텍스트를 변경시켜주는 메소드 입니다.
	// 텍스트를 변경시킬 Label, 사용자의 입력 값 리스트를 <Integer>의 형태로, 로또 결과 리스트를 <String>의 형태로
	// 넣어주시면 되며
	// 사용자의 입력 값 리스트는 원래 List<JCheckBox>의 형태일 것이기에
	// returnCheckBoxListToIntegerList메소드를 이용하여 Integer형 리스트를 반환 받아 사용하시면 됩니다.
	public void setLabelTextToResult(JLabel lbl, List<Integer> userList, List<String> resultStrList) {
		List<Integer> resultList = new ArrayList<>();
		for (int i = 0; i < resultStrList.size() - 1; i++) {
			resultList.add(Integer.valueOf(resultStrList.get(i)));
		}

		Integer bonusNum = Integer.valueOf(resultStrList.get(resultStrList.size() - 1));
		boolean containBonusNum = userList.contains(bonusNum);

		int count = 0;
		for (Integer num : userList) {
			if (resultList.contains(num)) {
				count++;
			}
		}

		if (count == 6) {
			lbl.setText("1등 당첨");
		} else if (count == 5 && containBonusNum) {
			lbl.setText("2등 당첨");
		} else if (count == 5) {
			lbl.setText("3등 당첨");
		} else if (count == 4) {
			lbl.setText("4등 당첨");
		} else if (count == 3) {
			lbl.setText("5등 당첨");
		} else {
			lbl.setText("망했습니다.");
		}
	}

	// 사용자가 모든 번호를 선택하였는지를 체크하는 메소드 입니다.
	// 사용자가 체크한 모든 정보가 들어있는 List<List<JCheckBox>>를 파라미터값으로 넣어주세요
	// 만약 모두 6개씩 정상적으로 체크했다면 true를 반환하며,
	// 나머지 상황에서는 false를 반환합니다.
	public boolean checkAllSelected(List<List<JCheckBox>> result) {
		for (int i = 0; i < result.size(); i++) {
			int count = 0;
			for (int j = 0; j < result.get(i).size(); j++) {
				if (result.get(i).get(j).isSelected()) {
					count++;
				}
			}
			if (count != 6) {
				return false;
			}
		}
		return true;
	}

	// 체크박스를 선택하는 기능 중 중복된 내용을 메소드화
	// 추가 건의사항을 구현하기 위하여 반환 타입을 List<Integer>로 변경
	private List<Integer> selectCheckBox(List<JCheckBox> checkBoxList, int count) {
		List<Integer> result = new ArrayList<>();
		while (count < 6) {
			Random random = new Random();
			int index = random.nextInt(45);
			if (!checkBoxList.get(index).isSelected()) {
				checkBoxList.get(index).setSelected(true);
				count++;
				result.add(index + 1);
			}
		}
		Collections.sort(result);
		return result;
	}

	// 체크박스를 선택하는 기능 중 중복된 내용을 메소드화
	private void changecheckBoxEnableToTrue(List<JCheckBox> checkBoxList) {
		for (int i = 0; i < checkBoxList.size(); i++) {
			checkBoxList.get(i).setEnabled(true);
		}
	}
	
	//
	private void listComposition(List<Integer> findBtn, List<Integer> list, int btnDivideNum) {
		findBtn.clear();
		findBtn.add(btnDivideNum);
		for(Integer num : list) {
			findBtn.add(num);
		}
	}
	

}