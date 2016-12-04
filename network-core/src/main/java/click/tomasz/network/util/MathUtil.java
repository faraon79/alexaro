package click.tomasz.network.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.concurrent.ThreadLocalRandom;

public class MathUtil {

	public static double round(double value) {
		return round(value, 6);
	}

	private static double round(double value, int size) {
		String format = createFormat(size);
		DecimalFormat df = new DecimalFormat(format);
		DecimalFormatSymbols dfs = new DecimalFormatSymbols();
		dfs.setDecimalSeparator('.');
		df.setDecimalFormatSymbols(dfs);
		return Double.valueOf(df.format(value));
	}

	private static String createFormat(int size) {
		StringBuffer format = new StringBuffer();
		format.append("0.0");
		if (size < 1) {
			return format.toString();
		}
		for (int i = 1; i < size; i++) {
			format.append("0");
		}
		return format.toString();
	}

	public static double getRandomWeight(double min, double max) {
		return round(ThreadLocalRandom.current().nextDouble(min, max), 4);
	}
}
