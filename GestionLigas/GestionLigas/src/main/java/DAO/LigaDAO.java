package DAO;

import model.Liga;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.List;

public class LigaDAO {

    private Session session;

    //CREAR LIGA
    public void crearLiga(Liga liga){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(liga);
        session.getTransaction().commit();
        session.close();
    }


    //OBTENER ID
    public Liga obtenerLigaID(int id){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Liga liga = session.get(Liga.class,id);
        session.getTransaction().commit();
        session.close();
        return liga;
    }

    //EDITAR LIGA
    public Liga actualizarLigaID(int id){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        //Buscamos liga
        Liga liga = session.get(Liga.class,id);
        //Introducimos los datos a actualizar
        if (liga != null){
            liga.setNombre_liga("Nombrecito");
            session.merge(liga);
        }
        session.getTransaction().commit();
        session.close();
        return liga;
    }

    public void editarLiga(Liga liga){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(liga);
        session.getTransaction().commit();
        session.close();
    }

    //ELIMINAR LIGA
    public void eliminarLigaID(int id){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
       session.beginTransaction();
        Liga liga = session.get(Liga.class,id);
        if (liga != null){
            session.delete(liga);
        }
        session.getTransaction().commit();
        session.close();
    }

    //OBTENER LIGAS DISPONIBLES
    public String ligasDisponibles(){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Liga> query = session.createNamedQuery("Liga.findAll", Liga.class);
        List<Liga> ligaList = query.list();
        for (Liga liga : ligaList){
            System.out.println(liga);
        }
        session.getTransaction().commit();
        session.close();
        return ligaList.toString();
    }
}
