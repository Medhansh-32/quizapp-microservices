# Quiz Microservice Project

[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.10+-green.svg)](https://spring.io/projects/spring-boot)
[![Microservices](https://img.shields.io/badge/Architecture-Microservices-blue.svg)](https://microservices.io/)
[![Netflix Eureka](https://img.shields.io/badge/Discovery-Netflix_Eureka-red.svg)](https://spring.io/projects/spring-cloud-netflix)
[![Docker](https://img.shields.io/badge/Container-Docker-blue.svg)](https://www.docker.com/)

A robust quiz application built with a microservices architecture, allowing users to create, take, and score quizzes across various categories.

## 📋 Table of Contents

- [Architecture Overview](#architecture-overview)
- [Microservices](#microservices)
  - [Question Service](#question-service)
  - [Quiz Service](#quiz-service)
  - [API Gateway](#api-gateway)
  - [Service Discovery](#service-discovery)
- [API Endpoints](#api-endpoints)
- [Technology Stack](#technology-stack)
- [Getting Started](#getting-started)
  - [Using Docker Compose](#using-docker-compose)
  - [Manual Setup](#manual-setup)
- [Development](#development)
- [Contributing](#contributing)


## 🏗️ Architecture Overview

This project follows a microservices architecture pattern with the following components:

```
┌─────────────────┐     ┌─────────────────┐
│   API Gateway   │◄────►Service Registry │
│                 │     │ (Netflix Eureka)│
└────────┬────────┘     └────────┬────────┘
         │                       │
         │    ┌─────────────────┐│
         ├────►Question Service ◄┤
         │    └─────────────────┘│
         │                       │
         │    ┌─────────────────┐│
         └────►  Quiz Service   ◄┘
              └─────────────────┘
```

Services communicate with each other using the OpenFeign client, with service discovery handled by Netflix Eureka. The API Gateway discovers services through Eureka to dynamically route client requests to the appropriate microservice.

## 🧩 Microservices

### Question Service

Manages the question database and provides endpoints for question management and retrieval.

**Key Features:**
- Question storage and retrieval
- Category-based filtering
- Quiz question generation
- Answer verification and scoring

### Quiz Service

Handles quiz creation, management, and scoring by coordinating with the Question Service.

**Key Features:**
- Quiz creation with specified categories and question count
- Retrieving questions for a quiz
- Scoring submitted quiz answers

### API Gateway

Acts as a single entry point for all client requests, routing them to the appropriate microservices. Utilizes the Service Registry to dynamically discover service locations.

**Key Features:**
- Request routing to appropriate microservices
- Load balancing


### Service Discovery

Netflix Eureka server for service registration and discovery, allowing services to find and communicate with each other.

**Key Features:**
- Dynamic service registration
- Load balancing capabilities

## 🔌 API Endpoints

### Question Service Endpoints

Question Service base url : http://localhost:8765/question-service/

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/questions/allQuestions` | Retrieve all questions |
| GET | `/questions/category/{category}` | Get questions by category |
| POST | `/questions/add` | Add a new question |
| GET | `/questions/generate` | Generate questions for a quiz (params: categoryName, numQuestions) |
| POST | `/questions/getQuestions` | Get questions from a list of IDs |
| POST | `/questions/getScore` | Calculate score based on responses |

### Quiz Service Endpoints

Quiz Service base url : http://localhost:8765/question-service/

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/quiz/create` | Create a new quiz (body: QuizDto) |
| GET | `/quiz/get/{id}` | Get questions for a specific quiz |
| POST | `/quiz/submit/{id}` | Submit answers and get score |

## 🔧 Technology Stack

- **Spring Boot**: Framework for creating microservices
- **Netflix Eureka**: Service discovery
- **Spring Cloud Gateway**: API Gateway implementation
- **OpenFeign**: Service-to-service communication
- **JPA/Hibernate**: Database ORM
- **Lombok**: Reduce boilerplate code
- **Spring Web**: RESTful API support
- **Docker**: Containerization
- **Docker Compose**: Multi-container orchestration

## 🚀 Getting Started

### Using Docker Compose

The easiest way to run the entire application stack is using Docker Compose:

1. Build and start all microservices using Docker Compose — without requiring manual cloning of individual projects .

2. 📄 Download Docker Compose File

[Download docker-compose.yml](https://github.com/Medhansh-32/quizapp-microservices/raw/main/docker-compose.yml)

Open terminal and run these commands : 

```bash
cd downloads
docker-compose up --build
```

This will start services all required services in the correct order, including:
- Eureka Server
- Question Service
- Quiz Service
- API Gateway
- Database(s)

Services wil be available at : 

    1. Question Service base-url : http://localhost:8765/question-service/**api-routes**  
 
    2. Quiz Service base-url : http://localhost:8765/quiz-service/**api-routes**  

3. To stop all services:
```bash
docker-compose down
```

4. To view logs for all services:
```bash
docker-compose logs -f
```

### Manual Setup

If you prefer to run services individually:

#### ⚠️ **Note:**
When cloning this project and running it locally, make sure to:
     
    1. Set up PostgreSQL or your preferred database.
    2. Update the `application.properties` file in each service with the correct database connection details.



1. Clone the repository:
```bash
git clone https://github.com/yourusername/quiz-microservice.git
cd quiz-microservice
```

2. Start the Eureka Server first:
```bash
cd eureka-server
./mvnw spring-boot:run
```

3. Start the Question Service:
```bash
cd ../question-service
./mvnw spring-boot:run
```

4. Start the Quiz Service:
```bash
cd ../quiz-service
./mvnw spring-boot:run
```

5. Start the API Gateway:
```bash
cd ../api-gateway
./mvnw spring-boot:run
```

### Configuration

Each service has its own `application.properties` or `application.yml` file for configuration. Ensure these files are properly set up with the correct database connections and service discovery settings, you can take reference from properties file provided in each service.


## 💻 Development

### Project Structure

```
quiz-microservice/
├── eureka-server/        # Service discovery
├── api-gateway/          # API Gateway
├── question-service/     # Question management service
├── quiz-service/         # Quiz management service
└── docker-compose.yml    # Docker-Compose file   
```

### Docker Configuration

The project includes Docker support for all services:

1. Each service has its own `Dockerfile`
2. A central `docker-compose.yml` file orchestrates all services
3. Docker networks handle inter-service communication
4. Volumes are used for persistent data storage


### Adding a New Service

1. Create a new Spring Boot project
2. Add Eureka client dependency
3. Create a Dockerfile for the service
4. Add the service to docker-compose.yml
5. Register the service with the Eureka server
6. Implement the service logic


## 🤝 Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature-name`
3. Commit your changes: `git commit -m 'Add some feature'`
4. Push to the branch: `git push origin feature-name`
5. Open a pull request
