package com.br.apispringbbootjava.repository;

import com.br.apispringbbootjava.model.PessoaModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends CrudRepository<PessoaModel, Long> {

    Iterable<PessoaModel> findByCidade(String cidade);
    Iterable<PessoaModel> findByCidadeOrCidade(String cidade1, String cidade2);
 
    Iterable<PessoaModel> findByGreaterThanEqual(Integer idade);
    Iterable<PessoaModel> findByIdadeBetween(Integer idade1, Integer idade2);

    Iterable<PessoaModel> findByNomeLike(String nome);

}
