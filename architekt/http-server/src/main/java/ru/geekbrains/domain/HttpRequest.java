package ru.geekbrains.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class HttpRequest {

    private String method;

    private String path;

    private Map<String, String> headers;

    private String body;

    private Map<String, String> parameters;
}
