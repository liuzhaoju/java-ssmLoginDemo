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
@RequestMapping(value = "user") // ģ����
public class UserController {

	@Autowired
	private UserService userService;

	// ��¼��֤
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
			// mv.addObject("error","�˺Ż��������");
			// mv.setViewName("login");
			model.addAttribute("error", "�˺Ż��������");
			return "login";
		}
	}

	@RequestMapping(value = "add.do")
	public ModelAndView addUser(HttpSession session, User user, MultipartFile file)
			throws IllegalStateException, IOException {
		ModelAndView mv = new ModelAndView();
		// 1.�ļ�����
		String oldFileName = file.getOriginalFilename(); // ��ȡ�ϴ��ļ���ԭ��
		// 2.�洢ͼƬ������·��
		String file_path = session.getServletContext().getRealPath("images");
		// 3.�ϴ�ͼƬ
		if (file != null && oldFileName != null && oldFileName.length() > 0) {
			// 4.�µ�ͼƬ����
			String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
			// 5.��ͼƬ
			// File newFile = new File("D://uploadImages/"+newFileName);
			File newFile = new File(file_path + "\\" + newFileName);
			user.setFile(newFileName);
			// 6.���ڴ��е�����д�����
			file.transferTo(newFile);
			// 7.���ļ����ֱ��浽���ݿ⣬ǰ�˶�ȡ��ʱ����õ������߿��԰�ͼƬ����ֱ�Ӵ���ǰ����һ��ͼƬ�Ļ��ԣ��������Լ�������
		} else {
			// log.info("File Error!")
			System.out.println("File Error!");
		}
		int rows = userService.addUser(user);

		if (rows > 0) {
			mv.addObject("msg", "�û�" + user.getUsername() + "��ӳɹ�!");
			mv.setViewName("success");
		} else {
			mv.addObject("msg", "�û�" + user.getUsername() + "���ʧ��!");
			mv.setViewName("fail");
		}

		return mv;
	}

	@RequestMapping(value = "update.do")
	public ModelAndView updateUser(HttpSession session, MultipartFile myfile, User user)
			throws IllegalStateException, IOException {
		ModelAndView mv = new ModelAndView();
//		if (null != myfile && StringUtils.isNotEmpty(myfile.getOriginalFilename())) {
//			// ��ȡ�ϴ��ļ���ԭʼ����
//			String originalFilename = myfile.getOriginalFilename();
//			// ��ȡ��Ŀ����Ŀ¼�� ���˴�Ϊtomcat�µ�webappsĿ¼·����
//			// D:\apache-tomcat-7.0.92\webapps��������Ŀ���²�����ϴ����ļ��������ʧ��
//			File rootPath = new File(session.getServletContext().getRealPath("/")).getParentFile();
//			File uploadFile = new File(rootPath.getPath() + "/images/upload/");
//			// ��������ļ��ĵ�ַ�����ڣ����ȴ���Ŀ¼
//			if (!uploadFile.exists()) {
//				uploadFile.mkdirs();
//			}
//			String newFilename = UUID.randomUUID() + "_" + originalFilename;
//			String url = rootPath.getPath() + "/images/upload/" + newFilename;
//			user.setFile("/images/upload/"+newFilename);
//			// �ļ�·��url ��:
//			// D:\\apache-tomcat-7.0.92\\webapps\\images\\uploads\dsads-sdsd-231-cat.jpg
//			System.out.println("/images/upload/" + newFilename);
//			try {
//				// ʹ��MultipartFile�ӿڵķ�������ļ��ϴ���ָ��λ��
//				myfile.transferTo(new File(url));
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
////		int rows = userService.updateUser(user);
		int rows = userService.uploadUser(session,myfile,user);

		if (rows > 0) {
			mv.addObject("msg", "�û�" + user.getUsername() + "�޸ĳɹ�!");
			mv.setViewName("success");
		} else {
			mv.addObject("msg", "�û�" + user.getUsername() + "�޸�ʧ��!");
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
//			mv.addObject("msg", "�û�ɾ���ɹ�! ɾ��: "+rows+"���û�");
//			mv.setViewName("success");
//			session.setAttribute("msg", "�û�ɾ���ɹ�! ɾ��: "+rows+"���û�");
//			System.out.println("**********************************�û�ɾ���ɹ�! ɾ��: "+rows+" ���û�");
			return "�û�ɾ���ɹ�! ɾ��: "+rows+"���û�";
		} else {
//			mv.addObject("msg", "�û�ɾ��ʧ��!");
//			mv.setViewName("fail");
//			session.setAttribute("msg", "�û�ɾ��ʧ��!");
			return "�û�ɾ��ʧ��!";
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

	//����excel
	@RequestMapping("/customerMassWebController/excel")
	public void excel(HttpServletResponse response) throws IOException {
		userService.exportExcel(response);
		
	}
	//����xml
		@RequestMapping("/customerMassWebController/xml")
		public void xml(HttpServletResponse response) throws IOException {
			List<User> list=userService.listUser();
			XmlUtils.writeXmlByDom4J(response, list);
			
		}

	// ����excel
	@RequestMapping("/inputexcel")
	// @ResponseBody
	public String importExcel(MultipartFile userExcel, HttpServletRequest request, HttpSession session) {
		if (userExcel == null) {
			session.setAttribute("msg", "δ�ϴ��ļ����ϴ�ʧ�ܣ�");
			return "fail";
		}
		String userExcelFileName = userExcel.getOriginalFilename();
		if (!userExcelFileName.matches("^.+\\.(?i)((xls)|(xlsx))$")) {
			session.setAttribute("msg", "�ļ���ʽ����ȷ����ʹ��.xls��.xlsx��׺���ĵ�������ʧ�ܣ�");
			return "fail";
		}
		// ����
		try {
			userService.importExcel(userExcel);
		} catch (Exception e) {
			e.printStackTrace();
		}
		session.setAttribute("excelName", "����ɹ���");
		return "index";
	}
	
	// ����xml
		@RequestMapping("/inputXml")
		// @ResponseBody
		public String inputXml(MultipartFile userXml, HttpServletRequest request, HttpSession session,Model model) {
			if (userXml == null) {
				session.setAttribute("msg", "δ�ϴ��ļ����ϴ�ʧ�ܣ�");
				return "fail";
			}
			String userExcelFileName = userXml.getOriginalFilename();
			if (!userExcelFileName.matches("^.+\\.(?i)(xml)$")) {
				session.setAttribute("msg", "�ļ���ʽ����ȷ����ʹ��.xml��׺���ĵ�������ʧ�ܣ�");
				return "fail";
			}
			// ����
			try {
				String msg=XmlUtils.readXmlByDom4J(userXml);
				model.addAttribute("msgu", msg);
				session.setAttribute("msgXml", msg);
//				mv.
//				userService.importExcel(userExcel);
			} catch (Exception e) {
				e.printStackTrace();
			}
			session.setAttribute("xmlName", "����ɹ���");
			return "index";
		}
	
	/*
	 * ��ȡָ��id���û�
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
		listObject.setMsg("���ʳɹ�");
		ResponseUtils.renderJson(response, JsonUtils.toJson(listObject));
	}


}
