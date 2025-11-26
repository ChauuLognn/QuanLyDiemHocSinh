package StudentManager.service;


import java.util.Scanner;
import StudentManager.Student;
import StudentManager.data.StudentDatabase;

public class deleteStudent {
    private StudentDatabase studentDB = StudentDatabase.getStudentDB();
    String id;
    Scanner sc = new Scanner(System.in);


    //Xóa sinh viên
    public void delete(){
        //Hiển thị danh sách học sinh trước khi xóa
        studentDB.showAllStudents();

        //Nhập ID học sinh muốn xóa
        System.out.print("Nhập ID học sinh muốn xóa: ");
        id = sc.nextLine();
        Student s = studentDB.findByID(id);
        if (s != null){
            System.out.print("Chắc chắn muốn xóa học sinh "+ s + "?\nY/N: " );
            String answer = sc.nextLine();
            while (true){
                if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("yes")) {
                    studentDB.deleteStudent(id);
                    System.out.print("Xóa học sinh thành công!");
                    break;
                }
                else if (answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no")){
                    System.out.print("Hủy xóa học sinh thành công!");
                    break;
                }
                else {
                    System.out.print("Y/N: ");
                    answer = sc.nextLine();
                }
            }
        }
        else {
            System.out.println("Không tồn tại!");
        }
    }
}
