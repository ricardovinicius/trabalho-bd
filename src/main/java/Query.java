import java.util.HashMap;
import java.util.Map;

public abstract class Query {
    public static Map<String, String> insertSQL = new HashMap<String,String>();
    private static final String insertSqlEndereco = "INSERT INTO Endereco " +
            "(id_endereco, rua, numero_casa, bairro, cidade, estado) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String insertSqlLoja = "INSERT INTO Loja " +
            "(cnpj, nome_loja, telefone_loja, id_endereco) " +
            "VALUES (?, ?, ?, ?)";

    private static final String insertSqlCliente = "INSERT INTO Cliente " +
            "(cpf, nome_cliente, email, telefone, id_endereco) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String insertSqlEvento = "INSERT INTO Evento " +
            "(id_evento, nome_evento, data_evento, formato) " +
            "VALUES (?, ?, ?, ?)";

    private static final String insertSqlParticipacaoEvento = "INSERT INTO ParticipacaoEvento " +
            "(id_evento, cpf_cliente) " +
            "VALUES (?, ?)";

    private static final String insertSqlProduto = "INSERT INTO Produto " +
            "(id_produto, nome_produto, preco, qtd_estoque, tipo_produto) " +
            "VALUES (?, ?, ?, ?, ?)";

    private static final String insertSqlCarta = "INSERT INTO Carta " +
            "(id_carta, idioma, edicao, extra, qualidade, id_produto) " +
            "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String insertSqlAcessorio = "INSERT INTO Acessorio " +
            "(id_acessorio, tipo_acessorio, id_produto) " +
            "VALUES (?, ?, ?)";

    private static final String insertSqlSelado = "INSERT INTO Selado " +
            "(id_selado, tipo_selado, id_produto) " +
            "VALUES (?, ?, ?)";

    private static final String insertSqlVenda = "INSERT INTO Venda " +
            "(id_venda, valor_total, data_compra, id_cliente) " +
            "VALUES (?, ?, ?, ?)";

    private static final String insertSqlCompraDeCartaDeCliente = "INSERT INTO CompraDeCartaDeCliente " +
            "(id_compra, valor_total, data_venda, id_cliente) " +
            "VALUES (?, ?, ?, ?)";

    private static final String insertSqlProdutoVendido = "INSERT INTO ProdutoVendido " +
            "(id_venda, id_produto, qtd) " +
            "VALUES (?, ?, ?)";

    private static final String insertSqlCartasCompradasDeCliente = "INSERT INTO CartasCompradasDeCliente " +
            "(id_compra, id_produto, qtd) " +
            "VALUES (?, ?, ?)";

    public static final String selectSqlEnderecoId = "SELECT id_endereco " +
            "FROM Endereco";

    public static final String selectSqlEventoId = "SELECT id_evento " +
            "FROM Evento";

    public static final String selectSqlClienteCpf = "SELECT cpf " +
            "FROM Cliente";

    public static final String selectSqlVendaId = "SELECT id_venda " +
            "FROM Venda";

    public static final String selectSqlProdutoId = "SELECT id_produto " +
            "FROM Produto";

    public static final String selectSqlCompraId = "SELECT id_compra " +
            "FROM CompraDeCartaDeCliente";

    public static void initializeQueries() {
        insertSQL.put("Endereco", insertSqlEndereco);
        insertSQL.put("Loja", insertSqlLoja);
        insertSQL.put("Cliente", insertSqlCliente);
        insertSQL.put("Evento", insertSqlEvento);
        insertSQL.put("ParticipacaoEvento", insertSqlParticipacaoEvento);
        insertSQL.put("Produto", insertSqlProduto);
        insertSQL.put("Carta", insertSqlCarta);
        insertSQL.put("Acessorio", insertSqlAcessorio);
        insertSQL.put("Selado", insertSqlSelado);
        insertSQL.put("Venda", insertSqlVenda);
        insertSQL.put("CompraDeCartaDeCliente", insertSqlCompraDeCartaDeCliente);
        insertSQL.put("ProdutoVendido", insertSqlProdutoVendido);
        insertSQL.put("CartasCompradasDeCliente", insertSqlCartasCompradasDeCliente);
    }


    static {
        initializeQueries();
    }

}
