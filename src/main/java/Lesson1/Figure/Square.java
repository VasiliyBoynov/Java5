package Lesson1.Figure;

public class Square implements Figure
{
	private double a;

	public Square(double a)
	{
		this.a = a;
	}

	public double getA()
	{
		return a;
	}

	public void setA(double a)
	{
		this.a = a;
	}

	@Override
	public double findArea()
	{
		return (a*a);
	}
}
