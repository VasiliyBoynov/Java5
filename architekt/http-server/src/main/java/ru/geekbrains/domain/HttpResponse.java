package ru.geekbrains.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpResponse
{
    private int statusCode;
    private String status;
    private Map<String, String> headers = new HashMap<>();

    public Map<String, String> getHeaders()
    {
        return Collections.unmodifiableMap(headers);
    }

    public static Builder createResponse(){
        return new Builder();
    }

    public static class Builder{
        private HttpResponse response;

        private Builder()
        {
            this.response = new HttpResponse();
        }

        public Builder withStatusCode(int statusCode){
            this.response.statusCode = statusCode;
            return this;
        }

        public Builder withStatus(String status){
            this.response.status = status;
            return this;
        }

        public HttpResponse build(){
            return response;
        }
    }
}
