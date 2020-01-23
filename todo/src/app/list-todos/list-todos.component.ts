import { Component, OnInit } from '@angular/core';
import { TodoDataService } from '../service/data/todo-data.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, FormArray, FormControl, ValidatorFn } from '@angular/forms';
import { of } from 'rxjs';
// import { filter } from 'rxjs/operators';

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

  form: FormGroup
  filters = []

  todos = Todo[100]
  message: string
  check: boolean

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

  constructor(private service: TodoDataService,private router:Router, private formBuilder: FormBuilder) 
  {
       this.form = this.formBuilder.group({
        filters: ['']
       });

       of(this.getFilters()).subscribe(filters => {
        this.filters = filters;
        this.form.controls.filters.patchValue(this.filters[0].id);
      });

      // sync filters
      //  this.filters = [] = this.getFilters();
      //  this.form.controls.filters.patchValue(this.filters[0].id);
  }

  getFilters() {
    return [
      {id:'0', name:'Filter'},
      {id:'1', name:true},
      {id:'2', name:false}
    ]
  }

  refreshTodos(){
    if(this.check == true || this.check == false){
      // console.log('In Conditioned Value ____ '  + this.filters[this.form.value['filters']]['name'])
      this.service.findByCriteria('Mohit',this.check).subscribe(
        response => {
          this.todos = response
        },
        error =>{
          this.message = `Can't Find Todos for Condtion ${this.check}`
        }

      )
    }
    else{
      this.service.retrieveAllTodos('Mohit').subscribe(
        response => {
          // console.log(response)
          this.todos = response
        }
      )
    }
  }

  ngOnInit() {
    this.refreshTodos()
  }

  submit(){
    // console.log(this.form.value['filters']) // this gives id
    // console.log(this.filters[this.form.value['filters']]['name']) // this gives values from dict. either false or True

    if(this.filters[this.form.value['filters']]['name'] == true){
      console.log('this is true')
      this.check = true
      this.refreshTodos()
    }
    else if(this.filters[this.form.value['filters']]['name'] == false){
      console.log('this is false')
      this.check = false
      this.refreshTodos()
    }
    else{
      this.check = null
      console.log(this.filters[this.form.value['filters']]['name'])
      this.refreshTodos()
    }
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
