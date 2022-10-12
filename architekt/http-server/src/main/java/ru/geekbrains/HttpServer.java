package ru.geekbrains;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer
{
	private static String WWW = "/Users/19583347/GeekBrains/architekt/www";
	private static final LogHelper Log = LogHelper.getInstance();

	public static void main(String[] args) {
		try (ServerSocket serverSocket = ServerSocketFactory.getInstance())
		{
			while (true)
			{
				Socket socket = serverSocket.accept();

				Log.info("New client connected!");
				ExecuterHelper.getInstance().execute(socket, WWW);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


}
