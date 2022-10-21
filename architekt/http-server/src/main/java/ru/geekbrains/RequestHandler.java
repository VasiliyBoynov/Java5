package ru.geekbrains;

import ru.geekbrains.Parser.RequestParser;
import ru.geekbrains.Parser.SimpleParser;
import ru.geekbrains.Serializer.ResponseSerializer;
import ru.geekbrains.Serializer.SimpleSerializer;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.logger.ConsoleLogger;
import ru.geekbrains.logger.Logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class RequestHandler implements Runnable {

    private static final String WWW = "/Users/aleks/dev/geek-architecture-123/www";

    private static final Logger logger = ConsoleLogger.getInstance();

    private final SocketService socketService;

    public RequestHandler(SocketService socketService) {
        this.socketService = socketService;
    }

    private final RequestParser parser = SimpleParser.getInstance();
    private final ResponseSerializer serializer = SimpleSerializer.getInstance();

    @Override
    public void run() {

        HttpRequest httpRequest = parser.parse(socketService.readRequest());

        Path path = Paths.get(WWW, httpRequest.getPath());

        if (!Files.exists(path)) {
            HttpResponse response = HttpResponse.createResponse()
                .withStatus("NOT_FOUND")
                .withStatusCode(404)
                .build();
            socketService.writeResponse(serializer.serialize(response),
                 new StringReader("<h1>Файл не найден!</h1>\n")
            );
            return;
        }

        try {
            HttpResponse response = HttpResponse.createResponse()
                .withStatus("OK")
                .withStatusCode(200)
                .build();
            socketService.writeResponse(serializer.serialize(response),
                    Files.newBufferedReader(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger.info("Client disconnected!");
    }
}
