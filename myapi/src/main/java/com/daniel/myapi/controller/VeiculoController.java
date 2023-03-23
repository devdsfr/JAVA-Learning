package com.daniel.myapi.controller;


import com.daniel.myapi.domain.Veiculo;
import com.daniel.myapi.exception.InvalidMarcaModeloAnoException;
import com.daniel.myapi.exception.VeiculoAlreadyExistsException;
import com.daniel.myapi.exception.VeiculoNotFoundException;
import com.daniel.myapi.services.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/veiculos")
public class VeiculoController {
    @Autowired
    private VeiculoService veiculoService;

    @ExceptionHandler(VeiculoNotFoundException.class)
    public ResponseEntity<?> handleVeiculoNotFoundException(VeiculoNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(VeiculoAlreadyExistsException.class)
    public ResponseEntity<?> handleVeiculoAlreadyExistsException(VeiculoAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
    }

    @ExceptionHandler(InvalidMarcaModeloAnoException.class)
    public ResponseEntity<?> handleInvalidMarcaModeloAnoException(InvalidMarcaModeloAnoException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @PostMapping
    public ResponseEntity<Veiculo> cadastrarVeiculo(@RequestBody Veiculo veiculo) {
        Veiculo veiculoCadastrado = veiculoService.cadastrarVeiculo(veiculo);
        return new ResponseEntity<>(veiculoCadastrado, HttpStatus.CREATED);
    }

    @GetMapping("/{placa}")
    public ResponseEntity<Veiculo> buscarPorPlaca(@PathVariable String placa) {
        Veiculo veiculo = veiculoService.buscarPorPlaca(placa);
        return ResponseEntity.ok(veiculo);
    }

    @GetMapping(value = "/{marca}")
    public ResponseEntity<Page<Veiculo>> listarPorMarca(
            @PathVariable("marca") String marca,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "size", defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Veiculo> veiculos = veiculoService.listarPorMarca(marca, pageable);
        return ResponseEntity.ok(veiculos);
    }
}
