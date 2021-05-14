package com.qicheng.ssm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.qicheng.ssm.domain.User;
import com.qicheng.ssm.domain.UserExample;

public interface UserDao {
	int countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
	
	int addUser(User user);
	List<User> listUser();
	int deleteUserById(int id);
	int updateUser(User user);
	User getUserById(int id);
	//��¼��֤
	User logincheck(User user);
}
