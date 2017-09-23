package com.example.demo.tutorial3.service;

import java.util.List;

import com.example.demo.tutorial3.model.StudentModel;

public interface StudentService {
	StudentModel selectStudent(String npm);
	void deleteStudent(StudentModel student);
	List<StudentModel> selectAllStudents();
	void addStudent(StudentModel student);
}
