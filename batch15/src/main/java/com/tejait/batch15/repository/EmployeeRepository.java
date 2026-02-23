package com.tejait.batch15.repository;


import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.tejait.batch15.model.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
public List<Employee> findByDept(String dept);
public List<Employee> findByFname(String fname);
public List<Employee>findByFnameAndLname(String fname,String lname);
public List<Employee>findByFnameOrLname(String fname,String lname);
public List<Employee>findByAgeBetween(int age,int age2);
public List<Employee>findBySalaryLessThan(long salary);
public List<Employee>findByAgeGreaterThan(int age);
public List<Employee>findByAgeGreaterThanEqual(int age);
public List<Employee>findBySalaryLessThanEqual(long salary);
public List<Employee>findByFnameLike(String fname);
public List<Employee>findByFnameNotLike(String fname);
public List<Employee>findByFnameStartingWith(String fname);
public List<Employee>findByDeptEndingWith(String dept);
public List<Employee>findByFnameContaining(String fname);
public List<Employee>findByOrderByAgeDesc(int age);
public List<Employee>findByFnameIgnoreCase(String fname);
public List<Employee>findByAgeIn(Collection<Integer> ages);
public List<Employee>findByAgeIsNull();
public List<Employee>findByAgeIsNotNull();
public List<Employee>findByDeptNotIn(Collection<String> depts);


@Query("select e from Employee e")
public List<Employee> getAll();

@Query (value="select*from employee_b15",nativeQuery = true)
public List<Employee> getAllEmps();

@Query("select e from Employee e where e.dept=?1")
public List<Employee>findByDepartment(String dept);

@Query("select e from Employee e where e.fname=?1 and e.lname=?2")
public List<Employee>findByFirstNameAndLastName(String firstName,String lastName );

@Query("select e from Employee e where e.fname=:firstName and e.lname=:lastname")
public List<Employee>findByFirstNameAndLastNameData(@Param("firstName")String fstName,@Param("lastname") String lstName);

@Query("select distinct(e.dept) from Employee e")
public List<String>findDistinctByDept();
@Query("select e from Employee e where concat(e.fname,e.lname, e.fullname,e.dept,e.empCode,e.salary,e.age) like %?1%")
public List<Employee> searchEmployee(String searchTerm);

public List<Employee>findByEmpCodeStartingWith(String empcode);
public List<Employee>findByEmpCodeEndingWith(String empCode );
public List<Employee>findByEmpCodeContaining(String empCode);
public List<Employee>findByEmpCodeNotContaining(String empCode);
public List<Employee>findByEmpCode(String empCode);
public List<Employee>findByEmpCodeNot(String empCode);




}
