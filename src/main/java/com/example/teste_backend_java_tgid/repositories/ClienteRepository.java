package com.example.teste_backend_java_tgid.repositories;

import com.example.teste_backend_java_tgid.models.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
