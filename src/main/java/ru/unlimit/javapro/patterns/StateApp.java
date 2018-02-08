package ru.unlimit.javapro.patterns;
public class StateApp {
	public static void main(String[] args) {

//		Station dfm = new RadioDFM();
//		Radio radio = new Radio();
//		radio.setStation(dfm);
//		
//		for(int i=0; i<10;i++){
//			radio.play();
//			radio.nextStation();
//		}	
		
		Humann human = new Humann();
		human.setState(new Work());
		for(int i=0;i<10;i++){
			human.doSomething();
		}		
	}
}

//Context
class Humann{
	private Activity state;
	public void setState(Activity s){this.state = s;}
	
	public void doSomething(){
		state.doSomething(this);
	}
}
//State
interface Activity{
	void doSomething(Humann humann);
}
class Work implements Activity{
	public void doSomething(Humann context) {
		System.out.println("Работаем!!!");
		context.setState(new WeekEnd());
	}
}
class WeekEnd implements Activity{
	private int count = 0;
	public void doSomething(Humann context) {
		if(count<3){
			System.out.println("Отдыхаем (Zzz)");
			count++;
			//context.setState(this);
		}
		else{
			context.setState(new Work());
		}
	}	
}


//State
interface Station{
	void play();
}
class Radio7 implements Station{
	public void play() {System.out.println("Радио 7...");}
}
class RadioDFM implements Station{
	public void play() {System.out.println("Радио DFM...");}
}
class VestiFM implements Station{
	public void play() {System.out.println("Вести FM...");}
}

//Context
class Radio{
	Station station;
	void setStation(Station st){station = st;}
	void nextStation(){
		if(station instanceof Radio7){
			setStation(new RadioDFM());
		}
		else if(station instanceof RadioDFM){
			setStation(new VestiFM());
		}
		else if(station instanceof VestiFM){
			setStation(new Radio7());
		}
	}
	void play(){
		station.play();
	}
}