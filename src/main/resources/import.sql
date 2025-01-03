insert into cozinha(id,nome) values (1,'Tailandesa');
insert into cozinha(id,nome) values (2,'Indiana');

insert into restaurante(nome, taxa_frete, cozinha_id) values ('Copo Sujo', 10, 1);
insert into restaurante(nome, taxa_frete, cozinha_id) values ('Copo Mal Lavado', 9.50, 1);
insert into restaurante(nome, taxa_frete, cozinha_id) values ('Copo Limpo', 15, 2);

insert into estado(id, nome) values (1,'Espirito Santo');

insert into cidade(nome,estado_id) values ('Vitória', 1);

insert into forma_pagamento(id, descricao) values (1, 'Pix');
