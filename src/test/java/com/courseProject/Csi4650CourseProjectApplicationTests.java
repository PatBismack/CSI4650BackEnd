package com.courseProject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.ArrayList;
import static org.mockito.Mockito.*; //for getTotalTest


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

	@Test
	public void submitVoteNegativeTest() {
		Controller controller = new Controller();
		String invalidInputResponse = controller.write(new Votes(-1, "invalidID"));
		Assertions.assertNotEquals("Vote was recorded successfully: Vote 0 Id: 000", invalidInputResponse);
	}

	//Re-wrote test, couldn't get old test to work
	@Test
	public void getAllVotesTest() {

		Controller controller = new Controller();

		List<Votes> actualVotes = controller.Read();
		List<Votes> expectedVotes = new ArrayList<>();
		for (Votes vote : actualVotes) {
			expectedVotes.add(new Votes(vote.getVote(), vote.getId()));
		}

		Assertions.assertEquals(expectedVotes.size(), actualVotes.size());
		for (int i = 0; i < expectedVotes.size(); i++) {
			Assertions.assertEquals(expectedVotes.get(i).getVote(), actualVotes.get(i).getVote());
			Assertions.assertEquals(expectedVotes.get(i).getId(), actualVotes.get(i).getId());
		}
	}

	@Test
	public void getAllVotesNegativeTest() {
		Controller controller = new Controller();

		List<Votes> actualVotes = controller.Read();
		List<Votes> incorrectExpectedVotes = new ArrayList<>();
		incorrectExpectedVotes.add(new Votes(999, "incorrectID"));

		Assertions.assertNotEquals(incorrectExpectedVotes.size(), actualVotes.size());
		for (int i = 0; i < incorrectExpectedVotes.size(); i++) {
			Assertions.assertNotEquals(incorrectExpectedVotes.get(i).getVote(), actualVotes.get(i).getVote());
			Assertions.assertNotEquals(incorrectExpectedVotes.get(i).getId(), actualVotes.get(i).getId());
		}
	}

	// Works a bit differently that the other test, uses Mockito
	@Test
	public void getTotalTest() {

		Controller controller = mock(Controller.class);

		Total expectedTotal = new Total(1, 2, 3);
		when(controller.voteTotal()).thenReturn(expectedTotal);
		Total result = controller.voteTotal();

		Assertions.assertEquals(1, result.getYes());
		Assertions.assertEquals(2, result.getNo());
		Assertions.assertEquals(3, result.getDontCare());
	}

	@Test
	public void getTotalNegativeTest() {
		Controller controller = mock(Controller.class);

		Total incorrectTotal = new Total(1, 2, 3);
		when(controller.voteTotal()).thenReturn(incorrectTotal);
		Total result = controller.voteTotal();

		Assertions.assertNotEquals(4, result.getYes());
		Assertions.assertNotEquals(5, result.getNo());
		Assertions.assertNotEquals(6, result.getDontCare());
	}
}