package com.daniel.myapi.domain;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

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

//    @OneToOne
//    @JoinColumn(name = "marca_id")
//    private Marca marca;

    @Column(name = "preco_anuncio")
    private Double precoAnuncio;

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "preco_fipe")
    private BigDecimal precoFipe;

    @Column(name = "data_cadastro")
    private LocalDate dataCadastro;


    public String getPlaca() {
        return placa;
    }


    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setPrecoFipe(BigDecimal precoFipe) {
        this.precoFipe = precoFipe;
    }

    public void setDataCadastro(LocalDate dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

//    public Marca getMarca() {
//        return marca;
//    }
//    public void setMarca(Marca marca) {
//        this.marca = marca;
//    }


}

