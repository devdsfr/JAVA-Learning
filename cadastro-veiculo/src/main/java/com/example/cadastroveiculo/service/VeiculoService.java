package com.example.cadastroveiculo.service;

import com.example.cadastroveiculo.exception.InvalidMarcaModeloAnoException;
import com.example.cadastroveiculo.exception.VeiculoAlreadyExistsException;
import com.example.cadastroveiculo.model.Marca;
import com.example.cadastroveiculo.model.Modelo;
import com.example.cadastroveiculo.model.Veiculo;
import com.example.cadastroveiculo.repository.MarcaRepository;
import com.example.cadastroveiculo.repository.ModeloRepository;
import com.example.cadastroveiculo.repository.VeiculoRepository;
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
    //private FipeService fipeService;

    @Transactional
    public Veiculo cadastrarVeiculo(Veiculo veiculo) {
        // Verifique se a placa já foi cadastrada
        Optional<Veiculo> veiculoExistente = veiculoRepository.findByPlaca(veiculo.getPlaca());

        if (veiculoExistente.isPresent()) {
            throw new VeiculoAlreadyExistsException("Já existe um veículo com a placa " + veiculo.getPlaca());
        }

        // Valide e obtenha a marca e o modelo a partir dos IDs informados
        Marca marca = marcaRepository.findById(veiculo.getMarca().getId())
                .orElseThrow(() -> new InvalidMarcaModeloAnoException("Marca não encontrada."));

        Modelo modelo = modeloRepository.findById(veiculo.getModelo().getId())
                .orElseThrow(() -> new InvalidMarcaModeloAnoException("Modelo não encontrado."));

        if (veiculo.getAno() < 1900 || veiculo.getAno() > LocalDate.now().getYear()) {
            throw new InvalidMarcaModeloAnoException("Ano inválido");
        }
        // Consulte o preço FIPE usando a API externa

        String precoFipe = null;
        try {
            //precoFipe = fipeService.consultarPrecoFipe("1", "26", "2011", "2011-1");
            // faça algo com o resultado
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Preencha os dados do veículo e salve-o no banco de dados
        veiculo.setMarca(marca);
        veiculo.setModelo(modelo);
        veiculo.setPrecoFipe(50.000);
        veiculo.setDataCadastro(LocalDate.now());
        return veiculoRepository.save(veiculo);
    }
    public Veiculo buscarPorPlaca(String placa ) {
        return veiculoRepository.findByPlaca(placa)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado."));
    }

    public Page<Veiculo> listarVeiculos(Pageable pageable) {
        return veiculoRepository.findAll(pageable);
    }

}