import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Memorize_Words_Sub extends Frame implements WindowListener, ActionListener, FocusListener {
	private MenuBar menuBar = new MenuBar();
	private Menu file = new Menu("파일");
	private Menu edit = new Menu("편집");
	private MenuItem file_menu1 = new MenuItem("새 파일");
	private MenuItem file_menu2 = new MenuItem("계산기");
	private MenuItem edit_menu1 = new MenuItem("수정");
	private MenuItem edit_menu2 = new MenuItem("추가");
	private MenuItem edit_menu3 = new MenuItem("삭제");
	private BorderLayout bl = new BorderLayout();
	private GridBagLayout gbl = new GridBagLayout();
	private Panel center = new Panel();
	private Panel form = new Panel();
	private Panel south = new Panel();
	private Panel button = new Panel();
	private ArrayList<TextField> english_list = new ArrayList<>();	// 단어 입력 받을 TextField들의 리스트
	private ArrayList<TextField> korean_list = new ArrayList<>();	// 해석 입력 받을 TextField들의 리스트
	private int line = 0;											// 리스트의 사이즈 크기 및 폼 안의 textField 라인 수
	private ModifyForm modifyForm;
	private Button submit = new Button("확인");
	
	public Memorize_Words_Sub() {
		addMenuBar();
		init();
		start();
	}
	
	public Memorize_Words_Sub(Point location) {
		addMenuBar();
		init(location);
		start();
	}
	
	public void init() {
		setTitle("영단어 암기");
		setSize(300, 580);
		setLocation(300, 100);
		setResizable(false);
		setLayout(bl);
		setMenuBar(menuBar);
		add();
		setVisible(true);
	}
	
	public void init(Point location) {
		setTitle("영단어 암기");
		setSize(300, 580);
		setLocation(location);
		setResizable(false);
		setLayout(bl);
		setMenuBar(menuBar);
		add();
		setVisible(true);
	}
	
	public void addMenuBar() {
		menuBar.add(file);
		menuBar.add(edit);
		file.add(file_menu1);
		file.add(file_menu2);
		edit.add(edit_menu1);
		edit.add(edit_menu2);
		edit.add(edit_menu3);
	}
	
	public void add() {
		add(new Panel(), BorderLayout.NORTH);	// 위쪽 간격 주기 위해
		add(new Panel(), BorderLayout.WEST);	// 왼쪽 간격 주기 위해
		add(new Panel(), BorderLayout.EAST);	// 오른쪽 간격 주기 위해
		add(center, BorderLayout.CENTER);		// 가운데 center 패널 추가
		center.setLayout(new BorderLayout());	// center 패널의 레이아웃 BorderLayout으로 지정
		center.add(form, BorderLayout.NORTH);	// center 패널 위쪽에 form 패널 추가
		form.setLayout(gbl);					// form 패널의 레이아웃 GridBagLayout으로 지정
		addGridBagConstraints(new Label("단어 입력", Label.CENTER), 1, 0, 1, 1);	// GridBagLayout 제약 추가 1번째 열, 1번째 행, 폭 1칸, 높이 1칸
		addGridBagConstraints(new Label("해석 입력", Label.CENTER), 2, 0, 1, 1);	// GridBagLayout 제약 추가 2번째 열, 1번째 행, 폭 1칸, 높이 1칸
		english_list.add(new TextField());										// enlish_list 배열 리스트에 추가
		addGridBagConstraints(english_list.get(line) , 1, 1, 1, 1);				// GridBagLayout 제약 추가 1번째 열, 2번째 행, 폭 1칸, 높이 1칸
		korean_list.add(new TextField());										// korean_list 배열 리스트에 추가
		addGridBagConstraints(korean_list.get(line), 2, 1, 1, 1);				// GridBagLayout 제약 추가 2번째 열, 2번째 행, 폭 1칸, 높이 1칸
		south.setLayout(new BorderLayout());									// south 패널의 레이아웃 BorderLayout 지정
		button.setLayout(new FlowLayout());										// button 패널의 레이아웃 FlowLayout 지정
		button.add(submit);														// button 패널에 submit 버튼 추가
		south.add(button, BorderLayout.SOUTH);									// south 패널에 button 패널 추가 및 프레임 하단에 위치 지정
		add(south, BorderLayout.SOUTH);											// 프레임 하단에 south 패널 추가
		
		line = 1;																// 기본적으로 1라인이 들어가있음.
	}
	
	public void start() {														// 리스너 추가
		addWindowListener(this);
		file_menu1.addActionListener(this);
		file_menu2.addActionListener(this);
		edit_menu1.addActionListener(this);
		edit_menu2.addActionListener(this);
		edit_menu3.addActionListener(this);
		submit.addActionListener(this);
		english_list.get(0).addFocusListener(this);								// 첫 실행 시 추가되어 있는 TextField에 포커스 리스너
		korean_list.get(0).addFocusListener(this);								// 첫 실행 시 추가되어 있는 TextField에 포커스 리스너
	}
	
	public void addGridBagConstraints(Component comp, int x, int y, int width, int height) {	// 컴포넌트, x좌표, y좌표, 폭, 높이
		GridBagConstraints gbc = new GridBagConstraints();										// GridBag 제약 (위치, 크기 조정 해주는 객체)
		
		gbc.fill = GridBagConstraints.BOTH;														// 채우기를 가로, 세로 모두 적용
		gbc.weightx = 1.0;																		// 가로에 가중치를 주어 채울 수 있도록 한다.
		gbc.weighty = 1.0;																		// 세로에 가중치를 주어 채울 수 있도록 한다.
		gbc.gridx = x;																			// x의 위치를 매개변수 x로 지정
		gbc.gridy = y;																			// y의 위치를 매개변수 y로 지정
		gbc.gridwidth = width;																	// 폭을 매개변수 width로 지정
		gbc.gridheight = height;																// 높이를 매개변수 height로 지정
		
		form.add(comp, gbc);																	// form 패널에 추가 후 제약 적용
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowClosed(WindowEvent e) {															// 어떤 프레임이든 닫혔을 때
		if (e.getSource() == modifyForm && modifyForm.textField != null) {								// 닫힌 프레임이 modifyForm이고, modifyForm의 textField가 null이 아닐 때
			line = Integer.parseInt(modifyForm.textField.getText());									// modifyForm의 textField에 입력된 값을 line의 수로 지정
			int number = 0;																				// 임의의 number 변수 0으로 초기화
			if (line > english_list.size()) {															// line이 english_list의 크기보다 크면
				number = line - english_list.size();													// line에서 english_list의 크기를 뺀 값을 저장
				for (int i=0; i<number; i++) {															// 뺀 값만큼 반복하여 각 list 추가 및 프레임에 추가
					english_list.add(new TextField());
					addGridBagConstraints(english_list.get(english_list.size()-1), 1, english_list.size(), 1, 1);
					korean_list.add(new TextField());
					addGridBagConstraints(korean_list.get(korean_list.size()-1), 2, korean_list.size(), 1, 1);
				}
			} else if (english_list.size() > line) {													// english_list의 크기가 line보다 크면
				number = english_list.size() - line;													// english_list의 크기에서 line 수를 뺀 값을 저장
				for (int i=0; i<number; i++) {															// 뺀 값만큼 반복하여 각 list 제거 및 프레임에서 제거
					form.remove(english_list.get(english_list.size()-1));
					english_list.remove(english_list.size()-1);
					form.remove(korean_list.get(korean_list.size()-1));
					korean_list.remove(korean_list.size()-1);
				}
			}
			
			for (int i=1; i<line; i++) {																// 라인 수만큼 반복하여 textField에 리스너 추가
				english_list.get(i).addFocusListener(this);
				korean_list.get(i).addFocusListener(this);
			}
			
			setEnabled(true);																			// 프레임 활성화
			setVisible(true);																			// 변동된 프레임의 상태를 보여줌
		} else {																						// 그 외의 프레임이 닫히면
			setEnabled(true);
			requestFocus();																				// 포커스 요청
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {															// 닫기 버튼 누르면 프로그램 자체를 종료
		if (e.getSource() == this)
			System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == file_menu1) {																	// 파일 - 새 파일 버튼을 눌렀을 때
			english_list.clear();	// english_list를 비워준다
			korean_list.clear();	// korean_list를 비워준다
			this.removeAll();		// 프레임의 모든 컴포넌트를 제거
			center.removeAll();		// center 패널의 모든 컴포넌트를 제거
			form.removeAll();		// form 패널의 모든 컴포넌트를 제거
			south.removeAll();		// south 패널의 모든 컴포넌트를 제거
			button.removeAll();		// button 패널의 모든 컴포넌트를 제거
			line = 0;				// 라인 수를 0으로 초기화
			init();					// 프로그램 실행 했을 때 처럼 프레임 초기화
		} else if (e.getSource() == file_menu2) {															// 파일 - 계산기 버튼을 눌렀을 때
			new Calculator(getLocation());																	// Calculator의 생성자에 현재 프레임 위치를 매개변수로 넣어줌
		} else if (e.getSource() == edit_menu1) {															// 편집 - 수정 버튼을 눌렀을 때
			setEnabled(false);																				// 프레임을 비활성화한다
			modifyForm = new ModifyForm(getX(), getY());													// modifyForm의 프레임 위치를 현재 프레임의 위치에 배정
			modifyForm.addWindowListener(this);
		} else if (e.getSource() == edit_menu2) {															// 편집 - 추가 버튼을 눌렀을 때
			if (line < 20) {																				// line의 값이 20 미만일 경우
				english_list.add(new TextField());
				addGridBagConstraints(english_list.get(english_list.size() - 1), 1, english_list.size(), 1, 1);
				korean_list.add(new TextField());
				addGridBagConstraints(korean_list.get(korean_list.size() - 1), 2, korean_list.size(), 1, 1);
				english_list.get(english_list.size() - 1).addFocusListener(this);
				korean_list.get(korean_list.size() - 1).addFocusListener(this);								// 추가
				line++;																						// 라인 수 증가
				setVisible(true);																			// 프레임의 변동 사항 보여줌
			}
		} else if (e.getSource() == edit_menu3) {															// 편집 - 삭제 버튼을 눌렀을 때
			if (line > 1) {																					// line이 1 초과하였을 때,
				form.remove(english_list.get(english_list.size()-1));
				english_list.remove(english_list.size()-1);
				form.remove(korean_list.get(korean_list.size()-1));
				korean_list.remove(korean_list.size()-1);													// 제거
				line--;																						// 라인 수 감소
				setVisible(true);																			// 프레임의 변동 사항 보여줌
			}
		} else if (e.getSource() == submit) {																// 확인 버튼을 눌렀을 때
			for (int i=0; i<line; i++) {																	// line의 수만큼 반복
				if (english_list.get(i).getText().equals("")) {												// 비어있다면
					english_list.get(i).requestFocus();														// 포커스 요청
					return;																					// 메소드 종료
				}
				else if (korean_list.get(i).getText().equals("")) {
					korean_list.get(i).requestFocus();
					return;
				}
			}
			
			new QuestionForm(this.getLocation(), english_list, korean_list);								// 메소드 종료 없이 왔다면, QuestionForm을 현재 프레임 위치 배정 및 입력한 값의 list 넘겨줌
			dispose();																						// 프레임 닫음
		}
	}

	@Override
	public void focusGained(FocusEvent e) {																	// 포커스 얻었을 때
		for (int i=0; i<line; i++) {
			if (e.getSource() == english_list.get(i) || e.getSource() == korean_list.get(i)) {				// 이벤트 발생이 english_list이거나 korean_list에서 발생하였을 시
				for (int j=0; j<=i; j++) {																	// 이번트 발생한 인덱스의 값만큼 반복(즉, 현재 입력 부분 이전에 비어 있는 부분 체크)
					if (english_list.get(j).getText().equals("")) {											// 비어있다면
						english_list.get(j).requestFocus();													// 포커스 요청
						return;																				// 메소드 종료하지 않으면 반복문의 조건에 의해 포커스 변동이 반복됨.
					}
					else if (korean_list.get(j).getText().equals("")) {										// 위와 같음
						korean_list.get(j).requestFocus();
						return;
					}
				}
			}
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		
	}
}

public class Memorize_Words {
	public static void main(String[] args) {
		new Memorize_Words_Sub();
	}
}