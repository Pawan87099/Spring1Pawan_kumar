package com.demo.ServiceIMPL;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.Entity.Student;
import com.demo.Model.StudentDTO;
import com.demo.Repository.StudentRepo;
import com.demo.Service.StudentService;
import com.demo.util.Converter;

import jakarta.validation.Valid;


@Service
public class StudentServiceIMPL implements StudentService {
	@Autowired
	StudentRepo r;// by using student object store the data in database

	@Autowired
	Converter c;

	@Override
	public StudentDTO createStudent(Student st) {
		// Service to repository
		Student st1 = r.save(st);
		return c.convertToStudentDTO(st1);
	}

	@Override
	public List<StudentDTO> getStudents() {
		List<Student> st = r.findAll();
		List<StudentDTO> dtoList = new ArrayList();
		for (Student s : st) {
			dtoList.add(c.convertToStudentDTO(s));
		}
		return dtoList;
	}

	@Override
	public StudentDTO getStudentById(int id) {

		Student s = r.findById(id).get();
		return c.convertToStudentDTO(s);
	}

	@Override
	public StudentDTO updateStudent(int id, Student updatedStudent) {
		// Optional<Student> existingStudentOptional = sr.findById(id);
		Student existingStudent = r.findById(id).get();

		// Student existingStudent = existingStudentOptional.get();
		existingStudent.setName(updatedStudent.getName());
		existingStudent.setAddress(updatedStudent.getAddress());
		existingStudent.setPhoneNo(updatedStudent.getPhoneNo());

		Student s3 = r.save(existingStudent);
		return c.convertToStudentDTO(s3);

	}

	@Override
	public String deleteStudentById(int id) {
	    // Retrieve the student before deletion
	    //Student s = r.findById(id).orElse(null);

	    // Perform the deletion
	    r.deleteById(id);

	    // Convert the deleted student to DTO and return it
	    return "Deleted Successfully";
	}
}