package com.example.proje.utilities.results;

import java.io.Serializable;

public class SuccessDataResult<T> extends DataResult<T> implements Serializable {

    private static final long serialVersionUID = 7156526077883281623L;

    public SuccessDataResult(T data, String message) {
        super(data, true, message);
    }

    public SuccessDataResult(T data) {
        super(data, true);
    }

    public SuccessDataResult(String message) {
        super(null, true, message);
    }

    public SuccessDataResult() {
        super(null, true);
    }

}
