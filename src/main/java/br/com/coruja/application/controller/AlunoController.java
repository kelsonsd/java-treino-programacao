package br.com.coruja.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.coruja.model.Aluno;
import br.com.coruja.repository.AlunoRepository;
import br.com.coruja.service.AlunoService;

@RestController
public class AlunoController {

    @Autowired
    private AlunoRepository alunoRepository;

    //find deve buscar por um aluno específico recebendo o ID no path param - GET
    //retornar 200 como status code
    @GetMapping("/aluno/find/{id}")
    public ResponseEntity<?> find(@PathVariable int id) {
        return new ResponseEntity<>(alunoRepository.findById(id), HttpStatus.OK);
    }

    //list deve listar todos os alunos - GET
    //retornar 200 como status code
    @GetMapping("/aluno/list")
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(alunoRepository.findAll(), HttpStatus.OK);
    }

    //save deve salvar um novo aluno - POST
    //retornar 201 como status code
    @PostMapping("/aluno")
    public ResponseEntity<?> cadastrar(@RequestBody Aluno aluno) {
        return new ResponseEntity<>(alunoRepository.save(aluno), HttpStatus.CREATED);
    }

    //put deve atualizar todos os atributos de um aluno recebendo o ID no path param - PUT
    //retornar 200 como status code
    @PutMapping("/aluno/{id}")
    public ResponseEntity<?> update(@RequestBody Aluno aluno) {
        return new ResponseEntity<>(alunoRepository.save(aluno), HttpStatus.OK);
    }

    //delete deve remover um aluno - DELETE
    //retornar 200 como status code
    @DeleteMapping("/aluno/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        Aluno a = alunoRepository.findById(id);
        alunoRepository.delete(a);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    
}
