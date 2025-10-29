
INSERT INTO perfil (nome) VALUES ('ROLE_ADMIN');
INSERT INTO perfil (nome) VALUES ('ROLE_USER');


INSERT INTO usuario (username, password) VALUES ('admin', '123456');


INSERT INTO usuario (username, password) VALUES ('usuario', '123456');

INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (1, 1); 
INSERT INTO usuario_perfil (usuario_id, perfil_id) VALUES (2, 2); 