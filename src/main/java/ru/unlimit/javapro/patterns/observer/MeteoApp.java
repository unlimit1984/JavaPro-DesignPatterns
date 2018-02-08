package ru.unlimit.javapro.patterns.observer;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MeteoApp {
	public static void main(String[] args) {
		MeteoStation station = new MeteoStation();
		
		station.addObserver(new ConsoleObserver());
		station.addObserver(new FileObserver());
		
		station.setMeasurements(25, 760);
		station.setMeasurements(-5, 745);
		
	}
}
interface Observed{
	void addObserver(Observer o);
	void removeObserver(Observer o);
	void notifyObservers();
}
class MeteoStation implements Observed{
	int temperature;//1.Наблюдаемый имеет и меняет свое состояние и посылает это изменение своим подписчикам, а 
	int pressure;//а медиатор нет

	List<Observer> observers = new ArrayList<>();//2. имеет коллекцию подписчиков и не делает различий между ними
	
	public void setMeasurements(int t, int p){//3. Наблюдаемый тупо рассылает свое изменившееся состояние подписчикам
		temperature = t;
		pressure = p;
		notifyObservers();
	}
	
	public void addObserver(Observer o) {
		observers.add(o);
	}
	public void removeObserver(Observer o) {
		observers.remove(o);
	}
	public void notifyObservers() {
		for(Observer o: observers){
			o.handleEvent(temperature, pressure);
		}	
	}
}

interface Observer{
	void handleEvent(int temp, int presser);//4. Наблюдатели только получают сообщения, отправлять не могут
}

class ConsoleObserver implements Observer{
	public void handleEvent(int temp, int presser) {
		System.out.println("Погода изменилась. Температура = " + temp + ", Давление = " + presser +".");
	}
}
class FileObserver implements Observer{
	public void handleEvent(int temp, int presser) {
		File f;
		try {
			f = File.createTempFile("TempPressure", "_txt");
			PrintWriter pw = new PrintWriter(f);
			pw.print("Погода изменилась. Температура = " + temp + ", Давление = " + presser +".");
			pw.println();
			pw.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
