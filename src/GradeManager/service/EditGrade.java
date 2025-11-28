package GradeManager.service;

import GradeManager.Grade;
import GradeManager.data.GradeDatabase;
import StudentManager.Student;
import StudentManager.data.StudentDatabase;

import java.util.Scanner;

public class EditGrade {
    private GradeDatabase gradeDB = GradeDatabase.getGradeDB();

    Scanner sc = new Scanner(System.in);
    String studentID;

    public void edit(){
        System.out.print("Nhập mã học sinh: ");
        studentID = sc.nextLine();


        if (!gradeDB.getGrades().containsKey(studentID)){
            System.out.println("Không tìm thấy học sinh có mã " + studentID);
            return;
        }

        //Lấy điểm hiện tại của học sinh
        Grade grade = gradeDB.getGradeByStudentID(studentID);

        //Nếu điểm ban đầu là null thì tạo mới
        if (grade == null) {
            grade = new Grade(studentID, 0, 0, 0);
        }
        int choose;
        do{
            System.out.print("Chọn điểm muốn sửa:\n" +
                    "1. Điểm thường xuyên\n" +
                    "2. Điểm giữa kì\n" +
                    "3. Điểm cuối kì\n" +
                    "4. Thoát\n" +
                    "Chọn: ");
            choose = sc.nextInt();
            sc.nextLine();
            switch (choose){
                case 1 -> {
                    System.out.print("Nhập điểm thường xuyên mới: ");
                    double newRegularScore = sc.nextDouble();
                    sc.nextLine();
                    grade.setRegularScore(newRegularScore);
                    gradeDB.addOrUpdateGrade(grade);
                    System.out.println("Cập nhật điểm thường xuyên thành công!");
                }
            }
        }while (choose != 4);

    }
}
