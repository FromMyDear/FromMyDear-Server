# FromMyDear-Server

Core API server & database services for From My Dear — Powering secure memory archiving & real-time sharing.

## 기술 스택

| 항목 | 버전·선택 | 이유 |
| --- | --- | --- |
| Java | 21 (LTS) | Spring Boot 4가 지원하는 LTS |
| Spring Boot | 4.1.2 | 2026-11 기준 최신 안정 버전 |
| Build | Gradle 8.14.3 (Wrapper 포함) | |
| DB | PostgreSQL 17 + Flyway | 스키마 변경 이력 관리 |
| Test | JUnit 5 + Testcontainers | 실제 PostgreSQL로 통합 테스트 |

## 로컬 실행

필요한 것: JDK 21, Docker

```bash
cp .env.example .env          # 값 확인 후 저장
docker compose up -d          # PostgreSQL 실행
set -a; source .env; set +a   # 환경 변수 적용
./gradlew bootRun
```

확인:

```bash
curl http://localhost:8080/actuator/health   # {"status":"UP"}
curl -i http://localhost:8080/api/v1/me      # 401 + {"code":"UNAUTHORIZED",...}
```

## 테스트

```bash
./gradlew test    # Docker가 실행 중이어야 한다 (Testcontainers)
```

## 폴더 구조

```
src/main/java/com/frommydear/server
├── FromMyDearServerApplication.java
├── global
│   ├── error      # ErrorCode, ErrorResponse, GlobalExceptionHandler
│   └── security   # SecurityConfig (헬스 체크만 공개)
└── (도메인 패키지: auth, couple, media, schedule, list, trip, diary, phrase, notification — 단계별로 추가)
src/main/resources
├── application.yml
└── db/migration/V1__baseline.sql
```

## 공통 규칙

- API 경로: `/api/v1/...`
- 오류 응답: `{"code": "...", "message": "...", "fieldErrors": [...]}`
- 시각: UTC 저장, ISO-8601 문자열로 주고받음

## 남은 작업

- [ ] 인증(Sign in with Apple 검증, 액세스·갱신 토큰)
- [ ] OpenAPI 명세 생성 (Boot 4 호환 springdoc 버전 확인 후 추가)
- [ ] S3 호환 객체 스토리지 로컬 환경 (compose에 추가)
- [ ] APNs 어댑터와 로컬 대체 구현
