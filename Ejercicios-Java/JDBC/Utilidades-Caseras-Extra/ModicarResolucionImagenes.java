package UtilidadesCaseras;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Scanner;

public class ModicarResolucionImagenes {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione una opción:");
        System.out.println("1. Redimensionar imágenes");
        System.out.println("2. Renombrar archivos a minúsculas");
        System.out.print("Opción: ");
        int option = scanner.nextInt();
        scanner.nextLine();  // Consumir la nueva línea

        System.out.print("Ingresa la ruta de la carpeta con las imágenes: ");
        String folderPath = scanner.nextLine();

        File folder = new File(folderPath);
        File[] files = folder.listFiles((dir, name) -> name.toLowerCase().endsWith(".jpg") || name.toLowerCase().endsWith(".jpeg"));

        if (files == null) {
            System.out.println("No se encontraron archivos JPG en la carpeta especificada.");
            scanner.close();
            return;
        }

        switch (option) {
            case 1:
                redimensionarImagenes(scanner, folder, files);
                break;
            case 2:
                renombrarArchivos(files);
                break;
            default:
                System.out.println("Opción no válida.");
        }

        scanner.close();
    }

    private static void redimensionarImagenes(Scanner scanner, File folder, File[] files) {
        System.out.print("Ingresa el ancho deseado: ");
        int newWidth = scanner.nextInt();

        System.out.print("Ingresa la altura deseada: ");
        int newHeight = scanner.nextInt();

        // Crear carpeta de salida
        File outputFolder = new File(folder, "resized");
        if (!outputFolder.exists()) {
            outputFolder.mkdirs();
        }

        for (File file : files) {
            try {
                BufferedImage originalImage = ImageIO.read(file);
                BufferedImage resizedImage = resizeImage(originalImage, newWidth, newHeight);

                // Guardar la imagen redimensionada en la carpeta de salida
                File outputFile = new File(outputFolder, file.getName());
                ImageIO.write(resizedImage, "jpg", outputFile);

                System.out.println("Imagen redimensionada guardada: " + outputFile.getPath());
            } catch (IOException e) {
                System.out.println("Error al procesar la imagen: " + file.getName());
                e.printStackTrace();
            }
        }

        System.out.println("Redimensionado completado.");
    }

    private static BufferedImage resizeImage(BufferedImage originalImage, int width, int height) {
        Image tmp = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return resizedImage;
    }

    private static void renombrarArchivos(File[] files) {
        for (File file : files) {
            String fileName = file.getName();
            String lowerCaseName = fileName.toLowerCase();
            File renamedFile = new File(file.getParent(), lowerCaseName);

            if (!fileName.equals(lowerCaseName)) {
                if (file.renameTo(renamedFile)) {
                    System.out.println("Archivo renombrado: " + renamedFile.getPath());
                } else {
                    System.out.println("Error al renombrar el archivo: " + fileName);
                }
            } else {
                System.out.println("El archivo ya está en minúsculas: " + fileName);
            }
        }

        System.out.println("Renombrado completado.");
    }
}
