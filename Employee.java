import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Employee {
    File file = new File("Employee.csv");

    public Employee() throws FileNotFoundException {

    }

    public Employee(String fname, String lname, int age, int salary, String phone) throws IOException {
        add(fname, lname, age, salary, phone);
    }

    public void insert(String fname, String lname, int age, int salary, String phone) throws IOException {
        add(fname, lname, age, salary, phone);
    }

    private void add(String fname, String lname, int age, int salary, String phone) throws IOException {
        FileWriter writer = new FileWriter(file, true);
        writer.write(lastId() + " , " + fname + " , " + lname + " , " + age + " , " + salary + " , " + phone + "\n");
        writer.close();
    }

    public void get (int id) throws FileNotFoundException {
        Scanner input = new Scanner(file);
        while (input.hasNext()) {
            String x = "";
            int i = 0;
            String line = input.nextLine();
            while (line.charAt(i) != ' ') {
                x += line.charAt(i);
                i++;
            }
            if (Integer.parseInt(x) == id) {
                System.out.println(line);
            }
        }

    }

    public void update(int id, String fname, String lname, int age, int salary, String phone) throws IOException {
        Scanner input = new Scanner(file);
        while (input.hasNext()) {
            String x = "";
            int i = 0;
            String line = input.nextLine();
            while (line.charAt(i) != ' ') {
                x += line.charAt(i);
                i++;
            }
            if (Integer.parseInt(x) == id) {
                add(fname, lname, age, salary, phone);
                System.out.println(line);
            }
        }
    }

    public int lastId() throws FileNotFoundException {
        Scanner input = new Scanner(file);
        int i = 0;
        String x = "";
        String s = "";
        while (input.hasNext()) {
            s = input.nextLine();
        }
        while (s.charAt(i) != ' ') {
            x += s.charAt(i);
            i++;
        }
        return Integer.parseInt(x) + 1;
    }


}
