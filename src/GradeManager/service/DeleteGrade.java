package GradeManager.service;

import GradeManager.data.GradeDatabase;

import java.util.Scanner;

public class DeleteGrade {
    private GradeDatabase gradeDB = GradeDatabase.getGradeDB();
    Scanner sc = new Scanner(System.in);
    String studentID;

    public void delete(){
        System.out.print("Nhập mã học sinh: ");
        studentID = sc.nextLine();

        if (!gradeDB.getGrades().containsKey(studentID)){
            System.out.println("Không tìm thấy học sinh có mã " + studentID);
            return;
        }

        gradeDB.deleteGrade(studentID);
        System.out.println("Xóa điểm thành công!");
    }
}
