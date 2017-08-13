package com.bth.bdd.todolist

import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import spock.lang.Ignore
import spock.lang.PendingFeature
import spock.lang.Shared
import spock.lang.Specification


@ActiveProfiles("test")
@SpringBootTest(classes = ToDoListApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ToDoListApplicationSpec extends Specification {
	
	private TestRestTemplate restTemplate = new TestRestTemplate()
	
   // Inject random port of test Web Server:
//   @Value('${local.server.port}')
   def int serverPort = 8080
   
   @Shared
   def url; 
	
	def setup() {
		println('serverPort = "' + serverPort + '"')
		url = "http://localhost:" + serverPort + "/toDoList"
		println('URL ="' + url + '"')
	}
	
	def 'Adding an item to an empty list should result in a list with one item'() {
		given: 'my to do list is empty'
			def list = restTemplate.getForEntity(url, List.class).getBody()
			assert(list.size() == 0)
		
		when: 'I add "Shave yak" to the list'
			restTemplate.postForObject(url,"Shave yak", String.class)
		
		then: 'the list should contain one item'
			def response = restTemplate.getForEntity(url, List.class)
			response.statusCode.value == 200
			def newList = response.getBody()
			newList.size() == 1
		
		and: 'the description should be "Shave yak"'	
			newList.get(0) == "Shave yak"	
	}
	
	@Ignore
	def 'A compled task should remove it from the to do list and add it to the done list' () {
		given: 'my to do list contains the task "Shave yak"'
			def list = restTemplate.getForEntity(url, List.class).getBody()
			restTemplate.postForObject(url,"Shave yak", String.class)
			
		and:  'my to do list contains the task "Prepare workshop"'
			restTemplate.postForObject(url,"Prepare workshop", String.class)
		
		when: 'I complete the task "Shave yak"'
		
		then: 'it should not show up in the to do list'
		
		and: "it should shou up in the done list"
		
		and: 'the to do list should still contain the task "Prepare workshop"'
		
	}
	
	@Ignore
	def "You can't have more that three tasks in progress at one time"() {
		given: 'a task list with four tasks'
		
		and: 'three of the tasks are in progress'
		
		when: 'trying to start a new task'
		
		then: 'the request should fail'
		
		and: 'the error message should indicate that the maximun number of started tasks has been reached'
	}

}