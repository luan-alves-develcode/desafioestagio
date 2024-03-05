CREATE TABLE itens_venda (
  quantidade INT NULL,
   produto_id BIGINT NOT NULL,
   carrinho_id BIGINT NOT NULL,
   CONSTRAINT pk_item_carrinho PRIMARY KEY (produto_id, carrinho_id)
);

ALTER TABLE itens_venda ADD CONSTRAINT FK_ITENS_VENDA_ON_CARRINHO FOREIGN KEY (carrinho_id) REFERENCES carrinhos (id);

ALTER TABLE itens_venda ADD CONSTRAINT FK_ITENS_VENDA_ON_PRODUTO FOREIGN KEY (produto_id) REFERENCES produtos (id);