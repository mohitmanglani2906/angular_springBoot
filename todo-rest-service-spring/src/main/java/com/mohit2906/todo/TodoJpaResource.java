package com.mohit2906.todo;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


@RestController
@CrossOrigin(origins="http://localhost:4200")
public class TodoJpaResource {
	
	@Autowired
	private TodoHardcodedService todoHardCodedService;
	
	@Autowired
	private TodoJpaRepository todoJpaRepository;
	
	@GetMapping("/db/users/{username}/todos")
	public List<Todo> getAllTodos(@PathVariable String username){
			return todoJpaRepository.findByUsername(username);
//			return todoJpaRepository.findAll();
			
	}
	
	@GetMapping("/db/users/{username}/todos/{id}")
	public Todo getTodo(@PathVariable String username,@PathVariable Long id){
			return todoJpaRepository.findById(id).get();
	}
	
	@DeleteMapping("/db/users/{username}/todos/{id}")
	public ResponseEntity<Void> deleteTodo(@PathVariable String username,@PathVariable Long id){
		//Todo todo = todoHardCodedService.deleteById(id);
		
		todoJpaRepository.deleteById(id);
		return ResponseEntity.noContent().build();
		//return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/db/users/{username}/todos/{id}")
	public ResponseEntity<Todo> updateTodo(
			@PathVariable String username,@PathVariable Long id,@RequestBody Todo todo){
		
		//Todo todoUpdated = todoHardCodedService.save(todo);
		Todo todoUpdated = todoJpaRepository.save(todo);
		return new ResponseEntity<Todo>(todo,HttpStatus.OK);
		
	}
	
	@PostMapping("/db/users/{username}/todos")
	public ResponseEntity<Void> createTodo(
			@PathVariable String username,@RequestBody Todo todo){
		
		//Todo createdTodo = todoHardCodedService.save(todo);
		todo.setUsername(username);
		Todo createdTodo = todoJpaRepository.save(todo);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdTodo.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
	
	@GetMapping("/db/users/{username}/{isDone}/todos")
	public List<Todo> findByCriteria(@PathVariable Boolean isDone, @PathVariable String username) throws Exception{
		
		List<Todo> todosList = null;
		
		try {
			todosList = todoJpaRepository.findAll(new Specification<Todo>() {
				
				@Override
				public Predicate toPredicate(Root<Todo> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
					List<Predicate> predicates = new ArrayList<>();
					
					if(username != null && isDone != null) {
						predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("isDone"),isDone)));
					}
					
					return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
				}
			});	
			
			if(todosList.isEmpty()) {
				throw new Exception("Not Found");
			}
		}
		
		catch(Exception e) {
			System.out.println("___ Error ____ " + e.getMessage());
		}
		
//		if(todosList.isEmpty()) {
//			System.out.println("___ Not Found ____ ");
//			throw new Exception("Not Found");
//		}
//		
		return todosList;
		
	}
	
	
	//Filterring
	
//	@GetMapping("/search/{isDone}")
//	public String search(@PathVariable Boolean isDone, Pageable pageable) {
////		ModelAndView mv = new ModelAndView("travel/list");
//		Page<Todo> todo = todoJpaRepository.findByisDoneLike("%" + isDone + "%",pageable);
//		
//		if(todo != null) {
//			return "Done";
//		}
//		
//		return "Not Done";
//	}
//	
//	@GetMapping("/search/todo/{Id}")
//	public String searchById(@PathVariable Long Id, Pageable pageable) {
//		try {
//			List<Todo> todo = todoJpaRepository.findByIdLike(Id,pageable);
//			
//			if(todo != null) {
//				return "Done";
//			}
//		}
//		
//		catch(Exception e) {
//			System.out.println("___ error ___" + e.getMessage());
//		}
//		
//		
//		return "Not Done";
//	}
	
	
	
	
}
