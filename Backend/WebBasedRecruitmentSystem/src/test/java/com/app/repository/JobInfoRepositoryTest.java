package com.app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.app.entities.ApplicantEntity;
import com.app.entities.DepartmentEntity;
import com.app.entities.HREntity;
import com.app.entities.JobInfoEntity;
import com.app.entities.WorkSchedule;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class JobInfoRepositoryTest {

	@Autowired
	private JobInfoRepository jobRepo;
	
	@Autowired
	private DepartmentEntityRepository depRepo;
	
	@Autowired
	private HREntityRepository hrRepo;
	
	@Autowired
	private ApplicantRepository appRepo;
	
	@Test
	public void addJob()
	{
		DepartmentEntity depart1= depRepo.findById(1l).orElseThrow();
		DepartmentEntity depart2= depRepo.findById(2l).orElseThrow();
		HREntity hr=hrRepo.findById(3L).orElseThrow();
		
		List<JobInfoEntity> jobList = List.of(
				/**
				 * JobInfoEntity(String jobTitle, int experienceRequired, WorkSchedule workSchedule, int salary,
			LocalDate applicationDeadline, String location, LocalDate jobCreatedDate, String qualification, HREntity hr,
			DepartmentEntity department,boolean status,String description)
				 * */
			    new JobInfoEntity("Software Engineer", 2, WorkSchedule.FULL_TIME, 450000, LocalDate.now().plusMonths(1),
			    		"Hyderabad", LocalDate.now(), "Bachelor's in Computer Science",hr,depart1,true,"We are seeking a skilled Node.js developer to join our team in designing and implementing backend services for our web applications. As a Node.js developer, you will be responsible for building scalable, high-performance APIs and server-side components using JavaScript and Node.js runtime environments. You will collaborate with frontend developers, architects, and product managers to deliver robust solutions that meet our clients' needs. The ideal candidate will have a strong understanding of asynchronous programming, RESTful APIs, and database technologies, along with a passion for writing clean, maintainable code."),
			    
			    new JobInfoEntity("Data Scientist", 10, WorkSchedule.FULL_TIME, 140000, LocalDate.now().plusMonths(1), 
			    		"Pune", LocalDate.now(), "Master's in Data Science",hr,depart2,true,"We are looking for an experienced Data Scientist to join our data analytics team and drive actionable insights from complex datasets. As a Data Scientist, you will apply advanced statistical and machine learning techniques to extract valuable patterns and trends from large-scale data sources. You will work closely with cross-functional teams to identify business opportunities, develop predictive models, and create data-driven solutions that drive business growth. The ideal candidate will have a solid foundation in mathematics, statistics, and programming, with hands-on experience in data mining, data visualization, and statistical analysis."),
			    new JobInfoEntity("Spring Boot Developer", 2, WorkSchedule.INTERNSHIP, 85000, LocalDate.now().plusMonths(1), 
			    		"Gurgaon", LocalDate.now(), "Master's in computer science",hr,depart1,true,"We are hiring a skilled Spring Boot developer to join our software development team and contribute to the design and implementation of enterprise-grade applications. As a Spring Boot developer, you will be responsible for developing RESTful APIs, microservices, and backend systems using Java and Spring Boot framework. You will collaborate with frontend developers, QA engineers, and product owners to deliver scalable and maintainable solutions that meet our clients' requirements. The ideal candidate will have a strong understanding of object-oriented programming, dependency injection, and cloud technologies, along with hands-on experience in building and deploying Spring Boot applications."),
			    new JobInfoEntity("Frontend Developer", 23, WorkSchedule.PART_TIME, 225000, LocalDate.now().plusMonths(1), 
			    		"Indore", LocalDate.now(), "Bechelor's in computer science ",hr,depart1,true,"We are seeking a talented Frontend Developer to join our dynamic team and contribute to the development of innovative web applications. As a Frontend Developer, you will be responsible for translating UI/UX designs into code, implementing responsive web designs, and optimizing web performance. You will work closely with designers and backend developers to ensure seamless integration of frontend components with backend systems. The ideal candidate will have a strong proficiency in HTML, CSS, and JavaScript frameworks such as React, Angular, or Vue.js, along with a passion for creating intuitive and visually appealing user interfaces."),
			    new JobInfoEntity("Cybersecurity Analyst", 7, WorkSchedule.FULL_TIME, 145000, LocalDate.now().plusMonths(1), 
			    		"Hyderabad", LocalDate.now(), "Master's in security science",hr,depart2,true,"We are looking for a skilled Cybersecurity Analyst to join our cybersecurity team and protect our organization's systems and data from cyber threats. As a Cybersecurity Analyst, you will monitor security systems, analyze security breaches, and implement security measures to safeguard against potential vulnerabilities. You will conduct vulnerability assessments, penetration testing, and incident response procedures to identify and mitigate security risks. The ideal candidate will have a deep understanding of network security principles, threat intelligence, and security best practices, along with hands-on experience with security tools and technologies."),
			    new JobInfoEntity("Cloud Engineer", 2, WorkSchedule.INTERNSHIP, 85000, LocalDate.now().plusMonths(1), 
			    		"Pune", LocalDate.now(), "Master's in Data Science",hr,depart2,true,"We are hiring a Cloud Engineer to join our cloud services team and help design, implement, and maintain cloud infrastructure solutions. As a Cloud Engineer, you will work with cross-functional teams to deploy and manage cloud environments, automate infrastructure provisioning, and optimize cloud resources for performance and cost efficiency. You will collaborate with developers and system administrators to ensure seamless integration of cloud services with existing systems and applications. The ideal candidate will have expertise in cloud platforms such as AWS, Azure, or Google Cloud, along with strong scripting skills and experience with infrastructure-as-code tools like Terraform or CloudFormation."),
			    new JobInfoEntity("Database Administrator ", 4, WorkSchedule.INTERNSHIP, 85000, LocalDate.now().plusMonths(1), 
			    		"Hyderabad", LocalDate.now(), "Master's in Data Science",hr,depart1,true,"We are seeking an experienced Database Administrator (DBA) to manage and optimize our organization's databases for performance, reliability, and security. As a DBA, you will be responsible for designing database structures, configuring database servers, and ensuring data integrity and availability. You will monitor database performance, troubleshoot issues, and implement backup and recovery strategies to protect critical data assets. The ideal candidate will have a solid understanding of relational database management systems (RDBMS) such as MySQL, PostgreSQL, or Oracle, along with proficiency in SQL query optimization and database tuning techniques."),
			    new JobInfoEntity("Machine Learning Engineer", 1, WorkSchedule.PART_TIME, 122000, LocalDate.now().plusMonths(1), 
			    		"Gurgaon", LocalDate.now(), "Master's in Data Science",hr,depart2,true,"We are looking for a talented Machine Learning Engineer to join our data science team and develop cutting-edge machine learning models and algorithms. As a Machine Learning Engineer, you will collaborate with data scientists and software engineers to collect, preprocess, and analyze data for model training and evaluation. You will design and implement machine learning pipelines, experiment with different algorithms, and deploy models into production environments. The ideal candidate will have a strong background in mathematics, statistics, and programming, with hands-on experience in machine learning frameworks such as TensorFlow or PyTorch, along with a passion for solving complex problems using data-driven approaches.")
			);
		List<JobInfoEntity> jobListTest=jobRepo.saveAll(jobList);
		assertEquals(8,jobListTest.size());
	}
	
	@Test
	public void addSavedJob()
	{
		ApplicantEntity app=appRepo.findById(4L).orElseThrow();
		JobInfoEntity job=jobRepo.findById(3L).orElseThrow();
		job.addApplicant(app);
		
	}
	
	@Test
	public void totalJobsAndActiveJob()
	{
		System.out.println(jobRepo.count()+"::"+jobRepo.countActiveJobs());
	}
}
