import java.awt.*;
import java.awt.event.*;

public class ModifyForm extends Frame implements WindowListener, ActionListener, KeyListener {
	private Panel center = new Panel();
	private Panel form = new Panel();
	private Panel button_form = new Panel();
	TextField textField = new TextField();
	private Button submit = new Button("Ȯ��");
	private Button cancel = new Button("���");
	int x, y;
	
	public ModifyForm(int x, int y) {
		this.x = x;		// ������ x ��ġ
		this.y = y;		// ������ y ��ġ
		init();			// �ʱ�ȭ
		start();		// ������ �߰�
	}
	
	public void init() {
		setTitle("����");
		setSize(400, 200);
		setLocation(x - 50, y + 150);	// ���� ���� ������ ��ġ ����
		setResizable(false);			// ������ ũ�� ���� �Ұ�
		setLayout(new BorderLayout());
		add();							// ������Ʈ �߰�
		setVisible(true);
	}
	
	public void add() {
		add(new Panel(), BorderLayout.NORTH);
		add(new Panel(), BorderLayout.WEST);
		add(new Panel(), BorderLayout.EAST);
		add(new Panel(), BorderLayout.SOUTH);
		center.setLayout(new BorderLayout());
		form.setLayout(new GridBagLayout());
		addGridBagConstraints(new Label("�� ���� �ܾ �߰��Ͻðڽ��ϱ�?"), 0, 0, 1, 1);
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
		textField = null;	// ��ҿ� ����
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
					Integer.parseInt(textField.getText()) <= 20)	// textField �Է� �Ǿ� �ְ� ���� 1 �̻� 20 ������ ��
				dispose();
		} else if (e.getSource() == cancel) {
			textField = null;	// �ݱ� ��ư�� ����
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
	public void keyTyped(KeyEvent e) {	// Ű Ÿ���� ��
		char c = e.getKeyChar();		// �̺�Ʈ�� �Ͼ Ű�� ���� ����
		
		if (!Character.isDigit(c)) {	// Character c�� ���ڰ� �ƴ� ��
			e.consume();				// �Ͼ �̺�Ʈ e�� ������.
			return;
		}
	}
}