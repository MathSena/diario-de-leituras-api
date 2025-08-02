-- INSERÇÕES NA TABELA Livro (sem a coluna 'reflexoes')
INSERT INTO Livro(id, titulo, autor, categoria, status, dataInicio, dataConclusao, nota, capaUrl)
VALUES (1001, 'O Guia do Mochileiro das Galáxias', 'Douglas Adams', 'FICCAO', 'LIDO', '2024-01-01', '2024-01-15', 5, 'https://m.media-amazon.com/images/I/81e+m8M5rEL._AC_UF1000,1000_QL80_.jpg');

INSERT INTO Livro(id, titulo, autor, categoria, status, dataInicio, dataConclusao, nota, capaUrl)
VALUES (2000, 'Duna', 'Frank Herbert', 'FICCAO', 'LISTA_DE_DESEJOS', NULL, NULL, NULL, 'https://m.media-amazon.com/images/I/81dI193w3iL._AC_UF1000,1000_QL80_.jpg');

INSERT INTO Livro(id, titulo, autor, categoria, status, dataInicio, dataConclusao, nota, capaUrl)
VALUES (3000, 'O Problema dos 3 Corpos', 'Cixin Liu', 'FICCAO', 'LENDO', '2024-07-10', NULL, NULL, NULL);


-- INSERÇÕES NA NOVA TABELA Reflexao
-- Associamos cada reflexão a um livro usando o 'livro_id'
INSERT INTO Reflexao(id, conteudo, livro_id) VALUES (1, 'Um livro incrivelmente divertido e inteligente.', 1001);
INSERT INTO Reflexao(id, conteudo, livro_id) VALUES (2, 'A nota 42 faz todo o sentido agora!', 1001);
INSERT INTO Reflexao(id, conteudo, livro_id) VALUES (3, 'Início muito promissor...', 3000);