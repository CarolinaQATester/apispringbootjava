package com.br.apispringbbootjava.repository;

import com.br.apispringbbootjava.model.PessoaModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends CrudRepository<PessoaModel, Long> {

    Iterable<PessoaModel> findByCidade(String cidade);

}
