package com.tallerwebi.dominio;

import java.util.List;

public interface RepositorioSuscripcion {
    Cliente buscarCliente (Long dni);

    void guardar(Cliente cliente);

    List<Cliente> obtenerHistorial();

    List<Cliente> listarPorPlanSolicitaado(Plan plan);

    void eliminarSuscriptor(Long dni);
}
