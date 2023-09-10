import { ObjavaModel } from "./objava.model";

export class KomentarModel {
  id!: number;
  content!: string;
  userId!: number;
  post!: ObjavaModel;
  postedOn!: Date;
  
  }