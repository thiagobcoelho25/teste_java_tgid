package com.example.teste_backend_java_tgid.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    private Double saldo;

    @OneToMany(mappedBy="cliente")
    private List<Transacao> transacoes = new ArrayList<Transacao>();

}
