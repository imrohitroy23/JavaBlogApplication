import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { QuestionService } from 'src/app/services/question.service';
import { QuizService } from 'src/app/services/quiz.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-add-question',
  templateUrl: './add-question.component.html',
  styleUrls: ['./add-question.component.css'],
})
export class AddQuestionComponent implements OnInit {

 posts= [
    {
      "id": 1,
      "title": "sdsdf",
      "content": "sdafss",
      "imageName": "default",
      "addedDate": "2023-03-04T15:16:31.093+00:00",
      "category": {
        "categoryId": 4,
        "categoryTitle": "afdsvfadaczcdsd",
        "categoryDescription": "SAVSAsacdsvfbdgfdasfadsfdfsdafdfZVFS"
      },
      "comments": [
        {
          "id": 1,
          "content": "sddasafss"
        }
      ],
      "user": {
        "id": 3,
        "name": "hjhvh",
        "email": "hhasnn@hh.com",
        "password": "$2a$10$poaeIUVmukHHgitRIKfi9O2p1uuvreFCcYx0jkZ5c5lo1Guw0fMwK",
        "about": "sdjbkhbkba",
        "roles": [
          {
            "id": 502,
            "name": "NORMAL_USER"
          }
        ]
      }
    }
  ]
  qid:any;
  qtitle:any;
  question = {
    quiz: {qid:''},
    content: '',
    option1: '',
    option2: '',
    option3: '',
    option4: '',
    answer: '',
  };

  constructor(
    private _route: ActivatedRoute,
    private _question: QuestionService
  ) {}

  ngOnInit(): void {
    this.qid = this._route.snapshot.params['qid'];
    this.qtitle = this._route.snapshot.params['title'];
    this.question.quiz['qid'] = this.qid;
  }

  formSubmit() {
    if (this.question.content.trim() == '' || this.question.content == null) {
      return;
    }

    if (this.question.option1.trim() == '' || this.question.option1 == null) {
      return;
    }
    if (this.question.option2.trim() == '' || this.question.option2 == null) {
      return;
    }
    if (this.question.answer.trim() == '' || this.question.answer == null) {
      return;
    }

    //form submit
    this._question.addQuestion(this.question).subscribe(
      (data: any) => {
        Swal.fire('Success ', 'Question Added. Add Another one', 'success');
        this.question.content = '';
        this.question.option1 = '';
        this.question.option2 = '';
        this.question.option3 = '';
        this.question.option4 = '';
        this.question.answer = '';
      },
      (error) => {
        Swal.fire('Error', 'Error in adding question', 'error');
      }
    );
  }
}
