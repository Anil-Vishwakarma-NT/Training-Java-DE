package DayOne.FileIO;

import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        String input = "DayOne/FileIO/employee.csv";
        String legacyOutput = "engineering_legacy.csv";
        String modernOutput = "engineering_modern.csv";

        System.out.println("Working Directory: " + System.getProperty("user.dir"));

        List<Employee> legacyFiltered = LegacyIOParser.readAndFilterEmployees(input);
        LegacyIOParser.writeEmployees(legacyOutput,legacyFiltered);

        List<Employee> nioFiltered = ModernNIOParser.readAndFilterEmployees(Paths.get(input));
        ModernNIOParser.writeEmployees(Paths.get(modernOutput),nioFiltered);

        System.out.println("Filtered data written using both legacy and modern methods.");
    }
}
