package h01_anotasyonlar;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class H3_Fetch {

	public static void main(String[] args) {

		SessionFactory sf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(H1_Sehir.class)
				.buildSessionFactory();

		Session session = sf.openSession();
		Transaction tx = session.beginTransaction();

		// Sehir tablosundan istenilen id ye ait bilgileri getir
		// ======================================================

		System.out.println(session.get(H1_Sehir.class, 07));
		session.get(H1_Sehir.class, 37).setSehirPlaka(34);
		System.out.println(session.get(H1_Sehir.class, 34).getSehirNufus()); // plakasi 35 olanin nufusunu getir.
		
		tx.commit();
		sf.close();
		session.close();
	}

}
