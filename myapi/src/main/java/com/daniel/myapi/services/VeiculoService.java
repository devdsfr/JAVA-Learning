package com.daniel.myapi.services;


import com.daniel.myapi.api.FipeApi;
import com.daniel.myapi.domain.Marca;
import com.daniel.myapi.domain.Modelo;
import com.daniel.myapi.domain.Veiculo;
import com.daniel.myapi.exception.InvalidMarcaModeloAnoException;
import com.daniel.myapi.exception.VeiculoAlreadyExistsException;
import com.daniel.myapi.repositories.MarcaRepository;
import com.daniel.myapi.repositories.ModeloRepository;
import com.daniel.myapi.repositories.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private ModeloRepository modeloRepository;

    @Autowired
    private FipeApi fipeApi;

    @Transactional
    public Veiculo cadastrarVeiculo(Veiculo veiculo) {
        // Verifique se a placa já foi cadastrada
        Optional<Veiculo> veiculoExistente = veiculoRepository.findByPlaca(veiculo.getPlaca());

        if (veiculoExistente.isPresent()) {
            throw new VeiculoAlreadyExistsException("Já existe um veículo com a placa " + veiculo.getPlaca());
        }

        // Valide e obtenha a marca e o modelo a partir dos IDs informados
        Marca marca = marcaRepository.findById(veiculo.getModelo().getMarca().getId())
                .orElseThrow(() -> new InvalidMarcaModeloAnoException("Marca não encontrada."));

        Modelo modelo = modeloRepository.findById(veiculo.getModelo().getId())
                .orElseThrow(() -> new InvalidMarcaModeloAnoException("Modelo não encontrado."));

        if (veiculo.getAno() < 1900 || veiculo.getAno() > LocalDate.now().getYear()) {
            throw new InvalidMarcaModeloAnoException("Ano inválido");
        }

        // Consulte o preço FIPE usando a API externa
        BigDecimal precoFipe = fipeApi.consultarPrecoFipe(marca, modelo, veiculo.getAno());

        // Preencha os dados do veículo e salve-o no banco de dados
        veiculo.getModelo().setMarca(marca);
        veiculo.setModelo(modelo);
        veiculo.setPrecoFipe(precoFipe);
        veiculo.setDataCadastro(LocalDate.now());
        return veiculoRepository.save(veiculo);
    }
    public Veiculo buscarPorPlaca(String placa) {
        return veiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado."));
    }

    public Page<Veiculo> listarPorMarca(String marca, Pageable pageable) {
        Marca marcaEntity = marcaRepository.findByNomeIgnoreCase(marca)
                .orElseThrow(() -> new RuntimeException("Marca não encontrada."));
        return veiculoRepository.findByMarca(marcaEntity, pageable);
    }

}