package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import grader.Grade;
import grader.GradeFraction;

class GradeTest {

	GradeFraction gradeFraction;
	Grade grade;

	@BeforeEach
	void setUp() throws Exception {
		grade = new Grade("myGrade", 0.8);
		gradeFraction = new GradeFraction("myGradeFraction", 90, 100);
	}

	@AfterEach
	void tearDown() throws Exception {
		grade = null;
		gradeFraction = null;
	}

	@Test
	void testFileString() {
		fail("Not yet implemented");
	}

	@Test
	void testEqualsGrade() {
		assertTrue(grade.equals(new Grade("myGrade", 0.8)));

		assertTrue(gradeFraction.equals(new GradeFraction("myGradeFraction", 90, 100)));
		
		System.out.println(grade.equals(new GradeFraction("myGrade", 0.8, 1)));
		System.out.println(gradeFraction.equals(new Grade("myGradeFraction", 0.9)));
//		fail("Not yet implemented");
	}

	@Test
	void testShouldBeNotEqual() {
		assertFalse(grade.equals(new Grade("aGradeFraction", 0.7)));

		assertFalse(gradeFraction.equals(new GradeFraction("aGrade", 80, 100)));
		
		System.out.println(grade.equals(gradeFraction));
		System.out.println(gradeFraction.equals(grade));
	}

	@Test
	void testCalculateGrade() {
		assertEquals(grade.calculateGrade(), 0.8);

		assertEquals(gradeFraction.calculateGrade(), 0.9);
//		fail("Not yet implemented");
	}

	@Test
	void testPrintString() {
		fail("Not yet implemented");
	}

}
