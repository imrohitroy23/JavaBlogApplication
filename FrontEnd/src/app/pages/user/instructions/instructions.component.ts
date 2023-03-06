import { Component, OnInit } from '@angular/core'
import { ActivatedRoute, Router } from '@angular/router'
import { QuizService } from 'src/app/services/quiz.service'
import Swal from 'sweetalert2'
//if any error occurrs delete the instructionwala
@Component({
  selector: 'app-instructions',
  templateUrl: './instructions.component.html',
  styleUrls: ['./instructions.component.css'],
})
export class InstructionsComponent  {
 qid:any
  posts:any=[{
    content:[],
    lastPage:true,
pageNumber:0,
pageSize:10,
totalElements:8,
totalPages:0
  }
]
  constructor(private  _route:ActivatedRoute, private _router:Router , private _quiz:QuizService) {}

  ngOnInit(): void {
    this._quiz.getAllPosts().subscribe((data)=>{
      this.posts=data
      console.log(this.posts)
    },(error)=>{
      console.log(error)
    })
  }
//error in this ts file and html file

}
