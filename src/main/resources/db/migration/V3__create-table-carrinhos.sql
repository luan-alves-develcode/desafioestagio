CREATE TABLE carrinhos (
  id BIGINT AUTO_INCREMENT NOT NULL,
   total DECIMAL NULL,
   quantidade_itens INT NULL,
   cliente_id BIGINT NULL,
   CONSTRAINT pk_carrinhos PRIMARY KEY (id)
);

ALTER TABLE carrinhos ADD CONSTRAINT uc_carrinhos_cliente UNIQUE (cliente_id);

ALTER TABLE carrinhos ADD CONSTRAINT FK_CARRINHOS_ON_CLIENTE FOREIGN KEY (cliente_id) REFERENCES clientes (id);