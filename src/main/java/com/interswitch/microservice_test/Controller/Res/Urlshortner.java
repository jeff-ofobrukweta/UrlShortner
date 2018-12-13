package com.interswitch.microservice_test.Controller.Res;

import java.util.List;

public class Urlshortner extends ResponseApi {
    public String getUrl() {
        return url;
    }

    private final String url;

    public Urlshortner(String code, String description, String errors, String url) {
        super(code, description, errors);
        this.url = url;
    }


}
