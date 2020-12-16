# Instruções
O projeto utiliza Spring Boot com Java 8 e como gerenciador de dependências temos o Maven, então para que a mesma funcione corretamente é necessário ter o Java 8 em sua máquina ou no ambiente de workspace onde a aplicação será executada na IDE(Eclipse, InteliJ...), e ter o Maven seja ele instalado diretamente em sua máquina ou somente na IDE.
Uma vez que o projeto tenha sido baixado e importado para sua IDE de preferência, em geral as IDEs já baixam as dependências no momento da importação, do contrário é necessário executar um maven update para que ele possa buscar pelas dependências do projeto.
A porta que está sendo utilizada é a 9000, caso ela não esteja disponível em sua máquina, altere para uma porta de sua preferência que esteja livre.
No banco de dados temos a aplicação utilizando o MySQL, para que a aplicação funcione deve-se atentar às seguintes configurações no application.properties

spring.jpa.database=MYSQL
spring.datasource.url=jdbc:mysql://localhost:3306/sincredi?createDatabaseIfNotExist=true&allowPublicKeyRetrieval=true&useSSL=false&useTimezone=true&serverTimezone=UTC
spring.datasource.username=root 
spring.datasource.password=root

A database será criada automáticamente com o nome de sincredi, se por acaso a porta do MySQL seja diferente da 3306, por favor substituir pela porta correta nos campos de usuername e password devem ser alterados para seu usuário e senha de acessos ao banco.
Com estas configurações realizadas, no momento da execução da aplicação, os scripts de banco serão executados automaticamente e as tabelas necessárias serão criadas.

# Tarefa Bônus 4 - Versionamento da API
## Como você versionaria a API da sua aplicação? Que estratégia usar?

Para o versionamento da API poderia ser utilizado o modelo via PATH, com a versão da API devendo ser utilizada logo após a URL base da aplicação
Ex: http://api.assembleia/v1/
Desse modo acredito que além de ficar mais claro qual versão da API deve ser chamada, a URL se torna mais amigável, de modo que somente a numeração deva ser alterada para acessar uma versão específica da API.
