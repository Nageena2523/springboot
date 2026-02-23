package com.tejait.batch15.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("ctrltest")
public class TestController {
	@ResponseBody
	@GetMapping("getName")
	public String getName() {
		return "Controller Test Classes";
	}

}
