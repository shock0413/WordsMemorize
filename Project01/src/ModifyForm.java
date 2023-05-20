import java.awt.*;
import java.awt.event.*;

public class ModifyForm extends Frame implements WindowListener, ActionListener, KeyListener {
	private Panel center = new Panel();
	private Panel form = new Panel();
	private Panel button_form = new Panel();
	TextField textField = new TextField();
	private Button submit = new Button("확인");
	private Button cancel = new Button("취소");
	int x, y;
	
	public ModifyForm(int x, int y) {
		this.x = x;		// 프레임 x 위치
		this.y = y;		// 프레임 y 위치
		init();			// 초기화
		start();		// 리스너 추가
	}
	
	public void init() {
		setTitle("수정");
		setSize(400, 200);
		setLocation(x - 50, y + 150);	// 전달 받은 프레임 위치 지정
		setResizable(false);			// 프레임 크기 수정 불가
		setLayout(new BorderLayout());
		add();							// 컴포넌트 추가
		setVisible(true);
	}
	
	public void add() {
		add(new Panel(), BorderLayout.NORTH);
		add(new Panel(), BorderLayout.WEST);
		add(new Panel(), BorderLayout.EAST);
		add(new Panel(), BorderLayout.SOUTH);
		center.setLayout(new BorderLayout());
		form.setLayout(new GridBagLayout());
		addGridBagConstraints(new Label("몇 개의 단어를 추가하시겠습니까?"), 0, 0, 1, 1);
		addGridBagConstraints(textField, 0, 1, 1, 1);
		button_form.setLayout(new FlowLayout());
		button_form.add(submit);
		button_form.add(cancel);
		center.add(form, BorderLayout.CENTER);
		add(center, BorderLayout.CENTER);
		add(button_form, BorderLayout.SOUTH);
	}
	
	public void start() {
		addWindowListener(this);
		submit.addActionListener(this);
		cancel.addActionListener(this);
		textField.addKeyListener(this);
	}
	
	public void addGridBagConstraints(Component comp, int x, int y, int width, int height) {
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		
		form.add(comp, gbc);		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		textField = null;	// 취소와 같음
		dispose();
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submit) {
			if (!textField.getText().equals("") && Integer.parseInt(textField.getText()) > 0 &&
					Integer.parseInt(textField.getText()) <= 20)	// textField 입력 되어 있고 값이 1 이상 20 이하일 때
				dispose();
		} else if (e.getSource() == cancel) {
			textField = null;	// 닫기 버튼과 같음
			dispose();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {	// 키 타이핑 시
		char c = e.getKeyChar();		// 이벤트가 일어난 키의 문자 저장
		
		if (!Character.isDigit(c)) {	// Character c가 숫자가 아닐 때
			e.consume();				// 일어난 이벤트 e를 버려줌.
			return;
		}
	}
}