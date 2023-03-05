import { Component, OnInit } from '@angular/core'
import { QuizService } from 'src/app/services/quiz.service'
import Swal from 'sweetalert2'

@Component({
  selector: 'app-view-quizzes',
  templateUrl: './view-quizzes.component.html',
  styleUrls: ['./view-quizzes.component.css'],
})
export class ViewQuizzesComponent {
  quizzes = [
    {
      qid: 23,
      title: 'basic',
      description: 'hello',
      maxMarks: '50',
      noofqsns: '50',
      acivie: '',
      category: {
        title: 'programming',
      },
    },
    {
      qid: 23,
      title: 'basic',
      description: 'hello',
      maxMarks: '50',
      noofqsns: '50',
      acivie: '',
      category: {
        title: 'programming',
      },
    },
  ]
  constructor(private _quiz: QuizService) {}
  ngOnInit(): void {


    this._quiz.quizzes().subscribe(
      (data: any) => {
        this.quizzes = data
        console.log(this.quizzes)
      },
      (error) => {
        console.log(error)
        Swal.fire('error!!', 'error in loading data', 'error')
      },
    )
  }

deleteQuiz(qid:any){
Swal.fire({
  icon:'info',
  title:'Are you sure ?',
  confirmButtonText:'Delete',
  showCancelButton:true
}).then((result)=>{
  if(result.isConfirmed){
    this._quiz.deleteQuiz(qid).subscribe(
      (data:any)=>{
        this.quizzes=this.quizzes.filter((quiz)=>quiz.qid!=qid);
      Swal.fire('Success',"Quiz deleted",'success')
    },(error)=>{
      Swal.fire("error!!","error, loading quiz",'error')
    })
  }
})

}


}
