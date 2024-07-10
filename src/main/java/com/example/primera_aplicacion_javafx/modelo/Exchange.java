package com.example.primera_aplicacion_javafx.modelo;

public record Exchange(
        String result,
        String base_code,
        String target_code,
        double conversion_rate) {
}
