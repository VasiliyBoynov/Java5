package ru.geekbrains;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 *
 * Класс логирование событий
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LogHelper
{
	private static LogHelper INSTANCE = new LogHelper();
	final public static String NAME_SERVICE = "HTTP-Service : ";

	public static LogHelper getInstance()
	{
		return INSTANCE;
	}

	public void info(String message)
	{
		System.out.println(NAME_SERVICE + message);
	}
}
