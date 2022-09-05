package com.writer.service;

public interface Writer {

    void write(String content);

    String read();

    void close();
}
