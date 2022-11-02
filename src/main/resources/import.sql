INSERT INTO public.produto1(descricao, preco_unitario) VALUES ('Arroz', '15.6');
INSERT INTO public.produto1(descricao, preco_unitario) VALUES ('Feijão', '19.3');
INSERT INTO public.produto1(descricao, preco_unitario) VALUES ('Banana', '7.4');
INSERT INTO public.produto1(descricao, preco_unitario) VALUES ('Laranja', '4.3');

--INSERT INTO public.usuario(login, nome, senha) VALUES ('md', 'Murilo Dircksen', '102030');
--INSERT INTO public.usuario(login, nome, senha) VALUES ('br', 'Bruno Soares', '101020');
--INSERT INTO public.usuario(login, nome, senha) VALUES ('lele', 'Leticia Abravanel', '201020');

INSERT INTO public.usuario(login, nome, senha) VALUES ('md', 'Murilo Dircksen', '$2a$10$8okjbHGqFzdFAX.XtVBO6eEZWi4F7W285Yy6ZsQC9ZyecUB.gO91C'); --senha codificada 102030
INSERT INTO public.usuario(login, nome, senha) VALUES ('br', 'Bruno Soares', '$2a$10$umA2cp3zGaSMPAGreI3PKePL9eZaDcravDUIONL./dbk1g.LQS7hW'); --203040


INSERT INTO public.telefone(numero, tipo, id_usuario)	VALUES ('1111111111', 'cel', 1);
INSERT INTO public.telefone(numero, tipo, id_usuario)	VALUES ('2222222222', 'cel', 2);

INSERT INTO public.role(nome_role) VALUES ('ROLE_ADMIN');
INSERT INTO public.role(nome_role) VALUES ('ROLE_CADASTRADOR');
INSERT INTO public.role(nome_role) VALUES ('ROLE_PEDIDO');
INSERT INTO public.usuarios_role(usuario_id, role_id) VALUES (1, 1);
INSERT INTO public.usuarios_role(usuario_id, role_id) VALUES (2, 2);


--Projeto semana 4

INSERT INTO public.cliente(	cpf, nome, rg) 	VALUES ('11111111111', 'Fulano', '1111111');
INSERT INTO public.cliente(	cpf, nome, rg) 	VALUES ('22222222222', 'Ciclano', '2222222');
INSERT INTO public.cliente( cpf, nome, rg)	VALUES ('33333333333', 'Trajano', '3333333');

INSERT INTO public.produto(	descricao, data_hora_atualizacao, data_hora_cadastro, preco_compra, preco_venda) VALUES ('Celular Iphone', timezone('utc',CURRENT_TIMESTAMP(0)), timezone('utc',CURRENT_TIMESTAMP(0)), '700', '1000');
INSERT INTO public.produto(	descricao, data_hora_atualizacao, data_hora_cadastro, preco_compra, preco_venda) VALUES ('Notebook Dell', timezone('utc',CURRENT_TIMESTAMP(0)), timezone('utc',CURRENT_TIMESTAMP(0)), '2700', '3500');

INSERT INTO public.forma_pagamento (descricao) 	VALUES ('debito');
INSERT INTO public.forma_pagamento (descricao) VALUES ('credito');

INSERT INTO public.pedido(data_hora_atualizacao, data_hora_cadastro, id_cliente, id_forma_pagamento) VALUES (timezone('utc',CURRENT_TIMESTAMP(0)), timezone('utc',CURRENT_TIMESTAMP(0)), 1, 1);
INSERT INTO public.pedido(data_hora_atualizacao, data_hora_cadastro, id_cliente, id_forma_pagamento) VALUES (timezone('utc',CURRENT_TIMESTAMP(0)), timezone('utc',CURRENT_TIMESTAMP(0)), 2, 1);
INSERT INTO public.pedido(data_hora_atualizacao, data_hora_cadastro, id_cliente, id_forma_pagamento) VALUES (timezone('utc',CURRENT_TIMESTAMP(0)), timezone('utc',CURRENT_TIMESTAMP(0)), 3, 2);

INSERT INTO public.item_pedido(	produto, quantidade, valor_item, id_pedido, id_produto)	VALUES ('celular', '1', '1000', 1, 1);
INSERT INTO public.item_pedido(	produto, quantidade, valor_item, id_pedido, id_produto)	VALUES ('notebook', '1', '3500', 2,2);
INSERT INTO public.item_pedido(	produto, quantidade, valor_item, id_pedido, id_produto)	VALUES ('celular', '2', '1000', 3,1);




