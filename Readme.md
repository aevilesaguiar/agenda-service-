











## Tabelas

CREATE TABLE agenda(
id serial primary key,
descricao varchar (255),
data_hora timestamp,
data_criacao timestamp,
paciente_id integer,
CONSTRAINT fk_agenda_paciente FOREIGN KEY(paciente_id) REFERENCES paciente(id)
);



CREATE TABLE paciente(
id serial primary key,
nome varchar (50),
sobrenome varchar (100),
cpf varchar(50),
email varchar (100)
);

##@RequiredArgsConstructor

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

## Artigos Interessantes

https://www.linkedin.com/pulse/limpando-ainda-mais-seu-c%C3%B3digo-spring-boot-com-denis-caminha/?originalSubdomain=pt