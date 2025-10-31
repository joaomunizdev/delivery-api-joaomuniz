# Delivery Tech API

Sistema de delivery desenvolvido com Spring Boot e Java 21.

## 🚀 Tecnologias
- **Java 21 LTS** (versão mais recente)
- Spring Boot 3.5.7
- Spring Web
- Spring Data JPA
- H2 Database
- Maven

## ⚡ Recursos Modernos Utilizados
- Records (Java 14+)
- Text Blocks (Java 15+)
- Pattern Matching (Java 17+)
- Virtual Threads (Java 21)

## 🏃‍♂️ Como executar
1. **Pré-requisitos:** JDK 21 instalado
2. Clone o repositório
3. Execute: `./mvnw spring-boot:run`
4. Acesse: http://localhost:8080/health

## 📋 Endpoints da API

Abaixo estão os endpoints disponíveis na aplicação.

### ⚙️ Health & Info
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `GET` | `/health` | Status da aplicação (inclui versão Java) |
| `GET` | `/info` | Informações da aplicação (usando Java Records) |
| `GET` | `/h2-console` | Console do banco H2 (desabilitado por padrão) |

### 👤 Clientes (`/clients`)
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/clients` | Cadastra um novo cliente |
| `GET` | `/clients` | Lista todos os clientes ativos |
| `GET` | `/clients/{id}` | Busca um cliente por ID |
| `PUT` | `/clients/{id}` | Atualiza um cliente por ID |
| `DELETE` | `/clients/{id}` | Inativa (soft delete) um cliente por ID |

### 🏪 Restaurantes (`/restaurants`)
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/restaurants` | Cadastra um novo restaurante |
| `GET` | `/restaurants` | Lista restaurantes (ordenados por avaliação) |
| `GET` | `/restaurants/{id}` | Busca um restaurante por ID |
| `GET` | `/restaurants/by_category` | Busca restaurantes por categoria (ex: `?name=Pizza`) |
| `DELETE`| `/restaurants/{id}` | Desativa (soft delete) um restaurante por ID |

### 🍔 Produtos (`/products`)
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/products` | Cadastra um novo produto (requer `restaurantId`) |
| `GET` | `/products/{id}` | Busca um produto por ID |
| `GET` | `/products/by_restaurant` | Busca produtos de um restaurante (ex: `?id=1`) |
| `PATCH` | `/products/{id}/availability` | Altera a disponibilidade (ex: `?available=true`) |
| `DELETE`| `/products/{id}` | Deleta (hard delete) um produto por ID |

### 🛒 Pedidos (`/orders`)
| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/orders` | Cria um novo pedido (requer `clientId`) |
| `GET` | `/orders/by_client` | Busca pedidos de um cliente (ex: `?id=1`) |
| `PATCH` | `/orders/{id}/status` | Altera o status de um pedido (Ex: `{"newStatus": "ENTREGUE"}`) |

## 🔧 Configuração
- Porta: 8080
- Banco: H2 em memória
- Profile: development

## 👨‍💻 Desenvolvedor
[Joao Muniz] - [EXTESPDG-AJWW1]
Desenvolvido com JDK 21 e Spring Boot 3.5.7