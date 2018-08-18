package com.ajie.res.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author niezhenjie
 */
@Controller
public class BlogController {
	public static final String PREFIX = "blog/";
	@RequestMapping
	String blog(HttpServletRequest request , HttpServletResponse response){
		return PREFIX+"blog";
	}

}
