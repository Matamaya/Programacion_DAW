/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.console;

import exceptions.InvalidNameException;
import exceptions.InvalidPostalCodeException;
import java.util.Scanner;
import model.validations.UserDataValidations;


/**
 *
 * @author mateoamagra
 */
public class Main {
    
    static Scanner sc = new Scanner(System.in);    
    public static void main(String[] args) {
        sc.useDelimiter("\n");
        String option;
        do {
            System.out.println("TESTER FUNCIONES UserDataValidations: ");
            System.out.println("1. testCheckId ");
            System.out.println("2. testCheckFormatDate ");
            System.out.println("3. testCalculateAge ");
            System.out.println("4. testCheckPostalCode ");
            System.out.println("5. testIsNumeric ");
            System.out.println("6. testIsAlphabetic ");
            System.out.println("7. testCheckEmail ");
            System.out.println("8. testCheckName ");
            System.out.println("Z. SALIR ");
            
            System.out.println("Selecciona una opcion: ");
            option = sc.next();
            
            switch (option) {
                case "1":
                    testCheckId();
                    break;
                case "2":
                    testCheckFormateDate();
                    break;
                case "3":
                    testCalculateAge();
                    break;
                case "4":
                    testCheckPostalCode();
                    break;
                case "5":
                    testIsNumeric();
                    break;
                case "6":
                    testIsAlphabetic();
                    break;
                case "7":
                    testCheckEmail();
                    break;
                case "8":
                    testCheckName();
                    break;
                case "Z":
                    System.out.println("FIN");
                    break;
                default:
                    System.out.println("Opción invalida");
            }
            
        } while (!option.equals("Z"));
    }
    
    public static void testCheckId(){
        System.out.println("Enter your Type of Documentation: (1:DNI) (2:ELSE)");
        int tipo = sc.nextInt();
        while (tipo!=1) {
            System.out.println("The Documentation type it's not supported by the program yet, please enter 1...");
            tipo = sc.nextInt();
        }
        System.out.println("Enter your ID: ");
        String nif = sc.next();
        boolean nifOk = UserDataValidations.checkId(tipo,nif);
        if (nifOk==true) {
            System.out.println("The ID is correct");
        }else{
            System.out.println("Wrong ID format");
        }  
    }
     
    public static void testCheckFormateDate(){
        System.out.println("Enter the current date (Format: DD/MM/YYYY)");
        String date = sc.next();
        boolean dateOk = UserDataValidations.checkFormatDate(date);
        if (dateOk) {
            System.out.println("correct formate date");
        }else{
            System.out.println("incorrect formate date");
        } 
    }
      
    public static void testCalculateAge(){
        System.out.println("Enter your birth date");
        String birthDate = sc.next();
        int ageOk = UserDataValidations.calculateAge(birthDate);
        if (ageOk!=-1) {
            System.out.println("correct format, the age is: "+ageOk);
        }else{
            System.out.println("incorrect format, error: "+ageOk);
        }
    }
    
    //cambio de validacion
    public static void testCheckPostalCode(){
        System.out.println("Introducir el codigo postal (5 numeros)");
        String zip = sc.next();
        try{
            UserDataValidations.checkPostalCode(zip);
            System.out.println("the postal Code format is correct");
        }catch (InvalidPostalCodeException e) {
            System.out.println("Codigo postal invalido");
        }
        
    }
    
    public static void testIsNumeric(){
        System.out.println("enter a text");
        String str = sc.next();
        boolean numeric = UserDataValidations.isNumeric(str);
        if (numeric) {
            System.out.println(numeric+" the text is numeric");
        }else{
            System.out.println(numeric+" the text it's not numeric");
        }     
    }
    
    public static void testIsAlphabetic(){
        System.out.println("enter a text");
        String str = sc.next();
        boolean alphabetic = UserDataValidations.isAlphabetic(str);
        if (alphabetic) {
            System.out.println(alphabetic+" the text is alphabetic");
        }else{
            System.out.println(alphabetic+" the text it's not alphabetic");
        }     
    }
    
    public static void testCheckEmail(){
        System.out.println("Ingrese un Correo Electrónico:");
        String email = sc.next();
        boolean emailOk = UserDataValidations.checkEmail(email); 
        if (emailOk) {
            System.out.println("¿Es el correo valido? " + emailOk);
        } else {
            System.out.println("¿Es el correo valido? "+emailOk);
        }
    }
    
    //cambio de validacion
    public static void testCheckName(){
        System.out.println("Ingrese un Nombre");
        String name = sc.next();
        
        try{
            UserDataValidations.checkName(name);
            System.out.println("Correct name");
        }catch(InvalidNameException e){
            System.out.println("invalid name");
        }
    }
    
}
