import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ObjavaModel } from '../models/objava.model';
import { GrupaModel } from '../models/grupa.model';
import { ConfigServiceService } from './config-service.service';
import { ApiServiceService } from './api-service.service';


@Injectable({
  providedIn: 'root'
})
export class ObjavaServiceService {

  constructor(
    private apiService: ApiServiceService,
    private config: ConfigServiceService,
  ) {}

  public getAllPosts(): Observable<ObjavaModel[]> {
    return this.apiService.get(this.config.posts_url);
  }

  public addPost(post: any): Observable<any> {
    return this.apiService.post(this.config.new_post_url, post);
  }

  public addComment(postId: number,comment: any): Observable<any> {
    return this.apiService.put(this.config.add_comment_url + postId, comment);
  }
  public addLike(postId: number,userId: number): Observable<any> {
    return this.apiService.put(`${this.config.like_url}${postId}/${userId}`,"");
  }
  public removeLike(postId: number,userId: number): Observable<any> {
    return this.apiService.delete(`${this.config.unlike_url}${postId}/${userId}`,"");
  }
  public addDislike(postId: number,userId: number): Observable<any> {
    return this.apiService.put(`${this.config.dislike_url}${postId}/${userId}`,"");
  }
  public removeDislike(postId: number,userId: number): Observable<any> {
    return this.apiService.delete(`${this.config.undislike_url}${postId}/${userId}`,"");
  }

  public updatePost(post: ObjavaModel): Observable<ObjavaModel> {
    return this.apiService.put(this.config.update_post_url, post);
  }

  public deletePost(postId: number): Observable<void> {
    return this.apiService.delete(`${this.config.delete_post_url}/${postId}`);
  }


  public createGroup(group: GrupaModel): Observable<void> {
    return this.apiService.post(this.config.add_group_url,group);
  }

  public allGroups(): Observable<GrupaModel[]> {
    return this.apiService.get(this.config.all_groups_url,"");
  }

  public findGroup(id: number): Observable<GrupaModel> {
    return this.apiService.get(`${this.config.group_id_url}${id}`,"");
  }

  public deleteGroup(id: number): Observable<GrupaModel> {
    return this.apiService.delete(`${this.config.group_delete_url}${id}`,"");
  }


}