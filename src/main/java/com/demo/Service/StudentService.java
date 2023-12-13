package com.demo.Service;

import java.util.List;

import com.demo.Entity.Student;
import com.demo.Model.StudentDTO;

public interface StudentService {
	
	StudentDTO createStudent(Student st);
	
	List<StudentDTO> getStudents();
	
	StudentDTO getStudentById(int id);
	
	StudentDTO updateStudent(int id,Student s);
	
	String deleteStudentById(int id);
	

}