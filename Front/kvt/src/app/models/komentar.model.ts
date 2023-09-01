import { ObjavaModel } from "./objava.model";

export class KomentarModel {
    id!: number;
    sadzraj!: string;
    korisnik_Id!: number;
    objava!: ObjavaModel;
    d_v_objave!: Date;
  
  }