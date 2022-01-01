import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Employee {
  File file = new File("D:\\OSS\\src\\Employee.csv");

  public Employee() throws FileNotFoundException {}

  public Employee(String fname, String lname, int age, int salary, String phone)
      throws IOException {
    add(lastId(), fname, lname, age, salary, phone, file);
  }

  public void insert(String fname, String lname, int age, int salary, String phone)
      throws IOException {
    add(lastId(), fname, lname, age, salary, phone, file);
  }

  public String get(int id) throws FileNotFoundException {
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
        return line;
      }
    }
    return "No employee exist with id " + id;
  }

  /**
   * loop over the original file and write all lines in temp file until find the wanted row then
   * insert the new row instead of this row. then delete the original row and rename the temp to the
   * name of original file.
   */
  public void update(int id, String fname, String lname, int age, int salary, String phone)
      throws IOException {
    String str =
        id + " , " + fname + " , " + lname + " , " + age + " , " + salary + " , " + phone + "\n";
    Scanner input = new Scanner(file);
    // create temp file.
    File temp = new File("D:\\OSS\\src\\temp.csv");
    FileWriter writer = new FileWriter(temp, true);
    temp.createNewFile();
    boolean flag = false;
    // loop over the original file.

    while (input.hasNext()) {
      int iterator = 0;
      String s = "";
      String line = input.nextLine();
      // check on the row's id.
      while (line.charAt(iterator) != ' ') {
        s += line.charAt(iterator);
        iterator++;
      }
      if (Integer.parseInt(s) == id) {
        writer.write(str);
        flag = true;
        continue;
      }
      writer.write(line + "\n");
    }
    if (!flag) writer.write(str);
    input.close();
    writer.close();
    file.delete();
    temp.renameTo(file);
  }

  public void delete(int id) throws IOException {
    Scanner input = new Scanner(file);

    // create temp file.
    File temp = new File("D:\\OSS\\src\\temp.csv");
    FileWriter writer = new FileWriter(temp, true);
    temp.createNewFile();

    // loop over the original file.
    while (input.hasNext()) {
      int iterator = 0;
      String s = "";
      String line = input.nextLine();
      // check on the row's id.
      while (line.charAt(iterator) != ' ') {
        s += line.charAt(iterator);
        iterator++;
      }
      if (Integer.parseInt(s) == id) {
        continue;
      }
      writer.write(line + "\n");
    }
    input.close();
    writer.close();
    file.delete();
    temp.renameTo(file);
  }

  private int lastId() throws FileNotFoundException {
    Scanner input = new Scanner(file);
    int i = 0;
    String x = "";
    String s = "";

    while (input.hasNext()) {
      s = input.nextLine();
    }

    // if file is empty
    if (s.length() < 1) return 1;

    // if file not empty -> return the last row's id + 1
    while (s.charAt(i) != ' ') {
      x += s.charAt(i);
      i++;
    }
    return Integer.parseInt(x) + 1;
  }

  private void add(int id, String fname, String lname, int age, int salary, String phone, File file)
      throws IOException {
    FileWriter writer = new FileWriter(file, true);
    writer.write(
        id + " , " + fname + " , " + lname + " , " + age + " , " + salary + " , " + phone + "\n");
    writer.close();
  }
}
