package com.qicheng.ssm.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qicheng.ssm.dao.UserDao;
import com.qicheng.ssm.domain.User;
import com.qicheng.ssm.domain.UserExample;
import com.qicheng.ssm.service.UserService;
import com.qicheng.ssm.utils.EasyUIDataGridResult;


@Service
//@Transactional
public class UserServiceImpl implements UserService {
	
	//byType装配
	@Autowired
	private UserDao userDao;

	@Override
	public int addUser(User user) {
		return userDao.addUser(user);
	}

	@Override
	public List<User> listUser() {
		List<User> userList = userDao.listUser();
		return userList;
	}

	@Override
	public int deleteUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.deleteUserById(id);
	}

	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}

	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.getUserById(id);
	}

	@Override
	public User logincheck(User user) {
		// TODO Auto-generated method stub
		return userDao.logincheck(user);
	}

	@Override
//	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
	public void importExcel(MultipartFile userExcel) throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
//		try{
			// TODO Auto-generated method stub
			//获取输入流
		    InputStream inputStream =  userExcel.getInputStream();
		    //创建读取工作簿
		    Workbook workbook = WorkbookFactory.create(inputStream);
		    //获取工作表
		    Sheet sheet = workbook.getSheetAt(0);
		    //获取总行   
		    Map map=new HashMap<String,Integer>();
		    int rows=sheet.getPhysicalNumberOfRows();
		    Row filed=sheet.getRow(0);
		    filed.getCell(0).getStringCellValue();
		    if(rows>1){
		        //获取单元格
		        for (int i = 1; i < rows; i++) {
		            Row row = sheet.getRow(i);
		            User user =new User();
		            
		            String name = row.getCell(1).getStringCellValue();
		            user.setUsername(name); 
		            if(name.equals("a李四")){
		            	System.out.println("----------------------------------------------------------------------");
		            	throw new RuntimeException();
		             }
		            String pass = row.getCell(2).getStringCellValue();
		            user.setPassword(pass);
		             
		            //想数据库中添加新对象
		            userDao.addUser(user);//方法
		        }
		         
		    }
		     
		    inputStream.close();
		
//	} catch (Exception e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}

}

	@Override
	public void exportExcel(HttpServletResponse response)throws IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		List<User> list = listUser();
		// List<CustomerMass> list = customerMassService.queryExcelInfo();
		System.out.println(list);
		// 创建excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 创建sheet页
		HSSFSheet sheet = wb.createSheet("客户信息表");
		// 创建标题行
		HSSFRow titleRow = sheet.createRow(0);
		titleRow.createCell(0).setCellValue("id");
		titleRow.createCell(1).setCellValue("用户名");
		titleRow.createCell(2).setCellValue("密码");
		// titleRow.createCell(9).setCellValue("创建时间 ");
		// titleRow.createCell(10).setCellValue("更新时间");
		// 遍历将数据放到excel列中
		for (User user : list) {
			HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
			dataRow.createCell(0).setCellValue(user.getId());
			dataRow.createCell(1).setCellValue(user.getUsername());
			dataRow.createCell(2).setCellValue(user.getPassword());
			// dataRow.createCell(9).setCellValue(user.getCreateDateStart());
			// dataRow.createCell(10).setCellValue(user.getCreateDateEnd());

		}
		/*
		 * // 设置下载时客户端Excel的名称 String filename =new SimpleDateFormat(
		 * "yyyy-MM-dd HH:mm:ss").format(new Date()) + ".xls";
		 * response.setContentType("application/vnd.ms-excel");
		 * response.setHeader("Content-disposition", "attachment;filename=" +
		 * filename);
		 */

		// 设置下载时客户端Excel的名称 （上面注释的改进版本，上面的中文不支持）
		response.setContentType("application/octet-stream;charset=utf-8");
		response.setHeader("Content-Disposition",
				"attachment;filename=" + new String("用户名单".getBytes(), "iso-8859-1") + ".xls");

		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();

	}

	@Override
	public int uploadUser(HttpSession session, MultipartFile myfile, User user)
			throws IllegalStateException, IOException {
		// TODO Auto-generated method stub
		if (null != myfile && StringUtils.isNotEmpty(myfile.getOriginalFilename())) {
			// 获取上传文件的原始名称
			String originalFilename = myfile.getOriginalFilename();
			// 获取项目部署目录根 （此处为tomcat下的webapps目录路径如
			// D:\apache-tomcat-7.0.92\webapps，避免项目重新部署后上传的文件被清除丢失）
			File rootPath = new File(session.getServletContext().getRealPath("/")).getParentFile();
			File uploadFile = new File(rootPath.getPath() + "/images/upload/");
			// 如果保存文件的地址不存在，就先创建目录
			if (!uploadFile.exists()) {
				uploadFile.mkdirs();
			}
			String newFilename = UUID.randomUUID() + "_" + originalFilename;
			String url = rootPath.getPath() + "/images/upload/" + newFilename;
			user.setFile("/images/upload/"+newFilename);
			// 文件路径url 如:
			// D:\\apache-tomcat-7.0.92\\webapps\\images\\uploads\dsads-sdsd-231-cat.jpg
			System.out.println("/images/upload/" + newFilename);
			try {
				// 使用MultipartFile接口的方法完成文件上传到指定位置
				myfile.transferTo(new File(url));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		int rows = updateUser(user);
		return rows;
	}

	@Override
	public EasyUIDataGridResult getItemList(Integer page, Integer rows) {
		//设置分页信息
		if(page==null){page=1;rows=10;}
		PageHelper.startPage(page, rows);
		//执行查询
		UserExample example=new UserExample();
		List<User> list = userDao.selectByExample(example);
		//取分页信息
		PageInfo<User> pageInfo=new PageInfo<>(list);
		//创建返回结果对象
		EasyUIDataGridResult dataGridResult=new EasyUIDataGridResult(pageInfo.getTotal(), list);
		
		
		return dataGridResult;
	}

	@Override
	public int deleteUserByIds(String[] ids) {
		// TODO Auto-generated method stub
		int num=0;
		for (int i = 0; i < ids.length; i++) {
			num+=userDao.deleteUserById(Integer.parseInt(ids[i]));
		}
		return num;
	}

}
