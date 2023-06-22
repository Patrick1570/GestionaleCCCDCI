package modules;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateManager {
	
	//Restituisce il numero di giorni trascorsi fra una data a oggi
	public int getDaysBetween(String date) {
		return (int) ChronoUnit.DAYS.between(parseDate(date), LocalDate.now());
	}
	
	//Restituisce il numero di giorni trascorsi fra una data a oggi
		public int getDaysBetween(LocalDate date) {
			return (int) ChronoUnit.DAYS.between(date, LocalDate.now());
		}
	
	//Restituisce i giorni passati fra due date
	public int getDaysBetween(String pastDate, String nextDate) {
		
		LocalDate date1 = parseDate(pastDate);
		LocalDate date2 = parseDate(nextDate);

		return (int) ChronoUnit.DAYS.between(date1, date2);
	}
	
	//Restituisce i giorni passati fra due date
		public int getDaysBetween(LocalDate pastDate, LocalDate nextDate) {
			
			return (int) ChronoUnit.DAYS.between(pastDate, nextDate);
		}
	
	
	//Restituisce un oggetto LocalDate a partire da una stringa gg/mm/aaaa
	public LocalDate parseDate(String date) {
		
		String parts[] = date.split("/");
				
		try {
			return LocalDate.of(Integer.parseInt(parts[2]), Integer.parseInt(parts[1]), Integer.parseInt(parts[0]));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public LocalDate today() {
		return LocalDate.now();
	}
	
}
