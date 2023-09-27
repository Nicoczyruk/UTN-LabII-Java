package GestionCuentas;

import java.util.ArrayList;

public class GestorCuentas {

    private ArrayList<CuentaPersona> cuentaPersonas;
    private ArrayList<CuentaSociedad> cuentaSociedad;

    public GestorCuentas() {
        this.cuentaPersonas = new ArrayList<CuentaPersona>();
        this.cuentaSociedad = new ArrayList<CuentaSociedad>();
    }

    public void agregarCuentaPersona(CuentaPersona cuenta) {
        boolean cuentaExiste = false;

        for (CuentaPersona persona : this.cuentaPersonas) {
            if (persona.numeroCuenta == cuenta.numeroCuenta) {
                cuentaExiste = true;
                System.out.println("Numero de cuenta encontrado en la base de datos, por favor edite el saldo");
                break;
            }
        }

        if (!cuentaExiste) {
            this.cuentaPersonas.add(cuenta);
        }
    }

    public void agregarCuentaSociedad(CuentaSociedad cuenta) {
        boolean exist = false;

        for(CuentaSociedad persona : this.cuentaSociedad) {
            if (persona.numeroCuenta == cuenta.numeroCuenta) {
                exist = true;
                System.out.println("Numero de cuenta ya existe, use otra opcion");
                break;
            }
        }

        if (!exist) {
            this.cuentaSociedad.add(cuenta);
        }
    }

    public void eliminarCuentaPersona(int numeroCuenta) {

        for (int i = 0; i < this.cuentaPersonas.size(); i++) {
            if (this.cuentaPersonas.get(i).numeroCuenta == numeroCuenta) {
                this.cuentaPersonas.remove(i);
                break;
            } else
                System.out.println("Persona no existe");
        }
    }

    public void eliminarCuentaSociedad(int numeroCuenta) {

        for (int i = 0; i < this.cuentaSociedad.size(); i++) {
            if (this.cuentaSociedad.get(i).numeroCuenta == numeroCuenta) {
                cuentaSociedad.remove(i);
                break;
            } else
                System.out.println("Empresa no existe");
        }
    }

    public void editarCuentaPersona(int numeroCuenta, double nuevoSaldo) {
        int id = -1;

        for(int i = 0; i < this.cuentaPersonas.size(); i++) {
            if (this.cuentaPersonas.get(i).numeroCuenta == numeroCuenta) {
                id = i;
                break;
            } else
                System.out.println("Numero de persona no encontrado");

        }

        if (id != -1) {
            this.cuentaPersonas.get(id).setSaldo(nuevoSaldo);
        }
    }

    public void editarCuentaSociedad(int numeroCuenta, double nuevoSaldo) {
        int id = -1;

        for (int i = 0; i < this.cuentaSociedad.size(); i++) {
            if (this.cuentaSociedad.get(i).numeroCuenta == numeroCuenta) {
                id = i;
                break;
            } else
                System.out.println("Numero de persona no encontrado");
        }
        if (id != -1) {
            this.cuentaSociedad.get(id).setSaldo(nuevoSaldo);
        }
    }

    public void mostrarTodasLasCuentas() {

        System.out.println("---------------------CUENTAS PERSONAS-----------------");

        for (int i = 0; i < this.cuentaPersonas.size(); i++ ) {
            this.cuentaPersonas.get(i).mostrarInformacion();
            System.out.println();
        }

        System.out.println("---------------------CUENTAS SOCIEDAD------------------");

        for (int j = 0; j < this.cuentaSociedad.size(); j++ ) {
            this.cuentaSociedad.get(j).mostrarInformacion();
            System.out.println();
        }


    }
}
