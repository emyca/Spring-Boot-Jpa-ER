package com.example.Spring_Boot_Jpa_ER.common.exception;

import java.io.Serial;

public class ResourceException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ResourceException(String msg) {
        super(msg);
    }
}
