package com.example.teste_backend_java_tgid.services;

import com.example.teste_backend_java_tgid.repositories.EmpresaRepository;
import org.springframework.stereotype.Service;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }


}
