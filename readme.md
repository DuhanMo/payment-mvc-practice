# 예제: 간단한 결제 서비스 API 구현 (GPT 작성)
## 시나리오
- 간단한 결제(또는 주문) 처리 시스템의 일부 모듈을 구현해야 합니다.
- 결제요청, 결제정보 조회, 결제 취소의 기능을 제공하는 REST API를 Kotlin(혹은 Java)와 Spring Boot를 활용해 작성해보세요.
- 데이터는 In-Memory DB(H2) 또는 간단한 리스트/맵 자료구조로 관리해도 무방합니다. (테이블 설계 경험을 보여주고 싶다면 H2 DB 사용 추천)

## 1. 요구사항 상세
   
### 결제 생성 (Payment Create)

`POST /payments`
**요청 바디 예시:**
```json
{
"orderId": "ORDER-20250108-0001",
"amount": 15000,
"currency": "KRW"
}
```
**응답 바디 예시:**
```json
{
  "message": "",
  "body": {
    "paymentId": "PAY-20250108-0001",
    "orderId": "ORDER-20250108-0001",
    "amount": 15000,
    "currency": "KRW",
    "createdAt": "2025-01-08T10:15:30",
    "status": "APPROVED"
  }
}
```
**조건:**
- paymentId는 시스템에서 자동 생성 (예: PAY-20250108-XXXX 형태 등 자유롭게)
- createdAt(결제 생성일시)은 서버 시스템 시간을 사용
- 결제 상태(status)는 생성 시 무조건 "APPROVED"로 초기화한다고 가정
- amount가 0 이하이면 오류 응답 (유효성 검증)
- orderId는 유니크하지 않아도 되며, 결제 생성 시 단순 저장

### 결제 조회 (Get Payment)

`GET /payments/{paymentId}`
**응답 바디 예시:**
```json
{
  "message": "",
  "body": {
    "paymentId": "PAY-20250108-0001",
    "orderId": "ORDER-20250108-0001",
    "amount": 15000,
    "currency": "KRW",
    "createdAt": "2025-01-08T10:15:30",
    "status": "APPROVED"
  }
}
```
**조건**:
- paymentId로 찾을 수 없으면 404 Not Found 응답
- 조회 성공 시 결제 상세 정보를 JSON으로 반환

### 결제 취소 (Cancel Payment)

`POST /payments/{paymentId}/cancel`
**응답 바디 예시:**
```json
{
  "message": "",
  "body": {
    "paymentId": "PAY-20250108-0001",
    "status": "CANCELED",
    "canceledAt": "2025-01-08T11:00:00"
  }
}
```
**조건:**
- 결제 상태가 APPROVED일 때만 취소 가능 (이미 CANCELED or 기타 다른 상태인 경우는 에러 처리 또는 무시)
- 성공 시 status를 CANCELED로 업데이트하고, canceledAt 필드를 기록
- 이미 취소된 결제를 다시 취소하려 하면 “이미 취소된 결제”라는 메시지로 에러 반환(또는 No-Op 처리)
- 예외 상황에 대해 적절한 HTTP Status와 메시지를 반환 (예: 400 Bad Request, 404 Not Found 등)

## 2. 기본 구현 조건
- 프레임워크: Spring Boot (Kotlin/Java)
- 빌드 도구: Gradle 혹은 Maven 자유
- JDK 버전: 17
- 데이터 저장:
  - H2 In-Memory 사용 시, schema.sql이나 JPA Entity 구성하여 테이블 생성
  - DB 미사용 시, 단순히 Map<String, Payment> 혹은 리스트 등을 이용해 CRUD 구현 가능
- 프로젝트 구조(예시):
  - controller / service / repository / domain 패키지(디렉터리) 분리 권장
  - 도메인 객체(예: Payment)를 만들어서 관리
- 예외 처리:
  - Spring @ControllerAdvice나 ResponseStatusException 등을 활용
  - 적절한 HTTP Status와 메시지 반환
- 테스트(선택적이지만 권장):
  - JUnit(또는 Kotest)을 통해 핵심 로직 단위 테스트 혹은 API 테스트 작성
  - Controller 레벨이나 Service 레벨에서 간단한 정상 케이스 & 예외 케이스 점검