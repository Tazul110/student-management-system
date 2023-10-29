package com.example.demo.sms.Controller;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.sms.entity.Student;

import com.example.demo.sms.service.StudentService;

@Controller
public class StudentController {

	private StudentService studentService;
	
	
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}
	@GetMapping("/students")
	public String ListStudent(Model model)
	{
		model.addAttribute("students",studentService.getAllStudents());
		return "students";
	}
	
	@GetMapping("/students/new")
	public String Addstudent()
	{
		
		return "add_student.html";
	}
	
	@RequestMapping("/student_submit")
	public String addStu(@ModelAttribute("student") Student student)
	{
		studentService.saveStudent(student);
		return "redirect:/students";
	}
	
	@GetMapping("/students/edit/{id}")
	public String update(@PathVariable Long id,Model model)
	{
		model.addAttribute("student",studentService.getStudentById(id));
		return "edit_student.html";
	}
	
	@PostMapping("/updated_student/{id}")
	public String updateStudent(@PathVariable Long id, @ModelAttribute("student") Student student, Model model) {
	    Student existingStudent = studentService.getStudentById(id);
	    existingStudent.setFirstName(student.getFirstName());
	    existingStudent.setLastName(student.getLastName());
	    existingStudent.setEmail(student.getEmail());
	    studentService.updateStudent(existingStudent);
	    return "redirect:/students";
	}
	
	@GetMapping("/students/delete/{id}")
	public String delete(@PathVariable Long id,Model model)
	{
		studentService.deleteStudentById(id);
		return "redirect:/students";
	}

	
	
	
}
