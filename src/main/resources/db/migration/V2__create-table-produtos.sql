create table produtos(

    id bigint not null auto_increment,
    codigo_produto varchar(32) not null unique,
    nome varchar(100) not null,
    preco DECIMAL(10,2) not null,
    ativo tinyint not null,
    
    primary key(id)

);