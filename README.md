# Desafio TGID

Olá! Nesse repositório foi desenvolvida de forma inicial o projeto apresentado para o teste de desenvolvedor backend Java da *TGID*. Foquei inicialmente apenas em algumas funcionalidades de maneira superficial para mostrar os conhecimentos de desenvolvimento em springboot.

## OBJETIVOS:
O sistema deve conter ao menos dois usuários: Empresa e Cliente CPF (para Cliente) e CNPJ (para Empresa) devem ser validados. Para cada Empresa, deve haver ao menos um tipo de taxa de sistema que será incidida no momento da transação (seja saque ou depósito).
As Empresas devem ter um saldo que advém dos depósitos e saques realizados por Clientes na sua empresa, e já com o abate das taxas de administração (sistema).
Clientes podem fazer depósitos e saques pelas Empresas (a depender dos saldos
das empresas).
No momento em que a transação é realizada, deve ser enviado um callback para Empresa informando a transação (essa informação pode dar erro caso o sistema esteja instável, utilize o site webhook.site para simular envio), e algum tipo de
notificação para o Cliente (seja e-mail, SMS ou algo do tipo).

### PONTOS PRINCIPAIS:
* Lógica para regras de negócio
* Modelagem de dados
* Clean Code
* Manutenibilidade de código
* Tratamento de erros
* Desacoplamento de componentes
### DIFERENCIAIS:
* Spring Boot
* Documentação
* Propostas de arquitetura
* Testes
### NAO SERÁ AVALIADO:
* Autorização/Autenticação


### Exemplos
  - Criação de Cliente - /api/clientse

```json
{
  "nome": "Thiago",
  "saldo": 50.0,
  "cpf": "12345678901"
}
```

- Obter Cliente Por ID - /api/clientse/{id}

```json
{
  "id": 1,
  "nome": "Thiago",
  "saldo": 50.0,
  "cpf": "12345678901",
  "transacoes": []
}
```

- Criar uma transação - /api/transacao

  
```json
{
  "cliente_id": 1,
  "empresa_id": 1,
  "valor_deposito": 50.0
}
```

