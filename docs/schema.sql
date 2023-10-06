CREATE TABLE IF NOT EXISTS "Endereco" (
      "id_endereco"	TEXT,
      "rua"	TEXT,
      "numero_casa"	TEXT,
      "bairro"	TEXT,
      "cidade"	TEXT,
      "estado"	TEXT,
      PRIMARY KEY("id_endereco")
);
CREATE TABLE IF NOT EXISTS "Loja" (
      "cnpj"	TEXT,
      "nome_loja"	TEXT NOT NULL,
      "telefone_loja"	TEXT NOT NULL,
      "id_endereco"	INTEGER NOT NULL,
      FOREIGN KEY("id_endereco") REFERENCES "Endereco"("id_endereco"),
      PRIMARY KEY("cnpj")
);
CREATE TABLE IF NOT EXISTS "Cliente" (
     "cpf"	TEXT,
     "nome_cliente"	TEXT NOT NULL,
     "email"	TEXT NOT NULL,
     "telefone"	TEXT NOT NULL,
     "id_endereco"	TEXT NOT NULL,
     PRIMARY KEY("cpf"),
     FOREIGN KEY("id_endereco") REFERENCES "Endereco"("id_endereco")
);
CREATE TABLE IF NOT EXISTS "Evento" (
    "id_evento"	TEXT,
    "nome_evento"	TEXT,
    "data_evento"	TEXT,
    "formato"	TEXT,
    PRIMARY KEY("id_evento")
);
CREATE TABLE IF NOT EXISTS "ParticipacaoEvento" (
    "id_evento"	TEXT NOT NULL,
    "cpf_cliente"	TEXT NOT NULL,
    PRIMARY KEY("id_evento","cpf_cliente")
);
CREATE TABLE IF NOT EXISTS "Produto" (
     "id_produto"	TEXT,
     "nome_produto"	TEXT NOT NULL,
     "preco"	REAL,
     "qtd_estoque"	INTEGER,
     "tipo_produto"	TEXT,
     PRIMARY KEY("id_produto")
);
CREATE TABLE IF NOT EXISTS "Carta" (
   "id_carta"	TEXT,
   "idioma"	TEXT NOT NULL,
   "edicao"	TEXT NOT NULL,
   "extra"	TEXT,
   "qualidade"	TEXT,
   "id_produto"	TEXT NOT NULL,
   PRIMARY KEY("id_carta"),
   FOREIGN KEY("id_produto") REFERENCES "Produto"("id_produto")
);
CREATE TABLE IF NOT EXISTS "Acessorio" (
   "id_acessorio"	TEXT,
   "tipo_acessorio"	TEXT NOT NULL,
   "id_produto"	TEXT NOT NULL,
   FOREIGN KEY("id_produto") REFERENCES "Produto"("id_produto"),
   PRIMARY KEY("id_acessorio")
);
CREATE TABLE IF NOT EXISTS "Selado" (
    "id_selado"	TEXT,
    "tipo_selado"	TEXT NOT NULL,
    "id_produto"	TEXT NOT NULL,
    FOREIGN KEY("id_produto") REFERENCES "Produto"("id_produto"),
    PRIMARY KEY("id_selado")
);
CREATE TABLE IF NOT EXISTS "Venda" (
   "id_venda"	TEXT,
   "valor_total"	REAL NOT NULL,
   "data_compra"	TEXT NOT NULL,
   "id_cliente"	TEXT NOT NULL,
   FOREIGN KEY("id_cliente") REFERENCES "Cliente"("cpf"),
   PRIMARY KEY("id_venda")
);
CREATE TABLE IF NOT EXISTS "CompraDeCartaDeCliente" (
    "id_compra"	TEXT,
    "valor_total"	REAL NOT NULL,
    "data_venda"	TEXT NOT NULL,
    "id_cliente"	TEXT NOT NULL,
    FOREIGN KEY("id_cliente") REFERENCES "Cliente"("cpf"),
    PRIMARY KEY("id_compra")
);
CREATE TABLE IF NOT EXISTS "ProdutoVendido" (
    "id_venda"	TEXT NOT NULL,
    "id_produto"	TEXT NOT NULL,
    "qtd"	INTEGER NOT NULL,
    PRIMARY KEY("id_venda","id_produto")
);
CREATE TABLE IF NOT EXISTS "CartasCompradasDeCliente" (
      "id_compra"	TEXT NOT NULL,
      "id_produto"	TEXT NOT NULL,
      "qtd"	INTEGER NOT NULL,
      PRIMARY KEY("id_compra","id_produto")
);