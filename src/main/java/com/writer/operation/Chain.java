package com.writer.operation;

public interface Chain {
    void nextChain(Chain chain);
    String apply(String content);
}
