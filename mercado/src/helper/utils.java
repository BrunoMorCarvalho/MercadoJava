package helper;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import java.util.Date;
import java.text.ParseException;

public class utils {
	
	static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	static NumberFormat nf = new DecimalFormat("R$ #,##0.00", 
			new DecimalFormatSymbols(new Locale("pt","BR")));
	
	public static String dateParaString(Date data) {
		return utils.sdf.format(data);
		
		}	
	
	public static String doubleParaString(Double valor) {
		return utils.nf.format(valor);
		}
	
	public static Double stringParaDouble(String valor) {
		try {
			return (Double)utils.nf.parse(valor);
		}catch(ParseException e ) {
			return null;
		}
	}
	
		public static void pausar(int segundos) {
			try {
				TimeUnit.SECONDS.sleep(segundos);
			}catch(InterruptedException e) {
				System.out.println("Erro ao pausar por " + segundos + " Segundos.");
			}
		}
}


