INSERT INTO usuario (id, nome, cpf) VALUES (1, 'Luciano', '77788486191');
INSERT INTO pauta (id,titulo,descricao,ind_ativa) VALUES (1,'Você é a favor do voto impresso','Votação para definir o desejo da polulação sobre o voto impresso','S');
INSERT INTO opcao_voto (id,titulo)  VALUES (1, 'SIM');
INSERT INTO opcao_voto (id,titulo)  VALUES (2, 'NÃO');
INSERT INTO votacao (id,usuario_id,pauta_id,opcao_voto)  VALUES (1,1,1,'S');