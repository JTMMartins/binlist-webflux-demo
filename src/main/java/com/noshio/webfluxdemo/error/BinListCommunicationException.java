package com.noshio.webfluxdemo.error;

public class BinListCommunicationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BinListCommunicationException(String message) {
        super(message);
    }
}
