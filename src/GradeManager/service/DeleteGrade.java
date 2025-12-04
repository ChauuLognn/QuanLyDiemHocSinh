package GradeManager.service;

import GradeManager.data.GradeDatabase;
import Exception.NotFoundException;

import java.util.Scanner;

public class DeleteGrade {
    private GradeDatabase gradeDB = GradeDatabase.getGradeDB();
    Scanner sc = new Scanner(System.in);
    String studentID;

    public void delete(){
        System.out.print("Nhập mã học sinh: ");
        studentID = sc.nextLine();

        try {
            gradeDB.deleteGrade(studentID);
            System.out.println("Xóa điểm thành công!");
        } catch (NotFoundException e){
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}
