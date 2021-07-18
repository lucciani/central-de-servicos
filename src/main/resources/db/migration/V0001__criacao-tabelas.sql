create table categoria (
	id bigint not null auto_increment, 
	nome varchar(60) not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;


create table grupo (
	id bigint not null auto_increment, 
	nome varchar(60) not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;

create table grupo_permissao (
	grupo_id bigint not null, 
	permissao_id bigint not null
) engine=InnoDB default charset=utf8;

create table permissao (
	id bigint not null auto_increment, 
	descricao varchar(255) not null, 
	nome varchar(60) not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;

create table prioridade (
	id bigint not null auto_increment, 
	descricao varchar(60) not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;

create table status (
	id bigint not null auto_increment, 
	descricao varchar(60) not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;

create table ticket (
	id bigint not null auto_increment, 
	data_abertura datetime(0) not null, 
	data_atualizacao datetime(0) not null, 
	descricao varchar(255) not null, 
	titulo varchar(100) not null, 
	categoria_id bigint not null, 
	prioridade_id bigint not null, 
	status_id bigint not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;

create table usuario (
	id bigint not null auto_increment, 
	data_cadastro datetime(0) not null, 
	email varchar(60) not null, 
	nome varchar(100) not null, 
	usuario varchar(60) not null, 
	primary key (id)
) engine=InnoDB default charset=utf8;

create table usuario_grupo (
	usuario_id bigint not null, 
	grupo_id bigint not null
) engine=InnoDB default charset=utf8;

alter table grupo_permissao add constraint fk_grupo_permissao_permissao foreign key (permissao_id) references permissao (id);
alter table grupo_permissao add constraint fk_grupo_permissao_grupo foreign key (grupo_id) references grupo (id);
alter table ticket add constraint fk_ticket_categoria foreign key (categoria_id) references categoria (id);
alter table ticket add constraint fk_ticket_prioridade foreign key (prioridade_id) references prioridade (id);
alter table ticket add constraint fk_ticket_status foreign key (status_id) references status (id);
alter table usuario_grupo add constraint fk_usuario_grupo_grupo foreign key (grupo_id) references grupo (id);
alter table usuario_grupo add constraint fk_usuario_grupo_usuario foreign key (usuario_id) references usuario (id);
