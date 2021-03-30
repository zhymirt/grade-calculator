package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import grader.Grade;
import grader.GradeFraction;
import grader.GradeSection;
import grader.GradeSectionSum;
import grader.GradeSectionWeighted;

class GradeSectionTest {

	private double error;
	private Grade gradeThree;
	private GradeFraction gradeOne, gradeTwo;
	private GradeSection firstSection;
	
	boolean isWithinError(double num1, double num2) {
		return Math.abs(num1 - num2) < error;
	}

	@BeforeEach
	void setUp() throws Exception {
		error = 0.001;
		gradeOne = new GradeFraction("gradeOne", 7, 10);
		gradeTwo = new GradeFraction("gradeTwo", 80, 100);
		gradeThree = new Grade("gradeThree", 0.9);
		firstSection = new GradeSection("sectionOne");
	}

	@AfterEach
	void tearDown() throws Exception {
		gradeOne = gradeTwo = null;
		gradeThree = null;
		firstSection.setGrades(null);
		firstSection = null;
	}

	@Test
	void testAddGrade() {
		firstSection.addGrade(gradeOne);
		assertEquals(firstSection.getSize(), 1);
		assertEquals(firstSection.getGrade(0), gradeOne);
		firstSection.addGrade(gradeTwo);
		assertEquals(firstSection.getSize(), 2);
		assertEquals(firstSection.getGrade(1), gradeTwo);
		firstSection.addGrade(gradeThree);
		assertEquals(firstSection.getSize(), 3);
		assertEquals(firstSection.getGrade(2), gradeThree);
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

	@Test
	void testFileString() {
		fail("Not yet implemented");
	}

	@Test
	void testPrintString() {
		fail("Not yet implemented");
	}

	@Test
	void testCalculateAverage() {
		firstSection.addGrade(gradeOne);
		assertEquals(firstSection.calculateTotalGrade(), 0.7);
		firstSection.addGrade(gradeTwo);
		assertEquals(firstSection.calculateTotalGrade(), 0.75);
		firstSection.addGrade(gradeThree);
//		assertTrue(Math.abs(firstSection.calculateTotalGrade() - 0.8) < error);
		assertTrue(isWithinError(firstSection.calculateTotalGrade(), 0.8));
//		fail("Not yet implemented");
	}

	@Test
	void testCalculateSum() {
		GradeSectionSum sumSection = new GradeSectionSum("sum section");
		sumSection.addGrade(gradeOne);
		sumSection.addGrade(gradeTwo);
//		System.out.println(firstSection.calculateSum());
		assertEquals(sumSection.calculateTotalGrade(), 87);
		sumSection.addGrade(gradeThree);
		assertEquals(sumSection.calculateTotalGrade(), 87.9);

	}

	@Test
	void testCalculateWeightedAverage() {
		GradeSectionWeighted weightedSection = new GradeSectionWeighted("weighted section");
		weightedSection.addGrade(gradeOne);
		weightedSection.addGrade(gradeTwo);
		weightedSection.setWeight(0.3);
//		assertEquals(firstSection.calculateWeightedAverage(), 0.225);
		double weightedAvg = weightedSection.calculateTotalGrade();
//		System.out.println(weightedAvg);
		assertTrue(Math.abs(weightedAvg - 0.225) < error);
		weightedSection.addGrade(gradeThree);
		weightedAvg = weightedSection.calculateTotalGrade();
		assertTrue(Math.abs(weightedAvg - 0.24) < error);
	}


}
