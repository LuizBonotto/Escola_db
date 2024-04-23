package com.dev.aluno_jpa.controller;

import com.dev.aluno_jpa.entity.AlunosEntity;
import com.dev.aluno_jpa.repository.AlunosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AlunosController {
    @Autowired
    private AlunosRepository alunosRepository;
    @GetMapping("/alunos")
    public ResponseEntity get() {
        return new ResponseEntity<>(alunosRepository.getAll(), HttpStatus.OK);
    }

    @PostMapping("/alunos")
    public ResponseEntity post (@RequestBody AlunosEntity alunos) {
        try {
            return new ResponseEntity<>(alunosRepository.insert(alunos), HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/alunos/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(alunosRepository.remove(id), HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/alunos")
    public ResponseEntity put(@RequestBody AlunosEntity aluno) {
        try {
            return new ResponseEntity<>(alunosRepository.update(aluno), HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.NO_CONTENT);
        }
    }


//    @Bean
//    public AlunosEntity init() {
//
//        AlunosEntity aluno1 = new AlunosEntity();
//        aluno1.setId(1L);
//        aluno1.setNome("Aluno 1");
//        aluno1.setEmail("aluno1@gmail.com");
//
//        AlunosEntity aluno2 = new AlunosEntity();
//        aluno2.setId(2L);
//        aluno2.setNome("Aluno 2");
//        aluno2.setEmail("aluno2@gmail.com");
//
//        alunosRepository.insert(aluno1);
//        alunosRepository.insert(aluno2);
//
//        return aluno1;
//    }

}
