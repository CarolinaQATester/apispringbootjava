package com.br.apispringbbootjava.controller;

import com.br.apispringbbootjava.model.PessoaModel;
import com.br.apispringbbootjava.repository.PessoaRepository;
import com.br.apispringbbootjava.service.PessoaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    @PostMapping("/pessoas")
    public ResponseEntity<PessoaModel> cadastrarPessoa(@Valid @RequestBody  PessoaModel pessoaModel) {
        PessoaModel pessoaSalva = pessoaService.cadastrarPessoa(pessoaModel);
        return new ResponseEntity<>(pessoaSalva, HttpStatus.CREATED);
    }

    @GetMapping("/pessoas")
    public ResponseEntity<Iterable<PessoaModel>> buscarTodasPessoas() {
        return new ResponseEntity<>(pessoaService.buscarTodasPessoas(), HttpStatus.OK);
    }

    @PutMapping("/pessoas/{id}")
    public ResponseEntity<PessoaModel> atualizarPessoa(@PathVariable Long id,@Valid @RequestBody PessoaModel pessoaModel) {
        Optional<PessoaModel> existente = pessoaService.buscarPessoaPorId(id);
        if (existente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        pessoaModel.setId(id);  // garante que atualiza o registro certo
        PessoaModel atualizada = pessoaService.atualizarPessoa(pessoaModel);
        return ResponseEntity.ok(atualizada);
    }

    @PatchMapping("/pessoas/{id}")
    public ResponseEntity<PessoaModel> atualizacaoParcialPessoa(@PathVariable Long id, @RequestBody PessoaModel pessoaModel) {
        Optional<PessoaModel> atualizada = pessoaService.atualizacaoParcial(id, pessoaModel);
        if (atualizada.isPresent()) {
            return ResponseEntity.ok(atualizada.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    //Rota responsavel pelos testes
    @GetMapping("/testes")
    public Iterable<PessoaModel> teste() {
        return pessoaService.teste("São Paulo");
    }

       @GetMapping("/teste")
    public Iterable<PessoaModel> teste1(){
        return pessoaService.teste1("rio de janeiro", "Sao Paulo");
    }
    @GetMapping("/idade")
    public Iterable<PessoaModel> idade(){
        return pessoaService.teste2(30);
    }
    @GetMapping("/between")
    public Iterable<PessoaModel>between(){
        return pessoaService.teste3(20, 40);
    }
    @DeleteMapping("/pessoas/{id}")
    public ResponseEntity<Void> deletarPessoa(@PathVariable Long id) {
        boolean deletou = pessoaService.deletarPessoa(id);
        if (deletou) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
 
}
