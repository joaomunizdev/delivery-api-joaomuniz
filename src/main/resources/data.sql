INSERT INTO clients (name, email, phone_number, is_active)
VALUES 
('João Muniz', 'joao.muniz@email.com', '(11) 91111-1111', true),
('Maria Souza', 'maria.souza@email.com', '(21) 92222-2222', true),
('Pedro Almeida', 'pedro.almeida@email.com', '(31) 93333-3333', true),
('Ana Clara', 'ana.clara@email.com', '(41) 94444-4444', false);

INSERT INTO restaurants (name, category, phone_number, is_active, rating)
VALUES 
('Pizzaria do Bairro', 'Pizza', '(11) 95555-1000', true, 4.5),
('Sushi da Praça', 'Japonesa', '(21) 95555-2000', true, 4.8),
('Burger Fast', 'Lanches', '(31) 95555-3000', true, 4.2),
('Cantina da Nona', 'Italiana', '(41) 95555-4000', false, 4.0); 

INSERT INTO products (name, restaurant_id, category, is_available)
VALUES 
('Pizza Margherita', 1, 'Pizzas Salgadas', true),
('Pizza Calabresa', 1, 'Pizzas Salgadas', true),
('Pizza Quatro Queijos', 1, 'Pizzas Salgadas', true),
('Pizza de Nutella', 1, 'Pizzas Doces', false), -- Produto indisponível
('Coca-Cola 2L', 1, 'Bebidas', true);

INSERT INTO products (name, restaurant_id, category, is_available)
VALUES 
('Combinado Salmão (20 peças)', 2, 'Combinados', true),
('Temaki Salmão Completo', 2, 'Temakis', true),
('Hot Roll (10 peças)', 2, 'Quentes', true);

INSERT INTO products (name, restaurant_id, category, is_available)
VALUES 
('X-Burger Clássico', 3, 'Hambúrgueres', true),
('Batata Frita Média', 3, 'Acompanhamentos', true),
('Milk-shake de Morango', 3, 'Bebidas', true);

INSERT INTO products (name, restaurant_id, category, is_available)
VALUES 
('Spaghetti ao Sugo', 4, 'Massas', true);

INSERT INTO orders (client_id, status, order_date)
VALUES 
(1, 'ENTREGUE', '2025-10-28'), -- Pedido antigo
(1, 'PENDENTE', '2025-10-30'); -- Pedido de hoje

INSERT INTO orders (client_id, status, order_date)
VALUES 
(2, 'CANCELADO', '2025-10-29');