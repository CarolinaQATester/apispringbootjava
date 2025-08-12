package com.br.apispringbbootjava.service;

import com.br.apispringbbootjava.model.PessoaModel;
import com.br.apispringbbootjava.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public PessoaModel cadastrarPessoa(PessoaModel pessoaModel) {
        return pessoaRepository.save(pessoaModel);
    }

    public Iterable<PessoaModel> buscarTodasPessoas() {
        return pessoaRepository.findAll();
    }

    public PessoaModel atualizarPessoa(PessoaModel pessoaModel) {
        return pessoaRepository.save(pessoaModel);
    }

    public Optional<PessoaModel> buscarPessoaPorId(Long id) {
        return pessoaRepository.findById(id);
    }

    public Optional<PessoaModel> atualizacaoParcial(Long id, PessoaModel pessoaModel) {
        Optional<PessoaModel> pessoaModelOptional = pessoaRepository.findById(id);
        if (pessoaModelOptional.isEmpty()) {
            return Optional.empty();
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
        // Outros campos que quiser atualizar...

        PessoaModel atualizado = pessoaRepository.save(pessoaExistente);
        return Optional.of(atualizado);
    }

    public boolean deletarPessoa(Long idPessoa) {
        if (pessoaRepository.existsById(idPessoa)) {
            pessoaRepository.deleteById(idPessoa);
            return true;
        }
        return false;
    }
    //metodo para estar as funcionalidades implementadas no repositorio
    public Iterable<PessoaModel> teste(String cidade) {
        return pessoaRepository.findByCidade(cidade);
    }
}
