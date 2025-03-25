package test;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import exception.ValidationException;

/**
 * <p> QuestionsTesting JUnit Class
 * <p> A JUnit module for testing various functions of the Questions.java class
 * 
 * @author Tyler Knippel
 * 
 * @see Questions
 * @see Question
 * 
 * 
 */

public class QuestionsTesting {
	

	//Initial Array of Questions
	Question[] questionsArray = {
			new Question(1, "Test Body 1", "StudentA"),
			new Question(2, "Test Body 2", "StudentB"),
			new Question(3, "Test Body 3", "StudentC"),
			new Question(4, "Test Body 4", "StudentA"),
			new Question(5, "Test Body 5", "StudentB"),
			new Question(6, "Test Body 6", "StudentC"),
			new Question(7, "Test Body 7", "StudentB", 1),
			new Question(8, "Test Body 8", "StudentA", 1),
			new Question(9, "Test Body 9", "StudentB", 2),
			new Question(10, "Test Body 10", "StudentC", 8),
			new Question(11, "Test Body 11", "StudentA", 7)
	};
	
	Questions qTest;

	/**
	 * Called before the running of each test to reinitialize the questionsArray.
	 * 
	 * 
	 * @throws ValidationException If a Question is unable to be created.
	 */
	@Before
	public void SetUp() throws ValidationException {
		qTest = new Questions();
		for(int i = 0; i < questionsArray.length; i++) {
			qTest.createQuestion(questionsArray[i]);
		}
	}
	
	
	/**
	 * Tests the Questions class to see if the questions added were successful
	 * by comparing the original Question object with the returned question object
	 * per Id.
	 * <p>
	 * Also tests mismatched and invalid question Id's and asserts not equal.
	 */
	@Test
	public void test_GetQuestionById(){
		assertEquals(questionsArray[0], qTest.getQuestionById(1));
		assertEquals(questionsArray[1], qTest.getQuestionById(2));
		assertEquals(questionsArray[2], qTest.getQuestionById(3));
		assertNotEquals(questionsArray[3], qTest.getQuestionById(3));
		assertNotEquals(questionsArray[4], qTest.getQuestionById(1));
		assertNotEquals(questionsArray[5], qTest.getQuestionById(10));
	}
	
	/**
	 * Tests the capability of deleting questions and checks the size of the
	 * Question List to see if it has accurately decremented.
	 * 
	 * @see Question
	 * 
	 */
	@Test
	public void test_DeleteQuestion() {

		//delete fails, size remains the same
		qTest.deleteQuestion(0);
		assertEquals(11, qTest.getQuestionCount());
		
		//delete success, size is different
		qTest.deleteQuestion(1);
		assertEquals(10, qTest.getQuestionCount());
		
	}
	
	
	/**
	 * Tests the capability of the Questions.GetQuestionsByStudent for each StudentID
	 * registered in the array. Also checks for a non-exsitent Student.
	 * 
	 */
	@Test
	public void test_GetQuestionsByStudent() {

		
		assertEquals(4, qTest.getQuestionsByStudent("StudentA").size());
		assertEquals(4, qTest.getQuestionsByStudent("StudentB").size());
		assertEquals(3, qTest.getQuestionsByStudent("StudentC").size());
		assertEquals(0, qTest.getQuestionsByStudent("StudentD").size());
	}
	
	
	/**
	 * Tests the capability of the Questions.GetQuestionsByStudent after new questions
	 * have been added for a student. 
	 * 
	 * 
	 */
	@Test
	public void test_GetQuestionsByStudentAfterDeletion() {

		
		assertEquals(4, qTest.getQuestionsByStudent("StudentA").size());
		qTest.deleteQuestion(1);
		assertEquals(3, qTest.getQuestionsByStudent("StudentA").size());
		qTest.deleteQuestion(4);
		assertEquals(2, qTest.getQuestionsByStudent("StudentA").size());
		qTest.deleteQuestion(8);
		assertEquals(1, qTest.getQuestionsByStudent("StudentA").size());
		qTest.deleteQuestion(11);
		assertEquals(0, qTest.getQuestionsByStudent("StudentA").size());
	}
	
	/**
	 * Tests the capability of Question.getQuestionsByStudent after a student's questions
	 * have been deleted.
	 * 
	 * @throws ValidationException If a Question is unable to be Created
	 * 
	 * @see Question 
	 * 
	 */
	
	@Test
	public void test_GetQuestionsByStudentAfterCreation() throws ValidationException {
		assertEquals(4, qTest.getQuestionsByStudent("StudentB").size());
		qTest.createQuestion(new Question(12, "Test Body 12", "StudentB"));
		assertEquals(5, qTest.getQuestionsByStudent("StudentB").size());
		qTest.createQuestion(new Question(13, "Test Body 13", "StudentB"));
		assertEquals(6, qTest.getQuestionsByStudent("StudentB").size());
	}

}
