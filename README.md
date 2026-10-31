### Budgeting 

The **Budgeting** application is a modern Spring Boot service built with Java 25 and Spring AI that combines personal finance management with OpenAI-powered conversational AI and audio processing capabilities.

---

### Key Capabilities & Modules

#### 1. Conversational AI & Tool Calling
- **Spring AI Integration (`gpt-4o-mini`)**:
  - Exposes REST endpoints (`/api/chat` and `/api/chat-model`) for prompt-based LLM interactions using both low-level `OpenAiChatModel` and the fluent `ChatClient` API.
  - Supports function calling / tool calling allowing AI models to invoke internal system capabilities or domain actions dynamically.

#### 2. Audio Processing (STT & TTS)
- **Speech-to-Text (Transcription)**:
  - Uses OpenAI Whisper (`whisper-1`) via `/api/transcribe` (accepting multipart audio uploads) to transcribe spoken audio clips (e.g., voice expense recordings).
- **Text-to-Speech (Audio Synthesis)**:
  - Uses OpenAI TTS (`gpt-4o-mini-tts` / `nova` voice) via `/api/sinthesize` to convert text inputs into MP3 audio streams.

#### 3. Transaction Management (Clean / Hexagonal Architecture)
- **Domain & Application**:~~~~
  - Core domain models (`Transaction`, `TransactionId`, `Category`) and use case `PersistTransactionUseCase`.
  - Supports transaction categorization (`GROCERIES`, `PHARMA`, `AUTO`).
- **Infrastructure & Persistence**:
  - Exposes `/transactions` REST API for creating and recording expenses.
  - Persists transaction records to MySQL using Spring Data JPA / Hibernate.

---

### Tech Stack Overview

- **Language / Runtime**: Java 25
- **Framework**: Spring Boot 4.1.x, Spring Data JPA
- **AI Framework**: Spring AI 2.0.0-M4 (OpenAI Starter)
- **Database**: MySQL 9.6 (configured via `docker-compose.yaml` on port `3307`)
- **Build & Dependencies**: Gradle with Lombok
- **Testing**: JUnit 5, AssertJ, Spring Boot Integration Tests with environment-conditional OpenAI execution (`@EnabledIfEnvironmentVariable`)

---

### Essential Configuration Reference

#### Environment Variables

Set the `OPENAI_API_KEY` environment variable before running the application:

- **macOS / Linux**:
  ```bash
  export OPENAI_API_KEY="sk-proj-your-openai-api-key"
  ```
- **Windows (PowerShell)**:
  ```powershell
  $env:OPENAI_API_KEY="sk-proj-your-openai-api-key"
  ```
- **Windows (CMD)**:
  ```cmd
  set OPENAI_API_KEY=sk-proj-your-openai-api-key
  ```

#### Application Properties Reference (`application.properties`)

| Property | Default Value | Description |
| :--- | :--- | :--- |
| `spring.application.name` | `budgeting` | Application identifier name |
| `spring.ai.openai.api-key` | `${OPENAI_API_KEY}` | OpenAI API Key |
| `spring.ai.openai.chat.options.model` | `gpt-4o-mini` | OpenAI Chat model |
| `spring.ai.openai.chat.options.response-format.type` | `TEXT` | Response format for chat model (`TEXT` or `JSON_OBJECT`) |
| `spring.ai.model.audio.transcription` | `openai` | Speech-to-text provider |
| `spring.ai.openai.audio.transcription.options.model` | `whisper-1` | Whisper transcription model |
| `spring.ai.openai.audio.transcription.options.language` | `pt` | Default audio spoken language code |
| `spring.ai.openai.audio.transcription.options.temperature` | `0` | Sampling temperature for transcription |
| `spring.ai.openai.audio.transcription.options.response-format` | `text` | Transcription response output format |
| `spring.ai.openai.audio.transcription.options.prompt` | *(custom pt-br prompt)* | Prompt guide for audio transcription context |
| `spring.ai.model.audio.speech` | `openai` | Text-to-speech provider |
| `spring.ai.openai.audio.speech.options.model` | `gpt-4o-mini-tts` | Text-to-speech model |
| `spring-ai.openai.audio.speech.options.voice` | `nova` | Voice persona (`nova`, `alloy`, `echo`, `fable`, `onyx`, `shimmer`) |
| `spring-ai.openai.audio.speech.options.speed` | `1.2` | Voice playback speed |
| `spring.ai.openai.audio.speech.options.response-format` | `mp3` | Synthesized audio output format (`mp3`, `wav`, etc.) |
| `spring.jpa.hibernate.ddl-auto` | `update` | Hibernate schema management |
| `spring.jpa.show-sql` | `true` | SQL query logging |
| `logging.level.org.springframework.ai` | `DEBUG` | Log level for Spring AI operations |

#### Database Connection (Docker Compose)

| Setting | Default |
| :--- | :--- |
| **Host / Port** | `localhost:3307` (mapped to container `3306`) |
| **Database** | `transaction` |
| **Username** | `app` |
| **Password** | `app` |
| **Image** | `mysql:9.6` |

---

### Project Architecture & Layout

```
src/main/java/org/ercsn/budgeting/
├── application/                # Business logic, use cases, inputs/outputs
│   ├── input/
│   ├── output/
│   └── PersistTransactionUseCase.java
├── domain/                     # Entities, value objects, domain interfaces
│   ├── Category.java
│   ├── Transaction.java
│   ├── TransactionId.java
│   └── TransactionRepository.java
├── infrastructure/             # Controllers, JPA entities, database repositories
│   ├── http/
│   └── persistence/
├── ChatClientController.java   # ChatClient REST controller
├── ChatModelController.java    # OpenAiChatModel REST controller
├── TextToSpeechController.java # OpenAI TTS REST controller
└── TranscriptionController.java# OpenAI Whisper REST controller
```