package com.example.proje.utilities.results;

import java.io.Serializable;

public class Result implements Serializable {
    private static final long serialVersionUID = 7156526077883281623L;
    private boolean success;
    private String message;

    public Result(boolean success) {
        this.success = success;
    }

    public Result(boolean success, String message) {
        this(success);
        this.message = message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public String getMessage() {
        return this.message;
    }
}
