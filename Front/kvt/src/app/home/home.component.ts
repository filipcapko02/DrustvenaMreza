import { Component, OnInit } from '@angular/core';
import { HttpErrorResponse } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { KorisnikModel } from '../models/korisnik.model';
import { ObjavaModel } from '../models/objava.model';
import { KomentarModel } from '../models/komentar.model';
import { LikeModel } from 'src/app/models/like.model';
import { GrupaModel } from '../models/grupa.model';
import { NgForm } from '@angular/forms';
import { ObjavaServiceService } from '../service/objava-service.service';
import { KorisnikServiceService } from '../service/korisnik-service.service';
import { AuthServiceService } from '../service/auth-service.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DislikeModel } from '../models/dislike.model';
import { HeartModel } from '../models/heart.model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit{

  newPost: any;
  postForm: FormGroup;
  commentForm: FormGroup;
  groupForm: FormGroup;
  comment: KomentarModel | null = null;
  allPosts!: ObjavaModel[];
  allGroups: GrupaModel[];

  datepipe: DatePipe = new DatePipe('en-US');
  currentUser: KorisnikModel | null = null;
  constructor(private postService: ObjavaServiceService,private formBuilder: FormBuilder, private userService: KorisnikServiceService, private authService: AuthServiceService){
  }

  ngOnInit(): void {
    this.initPostForm();
    this.initCommentForm();
    this.getAllPosts();
    this.initGroupForm()
    if(this.signed){
      this.getCurrentUser();
      this.getAllGroups();
    }
    this.newPost={
      content: '',
      user: null,
      creationDate: null
    }

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

  initGroupForm(): void {
    this.groupForm = this.formBuilder.group({
      name: ['', Validators.required],
      description: ['', Validators.required]
    });
  }

  initCommentForm(): void {
    this.commentForm = this.formBuilder.group({
      text: ['', Validators.required]
    });
  }
  getCurrentUser() {
    if(this.signed){
    this.userService.getMyInfo().subscribe(
      (response: KorisnikModel) => {
        this.currentUser = response;
      },
      (error: HttpErrorResponse) => {
      }
    );
  }}

  onDelete(postId: number) {
    this.postService.deletePost(postId).subscribe(() => {
      this.getAllPosts();
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    });
  }

  addComment(id: number) {
    const { text } = this.commentForm.value;
    this.comment.userId = this.currentUser.userId;
    this.comment.content = text;
    this.postService.addComment(id, this.comment).subscribe(() => {
      this.getAllPosts();
    },
    (error: HttpErrorResponse) => {
      alert(error.message);
    });
  }

  signed() {
    return Boolean(this.userService.currentUser);
  }


  createPost() {
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



    this.postService.addPost(post).subscribe(
      (response: ObjavaModel[]) => {
        this.allPosts = response;
        this.getAllPosts();
        this.postForm.reset();
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
        this.getAllPosts();
        this.allPosts = Response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
      );
    }else{
      this.postService.removeLike(post.postId,this.currentUser.userId).subscribe(
        (Response: ObjavaModel[]) => {
          this.getAllPosts();
          this.allPosts = Response;
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
        );
    }
  }
  

  createGroup() {
    if (this.groupForm.invalid) {
      return;
    }

    const { name, description } = this.groupForm.value;

    const group: GrupaModel = {
      id: 0,
      name: name,
      descripiton: description,
      creationDate: new Date(),
      posts: [],
      user: this.currentUser
    };

    this.postService.createGroup(group).subscribe(
      (response: any) => {
        this.getAllGroups()
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
    );
  }


  getAllPosts() {
    this.postService.getAllPosts().subscribe(
      (Response: ObjavaModel[]) => {
        this.allPosts = Response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
      );
  }

  getAllGroups() {
    this.postService.allGroups().subscribe(
      (response: GrupaModel[]) => {
        this.allGroups = response;
      },
      (error: HttpErrorResponse) => {
      }
    );
  }
  logout() {
    this.authService.logout();
  }
  dislikePost(post: any){
    const alreadyDisliked = post.dislikes.some((dislike: DislikeModel) => dislike.userId === this.currentUser?.userId);
    if(!alreadyDisliked){
    this.postService.addDislike(post.postId,this.currentUser.userId).subscribe(
      (Response: ObjavaModel[]) => {
        this.getAllPosts();
        this.allPosts = Response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
      );
    }else{
      this.postService.removeDislike(post.postId,this.currentUser.userId).subscribe(
        (Response: ObjavaModel[]) => {
          this.getAllPosts();
          this.allPosts = Response;
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
        );
    }
  }
  heartPost(post: any){
    const alreadyHearted = post.hearts.some((heart: HeartModel) => heart.userId === this.currentUser?.userId);
    if(!alreadyHearted){
    this.postService.addHeart(post.postId,this.currentUser.userId).subscribe(
      (Response: ObjavaModel[]) => {
        this.getAllPosts();
        this.allPosts = Response;
      },
      (error: HttpErrorResponse) => {
        alert(error.message);
      }
      );
    }else{
      this.postService.removeHeart(post.postId,this.currentUser.userId).subscribe(
        (Response: ObjavaModel[]) => {
          this.getAllPosts();
          this.allPosts = Response;
        },
        (error: HttpErrorResponse) => {
          alert(error.message);
        }
        );
    }
  }
}
