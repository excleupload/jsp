package com.salesupload.Tabledef.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.salesupload.Tabledef.enitity.Tabledefinition;

@Controller
//@RequestMapping("tabledef")

@RequestMapping("tabledefinition")
public interface TableDefService {

	@RequestMapping
	public String getPage();

	@RequestMapping(value = "/saveData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> SaveOrUpdate(@RequestBody Tabledefinition tabledefinition);

	@RequestMapping("/getAllData")
	public ResponseEntity<Object> getTableDefinition();


	@ResponseBody
	@RequestMapping(value = "/getcolumndetails/{tabledefinitionid}")
	public ResponseEntity<?> getColumnDetail(@PathVariable("tabledefinitionid") String tabledefinitionid);

	@RequestMapping("/export/{tabledefid}")
	@ResponseBody
	public void exportTableDefToExcel(HttpServletResponse response, @PathVariable("tabledefid") String tabledefid);

	@RequestMapping(value = "/edit/{id}")
	public ResponseEntity<Object> edittabdef(@PathVariable("id") String id);

	@RequestMapping("/tablecreationadd")
	public String tableCreationAdd(String tableId, Model model);

}
