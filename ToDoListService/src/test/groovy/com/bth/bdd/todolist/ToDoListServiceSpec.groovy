package com.bth.bdd.todolist

import spock.lang.Specification

class ToDoListServiceSpec extends Specification {
	
	def "The to do list should initially be empty"() {
		given: "a new instance of ToDoListService"
			def service = new ToDoListService()  // Force creation of class
			
		when: "the list is retrieved"
		    def list = service.getAllTasks()  // Force creation of method
		
		then: "it should be empty"
			list.size() == 0  // Force creation of list
	}
	
	
	def "When an item is added to an empty list, then the list should contain the item"() {
		given: "a new instance of ToDoListService"
			def service = new ToDoListService()  // Force creation of class
		
		when: "the task 'Shave yak' is added"
			def description = "Shave yak"
			def index = service.addTask(description)   // Force creation of method
		
		then: "the list should contain one task"
			def list = service.getAllTasks()  // Force creation of actual task repository
			
		and: "the task 'Shave yak' should be in the list"
			list.get(index) == description
	}
}
