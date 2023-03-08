import { Component } from '@angular/core'
import { ActivatedRoute } from '@angular/router'
import { LoginService } from 'src/app/services/login.service'
import { QuizService } from 'src/app/services/quiz.service'
import Swal from 'sweetalert2'
import { DatePipe } from '@angular/common';
@Component({
  selector: 'app-postspage',
  templateUrl: './postspage.component.html',
  styleUrls: ['./postspage.component.css'],
})
export class PostspageComponent {
  posts: any
  pid: any
  imageDirectory:any='http://localhost:8084/demo/images/'
  post: any

  constructor(
    private quiz: QuizService,
    private router: ActivatedRoute,
    public login: LoginService,
  ) {}

  ngOnInit(): void {
    this.pid = this.router.snapshot.params['postId']
    console.log(this.pid)
    this.quiz.getPostsbyId(this.pid).subscribe((data) => {
      this.post = data
      console.log(data)
    })
  }
  deleteQuiz(qid: any) {
    console.log(qid)
    Swal.fire({
      icon: 'info',
      title: 'Are you sure ?',
      confirmButtonText: 'Delete',
      showCancelButton: true,
    }).then((result) => {
      if (result.isConfirmed) {
        this.quiz.deleteQuiz(qid).subscribe(
          (data: any) => {
            this.posts = this.posts.filter((quiz: any) => quiz.id != qid)
            Swal.fire('Success', 'Quiz deleted', 'success')
          },
          (error) => {
            Swal.fire('error!!', 'error, loading quiz', 'error')
          },
        )
      }
    })
  }
}
