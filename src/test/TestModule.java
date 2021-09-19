package test;

import java.util.Iterator;
import java.util.Date;
import java.time.LocalDateTime;    
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import tables.Offer;
import tables.Product;
import tables.Review;

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
		Scanner input = new Scanner(System.in);
		System.out.println("Wie viele Produkte sollen angezeigt werden?: ");
		int k = input.nextInt();

		
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
//		Scanner input = new Scanner(System.in);
//		System.out.println("Bitte Produkt-ID eingeben: ");
//		String productId = input.nextLine();
//
//		Transaction tx = this.session.beginTransaction();
//		
//		List products = this.session.createQuery("FROM SimilarProducts sp INNER JOIN Product p WHERE (sp.productNumber1 = :productId AND sp.productNumber2 = p.productNumber AND )").list();
//		
//		if(products.isEmpty()) {
//			System.out.println("Fehler");
//			tx.commit();
//			return;
//		}else {
//		for (Iterator iterator = products.iterator(); iterator.hasNext();) {
//			Product product = (Product) iterator.next();
//			System.out.println("Produktnummer: " + product.getProductNumber());
//			System.out.println("Titel: " + product.getTitle());
//			System.out.println("Rating: " + product.getRating());
//			System.out.println("Ähnliche Produkte: ");
//			for (Product similarProduct : product.getSimilarProducts()) {
//				System.out.println(similarProduct.getProductNumber());
//			}
//			System.out.println("Angebote: ");
//			for (Offer offer : product.getOffers()) {
//				System.out.println(offer.getBranchName());
//			}
//			System.out.println();
//		}
//		}
//		tx.commit();

	}

	@Override
	public void addNewReview() {
		Scanner input = new Scanner(System.in);
		System.out.println("Nutzername: ");
		String username = input.nextLine();
		System.out.println("Produktnummer: ");
		String productNumber = input.nextLine();
		System.out.println("Sterne: ");
		Short stars = input.nextShort();
		System.out.println("Zusammenfassung: ");
		String summary = input.nextLine();
		System.out.println("Text: ");
		String reviewText = input.nextLine();
		Date date = new Date();
		
		Transaction tx = this.session.beginTransaction();
		Review review = new Review();
		review.setProductNumber(productNumber);
		review.setUsername(username);
		review.setStars(stars);
		review.setSummary(summary);
		review.setReviewText(reviewText);
		review.setReviewDate((java.sql.Date) date);
		
		this.session.save(review);
		tx.commit();

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
