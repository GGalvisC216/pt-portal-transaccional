# Portal Transaccional Spring Boot

## Despliegue

Ubicarse en la carpeta del proyecto y generar JAR

`
./mvnw clean package
`

Copiar JAR a la carpeta src/main/docker

`
cp target/portal-transaccional-1.0.1.jar src/main/docker
`

Ubicarse en la carpeta src/main/docker

`
cd src/main/docker
`

Iniciar Docker

`
docker-compose up
`