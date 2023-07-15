package Projekt;

import java.util.*;

public class Projekt {
    public static void main(String[] args) {
        String tekst = "<>1234567890qwertyuiopasdfgqwert| '*-*<>+yuiop[]asdfghjkl;'zxcvbnm,.QWERT YUIOP[ASDFGHJ KL;ZXCVBNM,12345678098765efbjrdfghj]";
        short[] text = TextToInts(tekst);
        String password = "xd";
        short[] passwordInInt = TextToInts(password);
        passwordInInt = PasswordPepper(passwordInInt);
        short[] zaszyfrowane = VernamEncryption(text, passwordInInt);
        zaszyfrowane = Salting(zaszyfrowane, passwordInInt);
        zaszyfrowane = Desalting(zaszyfrowane, passwordInInt);
        System.out.print(IntsToString(VernamDecrypting(zaszyfrowane,passwordInInt)));
        //System.out.println(IntsToString(PasswordPepper(passwordInInt)));
        //System.out.println(IntsToString(PasswordPepper(passwordInInt)));
        /*passwordInInt = PasswordPepper(passwordInInt);
        short[] passwordInInt2 = passwordInInt;
        for (int value : passwordInInt) {
            System.out.print(value + " ");
        }
        passwordInInt = Salting(passwordInInt, passwordInInt);
        
        System.out.println();
        passwordInInt = Desalting(passwordInInt, passwordInInt2);
        for (int value : passwordInInt) {
            System.out.print(value + " ");
        }*/
        //System.out.println(IntsToString(output));
        //System.out.println(CharToInt(' '));
    }


/* Mechanika zdefiniowania znaku:
 * 1 jeżeli znak jest przez nas zdefiniowany to jest tam int odpowiadający znakowi z naszego słownika
 * 2 jeśli znak nie jest przez nas zdefiniowany to w jego miejscu jest 0 a następna liczba jest intem z kodu ascii odpowiadającym temu znakowi
 * 
 * 
 * przykład:
 * 
 * nasz słownik:
 * A-1
 * B-2
 * 
 * ascii:
 * C-5
 * D-6
 * 
 * [A][D][B][C][C] = [1][0][6][2][0][5][0][5]
*/
    public static short[] TextToInts(String text)
    {
        int textLength = text.length();
        ArrayList<Short> secret = new ArrayList<Short>();
        
        for (int i = 0; i < textLength; i++) {
            secret.add(CharToInt(text.charAt(i)));
            int assist = secret.get(secret.size()-1);
            if(secret.get(secret.size()-1)==0)
            {
                secret.add((short)text.charAt(i));
            }
        }
        short[] secretArray = new short[secret.size()];
        for (int i = 0; i < secret.size(); i++) {
            secretArray[i] = secret.get(i);
        }

        return secretArray;
    }

