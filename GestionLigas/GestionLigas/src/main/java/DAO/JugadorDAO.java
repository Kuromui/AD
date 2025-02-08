package DAO;


import model.Jugador;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.List;

public class JugadorDAO {

    private Session session;

    //CREAR JUGADOR
    public void crearJugador(Jugador jugador){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(jugador);
        session.getTransaction().commit();
        session.close();
    }
    public Jugador obtenerJugadorID(int id){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Jugador jugador = session.get(Jugador.class,id);
        session.getTransaction().commit();
        session.close();
        return jugador;
    }
    public void editarJugador(Jugador jugador){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(jugador);
        session.getTransaction().commit();
        session.close();
    }
    //MOSTRAR TODOS JUGADORES

    public String obtenerTodosJugadores(){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        //preparedStatement -> resultSet -> while ->rs.getString -> creaObjeto-> List
        Query<Jugador> query = session.createQuery("FROM Jugador", Jugador.class);
        List<Jugador> lista = query.list();
        for (Jugador jugador: lista){
            System.out.println(jugador);
        }
        session.getTransaction().commit();
        session.close();
        return lista.toString();
    }
}
