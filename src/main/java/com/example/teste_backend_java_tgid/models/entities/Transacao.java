package com.example.teste_backend_java_tgid.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    Cliente cliente;

    @ManyToOne(fetch=FetchType.LAZY)
    Empresa empresa;

    @ManyToOne(fetch=FetchType.LAZY)
    TipoTaxa tipoTaxa;

    private Double quantidade;

}
