Feature: To Do List 

As a developer with too much to do
I want a simple to do list app
so that I don't have to keep it all in my head
and I can see what I've accomplished
and what's still left to do


Scenario: Add a task to the list
    Given my to do list is empty
    When I add "Shave yak" to the list
    Then the list should contain 1 task
    And the description should be "Shave yak"
    
Scenario: Update task status
    Given my to do list contains the task "Shave yak"
    And my to do list contains the task "Prepare workshop"
    When I complete "Shave yak"
    Then it should be removed from the list
    And the list should still contain the task "Prepare workshop"