package ru.geekbrains.Parser;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.logger.ConsoleLogger;
import ru.geekbrains.logger.Logger;

import java.util.*;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleParser implements RequestParser
{
	private Logger Log = ConsoleLogger.getInstance();
	private static SimpleParser INSTANCE = new SimpleParser();

	public static SimpleParser getInstance()
	{
		return INSTANCE;
	}

	@Override
	public HttpRequest parse(List<String> rawRequest)
	{
		Log.info("Старт преобразования запроса");

		try
		{
			String method = Arrays.stream(Metod.values())
				.map(Enum::name)
				.filter(m -> rawRequest.get(0).startsWith(m))
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);

			String path = Arrays.stream(rawRequest.get(0).split(" "))
				.skip(1L)
				.findFirst()
				.orElseThrow(IllegalArgumentException::new);

			Map<String, String> map = new HashMap<>();

			rawRequest.stream()
				.filter(str -> str.contains(": "))
				.forEach(s -> {
					String[] str = s.split(": ");
					if (str[0] != null)
					{
						map.put(str[0], str[1]);
					}
				});

			HttpRequest request = HttpRequest.createRequest()
				.withMethod(method)
				.withPath(path)
				.withHeaders(map)
				.build();

			return request;
		}
		catch (Exception e)
		{
			Log.info(e.getMessage());
			Log.info("Не возможно преобразовать в HTTP запрос");
			return null;
		}

	}
}
