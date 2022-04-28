
# Bootcamp Java Womarkers Code

##  O que é o projeto?


## Projeto

Pequeno microserviço de agendamento de Meetup

## Tecnologias Usadas

- Java 11
- Maven
- Spring
- JPA
- TDD Test Driven Development
- TDD Test Driven Development
- Azure functions
- Docker
- Postgree
- Flyway
- jwt

## TODO list

- Implementação da classe se servico [ok]
- Testes no service[ok]
- Implementação da classe de controlle da web[ok]
- Testes de contrato no controller[ok]
- Testes unitarios no repository[ok]
- Implementação do repository[ok]
- Swagger[ok]
- Testes locais via postman[ok]
- Deploy no azure[ok]

## DTO

Um objeto de transferência de dados (DTO) é um objeto que transporta dados entre processos. Você pode usar essa técnica
para facilitar a comunicação entre dois sistemas (como uma API e seu servidor) sem expor potencialmente informações
confidenciais.
DTO significa objeto de transferência de dados. Como o nome sugere, um DTO é um objeto feito para transferir dados.

Um DTO deve conter apenas dados, não lógica de negócios. É uma coisa simples e pequena que deve fazer apenas uma tarefa.


## O que é Desenvolvimento Orientado a Testes (TDD)?
Test Driven Development (TDD) é uma abordagem de desenvolvimento de software na qual os casos de teste são desenvolvidos
para especificar e validar o que o código fará. Em termos simples, os casos de teste para cada funcionalidade são criados
e testados primeiro e, se o teste falhar, o novo código é escrito para passar no teste e tornar o código simples e
livre de erros.

O Desenvolvimento Orientado a Testes começa com o design e desenvolvimento de testes para cada pequena funcionalidade
de um aplicativo. A estrutura TDD instrui os desenvolvedores a escreverem novos códigos somente se um teste automatizado
falhar. Isso evita a duplicação de código. A forma completa do TDD é o desenvolvimento orientado a testes.

![img.png](img.png)
O conceito simples de TDD é escrever e corrigir os testes com falha antes de escrever um novo código (antes do
desenvolvimento). Isso ajuda a evitar a duplicação de código à medida que escrevemos uma pequena quantidade de código de
cada vez para passar nos testes. (Os testes nada mais são do que condições de requisitos que precisamos testar para
cumpri-los).

O desenvolvimento orientado a testes é um processo de desenvolvimento e execução de testes automatizados antes do
desenvolvimento real do aplicativo. Portanto, o TDD às vezes também é chamado de Test First Development.


## Por que ModelMapper?

O objetivo do ModelMapper é facilitar o mapeamento de objetos, determinando automaticamente como um modelo de objeto é 
mapeado para outro, com base em convenções, da mesma forma que um humano faria - enquanto fornece uma API simples e 
segura para refatoração para lidar com casos de uso específicos

## @RequiredArgsConstructor

Muita gente usa do @Autowired pra fazer essas injeções de dependências, da seguinte forma:
![img.png](img.png)
O lado ruim de injetar dessa forma é que isso pode te atrapalhar quanto a fazer código de teste, uma vez que vai ser 
complicado de mockar as dependências para testar a classe.

Uma forma de resolver esse problema é usufruindo das habilidades do Spring Boot e criando um construtor que receba essas
dependências como parâmetro:

![img_1.png](img_1.png)

É ai que entra o RequiredArgsConstructor !!

![img_2.png](img_2.png)

e ai em tempo de compilação o Lombok vai criar um construtor com as dependências necessárias, removendo toda a
necessidade de escrever todo o código anterior para usufruir da injeção de dependência do Spring:

![img_3.png](img_3.png)

após compilar o projeto, abri o .class da classe em questão, e no fim do arquivo o Lombok adicionou o construtor com
todas as dependências necessárias

## por que anotar com:

@Component: é um estereótipo genérico para qualquer componente gerenciado pelo Spring;

Podemos usar o @Component no aplicativo para marcar os beans como componentes gerenciados do Spring. O Spring apenas
registra beans com @Component sem procurar por @Service e @Repository os quais são registrados no ApplicationContext
porque são anotados com @Component ,


@Service faz anotações de classes na camada de serviço;
@Repository anota classes na camada de persistência, que atuará como um repositório de banco de dados;

Diferenças entre @Component, @Service e @Repository
A principal diferença entre esses estereótipos é que eles são usados para diferentes classificações.

Quando anotamos uma classe para detecção automática, devemos usar o respectivo estereótipo.

@Componente
Podemos usar o @Component no aplicativo para marcar os beans como componentes gerenciados do Spring. O Spring apenas 
registra beans com @Component sem procurar por @Service e @Repository os quais são registrados no ApplicationContext 
porque são anotados com @Component.

@Service e @Repository são componentes especíais de @Component. Tecnicamente são iguais, mas utilizados para diferentes casos.

@Repositório
O @Repository tem como objetivo criar beans para a parte de persistência, além de capturar exceções específicas de 
persistência e repeti-las novamente como uma das exceções não verificadas e unificadas do Spring.

@Service
Marcamos beans com @Service para indicar que ele está mantendo a lógica de negócios. Não há outra especialidade, além 
do uso na camada de serviço.

#Swagger

Porquê usar Swagger?
O Swagger permite criar a documentação da API de 3 formas: 
1- Automaticamente: Simultaneamente ao desenvolvimento da API é gerada a documentação. 
2- Manualmente: Permite ao desenvolvedor escrever livremente as especificações da API e as publicar posteriormente 
em seu próprio servidor.


http://localhost:8080/swagger-ui/#/

https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockito-junit-example/


##Processo para incluir Segurança

- senha para autorizar gerada na hora quando rodo o spring

- Para gerar o token foi usado o JWT - https://jwt.io/#debugger-io

primeiro fiz um POST com o usuario e senha ele gerou o token
![img_5.png](img_5.png)

copiei o tken do postman e joguei no JWT (adicionamos 10 minutos para esse token), por isso precisamos atualizar o itoken 

![img_4.png](img_4.png)


## O que é Flyway

- ferramenta de migração de banco de dados
- Controle de versão para banco de dados
- Verssiono junto com o código

 **Comandos básicos**


haverá uma pasta na aplicação que por padrão é a db.migration
- Migrate(migro o código para a versão mais atual do script)
- Clean(limpa todo o banco de dados)
- Info(vai ter um objeto que vai dizer se existe alguma coisa pendente)
- Validate(valida se o bd esta aderente as migrações - valida se a tabela está condizente com o modelo atual)
- Undo(desfaz a migração - apenas no interprise e não é indicado pelo o flyway)
- Baseline(se vc já tem o banco e indica que apartir de um momento usar)
- Repair( corrige as tableas de histórico)




## Artigos Interessantes

https://www.linkedin.com/pulse/limpando-ainda-mais-seu-c%C3%B3digo-spring-boot-com-denis-caminha/?originalSubdomain=pt

http://modelmapper.org/

https://imasters.com.br/desenvolvimento/autenticacao-com-jwt-no-spring-boot

https://receitasdecodigo.com.br/spring-framework/component-repository-service-em-spring#:~:text=Podemos%20usar%20o%20%40Component%20no,%2F%2F%20%5B...%5D