package ru.geekbrains;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExecuterHelper
{
	private static final LogHelper Log = LogHelper.getInstance();
	private static final ExecuterHelper INSTANCE = new ExecuterHelper();

	private ExecutorService service = Executors.newFixedThreadPool(10);

	public void execute(Socket socket, String WWW)
	{
		service.submit(() -> handleRequest(socket, WWW));
	}

	public void shutdown()
	{
		service.shutdown();
	}

	private void handleRequest(Socket socket, String WWW)
	{
		Log.info("Start validate request");
		try (BufferedReader input = new BufferedReader(
			new InputStreamReader(
				socket.getInputStream(), StandardCharsets.UTF_8));
		     PrintWriter output = new PrintWriter(socket.getOutputStream())
		) {
			while (!input.ready());

			String firstLine = input.readLine();
			String[] parts = firstLine.split(" ");
			System.out.println(firstLine);
			while (input.ready()) {
				System.out.println(input.readLine());
			}

			Path path = Paths.get(WWW, parts[1]);
			if (!Files.exists(path)) {
				output.println("HTTP/1.1 404 NOT_FOUND");
				output.println("Content-Type: text/html; charset=utf-8");
				output.println();
				output.println("<h1>Файл не найден!</h1>");
				output.flush();
				return;
			}

			output.println("HTTP/1.1 200 OK");
			output.println("Content-Type: text/html; charset=utf-8");
			output.println();

			BufferedReader reader = Files.newBufferedReader(path);
			String line;
			while ((line = reader.readLine()) != null)
			{
				output.println(line);
			}


			Log.info("Client disconnected!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static ExecuterHelper getInstance()
	{
		return INSTANCE;
	}
}
