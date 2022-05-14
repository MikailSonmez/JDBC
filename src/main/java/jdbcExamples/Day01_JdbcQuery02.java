package jdbcExamples;


import java.sql.*;

public class Day01_JdbcQuery02 {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "dota483361483");
		
		Statement st = con.createStatement();
		
		/*=======================================================================
		 ORNEK1: Bolumler tablosundaki tum kayitlari listeleyen bir sorgu yaziniz.
		========================================================================*/ 
		
		ResultSet tablo= st.executeQuery("select * from bolumler");
		
		while (tablo.next()) {
			System.out.println("bolum_id: "+tablo.getInt(1) + ",	bolum_isim: " + tablo.getString(2) + ", 	konum: " + tablo.getString(3));
			System.out.println("bolum_id: "+tablo.getInt(1) + ",	bolum_isim: " + tablo.getString("bolum_isim") + ", 	konum: " + tablo.getString("konum"));
			System.out.println();
			
		}
		System.out.println("========================================================================");
		/*=======================================================================
		 ORNEK2: SATIS ve MUHASEBE bolumlerinde calisan personelin isimlerini ve 
		 maaslarini, maas ters sirali olarak listeleyiniz
		========================================================================*/ 
		
	//	String sorgu = "SELECT isim, maas FROM personel WHERE bolum_id in(10,30) order by maas desc";
	//	st.executeQuery(sorgu);
		
		ResultSet tablo2 = st.executeQuery("SELECT isim, maas"
				+ " FROM personel"
				+ " WHERE bolum_id in(10,30)"
				+ " ORDER BY maas DESC");
		
		while (tablo2.next()) {
			System.out.println("Isim: "+tablo2.getString(1) + ",\tmaas: "+tablo2.getInt(2));
		}
		
		System.out.println("========================================================================");
		/*=======================================================================
		  ORNEK3: Tüm bolumlerde calisan personelin isimlerini, bolum isimlerini 
		  ve maaslarini, bolum ve maas sirali listeleyiniz. NOT: calisani olmasa 
		  bile bolum ismi gosterilmelidir.
		========================================================================*/ 
		String sorgu3= "Select b.bolum_isim, p.isim, p.maas "
				+ "from bolumler b left join personel "
				+ "p on p.bolum_id=b.bolum_id "
				+ "order by b.bolum_isim, p.maas ";
		ResultSet tablo3 = st.executeQuery(sorgu3);
		
		while (tablo3.next()) {
			System.out.println("Bolum: "+tablo3.getString(1) +
					"\t\tCalisan isim: "+tablo3.getString(2) +
					"\t\tmaas: "+ tablo3.getInt(3));
		}
		
		System.out.println("========================================================================");
		/*=======================================================================
		  ORNEK4: Maasi en yuksek 10 kisinin bolumunu,adini ve maasini listeyiniz
		========================================================================*/
		String sorgu4= "Select b.bolum_isim, p.isim, p.maas"
				+ " from personel p left join bolumler b"
				+ " on p.bolum_id=b.bolum_id"
				+ " order by p.maas desc limit 10";
		ResultSet tablo4 = st.executeQuery(sorgu4);
		
		while (tablo4.next()) {
			System.out.println("Bolum: "+tablo4.getString(1) +
					"\t\tCalisan isim: "+tablo4.getString(2) +
					"\t\tmaas: "+ tablo4.getInt(3));
		}
		
		con.close();
		st.close();
		tablo.close();
		tablo2.close();
		tablo3.close();
		tablo4.close();
		
		
	}

}