    public static String IntsToString(short[] textInInts)
    {
        int textLength2 = textInInts.length;
        int textLength = textLength2--;
        short assistant;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < textLength; i++)
        {
            if(i==textLength2)
            {
                sb.append(IntToChar(textInInts[i], (char)0));
            }
            else
            {
                assistant = textInInts[i];
                i++;
                sb.append(IntToChar(assistant, (char) textInInts[i]));
                i--;
            }
            if(textInInts[i]==0)
            {
                i++;
            }
        }
        return sb.toString();
    }


    public static short CharToInt(char letter)
    {
        if(Character.isLetter(letter)){
            //Tutaj są tlyko litery
            if(!Character.isUpperCase(letter)){
                // tutaj tylko małe litery
                if(letter == 'q'){
                    return 6;
                } else if(letter == 'w'){
                    return 1;
                } else if(letter == 'ę'){
                    return 22;
                } else if(letter == 'e'){
                    return 17;
                } else if(letter == 'r'){
                    return 11;
                } else if(letter == 't'){
                    return 2;
                } else if(letter == 'y'){
                    return 16;
                } else if(letter == 'u'){
                    return 8;
                } else if(letter == 'i'){
                    return 24;
                } else if(letter == 'o'){
                    return 34;
                } else if(letter == 'ó'){
                    return 19;
                } else if(letter == 'p'){
                    return 29;
                } else if(letter == 'a'){
                    return 35;
                } else if(letter == 'ą'){
                    return 21;
                } else if(letter == 's'){
                    return 20;
                } else if(letter == 'ś'){
                    return 25;
                } else if(letter == 'd'){
                    return 7;
                } else if(letter == 'f'){
                    return 26;
                } else if(letter == 'g'){
                    return 5;
                } else if(letter == 'h'){
                    return 14;
                } else if(letter == 'j'){
                    return 3;
                } else if(letter == 'k'){
                    return 12;
                } else if(letter == 'l'){
                    return 10;
                } else if(letter == 'ł'){
                    return 15;
                } else if(letter == 'z'){
                    return 27;
                } else if(letter == 'ż'){
                    return 32;
                } else if(letter == 'x'){
                    return 33;
                } else if(letter == 'ź'){
                    return 4;
                } else if(letter == 'c'){
                    return 9;
                } else if(letter == 'ć'){
                    return 13;
                } else if(letter == 'v'){
                    return 18;
                } else if(letter == 'b'){
                    return 23;
                } else if(letter == 'n'){
                    return 31;
                } else if(letter == 'ń'){
                    return 30;
                } else if(letter == 'm'){
                    return 28;
                }
            } else {
                // tu tylko duże
                if(letter == 'Q'){
                    return 54;
                } else if(letter == 'W'){
                    return 41;
                } else if(letter == 'Ę'){
                    return 56;
                } else if(letter == 'E'){
                    return 67;
                } else if(letter == 'R'){
                    return 70;
                } else if(letter == 'T'){
                    return 45;
                } else if(letter == 'Y'){
                    return 36;
                } else if(letter == 'U'){
                    return 57;
                } else if(letter == 'I'){
                    return 50;
                } else if(letter == 'O'){
                    return 63;
                } else if(letter == 'Ó'){
                    return 65;
                } else if(letter == 'P'){
                    return 49;
                } else if(letter == 'A'){
                    return 68;
                } else if(letter == 'Ą'){
                    return 66;
                } else if(letter == 'S'){
                    return 55;
                } else if(letter == 'Ś'){
                    return 38;
                } else if(letter == 'D'){
                    return 47;
                } else if(letter == 'F'){
                    return 61;
                } else if(letter == 'G'){
                    return 62;
                } else if(letter == 'H'){
                    return 39;
                } else if(letter == 'J'){
                    return 40;
                } else if(letter == 'K'){
                    return 69;
                } else if(letter == 'L'){
                    return 53;
                } else if(letter == 'Ł'){
                    return 59;
                } else if(letter == 'Z'){
                    return 42;
                } else if(letter == 'Ż'){
                    return 58;
                } else if(letter == 'Ź'){
                    return 46;
                } else if(letter == 'X'){
                    return 51;
                } else if(letter == 'C'){
                    return 43;
                } else if(letter == 'Ć'){
                    return 64;
                } else if(letter == 'V'){
                    return 52;
                } else if(letter == 'B'){
                    return 44;
                } else if(letter == 'N'){
                    return 37;
                } else if(letter == 'Ń'){
                    return 60;
                } else if(letter == 'M'){
                    return 48;
                }
            }
        } else if(Character.isDigit(letter)){
            // tylko cyfry
            if(letter == '0'){
                return 77;
            } else if(letter == '1'){
                return 73;
            } else if(letter == '2'){
                return 75;
            } else if(letter == '3'){
                return 76;
            } else if(letter == '4'){
                return 80;
            } else if(letter == '5'){
                return 78;
            } else if(letter == '6'){
                return 71;
            } else if(letter == '7'){
                return 79;
            } else if(letter == '8'){
                return 72;
            } else if(letter == '9'){
                return 74;
            }
        } else if(letter == ','){
            return 88;
        } else if(letter == '.'){
            return 101;
        } else if(letter == '?'){
            return 85;
        } else if(letter == '('){
            return 87;
        } else if(letter == ')'){
            return 91;
        } else if(letter == '!'){
            return 89;
        } else if(letter == '{'){
            return 83;
        } else if(letter == '}'){
            return 84;
        } else if(letter == '['){
            return 92;
        } else if(letter == ']'){
            return 98;
        } else if(letter == '/'){
            return 94;
        } else if(letter == '*'){
            return 95;
        } else if(letter == '-'){
            return 86;
        } else if(letter == '+'){
            return 99;
        } else if(letter == '='){
            return 97;
        } else if(letter == '%'){
            return 100;
        } else if(letter == '$'){
            return 96;
        } else if(letter == '#'){
            return 102;
        } else if(letter == '@'){
            return 90;
        } else if(letter == '^'){
            return 103;
        } else if(letter == '&'){
            return 93;
        } else if(letter == '_'){
            return 82;
        } else if(letter == '"'){
            return 105;
        } else if(letter == '`'){
            return 104;
        } else if(letter == '~'){
            return 81;
        } else if(letter == ':'){
            return 106;
        } else if(letter == ';'){
            return 108;
        } else if(letter == ' '){
            return 107;
        } else if(letter == '\''){
            return 109;
        } else if(letter == '\\'){
            return 110;
        }
        return 0;
    }

    public static char IntToChar(short number, char another)
    {
        if(number > 0 && number < 71){
            //Tutaj są tlyko litery
            if(number < 36){
                // tutaj tylko małe litery
                if(number == 6){
                    return 'q';
                } else if(number == 1){
                    return 'w';
                } else if(number == 22){
                    return 'ę';
                } else if(number == 17){
                    return 'e';
                } else if(number == 11){
                    return 'r';
                } else if(number == 2){
                    return 't';
                } else if(number == 16){
                    return 'y';
                } else if(number == 8){
                    return 'u';
                } else if(number == 24){
                    return 'i';
                } else if(number == 34){
                    return 'o';
                } else if(number == 19){
                    return 'ó';
                } else if(number == 29){
                    return 'p';
                } else if(number == 35){
                    return 'a';
                } else if(number == 21){
                    return 'ą';
                } else if(number == 20){
                    return 's';
                } else if(number == 25){
                    return 'ś';
                } else if(number == 7){
                    return 'd';
                } else if(number == 26){
                    return 'f';
                } else if(number == 5){
                    return 'g';
                } else if(number == 14){
                    return 'h';
                } else if(number == 3){
                    return 'j';
                } else if(number == 12){
                    return 'k';
                } else if(number == 10){
                    return 'l';
                } else if(number == 15){
                    return 'ł';
                } else if(number == 27){
                    return 'z';
                } else if(number == 32){
                    return 'ż';
                } else if(number == 33){
                    return 'x';
                } else if(number == 4){
                    return 'ź';
                } else if(number == 9){
                    return 'c';
                } else if(number == 13){
                    return 'ć';
                } else if(number == 18){
                    return 'v';
                } else if(number == 23){
                    return 'b';
                } else if(number == 31){
                    return 'n';
                } else if(number == 30){
                    return 'ń';
                } else if(number == 28){
                    return 'm';
                }
            } else {
                // tu tylko duże
                if(number == 54){
                    return 'Q';
                } else if(number == 41){
                    return 'W';
                } else if(number == 56){
                    return 'Ę';
                } else if(number == 67){
                    return 'E';
                } else if(number == 70){
                    return 'R';
                } else if(number == 45){
                    return 'T';
                } else if(number == 36){
                    return 'Y';
                } else if(number == 57){
                    return 'U';
                } else if(number == 50){
                    return 'I';
                } else if(number == 63){
                    return 'O';
                } else if(number == 65){
                    return 'Ó';
                } else if(number == 49){
                    return 'P';
                } else if(number == 68){
                    return 'A';
                } else if(number == 66){
                    return 'Ą';
                } else if(number == 55){
                    return 'S';
                } else if(number == 38){
                    return 'Ś';
                } else if(number == 47){
                    return 'D';
                } else if(number == 61){
                    return 'F';
                } else if(number == 62){
                    return 'G';
                } else if(number == 39){
                    return 'H';
                } else if(number == 40){
                    return 'J';
                } else if(number == 69){
                    return 'K';
                } else if(number == 53){
                    return 'L';
                } else if(number == 59){
                    return 'Ł';
                } else if(number == 42){
                    return 'Z';
                } else if(number == 58){
                    return 'Ż';
                } else if(number == 46){
                    return 'Ź';
                } else if(number == 51){
                    return 'X';
                } else if(number == 43){
                    return 'C';
                } else if(number == 64){
                    return 'Ć';
                } else if(number == 52){
                    return 'V';
                } else if(number == 44){
                    return 'B';
                } else if(number == 37){
                    return 'N';
                } else if(number == 60){
                    return 'Ń';
                } else if(number == 48){
                    return 'M';
                }
            }
        } else if(number<81){
            // tylko cyfry
            if(number == 77){
                return '0';
            } else if(number == 73){
                return '1';
            } else if(number == 75){
                return '2';
            } else if(number == 76){
                return '3';
            } else if(number == 80){
                return '4';
            } else if(number == 78){
                return '5';
            } else if(number == 71){
                return '6';
            } else if(number == 79){
                return '7';
            } else if(number == 72){
                return '8';
            } else if(number == 74){
                return '9';
            }
        } else if(number == 88){
            return ',';
        } else if(number == 101){
            return '.';
        } else if(number == 85){
            return '?';
        } else if(number == 87){
            return '(';
        } else if(number == 91){
            return ')';
        } else if(number == 89){
            return '!';
        } else if(number == 83){
            return '{';
        } else if(number == 84){
            return '}';
        } else if(number == 92){
            return '[';
        } else if(number == 98){
            return ']';
        } else if(number == 94){
            return '/';
        } else if(number == 95){
            return '*';
        } else if(number == 86){
            return '-';
        } else if(number == 99){
            return '+';
        } else if(number == 97){
            return '=';
        } else if(number == 100){
            return '%';
        } else if(number == 96){
            return '$';
        } else if(number == 102){
            return '#';
        } else if(number == 90){
            return '@';
        } else if(number == 103){
            return '^';
        } else if(number == 93){
            return '&';
        } else if(number == 82){
            return '_';
        } else if(number == 105){
            return '"';
        } else if(number == 104){
            return '`';
        } else if(number == 81){
            return '~';
        } else if(number == 106){
            return ':';
        } else if(number == 108){
            return ';';
        } else if(number == 107){
            return ' ';
        } else if(number == 109){
            return '\'';
        } else if(number == 110){
            return '\\';
        }
        return another;
    }


    public static short[] Vernam(short[] text, short[] password, boolean wantDecrypt)
    {
        if(wantDecrypt)
        {
            return VernamDecrypting(text, password);
        }
        else
        {
            return VernamEncryption(text, password);
        }
    }

    public static short[] VernamEncryption(short[] text, short[] password)
    {
        int textLength = text.length;
        short[] returner = new short[textLength];
        int passwordLength = password.length;
        for (int i = 0; i < textLength; i++)
        {
            returner[i] = (short)((text[i] + password[i%passwordLength])%256);
        }
        return returner;
    }

    public static short[] VernamDecrypting(short[] text, short[] password)
    {
        int textLength = text.length;
        short[] returner = new short[textLength];
        int passwordLength = password.length;
        for (int i = 0; i < textLength; i++)
        {
            returner[i] = (short)(text[i] - password[i%passwordLength]);
            if(returner[i]<0)
            {
                returner[i] = (short)(returner[i] + 256);
            }
        }
        return returner;
    }

