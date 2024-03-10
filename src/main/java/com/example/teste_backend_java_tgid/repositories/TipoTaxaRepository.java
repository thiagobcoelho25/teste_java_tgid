package com.example.teste_backend_java_tgid.repositories;

import com.example.teste_backend_java_tgid.models.entities.TipoTaxa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTaxaRepository extends JpaRepository<TipoTaxa, Long> {
}
