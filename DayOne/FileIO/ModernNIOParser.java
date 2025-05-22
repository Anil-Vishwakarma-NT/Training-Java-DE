package DayOne.FileIO;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ModernNIOParser {

    public static List<Employee> readAndFilterEmployees(Path path){
        List<Employee> filteredEmployees = null;
        System.out.println(path);
        try(Stream<String> lines = Files.lines(path)){
            filteredEmployees = lines
                    .skip(1)
                    .map(line -> {
                        String[] parts = line.split(",");
                          Employee emp = new Employee(
                                Integer.parseInt(parts[0]),
                                parts[1],
                                parts[2],
                                Double.parseDouble(parts[3])
                        );
                        return emp;

                    })
                    .filter(emp -> emp.getDepartment().equalsIgnoreCase("Engineering"))
                    .collect(Collectors.toList());

        }catch (IOException e){
            e.printStackTrace();
        }
        return filteredEmployees;
    }

    public static void writeEmployees(Path outputPath, List<Employee> employees){
        List<String> lines = employees.stream()
                .map(Employee :: toCSV)
                .collect(Collectors.toList());

        lines.add(0,"id,name,department,salary"); // adding header

        try{
            Files.write(outputPath, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
