package com.demo.Controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.demo.Service.StudentService;
import com.demo.util.Converter;

@RestController
public class StudentController {
	@Autowired
	StudentService sr;
	@Autowired
	Converter con;

	// to pass request from controller to service
//	@PostMapping("/api/createStudent")
//	//@RequestBody annotation converts the JSON to object and store it in database
//	StudentDTO createStudent(@Valid @RequestBody StudentDTO sd) {
//		Student s=con.ConvertToStudentEntity(sd);
//		
//		return sr.createStudent(s);
//	}
//	@GetMapping("/getStudent")
//    List<StudentDTO> getStudents() {
//		
//		return sr.getStudents();
//	}
//	@GetMapping("/getStudentById/{id}")
//	//pathVariable bind the uri parameter to method parameter
//	StudentDTO getStudentById(@Valid @PathVariable int id) {
//		return sr.getStudentById(id);
//	}
//	@PutMapping("/updateStudent/{id}")
//	StudentDTO updateStudent(@PathVariable int id,@RequestBody Student s) {
//		return sr.updateStudent(id, s);
//	}
//	
//}
	@PostMapping("/api/createStudent")
	ResponseEntity<?> createStudent(@Validated @RequestBody StudentDTO sd, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			// Validation errors occurred, handle them here
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
			return ResponseEntity.badRequest().body("Validation failed: " + errors);
		} else {
			// Proceed with creating the user since validation passed
			// Your logic here to create the user
			Student s = con.ConvertToStudentEntity(sd);
			StudentDTO createdStudent = sr.createStudent(s);
			if (createdStudent != null) {
				return ResponseEntity.ok(createdStudent); // Return the created student DTO
			} else {
				// Handle the case where the student creation failed
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create student");
			}
		}
	}

	@GetMapping("/getStudent")
	List<StudentDTO> getStudents() {
		return sr.getStudents();

	}

	@GetMapping("/getStudent/{id}")
	public StudentDTO getStudentById(@PathVariable int id) {

		return sr.getStudentById(id);
	}

	@PutMapping("/updateStudent/{id}")
	ResponseEntity<?> updateStudent(@Validated @PathVariable int id, @Validated @RequestBody StudentDTO updatedStudent,
			BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			System.out.println("errror");
			List<String> errors = bindingResult.getAllErrors().stream()
					.map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList());
			return ResponseEntity.badRequest().body("Validation failed: " + errors);
		} else {
			System.out.println("no  errror");

			Student s = con.ConvertToStudentEntity(updatedStudent);
			StudentDTO result = sr.updateStudent(id, s);
			if (result != null) {
				return ResponseEntity.ok(result);
			} else {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update student");
			}
		}
	}
	
	@DeleteMapping("/deleteById/{id}")
	public String deleteStudentById(@PathVariable int id) {
		return sr.deleteStudentById(id);
	}
}