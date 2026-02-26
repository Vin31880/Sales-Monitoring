# AI Insights Service

An AI-powered microservice that analyses sales data and detects anomalies
using natural language — built with Java 17, Spring Boot, LangChain4j and Ollama.

## What It Does

Takes daily sales records for a site and returns structured AI-generated insights:
- Detects revenue anomalies automatically
- Scores severity as LOW / MEDIUM / HIGH
- Identifies possible causes
- Recommends actions for site managers

## Sample Request

POST /insight/analyse
```json
{
  "siteId": "SITE-001",
  "siteName": "Dublin North",
  "records": [
    {"date": "2026-02-20", "revenue": 5200.00, "transactions": 143},
    {"date": "2026-02-21", "revenue": 5100.00, "transactions": 138},
    {"date": "2026-02-22", "revenue": 3100.00, "transactions": 89}
  ]
}
```

## Sample Response
```json
{
  "siteId": "SITE-001",
  "siteName": "Dublin North",
  "anomalyDetected": true,
  "severity": "HIGH",
  "summary": "Revenue on 2026-02-22 dropped significantly compared to previous days",
  "possibleCauses": ["Decrease in customer traffic", "Price increase or promotion change"],
  "recommendation": "Review marketing strategies and investigate potential reasons for drop"
}
```

## Tech Stack

- Java 17
- Spring Boot 3.2
- LangChain4j 0.27.1
- Ollama (Mistral)
- Lombok
- Jackson

## How To Run

1. Install and start Ollama: https://ollama.ai
2. Pull Mistral model: `ollama run mistral`
3. Start the service: `mvn spring-boot:run`
4. Service runs on port `8085`

## Architecture

This service is part of the Sales Monitoring System — it receives
sales data from the Sales Analysis Service and returns AI-generated
insights back into the microservices ecosystem.