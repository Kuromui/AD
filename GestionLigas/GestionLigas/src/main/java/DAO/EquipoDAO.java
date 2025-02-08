package DAO;

import model.Equipo;
import model.Liga;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtils;

import java.util.List;
import java.util.Scanner;

public class EquipoDAO {

    private Session session;
    //CREAR EQUIPO
    public void crearEquipo(Equipo equipo){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.persist(equipo);
        session.getTransaction().commit();
        session.close();
    }

    //EDITAR EQUIPO
    public void editarEquipo(Equipo equipo){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.merge(equipo);
        session.getTransaction().commit();
        session.close();
    }

    //ELIMINAR EQUIPO DE UNA LIGA
    public void eliminarEquipoLiga(int id_equipo, int id_liga){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        //String querySTR = "UPDATE Equipo e SET e.liga = NULL WHERE e.id = :id_equipo AND e.liga.id = :id_liga";
        Equipo equipo = session.get(Equipo.class,id_equipo);
        Liga liga = session.get(Liga.class,id_liga);
        if (equipo != null){
            equipo.setLiga(null);
            session.merge(equipo);
        }
           session.getTransaction().commit();
           session.close();
    }

    //OBTENER EQUIPO POR ID
    public Equipo obtenerEquipoID(int id){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Equipo equipo = session.get(Equipo.class,id);
        session.getTransaction().commit();
        session.close();
        return equipo;
    }

    //MOSTRAR EQUIPOS
    public String equiposDisponibles(){
        session = new HibernateUtils().getSessionFactory().getCurrentSession();
        session.beginTransaction();
        Query<Equipo> query = session.createNamedQuery("Equipo.findAll", Equipo.class);
        List<Equipo> equipoList = query.list();
        for (Equipo equipo : equipoList){
            System.out.println(equipo);
        }
        session.getTransaction().commit();
        session.close();
        return equipoList.toString();
    }

}
