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
    return this._http.delete(`${baseUrl}/posts/posts/${qid}`)
  }
public updateposts(posts:any,pid:any){
  return this._http.put(`${baseUrl}/posts/posts/${pid}`,posts)
}
  public upload(pid: any) {
    return this._http.post(`${baseUrl}/posts/post/image/upload/${pid}`,pid)
  }
public getPostsbyId(pid:any){
  return this._http.get(`${baseUrl}/posts/posts/${pid}`)
}

public getuser(){
  return this._http.get(`${baseUrl}/user/`)

}
public delUser(cid:any){
  return this._http.delete(`${baseUrl}/user/${cid}`)
}
public sea(key:any){
  return this._http.get(`${baseUrl}/posts/post/seacrh/${key}`)
}
public postComm(post:any,pid:any){
  return this._http.post(`${baseUrl}/comment/post/${pid}/comment/`,post)
}

  public getQuiz(qid: any) {
    return this._http.get(`${baseUrl}/posts/${qid}`)
  }
  public getAllPosts(){
    return this._http.get(`${baseUrl}/posts/posts`)
  }
  public getAllPosts2(pageNumber:any, pageSize:any){
    return this._http.get(`${baseUrl}/posts/posts?pageNumber=${pageNumber}&pageSize=${pageSize}`)
  }

  public updateQuiz(quiz: any) {
    return this._http.put(`${baseUrl}/posts/`, quiz)
  }
  // get quizzes of a category
  public getQuizzesOfCategory(categoryId: any) {
    return this._http.get(`${baseUrl}/posts/category/${categoryId}/posts`)
  }


}
