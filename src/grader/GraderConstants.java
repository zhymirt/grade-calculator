package grader;

public final class GraderConstants {

	private GraderConstants() {
	}

	public static final int MIN_ARR_SIZE = 10;
	public static final int MIN_HASH_SIZE = 16;
	public static final int MAX_LINES = 100;
	public static final String NEW_LINE = "\n";
	public static final String DELIM = "\t";

	public static final String LIST_PREFIX = "[ ";
	public static final String LIST_POSTFIX = " ]";
	public static final String LIST_DELIM = " ";

	// File constants
	public static final String GB_BOOK_NAME = "gradeBook_name";
	public static final String GB_GRADE_TYPE = "gradeBook_type";
	public static final String GB_N_SECTIONS = "gradeBook_num_sections";
	public static final String GS_NAME = "gradeSection_name";
	public static final String GS_W = "gradeSection_weight";
	public static final String GS_N_GRADES = "gradeSection_num_grades";
	public static final String G_NAME = "grade_name";
	public static final String G_VAL = "grade_value";
	public static final String G_POSS = "grade_possible";

	public static final String FILE_HEADER = "***GradeBook***";
	public static final String NAME_PREFIX = "GradeBook Name: ";
	public static final String STR_SEP = ", ";
	public static final String KV_SEP = ": ";

	// Grade Types
	public static final String WEIGHTAVG = "WA";
	public static final String UNWEIGHTAVG = "UA";
	public static final String SUM = "SUM";

	// IO File format
	/*
	 * GradeBook "name: __, grade_type: __, num_sections: __" gradeSection
	 * "name: __, weight: __, num_grades: __" grade
	 * "name: __, grade_value: __, possible_grade: __" grade "" gradeSection
	 * "name: __, num_grades: __"
	 */
}