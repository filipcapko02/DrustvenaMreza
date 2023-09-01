import { KorisnikModel } from "./korisnik.model";
import { ObjavaModel } from "./objava.model";

export class GrupaModel {
    id!: number;
    ime!: string;
    opis!: string;
    d_v_kreiranja!: Date;
    objave!: ObjavaModel[];
    korisnik!: KorisnikModel;
  }
  