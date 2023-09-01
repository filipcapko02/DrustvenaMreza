import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ConfigServiceService {

  private _api_url = 'http://localhost:8080/api';
  private _auth_url = this._api_url + '/auth';

  private _login_url = this._auth_url + '/login';

  get login_url(): string {
    return this._login_url;
  }

  private _profile_url = this._auth_url + '/profil';

  get profile_url(): string {
    return this._profile_url;
  }

  private _signup_url = this._auth_url + '/signup';

  get signup_url(): string {
    return this._signup_url;
  }

  private _user_url = this._api_url + '/korisnik';


  private _change_password_url = this._user_url + '/updatelozinka';

  get change_password_url(): string {
    return this._change_password_url;
  }

  private _users_url = this._user_url + '/all';

  get users_url(): string {
    return this._users_url;
  }

  private _post_url = this._api_url + '/objava';

  private _posts_url = this._post_url + '/allposts';

  get posts_url(): string {
    return this._posts_url;
  }

  private _like_url = this._post_url + '/like/';

  get like_url(): string {
    return this._like_url;
  }

  private _unlike_url = this._post_url + '/unlike/';

  get unlike_url(): string {
    return this._unlike_url;
  }

  private _add_comment_url = this._post_url + '/comment/';

  get add_comment_url(): string {
    return this._add_comment_url;
  }

  private _new_post_url = this._post_url + '/new';

  get new_post_url(): string {
    return this._new_post_url;
  }

  private _update_post_url = this._post_url + '/update';

  get update_post_url(): string {
    return this._update_post_url;
  }

  private _delete_post_url = this._post_url + '/delete';



  get delete_post_url(): string {
    return this._delete_post_url;
  }

  private _group_url = this._api_url + '/grupa';

  private _all_groups_url = this._group_url + '/sve';



  get all_groups_url(): string {
    return this._all_groups_url;
  }



  private _add_group_url = this._group_url + '/new';



  get add_group_url(): string {
    return this._add_group_url;
  }

  private _group_id_url = this._group_url + '/page/';



  get group_id_url(): string {
    return this. _group_id_url;
  }

  private _group_delete_url = this._group_url + '/delete/';



  get group_delete_url(): string {
    return this. _group_delete_url;
  }
}