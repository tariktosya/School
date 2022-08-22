package com.example.proje.utilities.results;

import java.io.Serializable;

public class SuccessResult extends Result implements Serializable {
    private static final long serialVersionUID = 7156526077883281623L;
    public SuccessResult() {
        super(true);
    }

    public SuccessResult(String message) {
        super(true, message);
    }
}
