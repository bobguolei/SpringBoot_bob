package com.bob.springboot.service;

import com.bob.springboot.bean.Employee;
import com.bob.springboot.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "emp", cacheManager = "employeeCacheManager")
@Service
public class EmployeeService {

  @Autowired
  EmployeeMapper employeeMapper;

  @Cacheable(value = {"emp"}/*, keyGenerator = "myKeyGenerator"*/)
  public Employee getEmp(Integer id) {
    System.out.println("查询" + id + "号员工！");
    Employee emp = employeeMapper.getEmpById(id);
    return emp;
  }

  @CachePut(/*value = "emp",*/key = "#result.id")
  public Employee updateEmp(Employee employee){
    System.out.println("updateEmp:"+employee);
    employeeMapper.updateEmp(employee);
    return employee;
  }

  @CacheEvict(/*value="emp",*/beforeInvocation = true/*key = "#id",*/)
  public void deleteEmp(Integer id){
    System.out.println("deleteEmp:"+id);
    //employeeMapper.deleteEmpById(id);
  }

  // @Caching 定义复杂的缓存规则
  @Caching(
      cacheable = {
          @Cacheable(/*value="emp",*/key = "#lastName")
      },
      put = {
          @CachePut(/*value="emp",*/key = "#result.id"),
          @CachePut(/*value="emp",*/key = "#result.email")
      }
  )
  public Employee getEmpByLastName(String lastName){
    return employeeMapper.getEmpByLastName(lastName);
  }




}
