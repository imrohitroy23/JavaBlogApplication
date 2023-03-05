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
  quiz:any
  constructor(private  _route:ActivatedRoute, private _router:Router , private _quiz:QuizService) {}

  ngOnInit(): void {
        this.qid=this._route.snapshot.params["qid"]
    this._quiz.getQuiz(this.qid).subscribe((data:any)=>{
      console.log(data)
      this.quiz=data
      console.log("Working fine")
    },
    (error:any)=>{
      alert("error in loading check instruction.ts")
    })
  }
//error in this ts file and html file

startQuiz() {
  Swal.fire({
    title: 'Do you want to start the quiz?',

    showCancelButton: true,
    confirmButtonText: `Start`,
    icon: 'info',
  }).then((result) => {
    /* Read more about isConfirmed, isDenied below */
    if (result.isConfirmed) {
      this._router.navigate(['/start/' + this.qid]);
    } else if (result.isDenied) {
      Swal.fire('Changes are not saved', '', 'info');
    }
  });
}
}
