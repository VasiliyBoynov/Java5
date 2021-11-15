package Lesson1.Figure;

public class Circuts implements Figure
{
	private double a;

	public double getA()
	{
		return a;
	}

	public void setA(double a)
	{
		this.a = a;
	}

	public Circuts(double a)
	{
		this.a = a;
	}

	@Override
	public double findArea()
	{
		return Math.PI*(Math.pow(a,2.0));
	}
}
