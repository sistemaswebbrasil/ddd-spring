package com.siswbrasil.ddd.repository;

import com.siswbrasil.ddd.modelo.Pessoa;
import com.siswbrasil.ddd.repository.PessoaRepository;
import com.siswbrasil.ddd.repository.filtro.PessoaFiltro;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = "/clean-database.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
@RunWith(SpringRunner.class)
@DataJpaTest
@TestPropertySource("classpath:application-test.properties")

public class PessoaRepositoryTest {

    @Autowired
    private PessoaRepository sut;

    @Test
    public void deve_procurar_pessoa_pelo_cpf() {
        Optional<Pessoa> optional = sut.findByCpf("38767897100");
        assertThat(optional.isPresent()).isTrue();
        Pessoa pessoa = optional.get();
        assertThat(pessoa.getCodigo()).isEqualTo(3L);
        assertThat(pessoa.getNome()).isEqualTo("Cauê");
        assertThat(pessoa.getCpf()).isEqualTo("38767897100");
    }

    @Test
    public void nao_deve_encontarr_pessoa_de_cpf_inexistente() {
        Optional<Pessoa> optional = sut.findByCpf("845125463252");
        assertThat(optional.isPresent()).isFalse();
    }

    @Test
    public void deve_encontrar_pessoa_pelo_ddd_e_numero() {
        Optional<Pessoa> optional = sut.findByTelefoneDddAndTelefoneNumero("86","35006330");
        assertThat(optional.isPresent()).isTrue();
        Pessoa pessoa = optional.get();
        assertThat(pessoa.getCodigo()).isEqualTo(3L);
        assertThat(pessoa.getNome()).isEqualTo("Cauê");
        assertThat(pessoa.getCpf()).isEqualTo("38767897100");
    }

    @Test
    public void nao_deve_encontar_pessoa_cujo_ddd_e_telefone_nao_estejam_cadastrados() {
        Optional<Pessoa> optional = sut.findByTelefoneDddAndTelefoneNumero("11","999158241");
        assertThat(optional.isPresent()).isFalse();
    }

    @Test
    public void deve_filtrar_pessoas_por_parte_do_nome() {
        PessoaFiltro pessoaFiltro = new PessoaFiltro();
        pessoaFiltro.setNome("a");
        List<Pessoa> pessoas = sut.filtrar(pessoaFiltro);
        assertThat(pessoas.size()).isEqualTo(3);
    }

    @Test
    public void deve_filtrar_pessoas_por_parte_do_cpf() {
        PessoaFiltro pessoaFiltro = new PessoaFiltro();
        pessoaFiltro.setNome("a");
        pessoaFiltro.setCpf("78");
        List<Pessoa> pessoas = sut.filtrar(pessoaFiltro);
        assertThat(pessoas.size()).isEqualTo(2);
    }

    @Test
    public void deve_filtrar_pessoas_por_filtro_composto() {
        PessoaFiltro pessoaFiltro = new PessoaFiltro();
        pessoaFiltro.setCpf("78");
        List<Pessoa> pessoas = sut.filtrar(pessoaFiltro);
        assertThat(pessoas.size()).isEqualTo(3);
    }

    @Test
    public void deve_filtrar_pessoas_pelo_ddd_do_telefone() {
        PessoaFiltro pessoaFiltro = new PessoaFiltro();
        pessoaFiltro.setDdd("21");
        List<Pessoa> pessoas = sut.filtrar(pessoaFiltro);
        assertThat(pessoas.size()).isEqualTo(1);
    }

    @Test
    public void deve_filtrar_pessoas_pelo_numero_do_telefone() {
        PessoaFiltro pessoaFiltro = new PessoaFiltro();
        pessoaFiltro.setTelefone("997504");
        List<Pessoa> pessoas = sut.filtrar(pessoaFiltro);
        assertThat(pessoas.size()).isEqualTo(0);
    }
}
