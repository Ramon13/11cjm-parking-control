package br.com.srvforo11.parkingcontroller.util;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public final class DateUtils {

	private DateUtils() {}
	
	private static final Locale DEFAULT_LOCALE = new Locale("pt", "BR");
	private static final String DEFAULT_PATTERN = "dd/MM/yyyy HH:mm:ss";
	public static final ZoneOffset DEFAULT_TIMEZONE = ZoneOffset.of("-03:00");

    public static OffsetDateTime now() {
        return OffsetDateTime.now(DEFAULT_TIMEZONE).truncatedTo(ChronoUnit.MILLIS);
    }
	
	public static String toDefaultFormat(OffsetDateTime dateTime) {
		return dateTime.format(DateTimeFormatter.ofPattern(DEFAULT_PATTERN, DEFAULT_LOCALE));
	}
}
