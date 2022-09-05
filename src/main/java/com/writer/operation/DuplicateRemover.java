package com.writer.operation;

import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DuplicateRemover implements Chain{
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
        String regex = "\\b(\\w+)(?:\\W+\\1\\b)+";
        Pattern p = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher m = p.matcher(content);
        while (m.find()) {
            content = content.replaceAll(m.group(),m.group(1));
        }
        if (nextChain == null) {
            return content;
        }
        return nextChain.apply(content);
    }

}
