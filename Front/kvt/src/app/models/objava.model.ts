import { KomentarModel } from "./komentar.model";
import { KorisnikModel } from "./korisnik.model";
import { LikeModel } from "./like.model";

export class ObjavaModel {
    objava_id!: number;
    objava_ime!: string;
    sadzaj!: string;
    creationDate!: Date;
    comments!: KomentarModel[];
    likes!: LikeModel[];
    korisnik!: KorisnikModel;
  }