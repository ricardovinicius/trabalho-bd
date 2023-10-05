import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.*;
import java.util.Random;
import java.io.File;

import com.github.javafaker.*;
import org.json.simple.*;
import org.json.simple.parser.*;

import static java.lang.Thread.sleep;


public class Populate {
    private static final int TABLES_MIN_SIZE = 200;
    private static final String CARD_API_URL = "https://api.scryfall.com/cards/random";
    private static final String[] FORMATOS_MTG = {"Standard",
    "Commander", "Legacy", "Modern"};
    private static final String[] TIPO_ACESSORIO = {"Dado",
    "Deck Box", "Fichario", "Pasta", "Playmat", "Sleeve"};
    private static final String[] TIPO_SELADO = {"Booster",
    "Bundle", "Caixa de Booster", "Clash", "Commander", "Duel"};
    private static final String[] EXTRA = {"NaN",
    "Foil", "Signed"};
    private static final String[] QUALIDADE = {"M",
    "NM", "SP", "MP", "HP", "D"};
    private static final String[] ADJETIVOS = {"Maneiro",
    "Massa", "Daora", "Bacana", "Supimpa", "Genial",
    "Superior", "Marcante", "Bolado", "Popular", "Oh ma Ga!",
    "Sensacional"};
    private static final String DEFAULT_NOME_LOJA = "Loja de Magic do Ricardo";
    private static final String DEFAULT_CPNJ_LOJA = "66.666.666/6666-66";

    private String url;
    private Faker faker;
    private Random random;
    private Connection connection;



