CREATE TABLE item_carrinho (
  quantidade INT NULL,
   produto_id BIGINT NOT NULL,
   carrinho_id BIGINT NOT NULL,
   CONSTRAINT pk_item_carrinho PRIMARY KEY (produto_id, carrinho_id)
);

ALTER TABLE item_carrinho ADD CONSTRAINT FK_ITEM_CARRINHO_ON_CARRINHO FOREIGN KEY (carrinho_id) REFERENCES carrinhos (id);

ALTER TABLE item_carrinho ADD CONSTRAINT FK_ITEM_CARRINHO_ON_PRODUTO FOREIGN KEY (produto_id) REFERENCES produtos (id);