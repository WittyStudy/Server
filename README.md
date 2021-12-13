# Witty Study Server Project

### Spring Server Project for Group Study Application:: WittyStudy

### ENVIRONMENT

jasypt_encryptor_password="SECRET"

### SESSION

server offers JSESSIONID

Every request require JSESSIONID except for "/register", "/login", "/logout", "**/test" 


### TEST

/test(GET)              : simple api test
/test/create/{number}   : create test data
/test/clear             : clear all data

----------------------------------------

## 0. Index

[1. API](#API)

- [1.1. Member API](#MemberAPI)

- [2.1. Board API](#BoardAPI)

[2. DTO](#DTO)

- [2.1. Member DTO](#MemberDTO)

- [2.2. Board DTO](#BoardDTO)

----------------------------------------

## <a name="API">1. API</a>

### <a name="MemberAPI">1.1. Member API</a>

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

### <a name="BoardAPI">1.2. Board API </a>

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

### <a name="CommentAPI">1.3. Comment API </a>

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

## <a name="DTO">2. DTO</a>

### <a name="MemberDTO">2.1. Member DTO </a>

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

### <a name="BoardDTO">2.2. Board DTO</a>

- NoticeDTO
```
String  title
String  content
```


- NoticeResponseDTO
```
String  title
String  writerName
Long    views
String  date
```

### <a name="CommentDTO">2.3. Comment DTO</a>

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

