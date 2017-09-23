package com.example.demo.tutorial3.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.example.demo.tutorial3.model.StudentModel;

public class InMemoryStudentService implements StudentService {
	private static List<StudentModel> studentList = new ArrayList<StudentModel>();
	@Override
	public StudentModel selectStudent(String npm) {
		Iterator<StudentModel> iterasi = studentList.iterator();		
		while(iterasi.hasNext()){
			StudentModel temp = iterasi.next();
			
			if(temp.getNpm().equals(npm)){
				return temp;
			}
		}
		return null;
	}

	@Override
	public List<StudentModel> selectAllStudents() {
		return studentList;
	}

	@Override
	public void addStudent(StudentModel student) {
		// TODO Auto-generated method stub
		studentList.add(student);
	}

	@Override
	public void deleteStudent(StudentModel student) {
		// TODO Auto-generated method stub
		studentList.remove(student);
	}

}
