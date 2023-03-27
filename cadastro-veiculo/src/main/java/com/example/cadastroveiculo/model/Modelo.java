package com.example.cadastroveiculo.model;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "modelo")
public class Modelo {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "fipe_id")
    private int fipe_id;

    @OneToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

}
