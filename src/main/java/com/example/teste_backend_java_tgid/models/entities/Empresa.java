package com.example.teste_backend_java_tgid.models.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cnpj;

    private Double saldo;

    @OneToMany(mappedBy="empresa")
    private List<Transacao> transacoes = new ArrayList<Transacao>();

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name = "nome")
    private Map<String, TipoTaxa> tiposTaxas;
}
