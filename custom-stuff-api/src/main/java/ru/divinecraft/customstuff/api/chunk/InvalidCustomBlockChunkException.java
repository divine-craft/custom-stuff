package ru.divinecraft.customstuff.api.chunk;

import lombok.NoArgsConstructor;

@NoArgsConstructor
@SuppressWarnings("PublicConstructor") // exception class
public class InvalidCustomBlockChunkException extends RuntimeException {

    public InvalidCustomBlockChunkException(final String message) {
        super(message);
    }

    public InvalidCustomBlockChunkException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public InvalidCustomBlockChunkException(final Throwable cause) {
        super(cause);
    }

    public InvalidCustomBlockChunkException(final String message, final Throwable cause,
                                            final boolean enableSuppression,
                                            final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
