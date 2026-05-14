package br.com.serratec.aula5.enums;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.serratec.aula5.exceptions.EnumValidationException;

public enum Categoria {
    HATCH, SEDAN, SUV, PICAPE, CONVERSIVEL, ESPORTIVO, COUPE;

    @JsonCreator
    public static Categoria verificaEnum(String value) {
        for (Categoria c : Categoria.values()) {
            if (c.name().equals(value)) {
                return c;
            }
        }
        throw new EnumValidationException("Categoria não encontrada!");
    }

    
}
