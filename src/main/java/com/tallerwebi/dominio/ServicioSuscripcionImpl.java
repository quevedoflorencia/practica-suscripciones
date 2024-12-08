package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.ClienteExistente;
import com.tallerwebi.dominio.excepcion.UsuarioExistente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ServicioSuscripcionImpl implements SerivicioSuscripcion{

    private RepositorioSuscripcion repositorioSuscripcion;

    @Autowired
    public ServicioSuscripcionImpl(RepositorioSuscripcion repositorioSuscripcion){this.repositorioSuscripcion=repositorioSuscripcion;}

    @Override
    public Cliente buscarCliente(Long dni) {
        return repositorioSuscripcion.buscarCliente(dni);
    }

    @Override
    public void guardar(Cliente cliente) throws ClienteExistente {
      Cliente clienteEncontrado = repositorioSuscripcion.buscarCliente(cliente.getDni());
      if (clienteEncontrado != null){
          throw new ClienteExistente();
      }
      repositorioSuscripcion.guardar(cliente);
    }

    @Override
    public List<Cliente> obtenerHistorial() {
        return repositorioSuscripcion.obtenerHistorial();
    }

    @Override
    public List<Cliente> listarPorPlanSolicitado(Plan plan) {
        return repositorioSuscripcion.listarPorPlanSolicitaado(plan);
    }

    @Override
    public void eliminarSuscriptor(Long dni) {
        repositorioSuscripcion.eliminarSuscriptor(dni);
    }
}
