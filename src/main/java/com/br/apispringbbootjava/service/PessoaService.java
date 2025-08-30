package com.br.apispringbbootjava.service;

import com.br.apispringbbootjava.dto.PessoaModeloDTO;
import com.br.apispringbbootjava.model.PessoaModel;
import com.br.apispringbbootjava.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.StreamSupport;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public ResponseEntity<PessoaModel> cadastrarPessoa(PessoaModel pessoaModel) {
        PessoaModel salvo = pessoaRepository.save(pessoaModel);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }
    // Método responsável pela listagem de pessoas
    public ResponseEntity<Iterable<PessoaModeloDTO>> buscarTodasPessoas() {
          // Busca todas as pessoas do banco de dados usando o repositório
        Iterable<PessoaModel> pessoas = pessoaRepository.findAll();
         // Converte cada PessoaModelo em um PessoaModeloDTO
        Iterable<PessoaModeloDTO> pessoasDTO = StreamSupport.stream(pessoas.spliterator(), false)
        .map(p -> new PessoaModeloDTO(p.getNome(), p.getIdade(), p.getEmail(), p.getCidade()))
        .toList(); // Converte o resultado em uma lista (que também é um Iterable)
        return new ResponseEntity<>(pessoasDTO, HttpStatus.OK);
    }

    public ResponseEntity<PessoaModel> atualizarPessoa(PessoaModel pessoaModel) {
        if (!pessoaRepository.existsById(pessoaModel.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        PessoaModel atualizado = pessoaRepository.save(pessoaModel);
        return new ResponseEntity<>(atualizado, HttpStatus.OK);
    }

    public ResponseEntity<PessoaModel> buscaPessoaPorId(Long id) {
        // Criar um obj do tipo Optional que recebe o PessoaModelo via findById
        Optional<PessoaModel> obj = pessoaRepository.findById(id);

        if (obj.isPresent()) {
            // Converter Optional para PessoaModelo
            return new ResponseEntity<>(obj.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<PessoaModel> atualizacaoParcial(Long id, PessoaModel pessoaModel) {
        Optional<PessoaModel> pessoaModelOptional = pessoaRepository.findById(id);
        if (pessoaModelOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        PessoaModel pessoaExistente = pessoaModelOptional.get();

        if (pessoaModel.getNome() != null) {
            pessoaExistente.setNome(pessoaModel.getNome());
        }
        if (pessoaModel.getEmail() != null) {
            pessoaExistente.setEmail(pessoaModel.getEmail());
        }
        if (pessoaModel.getIdade() != null) {
            pessoaExistente.setIdade(pessoaModel.getIdade());
        }

        PessoaModel atualizado = pessoaRepository.save(pessoaExistente);
        return new ResponseEntity<>(atualizado, HttpStatus.OK);
    }

    public ResponseEntity<Void> deletarPessoa(Long idPessoa) {
        if (pessoaRepository.existsById(idPessoa)) {
            pessoaRepository.deleteById(idPessoa);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
