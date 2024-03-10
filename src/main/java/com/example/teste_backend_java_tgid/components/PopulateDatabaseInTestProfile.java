package com.example.teste_backend_java_tgid.components;

import com.example.teste_backend_java_tgid.models.entities.Cliente;
import com.example.teste_backend_java_tgid.models.entities.Empresa;
import com.example.teste_backend_java_tgid.models.entities.TipoTaxa;
import com.example.teste_backend_java_tgid.models.entities.Transacao;
import com.example.teste_backend_java_tgid.repositories.ClienteRepository;
import com.example.teste_backend_java_tgid.repositories.EmpresaRepository;
import com.example.teste_backend_java_tgid.repositories.TipoTaxaRepository;
import com.example.teste_backend_java_tgid.repositories.TransacaoRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Profile("TESTE")
//@DependsOn({ "dataSource" })
public class PopulateDatabaseInTestProfile {

    private final ClienteRepository clienteRepository;
    private final EmpresaRepository empresaRepository;
    private final TransacaoRepository transacaoRepository;
    private final TipoTaxaRepository tipoTaxaRepository;

    public PopulateDatabaseInTestProfile(ClienteRepository clienteRepository, EmpresaRepository empresaRepository, TransacaoRepository transacaoRepository, TipoTaxaRepository tipoTaxaRepository) {
        this.clienteRepository = clienteRepository;
        this.empresaRepository = empresaRepository;
        this.transacaoRepository = transacaoRepository;
        this.tipoTaxaRepository = tipoTaxaRepository;
    }

    @PostConstruct
    public void populateDataBase() {
        Cliente c1 = new Cliente(null, "Thiago", "20838869092", 200.0, null);
        Cliente c2 = new Cliente(null, "Guto", "66006116006", 50.0, null);

        Empresa e1 = new Empresa(null, "TGID", "14858661000167", 2000.0, null, null);
        Empresa e2 = new Empresa(null,  "Fazendinha Feliz", "07148429000170", 30.0, null, null);

        TipoTaxa tx1 = new TipoTaxa(null, "SAQUE", 3.0, e1);
        TipoTaxa tx2 = new TipoTaxa(null, "DEPOSITO", 3.0, e1);
        TipoTaxa tx3 = new TipoTaxa(null, "SAQUE", 5.0, e2);
        TipoTaxa tx4 = new TipoTaxa(null, "DEPOSITO", 3.0, e1);

        Transacao t1 = new Transacao(null, c1, e1, tx1, 20.0);
        Transacao t2 = new Transacao(null, c2, e2, tx3, 30.0);
        Transacao t3 = new Transacao(null, c1, e1, tx2, 100.0);

        clienteRepository.saveAll(List.of(c1, c2));
        empresaRepository.saveAll(List.of(e1,e2));
        tipoTaxaRepository.saveAll(List.of(tx1,tx2,tx3,tx4));
        transacaoRepository.saveAll(List.of(t1,t2,t3));
    }
}
