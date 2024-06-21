import {Role} from "./role";

export interface AuthApiDto {
  token: string;
  rol: Role;
}
