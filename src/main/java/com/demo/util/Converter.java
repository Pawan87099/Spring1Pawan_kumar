package com.demo.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.demo.Entity.Student;
import com.demo.Model.StudentDTO;

@Component
public class Converter {
	//convert DTO to Entity
	public Student ConvertToStudentEntity(StudentDTO ref) {
		Student s=new Student();
		if(ref != null) {
//bean util classes copyproperties is static method to copy prop from source obj to target
			BeanUtils.copyProperties(ref, s);
		}
		return s;
		
	}
	//Convert Entity to DTO
	public StudentDTO convertToStudentDTO(Student s) {
		StudentDTO res=new StudentDTO();
		if(s != null) {
			BeanUtils.copyProperties(s, res);
		}
		return res;
	}

}