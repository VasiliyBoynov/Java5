package Lesson3;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lesson3
{
	public static void main(String[] args)
	{
		task1();
	}

	public static void task1(){
		AtomicBoolean state = new AtomicBoolean(true);
		new Thread(() -> {
			while (true)
			{
				if (state.get())
				{
					System.out.println("ping");
					state.set(false);
				}
			}
		}).start();
		new Thread(() -> {
			while (true)
			{
				if (!state.get())
				{
					System.out.println("pong");
					state.set(true);
				}
			}
		}).start();
	};
}

class  Counter{
	private final Lock lock = new ReentrantLock();
	private int value = 0;

	public int getValue() {
		return value;
	}

	public void add(int n){
		lock.lock();
		value +=n;
		lock.unlock();
	}

}
