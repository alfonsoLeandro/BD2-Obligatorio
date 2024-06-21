import { Role } from './role';

export interface AuthApiDto {
    id: number;
    token: string;
    rol: Role;
}
