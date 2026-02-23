package com.tejait.batch15.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.tejait.batch15.model.Employee;

public interface EmployeeService {
	public Employee saveEmployee(Employee emp);
	
	public void deleteEmployee(Integer id);

	public Optional<Employee> getById(Integer id);

	

	public List<Employee> getAllEmps();

	public boolean existsById(Integer id);

	public Page<Employee> getPaginationData(int pageNum, int pageSize);

	

	public List<Employee> dataSorting(String property, String orderType);

	public List<Employee> findByDept(String dept);

	public List<Employee> findByFname(String fname);

	public List<Employee> findByFnameAndLname(String fname, String lname);

	public List<Employee> findByFnameOrLname(String fname, String lname);

	public List<Employee> findByAgeBetween(int age,int age1);

	public List<Employee> findBySalaryLessThan(long salary);

	public List<Employee> findByAgeGreaterThan(int age);

	public List<Employee> findByAgeGreaterThanEqual(int age);

	public List<Employee> findBySalaryLessThanEqual(long salary);

	public List<Employee> findByFnameLike(String fname);

	public List<Employee> findByFnameNotLike(String fname);

	public List<Employee> findByFnameStartingWith(String fname);

	public List<Employee> findByDeptEndingWith(String dept);

	public List<Employee> findByFnameContaining(String fname);
    public List<Employee> findByOrderByAgeDesc(int age);
    public List<Employee> findByFnameIgnoreCase(String fname);
    public    List<Employee> findByAgeIn(Collection<Integer> ages);

	

	public List<Employee> findByAgeIsNull();

	public List<Employee> findByAgeIsNotNull();

	public List<Employee> findByDeptNotIn(List<String> depts);



	

	public List<String> findDistinctByDept();

	
	public List<Employee> searchEmployee(String searchTerm);

	public List<Employee> searchFilters(String filterType, String empCode);

	

	

	
	

	

}
