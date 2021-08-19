INSERT INTO usuario (id, nome, cpf) VALUES (1, 'Luciano', '77788486191');
INSERT INTO pauta (id,titulo,descricao,ind_ativa) VALUES (1,'Você é a favor do voto impresso','Votação para definir o desejo da polulação sobre o voto impresso','S');
INSERT INTO opcao_voto (id,titulo,pauta_id)  VALUES (1, 'SIM',1);
INSERT INTO opcao_voto (id,titulo,pauta_id)  VALUES (2, 'NÃO',1);
INSERT INTO opcao_voto (id,titulo,pauta_id)  VALUES (3, 'TALVEZ',1);
INSERT INTO opcao_voto (id,titulo,pauta_id)  VALUES (4, 'DEPENDE',1);
INSERT INTO votacao (id,usuario_id,pauta_id,opcao_voto_id)  VALUES (1,1,1,1);