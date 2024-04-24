package com.dev.aluno_jpa.entity;

import jakarta.persistence.*;
//import jakarta.validation.constraints.Email;
//import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alunos")
@Data                   //Inicializa os getters e setters
@AllArgsConstructor     //se precisar de argumento ele cria automatico
@NoArgsConstructor      //se não precisar ele cria também

public class AlunosEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "nome", nullable = false)
    private String nome;
    @Column(name = "email", nullable = false, unique = true)
    //@Email
    //@NotBlank(message = "Email não pode ser nulo")
    private String email;
}
