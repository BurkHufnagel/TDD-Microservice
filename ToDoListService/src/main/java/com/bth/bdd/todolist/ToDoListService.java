package com.bth.bdd.todolist;

import java.util.ArrayList;
import java.util.List;

public class ToDoListService {
	private List<String> tasks = new ArrayList<>();
	
	
    List<String> getAllTasks() {
    	return tasks;
    }
    
    int addTask( String description ) {
    	tasks.add( description );
    	
    	return tasks.indexOf(description);
    }
    
    

}
