package DAO;

import model.Entrenador;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.List;

public class EntrenadorDAO {

    private Session session;

    public void crearEntrenador (Entrenador entrenador){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(entrenador);
        session.getTransaction().commit();
        session.close();
    }
    public Entrenador obtenerEntrenadorID(int id){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Entrenador entrenador = session.get(Entrenador.class,id);
        session.getTransaction().commit();
        session.close();
        return entrenador;
    }
    public void editarEntrenador(Entrenador entrenador){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(entrenador);
        session.getTransaction().commit();
        session.close();
    }
    public String obtenerTodosEntrenadores(){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        //preparedStatement -> resultSet -> while ->rs.getString -> creaObjeto-> List
        Query<Entrenador> query = session.createQuery("FROM Entrenador", Entrenador.class);
        List<Entrenador> listaEntrenador = query.list();
        for (Entrenador entrenador: listaEntrenador){
            System.out.println(entrenador);
        }
        session.getTransaction().commit();
        session.close();
        return listaEntrenador.toString();
    }
}
