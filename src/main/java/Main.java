package main.java;

import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import tables.Artist;
import tables.Author;
import tables.Book;
import tables.Branch;
import tables.Category;
import tables.Customer;
import tables.Dvd;
import tables.InvolvedPersons;
import tables.Music;
import tables.MusicTitle;
import tables.Offer;
import tables.Product;
import tables.ProductCategory;
import tables.Purchase;
import tables.Review;
import tables.SimilarProducts;


public class Main {

	public static void main(String[] args) {
//		// Verbindung zur Datenbank aufbauen
//		Session session =  new
//				Configuration().buildSessionFactory().openSession();
//		System.out.println(session.isConnected());
		
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		
		Session session = factory.openSession();
		
		Transaction tx = session.beginTransaction();
//		List products = session.createQuery("FROM Product").list();
//        for (Iterator iterator = products.iterator(); iterator.hasNext();){
//           Product product = (Product) iterator.next(); 
//           System.out.println("Produktnummer: " + product.getProductNumber()); 
//           System.out.println("Titel: " + product.getTitle()); 
//           System.out.println("Produktgruppe: " + product.getProductGroup());
//           break;
//        }
		
		
//		List products2 = session.createQuery("FROM Book").list();
//      for (Iterator iterator = products2.iterator(); iterator.hasNext();){
//         Book book = (Book) iterator.next(); 
//         System.out.println("Produktnummer: " + book.getProductNumber()); 
//         System.out.println("Seitenzahl: " + book.getPageNumbers()); 
//         System.out.println("Erscheinungsdatum: " + book.getReleaseDate());
//         System.out.println("ISBN-Nummer: " + book.getIsbnNumber());
//         System.out.println("Verlag: " + book.getPublisher());
//         break;
//      }
		
		
//		List products3 = session.createQuery("FROM Music").list();
//      for (Iterator iterator = products3.iterator(); iterator.hasNext();){
//         Music music = (Music) iterator.next(); 
//         System.out.println("Produktnummer: " + music.getProductNumber()); 
//         System.out.println("Label: " + music.getLabel()); 
//         System.out.println("Erscheinungsdatum: " + music.getReleaseDate());
//         break;
//      }
      
      
//		List products4 = session.createQuery("FROM Dvd").list();
//	      for (Iterator iterator = products4.iterator(); iterator.hasNext();){
//	         Dvd dvd = (Dvd) iterator.next(); 
//	         System.out.println("Produktnummer: " + dvd.getProductNumber()); 
//	         System.out.println("Format: " + dvd.getFormat()); 
//	         System.out.println("Laufzeit: " + dvd.getRuntime());
//	         System.out.println("Regioncode: " + dvd.getRegionCode());
//	         break;
//	      }
		
//		List products5 = session.createQuery("FROM Author").list();
//	      for (Iterator iterator = products5.iterator(); iterator.hasNext();){
//	         Author author = (Author) iterator.next(); 
//	         System.out.println("Produktnummer: " + author.getProductNumber()); 
//	         System.out.println("Name: " + author.getName()); 
//	         break;
//	      }
//		
//		List products6 = session.createQuery("FROM Artist").list();
//	      for (Iterator iterator = products6.iterator(); iterator.hasNext();){
//	         Artist artist = (Artist) iterator.next(); 
//	         System.out.println("Produktnummer: " + artist.getProductNumber()); 
//	         System.out.println("Künstler Name: " + artist.getStageName()); 
//	         break;
//	      }
//		
//		List products7 = session.createQuery("FROM MusicTitle").list();
//	      for (Iterator iterator = products7.iterator(); iterator.hasNext();){
//	         MusicTitle musicTitle = (MusicTitle) iterator.next(); 
//	         System.out.println("Produktnummer: " + musicTitle.getProductNumber()); 
//	         System.out.println("Musiktitel: " + musicTitle.getMusicTitle()); 
//	         break;
//	      }
//		
//		List products8 = session.createQuery("FROM InvolvedPersons").list();
//	      for (Iterator iterator = products8.iterator(); iterator.hasNext();){
//	         InvolvedPersons involvedPerons = (InvolvedPersons) iterator.next(); 
//	         System.out.println("Produktnummer: " + involvedPerons.getProductNumber()); 
//	         System.out.println("Name: " + involvedPerons.getName()); 
//	         System.out.println("Rolle: " + involvedPerons.getRole());
//	         break;
//	      }
		
//		List products9 = session.createQuery("FROM Category").list();
//	      for (Iterator iterator = products9.iterator(); iterator.hasNext();){
//	         Category category = (Category) iterator.next(); 
//	         System.out.println("KategorieId: " + category.getCategoryId()); 
//	         System.out.println("Name: " + category.getName()); 
//	         System.out.println("ÜberKategorie: " + category.getOverCategory());
//	         break;
//	      }
		
//		List products10 = session.createQuery("FROM ProductCategory").list();
//	      for (Iterator iterator = products10.iterator(); iterator.hasNext();){
//	         ProductCategory productCategory = (ProductCategory) iterator.next(); 
//	         System.out.println("KategorieId: " + productCategory.getCategoryId()); 
//	         System.out.println("Produktnummer: " + productCategory.getProductNumber()); 
//	         break;
//	      }
	        
//		List products11 = session.createQuery("FROM SimilarProducts").list();
//	      for (Iterator iterator = products11.iterator(); iterator.hasNext();){
//	         SimilarProducts similarProducts = (SimilarProducts) iterator.next(); 
//	         System.out.println("Produktnummer1: " + similarProducts.getProductNumber1()); 
//	         System.out.println("Produktnummer2: " + similarProducts.getProductNumber2()); 
//	         break;
//	      }
		
//		List products12 = session.createQuery("FROM Branch").list();
//	      for (Iterator iterator = products12.iterator(); iterator.hasNext();){
//	         Branch branch = (Branch) iterator.next(); 
//	         System.out.println("FilialName: " + branch.getBranchName()); 
//	         System.out.println("Ort: " + branch.getLocation());
//	         System.out.println("PLZ: " + branch.getPlz());
//	         System.out.println("Straße: " + branch.getStreet());
//	         break;
//	      }
		
//		List products13 = session.createQuery("FROM Offer").list();
//	      for (Iterator iterator = products13.iterator(); iterator.hasNext();){
//	         Offer offer = (Offer) iterator.next(); 
//	         System.out.println("FilialName: " + offer.getBranchName()); 
//	         System.out.println("Produktnummer: " + offer.getProductNumber());
//	         System.out.println("Zustand: " + offer.getCondition());
//	         System.out.println("Verfügbar: " + offer.getAvailability());
//	         System.out.println("Preis: " + offer.getPrice());
//	         break;
//	      }
		
//		List products14 = session.createQuery("FROM Customer").list();
//	      for (Iterator iterator = products14.iterator(); iterator.hasNext();){
//	         Customer customer = (Customer) iterator.next(); 
//	         System.out.println("FilialName: " + customer.getUsername()); 
//	         System.out.println("Ort: " + customer.getLocation());
//	         System.out.println("PLZ: " + customer.getPlz());
//	         System.out.println("Straße: " + customer.getStreet());
//	         System.out.println("Kontonummer: " + customer.getAccountNumber());
//	         break;
//	      }
		
//		List products15 = session.createQuery("FROM Purchase").list();
//	      for (Iterator iterator = products15.iterator(); iterator.hasNext();){
//	         Purchase purchase = (Purchase) iterator.next(); 
//	         System.out.println("Nutzername: " + purchase.getUsername()); 
//	         System.out.println("Filialname: " + purchase.getBranchName());
//	         System.out.println("Produktnummer: " + purchase.getProductNumber());
//	         System.out.println("Zustand: " + purchase.getCondition());
//	         System.out.println("timestamp: " + purchase.getTimeStamp());
//	         System.out.println("Anzahl: " + purchase.getNumber());
//	         break;
//	      }
		
		List products16 = session.createQuery("FROM Review").list();
	      for (Iterator iterator = products16.iterator(); iterator.hasNext();){
	         Review review = (Review) iterator.next(); 
	         System.out.println("Nutzername: " + review.getUsername()); 
	         System.out.println("Produktnummer: " + review.getProductNumber());
	         System.out.println("Sterne: " + review.getStars());
	         System.out.println("Zusammenfassung: " + review.getSummary());
	         System.out.println("Rezensionstext: " + review.getReviewText());
	         System.out.println("Rezensionsdatum: " + review.getReviewDate());
	         break;
	      }
	      
        tx.commit();
        
        session.close();	
		
	}



}
