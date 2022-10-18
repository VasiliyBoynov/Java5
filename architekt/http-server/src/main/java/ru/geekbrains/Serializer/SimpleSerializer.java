package ru.geekbrains.Serializer;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.logger.ConsoleLogger;
import ru.geekbrains.logger.Logger;

import java.io.BufferedReader;
import java.util.Map;
import java.util.Optional;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SimpleSerializer implements ResponseSerializer
{
	private static final SimpleSerializer INSTANCE = new SimpleSerializer();

	private Logger Log = ConsoleLogger.getInstance();

	public static final String CONTENT_TYPE = "Content-Type";

	@Override
	public String serialize(HttpResponse httpResponse)
	{
		StringBuilder stringBuilder = new StringBuilder();
		Map<String, String> headers = httpResponse.getHeaders();

		stringBuilder.append(String.format("HTTP/1.1 %s %s\n", httpResponse.getStatusCode(), httpResponse.getStatus()));
		stringBuilder.append(String.format("%s: %s\n", CONTENT_TYPE, Optional.ofNullable(headers.get(CONTENT_TYPE)).orElse("text/html; charset=utf-8")));
		stringBuilder.append("\n");
		headers.keySet().stream()
			.filter(key -> !key.equals(CONTENT_TYPE))
			.forEach(key -> {
			stringBuilder.append(String.format("%s: %s\n", key, headers.get(key)));
		});

		return stringBuilder.toString();


	}

	public static SimpleSerializer getInstance()
	{
		return INSTANCE;
	}
}
