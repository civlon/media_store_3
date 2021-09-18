package test;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import tables.Offer;
import tables.Product;

public class TestModule implements ITestModule {
	
	public Session session;

	@Override
	public void init() {
		SessionFactory factory = new Configuration().configure().buildSessionFactory();

		this.session = factory.openSession();
	}

	@Override
	public void finish() {
		this.session.close();
	}

	@Override
	public void getProduct() {
		Scanner input = new Scanner(System.in);
		System.out.println("Bitte Produkt-ID eingeben: ");
		String id = input.nextLine();
		
		Transaction tx = this.session.beginTransaction();
		
		List products = this.session.createQuery("FROM Product WHERE productNumber = :id").setParameter("id", id).list();
		
		if(products.isEmpty()) {
			System.out.println("Zu dieser Produktnummer existiert kein Eintrag in der Datenbank.");
			tx.commit();
			return;
		}else {
		for (Iterator iterator = products.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			System.out.println("Produktnummer: " + product.getProductNumber());
			System.out.println("Titel: " + product.getTitle());
			System.out.println("Produktgruppe: " + product.getProductGroup());
//			System.out.println("Ähnliche Produkte: ");
//			for (Product similarProduct : product.getSimilarProducts()) {
//				System.out.println(similarProduct.getProductNumber());
//			}
//			System.out.println("Angebote: ");
//			for (Offer offer : product.getOffers()) {
//				System.out.println(offer.getBranchName());
//			}
//			System.out.println();
			break;
		}
		}
		tx.commit();

	}

	@Override
	public void getProducts(String pattern) {
		Transaction tx = this.session.beginTransaction();
		String queryString = "FROM Product p WHERE p.title like :pattern";
		
		List products = this.session.createQuery(queryString).setParameter("pattern", pattern).list();
		
		if(pattern == null) {
			queryString = "From Product";
			products = this.session.createQuery(queryString).getResultList();
		}

		if(products.isEmpty()) {
			System.out.println("Das Pattern stimmt mit keinem Titel überein.");
			tx.commit();
			return;
		}else {
			for (Iterator iterator = products.iterator(); iterator.hasNext();) {
				Product product = (Product) iterator.next();
				System.out.println("Produktnummer: " + product.getProductNumber());
				System.out.println("Titel: " + product.getTitle());
				System.out.println("Produktgruppe: " + product.getProductGroup());
	//			System.out.println("Ähnliche Produkte: ");
	//			for (Product similarProduct : product.getSimilarProducts()) {
	//				System.out.println(similarProduct.getProductNumber());
	//			}
	//			System.out.println("Angebote: ");
	//			for (Offer offer : product.getOffers()) {
	//				System.out.println(offer.getBranchName());
	//			}
				System.out.println();
		}}
		
		tx.commit();

	}

	@Override
	public void getCategoryTree() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getProductsByCategoryPath() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getTopProducts() {
		int k = 5;
		
		Transaction tx = this.session.beginTransaction();
		
		List products = this.session.createQuery("FROM Product p WHERE p.rating IS NOT NULL ORDER BY p.rating DESC").setMaxResults(k).list();
		
		if(products.isEmpty()) {
			System.out.println("Fehler");
			tx.commit();
			return;
		}else {
		for (Iterator iterator = products.iterator(); iterator.hasNext();) {
			Product product = (Product) iterator.next();
			System.out.println("Produktnummer: " + product.getProductNumber());
			System.out.println("Titel: " + product.getTitle());
			System.out.println("Rating: " + product.getRating());
//			System.out.println("Ähnliche Produkte: ");
//			for (Product similarProduct : product.getSimilarProducts()) {
//				System.out.println(similarProduct.getProductNumber());
//			}
//			System.out.println("Angebote: ");
//			for (Offer offer : product.getOffers()) {
//				System.out.println(offer.getBranchName());
//			}
//			System.out.println();
		}
		}
		tx.commit();

	}

	@Override
	public void getSimilarCheaperProduct() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addNewReview() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getTrolls() {
		// TODO Auto-generated method stub

	}

	@Override
	public void getOffers(String id) {
		// TODO Auto-generated method stub

	}

}
