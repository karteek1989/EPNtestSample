import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.apache.commons.lang.StringUtils;

public class Example {

	public static void main(String a[]) {
		int ex = 0;
		String string = "( + 3 ( - 2  ) (/ 6 3) 6 7)"; //dynamic
		Example e = new Example(); 
		System.out.println("Final result after calculate is: " + e.calculate(string));
	}
	
	public double calculate(String string) {
		if(!StringUtils.isBlank(string)) {
			string = string.replaceAll("\\s+","");
			double result = 0;
			
			Stack<String> st = new Stack<String>();
			
			for(int i=1; i<string.length(); i++) {
				if(string.charAt(i) != ')') {
					st.push(String.valueOf(string.charAt(i)));
				}
				else {
					List<String> l1 = new ArrayList<String>();
						String operand="";
						while(!st.isEmpty()) {
							String pop = st.pop();
							if(!(pop.equals("("))) {
								if(!(isNumeric(pop)))
									operand = pop;
								else 
									l1.add(pop);
							}
							else 
								break;
								
						}
						if(l1 != null && !l1.isEmpty())
						switch (operand.charAt(0)) {
						case '+' : result = Double.parseDouble(l1.get(l1.size()-1));
								   for(int j=l1.size()-2; j >= 0; j--) 
								   result+= Double.parseDouble(l1.get(j)); 
								   break;
						case '*' : result = Double.parseDouble(l1.get(l1.size()-1));
								   for(int j=l1.size()-2; j >= 0; j--) 
								   result*= Double.parseDouble(l1.get(j)); 
							       break;
						case '-' : result = Double.parseDouble(l1.get(l1.size()-1));
								   for(int j=l1.size()-2; j >=0; j--) { 
						   		   result-= Double.parseDouble(l1.get(j)); } 
							       break;
						case '/' : result = Double.parseDouble(l1.get(l1.size()-1)); 
								   for(int j=l1.size()-2; j >= 0; j--)  
								   result/=  Double.parseDouble(l1.get(j)) ;  
							       break;
						default  : break;
				}
				st.push(String.valueOf(result));
			}
			
		}
			return result;
		}
		
		return 0;
	}
	
	public boolean isNumeric(String s) {
		 return java.util.regex.Pattern.matches("(\\+|-)?\\d+", s) || java.util.regex.Pattern.matches("(\\+|-)?([0-9]*)\\.([0-9]*)", s);
	}
}