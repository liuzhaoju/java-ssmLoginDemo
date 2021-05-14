package com.qicheng.ssm.controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.SystemPropertyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.qicheng.ssm.domain.User;
import com.qicheng.ssm.service.UserService;
import com.qicheng.ssm.utils.JsonUtils;
import com.qicheng.ssm.utils.ListObject;
import com.qicheng.ssm.utils.ResponseUtils;
import com.qicheng.ssm.utils.StatusCode;
import com.qicheng.ssm.utils.XmlUtils;
import com.qicheng.ssm.utils.EasyUIDataGridResult;

@Controller
@RequestMapping(value = "user") // 模块名
public class UserController {

	@Autowired
	private UserService userService;

	// 登录验证
	@RequestMapping("login.do")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			Model model, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		User usercheck = userService.logincheck(user);
		if (usercheck != null) {
			// mv.addObject("username",username);
			session.setAttribute("username", username);
			model.addAttribute("username", username);
			// String file =userService.getFileByUsername();
			model.addAttribute("file", usercheck.getFile());
			// mv.setViewName("index");
			return "index";
		} else {
			// mv.addObject("error","账号或密码错误");
			// mv.setViewName("login");
			model.addAttribute("error", "账号或密码错误");
			return "login";
		}
	}

	@RequestMapping(value = "add.do")
	public ModelAndView addUser(HttpSession session, User user, MultipartFile file)
			throws IllegalStateException, IOException {
		ModelAndView mv = new ModelAndView();
		// 1.文件名称
		String oldFileName = file.getOriginalFilename(); // 获取上传文件的原名
		// 2.存储图片的物理路径
		String file_path = session.getServletContext().getRealPath("images");
		// 3.上传图片
		if (file != null && oldFileName != null && oldFileName.length() > 0) {
			// 4.新的图片名称
			String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
			// 5.新图片
			// File newFile = new File("D://uploadImages/"+newFileName);
			File newFile = new File(file_path + "\\" + newFileName);
			user.setFile(newFileName);
			// 6.将内存中的数据写入磁盘
			file.transferTo(newFile);
			// 7.将文件名字保存到数据库，前端读取的时候会用到。或者可以把图片名称直接传给前端做一个图片的回显，这里大家自己操作。
		} else {
			// log.info("File Error!")
			System.out.println("File Error!");
		}
		int rows = userService.addUser(user);

		if (rows > 0) {
			mv.addObject("msg", "用户" + user.getUsername() + "添加成功!");
			mv.setViewName("success");
		} else {
			mv.addObject("msg", "用户" + user.getUsername() + "添加失败!");
			mv.setViewName("fail");
		}

		return mv;
	}

	@RequestMapping(value = "update.do")
	public ModelAndView updateUser(HttpSession session, MultipartFile myfile, User user)
			throws IllegalStateException, IOException {
		ModelAndView mv = new ModelAndView();
//		if (null != myfile && StringUtils.isNotEmpty(myfile.getOriginalFilename())) {
//			// 获取上传文件的原始名称
//			String originalFilename = myfile.getOriginalFilename();
//			// 获取项目部署目录根 （此处为tomcat下的webapps目录路径如
//			// D:\apache-tomcat-7.0.92\webapps，避免项目重新部署后上传的文件被清除丢失）
//			File rootPath = new File(session.getServletContext().getRealPath("/")).getParentFile();
//			File uploadFile = new File(rootPath.getPath() + "/images/upload/");
//			// 如果保存文件的地址不存在，就先创建目录
//			if (!uploadFile.exists()) {
//				uploadFile.mkdirs();
//			}
//			String newFilename = UUID.randomUUID() + "_" + originalFilename;
//			String url = rootPath.getPath() + "/images/upload/" + newFilename;
//			user.setFile("/images/upload/"+newFilename);
//			// 文件路径url 如:
//			// D:\\apache-tomcat-7.0.92\\webapps\\images\\uploads\dsads-sdsd-231-cat.jpg
//			System.out.println("/images/upload/" + newFilename);
//			try {
//				// 使用MultipartFile接口的方法完成文件上传到指定位置
//				myfile.transferTo(new File(url));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
////		int rows = userService.updateUser(user);
		int rows = userService.uploadUser(session,myfile,user);

		if (rows > 0) {
			mv.addObject("msg", "用户" + user.getUsername() + "修改成功!");
			mv.setViewName("success");
		} else {
			mv.addObject("msg", "用户" + user.getUsername() + "修改失败!");
			mv.setViewName("fail");
		}

		return mv;
	}

	@RequestMapping(value = "delete.do", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String deleteUser(@RequestParam(value = "id", defaultValue = "0") String id, HttpSession session) {
		ModelAndView mv = new ModelAndView();
		String[] ids = id.split(",");
		
//		int rows = userService.deleteUserById(id);
		int rows = userService.deleteUserByIds(ids);

		if (rows > 0) {
//			mv.addObject("msg", "用户删除成功! 删除: "+rows+"个用户");
//			mv.setViewName("success");
//			session.setAttribute("msg", "用户删除成功! 删除: "+rows+"个用户");
//			System.out.println("**********************************用户删除成功! 删除: "+rows+" 个用户");
			return "用户删除成功! 删除: "+rows+"个用户";
		} else {
//			mv.addObject("msg", "用户删除失败!");
//			mv.setViewName("fail");
//			session.setAttribute("msg", "用户删除失败!");
			return "用户删除失败!";
		}

//		return mv;
	}

	@RequestMapping(value = "list.do")
//	@RequestMapping("list.do")
	@ResponseBody
	public EasyUIDataGridResult listUser(Integer page,Integer rows) {
//	public List<User> listUser(Integer page,Integer rows) {
		EasyUIDataGridResult easyUIDataGridResult = userService.getItemList(page, rows);
		
		return easyUIDataGridResult;
		
//		List<User> userList = userService.listUser();
//
//		if (userList == null) {
//			userList = new ArrayList<>();
//		}
//
//		return userList;

	}

	//导出excel
	@RequestMapping("/customerMassWebController/excel")
	public void excel(HttpServletResponse response) throws IOException {
		userService.exportExcel(response);
		
	}
	//导出xml
		@RequestMapping("/customerMassWebController/xml")
		public void xml(HttpServletResponse response) throws IOException {
			List<User> list=userService.listUser();
			XmlUtils.writeXmlByDom4J(response, list);
			
		}

	// 导入excel
	@RequestMapping("/inputexcel")
	// @ResponseBody
	public String importExcel(MultipartFile userExcel, HttpServletRequest request, HttpSession session) {
		if (userExcel == null) {
			session.setAttribute("msg", "未上传文件，上传失败！");
			return "fail";
		}
		String userExcelFileName = userExcel.getOriginalFilename();
		if (!userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
			session.setAttribute("msg", "文件格式不正确！请使用.xls或.xlsx后缀的文档，导入失败！");
			return "fail";
		}
		// 导入
		try {
			userService.importExcel(userExcel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("excelName", "导入成功！");
		return "index";
	}
	
	// 导入xml
		@RequestMapping("/inputXml")
		// @ResponseBody
		public String inputXml(MultipartFile userXml, HttpServletRequest request, HttpSession session,Model model) {
			if (userXml == null) {
				session.setAttribute("msg", "未上传文件，上传失败！");
				return "fail";
			}
			String userExcelFileName = userXml.getOriginalFilename();
			if (!userExcelFileName.matches("^.+\\.(?i)(xml)$")) {
				session.setAttribute("msg", "文件格式不正确！请使用.xml后缀的文档，导入失败！");
				return "fail";
			}
			// 导入
			try {
				String msg=XmlUtils.readXmlByDom4J(userXml);
				model.addAttribute("msgu", msg);
				session.setAttribute("msgXml", msg);
//				mv.
//				userService.importExcel(userExcel);
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.setAttribute("xmlName", "导入成功！");
			return "index";
		}
	
	/*
	 * 获取指定id的用户
	 */
	@RequestMapping(value = "/findById")
	public void findById(String id, HttpServletRequest request, HttpServletResponse response) {
//		Integer id = Integer.parseInt(id);
		User inform = userService.getUserById(Integer.parseInt(id));
		List<User> list = new ArrayList<User>();
		list.add(inform);
		ListObject listObject = new ListObject();
		listObject.setItems(list);
		listObject.setCode(StatusCode.CODE_SUCCESS);
		listObject.setMsg("访问成功");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}


}
