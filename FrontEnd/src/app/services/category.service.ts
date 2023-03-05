import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import baseUrl from './helper';
@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private _http:HttpClient) {}
    public categories(){
      return this._http.get(`${baseUrl}/category/`)
    }
 public addCategory(category: any){
  return this._http.post(`${baseUrl}/category/`,category)
 }
public delcat(cid:any){
  return this._http.delete(`${baseUrl}/category/${cid}`)
}
public getCat(cid:any){
  return this._http.get(`${baseUrl}/category/${cid}`)
}

public updateCategory(category: any,cid:any) {
  return this._http.put(`${baseUrl}/category/${cid}`,category)
}

}
