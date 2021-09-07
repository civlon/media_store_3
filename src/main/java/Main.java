package main.java;

import java.util.*;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class Main {

	public static void main(String[] args) {
		// Verbindung zur Datenbank aufbauen
		Session session =  new
				Configuration().buildSessionFactory().openSession();
		//String SELECT = "SELECT * FROM Produkt P LIMIT 20";
		System.out.println(session.isConnected());
	}



}
