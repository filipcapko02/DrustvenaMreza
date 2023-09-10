import { KorisnikModel } from "./korisnik.model";
import { ObjavaModel } from "./objava.model";

export class GrupaModel {
  id!: number;
  name: string;
  descripiton!: string;
  creationDate!: Date;
  posts!: ObjavaModel[];
  user!: KorisnikModel;
  }
  