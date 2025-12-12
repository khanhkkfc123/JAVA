package Bai7.Run;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import Bai7.data.Student;

public class run {
    public static Scanner sc = new Scanner(System.in);// bien toan cuc
    public static ArrayList<Student> sv = new ArrayList<>();
    public static boolean check = false;

    public static void main(String[] args) {
        // HAM KHAI BAO VA KHOI CHAY

        enterStudent();
    }

    // HAM THONG BAO MENU RA NGOAI VA GOI CAC HAM
    public static void enterStudent() {
        int luaChon = 0;
        showTime();
        System.out.println("The list Student at FPT university! ");

        do {
            // thanh menu
            System.out.println("Menu: ");
            System.out.println("1.ADD The student at list");
            System.out.println("2.Find profile student by id ");
            System.out.println("3.Show all list student: ");
            System.out.println("4.Delete student: ");
            System.out.println("5.Write in file : ");
            System.out.println("6.Reading   file : ");
            System.out.println("7.Update information are student : ");
            System.out.println("0.EXIT! ");
            // NHAP lua chon de thuc hien yeu cau
            System.out.printf("pleas enter your choose: ");
            luaChon = Integer.parseInt(sc.nextLine());

            if (luaChon == 1)
                // luu sinh vien
                saveListStudent();
            else if (luaChon == 2) {
                // tim information cua sv bang id
                System.out.printf("Enter id: ");
                String id = sc.nextLine().toUpperCase();
                findStudent(id);
            } else if (luaChon == 3)
                // hien danh sach sv
                showAllStudent();
            else if (luaChon == 4)
                delete();
            else if (luaChon == 5)
                writeToFile();
            else if (luaChon == 6)
                readFromFile();
            else if (luaChon == 7)
                upDate();
            else if (luaChon == 0)
                // thoat
                break;
        } while (luaChon != 0);

    }

    // ham show danh sach sinh vien
    public static void showAllStudent() {
        for (Student student : sv) {
            System.out.println(student);
        }

    }

    public static void exception(double n) {
        try {

        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    // ham tim kiem thong tin sinh vien bang id
    public static void findStudent(String id) {
        for (Student student : sv) {
            if (student.getId().equals(id)) {
                System.out.println(student);
                check = true;
                break;
            }
        }
        if (check == false)
            System.out.println("ID is empty!");

    }

    // ham luu danh sach sv
    public static void saveListStudent() {
        System.out.print("Enter the number of students to save: ");
        int n = Integer.parseInt(sc.nextLine());

        for (int i = 1; i <= n; i++) {
            System.out.println("ST: " + i);

            // Nhập ID + kiểm tra trùng
            String id;
            int yob = 0;
            double gpa = 0.0;
            while (true) {
                System.out.print("Enter student ID: ");
                id = sc.nextLine().toUpperCase();

                if (checkId(id) == null) {
                    break; // ID hợp lệ
                }
                System.out.println("❌ ID already exists! Try again.");
            }

            // Nhập tiếp thông tin
            System.out.print("Enter student name: ");
            String name = sc.nextLine();

            while (true) {
                try {
                    System.out.print("Enter the student year  of birth: ");
                    yob = Integer.parseInt(sc.nextLine());
                    if (yob > 1900 && yob < 2025)
                        break;
                    else
                        System.out.println("Errors logic! Pleas enter again:  ");
                } catch (Exception e) {
                    System.out.println("Errors syntax! Pleas enter again:  ");

                }

            }
            while (true) {
                try {
                    System.out.print("Enter GPA: ");
                    gpa = Double.parseDouble(sc.nextLine());
                    if (gpa > 0 && gpa <= 10)
                        break;
                    else
                        System.out.println("Errors logic! Pleas enter again:  ");
                } catch (Exception e) {

                    System.out.println("Errors syntax! Pleas enter again:  ");
                }
            }

            sv.add(new Student(id, name, yob, gpa));
        }
    }

    public static void delete() {
        System.out.printf("Enter  your id  want delete: ");
        String id = sc.nextLine().toUpperCase();
        Student student = checkId(id);
        if (student != null) {
            sv.remove((student));
            System.out.println("Delete access full!");
        } else
            System.out.println("ID is empty! ");
    }

    public static Student checkId(String id) {
        for (Student student : sv) {
            if (student.getId().equals(id)) {
                check = true;
                return student;
            }
        }
        return null;
    }

    public static void readFromFile() {
        try {
            sv.clear(); // xóa dữ liệu cũ
            File file = new File("students.txt");
            if (!file.exists())
                return;

            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] arr = line.split("\\|");

                String id = arr[0];
                String name = arr[1];
                int yob = Integer.parseInt(arr[2]);
                double gpa = Double.parseDouble(arr[3]);

                sv.add(new Student(id, name, yob, gpa));
            }

            reader.close();
            System.out.println("✔ Loaded data from file!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeToFile() {
        try {
            FileWriter fw = new FileWriter("students.txt");
            for (Student s : sv) {
                fw.write(s.getId() + "|" + s.getName() + "|" + s.getYob() + "|" + s.getGpa() + "\n");
            }
            fw.close();
            System.out.println("✔ Saved to file successfully!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showTime() {

        LocalTime time = LocalTime.now();
        DateTimeFormatter f = DateTimeFormatter.ofPattern("HH:mm");
        System.out.println("TIME: " + time.format(f));

    }

    public static void upDate() {
        int yob;
        String id;
        double gpa;
        System.out.printf("Enter id need update: ");
        id = sc.nextLine().toUpperCase();
        Student st = checkId(id);
        if (checkId(id) != null) {
            System.out.print("Enter student name: ");
            String name = sc.nextLine();

            while (true) {
                try {
                    System.out.print("Enter birth year: ");
                    yob = Integer.parseInt(sc.nextLine());
                    if (yob > 1900 && yob < 2025)
                        break;
                    else
                        System.out.println("Errors logic! ");
                } catch (Exception e) {
                    System.out.println("Errors syntax");
                }
            }

            while (true) {
                try {
                    System.out.print("Enter GPA: ");
                    gpa = Double.parseDouble(sc.nextLine());
                    if (gpa >= 0 && gpa <= 10)
                        break;
                    else
                        System.out.println("Errors logic! ");
                } catch (Exception e) {
                    System.out.println("Errors syntax");
                }
            }
            st.setName(name);
            st.setYob(yob);
            st.setGpa(gpa);
        } else {
            System.out.println("❌ ID does not exist!");
            return;
        }
    }
}
