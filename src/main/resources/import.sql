-- Inserindo um livro LIDO com ID 1
INSERT INTO livro(id, titulo, autor, categoria, status, dataInicio, dataConclusao, reflexoes, nota)
VALUES (1001, 'O Guia do Mochileiro das Galáxias', 'Douglas Adams', 'FICCAO', 'LIDO', '2024-01-01', '2024-01-15', 'Um livro incrivelmente divertido e inteligente.', 5);

-- Inserindo um livro na LISTA DE DESEJOS com ID 2
INSERT INTO livro(id, titulo, autor, categoria, status, dataInicio, dataConclusao, reflexoes, nota)
VALUES (1002, 'Duna', 'Frank Herbert', 'FICCAO', 'LISTA_DE_DESEJOS', NULL, NULL, NULL, NULL);

-- Inserindo um livro LENDO com ID 3
INSERT INTO livro(id, titulo, autor, categoria, status, dataInicio, dataConclusao, reflexoes, nota)
VALUES (1003, 'O Problema dos 3 Corpos', 'Cixin Liu', 'FICCAO', 'LENDO', '2024-07-10', NULL, 'Início muito promissor...', NULL);
