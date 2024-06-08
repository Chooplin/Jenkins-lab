# Jenkins deploy war Started

## install Jenkins in docker container

### jenkins 설치
```shell
docker pull jenkins/jenkins
```

### jenkins 실행
```shell
docker run -d -p 8080:8080 -p 50000:50000 --name jenkins-server --restart=on-failure -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts-jdk11
```

### Jenkins login
`http://127.0.0.1:8080`
- Dashboard > Jenkins 관리 > Plugins
- Maven Integration 검색
- check & install

---
### New Item
1. item name 작성
2. Maven project 클릭
3. OK 클릭

#### General
1. 설명 작성
2. 소스 코드 관리
    - git > Repository
        - Repository URL : github URL 작성
        - Credential : private repository 권한 설정
    - git > Branch
        - Branch Specifier : 배포할 브랜치 설정
3. Build Trigger (빌드 유발)
    - [ ] Build periodically
        - 주기적으로 cron job을 통해 변경사항이 없더라도 다시 빌드
    - [x] Poll SCM
        - commit에 대한 내용이 있는 경우에만 다시 빌드
        - schedule
          ```
          *****
          ```
4. Build
    - Root POM
      ```
      pom.xml
      ```
    - Goals and options
      ```
      clean compile package
      ```
5. 빌드 후 조치
    - WAR/EAR files
      ```
      **/*.war
      ```
    - Containers > add Tomcat 9.x Remote
        1. 톰캣 설정 및 실행
            - `..톰캣경로/conf/tomcat-users.xml`의 설정
            - `..톰캣경로/webapps/host-manager/META-INF/context.xml` 에서 Value 제거
            - `..톰캣경로/webapps/manager/META-INF/context.xml` 에서 Value 제거
                - Value: 접속 허용 IP 설정
                - 젠킨스는 도커로 돌리고 있기 때문에 네트워크 설정을 해야함
            - 톰캣 실행 : `..톰캣경로/bin/starup.bat` 클릭
        2. credential 추가
            - 로컬 톰캣의 설정에 맞게 credential 추가
        3. TomcatURL
            - `...톰캣경로/conf/server.xml`의 포트 설정
           ``` 
           127.0.0.1:8088
           ```
---