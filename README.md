#  Communication System - Twilio SMS Integration

Este projeto é uma aplicação **Java 21** de alto nível desenvolvida para atuar como um microsserviço de mensageria, utilizando a infraestrutura da **Twilio API**.
O sistema foi projetado seguindo princípios de design sólido, como inversão de controle, abstração de serviços e tratamento global de exceções.

##  Objetivo e Finalidade

O sistema foi arquitetado para garantir que a comunicação com provedores externos seja escalável e segura. A finalidade técnica é demonstrar:
* **Abstração de Interface:** O uso da interface `ISmsSender` permite que a implementação do provedor (Twilio) seja substituída por outro serviço (como AWS SNS ou Infobip) sem alterar a lógica do controlador.
* **Configuração Segura:** Centralização de credenciais sensíveis através de injeção de dependência e variáveis de ambiente.
* **Resiliência de API:** Tratamento padronizado de erros provenientes de serviços externos para evitar falhas silenciosas ou inconsistências no sistema.

##  O que a aplicação faz

A aplicação gerencia o fluxo completo de disparo de SMS:
* **Processamento de Requisições:** Recebe dados estruturados como remetente, DDD, número de telefone e mensagem.
* **Formatação E.164 Automática:** Concatena dinamicamente o prefixo internacional (+55) com os dados de entrada para garantir a conformidade com as operadoras globais.
* **Feedback de Erros:** Captura falhas específicas da Twilio (como `ApiException`) e retorna um objeto JSON detalhado contendo a mensagem de erro, timestamp e status HTTP.

---

## ️ Arquitetura e Organização

O projeto está dividido em pacotes estratégicos para garantir a separação de responsabilidades:

* **`conf`**: Gerencia a configuração e inicialização do SDK da Twilio através de `SenderConfiguration` e `TwilioConfiguration`.
* **`controller`**: Expõe o endpoint REST `/send-sms` para o cliente final.
* **`dto`**: Contém o `SmsRequest`, que utiliza anotações Jackson para garantir o mapeamento correto dos dados JSON.
* **`exception`**: Implementa o `RestExceptionHandler`, um interceptor global que utiliza `@RestControllerAdvice` para gerenciar erros inesperados e falhas de API.
* **`service`**: Contém a implementação real (`TwilioSmsSenderService`) da interface de envio, mantendo o código desacoplado.

---

##  Configuração do Provedor (Trial vs Produção)

### 1. Uso com Número Trial
Por padrão, este projeto está configurado para o ambiente **Trial** da Twilio.
* **Números Verificados:** No modo trial, você **só pode enviar mensagens para números que você verificou manualmente** no painel da Twilio.
* **Trial Number:** O número de origem (sender) deve ser exatamente o fornecido pela Twilio em sua conta de teste.

### 2. Transição para Produção
Para utilizar a aplicação comercialmente e remover as restrições de números verificados:
* **Upgrade:** Realize o upgrade da conta no console da Twilio.
* **Número Próprio:** Adquira um número de produção.
* **Configuração:** Atualize as propriedades `twilio.trial_number` (que passará a ser seu número oficial) e o `auth_token` nas variáveis de ambiente.

> **Nota:** A documentação completa para desenvolvedores sobre como manipular a API está disponível no site oficial da [Twilio Docs](https://www.twilio.com/docs).

---

## 🛠️ Tecnologias Utilizadas

* **Linguagem:** Java 21 (LTS)
* **Gerenciador de Dependências:** Gradle
* **Framework:** Spring Boot 3
* **API Externa:** Twilio SDK
* **Versionamento:** Git & GitHub

---

## 💻 Como Rodar o Projeto

### 1. Pré-requisitos
* Java JDK 21.
* Conta ativa na Twilio.

### 2. Configuração de Credenciais
Configure as chaves no seu `application.properties` ou como variáveis de ambiente no sistema:
``` properties
twilio.account_sid=seu_sid_aqui
twilio.auth_token=seu_token_aqui
twilio.trial_number=seu_numero_twilio
```

### 3. Execução via Terminal
``` Bash
./gradlew bootRun
```
### 4. Testando via Postman
Envie uma requisição POST para http://localhost:8080/send-sms com o seguinte corpo JSON:

JSON
{
    "sender": "NomeDoRemetente",
    "ddd": 11,
    "phoneNumber": "numero verificado",
    "message": "Teste de integração concluído com sucesso!"
}