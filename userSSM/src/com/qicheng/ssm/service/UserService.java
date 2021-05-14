package com.qicheng.ssm.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.qicheng.ssm.domain.User;
import com.qicheng.ssm.utils.EasyUIDataGridResult;

public interface UserService {
	int addUser(User user);
	List<User> listUser();
	int deleteUserById(int id);
	int updateUser(User user);
	int uploadUser(HttpSession session, MultipartFile myfile, User user)throws IllegalStateException, IOException ;
	User getUserById(int id);
	User logincheck(User user);
	void importExcel(MultipartFile userExcel) throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException;
	void exportExcel(HttpServletResponse response) throws IOException;
	EasyUIDataGridResult getItemList(Integer page, Integer rows);
	int deleteUserByIds(String[] ids);
}
