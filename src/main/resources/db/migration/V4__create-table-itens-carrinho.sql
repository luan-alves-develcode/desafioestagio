CREATE TABLE item_carrinho (
  id BIGINT AUTO_INCREMENT NOT NULL,
  carrinho_id BIGINT NOT NULL,
  produto_id BIGINT NOT NULL,
  quantidade INT NOT NULL,
   CONSTRAINT pk_item_carrinho PRIMARY KEY (id)
);
ALTER TABLE item_carrinho ADD CONSTRAINT uc_item_carrinho_carrinhos FOREIGN KEY (carrinho_id) REFERENCES carrinhos (id);
ALTER TABLE item_carrinho ADD CONSTRAINT uc_item_carrinho_produtos FOREIGN KEY (produto_id) REFERENCES produtos (id);