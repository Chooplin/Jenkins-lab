# Jenkins deploy war Started

## install Jenkins in docker container

### jenkins ��ġ
```shell
docker pull jenkins/jenkins
```

### jenkins ����
```shell
docker run -d -p 8080:8080 -p 50000:50000 --name jenkins-server --restart=on-failure -v jenkins_home:/var/jenkins_home jenkins/jenkins:lts-jdk11
```

### Jenkins login
`http://127.0.0.1:8080`
- Dashboard > Jenkins ���� > Plugins
- Maven Integration �˻�
- check & install

---
### New Item
1. item name �ۼ�
2. Maven project Ŭ��
3. OK Ŭ��

#### General
1. ���� �ۼ�
2. �ҽ� �ڵ� ����
    - git > Repository
        - Repository URL : github URL �ۼ�
        - Credential : private repository ���� ����
    - git > Branch
        - Branch Specifier : ������ �귣ġ ����
3. Build Trigger (���� ����)
    - [ ] Build periodically
        - �ֱ������� cron job�� ���� ��������� ������ �ٽ� ����
    - [x] Poll SCM
        - commit�� ���� ������ �ִ� ��쿡�� �ٽ� ����
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
5. ���� �� ��ġ
    - WAR/EAR files
      ```
      **/*.war
      ```
    - Containers > add Tomcat 9.x Remote
        1. ��Ĺ ���� �� ����
            - `..��Ĺ���/conf/tomcat-users.xml`�� ����
            - `..��Ĺ���/webapps/host-manager/META-INF/context.xml` ���� Value ����
            - `..��Ĺ���/webapps/manager/META-INF/context.xml` ���� Value ����
                - Value: ���� ��� IP ����
                - ��Ų���� ��Ŀ�� ������ �ֱ� ������ ��Ʈ��ũ ������ �ؾ���
            - ��Ĺ ���� : `..��Ĺ���/bin/starup.bat` Ŭ��
        2. credential �߰�
            - ���� ��Ĺ�� ������ �°� credential �߰�
        3. TomcatURL
            - `...��Ĺ���/conf/server.xml`�� ��Ʈ ����
           ``` 
           127.0.0.1:8088
           ```
---