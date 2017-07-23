package steganoenkrip;


import java.math.BigInteger;
import java.nio.charset.Charset;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author salamflamo
 */
public class Konvert {
    
    /**
     *
     * @param bin
     */
    
    
    public void konToString (StringBuilder bin){
        try {
            BigInteger nilai = new BigInteger(""+bin, 2);
            byte[] bytegambar = nilai.toByteArray();
            String n = new String(bytegambar);
        } catch (Exception e) {
            System.out.println("Error : "+e);
        }
    }
    
    public void konToBin(String text){
        byte[] arr;
        arr = text.getBytes(Charset.forName("UTF-8"));
        StringBuilder bin = new StringBuilder();
        for (byte b : arr) {
            int nilai = b;
            for (int i = 0; i < 8; i++) {
                bin.append((nilai & 128) == 0 ? 0 : 1);
                nilai <<= 1;
            }
        }
        konToString(bin);
    }
    
    public byte[] textToByte(String text){
        byte[] arr =text.getBytes(Charset.forName("UTF-8"));
        return arr;
    }
    
    public void konToBin(byte[] barr){
        StringBuilder bin = new StringBuilder();
        for(byte b : barr){
            int nilai = b;
            for (int i = 0; i < 8; i++) {
                bin.append((nilai & 128) == 0 ? 0 : 1);
                nilai <<= 1;
            }
        }
        konToString(bin);
    }
}
