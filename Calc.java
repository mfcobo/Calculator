
import java.awt.*;       // Using AWT layouts
import java.awt.event.*; // Using AWT event classes and listener interfaces
import javax.swing.*;    // Using Swing components and containers
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.StringTokenizer;
import java.math.RoundingMode;
/**
 * Created by mfcobo on 10/28/16.
 */

public class Calc extends JFrame{
	private ArrayList<String> text;
	private Container cp1;
	private Container cp2;//
 	private JTextField txtField;
 	private JButton allClear;
 	private JButton	clear;
 	private JButton negate;
 	private JButton percentage;
 	private JButton divide;
 	private JButton multiply;
 	private JButton minus;
 	private JButton add;
 	private JButton zero;
 	private JButton one;
 	private JButton two;
 	private JButton three;
 	private JButton four;
 	private JButton five;
 	private JButton six;
 	private JButton seven;
 	private JButton eight;
 	private JButton nine;
 	private JButton period;
 	private JButton equal;
 	
 	public static void main(String[] args){
 		new Calc();
 	}

 	public Calc(){
 	//ActionListener
        ActionListener listnr = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event){
                action(event);
            }
        };
 	text = new ArrayList<String>();
 	//Containers
 		cp1 = getContentPane();
 		cp1.setLayout(new FlowLayout());
 		cp2 = new JPanel(new GridLayout(5,4,0,0));
 	//initialization of TextField
 		txtField = new JTextField("0",23);
 		txtField.setEditable(false);
 		txtField.setPreferredSize(new Dimension(260, 60));
 		txtField.addActionListener(listnr);
 	//initialization of buttons
 		allClear = new JButton("AC");allClear.addActionListener(listnr);	
 		clear = new JButton("C");clear.addActionListener(listnr);
 		negate = new JButton("+/-");negate.addActionListener(listnr);
 		percentage = new JButton("%");percentage.addActionListener(listnr);
 		divide = new JButton("/");divide.addActionListener(listnr);
 		multiply = new JButton("x");multiply.addActionListener(listnr);
 		minus = new JButton("-");minus.addActionListener(listnr);
 		add = new JButton("+");add.addActionListener(listnr);
 		one = new JButton("1");one.addActionListener(listnr);
 		two = new JButton("2");two.addActionListener(listnr);
 		three = new JButton("3");three.addActionListener(listnr);
 		four = new JButton("4");four.addActionListener(listnr);
 		five = new JButton("5");five.addActionListener(listnr);
 		six = new JButton("6");six.addActionListener(listnr);
 		seven = new JButton("7");seven.addActionListener(listnr);
 		eight = new JButton("8");eight.addActionListener(listnr);
 		nine = new JButton("9");nine.addActionListener(listnr);
 		zero = new JButton("0");zero.addActionListener(listnr);
 		period = new JButton(".");period.addActionListener(listnr);
 		equal = new JButton("=");equal.addActionListener(listnr);
 		clear.setPreferredSize(new Dimension(65, 60));
 	//adding components to containers
	 	cp1.add(txtField);
	 	cp2.add(allClear);
	 	cp2.add(clear);
	 	cp2.add(negate);
	 	cp2.add(percentage);
	 	cp2.add(seven);
	 	cp2.add(eight);
	 	cp2.add(nine);
	 	cp2.add(divide);
	 	cp2.add(four);
	 	cp2.add(five);
	 	cp2.add(six);
	 	cp2.add(multiply);
	 	cp2.add(one);
	 	cp2.add(two);
	 	cp2.add(three);
	 	cp2.add(minus);
	 	cp2.add(zero);
	 	cp2.add(period);
	 	cp2.add(equal);
	 	cp2.add(add);
 	
        //txtField.addActionListener(listnr);
        add(cp2);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit program if close-window button clicked
	    setTitle("Calculator"); // "super" JFrame sets title
	    setSize(280, 410);         // "super" JFrame sets initial size
	    setVisible(true);          // "super" JFrame shows
	    JOptionPane.showMessageDialog(this,"Developer : Mark Louis F. Cobo\n" + 
	    	"Student Num : 2015 - 04638","Calculator v1.0", JOptionPane.YES_NO_OPTION);

	}

	public void display(){
		if (text.isEmpty()) {
			txtField.setText("0");
        }
        else{
        	String temp = "";
        	for(String s: text){
        		temp += s;
        	}
        	txtField.setText(temp);
        }
	}

	public void operation(String operate){
		if(text.isEmpty()){
			if(operate.equals("-")){
				text.add(operate);
			}
		}

		else if(isOperation(text.get(text.size()-1))){
			if(text.get(text.size()-1).equals("-") && isOperation(text.get(text.size()-2))){
				text.remove(text.size()-2);
				text.add(text.size()-1,operate);
			}
		else if((text.get(text.size()-1).equals("x") || text.get(text.size()-1).equals("/") ||  text.get(text.size()-1).equals("%")) && operate.equals("-")){
				text.add(operate);
			}
			else{
				text.remove(text.size()-1);
				text.add(operate);
			}
		}
		else{
				text.add(operate);
		}
	}

	public boolean isOperation(String s){
		return (s.equals("+") || s.equals("-") || s.equals("x") || s.equals("/") || s.equals("%"));
	}
	
	public void negatation(String s){
		int i = text.size();
		if(i == 0){
			text.add("-");
			text.add("0");
		}
		else{
			i--;
			while(true){
					if(i == -1){
						text.add(0,"-");
						break;
					}
					if(isOperation(text.get(i))){
						if(text.get(i).equals("-")){
							text.remove(i);
							text.add("+");
						}
						else{
							if(text.get(i).equals("+"))
								text.remove(i);
							else{
								text.add("-");
							}
						}
						break;
					}
				i--;	
			}
		}
	}

	public void checkDot(){
		int i = text.size();
		if(text.isEmpty()){
			text.add("0");
			text.add(".");
		}
		else{
			i--;
			int ctr = 0;
			while(i >= 0){
				if(isOperation(text.get(i))){
					break;
				}
				if(text.get(i).equals(".")){
					ctr++;
				}
				i--;
			}
			if(ctr == 0){
				text.add(".");
			}
		}
	}

	public void compute(){
		for(int i = 0 ; i < text.size(); i++){
			if(isOperation(text.get(i))){
				text.add(i," ");
				text.add(i+2, " ");
				if( i+3 < text.size()){
					if(text.get(i+3).equals("-")){
						i+=3;
					}
					else{
						i+=2;
					}
				}
				else{
					i+=2;
				}
			}
		}
		String s = "";
		for(String tmp : text){
			s += tmp;
		}
		solve(s);
	}

	public void solve(String s){
		ArrayList<String> result = new ArrayList<>();
        StringTokenizer strtok = new StringTokenizer(s," ");
        while (strtok.hasMoreTokens()) {
            result.add(strtok.nextToken());
        }
        BigDecimal	partialAns	 = new BigDecimal(result.get(0));
        for(int i = 1; i+1 < result.size() ; i++){
        	if(isOperation(result.get(i)) && isOperation(result.get(i+1))){
        		break;
        	}
        	String operand = result.get(i);
	        	if(operand.equals("+")){
	        		partialAns = partialAns.add(new BigDecimal(result.get(++i)));
	        	}
	        	else if(operand.equals("-")){
	        		partialAns = partialAns.subtract(new BigDecimal(result.get(++i)));
	        	}
	        	else if(operand.equals("x")){
	        		partialAns = partialAns.multiply(new BigDecimal(result.get(++i)));
	        	}
	        	else if(operand.equals("%")){
	        		partialAns = partialAns.divide(new BigDecimal("100"),2, RoundingMode.HALF_EVEN);
	        			partialAns = partialAns.multiply(new BigDecimal(result.get(++i)));		
	        	}
	         	else{
	         		if(result.get(i+1).equals("0")){
	         			text.clear();
	         			text.add("ERR");
	         			return;
	         		}
	         		else{
	         			partialAns = partialAns.divide(new BigDecimal(result.get(++i)),2, RoundingMode.HALF_EVEN);
	         		}	
	        	}
        }
        text.clear();
        text.add(partialAns.stripTrailingZeros().toPlainString() + "");
	}
 	public void action(ActionEvent event) {
    	JButton button = (JButton) event.getSource();
    	if(button.equals(equal)){
    		compute();
    	}
    	else if(button.equals(clear)){
    		if(!(text.isEmpty())){
    				text.remove(text.size()-1);		
    		}
    	}
    	else if(button.equals(allClear)){
    		text.clear();
    	}
    	else if(button.equals(period)){
    		checkDot();
    	}
    	else if(button.equals(negate)){
    		negatation(button.getText());
    	}
    	else if(button.equals(add) || button.equals(minus) || button.equals(multiply) ||
                button.equals(divide) || button.equals(percentage)){
    		operation(button.getText());
    	}
    	else{
    		text.add(button.getText());
    	}
    	display();
    }
 }