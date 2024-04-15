# 101Digital Assignment Local Development

## Tools

Please install following tools

| Tool               | Version | Required       |
| ------------------ |---------| -------------- |
| Java  | 21       | Yes |
| IntelliJ Idea Community Edition: Java IDE  | Latest  | Yes |
| Git  | Latest  | Yes |
| Docker | Latest  | Yes |
| Postgres  | Latest    | Yes |
| DBeaver: DB client  | Latest  | Yes |
| Talisman | Lastest | Yes |

## Setting up environment

1. **Java 21** : Install Java using [Adoptium](https://adoptium.net/temurin/releases/)

2. **IntelliJ Idea Community Edition: Java IDE** : Install the latest version of Intellij from [here](https://www.jetbrains.com/idea/download)

3. **Git** : Install the latest version of Git from [here](https://git-scm.com/downloads)

4. **Docker** : Install the latest version of Docker from [here](https://docs.docker.com/get-started/#download-and-install-docker)

5. **DBeaver** : Install the latest community edition of DBeaver from [here](https://dbeaver.io/download/)

## Git command-line configuration

Make sure you have configured your user with Git. This information will be used by Git.

```
git config --global user.name "Your Fullname"
git config --global user.email "Your Email"
```

Windows users should use Git bash for better command-line experience.

## Talisman

We use [Talisman](https://github.com/thoughtworks/talisman) to ensure that potential secrets or sensitive information do not leave the developer's workstation.

> It is responsibility of the developer to install talisman on their machine. This step is not yet automated.

To install Talisman run following command.

```
curl https://thoughtworks.github.io/talisman/install.sh > ~/install-talisman.sh
chmod +x ~/install-talisman.sh
```

And, now install talisman pre-commit hook

```
cd <repo>
~/install-talisman.sh pre-commit
```

To test talisman. Create a new file and try to commit.

```
echo "hello" >> test.pem
```

Now, add and commit to Git repository

```
git add test.pem
git commit -am "commiting pem file"
```

The `git commit` command will fail with following talisman exception.

```
Talisman Scan: 3 / 3 <---------------------------------------------------------------------------------------------------------------------------> 100.00%

Talisman Report:
+----------+--------------------------------+----------+
|   FILE   |             ERRORS             | SEVERITY |
+----------+--------------------------------+----------+
| test.pem | The file name "test.pem"       | high     |
|          | failed checks against the      |          |
|          | pattern ^.+\.pem$              |          |
+----------+--------------------------------+----------+
```

Finally, clean up the `test.pem` file.

```
git rm -f test.pem
```

## Local Docker Setup

This document explain how we make it easy for our development teams to do local development. Local development needs to support faster feedback loop and developers should not have to spend effort getting to install and download tools. To do that we have created a docker compose setup that installs adn starts the most important tools that we need for local software development.

The `docker-compose.yml` for the application exists under `docker` folder in the root.

It has two profiles - `default`, `kafka`, and `full`. The default profile runs following services:

* Postgres
* Redis

You run the `default` profile by executing following command:

```
docker-compose up -d
```

The `kafka` profile runs Kafka and Zookeeper.

```
docker-compose --profile kafka up -d
```

The `full` profile runs all the tools. These include

* Postgres
* Redis
* Kafka
* ZooKeeper
* SonarQube
* pgAdmin

You can run it using

```
docker-compose --profile full up -d
```

You can connect to Postgres from your local machine using following command.

```
psql postgresql://postgres:postgres@localhost:5432/postgres
```

You can use DBeaver or pgAdmin to access Postgres. If you use pgAdmin the host has to be `db` because pgAdmin connects with the docker container.

pgAdmin runs on http://localhost:5050/browser/. The default username/password combination is `pg@finx.com/admin`.

### Using local SonarQube

First time you start SonarQube please login to http://localhost:9001 and change default password `admin` to `password`.

To use the locally running SonarQube you can run following command.

```
./gradlew sonarqube -Dsonar.login=admin -Dsonar.password=password
```

SonarQube runs on following location http://localhost:9001/projects

### Using local Swagger

SonarQube runs on following location http://localhost:8080/swagger-ui/index.html
