/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Final;

import java.io.BufferedReader;
import java.io.File;
//import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CryptoUtils {
    private static final int SHIFT = 3;

    // Caesar cipher encryption
    public static String encrypt(String input) {
        StringBuilder encrypted = new StringBuilder();
        for (char c : input.toCharArray()) {
            encrypted.append((char) (c + SHIFT));
        }
        return encrypted.toString();
    }

    // Caesar cipher decryption
    public static String decrypt(String input) {
        StringBuilder decrypted = new StringBuilder();
        for (char c : input.toCharArray()) {
            decrypted.append((char) (c - SHIFT));
        }
        return decrypted.toString();
    }

    // Check if username/password is valid
    public static boolean isValidLogin(File file, String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    String storedUser = parts[0];
                    String storedEncryptedPass = parts[1];
                    String decryptedPass = decrypt(storedEncryptedPass);

                    if (storedUser.equals(username) && decryptedPass.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
        }

        return false;
    }

    // Register user with encrypted password
    public static void registerUser(String username, String password) {
        try (FileWriter writer = new FileWriter("C:\\Applicants\\Users.txt", true)) {
            String encryptedPass = encrypt(password);
            writer.write(username + ":" + encryptedPass + System.lineSeparator());
        } catch (IOException e) {
        }
    }

    // Export decrypted user data to another file
    public static void exportDecryptedUsers(File encryptedFile, File outputFile) {
        try (
            BufferedReader reader = new BufferedReader(new FileReader(encryptedFile));
            FileWriter writer = new FileWriter(outputFile)
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":", 2);
                if (parts.length == 2) {
                    String username = parts[0];
                    String decryptedPass = decrypt(parts[1]);
                    writer.write(username + ":" + decryptedPass + System.lineSeparator());
                }
            }
            System.out.println("Decrypted users exported to " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Testing
    public static void main(String[] args) {
        // Register new user
       

        // Validate login
        File loginFile = new File("C:\\Applicants\\Users.txt");
        boolean isValid = isValidLogin(loginFile, "testuser", "12345");
        System.out.println("Login valid: " + isValid);

        // Export decrypted file
        File decryptedOutput = new File("C:\\Applicants\\DecryptedUsers.txt");
        exportDecryptedUsers(loginFile, decryptedOutput);
    }
    
}
