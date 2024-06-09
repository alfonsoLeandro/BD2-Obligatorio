package com.github.alfonsoleandro.pencaucu.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author alfonsoLeandro
 * @since 0.0.1
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "alumno")
public class Alumno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne
    @JoinColumn(name = "id_user")
    private Usuario usuario;

    @Column(name = "puntaje")
    private String points;

    @ManyToOne
    @JoinColumn(name = "id_carrera")
    private Carrera carrera;
}
