package main.java;

import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import tables.Product;


public class Main {

	public static void main(String[] args) {
//		// Verbindung zur Datenbank aufbauen
//		Session session =  new
//				Configuration().buildSessionFactory().openSession();
//		//String SELECT = "SELECT * FROM Produkt P LIMIT 20";
//		System.out.println(session.isConnected());
		
		
		SessionFactory factory = new Configuration().configure().buildSessionFactory();
		
		Session session = factory.openSession();
		
		Transaction tx = session.beginTransaction();
		List products = session.createQuery("FROM \"Produkt\"").list();
        for (Iterator iterator = products.iterator(); iterator.hasNext();){
           Product product = (Product) iterator.next(); 
           System.out.print("Produktnummer: " + product.getProductNumber()); 
           System.out.print("Titel: " + product.getTitle()); 
           System.out.println("Produktgruppe: " + product.getProductGroup());
           break;
        }
        tx.commit();
        
        session.close();	
		
	}



}
