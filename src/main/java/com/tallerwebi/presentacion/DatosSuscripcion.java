package com.tallerwebi.presentacion;

import com.tallerwebi.dominio.Plan;

public class DatosSuscripcion {
    private  Long dni;
    private Plan plan;

    public DatosSuscripcion() {
    }

    public DatosSuscripcion(Long dni) {
        this.dni = dni;
    }

    public DatosSuscripcion(Long dni, Plan plan) {
        this.dni = dni;
        this.plan = plan;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }
}














