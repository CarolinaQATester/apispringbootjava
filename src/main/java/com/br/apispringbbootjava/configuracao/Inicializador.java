package com.br.apispringbbootjava.configuracao;

import com.br.apispringbbootjava.model.PessoaModel;
import com.br.apispringbbootjava.repository.PessoaRepository;
import com.br.apispringbbootjava.service.PessoaService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// Classe como um componente gerenciado pelo Spring, permitindo a injeção de dependência e execução na inicialização
@Component
public class Inicializador implements CommandLineRunner {

    // Atributo da classe

    private final PessoaService pessoaService;

    // Construtor que injeta a dependência do PessoaRepositorio, permitindo o acesso ao banco de dados
    public Inicializador(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }


    // Método que é executado logo após a inicialização da aplicação
    @Override
    public void run(String... args) throws Exception {

        // Criar objetos que serão utilizados para armazenar na tabela de pessoas
        PessoaModel pessoa1 = new PessoaModel();
        pessoa1.setNome("João");
        pessoa1.setIdade(30);
        pessoa1.setCidade("São Paulo");

        PessoaModel pessoa2 = new PessoaModel();
        pessoa2.setNome("Maria");
        pessoa2.setIdade(25);
        pessoa2.setCidade("Rio de Janeiro");

        PessoaModel pessoa3 = new PessoaModel();
        pessoa3.setNome("Carlos");
        pessoa3.setIdade(40);
        pessoa3.setCidade("Belo Horizonte");

        PessoaModel pessoa4 = new PessoaModel();
        pessoa4.setNome("Ana");
        pessoa4.setIdade(28);
        pessoa4.setCidade("São Paulo");

        PessoaModel pessoa5 = new PessoaModel();
        pessoa5.setNome("Luciano");
        pessoa5.setIdade(35);
        pessoa5.setCidade("Rio de Janeiro");

        PessoaModel pessoa6 = new PessoaModel();
        pessoa6.setNome("Suellen");
        pessoa6.setIdade(22);
        pessoa6.setCidade("Curitiba");

        PessoaModel pessoa7 = new PessoaModel();
        pessoa7.setNome("Felipe");
        pessoa7.setIdade(50);
        pessoa7.setCidade("Salvador");

        PessoaModel pessoa8 = new PessoaModel();
        pessoa8.setNome("Luana");
        pessoa8.setIdade(30);
        pessoa8.setCidade("Porto Alegre");

        PessoaModel pessoa9 = new PessoaModel();
        pessoa9.setNome("Ricardo");
        pessoa9.setIdade(40);
        pessoa9.setCidade("Florianópolis");

        PessoaModel pessoa10 = new PessoaModel();
        pessoa10.setNome("Sofia");
        pessoa10.setIdade(27);
        pessoa10.setCidade("Fortaleza");

        PessoaModel pessoa11 = new PessoaModel();
        pessoa11.setNome("Gustavo");
        pessoa11.setIdade(33);
        pessoa11.setCidade("Recife");

        PessoaModel pessoa12 = new PessoaModel();
        pessoa12.setNome("Fernanda");
        pessoa12.setIdade(45);
        pessoa12.setCidade("São Paulo");

        PessoaModel pessoa13 = new PessoaModel();
        pessoa13.setNome("Roberta");
        pessoa13.setIdade(26);
        pessoa13.setCidade("Manaus");

        PessoaModel pessoa14 = new PessoaModel();
        pessoa14.setNome("Eduardo");
        pessoa14.setIdade(38);
        pessoa14.setCidade("Campinas");

        PessoaModel pessoa15 = new PessoaModel();
        pessoa15.setNome("Camila");
        pessoa15.setIdade(31);
        pessoa15.setCidade("Belém");

        // Efetuar cadastros
        pessoaService.cadastrarPessoa(pessoa1);
        pessoaService.cadastrarPessoa(pessoa2);
        pessoaService.cadastrarPessoa(pessoa3);
        pessoaService.cadastrarPessoa(pessoa4);
        pessoaService.cadastrarPessoa(pessoa5);
        pessoaService.cadastrarPessoa(pessoa6);
        pessoaService.cadastrarPessoa(pessoa7);
        pessoaService.cadastrarPessoa(pessoa8);
        pessoaService.cadastrarPessoa(pessoa9);
        pessoaService.cadastrarPessoa(pessoa10);
        pessoaService.cadastrarPessoa(pessoa11);
        pessoaService.cadastrarPessoa(pessoa12);
        pessoaService.cadastrarPessoa(pessoa13);
        pessoaService.cadastrarPessoa(pessoa14);
        pessoaService.cadastrarPessoa(pessoa15);


    }
}
