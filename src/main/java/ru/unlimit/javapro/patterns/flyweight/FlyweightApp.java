package ru.unlimit.javapro.patterns.flyweight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class FlyweightApp {

	public static void main(String[] args) {
		
		ShapeFactory shapeFactory = new ShapeFactory();
		
		List<Shape> shapes = new ArrayList<>();
		
		shapes.add(shapeFactory.getShape("квадрат"));
		shapes.add(shapeFactory.getShape("круг"));
		shapes.add(shapeFactory.getShape("круг"));
		shapes.add(shapeFactory.getShape("точка"));
		shapes.add(shapeFactory.getShape("квадрат"));
		shapes.add(shapeFactory.getShape("круг"));
		
		Random rand = new Random();
		for(Shape shape : shapes){
			int x = rand.nextInt(100);
			int y = rand.nextInt(100);
			shape.draw(x,y);
		}


	}
}

//Flyweight
interface Shape {
	void draw(int x, int y);
}

//Point Flyweight
class Point implements Shape {
	public void draw(int x, int y) {System.out.println("("+x+","+y+"): рисуем точку ");}
}

//Cicrle Flyweight
class Circle implements Shape {
	int r = 5;
	public void draw(int x, int y) {System.out.println("("+x+","+y+"): рисуем круг радиусом "+r);}
}

//Square Flyweight
class Square implements Shape {
	int a=10;
	public void draw(int x, int y) {System.out.println("("+x+","+y+"): рисуем квадрат со стороной "+a);}
}

class ShapeFactory{
	private static final Map<String, Shape> shapes = new HashMap<>();
	public Shape getShape(String shapeName){
		
		Shape shape = shapes.get(shapeName);
		if(shape==null){
			switch(shapeName){
				case "круг":
					shape = new Circle();
					break;
				case "квадрат":
					shape = new Square();
					break;
				case "точка":
					shape = new Point();
					break;
			}
			shapes.put(shapeName, shape);
		}
		return shape;
	}
}
