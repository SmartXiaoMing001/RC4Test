/*
 * Implementación del algoritmo RC4
 * con Strings
 */
package rc4;

/**
 *
 * @author Ivan Garcia y Alvaro Alonso
 */
//Clase encargada del cifrado RC4
public class Rc4 {

    //Variables
    private char i;
    private char j;
    private int[] Sbox;
    private String key;

    //Constructor de la clase si recibe un String
    public Rc4(String k) {
        //Inicializamos las variables
        this.i = 0;
        this.j = 0;
        this.Sbox = new int[256];
        this.key = k;
        for (int n = 0; n < 256; n++) {
            this.Sbox[n] = n;
        }
    }

    //Primera mezcla de enteros en la caja Sbox
    //La salida se utiliza como entrada en prga
    public void ksa() {
        j = 0;
        int k;
        for (i = 0; i < 256; i++) {
            k = key.charAt(i % key.length());
            j = (char) ((j + Sbox[i] + k) % 256);
            swap(i, j);
        }
        this.i = 0;
        this.j = 0;
    }

    //Devuelve el valor del vector en la posicion concreta
    public int prga() {
        int t;
        i = (char) ((i + 1) % 256);
        j = (char) ((j + Sbox[i]) % 256);
        swap(i, j);
        t = (Sbox[i] + Sbox[j]) % 256;
        return Sbox[t];
    }

    //Se intercambia el valor de la posicion i con el valor de la posicion j
    public void swap(int x, int z) {
        int aux = Sbox[x];
        Sbox[x] = Sbox[z];
        Sbox[z] = aux;
    }

    //Descodificamos el mesnaje
    public String descifra(String cifrado) {
        String s = "";
        String aux[] = new String[cifrado.length() / 2];
        
        //Partimos el string en pares
        //Cada par sera un hexadecimal
        for (int n = 0, v = 0; v < aux.length; v++, n += 2) {
            aux[v] = cifrado.substring(n, n + 2);
        }
        
        int r;
        for (int n = 0; n <= cifrado.length() - 2; n += 2) {
            // ^ es el operador XOR
            r = Integer.parseInt(cifrado.substring(n, n + 2), 16) ^ this.prga();
            s = s.concat(Character.toString((char) r));
        }
        return s;
    }
}
