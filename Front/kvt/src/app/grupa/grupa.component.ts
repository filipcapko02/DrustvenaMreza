import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';
import { DatePipe } from '@angular/common';
import { KorisnikModel } from '../models/korisnik.model';
import { ObjavaModel } from '../models/objava.model';
import { KomentarModel } from '../models/komentar.model';
import { LikeModel } from '../models/like.model';
import { GrupaModel } from '../models/grupa.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ObjavaServiceService } from '../service/objava-service.service';
import { KorisnikServiceService } from '../service/korisnik-service.service';
import { AuthServiceService } from '../service/auth-service.service';


@Component({
  selector: 'app-grupa',
  templateUrl: './grupa.component.html',
  styleUrls: ['./grupa.component.css']
})
export class GrupaComponent implements OnInit{

  newPost: any;
  postForm: FormGroup;
  commentForm: FormGroup;
  groupId: number;
  comment: KomentarModel | null = null;
  allPosts!: ObjavaModel[];
  group: GrupaModel | null = null;
  datepipe: DatePipe = new DatePipe('en-US');
  currentUser: KorisnikModel | null = null;
  constructor(private route: ActivatedRoute,private postService: ObjavaServiceService,private formBuilder: FormBuilder, private userService: KorisnikServiceService, private authService: AuthServiceService){
  }


  ngOnInit(): void {
    this.route.queryParams.subscribe(params => {
      this.groupId = params['id'];
        this.loadGroup(this.groupId);

    });
    this.getCurrentUser();
    this.initPostForm();
    this.initCommentForm();

    this.comment={
      id: null,
      content: '',
      userId: null,
      postedOn: new Date(),
      post: null
    }
  }

  initPostForm(): void {
    this.postForm = this.formBuilder.group({
      name: ['', Validators.required],
      content: ['', Validators.required]
    });
  }

  initCommentForm(): void {
    this.commentForm = this.formBuilder.group({
      text: ['', Validators.required]
    });
  }


  loadGroup(groupId: number): void {
    this.postService.findGroup(groupId).subscribe(
      (group: GrupaModel) => {
        this.group = group;

      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );





}

likePost(post: any){
  const alreadyLiked = post.likes.some((like: LikeModel) => like.userId === this.currentUser?.userId);
  if(!alreadyLiked){
  this.postService.addLike(post.postId,this.currentUser.userId).subscribe(
    (Response: ObjavaModel[]) => {
      this.allPosts = Response;
      this.loadGroup(this.groupId);
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
    );
  }else{
    this.postService.removeLike(post.postId,this.currentUser.userId).subscribe(
      (Response: ObjavaModel[]) => {
        this.allPosts = Response;
        this.loadGroup(this.groupId);
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
      );
  }
}

addComment(id: number) {
  const { text } = this.commentForm.value;
  this.comment.userId = this.currentUser.userId;
  this.comment.content = text;
  this.postService.addComment(id, this.comment).subscribe(() => {
    this.loadGroup(this.groupId);

  },
  (error: HttpErrorResponse) => {
    alert(error.message);
  });
}

createGroupPost(): void {
  if (this.postForm.invalid) {
    return;
  }

  const { name, content } = this.postForm.value;


  const post: ObjavaModel = {
    postId: 0,
    postName: name,
    content: content,
    user: this.currentUser,
    comments: [],
    creationDate: new Date(),
    likes: []
  };
  this.group.posts.push(post);


  this.postService.createGroup(this.group).subscribe(
    (response: any) => {
      this.loadGroup(this.groupId);
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  );
}

getCurrentUser() {
  this.userService.getMyInfo().subscribe(
    (response: KorisnikModel) => {
      this.currentUser = response;
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    }
  );
}
signed() {
  return Boolean(this.userService.currentUser);
}

logout() {
  this.authService.logout();
}

}




