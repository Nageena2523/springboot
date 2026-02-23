package com.tejait.batch15.serviceimpl;




import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.tejait.batch15.constants.SearchFilter;
import com.tejait.batch15.model.Employee;
import com.tejait.batch15.repository.EmployeeRepository;
import com.tejait.batch15.service.EmployeeService;

import lombok.AllArgsConstructor;
@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	EmployeeRepository repository;

	@Override
	public Employee saveEmployee(Employee emp) {
	String fname	=emp.getFname();
	String lname    =emp.getLname();
	emp.setFullname(fname.concat(" "+lname));
	return repository.save(emp);
	}

	

	@Override
	public void deleteEmployee(Integer id) {
		repository.deleteById(id);
		
	}

	@Override
	public Optional<Employee> getById(Integer id) {
		
		return repository.findById(id);
	}

	@Override
	public List<Employee> getAllEmps() {
		
		//return repository.findAll();
		//return repository.getAll();
		return repository.getAllEmps();
	}



	@Override
	public boolean existsById(Integer id) {
		
		return repository.existsById(id);
	}



	@Override
	public Page<Employee> getPaginationData(int pageNum, int pageSize) {
		Pageable pageable=PageRequest.of(pageNum, pageSize);
		return repository.findAll(pageable);
	}



	@Override
	public List<Employee> dataSorting(String property, String orderType) {
		if(orderType.equalsIgnoreCase("desc")) {
			return repository.findAll(Sort.by(Direction.DESC,property));
		}
		return repository.findAll(Sort.by(Direction.ASC ,property ));
	}



	@Override
	public List<Employee> findByDept(String dept) {
		
		//return repository.findByDept(dept);
		return repository.findByDepartment(dept);
	}



	@Override
	public List<Employee> findByFname(String fname) {
	
		return repository.findByFname(fname);
	}



	@Override
	public List<Employee> findByFnameAndLname(String fname, String lname) {
		
		//return repository.findByFnameAndLname(fname, lname);
		//return repository.findByFirstNameAndLastName(fname, lname);
		return repository.findByFirstNameAndLastNameData(fname, lname);
		
	}



	@Override
	public List<Employee> findByFnameOrLname(String fname, String lname) {
		
		return repository.findByFnameOrLname(fname, lname);
	}



	@Override
	public List<Employee> findByAgeBetween(int age,int age1) {
	
		return repository.findByAgeBetween(age,age1);
	}



	@Override
	public List<Employee> findBySalaryLessThan(long salary) {
		
		return repository.findBySalaryLessThan(salary);
	}



	@Override
	public List<Employee> findByAgeGreaterThan(int age) {
		
		return repository.findByAgeGreaterThan(age);
	}



	@Override
	public List<Employee> findByAgeGreaterThanEqual(int age) {
		
		return repository.findByAgeGreaterThanEqual(age);
	}



	@Override
	public List<Employee> findBySalaryLessThanEqual(long salary) {
		
		return repository.findBySalaryLessThanEqual(salary);
	}



	@Override
	public List<Employee> findByFnameLike(String fname) {
		
		return repository.findByFnameLike("%"+fname+"%");
	}



	@Override
	public List<Employee> findByFnameNotLike(String fname) {
		
		return repository.findByFnameNotLike(fname);
	}



	@Override
	public List<Employee> findByFnameStartingWith(String fname) {
		
		return repository.findByFnameStartingWith(fname);
	}



	@Override
	public List<Employee> findByDeptEndingWith(String dept) {
		
		return repository.findByDeptEndingWith(dept);
	}



	@Override
	public List<Employee> findByFnameContaining(String fname) {
		return repository.findByFnameContaining(fname);
	}



	@Override
	public List<Employee> findByOrderByAgeDesc(int age) {
	   return repository.findByOrderByAgeDesc(age);
	}



	@Override
	public List<Employee> findByFnameIgnoreCase(String fname) {
		return repository.findByFnameIgnoreCase(fname);
	}



	@Override
	public List<Employee> findByAgeIn(Collection<Integer> ages) {
		
		return repository.findByAgeIn(ages);
	}



	@Override
	public List<Employee> findByAgeIsNull() {
		
		return repository.findByAgeIsNull();
	}



	@Override
	public List<Employee> findByAgeIsNotNull() {
	
		return repository.findByAgeIsNotNull();
	}



	@Override
	public List<Employee> findByDeptNotIn(List<String> depts) {
		
		return repository.findByDeptNotIn(depts);
	}



	@Override
	public List<String> findDistinctByDept() {
		
		return repository.findDistinctByDept();
	}



	@Override
	public List<Employee> searchEmployee(String searchTerm) {
		
		return repository.searchEmployee(searchTerm);
	}



	@Override
	public List<Employee> searchFilters(String filterType, String empCode) {
		List<Employee>list= null;
		switch(filterType) {
		case SearchFilter.EQUALS:
			list= repository.findByEmpCode(empCode);
		break;
		case SearchFilter.NOT_EQUALS:
			list= repository.findByEmpCodeNot(empCode);
			break;
		case SearchFilter.START_WITH:
			list= repository.findByEmpCodeStartingWith(empCode);
			break;
		case SearchFilter.END_WITH:
			list= repository.findByEmpCodeEndingWith(empCode);
			break;
		case SearchFilter.CONTAINS:
			list= repository.findByEmpCodeContaining(empCode);
			break;
		case SearchFilter.NOT_CONTAINS:
			list= repository.findByEmpCodeNotContaining(empCode);
			break;
			default:
				throw new IllegalArgumentException("unexpected value"+empCode);
			
			}
		
		return list;
	}







	
		
		
	



	
		
	}



	

	

	


	


	


	



	



	


	



	
	
	

	
	

	



	




	

	
	


