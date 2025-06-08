# Aplicativo PetAdota

## Visão Geral
   PetAdota é um aplicativo web Spring Boot que gerencia o registro, a atualização, a listagem e a remoção de animais disponíveis para adoção. Ele utiliza um banco de dados H2 para persistência e expõe endpoints RESTful para comunicação com o usuário.

## Recursos
- Cadastrar novos animais para adoção
- Atualizar detalhes dos animais existentes
- Listar todos os animais disponíveis
- Deletar animais da lista de adoção
- Pesquisar animais por id, raça ou tipo

## Tecnologias Utilizadas
- Java 17
- Spring Boot 2.7.5
- Banco de Dados H2
- Maven 3.9.9

## Estrutura do Projeto
```
petadota
├── src
│   ├── main
│   │   ├── java
│   │   │   └── br
│   │   │       └── com
│   │   │           └── mecenas
│   │   │               └── petadota
│   │   │                   ├── controller
│   │   │                   │   └── AnimalController.java
│   │   │                   ├── model
│   │   │                   │   └── Animal.java
│   │   │                   ├── repository
│   │   │                   │   └── AnimalRepository.java
│   │   │                   ├── service
│   │   │                   │   └── AnimalService.java
│   │   │                   ├── view
│   │   │                   │   └── PetAdotaView.java
│   │   │                   └── PetAdotaApplication.java
│   │   └── resources
│   │       ├── imagens
│   │       │   └── petadota-logo.png   
│   │       ├── application.properties
│   │       └── data.sql
│   └── test
│       └── java
│           └── br
│               └── com
│                   └── mecenas
│                       └── petadota
│                           └── PetAdotaApplicationTests.java
├── pom.xml
└── README.md
```

## Instruções de Configuração
1. Clone o repositório:
```
git clone https://github.com/ivanmecenas/petadota.git
```
2. Navegue até o diretório do projeto:
```
cd petadota
```
3. Compile o projeto usando o Maven:
```
Linux/Mac:
   ./mvnw clean install
Windows:
    mvnw.cmd clean install
IntelliJ IDEA:
   Use a opção de build do Maven no IntelliJ IDEA.   
```
4. Execute o aplicativo:
```
IntelliJ IDEA:
Java Swing
   localize a classe `PetAdotaView` e execute-a.
Serviços REST
   Localize a classe `PetAdotaApplication` e execute-a.
```

## Uso
- O aplicativo será iniciado em `http://localhost:8080` para serviços REST.
- Use ferramentas como Postman ou curl para interagir com os endpoints RESTful.

## Endpoints da API
- `POST /api/animais` - Registrar um novo animal
- `GET /api/animais` - Listar todos os animais
- `GET /api/animais/{id}` - Obter detalhes de um animal específico
- `PUT /api/animais/{id}` - Atualizar um animal existente
- `DELETE /api/animais/{id}` - Remover um animal da lista

## Banco de Dados
O aplicativo utiliza um banco de dados H2 em memória. Os dados de exemplo podem ser inicializados usando o arquivo `data.sql` localizado no diretório `src/main/resources`.

## Licença
Este projeto não está licenciado. Uso livre e modificações são permitidas sem restrições.
