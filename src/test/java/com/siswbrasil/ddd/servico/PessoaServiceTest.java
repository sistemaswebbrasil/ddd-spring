package com.siswbrasil.ddd.servico;

import com.siswbrasil.ddd.modelo.Pessoa;
import com.siswbrasil.ddd.modelo.Telefone;
import com.siswbrasil.ddd.servico.exception.TelefoneNaoEncontradoException;
import com.siswbrasil.ddd.servico.exception.UnicidadeCpfException;
import com.siswbrasil.ddd.servico.exception.UnicidadeTelefoneException;
import com.siswbrasil.ddd.servico.impl.PessoaServiceImpl;
import com.siswbrasil.ddd.servico.repository.PessoaRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class PessoaServiceTest {

    private static final String NUMERO = "999158241";
    private static final String NOME = "Adriano Faria Alves";
    private static final String CPF = "12345678901";
    private static final String DDD = "22";

    @MockBean
    private PessoaRepository pessoaRepository;

    private PessoaService sut;

    private Pessoa pessoa;
    private Telefone telefone;

    @Before
    public void setUp() throws Exception {
        sut = new PessoaServiceImpl(pessoaRepository);

        pessoa = new Pessoa();
        pessoa.setNome(NOME);
        pessoa.setCpf(CPF);

        telefone = new Telefone();
        telefone.setDdd(DDD);
        telefone.setNumero(NUMERO);

        pessoa.setTelefones(Arrays.asList(telefone));

        //when(pessoaRepository.findByCpf(CPF)).thenReturn(Optional.empty());
        //when(pessoaRepository.findByTelefoneDddAndTelefoneNumero(DDD,NUMERO)).thenReturn(Optional.empty());
    }

    @Test
    public void deve_salvar_pessoa_no_repositorio() throws Exception {
        sut.salvar(pessoa);

        verify(pessoaRepository).save(pessoa);
    }

    @Test(expected = UnicidadeCpfException.class)
    public void nao_deve_salvar_duas_pessoas_com_o_mesmo_cpf() throws  Exception {

        when(pessoaRepository.findByCpf(CPF)).thenReturn(Optional.of(pessoa));

        sut.salvar(pessoa);
    }

    @Test(expected = UnicidadeTelefoneException.class)
    public void nao_deve_salvar_duas_pessoas_com_o_mesmo_telefone() throws Exception {
        when(pessoaRepository.findByTelefoneDddAndTelefoneNumero(DDD,NUMERO)).thenReturn(Optional.of(pessoa));

        sut.salvar(pessoa);
    }

    @Test(expected = TelefoneNaoEncontradoException.class)
    public void deve_retornar_excecao_de_nao_encontrado_quando_nao_existir_pessoa_com_o_ddd_e_numero_de_telefone_() throws Exception {
        sut.buscarPorTelefone(telefone);
    }

    @Test
    public void deve_produrar_pessoa_pelodd_e_numero_do_telefone() throws Exception {
        when(pessoaRepository.findByTelefoneDddAndTelefoneNumero(DDD,NUMERO)).thenReturn(Optional.of(pessoa));

        Pessoa pessoaTeste = sut.buscarPorTelefone(telefone);

        verify(pessoaRepository).findByTelefoneDddAndTelefoneNumero(DDD,NUMERO);

        assertThat(pessoaTeste).isNotNull();
        assertThat(pessoaTeste.getNome()).isEqualTo(NOME);
        assertThat(pessoaTeste.getCpf()).isEqualTo(CPF);

    }
}
