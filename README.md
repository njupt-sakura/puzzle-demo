这是我的一个拼图小游戏，现在还在1.0阶段

## How to install dependencies

```bash
mvn clean -DskipTests
mvn install -DskipTests
```

## How to run

```bash
mvn org.codehaus.mojo:exec-maven-plugin:exec
# or
mvn exec:exec
```

## How to format

```bash
mvn com.spotify.fmt:fmt-maven-plugin:format
```

## How to bundle a binary

```bash
mvn -Pnative package -DskipTests
```
