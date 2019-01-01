package com.bob.springboot.service;

import com.bob.springboot.bean.Department;
import com.bob.springboot.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;

@Service
public class DeptService {

  @Autowired
  DepartmentMapper departmentMapper;

  @Qualifier("departmentCacheManager")
  @Autowired
  RedisCacheManager departmentCacheManager;

//  @Cacheable(cacheNames = "dept", cacheManager = "departmentCacheManager")
  public Department getDeptById(Integer id) {
    System.out.println("查询部门" + id);
    Department department = departmentMapper.getDeptById(id);

    Cache cache = departmentCacheManager.getCache("dept");

    cache.put("dept:1", department);
    return department;
  }

}
