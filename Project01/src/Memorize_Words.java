import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

class Memorize_Words_Sub extends Frame implements WindowListener, ActionListener, FocusListener {
	private MenuBar menuBar = new MenuBar();
	private Menu file = new Menu("����");
	private Menu edit = new Menu("����");
	private MenuItem file_menu1 = new MenuItem("�� ����");
	private MenuItem file_menu2 = new MenuItem("����");
	private MenuItem edit_menu1 = new MenuItem("����");
	private MenuItem edit_menu2 = new MenuItem("�߰�");
	private MenuItem edit_menu3 = new MenuItem("����");
	private BorderLayout bl = new BorderLayout();
	private GridBagLayout gbl = new GridBagLayout();
	private Panel center = new Panel();
	private Panel form = new Panel();
	private Panel south = new Panel();
	private Panel button = new Panel();
	private ArrayList<TextField> english_list = new ArrayList<>();	// �ܾ� �Է� ���� TextField���� ����Ʈ
	private ArrayList<TextField> korean_list = new ArrayList<>();	// �ؼ� �Է� ���� TextField���� ����Ʈ
	private int line = 0;											// ����Ʈ�� ������ ũ�� �� �� ���� textField ���� ��
	private ModifyForm modifyForm;
	private Button submit = new Button("Ȯ��");
	
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
		setTitle("���ܾ� �ϱ�");
		setSize(300, 580);
		setLocation(300, 100);
		setResizable(false);
		setLayout(bl);
		setMenuBar(menuBar);
		add();
		setVisible(true);
	}
	
	public void init(Point location) {
		setTitle("���ܾ� �ϱ�");
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
		add(new Panel(), BorderLayout.NORTH);	// ���� ���� �ֱ� ����
		add(new Panel(), BorderLayout.WEST);	// ���� ���� �ֱ� ����
		add(new Panel(), BorderLayout.EAST);	// ������ ���� �ֱ� ����
		add(center, BorderLayout.CENTER);		// ��� center �г� �߰�
		center.setLayout(new BorderLayout());	// center �г��� ���̾ƿ� BorderLayout���� ����
		center.add(form, BorderLayout.NORTH);	// center �г� ���ʿ� form �г� �߰�
		form.setLayout(gbl);					// form �г��� ���̾ƿ� GridBagLayout���� ����
		addGridBagConstraints(new Label("�ܾ� �Է�", Label.CENTER), 1, 0, 1, 1);	// GridBagLayout ���� �߰� 1��° ��, 1��° ��, �� 1ĭ, ���� 1ĭ
		addGridBagConstraints(new Label("�ؼ� �Է�", Label.CENTER), 2, 0, 1, 1);	// GridBagLayout ���� �߰� 2��° ��, 1��° ��, �� 1ĭ, ���� 1ĭ
		english_list.add(new TextField());										// enlish_list �迭 ����Ʈ�� �߰�
		addGridBagConstraints(english_list.get(line) , 1, 1, 1, 1);				// GridBagLayout ���� �߰� 1��° ��, 2��° ��, �� 1ĭ, ���� 1ĭ
		korean_list.add(new TextField());										// korean_list �迭 ����Ʈ�� �߰�
		addGridBagConstraints(korean_list.get(line), 2, 1, 1, 1);				// GridBagLayout ���� �߰� 2��° ��, 2��° ��, �� 1ĭ, ���� 1ĭ
		south.setLayout(new BorderLayout());									// south �г��� ���̾ƿ� BorderLayout ����
		button.setLayout(new FlowLayout());										// button �г��� ���̾ƿ� FlowLayout ����
		button.add(submit);														// button �гο� submit ��ư �߰�
		south.add(button, BorderLayout.SOUTH);									// south �гο� button �г� �߰� �� ������ �ϴܿ� ��ġ ����
		add(south, BorderLayout.SOUTH);											// ������ �ϴܿ� south �г� �߰�
		
		line = 1;																// �⺻������ 1������ ������.
	}
	
	public void start() {														// ������ �߰�
		addWindowListener(this);
		file_menu1.addActionListener(this);
		file_menu2.addActionListener(this);
		edit_menu1.addActionListener(this);
		edit_menu2.addActionListener(this);
		edit_menu3.addActionListener(this);
		submit.addActionListener(this);
		english_list.get(0).addFocusListener(this);								// ù ���� �� �߰��Ǿ� �ִ� TextField�� ��Ŀ�� ������
		korean_list.get(0).addFocusListener(this);								// ù ���� �� �߰��Ǿ� �ִ� TextField�� ��Ŀ�� ������
	}
	
	public void addGridBagConstraints(Component comp, int x, int y, int width, int height) {	// ������Ʈ, x��ǥ, y��ǥ, ��, ����
		GridBagConstraints gbc = new GridBagConstraints();										// GridBag ���� (��ġ, ũ�� ���� ���ִ� ��ü)
		
		gbc.fill = GridBagConstraints.BOTH;														// ä��⸦ ����, ���� ��� ����
		gbc.weightx = 1.0;																		// ���ο� ����ġ�� �־� ä�� �� �ֵ��� �Ѵ�.
		gbc.weighty = 1.0;																		// ���ο� ����ġ�� �־� ä�� �� �ֵ��� �Ѵ�.
		gbc.gridx = x;																			// x�� ��ġ�� �Ű����� x�� ����
		gbc.gridy = y;																			// y�� ��ġ�� �Ű����� y�� ����
		gbc.gridwidth = width;																	// ���� �Ű����� width�� ����
		gbc.gridheight = height;																// ���̸� �Ű����� height�� ����
		
		form.add(comp, gbc);																	// form �гο� �߰� �� ���� ����
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
	}

	@Override
	public void windowClosed(WindowEvent e) {															// � �������̵� ������ ��
		if (e.getSource() == modifyForm && modifyForm.textField != null) {								// ���� �������� modifyForm�̰�, modifyForm�� textField�� null�� �ƴ� ��
			line = Integer.parseInt(modifyForm.textField.getText());									// modifyForm�� textField�� �Էµ� ���� line�� ���� ����
			int number = 0;																				// ������ number ���� 0���� �ʱ�ȭ
			if (line > english_list.size()) {															// line�� english_list�� ũ�⺸�� ũ��
				number = line - english_list.size();													// line���� english_list�� ũ�⸦ �� ���� ����
				for (int i=0; i<number; i++) {															// �� ����ŭ �ݺ��Ͽ� �� list �߰� �� �����ӿ� �߰�
					english_list.add(new TextField());
					addGridBagConstraints(english_list.get(english_list.size()-1), 1, english_list.size(), 1, 1);
					korean_list.add(new TextField());
					addGridBagConstraints(korean_list.get(korean_list.size()-1), 2, korean_list.size(), 1, 1);
				}
			} else if (english_list.size() > line) {													// english_list�� ũ�Ⱑ line���� ũ��
				number = english_list.size() - line;													// english_list�� ũ�⿡�� line ���� �� ���� ����
				for (int i=0; i<number; i++) {															// �� ����ŭ �ݺ��Ͽ� �� list ���� �� �����ӿ��� ����
					form.remove(english_list.get(english_list.size()-1));
					english_list.remove(english_list.size()-1);
					form.remove(korean_list.get(korean_list.size()-1));
					korean_list.remove(korean_list.size()-1);
				}
			}
			
			for (int i=1; i<line; i++) {																// ���� ����ŭ �ݺ��Ͽ� textField�� ������ �߰�
				english_list.get(i).addFocusListener(this);
				korean_list.get(i).addFocusListener(this);
			}
			
			setEnabled(true);																			// ������ Ȱ��ȭ
			setVisible(true);																			// ������ �������� ���¸� ������
		} else {																						// �� ���� �������� ������
			setEnabled(true);
			requestFocus();																				// ��Ŀ�� ��û
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {															// �ݱ� ��ư ������ ���α׷� ��ü�� ����
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
		if (e.getSource() == file_menu1) {																	// ���� - �� ���� ��ư�� ������ ��
			english_list.clear();	// english_list�� ����ش�
			korean_list.clear();	// korean_list�� ����ش�
			this.removeAll();		// �������� ��� ������Ʈ�� ����
			center.removeAll();		// center �г��� ��� ������Ʈ�� ����
			form.removeAll();		// form �г��� ��� ������Ʈ�� ����
			south.removeAll();		// south �г��� ��� ������Ʈ�� ����
			button.removeAll();		// button �г��� ��� ������Ʈ�� ����
			line = 0;				// ���� ���� 0���� �ʱ�ȭ
			init();					// ���α׷� ���� ���� �� ó�� ������ �ʱ�ȭ
		} else if (e.getSource() == file_menu2) {															// ���� - ���� ��ư�� ������ ��
			new Calculator(getLocation());																	// Calculator�� �����ڿ� ���� ������ ��ġ�� �Ű������� �־���
		} else if (e.getSource() == edit_menu1) {															// ���� - ���� ��ư�� ������ ��
			setEnabled(false);																				// �������� ��Ȱ��ȭ�Ѵ�
			modifyForm = new ModifyForm(getX(), getY());													// modifyForm�� ������ ��ġ�� ���� �������� ��ġ�� ����
			modifyForm.addWindowListener(this);
		} else if (e.getSource() == edit_menu2) {															// ���� - �߰� ��ư�� ������ ��
			if (line < 20) {																				// line�� ���� 20 �̸��� ���
				english_list.add(new TextField());
				addGridBagConstraints(english_list.get(english_list.size() - 1), 1, english_list.size(), 1, 1);
				korean_list.add(new TextField());
				addGridBagConstraints(korean_list.get(korean_list.size() - 1), 2, korean_list.size(), 1, 1);
				english_list.get(english_list.size() - 1).addFocusListener(this);
				korean_list.get(korean_list.size() - 1).addFocusListener(this);								// �߰�
				line++;																						// ���� �� ����
				setVisible(true);																			// �������� ���� ���� ������
			}
		} else if (e.getSource() == edit_menu3) {															// ���� - ���� ��ư�� ������ ��
			if (line > 1) {																					// line�� 1 �ʰ��Ͽ��� ��,
				form.remove(english_list.get(english_list.size()-1));
				english_list.remove(english_list.size()-1);
				form.remove(korean_list.get(korean_list.size()-1));
				korean_list.remove(korean_list.size()-1);													// ����
				line--;																						// ���� �� ����
				setVisible(true);																			// �������� ���� ���� ������
			}
		} else if (e.getSource() == submit) {																// Ȯ�� ��ư�� ������ ��
			for (int i=0; i<line; i++) {																	// line�� ����ŭ �ݺ�
				if (english_list.get(i).getText().equals("")) {												// ����ִٸ�
					english_list.get(i).requestFocus();														// ��Ŀ�� ��û
					return;																					// �޼ҵ� ����
				}
				else if (korean_list.get(i).getText().equals("")) {
					korean_list.get(i).requestFocus();
					return;
				}
			}
			
			new QuestionForm(this.getLocation(), english_list, korean_list);								// �޼ҵ� ���� ���� �Դٸ�, QuestionForm�� ���� ������ ��ġ ���� �� �Է��� ���� list �Ѱ���
			dispose();																						// ������ ����
		}
	}

	@Override
	public void focusGained(FocusEvent e) {																	// ��Ŀ�� ����� ��
		for (int i=0; i<line; i++) {
			if (e.getSource() == english_list.get(i) || e.getSource() == korean_list.get(i)) {				// �̺�Ʈ �߻��� english_list�̰ų� korean_list���� �߻��Ͽ��� ��
				for (int j=0; j<=i; j++) {																	// �̹�Ʈ �߻��� �ε����� ����ŭ �ݺ�(��, ���� �Է� �κ� ������ ��� �ִ� �κ� üũ)
					if (english_list.get(j).getText().equals("")) {											// ����ִٸ�
						english_list.get(j).requestFocus();													// ��Ŀ�� ��û
						return;																				// �޼ҵ� �������� ������ �ݺ����� ���ǿ� ���� ��Ŀ�� ������ �ݺ���.
					}
					else if (korean_list.get(j).getText().equals("")) {										// ���� ����
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