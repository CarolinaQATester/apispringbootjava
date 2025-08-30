package com.br.apispringbbootjava.controller;

import com.br.apispringbbootjava.dto.PessoaModeloDTO;
import com.br.apispringbbootjava.model.PessoaModel;
import com.br.apispringbbootjava.service.PessoaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Pessoa", description = "Endpoints para gerenciamento de pessoas")
@RestController
@RequestMapping("/api/pessoas")
@CrossOrigin(origins = "*")
public class PessoaController {

    @Autowired
    private PessoaService pessoaService;

    // ------------------- CREATE -------------------
    @Operation(summary = "Criar nova pessoa")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Pessoa criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    @PostMapping
    public ResponseEntity<PessoaModel> criarPessoa(
            @RequestBody(description = "Objeto de pessoa a ser criado", required = true)
            @Valid @RequestBody PessoaModel pessoaModel) {
        PessoaModel pessoaSalva = pessoaService.cadastrarPessoa(pessoaModel);
        return ResponseEntity.status(201).body(pessoaSalva);
    }

    // ------------------- READ ALL -------------------
    @Operation(summary = "Listar todas as pessoas")
    @GetMapping
    public ResponseEntity<List<PessoaModeloDTO>> listarTodasPessoas() {
        List<PessoaModeloDTO> pessoas = pessoaService.buscarTodasPessoas();
        return ResponseEntity.ok(pessoas);
    }

    // ------------------- READ BY ID -------------------
    @Operation(summary = "Buscar pessoa por ID",
            responses = {
                @ApiResponse(responseCode = "200", description = "Pessoa encontrada"),
                @ApiResponse(responseCode = "404", description = "Pessoa não encontrada")
            })
    @GetMapping("/{id}")
    public ResponseEntity<PessoaModel> buscarPessoaPorId(
            @Parameter(name = "id", description = "ID da pessoa", required = true)
            @PathVariable Long id) {
        return pessoaService.buscarPessoaPorId(id);
    }

    // ------------------- UPDATE -------------------
    @Operation(summary = "Atualizar pessoa existente")
    @PutMapping("/{id}")
    public ResponseEntity<PessoaModel> atualizarPessoa(
            @Parameter(name = "id", description = "ID da pessoa a ser atualizada", required = true)
            @PathVariable Long id,
            @RequestBody(description = "Objeto de pessoa com dados atualizados", required = true)
            @Valid @RequestBody PessoaModel pessoaModel) {

        pessoaModel.setId(id); // garante que atualiza o registro correto
        return pessoaService.atualizarPessoa(pessoaModel);
    }

    // ------------------- PATCH -------------------
    @Operation(summary = "Atualização parcial de pessoa")
    @PatchMapping("/{id}")
    public ResponseEntity<PessoaModel> atualizacaoParcialPessoa(
            @Parameter(name = "id", description = "ID da pessoa a ser atualizada parcialmente", required = true)
            @PathVariable Long id,
            @RequestBody(description = "Objeto com campos a serem atualizados")
            @RequestBody PessoaModel pessoaModel) {

        return pessoaService.atualizacaoParcial(id, pessoaModel);
    }

    // ------------------- DELETE -------------------
    @Operation(summary = "Deletar pessoa por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPessoa(
            @Parameter(name = "id", description = "ID da pessoa a ser deletada", required = true)
            @PathVariable Long id) {

        return pessoaService.deletarPessoa(id);
    }

    // ------------------- SECURE ENDPOINT EXAMPLE -------------------
    @Operation(summary = "Exemplo de endpoint seguro", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/protegido")
    public ResponseEntity<String> endpointSeguro() {
        return ResponseEntity.ok("Acesso permitido com token!");
    }
}
