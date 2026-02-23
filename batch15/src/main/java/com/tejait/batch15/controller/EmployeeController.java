package com.tejait.batch15.controller;

import java.awt.PageAttributes.MediaType;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tejait.batch15.exceptions.InsuffiecientfundsException;
import com.tejait.batch15.model.Employee;
import com.tejait.batch15.service.EmployeeService;


//@CrossOrigin
@RestController
@RequestMapping("/employee")
public class EmployeeController {
	

	
	
	 EmployeeService service;
	@Autowired
	 public EmployeeController(EmployeeService service) {
		super();
		this.service = service;
	}
	 @PostMapping(value="saveEmp")
	 public ResponseEntity<Employee> saveEmployee(@RequestBody Employee emp){
		 Employee saveEmp= service.saveEmployee(emp);
		 return new ResponseEntity<>(saveEmp, HttpStatus.CREATED);
	 }
	 @PutMapping(value="updateEmp")
	 public ResponseEntity<Employee> updateEmployee(@RequestBody Employee emp){
		 Employee updatedEmployee=service.saveEmployee(emp);
		return  new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
		 
	 }
	@DeleteMapping(value="deleteEmp/{id}")
	 public ResponseEntity<String> deleteByEmpId(@PathVariable Integer id){
		service.deleteEmployee(id);
		return new ResponseEntity<>("deleted Employee ID::"+id,HttpStatus.OK);
		 	
	 }
	@GetMapping(value="getByEmpId/{id}")
	 public ResponseEntity<Employee> getByEmpId(@PathVariable Integer id){
		 Optional<Employee>empObj= service.getById(id);
		 if(empObj.isPresent()) {
			 return new ResponseEntity<>(empObj.get(),HttpStatus.OK);
		 }
		 return new ResponseEntity<>(HttpStatus.OK);
		 
	 }
	 @GetMapping (value="getAll")
	 public ResponseEntity<List<Employee>> getAllEmps(){
		 List<Employee>list= service.getAllEmps();
		 return new ResponseEntity<>(list,HttpStatus.OK);
	 }
	 @GetMapping("existsById/{id}")
	 
