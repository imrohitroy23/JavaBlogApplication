import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { LoginService } from 'src/app/services/login.service';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-postspage',
  templateUrl: './postspage.component.html',
  styleUrls: ['./postspage.component.css']
})
export class PostspageComponent {
  posts:any
  pid:any
  post:any={
    id:"", content:"",
    addedDate:"",
    imageName:"",
    title:""

  }

constructor(private quiz:QuizService,private router:ActivatedRoute,public login:LoginService){}

    ngOnInit(): void {
      this.pid = this.router.snapshot.params['postId'];
console.log(this.pid)
      this.quiz.getPostsbyId(this.pid).subscribe((data)=>{
              this.post=data;
              console.log(data)
      })

  }
  deleteQuiz(qid:any){
    console.log(qid)
    Swal.fire({
      icon:'info',
      title:'Are you sure ?',
      confirmButtonText:'Delete',
      showCancelButton:true
    }).then((result)=>{
      if(result.isConfirmed){
        this.quiz.deleteQuiz(qid).subscribe(
          (data:any)=>{
            this.posts=this.posts.filter((quiz:any)=>quiz.id!=qid);
          Swal.fire('Success',"Quiz deleted",'success')
        },(error)=>{
          Swal.fire("error!!","error, loading quiz",'error')
        })
      }
    })

    }
  // deleteCat(id:any){
  //   this.quiz.deleteQuiz(id).subscribe((data)=>{
  //     this.posts=this.posts.filter((pid:any)=>this.pid!=cid);
  //     Swal.fire('Success',"Quiz deleted",'success')
  //     console.log("posts deleted")
  //   })
  // }

}
// deleteCat(cid:any){
//   Swal.fire({
//     icon:'info',
//     title:'Are you sure ?',
//     confirmButtonText:'Delete',
//     showCancelButton:true
//   }).then((result)=>{
//     if(result.isConfirmed){
//       this._category.delcat(cid).subscribe(
//         (data:any)=>{
//           this.categories=this.categories.filter((categories)=>categories.categoryId!=cid);
//         Swal.fire('Success',"Quiz deleted",'success')
//       },(error)=>{
//         Swal.fire("error!!","Quiz exists with this Category. First delete that to delete this",'error')
//       })
//     }
//   })
// }
