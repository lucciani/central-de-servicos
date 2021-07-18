create table categoria (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table prioridade (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table status (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table ticket (id bigint not null auto_increment, data_abertura datetime(6) not null, data_atualizacao datetime(6) not null, descricao varchar(255) not null, titulo varchar(255) not null, categoria_id bigint not null, prioridade_id bigint not null, status_id bigint not null, primary key (id)) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime(6) not null, email varchar(255) not null, nome varchar(255) not null, usuario varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table ticket add constraint FKdpw8x2m6ptqfpr3bhs7n09clb foreign key (categoria_id) references categoria (id)
alter table ticket add constraint FK27gx1m1d776o2cv5orfqbacjt foreign key (prioridade_id) references prioridade (id)
alter table ticket add constraint FK7h1wcba93khggbl1ahgwjlssu foreign key (status_id) references status (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
create table categoria (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table prioridade (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table status (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table ticket (id bigint not null auto_increment, data_abertura datetime(6) not null, data_atualizacao datetime(6) not null, descricao varchar(255) not null, titulo varchar(255) not null, categoria_id bigint not null, prioridade_id bigint not null, status_id bigint not null, primary key (id)) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime(6) not null, email varchar(255) not null, nome varchar(255) not null, usuario varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table ticket add constraint FKdpw8x2m6ptqfpr3bhs7n09clb foreign key (categoria_id) references categoria (id)
alter table ticket add constraint FK27gx1m1d776o2cv5orfqbacjt foreign key (prioridade_id) references prioridade (id)
alter table ticket add constraint FK7h1wcba93khggbl1ahgwjlssu foreign key (status_id) references status (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
create table categoria (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo (id bigint not null auto_increment, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table grupo_permissao (grupo_id bigint not null, permissao_id bigint not null) engine=InnoDB
create table permissao (id bigint not null auto_increment, descricao varchar(255) not null, nome varchar(255) not null, primary key (id)) engine=InnoDB
create table prioridade (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table status (id bigint not null auto_increment, descricao varchar(255) not null, primary key (id)) engine=InnoDB
create table ticket (id bigint not null auto_increment, data_abertura datetime(6) not null, data_atualizacao datetime(6) not null, descricao varchar(255) not null, titulo varchar(255) not null, categoria_id bigint not null, prioridade_id bigint not null, status_id bigint not null, primary key (id)) engine=InnoDB
create table usuario (id bigint not null auto_increment, data_cadastro datetime(6) not null, email varchar(255) not null, nome varchar(255) not null, usuario varchar(255) not null, primary key (id)) engine=InnoDB
create table usuario_grupo (usuario_id bigint not null, grupo_id bigint not null) engine=InnoDB
alter table grupo_permissao add constraint FKh21kiw0y0hxg6birmdf2ef6vy foreign key (permissao_id) references permissao (id)
alter table grupo_permissao add constraint FKta4si8vh3f4jo3bsslvkscc2m foreign key (grupo_id) references grupo (id)
alter table ticket add constraint FKdpw8x2m6ptqfpr3bhs7n09clb foreign key (categoria_id) references categoria (id)
alter table ticket add constraint FK27gx1m1d776o2cv5orfqbacjt foreign key (prioridade_id) references prioridade (id)
alter table ticket add constraint FK7h1wcba93khggbl1ahgwjlssu foreign key (status_id) references status (id)
alter table usuario_grupo add constraint FKk30suuy31cq5u36m9am4om9ju foreign key (grupo_id) references grupo (id)
alter table usuario_grupo add constraint FKdofo9es0esuiahyw2q467crxw foreign key (usuario_id) references usuario (id)
