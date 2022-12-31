# banking-system-spring-node

간단한 뱅킹 시스템을 구현합니다. Client와 통신하는 API는 Spring 으로 구현합니다.

# 테크 스펙

- 작성자: 이은택
- 서비스명: 토이뱅크
- 작성일: 2022-12-29

## 요약 ( Summary )

- 사용자가 계좌를 등록, 조회하고 타인에게 이체하는 간단한 뱅킹 시스템을 구현합니다.

## 배경 ( Background )

- Java, Spring MVC, Mysql, redis 등 을 활용하여 토이 뱅크 시스템을 구현합니다.

## 목표 ( Goals )

- 요구사항대로 API를 설계하고 구현합니다.
- 뱅킹 시스템은 외부 뱅킹 시스템과 연동하여 통신합니다.
- 뱅킹 시스템 / 외부 뱅킹 시스템 모두 구현합니다.

### 요구사항

- **사용자는 계좌를 등록할 수 있습니다.**
  - 은행은 Bank_code 로 관리하며 다수의 은행이 존재합니다.
- **사용자는 user_id로 개설 된 계좌를 전부 조회할 수 있습니다.**
  - 특정 은행의 계좌만 조회할 수도 있습니다.
- **사용자는 계좌 번호를 통해 이체를 할 수 있습니다.**
  - 자신의 계좌에 있는 잔액보다 많은 금액을 이체할 수 없습니다.
  - 사용자 자신의 같은 계좌에 이체는 할 수 없습니다.
- **사용자는 이체 내역을 조회할 수 있습니다.**
  - 기본적으로 최근 30일 이내의 내역을 조회합니다.
  - 페이지네이션을 사용하여 더 많은 내역을 조회할 수 있습니다.
- **사용자는 계좌의 입금 / 출금 내역을 조회할 수 있습니다.**
  - 입금 혹은 출금만 선택해서 조회할 수 있습니다.

## 목표가 아닌 것 ( Non-golas )

- 사용자 관리에 대해서는 구현하지 않습니다. 임의의 user_id를 사용합니다.

## 계획 ( Plan )

<aside>
🚧 Sequence Diagram Editor: [https://mermaid.live/edit](https://mermaid.live/edit)

</aside>

### ERD

- bank_account
  | Field               | type         | description     | etc |
  | ------------------- | ------------ | --------------- | --- |
  | id                  | bigint       | bank_account_id |     |
  | bank_account_number | varchar(255) | 계좌 번호       |     |
  | bank_code           | varchar(255) | 은행 코드       |     |
  | user_id             | varchar(255) | 사용자 id       |     |
  | date_created        | dateTime(3)  | 생성일          |     |
  | date_updated        | dateTime(3)  | 수정일          |     |
  | date_deleted        | dateTime(3)  | 삭제일          |     |
- bank_account_balance
  | Field           | type         | description     | etc |
  | --------------- | ------------ | --------------- | --- |
  | id              | bigint       | id              |     |
  | bank_account_id | varchar(255) | bank_account_id |     |
  | amount          | int          | 삭제일          |     |
  | date_created    | dateTime(3)  | 생성일          |     |
  | date_updated    | dateTime(3)  | 수정일          |     |
- transfer
  | Field                  | type         | description    | etc |
  | ---------------------- | ------------ | -------------- | --- |
  | id                     | bigint       | transfer_id    |     |
  | from_bank_account_id   | varchar(255) | 계좌 ID        |     |
  | to_bank_code           | varchar(255) | 이체 은행 코드 |     |
  | to_bank_account_number | varchar(255) | 이체 계좌 번호 |     |
  | transfer_amount        | varchar(255) | 이체 금액      |     |
  | date_created           | varchar(255) | 생성일         |     |
- deposit_withdraw
  | Field           | type         | description  | etc |
  | --------------- | ------------ | ------------ | --- |
  | id              | bigint       | id           |     |
  | bank_account_id | varchar(255) | 계좌 ID      |     |
  | amount          | varchar(255) | 금액         |     |
  | dw_type         | varchar(255) | 입/출금 타입 |     |
  | date_created    | varchar(255) | 생성일       |     |

### 계좌 등록 API

```jsx
Method: POST

URL: /bank-account

Request Body
- bank_code (은행코드): "A001" ("A001", "A002", "A003", "A004")
- bank_account_number (계좌번호): "1234567890" (10자리 숫자)
- user_id (임의의 사용자ID)

Response 200 OK
{
   "id":1,
   "bank_code":"A001",
   "bank_account_number":"1234567890"
}

```

### 계좌 조회 API

```jsx
Method: GET

URL: /bank-account/{user_id}

Request parameter
- bank_code(optional) (은행코드): "A001" ("A001", "A002", "A003", "A004")
- user_id (임의의 사용자ID)

Response 200 OK
{
   "total_count":2,
   "item_list":[
      {
         "bank_account_id":1,
         "bank_code":"A001",
         "total_amount":1000000
      },
      {
         "bank_account_id":2,
         "bank_code":"A002",
         "total_amount":2000000
      }
   ]
}
```

### 이체 API

```jsx
Method: POST

URL: /bank-account/transfer

Request Body
- tx_id (요청하는 곳에서 생성하는 유니크 ID. 8자리 숫자값)
- from_bank_account_id (등록된 계좌 ID)
- to_bank_code (은행코드): "A001" ("A001", "A002", "A003", "A004")
- to_bank_account_number (계좌번호): "1234567890" (10자리 숫자값)
- transfer_amount (이체 금액): 1000

Response 200 OK
{
	 "result": "SUCCESS or FAIL",
}

```

### 이체 내역 조회 API

```jsx
Method: GET

URL: /bank-account/transfer/{from_bank_account_id}

Request parameter
- from_bank_account_id (등록된 계좌 ID)
- day (optional) (날짜 조회 1 당 30일)
- next_id (다음 아이템 ID)
- limit (제한 갯수 디폴트: 50)

Response 200 OK
{
   "total_count":2,
   "item_list":[
      {
         "transfer_id": 1,
         "bank_code": "A001",
				 "bank_account_number": "1234567890",
         "amount":1000000
      },
      {
         "transfer_id": 2,
         "bank_code": "A002",
				 "bank_account_number": "1234567890",
         "amount": 2000000
      },
   ]
}

```

### 거래 내역 조회 API

```jsx
Method: GET

URL: /bank-account/deposit-withdraw

Request parameter
- from_bank_account_id (등록된 계좌 ID)
- day (optional) (날짜 조회 1 당 30일)
- next_id (다음 아이템 ID)
- limit (제한 갯수 디폴트: 50)

Response 200 OK
{
   "total_count":2,
   "item_list":[
      {
         "dw_id": 1,
         "bank_code": "A001",
				 "bank_account_number": "1234567890",
				 "dw_type": 0,
         "amount":1000000
      },
      {
         "dw_id": 2,
         "bank_code": "A002",
				 "bank_account_number": "1234567890",
				 "dw_type": 1,
         "amount": 2000000
      },
   ]
}

```

## 보안, 개인정보, 리스크 ( Security, Privacy, Risks )

-

## 이외 고려사항들 ( Other Considerations )

-

## 마일스톤 ( Milestones )

-

## 질문 ( Open Questions )
