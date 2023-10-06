import java.sql.*;

/**
 *
 *
 * @author Ricardo Vinicius
 * @version 0.1.0
 * @
 */
public class Main {
    private static final String fileName = "test.db";
    public static final String url = "jdbc:sqlite:data/" + fileName;

    /**
     * Cria um novo banco de dados em SQLite
     */
    public static void createNewDatabase() {
        try (Connection connection = DriverManager.getConnection(url)) {
            if (connection != null) {
                DatabaseMetaData meta = connection.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createNewTable(String tableName, String... tableColumns) {
        // Constrói a query para criação de uma tabela no banco de dados
        StringBuilder sqlBuilder = new StringBuilder("CREATE TABLE IF NOT EXISTS " + tableName + "(\n");

        int counter = 0;

        for (String column : tableColumns) {
            counter++;

            if (counter == tableColumns.length)
            {
                sqlBuilder.append(column).append(");");
            } else {
                sqlBuilder.append(column).append(",\n");
            }
        }

        String sql = sqlBuilder.toString();

        try (Connection connection = DriverManager.getConnection(url);
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void createAllTables() {
        createNewTable("Endereco",
                "id_endereco TEXT PRIMARY KEY",
                "rua TEXT",
                "numero_casa TEXT",
                "bairro TEXT",
                "cidade TEXT",
                "estado TEXT");

        createNewTable("Loja",
                "cnpj TEXT PRIMARY KEY",
                "nome_loja TEXT NOT NULL",
                "telefone_loja TEXT NOT NULL",
                "id_endereco INTEGER NOT NULL",
                "FOREIGN KEY (id_endereco) REFERENCES Endereco(id_endereco)");

        createNewTable("Cliente",
                "cpf TEXT PRIMARY KEY",
                "nome_cliente TEXT NOT NULL",
                "email TEXT NOT NULL",
                "telefone TEXT NOT NULL",
                "id_endereco TEXT NOT NULL",
                "FOREIGN KEY (id_endereco) REFERENCES Endereco(id_endereco)");

        createNewTable("Evento",
                "id_evento TEXT PRIMARY KEY",
                "nome_evento TEXT",
                "data_evento TEXT",
                "formato TEXT");

        createNewTable("ParticipacaoEvento",
                "id_evento TEXT NOT NULL",
                "cpf_cliente TEXT NOT NULL",
                "PRIMARY KEY(id_evento, cpf_cliente)");

        createNewTable("Produto",
                "id_produto TEXT PRIMARY KEY",
                "nome_produto TEXT NOT NULL",
                "preco REAL",
                "qtd_estoque INTEGER",
                "tipo_produto TEXT");

        createNewTable("Carta",
                "id_carta TEXT PRIMARY KEY",
                "idioma TEXT NOT NULL",
                "edicao TEXT NOT NULL",
                "extra TEXT",
                "qualidade TEXT",
                "id_produto TEXT NOT NULL",
                "FOREIGN KEY (id_produto) REFERENCES Produto(id_produto)");

        createNewTable("Acessorio",
                "id_acessorio TEXT PRIMARY KEY",
                "tipo_acessorio TEXT NOT NULL",
                "id_produto TEXT NOT NULL",
                "FOREIGN KEY (id_produto) REFERENCES Produto(id_produto)");

        createNewTable("Selado",
                "id_selado TEXT PRIMARY KEY",
                "tipo_selado TEXT NOT NULL",
                "id_produto TEXT NOT NULL",
                "FOREIGN KEY (id_produto) REFERENCES Produto(id_produto)");

        createNewTable("Venda",
                "id_venda TEXT PRIMARY KEY",
                "valor_total REAL NOT NULL",
                "data_compra TEXT NOT NULL",
                "id_cliente TEXT NOT NULL",
                "FOREIGN KEY (id_cliente) REFERENCES Cliente(cpf)");

        createNewTable("CompraDeCartaDeCliente",
                "id_compra TEXT PRIMARY KEY",
                "valor_total REAL NOT NULL",
                "data_venda TEXT NOT NULL",
                "id_cliente TEXT NOT NULL",
                "FOREIGN KEY (id_cliente) REFERENCES Cliente(cpf)");

        createNewTable("ProdutoVendido",
                "id_venda TEXT NOT NULL",
                "id_produto TEXT NOT NULL",
                "qtd INTEGER NOT NULL",
                "PRIMARY KEY(id_venda, id_produto)");

        createNewTable("CartasCompradasDeCliente",
                "id_compra TEXT NOT NULL",
                "id_produto TEXT NOT NULL",
                "qtd INTEGER NOT NULL",
                "PRIMARY KEY(id_compra, id_produto)");
    }


    public static void main(String[] args) {
        createNewDatabase();

        createAllTables();

        Populate populate = new Populate(url);

        populate.allTables();


    }
}
