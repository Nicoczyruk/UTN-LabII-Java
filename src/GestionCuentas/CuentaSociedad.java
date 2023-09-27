package GestionCuentas;

public class CuentaSociedad extends Cuenta {

    private String nombreEmpresa;
    private String tipoEmpresa;

    public CuentaSociedad(int numeroCuenta, double saldo, String nombreEmpresa, String tipoEmpresa) {
        super(numeroCuenta,saldo);
        this.nombreEmpresa = nombreEmpresa;
        this.tipoEmpresa = tipoEmpresa;
    }

    @Override
    public void depositar(double cantidad) {
        this.saldo += cantidad;
    }

    @Override
    public void retirar(double cantidad) {
        if (this.saldo >= cantidad) {
            this.saldo -= cantidad;
        } else
            System.out.println("Saldo Insuficiente");
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Informacion de la cuenta\n"+
                "Numero de cuenta: "+this.numeroCuenta+"\n"+
                "Empresa: "+this.nombreEmpresa+"\n"+
                "Tipo: "+this.tipoEmpresa+"\n"+
                "Saldo: $"+this.saldo+"\n");;

    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getTipoEmpresa() {
        return tipoEmpresa;
    }

    public void setTipoEmpresa(String tipoEmpresa) {
        this.tipoEmpresa = tipoEmpresa;
    }
}
