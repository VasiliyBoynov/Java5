package ru.geekbrains.Serializer;

import ru.geekbrains.domain.HttpResponse;

import java.io.IOException;

public interface ResponseSerializer {

    String serialize(HttpResponse httpResponse) ;
}
