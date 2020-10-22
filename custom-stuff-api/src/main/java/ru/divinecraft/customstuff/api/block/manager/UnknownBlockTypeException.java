package ru.divinecraft.customstuff.api.block.manager;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UnknownBlockTypeException extends RuntimeException {

    public UnknownBlockTypeException(final String message) {
        super(message);
    }

    public UnknownBlockTypeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UnknownBlockTypeException(final Throwable cause) {
        super(cause);
    }

    public UnknownBlockTypeException(final String message, final Throwable cause, final boolean enableSuppression,
                                     final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
