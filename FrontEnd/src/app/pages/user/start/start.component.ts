import { LocationStrategy } from '@angular/common'
import { Component, OnInit } from '@angular/core'
import { ActivatedRoute } from '@angular/router'
import { QuestionService } from 'src/app/services/question.service'
import Swal from 'sweetalert2'

@Component({
  selector: 'app-start',
  templateUrl: './start.component.html',
  styleUrls: ['./start.component.css'],
})
export class StartComponent implements OnInit {
  qid: any
  marksGot = 0
  correctAnswers = 0
  isSubmit = false
  attempted = 0
  timer: any
  questions: any
  constructor(
    private _route: ActivatedRoute,
    private _question: QuestionService,
    private locationst: LocationStrategy,
  ) {}
  ngOnInit(): void {
    this.preventBackButton()
    this.qid = this._route.snapshot.params['qid']
    console.log(this.qid)
    this.loadQuestion()
  }
  loadQuestion() {
    this._question.getQuestionsOfQuizForTest(this.qid).subscribe(
      (data: any) => {
        this.questions = data
        this.timer = this.questions.length * 2 * 60 //seconds

        this.startTimer()
      },
      (error) => {
        Swal.fire('error', 'error in start.component.ts', 'error')
      },
    )
  }

  preventBackButton() {
    history.pushState(null, location.href)
    this.locationst.onPopState(() => {
      history.pushState(null, location.href)
    })
  }
  submitQuiz() {
    Swal.fire({
      title: 'Do you want to Submit the quiz?',

      showCancelButton: true,

      confirmButtonText: `Submit`,
      icon: 'info',
    }).then((result) => {
      if (result.isConfirmed) {
        this.evalQuiz()
        this.isSubmit = true
      }
    })
  }
  startTimer() {
    let t: any = window.setInterval(() => {
      if (this.timer <= 0) {
        this.evalQuiz()
        clearInterval(t)
      } else {
        this.timer--
      }
    }, 1000)
  }
  getMinutes() {
    let minutes = Math.floor(this.timer / 60)
    let ss = this.timer - minutes * 60
    return `${minutes} min : ${ss} sec`
  }
  evalQuiz() {
    this._question.evalquiz(this.questions).subscribe(
      (data: any) => {
        console.log(data)
        this.marksGot = parseFloat(Number(data.marksGot).toFixed(2))
        this.correctAnswers = data.correctAnswers
        this.attempted = data.attempted
        this.isSubmit = true
      },
      (error) => {
        console.log(error)
      },
    )
    // this.isSubmit=true
    // this.questions.forEach((q: any) => {
    //   if (q.givenAnswer == q.answer) {
    //     this.correctAnswers++
    //     let singleMarks =
    //       this.questions[0].quiz.maxMarks / this.questions.length
    //       console.log("singl"+singleMarks)
    //     this.marksGot += singleMarks
    //   }
    //   if(q.givenAnswer.trim()!=''){
    //     this.attempted++
    //   }
    // })
    // console.log('correct answer' + this.correctAnswers)
    // console.log('marks got ' + this.marksGot)
    // console.log("attempted "+this.attempted)
    // console.log(this.questions)
  }
  printpage() {
    window.print()
  }
}
