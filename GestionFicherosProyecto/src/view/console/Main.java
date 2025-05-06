/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.console;

/**
 *
 * @author mateoamagra
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import model.Funciones;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String path;
        String folderName;
        String fileName;
        String content;
        String oldWord, newWord;
        boolean exit = false;
                
        

        do {
            try {
                System.out.println("\n--- Menú de opciones ---");
                System.out.println("1. Crear carpeta");
                System.out.println("2. Crear archivo");
                System.out.println("3. Listar archivos de carpeta");
                System.out.println("4. Mostrar archivo");
                System.out.println("5. Sobrescribir archivo");
                System.out.println("6. Eliminar archivo");
                System.out.println("7. Contar caracteres");
                System.out.println("8. Contar palabras");
                System.out.println("9. Intercambiar palabras en archivo");
                System.out.println("10. Crear PDF de archivo");
                System.out.println("0. Salir");
                System.out.print("Selecciona una opción: ");
                int option = scanner.nextInt();
                scanner.nextLine();  // Limpiar buffer

                switch (option) {
                    case 1:
                        System.out.print("Introduce el nombre de la carpeta: ");
                        folderName = scanner.nextLine();
                        Funciones.createFolder(folderName);
                        System.out.println("Carpeta creada correctamente.");
                        break;

                    case 2:
                        System.out.print("Introduce la ruta de la carpeta donde se creará el archivo: ");
                        path = scanner.nextLine();
                        System.out.print("Introduce el nombre del archivo: ");
                        fileName = scanner.nextLine();
                        System.out.print("Introduce el contenido del archivo: ");
                        content = scanner.nextLine();
                        Funciones.createFile(path, fileName, content);
                        System.out.println("Archivo creado correctamente.");
                        break;

                    case 3:
                        System.out.print("Introduce la ruta de la carpeta para listar archivos: ");
                        path = scanner.nextLine();
                        String[] files = Funciones.showListFiles(path);
                        for (String file : files) {
                            System.out.println(file);
                        }
                        if (files.length == 0) {
                            System.out.println("No hay archivos en esta carpeta.");
                        }
                        break;

                    case 4:
                        System.out.print("Introduce la ruta de la carpeta: ");
                        path = scanner.nextLine();
                        System.out.print("Introduce el nombre del archivo: ");
                        fileName = scanner.nextLine();
                        String fileContent = Funciones.showFile(path, fileName);
                        System.out.println("Contenido del archivo:\n" + fileContent);
                        break;

                    case 5:
                        System.out.print("Introduce la ruta de la carpeta: ");
                        path = scanner.nextLine();
                        System.out.print("Introduce el nombre del archivo: ");
                        fileName = scanner.nextLine();
                        System.out.print("Introduce el nuevo contenido: ");
                        content = scanner.nextLine();
                        boolean overwritten = Funciones.overWriteFile(path, fileName, content);
                        if (overwritten) {
                            System.out.println("Archivo sobrescrito correctamente.");
                        } else {
                            System.out.println("El archivo no existe.");
                        }
                        break;

                    case 6:
                        System.out.print("Introduce la ruta de la carpeta: ");
                        path = scanner.nextLine();
                        System.out.print("Introduce el nombre del archivo: ");
                        fileName = scanner.nextLine();
                        Funciones.deleteFile(path, fileName);
                        System.out.println("Archivo eliminado correctamente.");
                        break;

                    case 7:
                        System.out.print("Introduce la ruta de la carpeta: ");
                        path = scanner.nextLine();
                        System.out.print("Introduce el nombre del archivo: ");
                        fileName = scanner.nextLine();
                        int charCount = Funciones.countChars(path, fileName);
                        System.out.println("Número de caracteres: " + charCount);
                        break;

                    case 8:
                        System.out.print("Introduce la ruta de la carpeta: ");
                        path = scanner.nextLine();
                        System.out.print("Introduce el nombre del archivo: ");
                        fileName = scanner.nextLine();
                        int wordCount = Funciones.countWords(path, fileName);
                        System.out.println("Número de palabras: " + wordCount);
                        break;

                    case 9:
                        System.out.print("Introduce la ruta de la carpeta: ");
                        path = scanner.nextLine();
                        System.out.print("Introduce el nombre del archivo: ");
                        fileName = scanner.nextLine();
                        System.out.print("Introduce la palabra a reemplazar: ");
                        oldWord = scanner.nextLine();
                        System.out.print("Introduce la nueva palabra: ");
                        newWord = scanner.nextLine();
                        String newContent = Funciones.swapWords(path, fileName, oldWord, newWord);
                        System.out.println("Nuevo contenido del archivo:\n" + newContent);
                        break;

                    case 10:
                        System.out.print("Introduce la ruta de la carpeta: ");
                        path = scanner.nextLine();
                        System.out.print("Introduce el nombre del archivo: ");
                        fileName = scanner.nextLine();
                        Funciones.printPDF(path, fileName);
                        System.out.println("PDF creado correctamente.");
                        break;

                    case 0:
                        exit = true;
                        break;

                    default:
                        System.out.println("Opción no válida. Intenta nuevamente.");
                }
            } catch (IOException e) {
                System.out.println("Error de I/O: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
        } while (!exit);

        scanner.close();
    }
}
