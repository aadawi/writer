package com.writer.operation;

import org.springframework.stereotype.Component;

@Component
public class LowerCase implements Chain{
    private Chain nextChain;
    @Override
    public void nextChain(Chain chain) {
        nextChain = chain;
    }
    @Override
    public String apply(String content) {
        if (content == null) {
            return null;
        }
        content = content.toLowerCase();
        if (nextChain == null) {
            return content;
        }
        return nextChain.apply(content);
    }
}
