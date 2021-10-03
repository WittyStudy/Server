# Witty Study Server Project

Spring MVC gradle jar project.

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
path:	    "/members"
require:    application/json (MemberRegisterDTO)
```

- login
```
method:     "POST"
path:	    "/members/{memberId}
require:    application/json (MemberLoginDTO)
```

- update-name
```
method:     "PATCH"
path:	    "/members/{memberId}/update/name
require:    text (String)
```

- update-password
```
method:     "PATCH"
path:	    "/members/{memberId}/update/password
require:    text (String)
```

- delete
```
method:	    "DELETE"
path:	    "/members/{memberId}"
```

- test api
```
method:	    "GET"
path:	    "/members"
```

### <a name="BoardAPI">1.2. Board API </a>

- create board

```
method:	    "POST"
path:	    "/members/{memberId}/boards"
require:    application/json (NoticeDTO)
```

- get boards

```
method:	    "GET"
path:	    "/members/{memberId}/boards"
```

- update board

```
method:	    "PUT"
path:	    "/members/{memberId}/boards/{noticeId}"
require:    application/json (NoticeDTO)
```

- delete board

```
method:	    "DELETE"
path:	    "/members/{memberId}/boards/{noticeId}"
require:    application/json (NoticeDTO)
```

### <a name="CommentAPI">1.3. Comment API </a>

- create comment
```
method:     "POST"
path:       "/comments/members/{memberId}/boards/{boardId}"
require:    application/json (CommentDTO)
```

- get comments by memberID
```
method:     "GET"
path:       "/comments/members/{memberId}
```

- get comments by boardId
```
method:     "GET"
path:       "/comments/boards/{boardId}
```

- update comment
```
method:     "PATCH"
path:       "/comments/{commentId}"
required:   application/json (CommentDTO)
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
String  ident
String  name
String  password
String  email
```


- MemberLoginDTO
```
String  ident
String  password
```

### <a name="BoardDTO">2.2. Board DTO</a>

- NoticeDTO
```
String  title
Long    writerId
Long    views
String  date    (format:"yyyy-MM-dd kk:mm:ss")
String  content
```

### <a name="CommentDTO">2.3. Comment DTO</a>

- CommentDTO
```
String  content
```