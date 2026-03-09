package Procesos.CreacionProcesos.ejerciciosCifrado;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.security.Key;

public class AESGuardaClaves {
    public static void main(String[] args) throws Exception {
        // Ruta donde se guardará la clave
        String claveFichero = "C:\\Users\\Jesús Garay\\IdeaProjects\\AccesoADatos\\src\\main\\java\\Procesos\\CreacionProcesos\\ejerciciosCifrado\\clave_aes.dat";

        // Generar clave AES de 128 bits
        Key key = generarClaveYGuardarEnFichero(claveFichero);

        // Leer clave del fichero para usarla
        key = leerClaveDeFichero(claveFichero);

        // Texto a encriptar
        String textoEnClaro = "Este es el texto que vamos a encriptar";

        // Se obtiene un cifrador AES
        Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");

        // Cifrar el texto
        aes.init(Cipher.ENCRYPT_MODE, key);
        byte[] encriptado = aes.doFinal(textoEnClaro.getBytes());

        // Mostrar texto cifrado en hexadecimal
        System.out.print("Texto cifrado: ");
        for (byte b : encriptado) {
            System.out.print(Integer.toHexString(0xFF & b));
        }
        System.out.println();

        // Descifrar el texto
        aes.init(Cipher.DECRYPT_MODE, key);
        byte[] desencriptado = aes.doFinal(encriptado);

        // Mostrar texto descifrado
        System.out.println("Texto descifrado: " + new String(desencriptado));
    }

    // Método para generar una clave AES y guardarla en un fichero
    public static Key generarClaveYGuardarEnFichero(String rutaFichero) throws Exception {
        // Generamos la clave
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // Clave de 128 bits
        Key key = keyGenerator.generateKey();

        // Guardar la clave en el fichero
        try (FileOutputStream fos = new FileOutputStream(rutaFichero)) {
            fos.write(key.getEncoded());
        }

        System.out.println("Clave generada y guardada en: " + rutaFichero);
        return key;
    }

    // Método para leer una clave AES desde un fichero
    public static Key leerClaveDeFichero(String rutaFichero) throws Exception {
        // Leer la clave del fichero
        File ficheroClave = new File(rutaFichero);
        byte[] claveBytes = new byte[(int) ficheroClave.length()];

        try (FileInputStream fis = new FileInputStream(ficheroClave)) {
            fis.read(claveBytes);
        }

        // Reconstruir la clave a partir de los bytes
        return new SecretKeySpec(claveBytes, "AES");
    }
}
