package com.qicheng.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("redirect")
public class JumpController {
	//��ת��ĳ��jsp
	@RequestMapping("tojsp.do")
	public String jumpToJsp(String target){
		//��ͼ���߼����ƣ��ļ�����
		return target;
	}
}
