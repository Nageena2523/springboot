package com.tejait.batch15.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tejait.batch15.model.Employee;
import com.tejait.batch15.model.Student;
import com.tejait.batch15.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	
	StudentService service;
	@Autowired
	public StudentController(StudentService service) {
		super();
		this.service = service;
	}
	@PostMapping(value = "saveStudent")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student){
		  Student saveStudent= service.saveStudent(student);
		  return new ResponseEntity<>(saveStudent, HttpStatus.CREATED);
	}
		@PostMapping(value="updatestudent")
		public ResponseEntity<Student>updateStudent(@RequestBody Student student){
			Student updatedStudent=service.saveStudent(student);
			return new ResponseEntity<>(updatedStudent,HttpStatus.OK);
		
			
		
	      }
		@PostMapping(value="deleteStudentId/{id}")
		public ResponseEntity<String> deleteByStudentId(@PathVariable Integer id){
			service.deleteStudent(id);
			return new ResponseEntity<>("delete Student ID::"+id,HttpStatus.OK);
		
			
		}
		@PostMapping(value="getByStudentId/{id}")
		public ResponseEntity<Student> getByStudentId(@PathVariable Integer id){
			Optional<Student>studentObj=service.getById(id);
			return new ResponseEntity<>(studentObj.get(),HttpStatus.OK) ;
			
		}
		@PostMapping(value="getAll")
		public ResponseEntity<List<Student>> getAllStudents(){
			List<Student>list=service.getAllStudents();
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
		//===============textfile======================
		@GetMapping("textfile/{id}")
	    public ResponseEntity<byte[]> downloadStudentFile(@PathVariable Integer id) {

	        byte[] data = service.generateTextFile(id);

	        return ResponseEntity.ok()
	                .header("Content-Disposition", "attachment; filename=student_" + id + ".txt")
	                .body(data);
	    }
	    //=================================pdffile===========================
	    @GetMapping("/downloadpdf")
	    public ResponseEntity<byte[]> downloadStudentsPdf() {

	        byte[] pdf = service.generateAllStudentsPdf();

	        return ResponseEntity.ok()
	                .header("Content-Disposition", "attachment; filename=students.pdf")
	                .header("Content-Type", "application/pdf")
	                .body(pdf);
	    }
	    //=============================wordfile========================
	    @GetMapping("/downloadword")
	    public ResponseEntity<byte[]> downloadWord() {

	        byte[] data = service.generateStudentsWord();

	        return ResponseEntity.ok()
	                .header("Content-Disposition", "attachment; filename=students.docx")
	                .header("Content-Type", "application/vnd.openxmlformats-officedocument.wordprocessingml.document")
	                .body(data);
	    }
	    //===================exel================================
	    @GetMapping("/downloadexcel")
	    public ResponseEntity<byte[]> downloadExcel() {

	        byte[] data = service.generateStudentsExcel();

	        return ResponseEntity.ok()
	                .header("Content-Disposition", "attachment; filename=students.xlsx")
	                .header("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")
	                .body(data);
	    }
	}
		


