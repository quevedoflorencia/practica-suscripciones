package com.tallerwebi.dominio;

import com.tallerwebi.dominio.excepcion.ClienteExistente;

import java.util.List;

public interface SerivicioSuscripcion {
    Cliente buscarCliente (Long dni);
    void guardar (Cliente cliente) throws ClienteExistente;
    List<Cliente> obtenerHistorial();

    List<Cliente> listarPorPlanSolicitado(Plan plan);

    void eliminarSuscriptor(Long dni);
}
