import { Component, OnInit } from '@angular/core';
import { TodoDataService } from '../service/data/todo-data.service';
import { Router } from '@angular/router';

export class Todo {
  constructor(
    public id: number,
    public description: string,
    public done: boolean,
    public targetDate: Date
  ) { }
}

@Component({
  selector: 'app-list-todos',
  templateUrl: './list-todos.component.html',
  styleUrls: ['./list-todos.component.css']
})
export class ListTodosComponent implements OnInit {

  todos = Todo[100]
  message: string

  // todos = [
  //   new Todo(1,'Learn to Dance',false, new Date()),
  //   new Todo(2,'Become Java Expert',false, new Date()),
  //   new Todo(3,'Become Angular Expert',true, new Date())

  // {id:1, description: 'Learn to Dance'},
  // {id:2, description: 'Become Java Expert'},
  // {id:3, description: 'Become Angular Expert'}

  //]
  // todo = {
  //   id : 1,
  //   description : 'Learn to Dance'
  // }

  constructor(private service: TodoDataService,private router:Router) { }

  refreshTodos(){
    this.service.retrieveAllTodos('Mohit').subscribe(
      response => {
        console.log(response)
        this.todos = response
      }

    )
  }

  ngOnInit() {
    this.refreshTodos()
  }

  

  deleteTodo(id) {
    console.log("Deleted" + id)
    this.service.deleteTodo('Mohit', id).subscribe(
      response => {
        console.log("In Success " + response);
        this.message = `Deleted Todo ${id} Successfull`;
        this.refreshTodos()
      }

    )

  }

  updateTodo(id){
    console.log('Update Todo ' + id)
    this.router.navigate(['todos',id])
  }
  addTodo(){
    this.router.navigate(['todos',-1])
  }

}
