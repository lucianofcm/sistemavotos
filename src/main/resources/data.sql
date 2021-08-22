INSERT INTO usuario (id, nome, cpf) VALUES (1, 'Luciano', '77788486191');
INSERT INTO pauta (id,titulo,descricao,ind_ativa) VALUES (1,'Pauta 1','Descricao pauta 1','S');
INSERT INTO pauta (id,titulo,descricao,ind_ativa) VALUES (2,'Pauta 2','Descricao pauta 2','S');
INSERT INTO pauta (id,titulo,descricao,ind_ativa) VALUES (3,'Pauta 3','Descricao pauta 3','S');
INSERT INTO duracao_votacao (fim_votacao,inicio_votacao,duracao_votacao,pauta_id) VALUES ('2021-08-21T22:54:21.532','2021-08-21T22:44:21.532',10,1);
INSERT INTO duracao_votacao (fim_votacao,inicio_votacao,duracao_votacao,pauta_id) VALUES ('2025-08-21T22:54:21.532','2021-08-21T22:44:21.532',10,2);
INSERT INTO opcao_voto (id,titulo)  VALUES (1, 'SIM');
INSERT INTO opcao_voto (id,titulo)  VALUES (2, 'NÃO');
INSERT INTO votacao (cpf_usuario,opcao_voto,pauta_id,usuario_id)  VALUES ('77788486191','S',1,1);