/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.validations;
import exceptions.InvalidNameException;
import exceptions.InvalidPostalCodeException;
import java.time.LocalDate;

/**
 *
 * @author mateoamagra
 */
public class UserDataValidations {

    /**
     * *
     * Debe validar si el documento identificativo es correcto
     *
     * @param typeDoc indica el tipo de documento identificativo (NIF - 1; DNI: 2; NIE: 3)
     * @param id contiene el documento a validar
     * @return devuelve true si el formato es correcto, caso contrario false
     */
    public static boolean checkId(int typeDoc, String id) {           
        // Validación 1: Verificar longitud del ID
        if (id.length() != 9) {
            return false;
        }

        if (typeDoc == 1) {
            // Validación 2: Los primeros 8 caracteres deben ser números
            for (int i = 0; i < 8; i++) {
                if (!Character.isDigit(id.charAt(i))) {
                    return false;  
                }
            }

            char lastChar = id.charAt(8);//separo el ultimo caracter: letra
            if (!Character.isLetter(lastChar)) {
                return false;  // Si el último carácter no es una letra, es inválido
            }

            //Separa los numeros del documento (8 numeros)
            int dniNumber = Integer.parseInt(id.substring(0, 8));
            //Calculo la letra de control llamando a la funcion ControlLetter
            char controlChar = controlLetter(dniNumber);

            if (controlChar != lastChar) {
                return false;  // Si la letra de control no coincide, es inválido
            }
        }
      
        return true; 
    }

    /**
     * Auxiliar para calcular si la letra de control del DNI es correcta
     *
     * @param suma se trata de la suma (CONCATENACION) de los primeros 8 números del DNI
     * @return devuelve la letra de control correspondiente
     */
    public static char controlLetter(int suma) {
        //Letras de control, los restos son desde el 0 al 22 y correspondent al indice de una letra
        char[] controlLetters = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};
        int resto = suma % 23;
        return controlLetters[resto];
    }

    /**
     * Comprueba si la fecha de nacimiento está en el formato correcto.
     *
     * @param birthDate cadena de texto en formato "DD/MM/yyyy".
     * @return true si el formato y valores de la fecha son correctos; false en
     * caso contrario.
     */
    public static boolean checkFormatDate(String birthDate) {
        String[] parts = birthDate.split("/"); // .split divide la cadena usando / como delimitador
        // el resultado es del array parts es: [0] = dia, [1] = mes, [2] = año
        if (parts.length != 3) {
            return false;
        }
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);

        if (month < 1 || month > 12) {
            return false;
        }

        if (day < 1 || day > 31) {
            return false;
        }

        // Meses con 30 dias (exclusivo)
        if ((month == 4 || month == 6 || month == 9 || month == 11) && day > 30) {
            return false;
        }

        // Febrero, si es bisciesto o no
        if (month == 2) {
            if (esBisciesto(year)) {
                if (day > 29) {
                    return false;
                }
            } else {
                if (day > 28) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Comprueba si un año es bisiesto.
     *
     * @param year el año a verificar.
     * @return true si el año es bisiesto; false en caso contrario.
     */
    public static boolean esBisciesto(int year) {
        boolean leap = false;
        if (year % 4 == 0 && year % 100 != 0) {
            leap = true;
            return leap;
        }
        return leap;
    }

    /**
     * Calcula la edad de una persona según su fecha de nacimiento.
     *
     * @param birthDate cadena de texto en formato "DD/MM/YYYY".
     * @return la edad calculada si la fecha es válida, -1 si no lo es.
     */
    public static int calculateAge(String birthDate) {
        boolean dateOk = checkFormatDate(birthDate);
        if (!dateOk) {
            return -1; 
        }
        String[] parts = birthDate.split("/");
        int birthYear = Integer.parseInt(parts[2]); 
        int currentYear = LocalDate.now().getYear();
        // LocalDate.now(): Obtiene la fecha actual del sistema. 
        // .getYear(): Extrae el año de la fecha obtenida.
        return currentYear - birthYear;
    }

    /**
     * Verifica si un código postal es válido.
     *
     * @param zip el código postal.
     * @return true si es numérico y tiene 5 dígitos; false en caso contrario.
     */
    public static void checkPostalCode (String zip) throws InvalidPostalCodeException {
        if (!(isNumeric(zip) && zip.length() == 5)) {
            throw new InvalidPostalCodeException("El código postal debe ser numérico y de 5 dígitos.");
        }
    }

    /**
     * Comprueba si una cadena contiene solo caracteres numéricos.
     *
     * @param str la cadena a evaluar.
     * @return true si solo contiene números; false en caso contrario.
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false; // Retorna false si la cadena es null 
        }
        return str.matches("\\d+");
        
        //str.matches(pattern) comprueba si toda la cadena coincide con la expresión
        // \\d → Representa un dígito (equivalente a [0-9]). 
        //Y el + Significa uno o más dígitos consecutivos .
    }

    /**
     * Comprueba si una cadena contiene solo caracteres alfabéticos.
     *
     * @param str la cadena a evaluar.
     * @return true si solo contiene letras; false en caso contrario.
     */
    public static boolean isAlphabetic(String str) {
        if (str == null) {
            return false; // Retorna false si la cadena esta vacia
        }
        return str.matches("[a-zA-Z]+");
        //mismo metodo matches que la funcion isNumeric
        // en este caso, [a-zA-Z] comprueba todas las letras de la A a la Z (en mayus y min)
    }

    /**
     * Verifica si un correo electrónico tiene un formato válido.
     *
     * @param email el correo electrónico a evaluar.
     * @return true si tiene un formato válido y termina con un dominio
     * registrado; false en caso contrario.
     */
    public static boolean checkEmail(String email) {
        int Indicearroba = email.indexOf("@");
        //ubicar el @ en una posicion que no sea fuera de del correo ni al final
        if (Indicearroba <= 0 || Indicearroba == email.length() - 1) {
            return false;
        }
        String[] dominiosRegistrados = {".com", ".es", ".cat", ".outlook", ".org"};
        for (String dominios : dominiosRegistrados) {
            if (email.endsWith(dominios)) {
                //devuelve true cuando el EMAIL acaba con el dominio registrado que ha puesto el user
                return true;
            }
        }
        return false;
    }

    /**
     * Verifica si un nombre es válido.
     *
     * @param name el nombre a evaluar.
     * @return true si contiene solo letras y tiene un máximo de 15 caracteres;
     * false en caso contrario.
     */
    public static void checkName(String name) throws InvalidNameException {
        if (!(isAlphabetic(name) && name.length() <= 15)) {
            throw new InvalidNameException("El nombre debe ser alfabético y tener máximo 15 caracteres.");
        }
    }
    
    

}
