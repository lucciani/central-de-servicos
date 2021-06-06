package io.github.lucciani.cs.infrastructure.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UtilitarioData {

	public static LocalDateTime getDataFormatada(LocalDateTime data) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String dataFormatada = formatter.format(data);
		return LocalDateTime.parse(dataFormatada, formatter);

	}

	public static LocalDateTime getInicioDia(LocalDateTime data) {
		return data.toLocalDate().atStartOfDay();
	}

}
