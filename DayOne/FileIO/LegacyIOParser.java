package DayOne.FileIO;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// using inputStream and BufferedReader
public class LegacyIOParser {

    public static List<Employee> readAndFilterEmployees(String filePath){
        List<Employee> filterdEmpoyees = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath)))){
            System.out.println(br);
            filterdEmpoyees = br.lines().skip(1)
                    .map(line -> {
                        String[] parts = line.split(",");
                        return new Employee(
                                Integer.parseInt(parts[0]),
                                parts[1],
                                parts[2],
                                Double.parseDouble((parts[3]))
                        );
                    } )
                    .filter(emp -> emp.getDepartment().equalsIgnoreCase("Engineering"))
                    .collect(Collectors.toList());

            System.out.println(filterdEmpoyees);

        }catch (IOException e){
            e.printStackTrace();
        }

        return filterdEmpoyees;
    }

    public static void writeEmployees(String filePath, List<Employee> employees){
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter((new FileOutputStream(filePath)))) ){
            writer.write("id,name, department, salary");
            writer.newLine();
            for(Employee emp : employees){
                writer.write(emp.toCSV());
                writer.newLine();
            }

        }catch (IOException e){
            e.printStackTrace();
        }


    }

}
