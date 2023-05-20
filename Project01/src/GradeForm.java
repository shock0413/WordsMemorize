import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class GradeForm extends Frame {
	Point location;
	ArrayList<TextField> words;		// 다시하기 위해서
	ArrayList<TextField> trans;		// 다시하기 위해서
	ArrayList<String> questions;	// QuestionForm에서 출제 됬던 문제들
	ArrayList<String> answers;		// QuestionForm에서 입력 했던 답안들 (questions와 크기 동일)
	int count = 0;					// 채점할 문제의 인덱스
	int hit = 0;					// 맞춘 갯수
	private Panel center = new Panel();
	private Panel south = new Panel();
	private Panel form = new Panel();
	private Button retry = new Button("다시하기");
	private Button reset = new Button("초기화");
	private GridBagLayout gbl = new GridBagLayout();
	ArrayList<Label> corrects = new ArrayList<Label>();	// 정답 표시 하기 위한 리스트
	ArrayList<Label> wrongs = new ArrayList<Label>();	// 오답 표시 하기 위한 리스트
	
	public GradeForm(Point point, ArrayList<TextField> word, ArrayList<TextField> tran, ArrayList<String> ques, ArrayList<String> answ) {
		location = point;	// QuestionForm에서 넘어온 위치 값
		words = word;		// QuestionForm에서 넘어온 단어 리스트
		trans = tran;		// QuestionForm에서 넘어온 해석 리스트
		questions = ques;	// QuestionForm에서 넘어온 문제 리스트
		answers = answ;		// QuestionForm에서 넘어온 답안 리스트
		init();
		start();
	}
	
	public void init() {
		setTitle("영단어 암기");
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
		addGridBagConstraints(new Label("문 제", Label.CENTER), 1, 0, 1, 1);
		addGridBagConstraints(new Label("답 안", Label.CENTER), 2, 0, 1, 1);
		addGridBagConstraints(new Label("정 답 여 부", Label.CENTER), 3, 0, 1, 1);
		for (int i=0; i<questions.size(); i++) {									// 출제 됬던 문제의 크기만큼 반복
			addGridBagConstraints(new TextField(questions.get(i)), 1, i+1, 1, 1);	// 문제 표시
			addGridBagConstraints(new TextField(answers.get(i)), 2, i+1, 1, 1);		// 답안 표시
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
	
	public void grade() {		// 채점하는 메소드
		while (count < questions.size()) {
			for (int i=0; i<words.size(); i++) {
				if (questions.get(count).trim().equals(words.get(i).getText().trim())) {
					if (answers.get(count).trim().equals(trans.get(i).getText().trim())) {
						corrects.add(new Label("정 답 !", Label.CENTER));
						corrects.get(corrects.size() - 1).setForeground(Color.BLUE);
						addGridBagConstraints(corrects.get(corrects.size() - 1), 3, count+1, 1, 1);
						hit++;
					} else if (!answers.get(count).trim().equals(trans.get(i).getText().trim())) {
						wrongs.add(new Label("오 답 !", Label.CENTER));
						wrongs.get(wrongs.size() - 1).setForeground(Color.RED);
						addGridBagConstraints(wrongs.get(wrongs.size() - 1), 3, count+1, 1, 1);
					}
				} else if (questions.get(count).trim().equals(trans.get(i).getText().trim())) {
					if (answers.get(count).trim().equals(words.get(i).getText().trim())) {
						corrects.add(new Label("정 답 !", Label.CENTER));
						corrects.get(corrects.size() - 1).setForeground(Color.BLUE);
						addGridBagConstraints(corrects.get(corrects.size() - 1), 3, count+1, 1, 1);
						hit++;
					} else if (!answers.get(count).trim().equals(words.get(i).getText().trim())) {
						System.out.println("오답!");
						wrongs.add(new Label("오 답 !", Label.CENTER));
						wrongs.get(wrongs.size() - 1).setForeground(Color.RED);
						addGridBagConstraints(wrongs.get(wrongs.size() - 1), 3, count+1, 1, 1);
					}
				}
				
			}
			count++;
		}
		
		south.setLayout(new FlowLayout());
		south.add(new Label("채점 결과 : " + hit + "/" + questions.size(), Label.CENTER));
		south.add(retry);
		south.add(reset);
	}
}