package com.example.teste_backend_java_tgid.services;

import com.example.teste_backend_java_tgid.exceptions.BusinessRuleException;
import com.example.teste_backend_java_tgid.models.dtos.outputs.transacao.TransacaoOutputDTO;
import com.example.teste_backend_java_tgid.models.entities.Cliente;
import com.example.teste_backend_java_tgid.models.entities.Empresa;
import com.example.teste_backend_java_tgid.models.entities.TipoTaxa;
import com.example.teste_backend_java_tgid.models.entities.Transacao;
import com.example.teste_backend_java_tgid.models.mappers.TransacaoMapper;
import com.example.teste_backend_java_tgid.repositories.ClienteRepository;
import com.example.teste_backend_java_tgid.repositories.EmpresaRepository;
import com.example.teste_backend_java_tgid.repositories.TransacaoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

@Service
public class TransacaoService {

    private final ClienteRepository clienteRepository;
    private final EmpresaRepository empresaRepository;
    private final TransacaoRepository transacaoRepository;

    private final TransacaoMapper transacaoMapper;
    public TransacaoService(ClienteRepository clienteRepository, EmpresaRepository empresaRepository, TransacaoRepository transacaoRepository, WebClient.Builder webClientBuilder, TransacaoMapper transacaoMapper) {
        this.clienteRepository = clienteRepository;
        this.empresaRepository = empresaRepository;
        this.transacaoRepository = transacaoRepository;
        this.transacaoMapper = transacaoMapper;
    }

    @Transactional
    public TransacaoOutputDTO depositar(Long cliente_id, Long empresa_id, Double quantidade){
        Cliente cliente = clienteRepository.findById(cliente_id).orElseThrow(() -> new EntityNotFoundException("Cliente Não Existe"));
        Empresa empresa = empresaRepository.findById(empresa_id).orElseThrow(() -> new EntityNotFoundException("Empresa Não Existe"));

        TipoTaxa taxa = empresa.getTiposTaxas().get("DEPOSITO");

        if(cliente.getSaldo() < quantidade + taxa.getTaxa()){
            throw new BusinessRuleException("Quantidade Insuficiente para deposito");
        }

        cliente.setSaldo(cliente.getSaldo() - (quantidade + taxa.getTaxa()));
        empresa.setSaldo(quantidade + taxa.getTaxa());
        clienteRepository.save(cliente);
        empresaRepository.save(empresa);

        Transacao transacao = transacaoRepository.save(new Transacao(null, cliente, empresa, taxa,quantidade + taxa.getTaxa()));

        boolean envio = sendViaWebHook(transacao);

        return transacaoMapper.entityToDTO(transacao);
    }

    private boolean sendViaWebHook(Transacao transacao){
        HttpClient httpClient = HttpClient.newHttpClient();

        String apiUrl = "https://webhook.site/137ab524-16f6-41a7-aed4-6694d3f21041";

        Map<Object, Object> requestBody = new HashMap<>();
        requestBody.put("id", transacao.getId());
        requestBody.put("cliente_id", transacao.getCliente().getId());
        requestBody.put("empresa", transacao.getEmpresa().getId());
        requestBody.put("quantidade", transacao.getQuantidade());

        // Cria a solicitação POST
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://webhook.site/137ab524-16f6-41a7-aed4-6694d3f21041"))
                .header("Content-Type", "application/json") // Defina o tipo de conteúdo conforme necessário
                .POST(buildRequestBodyFromMap(requestBody))
                .build();

        try {
            // Envia a solicitação e aguarda a resposta
            HttpResponse<String> httpResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            // Verifica o código de status
            if (httpResponse.statusCode() == 200) {
                // Retorna o corpo da resposta se o código de status for 200 OK
                return true;
            } else {
                // Lança um erro se o código de status não for 200
                throw new BusinessRuleException("Erro de reposta da empresa! " + httpResponse.statusCode());
            }
        } catch (Exception e) {
            throw new BusinessRuleException("Erro ao enviar a solicitação HTTP: " + e.getMessage());
        }

    }

    private static HttpRequest.BodyPublisher buildRequestBodyFromMap(Map<Object, Object> data) {
        // Converte o mapa em uma string JSON para o corpo da solicitação
        String json = "{" +
                data.entrySet().stream()
                        .map(entry -> "\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"")
                        .reduce((entry1, entry2) -> entry1 + "," + entry2)
                        .orElse("") +
                "}";
        return HttpRequest.BodyPublishers.ofString(json);
    }

}
