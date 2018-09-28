package com.siswbrasil.ddd.resources;

import com.siswbrasil.ddd.DddApplicationTests;
import org.junit.Test;
import org.springframework.http.HttpStatus;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class PessoaResourceTest extends DddApplicationTests {


    @Test
    public void deve_procurar_pessoa_pelo_ddd_e_numero() throws Exception {
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
    public void deve_retornar_erro_nao_encontrado_quando_buscar_pessoa_por_telefone_inexistente() throws Exception {
        given()
                .pathParam("ddd", "99")
                .pathParam("numero", "99158241")
                .get("/pessoas/{ddd}/{numero}")
                .then()
                .log().body().and()
                .statusCode(HttpStatus.NOT_FOUND.value())
                .body("erro", equalTo("Não existe pessoa com o telefone (99)99158241"));
    }
}
