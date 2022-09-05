package com.writer;

import com.writer.operation.Chain;
import com.writer.service.Writer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class WriterApplicationTests {

	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private Chain duplicateRemover;
	@Autowired
	private Chain lowerCase;
	@Autowired
	private Chain upperCase;
	@Autowired
	private Chain stupidRemover;
	@Autowired
	private Writer fileWriterService;
	@Autowired
	private Writer stringWriterService;

	@Test
	void lowerCaseTest() {
		String content = "This is TEST";
		assertEquals(content.toLowerCase(),lowerCase.apply(content));
	}

	@Test
	void upperCaseTest() {
		String content = "This is TEST";
		assertEquals(content.toUpperCase(),upperCase.apply(content));
	}

	@Test
	void duplicateRemoverTest() {
		String content = "This is is test";
		assertEquals("This is test",duplicateRemover.apply(content));
	}

	@Test
	void stupidRemoverTest() {
		String content = "This is stupid test";
		assertEquals("This is s***** test",stupidRemover.apply(content));
	}

	@Test
	void lowerCaseStupidRemoverDuplicateRemoverTest() {
		stupidRemover.nextChain(duplicateRemover);
		lowerCase.nextChain(stupidRemover);
		String content = "This is really really stupid test";
		assertEquals("This is really s***** test".toLowerCase(),lowerCase.apply(content));
	}

	@Test
	void stringWriterTest() {
		String content = "this is Amazing";
		stringWriterService.write(content);
		assertEquals(content,stringWriterService.read());
	}

	@Test
	void stringWriterWithIgnoreTest() {
		String content = "this is Amazing";
		String contentMore = " WOW again";
		String contentIgnoreThis = "This will be ignored";
		stringWriterService.write(content);
		stringWriterService.write(contentMore);
		stringWriterService.close();
		stringWriterService.write(contentIgnoreThis);
		assertEquals(content+contentMore,stringWriterService.read());
	}

	@Test
	void fileWriterTest() {
		String content = "this is Amazing";
		fileWriterService.write(content);
		assertEquals(content,fileWriterService.read());
	}

	@Test
	void fileWriterWithIgnoreTest() {
		String content = "this is Amazing";
		String contentMore = " WOW again";
		String contentIgnoreThis = "This will be ignored";
		fileWriterService.write(content);
		fileWriterService.write(contentMore);
		fileWriterService.close();
		fileWriterService.write(contentIgnoreThis);
		assertEquals(content+contentMore,fileWriterService.read());
	}
}
