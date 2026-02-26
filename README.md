# Sales Monitoring System

An enterprise-grade microservices platform for storing, tracking and analysing
sales data across all sites — now enhanced with an AI-powered insights layer.

## 🤖 AI Layer — New
The system now includes an **AI Insights Service** built with LangChain4j and Ollama
that automatically detects sales anomalies and generates natural language
recommendations for site managers.

## Architecture

<img width="2582" height="1622" alt="Untitled-2026-02-23-2251" src="https://github.com/user-attachments/assets/cba76c49-3a23-4946-9383-76de24e027fa" />

## Microservices

| Service | Description | Port |
|---|---|---|
| Gateway Service | Single entry point for all API calls | 8080 |
| Security Service | OAuth2 server — password grant flow | 8081 |
| Receiver Service | Exposes pushSalesData and getAllSalesData APIs | 8082 |
| Sales Analysis Service | Processes and analyses sales data | 8083 |
| Config Server | Centralised configuration management | 8888 |
| Registry Service | Eureka service discovery | 8761 |
| AI Insights Service 🆕 | LangChain4j + Ollama anomaly detection | 8085 |

## Tech Stack

**Backend:** Java 17 · Spring Boot · Spring Cloud · Kafka · OAuth2  
**AI:** LangChain4j · Ollama · Mistral · Prompt Engineering  
**Frontend:** Angular 16+ · JWT · NgRx  
**Cloud:** AWS · PCF  
**Security:** OAuth2 · JWT

## Services

### Sender Service UI
Angular 16+ interface for logging in, pushing sales data and viewing records.

### Gateway Service
API Gateway — single entry point for all external API calls.

### Security Service
OAuth2 server implementing password grant flow to secure all backend services.

### Receiver Service
Exposes two core APIs:
- `POST /pushSalesData` — accepts and stores incoming sales records
- `GET /getAllSalesData` — returns all stored sales data

### Sales Analysis Service
Consumes sales data from Receiver Service via Feign client and performs analysis.

### AI Insights Service 🆕
Accepts sales records and returns AI-generated anomaly detection and recommendations.
See [AI Insights Service README](./ai-insights-service/README.md) for full details.

## How To Run

1. Start Registry Service first
2. Start Config Server
3. Start Security Service
4. Start Gateway Service
5. Start Receiver Service
6. Start Sales Analysis Service
7. Install Ollama and run: `ollama run mistral`
8. Start AI Insights Service
9. Start Sender Service UI

## Author

Vinayak Jaybhaye — Senior AI Engineer  
[LinkedIn](https://linkedin.com/in/vinayak-jaybhaye58b41162) ·
[GitHub](https://github.com/Vin31880)



