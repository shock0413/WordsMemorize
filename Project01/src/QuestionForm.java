import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

public class QuestionForm extends Frame {
	ArrayList<TextField> words;									// �Է� ���� �ܾ� ������ ����Ʈ
	ArrayList<TextField> trans;									// �Է� ���� �ؼ� ������ ����Ʈ
	ArrayList<String> questions = new ArrayList<String>();		// ���� ����Ʈ
	private Label question = new Label();						// ���� ǥ�ÿ� ��
	private Button submit = new Button("Ȯ��");
	Font font = new Font("Font", Font.BOLD, 28);				// ��Ʈ ����(����, ũ�� 28)
	private Panel north = new Panel();
	private Panel center = new Panel();
	private Panel south = new Panel();
	private Panel inputForm = new Panel();
	ArrayList<String> answers = new ArrayList<String>();		// ��� �Է� ���� ����Ʈ
	ArrayList<Integer> indexs = new ArrayList<Integer>();		// ���� �ߺ����� �ʵ��� words �Ǵ� trans ����Ʈ�� �ε��� ����
	private int questionNum = 0;								// ���� ���� ���� ��ȣ
	private TextField inputAnswer = new TextField(20);			// ��� �Է¿� �ؽ�Ʈ �ʵ�
	
	public QuestionForm(Point location,ArrayList<TextField> english_list, ArrayList<TextField> korean_list) {
		this.words = english_list;								// Memorize_Words���� �Ѱ� ���� english_list ����
		this.trans = korean_list;								// Memorize_Words���� �Ѱ� ���� korean_list ����
		
		init(location);											// Memorize_Words���� �Ѱ� ���� ������ ��ġ �ʱ�ȭ
		start();
		makeQuestion();											// ���� ����� �޼ҵ�
		
		question.setText(questions.get(questionNum));			// ù��° ���� ����
		questionNum++;											// ���� ���� ��ȣ �̸� ����
	}
	
	public void init(Point location) {
		setTitle("���ܾ� �ϱ�");
		setSize(300, 300);
		setResizable(false);
		setLocation(location);
		setLayout(new BorderLayout());
		add();
		setVisible(true);
	}
	
	public void start() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				dispose();
			}
		});
		
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (questionNum == questions.size()) {								// ���� ������ ������ ������ ��
					answers.add(inputAnswer.getText());								// ��� ����Ʈ�� ��� �Է� �� �߰�
					new GradeForm(getLocation(), words, trans, questions, answers);	// ä���� ����� �� �� �ֵ��� GradeForm���� �� list�� ���� �� ��ġ �� ����
					dispose();														// ����
				} else {															// ������ ���� �ִٸ�
					question.setText(questions.get(questionNum));					// ���� ������ ����
					answers.add(inputAnswer.getText());								// ���� ������ ��� �Է� �� �߰�
					inputAnswer.setText("");										// ��� �Է� �ؽ�Ʈ �ʵ� �ʱ�ȭ
					questionNum++;													// ���� ���� ����
				}
			}
		});
	}
	
	public void add() {
		add(north, BorderLayout.NORTH);
		add(new Panel(), BorderLayout.WEST);
		add(new Panel(), BorderLayout.EAST);
		add(center, BorderLayout.CENTER);
		add(south, BorderLayout.SOUTH);
		center.setLayout(new BorderLayout());
		center.add(question, BorderLayout.CENTER);
		question.setAlignment(Label.CENTER);
		inputForm.setLayout(new FlowLayout());
		inputForm.add(inputAnswer);
		center.add(inputForm, BorderLayout.SOUTH);
		question.setFont(font);
		south.add(submit, BorderLayout.SOUTH);
	}
	
	public void makeQuestion() {
		while (true) {
			int randomNum1 = (int)(Math.random() * 2);					// 0 ~ 1�� ���´�. 0�̸� �ܾ� ����, 1�̸� �ؼ� ����
			int randomNum2 = (int)(Math.random() * words.size());		// 0 ~ (words�� ũ�� - 1)�� ���´�. Memorize_Words������ ���� ����
			int count = 0;												// �ߺ� ���� (1 �̻��̸� �ߺ�)
			
			if (indexs.size() == 0) {									// �ε��� ����Ʈ�� ��� �ִٸ�
				indexs.add(randomNum2);									// randomNum2 �� ����Ʈ�� �߰�
				if (randomNum1 == 0) {
					questions.add(words.get(indexs.get(indexs.size() - 1)).getText());
				}
				
				else if (randomNum1 == 1) {
					questions.add(trans.get(indexs.get(indexs.size() - 1)).getText());
				}
			}
			else {														// �ε��� ����Ʈ�� ��� ���� �ʴٸ�
				for (int i=0; i<indexs.size(); i++) {					// �ε��� ����Ʈ�� ũ�⸸ŭ �ݺ�
					if (indexs.get(i) == randomNum2) {					// �ε��� ����Ʈ�� randomNum2 ���ٸ� �ߺ��̹Ƿ�
						count++;										// �ߺ� Ȯ�ο� count �� ����
					}
				}
				
				if (count == 0) {										// �ߺ� Ȯ���� ���� �ʾ��� ��
					indexs.add(randomNum2);								// randomNum2�� �ε��� ����Ʈ�� �߰�
					
					if (randomNum1 == 0) {								// randomNum1�� 0�̹Ƿ� �ܾ ������ �߰�
						questions.add(words.get(indexs.get(indexs.size() - 1)).getText());	// ������ �߰� �ߴ� �ε��� ����°�� �ܾ� ����Ʈ�� ������ �߰�
					}
					
					else if (randomNum1 == 1) {							// randomNum1�� 1�̹Ƿ� �ؼ��� ������ �߰�
						questions.add(trans.get(indexs.get(indexs.size() - 1)).getText());	// ������ �߰� �ߴ� �ε��� ����°�� �ؼ� ����Ʈ�� ������ �߰�
					}
				}
			}
			
			if (questions.size() == words.size())						// ���� ���� ������ �ܾ��� ������ �����Ƿ�
				break;													// ���� ���� ��
		}
	}
}
