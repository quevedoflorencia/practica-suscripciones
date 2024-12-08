package com.tallerwebi.infraestructura;

import com.tallerwebi.dominio.Cliente;
import com.tallerwebi.dominio.Plan;
import com.tallerwebi.dominio.RepositorioSuscripcion;
import com.tallerwebi.dominio.Usuario;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository

public class RepositorioSuscripcionImpl implements RepositorioSuscripcion {
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioSuscripcionImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }
    /*
    @Override
    public Cliente consultarCliente(Cliente cliente) {
        final Session session = sessionFactory.getCurrentSession();
        return (Cliente) session.createCriteria(Cliente.class)
                .add(Restrictions.eq("dni", cliente.getDni()))
                .uniqueResult();
    }*/

    @Override
    public Cliente buscarCliente(Long dni) {

        return (Cliente) sessionFactory.getCurrentSession().createCriteria(Cliente.class)
                .add(Restrictions.eq("dni", dni))
                .uniqueResult();
    }

    @Override
    public void guardar(Cliente cliente) {
        sessionFactory.getCurrentSession().save(cliente);
    }

    @Override
    public List<Cliente> obtenerHistorial() {
        return sessionFactory.getCurrentSession()
                .createCriteria(Cliente.class)
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @Override
    public List<Cliente> listarPorPlanSolicitaado(Plan plan) {
        return sessionFactory.getCurrentSession()
                .createCriteria(Cliente.class)
                .add(Restrictions.eq("plan", plan))
                .setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY)
                .list();
    }

    @Override
    public void eliminarSuscriptor(Long dni) {
        Cliente clienteAEliminar= buscarCliente(dni);
        sessionFactory.getCurrentSession().delete(clienteAEliminar);
    }
}
