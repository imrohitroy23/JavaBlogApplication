import { Component, OnInit } from '@angular/core'
import { MatSnackBar } from '@angular/material/snack-bar'
import { ActivatedRoute } from '@angular/router'
import { QuestionService } from 'src/app/services/question.service'
import Swal from 'sweetalert2'

@Component({
  selector: 'app-view-questions',
  templateUrl: './view-questions.component.html',
  styleUrls: ['./view-questions.component.css'],
})
export class ViewQuestionsComponent implements OnInit {
  qid: any
  qtitle: any
  questions=[{
    quesId:"",
    content:'',
    option1:'',
    option2:'',
    option3:'',
    option4:'',
    answer:''

  }]

  constructor(private _route: ActivatedRoute,private _snak:MatSnackBar ,private _question:QuestionService) {}
  ngOnInit(): void {
    this.qid = this._route.snapshot.params['qid'];
    this.qtitle = this._route.snapshot.params['title'];
    this._question.getQuestionsOfQuiz(this.qid).subscribe(
      (data: any) => {
        console.log(data);
        this.questions = data;
      },
      (error) => {
        console.log(error);
      }
    );
  }
  //delete a question
  deleteQuestion(quesid:any) {
   Swal.fire({
    icon: 'info',
    showCancelButton: true,
    confirmButtonText: 'Delete',
    title: 'Are you sure , want to delete this question?',
   }).then((result)=>{
    if (result.isConfirmed) {
      //confim
      this._question.deleteQuestion(quesid).subscribe(
        (data) => {
          this._snak.open('Question Deleted ', '', {
            duration: 3000,
          });
          this.questions = this.questions.filter((q) => q.quesId != quesid);
        },

        (error) => {
          this._snak.open('Error in deleting questions', '', {
            duration: 3000,
          });
          console.log(error);
        }
      );
    }
   })
  }
}
