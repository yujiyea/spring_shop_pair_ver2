# spring-shopping-precourse

## 기능 목록
### 공통 기능
- [x] 상품 존재하는지 체크
- [x] 이미지 URL 체크

### 요구 사항 기능
- [x] 상품 목록 조회
- [x] 특정 상품 목록 조회
- [x] 이름, 가격, 이미지 URL을 입력받아 상품 추가 
- [x] 상품 수정
- [x] 상품 삭제

# 사용자 스토리

## 상품 이름은 공백을 포함하여 최대 15자까지 입력할 수 있음

```gherkin
Given 상품 이름이 "동해물과 백두산이 마르고 닳도록"
 When 상품을 생성하면
 Then 400 Bad Request를 반환한다.
  And "상품의 이름은 15자를 넘길 수 없습니다."라고 응답한다.
```

```gherkin
Given 기존 상품이 존재할 때 
  And 변경하는 상품의 이름이 "동해물과 백두산이 마르고 닳도록"
 When 상품을 수정하면
 Then 400 Bad Request를 반환한다.
  And "상품의 이름은 15자를 넘길 수 없습니다."라고 응답한다.
```

## 상품명은 일부 특수 문자를 사용할 수 있음

```gherkin
Given 상품명이 "()[]+-&/_" 일떄
 When 상품을 추가하면
 Then 200 Success 를 응답한다.
```

```gherkin
Given 상품명이 "$#@^\%" 일떄
 When 상품을 추가하면
 Then  400 Bad Request를 반환한다.
  And "상품의 특수 문자는 '( ), [ ], +, -, &, /, _' 만 포함할 수 있습니다."라고 응답한다.
```

```gherkin
Given 상품명이 "()[]+-&/_" 일떄
 When 상품을 수정하면
 Then 200 Success 를 응답한다.
```

```gherkin
Given 상품명이 "$#@^\%" 일떄
 When 상품을 수정하면
 Then  400 Bad Request를 반환한다.
  And "상품의 특수 문자는 '( ), [ ], +, -, &, /, _' 만 포함할 수 있습니다."라고 응답한다.
```

## 상품명은 비속어를 포함할 수 없음

```gherkin
Given 상품명이 "fuck" 일떄
 When 상품을 추가하면
 Then 400 Bad Request를 반환한다.
  And "상품명에는 욕설이 포함될 수 없습니다." 라고 응답한다.
```

```gherkin
Given 상품명이 "fuck" 일떄
 When 상품을 수정하면
 Then 400 Bad Request를 반환한다.
  And "상품명에는 욕설이 포함될 수 없습니다." 라고 응답한다.
```

# 용어 사전
| 한글명     | 영문명           | 설명                                         |
|---------|---------------|--------------------------------------------|
| 관리자     | admin         | 시스템 기능 관리한다.                               |
| 판매자     | saler         | 상품을 판매하기 위해 상품 등록, 수정, 삭제가 가능하다.           | 
| 구매자     | customer      | 상품을 구매하기 위해 조회, 위시리스트 추가, 삭제가 가능하다.        |
| 사용자 유형  | userType | 관리자, 판매자, 구매자 등 해당 사이트를 사용하는 사람의 유형을 의미한다. |
| 사용자     | user          | 사용자 유형을 가지는 사람을 의미한다.                      |
| 게스트     | guest | 등록되지 않은 사용자를 의미한다.                         |
| 위시리스트   | wishList      | 구매자가 관심있는 상품을 담는다.                         |
| 토큰      | token | JWT 토큰을 의미한다.                              | 
| 회원가입    | signUp        | 사용자가 사이트를 사용하기 위해 등록한다.                    |
| 로그인     | login         | 등록된 사용자가 로그인한다.                            |
| 회원탈퇴    | withdrawl     | 등록된 사용자를 삭제한다.                             |
| 상품      | product       | 이름, 가격, 이미지 URL을 입력받아 등록한 물건               |
| 상품 리스트  | productList | 관리자가 가진 등록된 상품의 목록                         |
| 상품 조회   | search        | 상품 리스트을 불러온다.                              |
| 상품 등록   | createProduct | 판매자가 판매할 상품을 등록한다.                         |
| 상품 삭제   | deleteProduct | 판매자가 판매를 중지할 상품을 삭제한다.                     |
| 상품 수정   | updateProduct | 판매자가 판매할 상품 상세 내용을 수정한다.                   |
| 비속어     | profanity     | 상품에 포함될 수 없는 단어                            |


# 모델링
- **guest**는 **signUp**을 할때, 사용자 이용 방식을 구별할 수 있는 **userType**을 가진다.
- **user**는 **token**을 가진다.
- **user**는 **login**을 할 수 있다.
- **userType**이 **customer**이면
  - **wishList**를 가진다.
  - **search**를 할 수 있다.
  - **withdrawl**를 할 수 있다.
- **userType**이 **saler**이면
  - **product**를 판매한다.
  - **search**를 할 수 있다.
  - **createProduct**를 할 수 있다.
  - **deleteProduct**를 할 수 있다.
  - **updateProduct**를 할 수 있다.

```mermaid
flowchart LR

A([guest]) -->|signUp| B([user])
B --> C{userType}
C -->|customer| D([customer])
C -->|saler| E([saler])
C -->|admin| F([admin])
```

```mermaid
flowchart LR

A([admin])
B([productList])
A -->|have| B
```

```mermaid
flowchart LR

D([customer])
D --> |search| G[productList]
G --> |addWishList| H[wishList]
D --> |deleteWishList| H
D --> |updateWishList| H
```

```mermaid
flowchart LR

A([saler])
A --> |addProduct| B[productList]
A --> |searchProduct| B
A --> |deleteProduct| B
A --> |updateProduct| B
```
---
# 용어 사전 (상품만)
| 한글명     | 영문명           | 설명                           |
|---------|---------------|------------------------------|
| 사용자     | user          | 사용자 유형을 가지는 사람을 의미한다.        |
| 위시리스트   | wishList      | 구매자가 관심있는 상품을 담는다.           |
| 상품      | product       | 이름, 가격, 이미지 URL을 입력받아 등록한 물건 |
| 상품 리스트  | productList | 등록된 상품의 목록                   |
| 상품 조회   | search        | 상품 리스트을 불러온다.                |
| 상품 등록   | createProduct | 상품리스트에 상품을 등록한다.             |
| 상품 삭제   | deleteProduct | 상품리스트에서 상품을 삭제한다.            |
| 상품 수정   | updateProduct | 상품리스트에서 상품 상세 내용을 수정한다.      |
| 비속어     | profanity     | 상품에 포함될 수 없는 단어              |


# 모델링
- **user**는 **productList**를 **search**를 할 수 있다.
- **user**는 **productList**에서 **createProduct**를 할 수 있다.
- **user**는 **productList**에서 **deleteProduct**를 할 수 있다.
- **user**는 **productList**에서 **updateProduct**를 할 수 있다.
- **user**는 **productList**에서 **addWishList**를 할 수 있다.
- **user**는 **productList**에서 **deleteWishList**를 할 수 있다.
- **user**는 **productList**에서 **updateWishList**를 할 수 있다.

```mermaid
flowchart LR

A([user])
A --> |addProduct| B[productList]
A --> |searchProduct| B
A --> |deleteProduct| B
A --> |updateProduct| B
```

```mermaid
flowchart LR

D([user])
D --> |search| G[productList]
G --> |addWishList| H[wishList]
D --> |deleteWishList| H
D --> |updateWishList| H
```