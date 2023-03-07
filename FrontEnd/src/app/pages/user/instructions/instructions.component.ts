import { Component, OnInit } from '@angular/core'
import { ActivatedRoute, Router } from '@angular/router'
import { QuizService } from 'src/app/services/quiz.service'
import Swal from 'sweetalert2'
import { PageEvent } from '@angular/material/paginator';
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
    lastPage:false,
  pageNumber:1,
  pageSize:10,
  totalElements:8,
  totalPages:0
  }
]
pp='3'
ps='5'

  constructor(private  _route:ActivatedRoute, private _router:Router , private _quiz:QuizService) {}

  ngOnInit(): void {
    // this._quiz.getAllPosts2(this.pp,this.ps).subscribe((data)=>{
    //   this.posts=data
    //   console.log(this.posts)
    // },(error)=>{
    //   console.log(error)
    // })
    this._quiz.getAllPosts().subscribe((data)=>{
      this.posts=data
      console.log(this.posts)
    },(error)=>{
      console.log(error)
    })

  }
//   public getProducts(request:any) {
//     this._quiz.getAllPosts(request)
//     .subscribe(data => {
//         this.posts.pageNumber = data['pageNumber'];
//         this.posts.pageSize = data['pageSize'];
//     }
//     , error => {
//         console.log(error.error.message);
//     }
//     );
// }
//error in this ts file and html file
// nextPage(event: PageEvent) {
//   const request = {};
//   request['page'] = event.pageIndex.toString();
//   request['size'] = event.pageSize.toString();
//   this.getProducts(request);
// }

}
