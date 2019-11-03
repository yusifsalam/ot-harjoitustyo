package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);      
    }
    
    @Test
    public void kortinSaldoAlussaOikein(){
        assertEquals(kortti.saldo(), 10);
    }
    
    @Test
    public void rahanLataaminenKasvattaaSaldoa() {
        kortti.lataaRahaa(10);
        assertEquals("saldo: 0.20", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenVahenee(){
        kortti.otaRahaa(5);
        assertEquals("saldo: 0.5", kortti.toString());
    }
    
    @Test
    public void saldoEiMuutuJosRahaaEiTarpeeksi() {
        kortti.otaRahaa(20);
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void otaRahaaPalauttaaOikeanArvon() {
        assertTrue(kortti.otaRahaa(5));
        assertFalse(kortti.otaRahaa(20));
    }
    
}
