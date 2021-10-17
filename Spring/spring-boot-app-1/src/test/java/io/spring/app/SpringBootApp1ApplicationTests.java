package io.spring.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import io.spring.app.entity.Student;
import io.spring.app.entity.StudentDTO;
import io.spring.app.service.StudentService;
import io.spring.app.util.AppUtil;

@SpringBootTest
class SpringBootApp1ApplicationTests {
	
	@Autowired
	private StudentService service;

	@Test
	void testFetchStudentByFullName() {
		Student std1 = new Student(100, "First", "Last");
		
		Optional<StudentDTO> fetched = this.service.fetchStudentByFullName(std1.getFirstName(), std1.getLastName());
		assertEquals(std1.getFirstName(), fetched.get().getFirstName());
		assertEquals(std1.getLastName(), fetched.get().getLastName());
	}
	
	@Test
	void testFetchStudentById() {
		Student std1 = new Student(100, "First", "Last");
		
		Optional<StudentDTO> fetched = this.service.fetchStudentById(std1.getId());
		assertEquals(std1.getId(), fetched.get().getId());
	}
	
	@Test
	void testFetchStudents() throws Exception {
		List<StudentDTO> s = new ArrayList<>();
		Student std1 = new Student(100, "First1", "Last1");
		Student std2 = new Student(200, "First2", "Last2");
		s.add(AppUtil.convertToDTO(std1));
		s.add(AppUtil.convertToDTO(std2));
		List<StudentDTO> fetched = this.service.fetchStudents();
		assertEquals(s.size(),fetched.size());
		assertNotSame(fetched, s);
		assertNotEquals(s, fetched);
		
	}
}
