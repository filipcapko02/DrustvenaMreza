import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import { KorisnikModel } from '../models/korisnik.model';
import { GrupaModel } from '../models/grupa.model';
import { ObjavaModel } from '../models/objava.model';
import { KorisnikServiceService } from '../service/korisnik-service.service';
import { AuthServiceService } from '../service/auth-service.service';
import { ObjavaServiceService } from '../service/objava-service.service';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {

  currentUser: KorisnikModel | null = null;
  form: FormGroup;
  editForm: FormGroup;
  userPosts: ObjavaModel[];
  editedPost: any = null;
  editPostFormVisible = false;
  userGroups: GrupaModel[];



  constructor(private userService: KorisnikServiceService,private postService: ObjavaServiceService,private formBuilder: FormBuilder,private authService: AuthServiceService,) {}

  ngOnInit(): void {

    this.getUserInfo();
    this.getUserPosts();
    this.initPostForm();
    this.getUserGroups();


  this.form = this.formBuilder.group({
    oldPassword: ['', Validators.required],
    newPassword: ['', Validators.required],
    repeatPassword: ['', Validators.required]
  });
}

initPostForm(): void {
  this.editForm = this.formBuilder.group({
    postName: ['', Validators.required],
    postContent: ['', Validators.required]
  });
}

getUserGroups() {
  this.postService.allGroups().subscribe(
    (response: GrupaModel[]) => {
      this.userGroups = response.filter(group => group.user.userId === this.currentUser?.userId);
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  );
}
deleteGroup(id: number) {
  this.postService.deleteGroup(id).subscribe(
    () => {
      this.getUserGroups();
    },
    (error: HttpErrorResponse) => {
      this.getUserGroups();
    }
  );
}

signed() {
  return Boolean(this.userService.currentUser);
}

getUserPosts() {
  this.postService.getAllPosts().subscribe(
    (response: ObjavaModel[]) => {
      this.userPosts = response.filter(post => post.user.userId === this.currentUser?.userId);
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  );
}

deletePost(postId: number) {
  this.postService.deletePost(postId).subscribe(
    () => {
      this.getUserPosts();
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  );
}

getUserInfo() {

  this.userService.getMyInfo().subscribe(
    (response: KorisnikModel) => {
      this.currentUser = response;
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  );
}

changePasswordForm(){
  const { oldPassword, newPassword, repeatPassword } = this.form.value;
  console.log(oldPassword);
  console.log(this.currentUser.password);
  if(newPassword == repeatPassword){
  this.currentUser.password = newPassword;
  this.userService.changePassword(this.currentUser,oldPassword).subscribe(
    (response: KorisnikModel) => {
      this.currentUser = response;
      this.logout();
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  );

}}


openEditForm(post: any) {
  this.editedPost = post;
  this.editPostFormVisible = true;
  this.editForm.patchValue({
    postName: post.postName,
    postContent: post.content
  });
}

submitEditForm(post: ObjavaModel) {
    const { postName, postContent } = this.editForm.value;
    this.editedPost = post;
    this.editedPost.content = postContent;
    this.editedPost.postName = postName;
    this.postService.addPost(this.editedPost).subscribe(
      (response: ObjavaModel) => {

        this.cancelEditForm();
        this.getUserPosts();
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );

}
logout() {
  this.authService.logout();
}

cancelEditForm() {
  this.editPostFormVisible = false;
  this.form.reset();
}
}
