package click.tomasz.network.persistence;

public class IdGenerator {

	private static int counter = 0;

	public static String generate() {
		return String.valueOf(counter++);
	}
}
