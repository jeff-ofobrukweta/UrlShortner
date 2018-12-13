package com.interswitch.microservice_test.Controller.Res;

import java.util.List;

public class ResponseApi {
    private String code;
    private String description;
    private String errors;

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getErrors() {
        return errors;
    }

    public ResponseApi(String code, String description, String errors) {
        this.code = code;
        this.description = description;
        this.errors = errors;
    }
}