	 public ResponseEntity<Boolean> existsById(@PathVariable Integer id){
		 boolean result= service.existsById(id);
		 if(!result) {
			 //throw new IdNotFoundException("given Id Not Available");
			 //throw new AccountAlreadyExists("given Account is not available");
			 //throw new MailAlreadyExists("given mail is not Available");
			 // throw new  MobileAlreadyexists("given mobile is not available");
			 // throw new DataNotFoundException("given data is not found");
			  throw new InsuffiecientfundsException("insufficient funds");
			 
			 
		 }
		 
		return new ResponseEntity<>(result,HttpStatus.OK);
		 
		 
	 }
	 @GetMapping("pagination")
	 public ResponseEntity<Page<Employee>>paginationData(@RequestParam int pageNum, @RequestParam int pageSize ){
		 Page<Employee>page= service.getPaginationData(pageNum,pageSize);
		return new ResponseEntity<>(page,HttpStatus.OK);
		 
	 }
	 @GetMapping("dataSorting")
	 public  ResponseEntity<List<Employee>>dateSorting(@RequestParam String property,@RequestParam String orderType){
	
		 List<Employee> list= service.dataSorting(property,orderType);
		 return new ResponseEntity<>(list,HttpStatus.OK);
		 
		 
	 }
	 @GetMapping("findByDept/{dept}")
	 public ResponseEntity<List<Employee>>findByDept(@PathVariable String dept){
	    List<Employee>	list= service.findByDept(dept);
		return new ResponseEntity<>(list,HttpStatus.OK);
		 
	 }
	 @GetMapping("findByFname/{fname}")
	 public ResponseEntity<List<Employee>>findByFname(@PathVariable String fname){
		 List<Employee>list= service.findByFname(fname);
		 return new ResponseEntity<>(list,HttpStatus.OK);
	 }
	 @GetMapping("findByFnameAndLname")
	 public ResponseEntity<List<Employee>>findByFnameAndLname(@RequestParam String fname, @RequestParam String lname){
		 List<Employee>list= service.findByFnameAndLname(fname,lname );
		return new ResponseEntity<>(list,HttpStatus.OK);
		 
	 }
	 @GetMapping("findByFnameOrLname")
	 public ResponseEntity<List<Employee>>findByFnameOrLname(@RequestParam String fname,@RequestParam String lname){
		 List<Employee>list=service.findByFnameOrLname(fname,lname);
		return new ResponseEntity<>(list,HttpStatus.OK);
		 
	 }
	 @GetMapping("findByAgeBetween")
	 public ResponseEntity<List<Employee>>findByAgeBetween(@RequestParam int age,@RequestParam int age1){
		 List<Employee>list= service.findByAgeBetween(age,age1);
		 return new ResponseEntity<>(list,HttpStatus.OK);
	 }
	 @GetMapping("findBySalaryLessThan/{salary}")
	 public ResponseEntity<List<Employee>>findBySalaryLessThan(@PathVariable long salary){
		 List<Employee>list= service.findBySalaryLessThan(salary);
		 return new ResponseEntity<>(list,HttpStatus.OK);
	 }
	 @GetMapping("findByAgeGreaterThan/{age}")
	 public ResponseEntity<List<Employee>>findByAgeGreaterThan(@PathVariable int age){
		 List<Employee>list= service.findByAgeGreaterThan(age);
		 return new ResponseEntity<>(list,HttpStatus.OK);
		 
	 }
	 @GetMapping("findByAgeGreaterThanEqual/{age}")
	 public ResponseEntity<List<Employee>>findByAgeGreaterThanEqual(@PathVariable int age){
		 List<Employee>list= service.findByAgeGreaterThanEqual(age);
		 return new ResponseEntity<>(list,HttpStatus.OK);
	 }
	 @GetMapping("findBySalaryLessThanEqual/{salary}")
	 public ResponseEntity<List<Employee>>findBySalaryLessThanEqual(@PathVariable long salary){
		 List<Employee>list= service.findBySalaryLessThanEqual(salary);
		 return new ResponseEntity<>(list,HttpStatus.OK);
	 }
	 @GetMapping("findByFnameLike/{fname}")
	 public ResponseEntity<List<Employee>>findByFnameLike(@PathVariable String fname){
		 List<Employee>list= service.findByFnameLike(fname);
		 return new ResponseEntity<>(list,HttpStatus.OK);
	 }
	 @RequestMapping("findByFnameNotLike/{fname}")
	 public ResponseEntity<List<Employee>>findByFnameNotLike(@PathVariable String fname){
		 List<Employee>list=service.findByFnameNotLike(fname);
		return new ResponseEntity<>(list,HttpStatus.OK);
		 
	 }
	 @RequestMapping("findByFnameStartingWith/{fname}")
	 public ResponseEntity<List<Employee>>findByFnameStartingWith(@PathVariable String fname){
		 List<Employee>list= service.findByFnameStartingWith(fname);
		 return new ResponseEntity<>(list,HttpStatus.OK);
	 }
	 @RequestMapping("findByDeptEndingWith/{dept}")
	 public ResponseEntity<List<Employee>>findByDeptEndingWith(@PathVariable String dept){
		 List<Employee>list= service.findByDeptEndingWith(dept);
		return new ResponseEntity<>(list,HttpStatus.OK);
		 
	 }
	 @RequestMapping("findByFnameContaining/{fname}")
	 public ResponseEntity<List<Employee>>findByFnameContaing(@PathVariable String fname){
		 List<Employee>list= service.findByFnameContaining(fname);
		 return new ResponseEntity<>(list,HttpStatus.OK);
		 
	 }
	 @RequestMapping("findByOrderByAgeDesc/{age}")
	 public ResponseEntity<List<Employee>>findByOrderByAgeDesc(@PathVariable int age){
		 List<Employee>list= service.findByOrderByAgeDesc(age);
		return new ResponseEntity<>(list,HttpStatus.OK);
		 
	 }
	 @RequestMapping("findByFnameIgnoreCase/{fname}")
	 public ResponseEntity<List<Employee>>findByFnameIgnoreCase(@PathVariable String fname){
		 List<Employee>list= service.findByFnameIgnoreCase(fname);
		return new ResponseEntity<>(list,HttpStatus.OK);
		 
	 }
	 @RequestMapping("findByAgeIn")
	 public ResponseEntity<List<Employee>>findByAgeIn(@RequestParam List<Integer> ages){
		 List<Employee>list= service.findByAgeIn(ages);
		 return new ResponseEntity<>(list,HttpStatus.OK);
	 }
	 @RequestMapping("findByAgeIsNull")
	 public ResponseEntity<List<Employee>>findByAgeIsNull(){
		 List<Employee>list= service.findByAgeIsNull();
		 return new ResponseEntity<>(list,HttpStatus.OK);
		 
	 }
	 @RequestMapping("findByAgeIsNotNull")
	 public ResponseEntity<List<Employee>>findByAgeIsNotNull(){
		 List<Employee>list= service.findByAgeIsNotNull();
		 return new ResponseEntity<>(list,HttpStatus.OK);
	 }
	 @RequestMapping("findByDeptNotIn")
	 public ResponseEntity<List<Employee>>findBydeptNotIn(@RequestParam List<String> depts){
		 List<Employee>list= service.findByDeptNotIn(depts);
		return new ResponseEntity<>(list,HttpStatus.OK);
		 
	 }
	 @RequestMapping("findDistnctByDept")
	 public ResponseEntity<List<String>>findDistinctByDept(){
		 List<String>list= service.findDistinctByDept();
		return new ResponseEntity<>(list,HttpStatus.OK);
		 
	 }
	@GetMapping("search/{searchTerm}")
	public ResponseEntity<List<Employee>>searchData(@PathVariable String searchTerm){
		List<Employee>list= service.searchEmployee(searchTerm);
		return new ResponseEntity<>(list,HttpStatus.OK);
	}
	@GetMapping("searchFilters")
	public ResponseEntity<List<Employee>>searchFilter(@RequestParam String filterType,@RequestParam String empCode){
		List<Employee>list= service.searchFilters(filterType,empCode);
		return new ResponseEntity<>(list,HttpStatus.OK);
		
		}
	
	 
}
	 


