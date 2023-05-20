import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;

public class QuestionForm extends Frame {
	ArrayList<TextField> words;									// 입력 받은 단어 저장할 리스트
	ArrayList<TextField> trans;									// 입력 받은 해석 저장할 리스트
	ArrayList<String> questions = new ArrayList<String>();		// 문제 리스트
	private Label question = new Label();						// 문제 표시용 라벨
	private Button submit = new Button("확인");
	Font font = new Font("Font", Font.BOLD, 28);				// 폰트 지정(굵게, 크기 28)
	private Panel north = new Panel();
	private Panel center = new Panel();
	private Panel south = new Panel();
	private Panel inputForm = new Panel();
	ArrayList<String> answers = new ArrayList<String>();		// 답안 입력 받은 리스트
	ArrayList<Integer> indexs = new ArrayList<Integer>();		// 문제 중복되지 않도록 words 또는 trans 리스트의 인덱스 저장
	private int questionNum = 0;								// 현재 문제 문항 번호
	private TextField inputAnswer = new TextField(20);			// 답안 입력용 텍스트 필드
	
	public QuestionForm(Point location,ArrayList<TextField> english_list, ArrayList<TextField> korean_list) {
		this.words = english_list;								// Memorize_Words에서 넘겨 받은 english_list 저장
		this.trans = korean_list;								// Memorize_Words에서 넘겨 받은 korean_list 저장
		
		init(location);											// Memorize_Words에서 넘겨 받은 프레임 위치 초기화
		start();
		makeQuestion();											// 문제 만드는 메소드
		
		question.setText(questions.get(questionNum));			// 첫번째 문제 설정
		questionNum++;											// 다음 문제 번호 미리 증가
	}
	
	public void init(Point location) {
		setTitle("영단어 암기");
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
				if (questionNum == questions.size()) {								// 현재 문항이 마지막 문제일 때
					answers.add(inputAnswer.getText());								// 답안 리스트에 답안 입력 값 추가
					new GradeForm(getLocation(), words, trans, questions, answers);	// 채점된 결과를 볼 수 있도록 GradeForm으로 각 list를 전달 및 위치 값 전달
					dispose();														// 닫음
				} else {															// 문제가 남아 있다면
					question.setText(questions.get(questionNum));					// 다음 문제로 설정
					answers.add(inputAnswer.getText());								// 이전 문제의 답안 입력 값 추가
					inputAnswer.setText("");										// 답안 입력 텍스트 필드 초기화
					questionNum++;													// 현재 문항 증가
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
			int randomNum1 = (int)(Math.random() * 2);					// 0 ~ 1이 나온다. 0이면 단어 문제, 1이면 해석 문제
			int randomNum2 = (int)(Math.random() * words.size());		// 0 ~ (words의 크기 - 1)가 나온다. Memorize_Words에서의 라인 선택
			int count = 0;												// 중복 갯수 (1 이상이면 중복)
			
			if (indexs.size() == 0) {									// 인덱스 리스트가 비어 있다면
				indexs.add(randomNum2);									// randomNum2 값 리스트에 추가
				if (randomNum1 == 0) {
					questions.add(words.get(indexs.get(indexs.size() - 1)).getText());
				}
				
				else if (randomNum1 == 1) {
					questions.add(trans.get(indexs.get(indexs.size() - 1)).getText());
				}
			}
			else {														// 인덱스 리스트가 비어 있지 않다면
				for (int i=0; i<indexs.size(); i++) {					// 인덱스 리스트의 크기만큼 반복
					if (indexs.get(i) == randomNum2) {					// 인덱스 리스트가 randomNum2 같다면 중복이므로
						count++;										// 중복 확인용 count 값 증가
					}
				}
				
				if (count == 0) {										// 중복 확인이 되지 않았을 때
					indexs.add(randomNum2);								// randomNum2를 인덱스 리스트에 추가
					
					if (randomNum1 == 0) {								// randomNum1이 0이므로 단어를 문제로 추가
						questions.add(words.get(indexs.get(indexs.size() - 1)).getText());	// 이전에 추가 했던 인덱스 값번째의 단어 리스트를 문제에 추가
					}
					
					else if (randomNum1 == 1) {							// randomNum1이 1이므로 해석을 문제로 추가
						questions.add(trans.get(indexs.get(indexs.size() - 1)).getText());	// 이전에 추가 했던 인덱스 값번째의 해석 리스트를 문제에 추가
					}
				}
			}
			
			if (questions.size() == words.size())						// 문제 수가 출제할 단어의 갯수와 같으므로
				break;													// 문제 출제 끝
		}
	}
}
