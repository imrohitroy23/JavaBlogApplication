import { LocationStrategy } from '@angular/common'
import { Component, OnInit } from '@angular/core'
import { ActivatedRoute } from '@angular/router'
import { CategoryService } from 'src/app/services/category.service'
import { QuestionService } from 'src/app/services/question.service'
import { QuizService } from 'src/app/services/quiz.service'
import Swal from 'sweetalert2'

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css'],
})
export class StartComponent {
  posts={
id:"",
    title: "",
    content: "",
    imageName:"",
    category: {
      categoryId:"" ,
    },
    comments: [],
    user: {
      id: ""

    }
  }
  category = [
    {
      categoryId:22 ,
      categoryTitle: 'programming',
    },

  ]


  constructor(private _cat:CategoryService,private _quiz:QuizService ,private _route:ActivatedRoute){}
  ngOnInit():void{
    this.posts.id=this._route.snapshot.params['postId']
    this._cat.categories().subscribe(

      (data: any) => {

        this.category = data
        this.posts.category.categoryId=this._route.snapshot.params["categoryId"]
        console.log(data)
      },
      (error) => {
        console.log(error)
        Swal.fire('Error!!', 'Something went wrong', 'error')
      },
    )
  }
  onSuit(){
    console.log(this.posts.id)
    this._quiz.updateposts(this.posts,this.posts.id).subscribe((data)=>{
console.log("done")
    })


  }
}
