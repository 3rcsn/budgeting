# Budgeting

A Spring Boot application that integrates with OpenAI via Spring AI to provide conversational AI endpoints.

## Overview

This project is built to deliver high utility with minimal overhead, providing direct integration with OpenAI's chat models using Spring AI. It exposes REST endpoints allowing users to interact with the LLM via either low-level model abstractions or the fluent `ChatClient` API.

## Prerequisites

- **Java 25** (or compatible JDK)
- **OpenAI API Key**

## Configuration

Set the required `OPENAI_API_KEY` environment variable before running the application:

```bash
export OPENAI_API_KEY=your_openai_api_key_here
```

On Windows PowerShell:

```powershell
$env:OPENAI_API_KEY="your_openai_api_key_here"
```

Application properties (`src/main/resources/application.properties`):
- `spring.application.name`: `budgeting`
- `spring.ai.openai.chat.options.model`: `gpt-4o-mini`
- `spring.ai.openai.chat.options.response-format.type`: `TEXT`

## Getting Started

### Build and Run

To build and run the application locally:

```bash
./gradlew bootRun
```

On Windows:

```cmd
gradlew.bat bootRun
```

### Running Tests

To run the test suite:

```bash
./gradlew test
```

*Note: Integration tests requiring OpenAI connectivity will execute when the `OPENAI_API_KEY` environment variable is present.*

## API Endpoints

### 1. Chat via `OpenAiChatModel`

- **Endpoint:** `GET /api/chat`
- **Query Parameter:** `prompt` (string)
- **Example Request:**
  ```http
  GET http://localhost:8080/api/chat?prompt=Hello%20World
  ```

### 2. Chat via `ChatClient`

- **Endpoint:** `GET /api/chat-model`
- **Query Parameter:** `prompt` (string)
- **Example Request:**
  ```http
  GET http://localhost:8080/api/chat-model?prompt=Hello%20World
  ```
