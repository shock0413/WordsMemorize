import java.awt.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Panel;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;


public class Calculator extends Frame implements WindowListener, ActionListener {
	private TextField textField = new TextField();
	private Panel p1 = new Panel();
	private Panel p2 = new Panel();
	private GridBagConstraints constraints;
	private Button ce = new Button("CE");
	private Button c = new Button("C");
	private Button backspace = new Button("←");
	private Button div = new Button("÷");
	private Button seven = new Button("7");
	private Button eight = new Button("8");
	private Button nine = new Button("9");
	private Button multi = new Button("×");
	private Button four = new Button("4");
	private Button five = new Button("5");
	private Button six = new Button("6");
	private Button minus = new Button("-");
	private Button one = new Button("1");
	private Button two = new Button("2");
	private Button three = new Button("3");
	private Button plus = new Button("+");
	private Button zero = new Button("0");
	private Button point = new Button(".");
	private Button result = new Button("=");
	private Button symhol = new Button("±");
	private Font font;
	private int count=0;												// 식에 대한 인덱스
	public String expr = "";											// 식을 입력 받기 위한 문자열
	private String temp;												// 두 번째 숫자를 입력 후 '='를 눌렀을 때 두번째 숫자를 저장
	
	public Calculator() {
		init();
		addListeners();
	}
	
	public Calculator(Point location) {
		init();
		setLocation(location);
		addListeners();
	}
	
	public void init() {													// 프레임 창 초기화
		setTitle("계산기");
		setSize(300,400);
		setResizable(false);
		setLayout(new GridBagLayout());
		constraints = new GridBagConstraints();
		constraints.weightx = 1.0;
		constraints.weighty = 1.0;
		constraints.fill = GridBagConstraints.BOTH;
		font = new Font("Font", Font.BOLD, 30);
		textField.setFocusable(false);
		textField.setFont(font);
		textField.setText("0");
		add(textField,0,0,0,2);
		add(ce,0,2,1,1);
		add(c,1,2,1,1);
		add(backspace,2,2,1,1);
		add(div,3,2,1,1);
		font = new Font("Font", Font.BOLD, 15);
		zero.setFont(font);
		zero.setBackground(Color.WHITE);
		one.setFont(font);
		one.setBackground(Color.WHITE);
		two.setFont(font);
		two.setBackground(Color.WHITE);
		three.setFont(font);
		three.setBackground(Color.WHITE);
		four.setFont(font);
		four.setBackground(Color.WHITE);
		five.setFont(font);
		five.setBackground(Color.WHITE);
		six.setFont(font);
		six.setBackground(Color.WHITE);
		seven.setFont(font);
		seven.setBackground(Color.WHITE);
		eight.setFont(font);
		eight.setBackground(Color.WHITE);
		nine.setFont(font);
		nine.setBackground(Color.WHITE);
		point.setFont(font);
		font = new Font("Font", Font.PLAIN, 15);
		ce.setFont(font);
		c.setFont(font);
		backspace.setFont(font);
		div.setFont(font);
		multi.setFont(font);
		minus.setFont(font);
		plus.setFont(font);
		result.setFont(font);
		add(seven,0,3,1,1);
		add(eight,1,3,1,1);
		add(nine,2,3,1,1);
		add(multi,3,3,1,1);
		add(four,0,4,1,1);
		add(five,1,4,1,1);
		add(six,2,4,1,1);
		add(minus,3,4,1,1);
		add(one,0,5,1,1);
		add(two,1,5,1,1);
		add(three,2,5,1,1);
		add(plus,3,5,1,1);
		add(symhol,0,6,1,1);
		add(zero,1,6,1,1);
		add(point,2,6,1,1);
		add(result,3,6,1,1);
		setVisible(true);
	}
	
	public void addListeners() {										// 이벤트 처리를 위한 리스너 추가
		addWindowListener(this);
		ce.addActionListener(this);
		c.addActionListener(this);
		zero.addActionListener(this);
		one.addActionListener(this);
		two.addActionListener(this);
		three.addActionListener(this);
		four.addActionListener(this);
		five.addActionListener(this);
		six.addActionListener(this);
		seven.addActionListener(this);
		eight.addActionListener(this);
		nine.addActionListener(this);
		point.addActionListener(this);
		backspace.addActionListener(this);
		div.addActionListener(this);
		multi.addActionListener(this);
		plus.addActionListener(this);
		minus.addActionListener(this);
		symhol.addActionListener(this);
		result.addActionListener(this);
	}
	
