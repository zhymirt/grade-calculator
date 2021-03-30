
import static org.junit.jupiter.api.Assertions.*;

import grader.GradeFraction;
import grader.GradeSection;
import grader.GradeFraction;

import org.junit.jupiter.api.Test;

class GradeSectionTests {

	@Test
	void test() {
		GradeFraction gradeOne = new GradeFraction("gradeOne", 7, 10);
		GradeFraction gradeTwo = new GradeFraction("gradeTwo", 80, 100);
		GradeSection firstSection = new GradeSection("sectionOne");
		firstSection.addGrade(gradeOne);
		assertEquals(firstSection.getSize(), 1);
		assertEquals(firstSection.getGrade(0), gradeOne);
//		fail("Not yet implemented");
	}

}