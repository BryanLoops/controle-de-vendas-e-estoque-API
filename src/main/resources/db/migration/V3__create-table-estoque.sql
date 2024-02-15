create table estoque(

    id bigint not null auto_increment,
    produto_id bigint not null,
    data datetime not null,
    nome varchar(200) not null,
    quantidade int not null,
	ativo tinyint not null,
    
    primary key(id),
    constraint fk_estoque_produto_id foreign key(produto_id) references produtos(id)

);