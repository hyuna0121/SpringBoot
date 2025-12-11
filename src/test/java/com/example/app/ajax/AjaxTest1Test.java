package com.example.app.ajax;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AjaxTest1Test {
	
	@Autowired
	private AjaxTest1 ajaxTest1;
	@Autowired
	private AjaxTest2 ajaxTest2;

	@Test
	void test() throws Exception {
		ajaxTest2.t3();
	}

}
