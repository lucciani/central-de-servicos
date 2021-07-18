set foreign_key_checks = 0;

delete from categoria;
delete from grupo;
delete from grupo_permissao;
delete from permissao;
delete from prioridade;
delete from status;
delete from ticket;
delete from usuario;
delete from usuario_grupo;

set foreign_key_checks = 1;

alter table categoria auto_increment = 1;
alter table grupo auto_increment = 1;
alter table permissao auto_increment = 1;
alter table prioridade auto_increment = 1;
alter table status auto_increment = 1;
alter table ticket auto_increment = 1;
alter table usuario auto_increment = 1;

insert into categoria (id, nome) values (1, 'Suporte');
insert into categoria (id, nome) values (2, 'Infraestrutura');

insert into status (id, descricao) values (1, 'Novo');
insert into status (id, descricao) values (2, 'Atribuido');
insert into status (id, descricao) values (3, 'Pendente');
insert into status (id, descricao) values (4, 'Resolvido');
insert into status (id, descricao) values (5, 'Aprovado');
insert into status (id, descricao) values (6, 'Cancelado');
insert into status (id, descricao) values (7, 'Fechado');

insert into prioridade (id, descricao) values (1, 'Baixa');
insert into prioridade (id, descricao) values (2, 'Media');
insert into prioridade (id, descricao) values (3, 'Alta');

insert into usuario (id, data_cadastro, email, nome, usuario) values(1, now(), 'email01@email.com', 'Pedro da Silva', 'usuario01');
insert into usuario (id, data_cadastro, email, nome, usuario) values(2, now(), 'email02@email.com', 'Raimundo Matos', 'usuario02');
insert into usuario (id, data_cadastro, email, nome, usuario) values(3, now(), 'email03@email.com', 'Antonio Riaj', 'usuario03');
insert into usuario (id, data_cadastro, email, nome, usuario) values(4, now(), 'email04@email.com', 'Maria Antonia', 'usuario04');


insert into ticket (id, titulo, descricao, categoria_id, prioridade_id, status_id,data_abertura,data_atualizacao, usuario_solicitante_id, usuario_solicitado_id) values (1,'Pc não liga','Meu pc está com a tela azul', 1,2,1, now(), now(),1,3);
insert into ticket (id, titulo, descricao, categoria_id, prioridade_id, status_id,data_abertura,data_atualizacao, usuario_solicitante_id, usuario_solicitado_id) values (2,'Sem internet','Não consigo abrir os sites', 1,1,1, now(), now(),2,4);
insert into ticket (id, titulo, descricao, categoria_id, prioridade_id, status_id,data_abertura,data_atualizacao, usuario_solicitante_id, usuario_solicitado_id) values (3,'Meu teclado não funciona','Meu teclado está com problema nas teclas', 1,2,1, now(), now(),1,4);

