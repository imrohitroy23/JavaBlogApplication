import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuizService } from 'src/app/services/quiz.service';

@Component({
  selector: 'app-getcat',
  templateUrl: './getcat.component.html',
  styleUrls: ['./getcat.component.css']
})
export class GetcatComponent {
comment={

  content:""
}
pid:any
constructor(private quiz:QuizService,private _route:ActivatedRoute){}
ngOnInit():void{
  this.pid = this._route.snapshot.params['postId'];
  console.log(this.pid)
}
formSubmit(){
  this.quiz.postComm(this.comment,this.pid).subscribe((data:any)=>{
    this.comment=data
  })
}

}
