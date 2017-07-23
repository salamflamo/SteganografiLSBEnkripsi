package steganoenkrip;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author salamflamo
 */
public class ProsesGambar {
    private final String path;
    public ProsesGambar(String path){
        this.path = path;
    }

    public ProsesGambar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public BufferedImage fetImage() throws Exception{
        File f = new File(path);
        BufferedImage gbr = ImageIO.read(f);
        return gbr;
    }
    
    public void sisipText(BufferedImage gbr, byte[] txt) throws Exception{
        int i = 0;
        int j = 0;
        
        for (byte b : txt) {
            for (int k = 7; k >= 0; k--) {
                Color c = new Color(gbr.getRGB(j, i));
                byte biru = (byte)c.getBlue();
                int merah = c.getRed();
                int hijau = c.getGreen();
                int nilaiBit = (b >>> k) & 1;
                biru = (byte)((biru & 0xFE) | nilaiBit);
                Color newC = new Color(merah,hijau,(biru & 0xFE));
                gbr.setRGB(j, i, newC.getRGB());
                j++;
            }
            i++;
        }
        gambarTersisip(gbr);
        System.out.println("Text Hidden");
    }
    
    public void gambarTersisip(BufferedImage gbr){
        try {
            File hasil = new File("Hasil.png");
            ImageIO.write(gbr, "png", hasil);
        } catch (Exception e) {
        }
    }
    
    public BufferedImage stegGambar(){
        File f = new File("Hasil.png");
        BufferedImage gbr = null;
        try {
            gbr = ImageIO.read(f);
        } catch (Exception e) {
        }
        return gbr;
    }
    
    public String bacaPesan(int panjangPesan, int offset){
        BufferedImage gbr = stegGambar();
        byte [] BytePesan = extractPesanTersembunyi(gbr, panjangPesan, offset);
        if (BytePesan == null) {
            return null;
        }
        String pesan = new String(BytePesan);
        return (pesan);
    }
    
    public byte[] extractPesanTersembunyi(BufferedImage gbr, int ukuran, int offset){
        int i = 0;
        int j = 0;
        byte[] pesanTersembunyi = new byte[ukuran];
        
        for (int l = 0; l < ukuran; l++) {
            for (int k = 0; k < 8; k++) {
                Color c = new Color(gbr.getRGB(j, i));
                byte biru = (byte)c.getBlue();
                int merah = c.getRed();
                int hijau = c.getGreen();
                pesanTersembunyi[l] = (byte)((pesanTersembunyi[l] << 1) | (biru&1));
                j++;
            }
            i++;
        }
        return pesanTersembunyi;
    }

    
}
