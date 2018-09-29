package com.siswbrasil.tdd.resources;

import com.siswbrasil.tdd.TddApplicationTests;
import com.siswbrasil.tdd.modelo.Pessoa;
import com.siswbrasil.tdd.modelo.Telefone;
import com.siswbrasil.tdd.repository.filtro.PessoaFiltro;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.IsEqual.equalTo;

public class PessoaResourceTest extends TddApplicationTests {


    @Test
    public void deve_procurar_pessoa_pelo_ddd_e_numero() {
        given()
                .pathParam("ddd", "86")
                .pathParam("numero", "35006330")
                .get("/pessoas/{ddd}/{numero}")
                .then()
                .log().body().and()
                .statusCode(HttpStatus.OK.value())

                .body(
                        "codigo", equalTo(3),
                        "nome", equalTo("Cauê"),
                        "cpf", equalTo("38767897100")
                );
    }

    @Test
    public void deve_retornar_erro_nao_encontrado_quando_buscar_pessoa_por_telefone_inexistente() {
        given()
                .pathParam("ddd", "99")
                .pathParam("numero", "99158241")
                .get("/pessoas/{ddd}/{numero}")
                .then()
                .log().body().and()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("erro", equalTo("Não existe pessoa com o telefone (99)99158241"));
    }

    @Test
    public void deve_salvar_nova_pessoa_no_sistema() {
        final Pessoa pessoa = new Pessoa();
        pessoa.setCodigo(333L);
        pessoa.setNome("Lorenzo");
        pessoa.setCpf("62461410720");


        final Telefone telefone = new Telefone();
        telefone.setDdd("79");
        telefone.setNumero("36977168");

        pessoa.setTelefones(Arrays.asList(telefone));

        given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-Type", ContentType.JSON)
                .body(pessoa)
                .when()
                .post("/pessoas")
                .then()
                .log().headers()
                .and()
                .log().body()
                .and()
                .statusCode(HttpStatus.CREATED.value())
                .headers("Location", equalTo("http://localhost:" + porta + "/pessoas/79/36977168"))
                .body(
                        "codigo", equalTo(6),
                        "nome", equalTo("Lorenzo"),
                        "cpf", equalTo("62461410720"));
    }

    @Test
    public void nao_deve_salvar_duas_pessoas_com_o_mesmo_cpf() {
        final Pessoa pessoa = new Pessoa();
        pessoa.setNome("Lorenzo");
        pessoa.setCpf("72788740417");


        final Telefone telefone = new Telefone();
        telefone.setDdd("79");
        telefone.setNumero("36977168");

        pessoa.setTelefones(Arrays.asList(telefone));

        given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-Type", ContentType.JSON)
                .body(pessoa)
                .when()
                .post("/pessoas")
                .then()
                .log().body()
                .and()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("erro", equalTo("Já existe pessoa cadastrada com o CPF '72788740417'"));
    }

    @Test
    public void nao_deve_salvar_duas_pessoas_com_o_mesmo_telefone() {
        final Pessoa pessoa = new Pessoa();
        pessoa.setNome("Adriano");
        pessoa.setCpf("10225134780");

        final Telefone telefone = new Telefone();
        telefone.setDdd("82");
        telefone.setNumero("39945903");

        pessoa.setTelefones(Arrays.asList(telefone));

        given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-Type", ContentType.JSON)
                .body(pessoa)
                .when()
                .post("/pessoas")
                .then()
                .log().body()
                .and()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .body("erro", equalTo("Já existe pessoa cadastrada com o telefone (82)39945903"));
    }

    @Test
    public void deve_filtrar_pelo_nome() {
        final PessoaFiltro filtro = new PessoaFiltro();
        filtro.setNome("a");

        given()
                .request()
                .header("Accept", ContentType.ANY)
                .header("Content-Type", ContentType.JSON)
                .body(filtro)
                .post("/pessoas/filtrar")
                .then()
                .log().body()
                .and()
                .statusCode(HttpStatus.OK.value())
                .body(
                        "codigo", containsInAnyOrder(1, 3, 5),
                        "nome", containsInAnyOrder("Thiago", "Iago", "Cauê"),
                        "cpf", containsInAnyOrder("72788740417", "38767897100", "86730543540"));

    }
}
