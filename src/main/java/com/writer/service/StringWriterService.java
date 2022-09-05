package com.writer.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service()
@Scope(value="prototype")
public class StringWriterService implements Writer {
    private StringBuffer stringBuffer = new StringBuffer();
    private boolean isClosed;
    @Override
    public void write(String content) {
        if (isClosed) {
            return;
        }
        stringBuffer.append(content);
    }
    @Override
    public String read() {
        return stringBuffer.toString();
    }
    @Override
    public void close() {
        isClosed = true;
    }

}
