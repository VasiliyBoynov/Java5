package ru.geekbrains.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpRequest {

    private String method;

    private String path;

    private Map<String, String> headers;

    private String body;

    private Map<String, String> parameters;

    public Map<String, String> getHeaders(){
        return Collections.unmodifiableMap(headers);
    }

    public Map<String, String> getParameters(){
        return Collections.unmodifiableMap(parameters);
    }

    public static BuilderHttpRequest createRequest(){
        return new BuilderHttpRequest();
    }

    public static class BuilderHttpRequest{

        private HttpRequest request;

        private BuilderHttpRequest createRequest(){
            return new BuilderHttpRequest();
        }

        private BuilderHttpRequest()
        {
            this.request = new HttpRequest();
        }

        public BuilderHttpRequest withMethod(String method){
            this.request.method = method;
            return this;
        }

        public BuilderHttpRequest withPath(String path){
            this.request.path = path;

            if (request.getPath().contains("?"))
            {
                Map<String, String> param = new HashMap<>();
                Arrays.stream(request.getPath().split("\\?")).skip(1L).findFirst().ifPresent(str -> {
                    for (String s : str.split("&"))
                    {
                        String[] sArray = s.split("=");
                        param.put(sArray[0], sArray[1]);
                    }
                    this.request.parameters = param;
                });
            }
            return this;
        }

        public BuilderHttpRequest withHeaders(Map<String, String> headers){
            this.request.headers = headers;
            return this;
        }

        public BuilderHttpRequest withParameters(Map<String, String> parameters){
            this.request.parameters = parameters;
            return this;
        }

        public BuilderHttpRequest withBody(String body){
            this.request.body = body;
            return this;
        }

        public HttpRequest build(){
            return this.request;
        }
    }
}


