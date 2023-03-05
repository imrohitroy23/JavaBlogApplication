import { Component,OnInit } from '@angular/core';
import { CategoryService } from 'src/app/services/category.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-view-categories',
  templateUrl: './view-categories.component.html',
  styleUrls: ['./view-categories.component.css']
})
export class ViewCategoriesComponent {
  categories=[
  {
    categoryId:23,
    categoryTitle:'programming',
    categoryDescription:"this is from backened"
  }
]
constructor(private _category:CategoryService){}
ngOnInit():void{
  this._category.categories().subscribe((data:any)=>{
    this.categories=data;
    console.log(this.categories)
  },
  (error)=>{
    console.log(error)
    Swal.fire('Error!!','Error in loading data','error')
  })
}
deleteCat(cid:any){
  Swal.fire({
    icon:'info',
    title:'Are you sure ?',
    confirmButtonText:'Delete',
    showCancelButton:true
  }).then((result)=>{
    if(result.isConfirmed){
      this._category.delcat(cid).subscribe(
        (data:any)=>{
          this.categories=this.categories.filter((categories)=>categories.categoryId!=cid);
        Swal.fire('Success',"Quiz deleted",'success')
      },(error)=>{
        Swal.fire("error!!","Quiz exists with this Category. First delete that to delete this",'error')
      })
    }
  })
}

}

