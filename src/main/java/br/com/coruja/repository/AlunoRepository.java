package br.com.coruja.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.com.coruja.model.Aluno;

public interface AlunoRepository extends CrudRepository<Aluno, Integer> {

    //find, list, save, update e delete

    public Aluno findById(int id);
   
    
}
