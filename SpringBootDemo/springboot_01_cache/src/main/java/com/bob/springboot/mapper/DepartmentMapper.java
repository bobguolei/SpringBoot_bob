package com.bob.springboot.mapper;

import com.bob.springboot.bean.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DepartmentMapper {

  @Select("SELECT * FROM department WHERE id = #{id}")
  Department getDeptById(Integer id);
}
