package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto;
    Varasto varasto2;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        varasto2 = new Varasto(11, 3);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(56, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void konstruktorinLuomaVarastonTilavuusNollaJosAnnettuTilavuusEiPositiivinen() {
        Varasto varastoNegTil = new Varasto(-54);
        assertEquals(0, varastoNegTil.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaSaldoOikein() {
        assertEquals(3, varasto2.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void uudellaVarastollaTilavuusOikein() {
        assertEquals(11, varasto2.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudenVarastonTilavuusNollaJosAnnettuTilavuusEiPositiivinen() {
        Varasto varastoNegTil = new Varasto(-54, 5);
        assertEquals(0, varastoNegTil.getTilavuus(), vertailuTarkkuus);
    }
    
    @Test
    public void uudenVarastonSaldoNollaJosAnnettuSaldoEiPositiivinen() {
        Varasto varastoNegTil = new Varasto(11, -5);
        assertEquals(0, varastoNegTil.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysNegatiivisellaEiMuutaSaldoa() {
        varasto.lisaaVarastoon(-43);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysNegatiivisellaEiMuutaVapaataTilaa() {
        varasto.lisaaVarastoon(-43);
        
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysJottaTuleeTayteenSaldoMaksimi() {
        varasto.lisaaVarastoon(400);
        
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void lisaysJottaTuleeTayteenEiVapaataTilaa() {
        varasto.lisaaVarastoon(400);
        
        assertEquals(0, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenNegatiivisellaEiMuutaSaldoa() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(-54);
        
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenNegatiivisellaEiMuutaVapaataTilaa() {
        varasto.lisaaVarastoon(8);
        varasto.otaVarastosta(-54);
        
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenYliSaldoSaadanKokoSaldo() {
        varasto.lisaaVarastoon(5);
        
        assertEquals(5, varasto.otaVarastosta(30), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenYliSaldoSaldoNolla() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(30);
        
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }
    
    @Test
    public void ottaminenYliSaldoVapaaTilaMaksimi() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(30);
        
        assertEquals(10, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }
    
    @Test
    public void toStringToimii() {
        varasto.lisaaVarastoon(4);
        
        assertEquals("saldo = 4.0, vielä tilaa 6.0", varasto.toString());
    }

}