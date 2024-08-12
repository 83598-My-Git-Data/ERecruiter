package com.app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.QuestionEntity;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class QuestionRepositoryTest {

	@Autowired
	private QuestionRepository questionRepo;
	
	@Test
	void addQuestion()
	{
		List<QuestionEntity> questionList = List.of(
			    new QuestionEntity("What does the acronym 'JVM' stand for?", 'A', "Java Virtual Machine", "JavaScript Virtual Machine", "Java Visual Machine", "Java Virtual Memory"),
			    new QuestionEntity("Which programming language introduced the concept of 'garbage collection'?", 'B', "C", "Java", "Python", "C++"),
			    new QuestionEntity("What is the purpose of the 'final' keyword in Java?", 'C', "To declare a constant variable", "To mark a method as final and prevent overriding", "To indicate the end of a program", "To specify the access level of a class"),
			    new QuestionEntity("In Python, what is the 'self' keyword used for in class methods?", 'A', "Refers to the instance of the class", "Denotes a static method", "Defines a constant variable", "Indicates a private method"),
			    new QuestionEntity("Which of the following is a statically-typed programming language?", 'B', "Python", "C++", "JavaScript", "Ruby"),
			    new QuestionEntity("What is the main purpose of the 'git' version control system?", 'C', "Building websites", "Managing databases", "Tracking changes in source code", "Creating graphical user interfaces"),
			    new QuestionEntity("What does the acronym 'API' stand for in software development?", 'A', "Application Programming Interface", "Advanced Programming Interface", "Automated Program Integration", "Application Process Interface"),
			    new QuestionEntity("Which data structure follows the Last In, First Out (LIFO) principle?", 'B', "Queue", "Stack", "LinkedList", "Tree"),
			    new QuestionEntity("What is the purpose of the 'try', 'catch', and 'finally' blocks in Java exception handling?", 'C', "Ensuring the code runs without any errors", "Defining custom exceptions", "Handling and recovering from exceptions", "Specifying the scope of variables"),
			    new QuestionEntity("In object-oriented programming, what is encapsulation?", 'A', "Binding data and methods into a single unit", "Creating multiple instances of a class", "Inheriting properties from a superclass", "Implementing interfaces")
			);
		List<QuestionEntity> list2=questionRepo.saveAll(questionList);
		assertEquals(10,list2.size());
	}
}
