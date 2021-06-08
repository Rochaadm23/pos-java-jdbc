package dao;

import conexaojdbc.SingleConnection;
import model.Telefone;
import model.Userposjava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*Persistência de dados*/
public class Userposdao {

    private Connection connection;

    public Userposdao() {
        this.connection = SingleConnection.getConnection();
    }

    /*Método para salvar os dados do usuário*/
    public void salvar(Userposjava userposjava) {
        try {

            String sql = "insert into userposjava (nome, email) values (?, ?)";
            PreparedStatement insert = connection.prepareStatement(sql);

            insert.setString(1, userposjava.getNome());
            insert.setString(2, userposjava.getEmail());
            insert.execute();
            /*Salva no BD*/
            connection.commit();


        } catch (Exception e) {
            try {
                connection.rollback();//reverte operação
            } catch (SQLException e1) {
                e1.printStackTrace();
            }

            e.printStackTrace();
        }
    }
    /*Salva telefone do usuário*/
    public void salvarTelefone(Telefone telefone){
        try {
            String sql = "INSERT INTO public.telefoneuser(numero, tipo, usuariopessoa) VALUES (?, ?, ?);";
            PreparedStatement insert = connection.prepareStatement(sql);

            insert.setString(1, telefone.getNumero());
            insert.setString(2, telefone.getTipo());
            insert.setLong(3, telefone.getUsuario());

            insert.execute();

            connection.commit();

        }catch (Exception e){
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            e.printStackTrace();
        }

    }
    //Lista todos os usuários do  banco de dados
    public List<Userposjava> listar() throws SQLException {

        List<Userposjava> list = new ArrayList<Userposjava>();
        String sql = "select * from userposjava";
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado = statement.executeQuery();

        while (resultado.next()) {
            Userposjava userposjava = new Userposjava();
            userposjava.setId(resultado.getLong("id"));
            userposjava.setNome(resultado.getString("nome"));
            userposjava.setEmail(resultado.getString("email"));

            list.add(userposjava);
        }

        return list;

    }

    /*Busca por um usuário(objeto) só*/
    public Userposjava buscarUm(Long id) throws SQLException {

        Userposjava retorno = new Userposjava();
        String sql = "select * from userposjava where id = " + id;
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultado = statement.executeQuery();

        while (resultado.next()) {// retona apenas um ou nenhum

            retorno.setId(resultado.getLong("id"));
            retorno.setNome(resultado.getString("nome"));
            retorno.setEmail(resultado.getString("email"));


        }

        return retorno;

    }

    //Atualiza o usuário pelo ID
    public void atualizarContato(Userposjava userposjava){

        try {
            String sql = "update userposjava set nome = ? where id = " + userposjava.getId();
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, userposjava.getNome());
            statement.execute();
            connection.commit();

        }catch (SQLException e){
            try {
                connection.rollback();

            }catch (SQLException e6){
                e6.printStackTrace();
            }

            e.printStackTrace();
        }


    }

    //Deleta usuário pelo ID
    public void deletar(Long id){
        try {

            String sql = "delete from userposjava where id = " + id;
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            connection.commit();

        }catch (Exception e){

            try {
                connection.rollback();
            }catch (Exception e2){
                e2.printStackTrace();
            }
            e.printStackTrace();

        }

    }
}
