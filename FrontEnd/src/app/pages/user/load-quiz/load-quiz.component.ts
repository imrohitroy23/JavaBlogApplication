import { Component, OnInit } from '@angular/core'
import { ActivatedRoute } from '@angular/router'
import { QuizService } from 'src/app/services/quiz.service'

@Component({
  selector: 'app-load-quiz',
  templateUrl: './load-quiz.component.html',
  styleUrls: ['./load-quiz.component.css'],
})
export class LoadQuizComponent implements OnInit {
  catId: any
 post:any= [
    {
      id:"" ,
      title: "",
      content: "",
      imageName: "default",
      addedDate: "2023-03-05T12:49:42.804+00:00",
      category: {
        categoryId: 7,
        categoryTitle: "dsadvssdas",
        categoryDescription: "sddadvszd"
      },
      comments: [],
      user: {
        id: 29,
        name: "normal",
        email: "hha@hh",
        password: "$2a$10$kq6VqAWu14.IayV0EibTk.eOQX1kQhlPTIlFdljoFxRvoCUZe2q6m",
        about: "nndsndfnd",


      }
    }
  ]
  constructor(private _route: ActivatedRoute, private _quiz: QuizService) {}


  ngOnInit(): void {
    this.catId=this._route.snapshot.params["categoryId"]
        console.log('Load specific quiz');
        this._quiz.getQuizzesOfCategory(this.catId).subscribe(
          (data: any) => {
            this.post = data;
            console.log(this.post);
          },
          (error) => {
            alert('error in loading quiz data');
          }
        );
  }
}
