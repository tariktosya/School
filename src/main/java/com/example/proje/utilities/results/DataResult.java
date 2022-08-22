package com.example.proje.utilities.results;

import java.io.Serializable;

public class DataResult<T> extends Result implements Serializable {
    private static final long serialVersionUID = 7156526077883281623L;
    private T data;

    public DataResult(T data, boolean success, String message) {
        super(success, message);
        this.data = data;
    }

    public DataResult(T data, boolean success) {
        super(success);
        this.data = data;
    }

    public T getData() {
        return this.data;
    }


}
