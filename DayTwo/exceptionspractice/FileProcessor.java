package DayTwo.exceptionspractice;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {
    public static void main(String[] args) {
        String fileName = "DayTwo/exceptionspractice/data";
        BufferedReader reader = null;

        try{
            reader = new BufferedReader((new FileReader(fileName)));
            String line;
            int sum = 0;
            boolean hasData = false;

            while((line = reader.readLine()) != null){
                hasData = true;
                try{
                    int value = Integer.parseInt((line.trim()));
                    sum += value;
                }catch (NumberFormatException e){
                    System.err.println("Skipping invalid number: "+line);
                }
            }

            if(!hasData){
                throw new EmptyFileException(("File is empty!"));
            }

            System.out.println("Total sum:" + sum);

        }catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        } catch (EmptyFileException e) {
            System.err.println("Custom Exception: " + e.getMessage());
        } finally {
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.err.println("Failed to close reader: " + e.getMessage());
            }
        }
    }
}
