package test;

import static org.junit.jupiter.api.Assertions.*;

//import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import grader.Grade;
import grader.GradeBook;
//import grader.GradeFraction;
import grader.GradeSection;

class GradeBookTest {
	/*
	 * @BeforeAll static void setUpBeforeClass() throws Exception { }
	 * 
	 * @AfterAll static void tearDownAfterClass() throws Exception { }
	 */
	private GradeBook gradeBook;
	private GradeSection section;
	private Grade grade;
//	private GradeFraction gradeTwo;

	@BeforeEach
	void setUp() throws Exception {
		gradeBook = new GradeBook();
		section = new GradeSection("test1");
		grade = new Grade("grade1", 0.9);
//		gradeTwo = new GradeFraction("grade2", 3, 10);
	}

	@AfterEach
	void tearDown() throws Exception {
		gradeBook = null;
		section = null;
		grade = null;
//		gradeTwo = null;
	}

	@Test
	void testHasSection() {
		assertFalse(gradeBook.hasSection("test1"));
		gradeBook.addGradeSection(section);
		assertTrue(gradeBook.hasSection("test1"));
	}

	@Test
	void testRemoveGradeSection() {
		gradeBook.addGradeSection(section);
		assertTrue(gradeBook.hasSection("test1"));
		gradeBook.removeGradeSection("test1");
		assertFalse(gradeBook.hasSection("test1"));
//		fail("Not yet implemented");
	}

	@Test
	void testAddGradeToSection() {
		gradeBook.addGradeSection(section);
		gradeBook.addGradeToSection("test1", grade);
		assertTrue(gradeBook.getGradeSection("test1").getGrade(0).equals(new Grade("grade1", 0.9)));
//		fail("Not yet implemented");
	}

	@Test
	void testRemoveGradeFromSectionStringInt() {
		fail("Not yet implemented");
	}

	@Test
	void testRemoveGradeFromSectionStringGrade() {
		fail("Not yet implemented");
	}

	@Test
	void testPrintBook() {
		fail("Not yet implemented");
	}

	@Test
	void testCalculateCurrentGrade() {
		fail("Not yet implemented");
	}

	@Test
	void testPrintString() {
		fail("Not yet implemented");
	}

	@Test
	void testWriteFile() {
		fail("Not yet implemented");
	}

	@Test
	void testFileString() {
		fail("Not yet implemented");
	}

}
