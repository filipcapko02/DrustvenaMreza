import { KomentarModel } from "./komentar.model";
import { KorisnikModel } from "./korisnik.model";
import { LikeModel } from "./like.model";

export class ObjavaModel {
  postId!: number;
  postName: string;
  content!: string;
  creationDate!: Date;
  comments!: KomentarModel[];
  likes!: LikeModel[];
  user!: KorisnikModel;
  }