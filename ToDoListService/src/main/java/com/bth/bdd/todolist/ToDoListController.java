package com.bth.bdd.todolist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/toDoList")
public class ToDoListController {

	private ToDoListService service = new ToDoListService();
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<String>> getToDoList() {
		List<String> toDoList = service.getAllTasks();  // Forces creation of service
		ResponseEntity<List<String>> response = new ResponseEntity<List<String>>( toDoList, HttpStatus.OK );
		return response;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Integer> addListItem(@RequestBody String description) {
		int index = service.addTask( description );  // Forces creation of specification for method
		
		ResponseEntity<Integer> response = new ResponseEntity<Integer>( Integer.valueOf(index), HttpStatus.OK );
		return response;
	}

}
