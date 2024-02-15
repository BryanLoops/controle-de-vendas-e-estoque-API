create table usuarios(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    data_de_nascimento varchar(100) not null,
    cpf_usuario varchar(14) not null unique,
    cep varchar(9) not null,
    ativo tinyint not null,
    
    primary key(id)

);