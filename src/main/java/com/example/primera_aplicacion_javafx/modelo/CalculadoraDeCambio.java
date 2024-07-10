package com.example.primera_aplicacion_javafx.modelo;

public class CalculadoraDeCambio {
    private double montoIntercambio;

    public double getMontoIntercambio() {
        return montoIntercambio;
    }

    public void calculaIntercambio(Double mBase, Resultado resultado){
        //System.out.println(algo.getTasaDeConversion());
        //System.out.println(mBase);
        this.montoIntercambio = mBase * resultado.getTasaDeConversion();
        //System.out.println(montoIntercambio);

    }

}
