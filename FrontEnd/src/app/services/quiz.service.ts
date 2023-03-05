import { HttpClient } from '@angular/common/http'
import { Injectable } from '@angular/core'
import baseUrl from './helper'

@Injectable({
  providedIn: 'root',
})
export class QuizService {
  constructor(private _http: HttpClient) {}
  public quizzes() {
    return this._http.get(`${baseUrl}/posts/posts/`)
  }
  public addQuiz(quiz: any,userId:any,categoryId:any) {
    return this._http.post(`${baseUrl}/posts/user/${userId}/category/${categoryId}/posts`, quiz)
  }
  public deleteQuiz(qid: any) {
    return this._http.delete(`${baseUrl}/posts/${qid}`)
  }

  public getQuiz(qid: any) {
    return this._http.get(`${baseUrl}/posts/${qid}`)
  }

  public updateQuiz(quiz: any) {
    return this._http.put(`${baseUrl}/posts/`, quiz)
  }
  // get quizzes of a category
  public getQuizzesOfCategory(categoryId: any) {
    return this._http.get(`${baseUrl}/posts/category/${categoryId}/posts`)
  }


}
