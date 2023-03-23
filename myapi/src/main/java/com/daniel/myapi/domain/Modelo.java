package com.daniel.myapi.domain;

import javax.persistence.*;
@Entity
@Table(name = "modelo")
public class Modelo {

    @Id
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "fipe_id")
    private int fipe_id;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    public String getId() {
        return id;
    }


    public Marca getMarca() {
        return marca;
    }
    public void setMarca(Marca marca) {
        this.marca = marca;
    }


}
