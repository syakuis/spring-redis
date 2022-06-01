# Spring Redis

## 주제

- Redis cache
- Redis pub/sub
  - 공지를 각각의 클라이언트에 동시적으로 전달한다.
  - 모든 전달이 끝나면 완료를 알린다.
- Redis store
- Final Catch
  - 일련의 작업으로 동시적으로 처리할때 최종 완료된 시점으로 알기 위한 기능 구현
  - 최종 완료 시점을 빠르고 정확하게 판단하기 한다.
  - redis 뿐만 아니라 kafka, rabbitmq 모두 지원할 것.