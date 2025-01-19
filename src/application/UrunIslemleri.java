package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UrunIslemleri {
	public List<Node> tukenenler=new ArrayList<>();
    private static UrunIslemleri instance = null;
    private Node bas; 
    private  String TUKENENLER_DOSYA_ADI = "tukenenler.txt";

    private final String DOSYA_ADI = "urunler.txt";

    private static class Node {
        String urunAdi;
        String kategori;
        int miktar;
        Node next;

        Node(String urunAdi, String kategori, int miktar) {
            this.urunAdi = urunAdi;
            this.kategori = kategori;
            this.miktar = miktar;
            this.next = null;
        }
    }

    private UrunIslemleri() {
        bas = null;
        dosyadanOku();
       
        
    }

    public static UrunIslemleri getInstance() {
        if (instance == null) {
            instance = new UrunIslemleri();
        }
        return instance;
    }

    public void urunEkle(String urunAdi, String kategori, int miktar) {
        Node yeniNode = new Node(urunAdi, kategori, miktar);
        tukenenUrunKaldir(urunAdi);

        if (bas == null) {
            bas = yeniNode;
            System.out.println("Depoya ilk urun eklendi: " + urunAdi + ", Kategori: " + kategori + ", Miktar: " + miktar);
            dosyayaYaz();
            return;
        }

        Node mevcut = bas;
        while (mevcut.next != null) {
            if (mevcut.urunAdi.equals(urunAdi) && mevcut.kategori.equals(kategori)) {
                mevcut.miktar += miktar;
                System.out.println("Urun mevcut, yeni stok: " + mevcut.miktar);
                dosyayaYaz();
                return;
            } else if (mevcut.urunAdi.equals(urunAdi) && !mevcut.kategori.equals(kategori)) {
                System.out.println("Urun " + mevcut.kategori + " kategorisinde mevcut. Kategoriyi dogru girin.");
                return;
            }
            mevcut = mevcut.next;
        }

        if (mevcut.urunAdi.equals(urunAdi) && mevcut.kategori.equals(kategori)) {
            mevcut.miktar += miktar;
            System.out.println("Urun mevcut, yeni stok: " + mevcut.miktar);
        } else if (mevcut.urunAdi.equals(urunAdi) && !mevcut.kategori.equals(kategori)) {
            System.out.println("Urun " + mevcut.kategori + " kategorisinde mevcut. Kategoriyi dogru girin.");
        } else {
            mevcut.next = yeniNode;
            System.out.println("Yeni urun eklendi: " + urunAdi + ", Kategori: " + kategori + ", Miktar: " + miktar);
        }
        dosyayaYaz();
    }


    public void urunEksilt(String urunAdi, int miktar) {
        Node mevcut = bas;
        Node onceki = null;
        
        while (mevcut != null) {
            if (mevcut.urunAdi.equals(urunAdi)) {
                if (miktar > mevcut.miktar) {
                    System.out.println("Yeterli stok yok. Mevcut stok: " + mevcut.miktar);
                    return;
                } else if (miktar == mevcut.miktar) {
                	tukenenUrunEkle(mevcut);
                	
                    System.out.println(urunAdi + " urunu tamamen tuketildi.");
                    if (onceki == null) {
                        bas = mevcut.next;
                    } else {
                        onceki.next = mevcut.next;
                    }
                    dosyayaYaz();
                    return;
                } else {
                    mevcut.miktar -= miktar;
                    System.out.println(urunAdi + " urununden " + miktar + " adet eksiltildi. Yeni stok: " + mevcut.miktar);
                    dosyayaYaz();
                    return;
                }
            }
            onceki = mevcut;
            mevcut = mevcut.next;
        }
        

        System.out.println(urunAdi + " urunu depoda bulunmamaktadir.");
    }

    public void urunGuncelle(String urunAdi, int yeniMiktar) {
        Node mevcut = bas;
        Node onceki =null;

        while (mevcut != null) {
            if (mevcut.urunAdi.equals(urunAdi)) {
            	if (yeniMiktar==0) {
            		tukenenUrunEkle(mevcut);
            	
            		System.out.println(urunAdi+ " tamamen tuketildi");
            		if (onceki==null) {
            			bas=mevcut.next;
					}else {
						onceki.next=mevcut.next;
					}
            		dosyayaYaz();
            		return;
            		
					
				}else {
					
				
                mevcut.miktar = yeniMiktar;
                System.out.println(urunAdi + " urununun yeni stogu: " + yeniMiktar);
                dosyayaYaz();
                return;
                
				}
            }
            onceki=mevcut;
            mevcut = mevcut.next;
        }

        System.out.println(urunAdi + " urunu depoda bulunmamaktadir.");
    }
    public void urunkategoriGuncelle(String urunAdi, String yenikategori) {
        Node mevcut = bas;

        while (mevcut != null) {
            if (mevcut.urunAdi.equals(urunAdi)) {
                mevcut.kategori = yenikategori;
                System.out.println(urunAdi + " urununun yeni kategorisi: " + yenikategori);
                dosyayaYaz();
                return;
            }
            mevcut = mevcut.next;
        }

        System.out.println(urunAdi + " urunu depoda bulunmamaktadir.");
    }
    public void urunSorgula(String urunAdi) {
        Node mevcut = bas;

        while (mevcut != null) {
            if (mevcut.urunAdi.equals(urunAdi)) {
                System.out.println(urunAdi + " depoda mevcut. Kategori: " + mevcut.kategori + ", Stok: " + mevcut.miktar);
                return;
            }
            mevcut = mevcut.next;
        }

        System.out.println(urunAdi + " urunu depoda bulunmamaktadir.");
    }

    public void urunSirala() {
        if (bas == null) {
            System.out.println("Depo bos, siralanacak urun yok.");
            return;
        }

        List<Node> nodeList = new ArrayList<>();
        Node mevcut = bas;
        while (mevcut != null) {
            nodeList.add(mevcut);
            mevcut = mevcut.next;
        }

        nodeList.sort((a, b) -> Integer.compare(a.miktar, b.miktar));

        System.out.println("Siralanmis urun listesi:");
        for (Node node : nodeList) {
            System.out.println("Urun: " + node.urunAdi + ", Kategori: " + node.kategori + ", Stok: " + node.miktar);
        }
    }
    public void urunkatogeriSirala() {
        if (bas == null) {
            System.out.println("Depo bos, siralanacak urun yok.");
            return;
        }

        List<Node> nodeList = new ArrayList<>();
        Node mevcut = bas;
        while (mevcut != null) {
            nodeList.add(mevcut);
            mevcut = mevcut.next;
        }
        

        nodeList.sort((a,b)->a.kategori.compareTo(b.kategori));

        System.out.println("Siralanmis urun listesi:");
        for (Node node : nodeList) {
            System.out.println("Urun: " + node.urunAdi + ", Kategori: " + node.kategori + ", Stok: " + node.miktar);
        }
    }
    public void kategorigoster(String tur) {
        if (bas == null) {
            System.out.println("Depo bos.");
            return;
        }

        System.out.println(tur + " kategorisindeki urunler:");

        Node mevcut = bas;
        boolean kategoriVar = false; 

        while (mevcut != null) {
            if (mevcut.kategori.equals(tur)) {
                System.out.println("Urun: " + mevcut.urunAdi + ", Kategori: " + mevcut.kategori + ", Miktar: " + mevcut.miktar);
                kategoriVar = true; 
            }
            mevcut = mevcut.next;
        }

        if (!kategoriVar) {
            System.out.println("Boyle bir kategori yok.");
        }

        System.out.println("------------------------------------------------");
    }

    public void urunleriYazdir() {
        if (bas == null) {
            System.out.println("Depo bos.");
            return;
        }
        Node mevcut = bas;
        while (mevcut != null) {
            System.out.println("Urun: " + mevcut.urunAdi + ", Kategori: " + mevcut.kategori + ", Miktar: " + mevcut.miktar);
            mevcut = mevcut.next;
        }
    }
    private void dosyayaYaz() {
        try (
        	BufferedWriter writer = new BufferedWriter(new FileWriter(DOSYA_ADI))) {
            Node mevcut = bas;
            while (mevcut != null) {
                writer.write(mevcut.urunAdi + "," + mevcut.kategori + "," + mevcut.miktar);
                writer.newLine();
                mevcut = mevcut.next;
            }
            
        } catch (IOException e) {
            System.err.println("Dosyaya yazma sırasında bir hata oluştu: " + e.getMessage());
        }
    }
    private void dosyadanOku() {
        File dosya = new File(DOSYA_ADI);
        if (!dosya.exists()) {
            System.out.println("Urun dosyası bulunamadı. Yeni bir dosya oluşturulacak.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(dosya))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
                String[] parcalar = satir.split(",");
                if (parcalar.length == 3) {
                    String urunAdi = parcalar[0];
                    String kategori = parcalar[1];
                    int miktar = Integer.parseInt(parcalar[2]);
                    Node yeniNode = new Node(urunAdi, kategori, miktar);
                    if (bas == null) {
                        bas = yeniNode;
                    } else {
                        Node mevcut = bas;
                        while (mevcut.next != null) {
                            mevcut = mevcut.next;
                        }
                        mevcut.next = yeniNode;
                    }
                }
            }
           
        } catch (IOException e) {
            System.err.println("Dosyadan okuma sırasında bir hata oluştu: " + e.getMessage());
        }
    }
    private void tukenenUrunEkle(Node urun) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TUKENENLER_DOSYA_ADI, true))) {
            writer.write(urun.urunAdi + "," + urun.kategori );
            writer.newLine();
            
        } catch (IOException e) {
            System.err.println("Tukenen urunu dosyaya yazarken bir hata oluştu: " + e.getMessage());
        }
    }
    public void tukenenUrunleriOku() {
        File dosya = new File(TUKENENLER_DOSYA_ADI);
        if (!dosya.exists()) {
            System.out.println("Tukenen urunler dosyası bulunamadı.");
            return;
        }
        else if (dosya.length()==0) {
        	System.out.println("tukenen urun yok");
			return;
		}

        try (BufferedReader reader = new BufferedReader(new FileReader(dosya))) {
            String satir;
            System.out.println("Tukenen urunler listesi:");
            while ((satir = reader.readLine()) != null) {
            	String[]parca=satir.split(",");
                System.out.println("urun: "+parca[0]+" kategorisi: "+parca[1]+" ");
            }
        } catch (IOException e) {
            System.err.println("Tukenen urunleri okurken bir hata oluştu: " + e.getMessage());
        }
    }
    private void tukenenUrunKaldir(String urunAdi) {
        File dosya = new File(TUKENENLER_DOSYA_ADI);
        if (!dosya.exists()) {
            
            return;
        }

        List<String> kalanUrunler = new ArrayList<>();


        try (BufferedReader reader = new BufferedReader(new FileReader(dosya))) {
            String satir;
            while ((satir = reader.readLine()) != null) {
                String[] parcalar = satir.split(",");
                if (parcalar.length >= 1 && !parcalar[0].equals(urunAdi)) {
                    kalanUrunler.add(parcalar[0]+","+parcalar[1]);
                }
            }
        } catch (IOException e) {
            System.err.println("Tukenen urunleri okurken bir hata oluştu: " + e.getMessage());
            return;
        }

        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dosya))) {
            for (String urun : kalanUrunler) {
                writer.write(urun);
                writer.newLine();
            }
            
        } catch (IOException e) {
            System.err.println("Tukenen urunler dosyasını güncellerken bir hata oluştu: " + e.getMessage());
        }
    }




}
