package Projekt;

import java.util.List;

public class Projekt {
    public static void main(String[] args) {
        String tekst = "Wielki test Tegoż gó*óównaaaaa kurła+jego+mać w-tą i_na z4d";
        //Zad1
        //Totek(args);
        //Zad2
        //Odwrotna(args);
        //Zad3
        //ileRazy(args);
        //Zad4
        //odwróć(args);
        //Zad5
        //System.out.println(suma(1.56, 2, 3, 4, 5));
        int[] output = TextToInts(tekst);

        for (int value : output) {
            System.out.print(value + " ");
        }
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
    public static int[] TextToInts(String text)
    {
        int textLength = text.length();
        int[] secret = new int[textLength*2];
        int sizeOfTable = textLength;
        
        for (int i = 0; i < textLength*2; i++) {
            secret[i] = CharToInt(text.charAt((i)/2));
            i++;
            secret[i] = (int) text.charAt((i)/2);
            if(secret[i-1]==0)
            {
                sizeOfTable++;
            }
        }

        int[] returner = new int[sizeOfTable];
        int assitant = 0;
        for (int i = 0; i < sizeOfTable; i++)
        {
            if(secret[i]==0)
            {
                returner[i] = 0;
                i++;
                assitant++;
                returner[i] = secret[assitant];
                assitant++;
            }
            else
            {
                returner[i] = secret[assitant];
                assitant += 2;
            }
        }
        return returner;
    }
    public static int CharToInt(char letter)
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
            return 82;
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
        }
        return 0;
    }

    public static char IntToChar(int number, char another)
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
        } else if(number == 82){
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
        }
        return another;
    }

    public static void Solenie(String[] args)
    {

    }
}
