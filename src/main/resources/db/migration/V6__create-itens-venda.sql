CREATE TABLE itens_venda (
  quantidade INT NULL,
   produto_id BIGINT NOT NULL,
   venda_id BIGINT NOT NULL,
   CONSTRAINT pk_item_venda PRIMARY KEY (produto_id, venda_id)
);

ALTER TABLE itens_venda ADD CONSTRAINT FK_ITENS_VENDA_ON_CARRINHO FOREIGN KEY (venda_id) REFERENCES vendas (id);

ALTER TABLE itens_venda ADD CONSTRAINT FK_ITENS_VENDA_ON_PRODUTO FOREIGN KEY (produto_id) REFERENCES produtos (id);