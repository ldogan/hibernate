package h03_OneToOne;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class H4_Fetch {

	public static void main(String[] args) {
		
		///////////////////////////////////////////////////////////////////////////////////////	
		
		Configuration con = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(H1_Kisi.class)
				.addAnnotatedClass(H2_Gunluk.class);

		SessionFactory sf = con.buildSessionFactory();
		Session session = sf.openSession();

		Transaction tx = session.beginTransaction();
		
		
		////////////////////////////////////////////////////////////////////////////////////////////	
		
		// 1) id=101 olan kisi bilgilerini getir
		
		System.out.println(session.get(H1_Kisi.class, 101));
		
		// id = 12 olan gunlugun bilgilerini getir
		
		System.out.println(session.get(H2_Gunluk.class, 12));
		
		
		// Gunluk classinda ki butun verileri getir
		for (int i = 12; i < 15; i++) {
			System.out.println(session.get("tercih edilmeyen : "+H2_Gunluk.class, i));
		}
		
		// iki tablodaki verilerin hepsi 
		
	@SuppressWarnings("unchecked")
	List<Object[]> liste= 	session.createQuery("from H1_Kisi k join H2_Gunluk g ON k.kisi=g.kisi").getResultList();
		liste.forEach(((x)->System.out.println(Arrays.toString(x))));
		
		//////////////////////////////////////////////////////////////////////////////////////////////////
	
		
		//3) Kisiler ve Gunlukler tablolarindaki ortak olan (one to one ile birebir bağladığımız) kayıtların,
		// Kisi adi, gunluk yazisi(yazilar) ve kisi yası (kisiYas) bilgilerini sorgulayiniz.
		
		String sorgu=" SELECT k.kisi_ad , g.owner, k.kisiYas FROM kisiler AS k INNER JOIN gunlukler AS g ON k.kisi_id=g.kisi_no";
		
		@SuppressWarnings("unchecked")
		List<Object[]> sonuc= session.createSQLQuery(sorgu).getResultList();
		for (Object[] each : sonuc) {
			System.out.println(Arrays.toString(each));
		}
		
		
		tx.commit();
		sf.close();
		session.close();
	}

}
