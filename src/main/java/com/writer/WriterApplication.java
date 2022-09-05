package com.writer;

import com.writer.operation.Chain;
import com.writer.service.Writer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class WriterApplication {

	public static void main(String[] args) {
		SpringApplication.run(WriterApplication.class, args);
	}


}
