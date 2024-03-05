CREATE TABLE vendas (
  id BIGINT AUTO_INCREMENT NOT NULL,
   total DECIMAL(10,2) NOT NULL,
   quantidade_itens INT NOT NULL,
   cliente_id BIGINT NOT NULL,
   CONSTRAINT pk_vendas PRIMARY KEY (id)
);

ALTER TABLE vendas ADD CONSTRAINT uc_vendas_cliente UNIQUE (cliente_id);

ALTER TABLE vendas ADD CONSTRAINT FK_VENDAS_ON_CLIENTE FOREIGN KEY (cliente_id) REFERENCES clientes (id);