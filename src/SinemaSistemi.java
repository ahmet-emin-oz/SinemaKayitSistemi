import java.util.Scanner;

public class SinemaSistemi {
    // Sabit limitler
    static final int MAX_FILM = 10;
    static final int MAX_MUSTERI = 20;

    // Film Verileri
    static String[] filmAdlari = new String[MAX_FILM];
    static int[] filmSureleri = new int[MAX_FILM];
    static String[] filmTurleri = new String[MAX_FILM];
    static int filmSayisi = 0;

    // Müşteri Verileri
    static String[] musteriAdlari = new String[MAX_MUSTERI];
    static String[] musteriEmailleri = new String[MAX_MUSTERI];
    static int musteriSayisi = 0;

    // Bilet Verileri (Müşteri İndeksi | Film İndeksi)
    static int[][] biletler = new int[MAX_MUSTERI][2];
    static int biletSayisi = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int secim;

        do {
            System.out.println("\n--- SİNEMA KAYIT SİSTEMİ ---");
            System.out.println("1. Film Tanımla");
            System.out.println("2. Müşteri Kaydı Oluştur");
            System.out.println("3. Bilet Satın Al (Eşleştirme)");
            System.out.println("4. Biletleri Listele");
            System.out.println("0. Çıkış");
            System.out.print("Seçiminiz: ");
            secim = scanner.nextInt();
            scanner.nextLine(); // Boş satırı temizle

            switch (secim) {
                case 1:
                    filmTanimla(scanner);
                    break;
                case 2:
                    musteriKaydet(scanner);
                    break;
                case 3:
                    biletAl(scanner);
                    break;
                case 4:
                    biletleriListele();
                    break;
                case 0:
                    System.out.println("Sistemden çıkılıyor...");
                    break;
                default:
                    System.out.println("Geçersiz seçim!");
            }
        } while (secim != 0);
    }

    // 1. Film Tanımlama
    public static void filmTanimla(Scanner sc) {
        if (filmSayisi < MAX_FILM) {
            System.out.print("Film Adı: ");
            filmAdlari[filmSayisi] = sc.nextLine();
            System.out.print("Süresi (dk): ");
            filmSureleri[filmSayisi] = sc.nextInt();
            sc.nextLine();
            System.out.print("Türü: ");
            filmTurleri[filmSayisi] = sc.nextLine();

            filmSayisi++;
            System.out.println("Film başarıyla eklendi.");
        } else {
            System.out.println("Hata: Maksimum film kapasitesine ulaşıldı!");
        }
    }

    // 2. Müşteri Kaydı
    public static void musteriKaydet(Scanner sc) {
        if (musteriSayisi < MAX_MUSTERI) {
            System.out.print("Müşteri Adı: ");
            musteriAdlari[musteriSayisi] = sc.nextLine();
            System.out.print("E-mail: ");
            musteriEmailleri[musteriSayisi] = sc.nextLine();

            musteriSayisi++;
            System.out.println("Müşteri kaydı tamamlandı.");
        } else {
            System.out.println("Hata: Maksimum müşteri kapasitesine ulaşıldı!");
        }
    }

    // 3. Bilet Kaydı (Eşleştirme)
    public static void biletAl(Scanner sc) {
        if (musteriSayisi == 0 || filmSayisi == 0) {
            System.out.println("Önce film ve müşteri kaydı yapmalısınız!");
            return;
        }

        // Müşterileri Listele
        System.out.println("\n--- Müşteri Seçin ---");
        for (int i = 0; i < musteriSayisi; i++) {
            System.out.println(i + ". " + musteriAdlari[i]);
        }
        int mIndex = sc.nextInt();

        // Filmleri Listele
        System.out.println("\n--- Film Seçin ---");
        for (int i = 0; i < filmSayisi; i++) {
            System.out.println(i + ". " + filmAdlari[i]);
        }
        int fIndex = sc.nextInt();

        // Eşleştirme yap ve biletler dizisine kaydet
        biletler[biletSayisi][0] = mIndex;
        biletler[biletSayisi][1] = fIndex;
        biletSayisi++;

        System.out.println("Bilet işlemi başarılı!");
    }

    // Bilet Listeleme
    public static void biletleriListele() {
        System.out.println("\n--- GÜNCEL BİLET LİSTESİ ---");
        if (biletSayisi == 0) System.out.println("Henüz bilet satışı yapılmadı.");

        for (int i = 0; i < biletSayisi; i++) {
            int mIdx = biletler[i][0];
            int fIdx = biletler[i][1];

            System.out.println("Bilet No: " + (i+1));
            System.out.println("Müşteri: " + musteriAdlari[mIdx] + " (" + musteriEmailleri[mIdx] + ")");
            System.out.println("Film: " + filmAdlari[fIdx] + " | Tür: " + filmTurleri[fIdx]);
            System.out.println("--------------------------");
        }
    }
}