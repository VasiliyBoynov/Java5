package ru.geekbrains;

import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.net.ServerSocket;

@UtilityClass
public class ServerSocketFactory
{
	public static final int PORT = 8080;

	private final ServerSocket INSTANCE = getServerSocket();

	private static ServerSocket getServerSocket()
	{
		LogHelper Log = LogHelper.getInstance();

		try
		{
			ServerSocket serverSocket = new ServerSocket(PORT);
			Log.info("Server started!");
			return serverSocket;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		Log.info("Server didn't start!");
		System.exit(0);
		return null;
	}

	public static ServerSocket getInstance()
	{
		return INSTANCE;
	}
}
