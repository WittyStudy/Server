# Witty Study Server Project

### Spring Server Project for Group Study Application:: WittyStudy


----------------------------------------

## Index

1. ENVIRONMENT


2. SESSION


3. TEST


4. API

- 4.1. Member API

- 4.2. Board API

5. DTO

- 5.1. Member DTO

- 5.2. Board DTO

- 5.3. Comment DTO

----------------------------------------

## 1. ENVIRONMENT

jasypt_encryptor_password="SECRET"

## 2. SESSION

server offers JSESSIONID in response header when progress /members/login

Every request require JSESSIONID except for below
```
"/members/register", 
"/members/login",
"/members/logout", 
"/test/**", 
"/boards"
"/boards/title/**", 
"/boards/{noticeId}" 
```

## 3. TEST

(METHOD=GET)
```
/test                   : simple api test
/test/create/{number}   : create test data
/test/clear             : clear all data
```


## 4. API

### 4.1. Member API

- register
```
method:	    "POST"
path:	    "/members/register"
require:    application/json (MemberRegisterDTO)
```

- login
```
method:     "POST"
path:	    "/members/login
require:    application/json (MemberLoginDTO)
offer:      JSESSIONID (Set-Cookie)
```

- update-name
```
method:     "PATCH"
path:	    "/members/name
require:    text (String)
```

- update-password
```
method:     "PATCH"
path:	    "/members/password
require:    text (String)
```

- delete
```
method:	    "DELETE"
path:	    "/members"
```

- test api
```
method:	    "GET"
path:	    "/members/test"
```

### 4.2. Board API

- create board

```
method:	    "POST"
path:	    "/boards"
require:    application/json (NoticeDTO)
```

- get boards

```
method:	    "GET"
path:	    "/boards"
response:   application/json (List<BoardResponseDTO>)
```

- get board detail (set view = view + 1)

```
method:	    "GET"
path:	    "/boards/{noticeId}"
response:   application/json (NoticeDetailDTO)
```


- get boards by title

```
method:     "GET"
path:       "/boards/title/{query}
require:    String(query)
response:   application/json (List<BoardResponseDTO>)
```

- update board

```
method:	    "PATCH"
path:	    "/boards/{noticeId}"
require:    application/json (NoticeDTO)
```

- delete board

```
method:	    "DELETE"
path:	    "/boards/{noticeId}"
```

### 4.3. Comment API

- create comment
```
method:     "POST"
path:       "/comments"
require:    application/json (CommentCreateDTO)

```

- get comments
```
method:     "GET"
path:       "/comments/{option}/{id}"
options:    "user" || "board" (board requires "id" path variable)
response:    application/json (List<CommentResponseDTO>)
```

- update comment
```
method:     "PATCH"
path:       "/comments"
required:   application/json (CommentUpdateDTO)
```

- delete comment
```
method:     "DELETE"
path:       "/comments/{commentId}"
```

--------------------------------------------

## 5. DTO

### 5.1. Member DTO

- MemberRegisterDTO

```
String  email
String  name
String  password
```


- MemberLoginDTO
```
String  email
String  password
```

### 5.2. Board DTO

- NoticeDTO
```
String  title
String  content
```


- NoticeResponseDTO
```
Long    id
String  title
String  writerName
Long    views
String  date
```

- NoticeDetailDTO
```
Long    id
String  title
String  writerName
Long    views
String  date
String  content
```

### 5.3. Comment DTO

- CommentCreateDTO
```
Long    boardId
String  content
```


- CommentUpdateDTO
```
Long    commentId
String  content
```


- CommentResponseDTO
```
String  content
String  writerName
String  boardTitle
```

