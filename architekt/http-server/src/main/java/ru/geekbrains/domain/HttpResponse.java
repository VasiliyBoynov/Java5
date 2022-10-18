package ru.geekbrains.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Reader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class HttpResponse
{
    private int statusCode;
    private String status;
    @Setter(AccessLevel.PRIVATE)
    private Map<String, String> headers = new HashMap<>();

    public void putHeaders(String key, String value){
        headers.put(key, value);
    }

    public Map<String, String> getHeaders()
    {
        return Collections.unmodifiableMap(headers);
    }
}
