/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

/**
 *
 * @author mateoamagra
 */
public class Funciones {

    /**
     * Crea una carpeta con el nombre indicado, si no existe.
     *
     * @param folderName nombre de la carpeta a crear
     */
    public static void createFolder(String folderName) {
        String path = System.getProperty("user.dir");
        String separator = File.separator;
        String pathFolder = path + separator + folderName;
        File folder = new File(pathFolder);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    /**
     *
     * Crea un archivo en la ruta especificada y escribe el contenido
     * proporcionado. Si el archivo ya existe, se añadirá el nuevo contenido.
     *
     * @param path ruta donde se creará el archivo
     * @param fileName nombre del archivo a crear
     * @param content contenido que se escribirá en el archivo
     */
    public static void createFile(String path, String fileName, String content) throws IOException {
        File file = new File(path, fileName);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            bw.write(content);
            bw.newLine();
            bw.flush();
        }
    }

    /**
     *
     * Retorna un listado con los nombres de los archivos contenidos en la ruta
     * especificada.
     *
     * @param path ruta del directorio a listar
     * @return devuelve un array de Strings con los nombres de los archivos
     */
    public static String[] showListFiles(String path) {
        File folder = new File(path);
        String[] filesList = folder.list();
        return filesList;
    }

    /**
     *
     * Retorna el contenido de un archivo específico ubicado en la ruta
     * indicada.
     *
     * @param path ruta del archivo
     * @param fileName nombre del archivo a leer
     * @return devuelve el contenido del archivo como String
     */
    public static String showFile(String path, String fileName) throws FileNotFoundException, IOException {
        File file = new File(path, fileName);
        StringBuilder content = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                content.append(line).append("\n");
            }
        }
        return content.toString();
    }

    /**
     * Sobrescribe el contenido de un archivo con nuevo texto.
     *
     * @param path ruta del archivo
     * @param fileName nombre del archivo a sobrescribir
     * @param newContent nuevo contenido a escribir en el archivo
     * @return devuelve true si la operación fue exitosa, false si el archivo no
     * existe
     */
    public static boolean overWriteFile(String path, String fileName, String newContent) throws IOException {
        File file = new File(path, fileName);
        if (!file.exists()) {
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write(newContent);
        }
        return true;
    }

    /**
     * Elimina el archivo especificado de la ruta dada.
     *
     * @param path ruta del archivo
     * @param fileName nombre del archivo a eliminar
     */
    public static void deleteFile(String path, String fileName) {
        File file = new File(path, fileName);
        file.delete();
    }

    /**
     *
     * Cuenta y retorna el número de caracteres en el archivo especificado.
     *
     * @param path ruta del archivo
     * @param fileName nombre del archivo a analizar
     * @return devuelve la cantidad de caracteres del archivo
     */
    public static int countChars(String path, String fileName) throws IOException {
        String content = showFile(path, fileName);
        return content.length() - 1;
    }

    /**
     *
     * Cuenta y retorna el número de palabras en el archivo especificado.
     *
     * @param path ruta del archivo
     * @param fileName nombre del archivo a analizar
     * @return devuelve la cantidad de palabras del archivo
     */
    public static int countWords(String path, String fileName) throws IOException {
        String content = showFile(path, fileName);
        if (content.isEmpty()) {
            return 0;
        }
        return content.split("\\s+").length;
    }

    /**
     *
     * Reemplaza todas las apariciones de una palabra por otra en el contenido
     * del archivo.
     *
     * @param path ruta del archivo
     * @param fileName nombre del archivo a modificar
     * @param oldWord palabra a buscar y reemplazar
     * @param newWord nueva palabra que sustituirá a la anterior
     * @return devuelve el nuevo contenido del archivo como String
     */
    public static String swapWords(String path, String fileName, String oldWord, String newWord) throws IOException {
        String content = showFile(path, fileName);
        String newContent = content.replaceAll(oldWord, newWord);
        overWriteFile(path, fileName, newContent);
        return newContent;
    }

    /**
     *
     * Genera una versión PDF del contenido del archivo especificado.
     *
     * @param path ruta del archivo
     * @param fileName nombre del archivo del cual se generará el PDF
     */
    public static void printPDF(String path, String fileName) throws IOException {
        String content = showFile(path, fileName);
            // Crear nuevo documento PDF
            try (PDDocument document = new PDDocument()) {
                PDPage page = new PDPage();
                document.addPage(page);

                // Escribir el contenido en el PDF
                try (PDPageContentStream contentPDF = new PDPageContentStream(document, page)) {
                    contentPDF.setFont(new PDType1Font(Standard14Fonts.FontName.COURIER), 12);
                    contentPDF.beginText();
                    contentPDF.newLineAtOffset(25, 700); // Posición inicial

                    // Escribir cada línea
                    for (String linea : content.split("\n")) {
                        contentPDF.showText(linea);
                        contentPDF.newLineAtOffset(0, -15); // Bajar 15px para la siguiente línea
                    }

                    contentPDF.endText();
                }

                // Generar nombre del PDF (mismo nombre pero .pdf
                String namePDF = fileName.replaceFirst("[.][^.]+$", "") + ".pdf";
                document.save(new File(path, namePDF).getAbsolutePath());
            }
        }

    }
