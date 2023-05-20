import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GradeForm extends Frame {
	Point location;
	ArrayList<TextField> words;		// �ٽ��ϱ� ���ؼ�
	ArrayList<TextField> trans;		// �ٽ��ϱ� ���ؼ�
	ArrayList<String> questions;	// QuestionForm���� ���� ��� ������
	ArrayList<String> answers;		// QuestionForm���� �Է� �ߴ� ��ȵ� (questions�� ũ�� ����)
	int count = 0;					// ä���� ������ �ε���
	int hit = 0;					// ���� ����
	private Panel center = new Panel();
	private Panel south = new Panel();
	private Panel form = new Panel();
	private Button retry = new Button("�ٽ��ϱ�");
	private Button reset = new Button("�ʱ�ȭ");
	private GridBagLayout gbl = new GridBagLayout();
	ArrayList<Label> corrects = new ArrayList<Label>();	// ���� ǥ�� �ϱ� ���� ����Ʈ
	ArrayList<Label> wrongs = new ArrayList<Label>();	// ���� ǥ�� �ϱ� ���� ����Ʈ
	
	public GradeForm(Point point, ArrayList<TextField> word, ArrayList<TextField> tran, ArrayList<String> ques, ArrayList<String> answ) {
		location = point;	// QuestionForm���� �Ѿ�� ��ġ ��
		words = word;		// QuestionForm���� �Ѿ�� �ܾ� ����Ʈ
		trans = tran;		// QuestionForm���� �Ѿ�� �ؼ� ����Ʈ
		questions = ques;	// QuestionForm���� �Ѿ�� ���� ����Ʈ
		answers = answ;		// QuestionForm���� �Ѿ�� ��� ����Ʈ
		init();
		start();
	}
	
	public void init() {
		setTitle("���ܾ� �ϱ�");
		setSize(300, 580);
		setResizable(false);
		setLocation(location);
		setLayout(new BorderLayout());
		add();
		grade();
		setVisible(true);
	}
	
	public void add() {
		add(new Panel(), BorderLayout.NORTH);
		add(new Panel(), BorderLayout.WEST);
		add(new Panel(), BorderLayout.EAST);
		center.setLayout(new BorderLayout());
		center.add(form, BorderLayout.NORTH);
		form.setLayout(gbl);
		addGridBagConstraints(new Label("�� ��", Label.CENTER), 1, 0, 1, 1);
		addGridBagConstraints(new Label("�� ��", Label.CENTER), 2, 0, 1, 1);
		addGridBagConstraints(new Label("�� �� �� ��", Label.CENTER), 3, 0, 1, 1);
		for (int i=0; i<questions.size(); i++) {									// ���� ��� ������ ũ�⸸ŭ �ݺ�
			addGridBagConstraints(new TextField(questions.get(i)), 1, i+1, 1, 1);	// ���� ǥ��
			addGridBagConstraints(new TextField(answers.get(i)), 2, i+1, 1, 1);		// ��� ǥ��
		}
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
	}
	
	public void start() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				dispose();
			}
		});
		
		retry.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new QuestionForm(getLocation(), words, trans);
				dispose();
			}
		});
		
		reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Memorize_Words_Sub(getLocation());
				dispose();
			}
		});
	}
	
	public void addGridBagConstraints(Component comp, int x, int y, int width, int height) {
		GridBagConstraints gbc = new GridBagConstraints();
		
		if (comp.getClass() == TextField.class) {
			comp.setFocusable(false);
		}
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = width;
		gbc.gridheight = height;
		
		form.add(comp, gbc);		
	}
	
	public void grade() {		// ä���ϴ� �޼ҵ�
		while (count < questions.size()) {
			for (int i=0; i<words.size(); i++) {
				if (questions.get(count).trim().equals(words.get(i).getText().trim())) {
					if (answers.get(count).trim().equals(trans.get(i).getText().trim())) {
						corrects.add(new Label("�� �� !", Label.CENTER));
						corrects.get(corrects.size() - 1).setForeground(Color.BLUE);
						addGridBagConstraints(corrects.get(corrects.size() - 1), 3, count+1, 1, 1);
						hit++;
					} else if (!answers.get(count).trim().equals(trans.get(i).getText().trim())) {
						wrongs.add(new Label("�� �� !", Label.CENTER));
						wrongs.get(wrongs.size() - 1).setForeground(Color.RED);
						addGridBagConstraints(wrongs.get(wrongs.size() - 1), 3, count+1, 1, 1);
					}
				} else if (questions.get(count).trim().equals(trans.get(i).getText().trim())) {
					if (answers.get(count).trim().equals(words.get(i).getText().trim())) {
						corrects.add(new Label("�� �� !", Label.CENTER));
						corrects.get(corrects.size() - 1).setForeground(Color.BLUE);
						addGridBagConstraints(corrects.get(corrects.size() - 1), 3, count+1, 1, 1);
						hit++;
					} else if (!answers.get(count).trim().equals(words.get(i).getText().trim())) {
						System.out.println("����!");
						wrongs.add(new Label("�� �� !", Label.CENTER));
						wrongs.get(wrongs.size() - 1).setForeground(Color.RED);
						addGridBagConstraints(wrongs.get(wrongs.size() - 1), 3, count+1, 1, 1);
					}
				}
				
			}
			count++;
		}
		
		south.setLayout(new FlowLayout());
		south.add(new Label("ä�� ��� : " + hit + "/" + questions.size(), Label.CENTER));
		south.add(retry);
		south.add(reset);
	}
}