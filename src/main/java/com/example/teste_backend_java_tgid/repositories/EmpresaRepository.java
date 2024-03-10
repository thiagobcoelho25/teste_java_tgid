package com.example.teste_backend_java_tgid.repositories;

import com.example.teste_backend_java_tgid.models.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
