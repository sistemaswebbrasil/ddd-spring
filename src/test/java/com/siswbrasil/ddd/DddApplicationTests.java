package com.siswbrasil.ddd;

import com.siswbrasil.ddd.servico.impl.PessoaServiceImpl;
import com.siswbrasil.ddd.servico.repository.PessoaRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DddApplicationTests {

    @MockBean
    private PessoaRepository pessoaRepository;

	@Test
	public void contextLoads() {
	}

}
