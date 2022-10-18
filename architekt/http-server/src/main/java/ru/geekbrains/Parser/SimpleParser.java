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
		HttpRequest request = new HttpRequest();
		try
		{
			request.setMethod(
				Arrays.stream(Metod.values())
					.map(Enum::name)
					.filter(m -> rawRequest.get(0).startsWith(m))
					.findFirst()
					.orElseThrow(IllegalArgumentException::new));

			request.setPath(Arrays.stream(rawRequest.get(0).split(" "))
				.skip(1L)
				.findFirst()
				.orElseThrow(IllegalArgumentException::new));

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

			request.setHeaders(map);

			if (request.getPath().contains("?"))
			{
				Map<String, String> param = new HashMap<>();
				Arrays.stream(request.getPath().split("\\?")).skip(1L).findFirst().ifPresent(str -> {
					for (String s : str.split("&"))
					{
						String[] sArray = s.split("=");
						param.put(sArray[0], sArray[1]);
					}
					request.setParameters(param);
				});
			}
		}
		catch (Exception e)
		{
			Log.info(e.getMessage());
			Log.info("Не возможно преобразовать в HTTP запрос");
			return null;
		}
		return request;
	}
}
