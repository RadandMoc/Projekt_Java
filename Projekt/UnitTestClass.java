package Projekt;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
public class UnitTestClass {


    @Test
    public void testIsPrime() {
        assertTrue(Projekt.IsPrime((short)2));
        assertTrue(Projekt.IsPrime((short)3));
        assertTrue(Projekt.IsPrime((short)5));
        assertTrue(Projekt.IsPrime((short)7));
        assertTrue(Projekt.IsPrime((short)11));
        assertTrue(Projekt.IsPrime((short)13));
        assertFalse(Projekt.IsPrime((short)1));
        assertFalse(Projekt.IsPrime((short)4));
        assertFalse(Projekt.IsPrime((short)9));
        assertFalse(Projekt.IsPrime((short)15));
        assertFalse(Projekt.IsPrime((short)16));
    }

    @Test

    public void testVernamEncryption() {

        // Testowanie funkcji VernamEncryption

        short[] text = {100, 200, 50, 150, 75};

        short[] password = {50, 100};

        short[] expected = {150, 44, 100, 250, 125};

        short[] result = Projekt.VernamEncryption(text, password);

        assertArrayEquals(expected, result);

    }

    @Test
    public void testVernamDecrypting() {

        // Testowanie funkcji VernamDecrypting

        short[] encryptedText = {150, 44, 48, 250, 125};
        short[] password = {50, 100};
        short[] expected = {100, 200, 254, 150, 75};

        short[] result = Projekt.VernamDecrypting(encryptedText, password);
        assertArrayEquals(expected, result);

    }

    @Test
    public void testPeppering()
    {
        short[] shortList=new short[1];
        shortList[0]=10;
        shortList=Projekt.PasswordPepper(shortList);
        Assert.assertTrue(shortList.length==20);


        short[] shortList2=new short[3];
        shortList2[0]=1;
        shortList2[1]=11;
        shortList2[2]=123;
        shortList2=Projekt.PasswordPepper(shortList2);
        Assert.assertTrue(shortList2.length==20);
    }

    @Test
    public void FullCryptologyTest()
    {
        String text = "Koło Naukowe Mentor potrzebuje nowych studentów";
        String key = "AGH Zarządzanie Projekt Java";
        short[] textToInt= Projekt.TextToInts(text);
        short[] keyToInt= Projekt.TextToInts(key);
        keyToInt=Projekt.PasswordPepper(keyToInt);
        textToInt=Projekt.Salting(textToInt,keyToInt);
        textToInt=Projekt.Vernam(textToInt,keyToInt,false);
        textToInt=Projekt.Vernam(textToInt,keyToInt,true);
        textToInt=Projekt.Desalting(textToInt,keyToInt);
        assertTrue(Projekt.IntsToString(textToInt).equals(text));
    }


}