    public Populate(String url) {
        this.url = url;
        this.random = new Random();
        this.faker = new Faker();
        connection = connect();
    }

    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private void setRandomInsertEndereco(PreparedStatement pstmt) {
        try {
            pstmt.setString(1, faker.number().digits(10));
            pstmt.setString(2, faker.address().streetName());
            pstmt.setString(3, faker.address().buildingNumber());
            pstmt.setString(4, faker.address().streetName());
            pstmt.setString(5, faker.address().city());
            pstmt.setString(6, faker.address().state());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getRandomEnderecoId() {
        try (Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(Query.selectSqlEnderecoId)) {

            int randomRow = random.nextInt(TABLES_MIN_SIZE);
            int i = 0;
            while (i < randomRow) {
                resultSet.next();
                i++;
            }

            return resultSet.getString("id_endereco");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String getRandomEventoId() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(Query.selectSqlEventoId)) {

            int randomRow = random.nextInt(TABLES_MIN_SIZE);
            int i = 0;
            while (i < randomRow) {
                resultSet.next();
                i++;
            }

            return resultSet.getString("id_evento");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getRandomClienteCpf() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(Query.selectSqlClienteCpf)) {

            int randomRow = random.nextInt(TABLES_MIN_SIZE);
            int i = 0;
            while (i < randomRow) {
                resultSet.next();
                i++;
            }

            return resultSet.getString("cpf");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getRandomCardName() {
        try {
            URL url = new URL("https://api.scryfall.com/cards/random");
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(new InputStreamReader(url.openStream()));
            Thread.sleep(100);
            return json.get("name").toString();
        } catch (IOException | ParseException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private String getRandomCardSet() {
        try {
            URL url = new URL("https://api.scryfall.com/cards/random");
            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(new InputStreamReader(url.openStream()));
            return json.get("set_name").toString();
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }

    private String getRandomVendaId() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(Query.selectSqlVendaId)) {

            int randomRow = random.nextInt(TABLES_MIN_SIZE);
            int i = 0;
            while (i < randomRow) {
                resultSet.next();
                i++;
            }

            return resultSet.getString("id_venda");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getRandomProdutoId() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(Query.selectSqlProdutoId)) {

            int randomRow = random.nextInt(TABLES_MIN_SIZE);
            int i = 0;
            while (i < randomRow) {
                resultSet.next();
                i++;
            }

            return resultSet.getString("id_produto");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getRandomCompraId() {
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(Query.selectSqlCompraId)) {

            int randomRow = random.nextInt(TABLES_MIN_SIZE);
            int i = 0;
            while (i < randomRow) {
                resultSet.next();
                i++;
            }

            return resultSet.getString("id_compra");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void setRandomInsertCliente(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, faker.numerify("###.###.###-##"));
            preparedStatement.setString(2, faker.name().name());
            preparedStatement.setString(3, faker.internet().emailAddress());
            preparedStatement.setString(4, faker.phoneNumber().cellPhone());
            preparedStatement.setString(5, getRandomEnderecoId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setRandomInsertEvento(PreparedStatement preparedStatement) {
        try {
            String f = FORMATOS_MTG[random.nextInt(FORMATOS_MTG.length)];
            preparedStatement.setString(1, faker.number().digits(10));
            preparedStatement.setString(2, f + " " + ADJETIVOS[random.nextInt(ADJETIVOS.length)] + " " + random.nextInt(1, 10));
            preparedStatement.setString(3, faker.date().birthday().toGMTString());
            preparedStatement.setString(4, f);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setRandomInsertParticipacaoEvento(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getRandomEventoId());
            preparedStatement.setString(2, getRandomClienteCpf());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String[] setRandomInsertProduto(PreparedStatement preparedStatement) {
        String randomId = faker.number().digits(10);
        int randomType = random.nextInt(3);
        // CARD = 0;
        // ACESSORIO = 1;
        // SELADO = 2;
        String productType;
        String id;

        try {
            preparedStatement.setString(1, randomId);
            switch (randomType) {
                // CARD
                case 0:  {
                    preparedStatement.setString(2, getRandomCardName());
                    preparedStatement.setDouble(3, random.nextDouble(0.1, 10));
                    preparedStatement.setInt(4, random.nextInt(1,20));
                    preparedStatement.setString(5, "Carta");

                    return new String[]{randomId, "Carta"};
                }
                // ACESSORIO
                case 1: {
                    String t = TIPO_ACESSORIO[random.nextInt(6)];

                    preparedStatement.setString(2, t + " " + faker.pokemon().name());
                    preparedStatement.setDouble(3, random.nextDouble(10,50));
                    preparedStatement.setInt(4, random.nextInt(0,20));
                    preparedStatement.setString(5, "Acessorio");

                    return new String[]{randomId, "Acessorio", t};
                }
                // SELADO
                case 2: {
                    String t = TIPO_SELADO[random.nextInt(6)];;

                    preparedStatement.setString(2, t + " " + faker.pokemon().name());
                    preparedStatement.setDouble(3, random.nextDouble(10,100));
                    preparedStatement.setInt(4, random.nextInt(0,20));
                    preparedStatement.setString(5, "Selado");

                    return new String[]{randomId, "Selado", t};
                }
                default: {
                    return null;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setRandomInsertCarta(PreparedStatement preparedStatement, String id) {
        try {
            preparedStatement.setString(1, faker.number().digits(10));
            preparedStatement.setString(2, faker.country().countryCode2());
            preparedStatement.setString(3, getRandomCardSet());
            preparedStatement.setString(4, EXTRA[random.nextInt(3)]);
            preparedStatement.setString(5, QUALIDADE[random.nextInt(6)]);
            preparedStatement.setString(6, id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setRandomInsertVenda(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, faker.number().digits(10));
            preparedStatement.setDouble(2, random.nextDouble(1,200));
            preparedStatement.setString(3, faker.date().birthday().toGMTString());
            preparedStatement.setString(4, getRandomClienteCpf());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setRandomInsertProdutoVendido(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getRandomVendaId());
            preparedStatement.setString(2, getRandomProdutoId());
            preparedStatement.setInt(3, random.nextInt(1,10));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setRandomInsertCartasCompradasDeCliente(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, getRandomCompraId());
            preparedStatement.setString(2, getRandomProdutoId());
            preparedStatement.setInt(3, random.nextInt(1,10));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setRandomInsertLoja(PreparedStatement preparedStatement) {
        try {
            preparedStatement.setString(1, DEFAULT_CPNJ_LOJA);
            preparedStatement.setString(2, DEFAULT_NOME_LOJA);
            preparedStatement.setString(3, faker.phoneNumber().cellPhone());
            preparedStatement.setString(4, getRandomEnderecoId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void insert(String table, String... args) {
        if (!Query.insertSQL.containsKey(table)) {
            return;
        }

        String sql = Query.insertSQL.get(table);

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            switch (table) {
                case "Endereco": {
                    setRandomInsertEndereco(pstmt);
                    pstmt.executeUpdate();
                    break;
                }
                case "Cliente": {
                    setRandomInsertCliente(pstmt);
                    pstmt.executeUpdate();
                    break;
                }
                case "Evento": {
                    setRandomInsertEvento(pstmt);
                    pstmt.executeUpdate();
                    break;
                }
                case "ParticipacaoEvento": {
                    setRandomInsertParticipacaoEvento(pstmt);
                    pstmt.executeUpdate();
                    break;
                }
                case "Produto": {
                    String[] r = setRandomInsertProduto(pstmt);
                    pstmt.executeUpdate();
                    insert(r[1], r);
                    break;
                }
                case "Carta": {
                    setRandomInsertCarta(pstmt, args[0]);
                    pstmt.executeUpdate();
                    break;
                }
                case "Acessorio", "Selado": {
                    pstmt.setString(1, faker.number().digits(10));
                    pstmt.setString(2, args[2]);
                    pstmt.setString(3, args[0]);
                    pstmt.executeUpdate();
                    break;
                }
                case "Venda", "CompraDeCartaDeCliente": {
                    setRandomInsertVenda(pstmt);
                    pstmt.executeUpdate();
                    break;
                }
                case "ProdutoVendido": {
                    setRandomInsertProdutoVendido(pstmt);
                    pstmt.executeUpdate();
                    break;
                }
                case "CartasCompradasDeCliente": {
                    setRandomInsertCartasCompradasDeCliente(pstmt);
                    pstmt.executeUpdate();
                    break;
                }
                case "Loja": {
                    setRandomInsertLoja(pstmt);
                    pstmt.executeUpdate();
                    break;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    // no req
    public void endereco(int size) {
        for (int i = 0; i < size; i++) {
            this.insert("Endereco");
        }
    }

    // req: endereco
    public void cliente(int size) {
        for (int i = 0; i < size; i++) {
            this.insert("Cliente");
        }
    }

    // no req
    public void evento(int size) {
        for (int i = 0; i < size; i++) {
            this.insert("Evento");
        }
    }

    // req: evento
    //      cliente
    public void participacaoEvento(int size) {
        for (int i = 0; i < size; i++) {
            this.insert("ParticipacaoEvento");
        }
    }

    // no req
    public void produto(int size) {
        for (int i = 0; i < size; i++) {
            this.insert("Produto");
        }
    }

    // req: produto
    //      cliente
    public void venda(int size) {
        for (int i = 0; i < size; i++) {
            this.insert("Venda");
        }
    }

    // req: produto
    //      cliente
    public void compraDeCartaDeCliente(int size) {
        for (int i = 0; i < size; i++) {
            this.insert("CompraDeCartaDeCliente");
        }
    }

    // req: venda
    //      produto
    public void produtoVendido(int size) {
        for (int i = 0; i < size; i++) {
            this.insert("ProdutoVendido");
        }
    }

    // req: compra
    //      produto
    public void cartasCompradasDeCliente(int size) {
        for (int i = 0; i < size; i++) {
            this.insert("CartasCompradasDeCliente");
        }
    }

    private static final int DEFAULT_TABLE_POP_SIZE = 1000;
    public void allTables() {
        System.out.println("Populating Endereco");
        endereco(DEFAULT_TABLE_POP_SIZE);
        System.out.println("Populating Cliente");
        cliente(DEFAULT_TABLE_POP_SIZE);
        System.out.println("Populating Loja");
        insert("Loja");
        System.out.println("Populating Evento");
        evento(DEFAULT_TABLE_POP_SIZE);
        System.out.println("Populating ParticipacaoEvento");
        participacaoEvento(DEFAULT_TABLE_POP_SIZE);
        System.out.println("Populating Produto");
        produto(DEFAULT_TABLE_POP_SIZE);
        System.out.println("Populating Venda");
        venda(DEFAULT_TABLE_POP_SIZE);
        System.out.println("Populating CompraDeCartaDeCliente");
        compraDeCartaDeCliente(DEFAULT_TABLE_POP_SIZE);
        System.out.println("Populating ProdutoVendido");
        produtoVendido(DEFAULT_TABLE_POP_SIZE);
        System.out.println("Populating CartasCompradasDeCliente");
        cartasCompradasDeCliente(DEFAULT_TABLE_POP_SIZE);
    }
}
