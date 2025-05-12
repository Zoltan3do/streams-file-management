package utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.commons.io.FileUtils;


import entities.Product;

public class Utils {
	
	public static void salvaProdottiSuDisco(List<Product> prodotti) {
		String path = "src/products.txt";
		File file = new File(path);
		prodotti.forEach(p -> {
			try {
				FileUtils.writeStringToFile(file, 
						p.getCategory() + "@" + p.getName() + "@" + p.getPrice() + "#",
						StandardCharsets.UTF_8, true);
				System.out.println("Done!");
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
	}
	
	
	
	public static String[] leggiProdottiDaDisco() {
		String path = "src/products.txt";
		File file = new File(path);
		try {
			return FileUtils.readFileToString(file,StandardCharsets.UTF_8).split("@");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
