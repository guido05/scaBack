package com.sca.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

	public static LocalDate parseFecha(String fechaString) {
	    DateTimeFormatter[] formatters = new DateTimeFormatter[] {
	        DateTimeFormatter.ofPattern("d/M/yyyy"),
	        DateTimeFormatter.ofPattern("dd/MM/yyyy"),
	        DateTimeFormatter.ofPattern("d/MM/yyyy"),
	        DateTimeFormatter.ofPattern("dd/M/yyyy")
	    };

	    for (DateTimeFormatter formatter : formatters) {
	        try {
	            return LocalDate.parse(fechaString, formatter);
	        } catch (DateTimeParseException ignored) {}
	    }

	    throw new IllegalArgumentException("Formato de fecha inválido: " + fechaString);
	}
}
