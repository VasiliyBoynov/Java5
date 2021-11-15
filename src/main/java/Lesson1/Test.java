package Lesson1;

import Lesson1.Figure.Circuts;
import Lesson1.Figure.Figure;
import Lesson1.Figure.Rectangle;
import Lesson1.Figure.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test
{
	public static void main(String[] args)
	{
		task1();
		task3();

	}
	public static void task1(){
		System.out.println("__________________");
		Person person = new Person.Builder()
			.addFirstName("Test")
			.addLastName("TestTest")
			.build();
		System.out.println(person);
	}
	public static void task3(){
		System.out.println("__________________");
		Rectangle rec = new Rectangle(1.0,2.0);
		Square sq = new Square(1.0);
		Circuts cir = new Circuts(1.0);
		List<Double> arreys = Stream.of(rec,sq,cir).map(s->s.findArea()).collect(Collectors.toList());
		for (Double arrey : arreys)
		{
			System.out.println(arrey);
		}
	}
	public static void task2(){
		System.out.println("абстрактная задача");
	}
}
