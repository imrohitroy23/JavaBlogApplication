import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CategoryService } from 'src/app/services/category.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-update-category',
  templateUrl: './update-category.component.html',
  styleUrls: ['./update-category.component.css']
})
export class UpdateCategoryComponent {
constructor(private _cat:CategoryService,
  private _route:ActivatedRoute){}

  category={
    categoryId:"",
    categoryTitle:"",
    categoryDescription:""
  }
ngOnInit():void{
  this.category.categoryId=this._route.snapshot.params["categoryId"]
  console.log(this.category.categoryId)

  this._cat.getCat(this.category.categoryId).subscribe((data:any)=>{
    this.category=data
    console.log(this.category)
  },(error)=>{
    console.log("error from update-category.ts"+error)
  })
}
  updateData(){
    this._cat.updateCategory(this.category,this.category.categoryId).subscribe((data:any)=>{
      Swal.fire('Success !!', 'Category updated', 'success')
    },
    (error)=>{
      Swal.fire('Error', 'error in updating category', 'error');
    })
  }
}
