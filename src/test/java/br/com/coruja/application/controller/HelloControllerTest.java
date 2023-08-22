package br.com.coruja.application.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

import br.com.coruja.model.Aluno;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HelloControllerTest {
    @Autowired
    protected WebTestClient web;

    @BeforeEach
    public void setUp() {
        web = web.mutate().responseTimeout(Duration.ofMillis(10000)).build();
    }

    @Test
    public void deve_dizer_ola_mundo() {

        web.get().uri("/api/ola").accept(MediaType.ALL).exchange().expectStatus().isOk().expectBody(String.class)
                .value(c -> assertEquals("Olá Mundo!", c));
    }

    @Test
    public void deve_salvar_aluno() {
        Aluno aluno = new Aluno("Kelson");

        web.post().uri("/aluno")
                .accept(MediaType.ALL)
                .body(BodyInserters.fromValue(aluno))
                .exchange()
                .expectStatus().isCreated().expectBody(Aluno.class)
                .value(c -> assertTrue(c.getId() > 0));
    }

    @Test
    public void deve_listar_alunos() {
        deve_salvar_aluno();
        deve_salvar_aluno();

        conferirAlunosListados(2);
    }

    private void conferirAlunosListados(int total) {
        web.get().uri("/aluno/list")
                .accept(MediaType.ALL)
                .exchange()
                .expectStatus().isOk().expectBodyList(Aluno.class)
                .hasSize(total);
    }

    @Test
    public void deve_atualizar_aluno() {
        Aluno aluno = new Aluno("Kelson");

        web.post().uri("/aluno")
            .accept(MediaType.ALL)
            .body(BodyInserters.fromValue(aluno))
            .exchange()
            .expectStatus().isCreated().expectBody(Aluno.class)
            .value(c -> {
                assertEquals("Kelson", c.getNome());
                aluno.setId(c.getId());
                aluno.setNome("Kelson Atualizado");
            });

            web.put().uri("/aluno/" + aluno.getId())
                .accept(MediaType.ALL)
                .body(BodyInserters.fromValue(aluno))
                .exchange()
                .expectStatus().isOk().expectBody(Aluno.class)
                .value(alunoAtualizado -> {
                    assertEquals("Kelson Atualizado", alunoAtualizado.getNome());
                });
    }

    @Test
    public void deve_remover_aluno() {
        conferirAlunosListados(0);
        deve_salvar_aluno();
        conferirAlunosListados(1);

        web.delete().uri("/aluno/1")
            .accept(MediaType.ALL)
            .exchange()
            .expectStatus().isOk();

        conferirAlunosListados(0);
    }

}