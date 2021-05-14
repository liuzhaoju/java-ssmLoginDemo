package com.qicheng.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("redirect")
public class JumpController {
	//跳转到某个jsp
	@RequestMapping("tojsp.do")
	public String jumpToJsp(String target){
		//视图的逻辑名称（文件名）
		return target;
	}
}
