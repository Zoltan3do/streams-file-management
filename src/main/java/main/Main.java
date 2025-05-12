package main;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

import entities.Customer;
import entities.Order;
import entities.Product;
import utils.Utils;

public class Main {

	public static void main(String[] args) {
		
		Customer customer1 = new Customer(1, "Alice Johnson", 1);
		Customer customer2 = new Customer(2, "Bob Smith", 2);
		Customer customer3 = new Customer(3, "Charlie Davis", 3);
		Customer customer4 = new Customer(4, "Diana Moore", 1);
		Customer customer5 = new Customer(5, "Eve Taylor", 2);
		Customer customer6 = new Customer(6, "Frank Brown", 3);
		Customer customer7 = new Customer(7, "Grace Green", 1);
		Customer customer8 = new Customer(8, "Henry White", 2);
		Customer customer9 = new Customer(9, "Ivy Harris", 3);
		Customer customer10 = new Customer(10, "Jack Wilson", 1);
		
		Product product1 = new Product(1, "Laptop", "Electronics", 1200.00);
		Product product2 = new Product(2, "Smartphone", "Electronics", 800.00);
		Product product3 = new Product(3, "Tablet", "Electronics", 400.00);
		Product product4 = new Product(4, "Chair", "Furniture", 150.00);
		Product product5 = new Product(5, "Desk", "Furniture", 300.00);
		Product product6 = new Product(6, "Couch", "Furniture", 700.00);
		Product product7 = new Product(7, "Refrigerator", "Appliances", 1000.00);
		Product product8 = new Product(8, "Microwave", "Appliances", 200.00);
		Product product9 = new Product(9, "Oven", "Appliances", 900.00);
		Product product10 = new Product(10, "Blender", "Appliances", 100.00);

		Order order1 = new Order(1, List.of(product1, product2), customer1);
		Order order2 = new Order(2, List.of(product3), customer2);
		Order order3 = new Order(3, List.of(product4, product5), customer3);
		Order order4 = new Order(4, List.of(product6), customer4);
		Order order5 = new Order(5, List.of(product7, product8), customer5);
		Order order6 = new Order(6, List.of(product9), customer6);
		Order order7 = new Order(7, List.of(product10, product1), customer7);
		Order order8 = new Order(8, List.of(product2, product3), customer8);
		Order order9 = new Order(9, List.of(product4, product6), customer9);
		Order order10 = new Order(10, List.of(product7, product10), customer10);

		
		/*Esercizio 1#
		 * Raggruppare gli ordini per cliente utilizzando Stream e Lambda Expressions. Crea una mappa in cui la 
		 * chiave è il cliente e il valore è una lista di ordini effettuati da quel cliente.
		 * */
		
		List<Order> orders = List.of(order1,order2,order3,order4,order5,order6,order7,order8,order9,order10);
		
		Map<Customer, List<Order>> ordiniPerCliente = 
				orders.stream().collect(Collectors.groupingBy(Order::getCustomer));
		
		ordiniPerCliente.forEach((customer,ordersList) -> {
			System.out.println("Customer: "+ customer);
			ordersList.forEach(order -> System.out.println("\n" + order));
		});
		
		
		
		System.out.println("---------------------------------------------------------------------------");
		/*Esercizio 2#
		 * Dato un elenco di ordini, calcola il totale delle vendite per ogni cliente utilizzando Stream e 
		 * Lambda Expresions. Crea una mappa in cui la chiave  è il  cliente e il valore 
		 * è l'importo totale dei suoi acquisti.
		 * */
		
		Map<Customer,Double> totaleAcquisti = orders.stream()
				.collect(Collectors.groupingBy(Order::getCustomer,
						Collectors.summingDouble(order -> order.getProducts().stream()
						.mapToDouble(Product::getPrice).sum())));
		
		totaleAcquisti.forEach((customer,total) -> {
			System.out.println("Customer: " + customer + ", articoli: " + total + "€");
		});
		
		
		
		System.out.println("---------------------------------------------------------------------------");
		/*
		 * Esercizio 3#
		 * Dato un elenco di prodotti, trova i prodotti più costosi utilizzando Stream e Lambda Expressions
		 * 
		 */
		List<Product> products = 
				List.of(product1, product2, product3, product4, product5,
						product6, product7, product8, product9, product10);
		
		Optional<Product> maxPriced = products.stream().max(Comparator.comparingDouble(Product::getPrice));
		
		maxPriced.ifPresent(product ->  {
			System.out.println("Il prodotto più costoso è : " + product);
		});
		
		
		
		System.out.println("---------------------------------------------------------------------------");
		/*
		 * Esercizio 4#
		 * Dato un elenco di ordini, calcola la media degli importi degli ordini utilizzando Stream e Lambda Expressions.
		 * 
		 * */
		
		OptionalDouble average  = orders.stream().mapToDouble(order -> order.getProducts().stream()
				.mapToDouble(Product::getPrice).sum())
				.average();
		
		System.out.println("La media dei prezzi di tutti gli ordini è: " + average);
		
		
		
		System.out.println("---------------------------------------------------------------------------");
		/*
		 * Esercizio 5#
		 * Dato un elenco di prodotti, raggruppa i prodotti per categoria e calcola la somma degli importi per
		 * ogni categoria utilizzando Stream e Lambda Expressions.
		 * */
		
		Map<String, Double> mediaPerCategoria = products.stream()
				.collect(Collectors.groupingBy(Product::getCategory,
						Collectors.summingDouble(product -> product.getPrice())));		
		
		mediaPerCategoria.forEach((categoria,somma) -> {
			System.out.println("Categoria: " + categoria + ", somma: " + somma);
		});
		
		
		
		System.out.println("---------------------------------------------------------------------------");
		/*
		 * [EXTRA] Esercizio 6#
		 * Usando la classe Apache Commons IO FileUtils implementare un metodo salvaProdottiSuDisco che 
		 * salvi su disco un file contenente la lista dei prodotti. Utilizzare un formato simile al seguente
		 * per storicizzare i dati su file:
		 * nomeprodotto1@categoriaprodotto1@prezzoprodotto1@#nomeprodotto2@categoriaprodotto2@prezzoprodotto2
		 * */
		
		Utils.salvaProdottiSuDisco(products);
		
		
		
		System.out.println("---------------------------------------------------------------------------");
		/**
		 * [EXTRA] Esercizio #7 
		 * Sempre usando la classe Apache Commons IO FileUtils Implementare un metodo leggiProdottoDaDisco che
		 * Riempia un ArrayList con il contenuto del file salvato al punto 6.
		 */
		
		for(String s: Utils.leggiProdottiDaDisco()) {
			System.out.println(s);
		}
		
		
	}
	
}
