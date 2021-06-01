import conexaojdbc.SingleConnection;
import dao.Userposdao;
import model.Userposjava;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

public class TesteBancoJdbc {

    @Test
    public void initBanco(){//método de insert
        Userposdao userposdao = new Userposdao();
        Userposjava userposjava = new Userposjava();


        userposjava.setNome("Samuel Rocha");
        userposjava.setEmail("samuel.rocha@email.com");

        userposdao.salvar(userposjava);

    }

    @Test
    public void initListar() throws SQLException {
        Userposdao dao = new Userposdao();

        List<Userposjava> list = dao.listar();

        for (Userposjava userposjava : list) {

            System.out.println(userposjava);
        }

    }


    @Test
    public void initBuscar(){
        try {
            Userposdao dao = new Userposdao();
            Userposjava userposjava = dao.buscarUm(3L);
            System.out.println(userposjava);

        }catch (SQLException e4){
            e4.printStackTrace();
        }


    }

    @Test
    public void initAtualizar(){
        Userposdao dao = new Userposdao();
        try {

            Userposjava objetoBanco = dao.buscarUm(1L);
            objetoBanco.setNome("Nome mudado com método aatualizar");
            dao.atualizarContato(objetoBanco);

        } catch (SQLException throwables) {

            throwables.printStackTrace();

        }
    }

    @Test
    public void initDeletar(){
        Userposdao dao = new Userposdao();
        try {

            dao.deletar(1L);

        }catch (Exception e2){
            e2.printStackTrace();
        }


    }

}
