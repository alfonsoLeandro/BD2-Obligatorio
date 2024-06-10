package com.github.alfonsoleandro.pencaucu.business.authentication.impl;

import com.github.alfonsoleandro.pencaucu.business.authentication.AuthenticationService;
import com.github.alfonsoleandro.pencaucu.business.authentication.exception.AuthExceptionCode;
import com.github.alfonsoleandro.pencaucu.business.authentication.model.request.RegisterDTO;
import com.github.alfonsoleandro.pencaucu.business.authentication.model.response.AuthenticationDTO;
import com.github.alfonsoleandro.pencaucu.business.carrera.exception.CarreraExceptionCode;
import com.github.alfonsoleandro.pencaucu.business.equipo.exception.exception.EquipoExceptionCode;
import com.github.alfonsoleandro.pencaucu.business.jwt.JwtService;
import com.github.alfonsoleandro.pencaucu.persistence.Role;
import com.github.alfonsoleandro.pencaucu.persistence.entity.*;
import com.github.alfonsoleandro.pencaucu.persistence.repository.*;
import com.github.alfonsoleandro.pencaucu.rest.exception.ApiException;
import com.github.alfonsoleandro.pencaucu.rest.exception.ConflictException;
import com.github.alfonsoleandro.pencaucu.rest.exception.NotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author alfonsoLeandro
 * @version 0.0.1
 */
@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AlumnoRepository alumnoRepository;
    private final CarreraRepository carreraRepository;
    private final EleccionRepository eleccionRepository;
    private final EquipoRepository equipoRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    @Transactional
    public AuthenticationDTO register(RegisterDTO registerBody) throws ApiException {
        if (this.usuarioRepository.findByEmail(registerBody.getEmail()).isPresent()) {
            throw new ConflictException(AuthExceptionCode.USUARIO_YA_EXISTE);
        }

        if(registerBody.getIdSubcampeon() == registerBody.getIdCampeon()){
            throw new ConflictException(EquipoExceptionCode.CAMPEON_IGUAL_SUBCAMPEON);
        }

        Usuario usuario = buildUsuario(registerBody);
        this.usuarioRepository.save(usuario);

        Carrera carrera = this.carreraRepository.findById(registerBody.getIdCarrera())
                .orElseThrow(() -> new NotFoundException(CarreraExceptionCode.CARRERA_NOT_FOUND));

        Alumno alumno = new Alumno();
        alumno.setCarrera(carrera);
        alumno.setUsuario(usuario);
        this.alumnoRepository.save(alumno);

        Equipo campeon = this.equipoRepository.findById(registerBody.getIdCampeon())
                .orElseThrow(() -> new NotFoundException(EquipoExceptionCode.CAMPEON_NOT_FOUND));

        Equipo subCampeon = this.equipoRepository.findById(registerBody.getIdSubcampeon())
                .orElseThrow(() -> new NotFoundException(EquipoExceptionCode.SUBCAMPEON_NOT_FOUND));

        Eleccion eleccionAlumno = new Eleccion();
        eleccionAlumno.setAlumno(alumno);
        eleccionAlumno.setCampeon(campeon);
        eleccionAlumno.setSubcampeon(subCampeon);
        this.eleccionRepository.save(eleccionAlumno);

        AuthenticationDTO authenticationDTO = new AuthenticationDTO();
        authenticationDTO.setToken(this.jwtService.generateToken(usuario));

        return authenticationDTO;
    }

    private Usuario buildUsuario(RegisterDTO registerBody) {
        Usuario usuario = new Usuario();
        usuario.setName(registerBody.getName());
        usuario.setSurname(registerBody.getSurname());
        usuario.setCi(registerBody.getCi());
        usuario.setEmail(registerBody.getEmail());
        usuario.setPassword(this.passwordEncoder.encode(registerBody.getPassword()));
        usuario.setPhone(registerBody.getPhone());
        usuario.setRole(Role.STUDENT);
        return usuario;
    }
}
