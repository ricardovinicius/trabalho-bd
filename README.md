# Aplicação de Banco de Dados
Simulando um ambiente comercial de produtos relacionados ao _cardgame_ "Magic the Gathering" (MTG), através da modelagem de um Modelo de Entidade Relacionmento (MER), prosseguindo com um Modelo Lógico, normalizando as tabelas até a terceira forma normal (3FN), e aplicando estes em um modelo físico utilizando a _engine_ de banco de dados SQLite.

Aluno: Ricardo Vinicius de Almeida Fernandes (22112863)
Disciplina: Banco de Dados
Professora: Maria Cristina Tenório Cavalcante Escarpini


## Modelo de Entidade Relacionamento
![draw drawio](https://github.com/ricardovinicius/trabalho-bd/assets/108153768/7c3e095c-7842-4cf9-8827-807d5a8fbd28)

## Modelo Lógico
![draw2 drawio](https://github.com/ricardovinicius/trabalho-bd/assets/108153768/94d5b419-beea-4859-a160-e8bcb46b094c)

## Criação do banco e das tabelas
O schema com os statements para criacao de tabelas está localizado na pasta /docs/schema.sql
Eles foram executados através da aplicacao construída em java, entretanto, também podem ser executados manualmente dentro do SQLite, ou outro sistema de banco de dados.

## Populando o banco
O banco foi populado utilizando a aplicacao, utilizando dados aleatorios através da biblioteca _faker_ e da API do site Scryfall que possui um repositório de cartas de MTG. As queries executadas para inserir os dados no banco estão em /docs/populate.sql. 

## A aplicacao
Para poder tornar o projeto melhor foi construído uma aplicacao em java para realizar esses processos, se conectando ao banco e realizando as queries necessarias. 

### Como rodar?
#### Pré-Requisitos
* Java 17 
* Maven

### Comando para executar:
```mvn exec:java```

### O que ela irá fazer?
1. Criar o banco
2. Realizar as queries para criar as tabelas necessárias
3. Realizar o processo de popular o banco

## Como visualizar o banco?
### Usando o SQLite
Rode o comando:
```sqlite /data/test.db```
Ou utilize um visualizador de banco SQLite:
1. SQLite Browser: https://sqlitebrowser.org/dl/ (Pessoalmente recomendo)
2. SQLite Viewer: https://sqliteviewer.app/ (Opcao via navegador) 