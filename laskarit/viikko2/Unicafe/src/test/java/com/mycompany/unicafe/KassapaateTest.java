
package com.mycompany.unicafe;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


public class KassapaateTest {
    
    Kassapaate kassa;
    Maksukortti kortti;
    
    @Before
    public void setUp() {
        kassa = new Kassapaate();
        kortti = new Maksukortti(0);
    }
    
    @Test
    public void luotuKassapaateOlemassa(){
        assertTrue(kassa!= null);
    }
    
    @Test
    public void rahaaAlussaOikein() {
        assertEquals(kassa.kassassaRahaa(), 100000);
    }
    
    @Test
    public void syoEdullisestiKateisella() {
        assertEquals(kassa.syoEdullisesti(240), 0);
        assertEquals(kassa.kassassaRahaa(), 100240);
    }
    
    @Test
    public void syoEdullisestiKateisellaVaihtoraha() {
        assertEquals(kassa.syoEdullisesti(300), 60);
        assertEquals(kassa.kassassaRahaa(), 100240);
    }
    
    @Test
    public void syoEdullisestiMaksuEiRiittava() {
        assertEquals(kassa.syoEdullisesti(200), 200);
        assertEquals(kassa.kassassaRahaa(), 100000);
    }
    
    
    @Test
    public void syoMaukkaastiKateisella() {
        assertEquals(kassa.syoMaukkaasti(400), 0);
        assertEquals(kassa.kassassaRahaa(), 100400);
    }
    
    @Test
    public void syoMaukkaastiKateisellaVaihtoraha() {
        assertEquals(kassa.syoMaukkaasti(500), 100);
        assertEquals(kassa.kassassaRahaa(), 100400);
    }
    
    @Test
    public void syoMaukkaastiMaksuEiRiittava() {
        assertEquals(kassa.syoMaukkaasti(200), 200);
        assertEquals(kassa.kassassaRahaa(), 100000);
    }
    
    @Test
    public void kateisellaMyytyjenMaaraKasvaa() {
        kassa.syoEdullisesti(240);
        assertEquals(kassa.edullisiaLounaitaMyyty(), 1);
        
        kassa.syoMaukkaasti(400);
        kassa.syoMaukkaasti(400);
        assertEquals(kassa.maukkaitaLounaitaMyyty(), 2);
    }
    
    @Test
    public void syoEdullisestiKortilla() {
        kortti.lataaRahaa(250);
        assertTrue(kassa.syoEdullisesti(kortti));
        assertEquals(kortti.saldo(), 10);
    }
    
    @Test
    public void syoEdullisestiKortillaEiTarpeeksiRahaa() {
        kortti.lataaRahaa(100);
        assertFalse(kassa.syoEdullisesti(kortti));
        assertEquals(kortti.saldo(), 100);
    }
    
    @Test
    public void syoMaukkaastiiKortilla() {
        kortti.lataaRahaa(450);
        assertTrue(kassa.syoMaukkaasti(kortti));
        assertEquals(kortti.saldo(), 50);
    }
    
    @Test
    public void syoMaukkaastiKortillaEiTarpeeksiRahaa() {
        kortti.lataaRahaa(100);
        assertFalse(kassa.syoMaukkaasti(kortti));
        assertEquals(kortti.saldo(), 100);
    }
    
    @Test
    public void kortillaMyytyjenMaaraKasvaa() {
        kortti.lataaRahaa(10000);
        kassa.syoEdullisesti(kortti);
        kassa.syoMaukkaasti(kortti);
        kassa.syoMaukkaasti(kortti);
        assertEquals(kassa.edullisiaLounaitaMyyty(), 1);
        assertEquals(kassa.maukkaitaLounaitaMyyty(), 2);
    }
    
    @Test
    public void kassanRahaEiMuutuKortilla() {
        kortti.lataaRahaa(500);
        kassa.syoEdullisesti(kortti);
        assertEquals(kassa.kassassaRahaa(), 100000);
    }
    
    @Test
    public void kortinLatausToimiiOikein() {
        kassa.lataaRahaaKortille(kortti, 500);
        assertEquals(kortti.saldo(), 500);
        assertEquals(kassa.kassassaRahaa(), 100500);
    }
    
    @Test
    public void kortinLatausNegatiivinenArvo() {
        kassa.lataaRahaaKortille(kortti, -5);
        assertEquals(kortti.saldo(), 0);
        assertEquals(kassa.kassassaRahaa(), 100000);
    }
    
}
