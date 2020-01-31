package ar.com.nssa.monitoreo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.com.nssa.monitoreo.service.SpecServiceBean;

@Controller
public class IndexController {

	static final Logger LOG = LoggerFactory.getLogger(IndexController.class);
	
	@Autowired
	private SpecServiceBean service;

	@RequestMapping("/")
	public String index(Model model) throws Exception {
		model.addAttribute("nodeRoot",service.getNodeRoot());
		return "/index";
	}

}