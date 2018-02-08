package ru.unlimit.javapro.patterns;

public class InterpreterApp {

	public static void main(String[] args) {

		//"1-2+3"
		Context context = new Context();
		Expression expr = context.evaluate("-1+2+3");
		System.out.println(expr.interpret());

	}
}

interface Expression{
	int interpret();
}

class NumberExpression implements Expression{
	int number;
	public NumberExpression(int number) {
		this.number = number;
	}
	public int interpret() {
		return number;
	}	
}
class MinusExpression implements Expression{
	Expression left;
	Expression right;
	public MinusExpression(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	public int interpret() {
		return left.interpret() - right.interpret();
	}
}
class PlusExpression implements Expression{
	Expression left;
	Expression right;
	public PlusExpression(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	public int interpret() {
		return left.interpret() + right.interpret();
	}
}

class Context{
	Expression evaluate(String s){
		int pos = s.length()-1;
		while(pos>0){
			if(Character.isDigit(s.charAt(pos))){
				pos--;
			}
			else{
				Expression left = evaluate(s.substring(0, pos));
				Expression right = new NumberExpression(Integer.valueOf(s.substring(pos+1, s.length())));
				char operator = s.charAt(pos);
				switch(operator){
					case '-': return new MinusExpression(left, right);
					case '+': return new PlusExpression(left, right); 					
				}
			}
		}
		int result = Integer.valueOf(s);
		return new NumberExpression(result);
	}
}
