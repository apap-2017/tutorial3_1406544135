package com.example.demo.tutorial3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.tutorial3.model.StudentModel;
import com.example.demo.tutorial3.service.InMemoryStudentService;
import com.example.demo.tutorial3.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {
	private final StudentService studentService;
	
	public StudentController(){
		studentService = new InMemoryStudentService();
	}
	
	@RequestMapping("/add")
	public String add(@RequestParam(value = "npm",required = true) String npm,
			@RequestParam(value =  "name", required = true) String name, 
			@RequestParam(value = "gpa", required = true) double gpa){
		StudentModel student = new StudentModel(npm,name, gpa);
		studentService.addStudent(student);
		return "add";
	}
	
	
	@RequestMapping(value={"/view","/view/{npm}"})
	public String viewNPM(Model model, @PathVariable Optional<String> npm){
		if(!npm.isPresent()){
			model.addAttribute("message", "NPM kosong");
			return "error";
		}
		StudentModel student = studentService.selectStudent(npm.get());
		if(student == null){
			model.addAttribute("message", "NPM tidak ditemukan");
			return "error";
		}
		model.addAttribute("student", student);
		return "view";
	}

	@RequestMapping(value={"/delete","/delete/{npm}"})
	public String delete(Model model, @PathVariable Optional<String> npm){
		if(!npm.isPresent()){
			model.addAttribute("message", "NPM kosong! Proses delete dibatalkan");
			return "error";
		}
		StudentModel student = studentService.selectStudent(npm.get());
		if(student == null){
			model.addAttribute("message", "NPM tidak ditemukan! Proses delete dibatalkan");
			return "error";
		}
		studentService.deleteStudent(student);
		return "delete";
	}
	
	@RequestMapping("/viewall")
	public String viewAll(Model model){
		List<StudentModel> students = studentService.selectAllStudents();
		model.addAttribute("students", students);
		return "viewall";
	}
}
