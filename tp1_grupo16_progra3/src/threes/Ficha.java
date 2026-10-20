package threes;

import java.awt.Color;

public class Ficha {
    private final int valor;

    public Ficha(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public boolean esVacia() {
        return valor == 0;
    }

    public boolean puedeCombinarCon(Ficha otra) {
        if (this.esVacia() || otra == null || otra.esVacia()) {
            return false;
        }
        int v1 = this.valor;
        int v2 = otra.getValor();

        if ((v1 == 1 && v2 == 2) || (v1 == 2 && v2 == 1)) {
            return true;
        }
        return (v1 >= 3 && v1 == v2);
    }

    public Ficha combinarCon(Ficha otra) {
        if (!puedeCombinarCon(otra)) {
            throw new IllegalArgumentException("Las fichas no son combinables");
        }
        return new Ficha(this.valor + otra.getValor());
    }

    private Color obtenerColorCasilla(int valor) {
        switch (valor) {
            case 0: return new Color(200, 200, 200);
            case 1: return new Color(100, 200, 255);
            case 2: return new Color(255, 100, 120);
            case 3: return Color.WHITE;
            case 6: return new Color(255, 235, 150);
            case 12: return new Color(255, 200, 150);
            case 24: return new Color(255, 150, 150);
            case 48: return new Color(150, 255, 150);
            case 96: return new Color(150, 255, 255);
            case 192: return new Color(150, 150, 255);
            case 384: return new Color(220, 150, 255);
            case 768: return new Color(255, 150, 220);
            default: return new Color(220, 220, 220);
        }
    }

    private Color obtenerColorTexto(int valor) {
        if (valor == 1 || valor == 2) {
            return Color.WHITE;
        }
        return Color.BLACK;
    }

    public String getText() {
        return esVacia() ? "" : String.valueOf(valor);
    }
}