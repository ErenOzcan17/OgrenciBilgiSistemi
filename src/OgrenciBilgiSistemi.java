import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class OgrenciBilgiSistemi {
    static class DersTanimla {
        Map<Integer, Ders> dersler;

        public DersTanimla() {
            this.dersler = new HashMap<>();
            // Dersleri başlangıçta tanımla
            dersEkle(new DersSene1(101, "Kalkülüs1"));
            dersEkle(new DersSene1(102, "Kalkülüs2"));
            dersEkle(new DersSene1(103, "Fizik1"));
            dersEkle(new DersSene1(104, "Fizik2"));
            dersEkle(new DersSene1(105, "Algoritma1"));
            dersEkle(new DersSene1(106, "Algoritma2"));

            dersEkle(new DersSene2(201, "Nesne"));
            dersEkle(new DersSene2(202, "Gereksinim"));
            dersEkle(new DersSene2(203, "WEB"));
            dersEkle(new DersSene2(204, "İş Sağlığı"));

            dersEkle(new OrtakDersler(11, "Turk Dili"));
            dersEkle(new OrtakDersler(12, "Yabancı dil"));
            dersEkle(new OrtakDersler(13, "İnkilap"));

            dersEkle(new secmeliDersler(21, "Python Programlama"));
            dersEkle(new secmeliDersler(22, "Servis Odaklı Mimari"));
            dersEkle(new secmeliDersler(23, "İleri Web Programlama"));
        }

        public void dersEkle(Ders ders) {
            dersler.put(ders.ders_kodu, ders);
        }

        public void dersListesiniGoruntule(int sene) {
            System.out.println("Alabileceğiniz Dersler:");
            for (Ders ders : dersler.values()) {
                if (ders.getSene() == sene) {
                    System.out.println(ders.ders_kodu + ": " + ders.ders_ismi + " - Kayıtlı öğrenciler: " + ders.kayit_sayisi);
                }
            }
        }
        public void dersListesiniGoruntule() {
            System.out.println("Ortak Dersler:");
            for (Ders ders : dersler.values()) {
                int sene = 0;
                if (ders.getSene() == sene) {
                    System.out.println(ders.ders_kodu + ": " + ders.ders_ismi + " - Kayıtlı öğrenciler: " + ders.kayit_sayisi);
                }
            }
        }
        public void dersListesiniGoruntule(double y){
            System.out.println("Seçmeli Dersler:");
            for (Ders ders : dersler.values()) {
                if (ders.getSene() == y) {
                    System.out.println(ders.ders_kodu + ": " + ders.ders_ismi + " - Kayıtlı öğrenciler: " + ders.kayit_sayisi);
                }
            }
        }
    }

    static class OgrenciTanimla {
        Map<Integer, Ogrenci> ogrenciler;

        public OgrenciTanimla() {
            this.ogrenciler = new HashMap<>();
            // Öğrencileri başlangıçta tanımla
            ogrenciEkle(new OgrenciSene1(1, "Eren", "Özcan", "Bilgisayar"));
            ogrenciEkle(new OgrenciSene1(3, "Onur", "Malay", "Makine Mühendisliği"));
            ogrenciEkle(new OgrenciSene1(5, "Tuğba", "Çelikten", "Kimya Mühendisliği"));

            ogrenciEkle(new OgrenciSene2(2, "Yiğit", "Geldi", "Elektrik Mühendisliği"));
            ogrenciEkle(new OgrenciSene2(4, "Osman", "Güzel", "Endüstri Mühendisliği"));
        }

        //bu metod ogreniyi hashmapin oğrenciler kısmına kaydediyor
        public void ogrenciEkle(Ogrenci ogrenci) {
            ogrenciler.put(ogrenci.ogrenci_numarasi, ogrenci);
        }
    }

    static class DersOgrenciEslestirme {
        Map<Integer, Ogrenci> ogrenciler;
        DersTanimla dersTanimla;

        public DersOgrenciEslestirme(Map<Integer, Ogrenci> ogrenciler, DersTanimla dersTanimla) {
            this.ogrenciler = ogrenciler;
            this.dersTanimla = dersTanimla;
        }

        public void ogrencininDersiAldigiMetod(int ogrenci_numarasi, int ders_kodu) {
            if (ogrenciler.containsKey(ogrenci_numarasi) && dersTanimla.dersler.containsKey(ders_kodu)) {
                Ogrenci ogrenci = ogrenciler.get(ogrenci_numarasi);
                Ders ders = dersTanimla.dersler.get(ders_kodu);

                dersTanimla.dersEkle(ders);
                ders.kayit_sayisi++;

                System.out.println(ogrenci.isim + " başarı ile " + ders.ders_ismi + "'e kaydolmuştur");
            } else {
                System.out.println("Geçersiz ders kodu");
            }
        }
    }

    static class DigerMainMetodislemleri {

        public static void istatistikleriGoruntule(DersTanimla dersTanimla) {
            System.out.println("Ders kayıt durumu");
            for (Ders ders : dersTanimla.dersler.values()) {
                System.out.println(ders.ders_ismi + " - Kayıtlı öğrenci sayısı " + ders.kayit_sayisi);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DersTanimla dersTanimla = new DersTanimla();
        OgrenciTanimla ogrenciTanimla = new OgrenciTanimla();
        DersOgrenciEslestirme dersOgrenciEslestirme = new DersOgrenciEslestirme(ogrenciTanimla.ogrenciler, dersTanimla);

        // giriş ekranındaki menü döngüsü
        while (true){
            System.out.println("Ortak Dersleri Görüntülemek için 1'i ");
            System.out.println("Seçmeli Dersleri Görüntülemek için 2'yi ");
            System.out.println("Sisteme giriş yapmak için 0 giriniz:");
            int x = scanner.nextInt();
            switch (x) {
                case 1:
                    dersTanimla.dersListesiniGoruntule();
                    break;
                case 2:
                    double y = 3;
                    dersTanimla.dersListesiniGoruntule(y);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("lütfen geçerli bir seçeneği seçin");
                    break;
            }
            if (x == 0) {
                break;
            }
        }

        //öğrenci girişinden sonra gerçekleştirilen işlemlerin döngüsü
        while (true) {
            //Exception 1
            try {
                System.out.print("Öğrenci numarası giriniz(Çıkış Yapmak İçin 0 Girin): ");
                int ogrenci_numarasi = scanner.nextInt();
                if (ogrenci_numarasi == 0) {
                    break;
                }

                // Boş satırı oku
                scanner.nextLine();

                // Öğrenci bilgilerini çekme
                if (ogrenciTanimla.ogrenciler.containsKey(ogrenci_numarasi)) {
                    Ogrenci ogrenci = ogrenciTanimla.ogrenciler.get(ogrenci_numarasi);

                    // Şifre kontrolü
                    System.out.println("Şifrenizi giriniz: ");
                    String girilenSifre = scanner.nextLine();

                    if (girilenSifre.length() < 5) {
                        //Exception 2
                        throw new SifreUzunlukException("Şifre en az 5 karakter uzunluğunda olmalıdır.");
                    }
                    else {
                        if (girilenSifre.equals(ogrenci.Sifre)) {
                            System.out.println("Giriş başarılı. Alabileceğiniz dersler aşağıda listelenmiştir");

                            // öğrencinin alabileceği ders listesini göster
                            dersTanimla.dersListesiniGoruntule(ogrenciTanimla.ogrenciler.get(ogrenci_numarasi).getSene());

                            // ders seçimi
                            System.out.print("Kayıt olmak istediğiniz dersin kodunu giriniz (çıkış için 0 girin): ");
                            int ders_kodu = scanner.nextInt();

                            if (ders_kodu == 0) {
                                break;
                            }

                            // Öğrenciyi ders ile eşleştirme
                            dersOgrenciEslestirme.ogrencininDersiAldigiMetod(ogrenci_numarasi, ders_kodu);
                        }
                        else {
                            System.out.println("Yanlış şifre");
                        }
                    }
                }
                else {
                    System.out.println("Geçersiz öğrenci numarası");
                }
            } catch (SifreUzunlukException e) {
                System.out.println("Hata: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.out.println("Geçersiz giriş. Lütfen bir tam sayı girin.");
                scanner.nextLine();
            }
        }

        // hangi dersi kaç öğrencinin aldıüını göster
        DigerMainMetodislemleri.istatistikleriGoruntule(dersTanimla);
    }
}
