# E-Commerce Microservices Platform (Saga Pattern)

An event-driven distributed e-commerce architecture built with Spring Boot, Spring Cloud Gateway, RabbitMQ, and MySQL.

---

## 🏗 System Architecture

```mermaid
flowchart TD
    subgraph Client ["Client Layer"]
        UI["UI Service (AffableBean Web)"]
    end

    subgraph Gateway ["API Gateway & Security"]
        GW["API Gateway (Spring Cloud Gateway)"]
        AUTH["Security Service (JWT / Auth)"]
    end

    subgraph MessageBroker ["Event Broker (RabbitMQ)"]
        MQ[("RabbitMQ Message Broker")]
    end

    subgraph Services ["Core Business Services (Saga Choreography)"]
        ORDER["Order Service"]
        INV["Inventory Service"]
        PAY["Payment Service"]
    end

    subgraph Storage ["Databases"]
        DB_ORDER[("Order DB")]
        DB_INV[("Inventory DB (MySQL)")]
        DB_PAY[("Payment DB")]
    end

    %% Client and Gateway Routing
    UI -->|HTTP Requests| GW
    GW -->|Validate Token| AUTH
    GW -->|Route Request| ORDER

    %% Saga Event-Driven Flow
    ORDER -.->|Publish: OrderCreatedEvent| MQ
    MQ -.->|Consume: Reserve Stock| INV
    INV -.->|Publish: InventoryReservedEvent| MQ
    MQ -.->|Consume: Process Payment| PAY
    PAY -.->|Publish: PaymentCompleted / Failed| MQ
    MQ -.->|Compensate / Finalize Order| ORDER

    %% Persistence
    ORDER --- DB_ORDER
    INV --- DB_INV
    PAY --- DB_PAY

    classDef gatewayStyle fill:#2563eb,stroke:#1d4ed8,color:#fff;
    classDef serviceStyle fill:#10b981,stroke:#047857,color:#fff;
    classDef brokerStyle fill:#f97316,stroke:#c2410c,color:#fff;
    classDef dbStyle fill:#64748b,stroke:#334155,color:#fff;

    class GW,AUTH gatewayStyle;
    class ORDER,INV,PAY serviceStyle;
    class MQ brokerStyle;
    class DB_ORDER,DB_INV,DB_PAY dbStyle;
```

---

## 🛠 Tech Stack & Services

- **API Gateway:** Spring Cloud Gateway (Routing & Rate Limiting)
- **Security Service:** Spring Security, JWT (Authentication & Authorization)
- **Order Service:** Spring Boot, JPA/Hibernate, MySQL
- **Inventory Service:** Spring Boot, Inventory Management, MySQL
- **Payment Service:** Spring Boot, Transaction Handling
- **UI Service:** Spring Boot, Web UI
- **Message Broker:** RabbitMQ (Event-driven asynchronous communication)