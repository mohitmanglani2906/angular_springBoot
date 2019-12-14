package com.mohit2906.todo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

import org.springframework.stereotype.Service;

@Service
public class TodoHardcodedService {
	
	private static List<Todo> todos = new ArrayList<>();
	private static int idCounter = 0;
		
	
	
	static {
		Date date  = new  Date();
		long time = date.getTime();
		todos.add(new Todo(++idCounter, "in28Minutes", "Learn To Dance", new Timestamp(time),false));
		todos.add(new Todo(++idCounter, "in28Minutes", "Learn To Angular", new Timestamp(time),false));
		todos.add(new Todo(++idCounter, "in28Minutes", "Learn To Spring",new Timestamp(time),false));
	}
	
	public List<Todo> findAll(){
		return todos;
	}
	
	public Todo deleteById(long id) {
		Todo todo = findById(id);
		
		if(todo == null) return null;

		
		if(todos.remove(todo)) {
			return todo;
		}

		return null;
	}

	public Todo findById(long id) {	
		for(Todo todo:todos) {
			if(todo.getId() == id) {
				return todo;
			}
		}
	   return null;	
	}
	
	public Todo save(Todo todo) {
		if(todo.getId() == -1 || todo.getId() == 0) {
			todo.setId(++idCounter);
			todos.add(todo);
		}
		else
		{
			
			deleteById(todo.getId());
			todos.add(todo);
		}
		

		return todo;
}
 
}