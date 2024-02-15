create table vendas(

    id bigint not null auto_increment,
    id_usuario bigint not null,
    id_produto bigint not null,
    data datetime not null,
    quantidade int not null,
	ativo tinyint not null,
    
    primary key(id)

);