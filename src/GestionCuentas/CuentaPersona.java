package GestionCuentas;

public class CuentaPersona extends Cuenta {

    private String nombre;
    private String apellido;

    public CuentaPersona(int numeroCuenta, double saldo, String nombre, String apellido) {
        super(numeroCuenta,saldo);
        this.nombre = nombre;
        this.apellido = apellido;
    }

    @Override
    public void depositar(double cantidad) {
        this.saldo += cantidad;
    }

    @Override
    public void retirar(double cantidad) {
        if (this.saldo >= cantidad)
            this.saldo -= cantidad;
        else {
            System.out.println("Saldo insuficiente");
        }
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Informacion de la cuenta\n"+
                "Numero de cuenta: "+this.numeroCuenta+"\n"+
                "Nombre: "+this.nombre+"\n"+
                "Apellido: "+this.apellido+"\n"+
                "Saldo: $"+this.saldo+"\n");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
}
