package br.com.coruja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.coruja.model.Aluno;
import br.com.coruja.repository.AlunoRepository;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;

    //Método para cadastrar aluno
    public ResponseEntity<?> save(Aluno aluno) {
        // if(obj.getNome().equals("")) {
        //     mensagem.setMensagem("O nome precisa ser preenchido");
        //     return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        // }
        // else if(obj.getIdade() < 0) {
        //     mensagem.setMensagem("Informe uma idade válida");
        //     return new ResponseEntity<>(mensagem, HttpStatus.BAD_REQUEST);
        // }
        // else {            
            return new ResponseEntity<>(alunoRepository.save(aluno), HttpStatus.CREATED);
        // }
    }
    
}
