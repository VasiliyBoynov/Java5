package Lesson1.Figure;

public class Rectangle implements Figure
{
	private Double a;
	private Double b;

	public Rectangle(Double a, Double b)
	{
		this.a = a;
		this.b = b;
	}

	public Double getA()
	{
		return a;
	}

	public void setA(Double a)
	{
		this.a = a;
	}

	public Double getB()
	{
		return b;
	}

	public void setB(Double b)
	{
		this.b = b;
	}

	@Override
	public double findArea()
	{
		return (double) (a*b);
	}
}
