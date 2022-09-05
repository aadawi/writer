package com.writer.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service
@Scope(value="prototype")
public class FileWriterService implements Writer {
    private boolean isClosed;
    private Path filePath = Path.of("file_"+System.currentTimeMillis()+".txt");
    @Override
    public void write(String content) {
        if (isClosed) {
            return;
        }
        try {
            Files.write(filePath, content.getBytes(), StandardOpenOption.APPEND,StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public String read() {
        try {
            return Files.readString(filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void close() {
        isClosed = true;
    }
}
