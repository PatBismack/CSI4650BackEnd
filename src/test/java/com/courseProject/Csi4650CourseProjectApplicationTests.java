package com.courseProject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
class Csi4650CourseProjectApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void submitVote() {
		Controller controller = new Controller();
		Assertions.assertEquals("Vote was recorded successfully: Vote 0 Id: 000",
				controller.write(new Votes(0, "000")));
	}
}
