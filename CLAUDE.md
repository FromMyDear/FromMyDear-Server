# 작업 지침 (AI·사람 공통)

- 기준 문서: FromMyDear-docs 레포의 PRD.md, DATA_MODEL.md, API_CONTRACT.md. 충돌하면 임의로 바꾸지 말고 이슈로 남긴다.
- 패키지: `com.frommydear.server.<도메인>` 단위의 모듈형 모놀리스. 공통 코드는 `global`에만 둔다.
- 스키마 변경은 Flyway 마이그레이션(`src/main/resources/db/migration`)으로만 한다. 적용된 파일은 수정 금지.
- 모든 오류 응답은 `ErrorResponse` 형식을 따른다. 새 `ErrorCode`는 API_CONTRACT.md에도 추가한다.
- 클라이언트가 보낸 userId·coupleId를 신뢰하지 않는다. 인증 정보에서 사용자를 얻고 멤버십을 서버에서 확인한다.
- 토큰·일기 본문 등 개인 데이터를 로그에 남기지 않는다. 비밀값을 커밋하지 않는다.
- 변경 후 `./gradlew test`를 통과시킨다.
