package jdbcExamples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Day01_JdbcQuery01 {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		
		// 1) ilgili driver'i yuklemeliyiz prizi fise takmak gibi ne calisacagini bilsin
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		// 2) baglanti olusturmaliyiz uydu sifrelerini girmeliyiz
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "dota483361483");
		
		// 3) SQL komutlari icin bir STATEMENT nesnesi olustur
		Statement st = con.createStatement();
		
		// 4) SQL ifadeleri yazabilir ve calistirabiliriz
				
		ResultSet veri = st.executeQuery("select isim, maas from personel where id=123456789");
		
		// 5) sonuclari aldik ve isledik
			
		while (veri.next()) {
			System.out.println(veri.getString("isim") + " "+ veri.getInt("maas"));
			System.out.println("Personel Adi:" + veri.getString(1) + ", Maas:"+ veri.getInt(2));
		}
				
		// 6) olusturulan nesneleri bellekten kaldiralim
		con.close();
		st.close();
		veri.close();
		
		
	}

}
