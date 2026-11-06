-- Baseline migration.
-- 실제 테이블(users, couple_spaces 등)은 DATA_MODEL.md 확정 후 V2부터 추가한다.
-- 규칙: 이미 적용된 마이그레이션 파일은 수정하지 않고 항상 새 버전을 만든다.
-- 시각은 timestamptz(UTC)로 저장하고, 종일 일정 날짜는 date 타입을 사용한다.
SELECT 1;
