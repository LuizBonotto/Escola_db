package com.dev.aluno_jpa.controller;

import com.dev.aluno_jpa.entity.ProfessoresEntity;
import com.dev.aluno_jpa.repository.ProfessoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ProfessoresController {
    @Autowired
    private ProfessoresRepository professoresRepository;
    @GetMapping("/professores")
    public ResponseEntity get() {
        return new ResponseEntity<>(professoresRepository.getAll(), HttpStatus.OK);
    }

    @PostMapping("/professores")
    public ResponseEntity post (@RequestBody ProfessoresEntity professores) {
        try {
            return new ResponseEntity<>(professoresRepository.insert(professores), HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/professores/{id}")
    public ResponseEntity delete (@PathVariable Long id) {
        try {
            return new ResponseEntity<>(professoresRepository.remove(id), HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @PutMapping("/professores")
    public ResponseEntity put (@RequestBody ProfessoresEntity professores) {
        try {
            return new ResponseEntity<>(professoresRepository.update(professores), HttpStatus.OK);
        } catch (Exception error) {
            return new ResponseEntity<>(error.getMessage(), HttpStatus.NO_CONTENT);
        }
    }



//    @Bean
//    public ProfessoresEntity init2() {
//
//        ProfessoresEntity professor1 = new ProfessoresEntity();
//        professor1.setId(1L);
//        professor1.setNome("Professor 1");
//        professor1.setEmail("professor1@gmail.com");
//
//        ProfessoresEntity professor2 = new ProfessoresEntity();
//        professor2.setId(2L);
//        professor2.setNome("Professor 2");
//        professor2.setEmail("professor2@gmail.com");
//
//
//        professoresRepository.insert(professor1);
//        professoresRepository.insert(professor2);
//
//        return professor1;
//    }

}
