package click.tomasz.network.persistence;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Paths;

import click.tomasz.network.model.Network;

public class FileUtil {

	public static void save(Network network, String path) throws IOException {
		if (path.endsWith(".ann")) {
			try {
				FileOutputStream fos = new FileOutputStream(path);
				ObjectOutputStream oos = new ObjectOutputStream(fos);
				oos.writeObject(network);
				oos.close();
				System.out.println("Done");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} else {
			throw new IOException("File format is invalid.");
		}
	}

	public static Network load(String path) throws FileNotFoundException {
		Network network;
		if (path != null && Paths.get(path).toFile().exists()) {
			try {
				FileInputStream fis = new FileInputStream(path);
				ObjectInputStream ois = new ObjectInputStream(fis);
				network = (Network) ois.readObject();
				ois.close();
				return network;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		throw new FileNotFoundException();
	}
}
