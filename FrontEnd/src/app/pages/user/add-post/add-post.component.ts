import { Component } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import { LoginService } from 'src/app/services/login.service';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent {
  selectedFiles?: FileList;
   posts={

    title: "",
    content: "",
    imageName:"",
    category: {
      categoryId:"",
    },
    comments: [],
    user: {
      id: ""

    }
  }
  category = [
    {
      categoryId:"" ,
      categoryTitle: '',
    },

  ]
  // quizData = {
  //   title: '',
  //   description: '',
  //   maxMarks: '',
  //   noofqsns: '',
  //   active: false,
  //   category: {
  //     categoryId: '',
  //   },
  // }

  constructor(public login:LoginService, private _cat: CategoryService,private _snack:MatSnackBar,private _quiz:QuizService,private _route:ActivatedRoute) {}
  ngOnInit(): void {


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

    const id=this.login.getUser().id
    console.log(id)
    this.posts.user.id=id

    }
  // addQuiz() {
  //   if (this.quizData.title.trim() == '' || this.quizData.title == null) {
  //     this._snack.open('Title Required !!', '', {
  //       duration: 3000,
  //     });
  //     return;
  //   }

onSuit(){

  this._quiz.addQuiz(this.posts,this.posts.user.id,this.posts.category.categoryId).subscribe(
    (data:any)=>{
      Swal.fire('Success', 'Post is added', 'success');
    },
    (error)=>{
      Swal.fire('Error!! ', 'Error while adding quiz', 'error');
      console.log(error);
    }
  )
}

}