	public void add(Component comp, int x, int y, int width, int height) {	// GridBagLayout에 넣을 위치, 차지 칸 설정
		constraints.gridx=x;
		constraints.gridy=y;
		constraints.gridwidth=width;
		constraints.gridheight=height;
		add(comp, constraints);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == zero) {
			insert(zero);
		} else if (e.getSource() == one) {
			insert(one);
		} else if (e.getSource() == two) {
			insert(two);
		} else if (e.getSource() == three) {
			insert(three);
		}  else if (e.getSource() == four) {
			insert(four);
		}  else if (e.getSource() == five) {
			insert(five);
		}  else if (e.getSource() == six) {
			insert(six);
		} else if (e.getSource() == seven) {
			insert(seven);
		} else if (e.getSource() == eight) {
			insert(eight);
		} else if (e.getSource() == nine) {
			insert(nine);
		} else if (e.getSource() == point) {
			textField.setText(textField.getText()+point.getLabel());
		} else if (e.getSource() == plus) {
			result(plus);
		} else if (e.getSource() == minus) {
			result(minus);
		} else if (e.getSource() == multi) {
			result(multi);
		} else if (e.getSource() == div) {
			result(div);
		} else if (e.getSource() == result) {
			result(result);
		} else if (e.getSource() == symhol) {
			if (Float.parseFloat(textField.getText()) != 0) {
				if (!textField.getText().startsWith("-")) {
					textField.setText("-"+textField.getText());
				} else {
					textField.setText(textField.getText().substring(1, textField.getText().length()));
				}
			}
		} else if (e.getSource() == backspace) {
			if (textField.getText().length() == 1) {
				textField.setText("0");
			} else {
				textField.setText(textField.getText().substring(0, textField.getText().length()-1));
			}
		} else if (e.getSource() == ce) {
			textField.setText("0");
		} else if (e.getSource() == c) {
			textField.setText("0");
			expr = "";
			count=0;
			temp = null;
		}
	}
	
	public void insert(Button obj) {													// 계산할 숫자를 클릭하면 삽입시켜주는 메소드
		if (count==0) {
			if (textField.getText().equals("0")) {
				textField.setText(obj.getLabel());
			} else {
				textField.setText(textField.getText()+obj.getLabel());
			}
		} else {
			temp = null;
			textField.setText("0");
			textField.setText(obj.getLabel());
			count=0;
		}
	}
	
	public void result(Button obj) {													// 계산 결과를 나타내기 위한 메소드
		if (expr.equals("")) {
			expr = textField.getText()+obj.getLabel();
			count=1;
		} else if (!expr.endsWith(obj.getLabel()) && obj != result) {
			if (count == 0) {
				expr += textField.getText();
				expr = calculate(expr);
			}
			expr = expr.substring(0, expr.length()-1) + obj.getLabel();
			System.out.println(expr);
		} else if (expr.endsWith(obj.getLabel()) && count == 1) {
			return;
		} else if (obj == result) {														// '='을 눌렀을 때
			if (expr != null && temp == null) {
				temp = textField.getText();
				expr += temp;
				expr = calculate(expr);
				System.out.println("temp:"+temp);
			}
			else {
				expr += temp;
				expr = calculate(expr);
			}
		} else{
			expr += textField.getText();
			expr = calculate(expr);
		}
	}
	
	public void operate(Button obj) {
		if (obj.getLabel().equals("+")) {
			
		} else if (obj.getLabel().equals("-")) {
			
		} else if (obj.getLabel().equals("×")) {
			
		} else if (obj.getLabel().equals("÷")) {
			
		}
	}
	
	public String calculate(String expr) {													// 계산 하기 위한 메소드
		if (expr.indexOf("+")>0) {															// 수식이 더하기일 경우 이외 모두 동일. 
			String num1 = expr.substring(0, expr.indexOf("+"));								// 수식에서 첫 숫자를 잘라냄.
			String num2 = expr.substring(expr.indexOf("+")+1, expr.length());				// 두번째 숫자를 잘라냄.
			if (num1.indexOf(".") > 0) {													// 첫번째 숫자에 소수점이 포함 되어 있다면
				if (num2.indexOf(".") > 0) {
					textField.setText(Float.parseFloat(num1)+Float.parseFloat(num2)+"");
					expr=textField.getText()+"+";
					count=1;
					return expr;
				}
				textField.setText(Float.parseFloat(num1)+Integer.parseInt(num2)+"");
				expr=textField.getText()+"+";
				count=1;
				return expr;
			} else {
				if (num2.indexOf(".") > 0) {												// 두번째 숫자에 소수점이 포함 되어 있다면
					textField.setText(Integer.parseInt(num1)+Float.parseFloat(num2)+"");
					expr=textField.getText()+"+";
					count=1;
					return expr;
				}
				textField.setText(Integer.parseInt(num1)+Integer.parseInt(num2)+"");
				expr=textField.getText()+"+";
				count=1;
				return expr;
			}
		} else if (expr.indexOf("-")>0) {
			String num1 = expr.substring(0, expr.indexOf("-"));
			String num2 = expr.substring(expr.indexOf("-")+1, expr.length());
			if (num1.indexOf(".") > 0) {
				if (num2.indexOf(".") > 0) {
					textField.setText(Float.parseFloat(num1)-Float.parseFloat(num2)+"");
					expr=textField.getText()+"-";
					count=1;
					return expr;
				}
				textField.setText(Float.parseFloat(num1)-Integer.parseInt(num2)+"");
				expr=textField.getText()+"-";
				count=1;
				return expr;
			} else {
				if (num2.indexOf(".") > 0) {
					textField.setText(Integer.parseInt(num1)-Float.parseFloat(num2)+"");
					expr=textField.getText()+"-";
					count=1;
					return expr;
				}
				textField.setText(Integer.parseInt(num1)-Integer.parseInt(num2)+"");
				expr=textField.getText()+"-";
				count=1;
				return expr;
			}
		} else if (expr.indexOf("×")>0) {
			String num1 = expr.substring(0, expr.indexOf("×"));
			String num2 = expr.substring(expr.indexOf("×")+1, expr.length());
			if (num1.indexOf(".") > 0) {
				if (num2.indexOf(".") > 0) {
					textField.setText(Float.parseFloat(num1)*Float.parseFloat(num2)+"");
					expr=textField.getText()+"×";
					count=1;
					return expr;
				}
				textField.setText(Float.parseFloat(num1)*Integer.parseInt(num2)+"");
				expr=textField.getText()+"×";
				count=1;
				return expr;
			} else {
				if (num2.indexOf(".") > 0) {
					textField.setText(Integer.parseInt(num1)*Float.parseFloat(num2)+"");
					expr=textField.getText()+"×";
					count=1;
					return expr;
				}
				textField.setText(Integer.parseInt(num1)*Integer.parseInt(num2)+"");
				expr=textField.getText()+"×";
				count=1;
				return expr;
			} 
		} else if (expr.indexOf("÷")>0) {
			String num1 = expr.substring(0, expr.indexOf("÷"));
			String num2 = expr.substring(expr.indexOf("÷")+1, expr.length());
			if (num1.indexOf(".") > 0) {
				if (num2.indexOf(".") > 0) {
					textField.setText(Float.parseFloat(num1)/Float.parseFloat(num2)+"");
					expr=textField.getText()+"÷";
					count=1;
					return expr;
				}
				textField.setText(Float.parseFloat(num1)/Integer.parseInt(num2)+"");
				expr=textField.getText()+"÷";
				count=1;
				return expr;
			} else {
				if (num2.indexOf(".") > 0) {
					textField.setText(Integer.parseInt(num1)/Float.parseFloat(num2)+"");
					expr=textField.getText()+"÷";
					count=1;
					return expr;
				}
				textField.setText(Integer.parseInt(num1)/Integer.parseInt(num2)+"");
				expr=textField.getText()+"÷";
				count=1;
				return expr;
			} 
		}
		return expr;
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {		// 윈도우 창에서 닫기 버튼을 누르면
		// TODO Auto-generated method stub
		dispose();												// 창을 닫고 종료됨.
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}