/*
 * Schemat solenia:
 * 
 * 1 Sprawdzenie czy solona tresc jest wystarczajaco duza zeby bylo to z sensem (24 liczb)
 * Patrz na miro
 */
    public static short[] Salting(short[] text, short[] password)
    {
        int textLength = text.length;
        if(textLength<24)
        {
            return text;
        }
        else
        {
            short A = text[0];
            Short B = password[1];
            short C = text[2];
            short G;
            short H = password[password.length - 2];
            int I;
            short howFarIsSalt;
            int numberOfSalts=1;
            Queue<Short> qreturner = new LinkedList<>();
            qreturner.offer(A);
            if((A%2)==0)
            {
                qreturner.offer(RandomCharSize());
                qreturner.offer(text[1]);
            } 
            else
            {
                qreturner.offer(text[1]);
                qreturner.offer(RandomCharSize());
            }
            howFarIsSalt = (short)(((Math.pow(A, 2))*B+C)%20 +1);
            while(textLength+numberOfSalts>qreturner.size())
            {
                if(howFarIsSalt == 0)
                {    
                    G=RandomCharSize(); //wartosc ostatniej soli szyfrujacej
                    I=qreturner.size(); //teraz zmienna I ma lokalizacje w tablicy pierwszej szyfrujacej(ostatniej) soli
                    qreturner.offer(G);
                    howFarIsSalt = (short)((((Math.pow(G, 2)) + (H*I) + B)%355)+1);
                    numberOfSalts++;
                }
                else
                {
                    howFarIsSalt--;
                    qreturner.offer(text[qreturner.size()-numberOfSalts]);
                }
            }
            short[] returner = new short[qreturner.size()];

            // Przepisywanie elementów z kolejki do tabeli
            int index = 0;
            while (!qreturner.isEmpty()) {
                returner[index] = qreturner.poll();
                index++;
            }

            return returner;
        }
    }

    public static short[] Desalting(short[] text, short[] password)
    {
        int textLength = text.length;
        if(textLength<24)
        {
            return text;
        }
        else
        {
            short A = text[0];
            Short B = password[1];
            short C = text[3];
            short G;
            short H = password[password.length - 2];
            int I;
            short howFarIsSalt;
            int numberOfSalts=1;
            Queue<Short> qreturner = new LinkedList<>();
            qreturner.offer(A);
            if((A%2)==0)
            {
                qreturner.offer(text[2]);
            } 
            else
            {
                qreturner.offer(text[1]);
            }
            howFarIsSalt = (short)(((Math.pow(A, 2))*B+C)%20 +1);
            while(textLength-numberOfSalts>qreturner.size())
            {
                if(howFarIsSalt == 0)
                {    
                    G=text[qreturner.size()+numberOfSalts]; //wartosc ostatniej soli szyfrujacej
                    I=qreturner.size()+numberOfSalts; //teraz zmienna I ma lokalizacje w tablicy pierwszej szyfrujacej(ostatniej) soli
                    howFarIsSalt = (short)((((Math.pow(G, 2)) + (H*I) + B)%355)+1);
                    numberOfSalts++;
                }
                else
                {
                    howFarIsSalt--;
                    qreturner.offer(text[qreturner.size()+numberOfSalts]);
                }
            }
            short[] returner = new short[qreturner.size()];

            // Przepisywanie elementów z kolejki do tabeli
            int index = 0;
            while (!qreturner.isEmpty()) {
                returner[index] = qreturner.poll();
                index++;
            }

            return returner;
        }
    }

    public static short RandomCharSize()
    {
        Random random = new Random();
        return (short)random.nextInt(256);
    }

    public static void IsPrimeWork()
    {
        short index = 0;
        while(index<256)
        {
            if(IsPrime(index))
            {
                System.out.print(index + " ");
            }
            index++;
        }
    }

    public static boolean IsPrime(short number) {
        if (number <= 1) {
            return false;
        }

        if (number == 2 || number == 3) {
            return true;
        }

        if (number % 2 == 0 || number % 3 == 0) {
            return false;
        }

        short divisor = 5;
        while (divisor * divisor <= number) {
            if (number % divisor == 0 || number % (divisor + 2) == 0) {
                return false;
            }
            divisor += 6;
        }

        return true;
    }

    public static short[] PasswordPepper(short[] password) {
        short[] array = new short[password.length];
        int x = 0;
        for (short s : password) {
            array[x] = s;
            x++;
        }
        Random random = new Random(Arrays.hashCode(array));

        for (int i = array.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            short temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }
        x = random.nextInt(20);
        int textLength = array.length;
        short[] pepper = new short[textLength*2];
        int idPepper = 0;
        for (int i = 0; i < textLength; i++)
        {
            if(IsPrime(array[i]) || idPepper == 0)
            {
                array[i]=(short)(Math.pow(array[i], 3)%256);
                pepper[idPepper] = (short)((Math.abs(array[i] * i - x))%256);
                idPepper++;
            }
            else
            {
                array[i] = (short)((array[i] * x)%256);
                x = random.nextInt(100);
            }
            array[i] = (short)((array[i]+x)%256);
            x = random.nextInt(20);
            if(array[i] % 3 == 0)
            {
                pepper[idPepper] = (short)((Math.abs(array[i] * x - Math.pow(array[i]%11, i)))%256);
                idPepper++;
            }
        }
        short[] returner = new short[textLength + idPepper];
        int i=0;
        for (short s : array) {
            returner[i] = s;
            i++;
        }
        for (short s : pepper) {
            returner[i] = s;
            i++;
            if(returner.length == i)
            {
                break;
            }
        }

        if(returner.length<20)
        {
            i=0;
            short[] finalReturner = new short[20];
            for (short s : returner) {
                finalReturner[i] = s;
                i++;
            }
            for(int j=20-returner.length; j>0; j--)
            {
                finalReturner[i] = (short)(random.nextInt(500)%256);
                i++;
            }
            returner = finalReturner;
        }
        //Dodac minimalna ilosc znakow zeby wyjsciowe haslo nie bylo nigdy krotsze od 20 znakow. Po takiej zmianie nalezy tablice jeszcze raz przemiksowac
        for (i = returner.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);

            short temp = returner[i];
            returner[i] = returner[j];
            returner[j] = temp;
        }
        return returner;
    }

}
