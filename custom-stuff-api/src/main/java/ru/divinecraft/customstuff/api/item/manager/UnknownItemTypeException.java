package ru.divinecraft.customstuff.api.item.manager;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UnknownItemTypeException extends RuntimeException {

    public UnknownItemTypeException(final String message) {
        super(message);
    }

    public UnknownItemTypeException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public UnknownItemTypeException(final Throwable cause) {
        super(cause);
    }

    public UnknownItemTypeException(final String message, final Throwable cause, final boolean enableSuppression,
                                    final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
