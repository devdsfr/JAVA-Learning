package com.example.cadastroveiculo.model;


import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "placa")
    private String placa;

    @OneToOne
    @JoinColumn(name = "modelo_id")
    private Modelo modelo;

    @OneToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    @Column(name = "preco_anuncio")
    private Double precoAnuncio;

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "preco_fipe")
    private Double precoFipe;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;


}

