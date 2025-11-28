package GradeManager.service;

import GradeManager.Grade;
import GradeManager.data.GradeDatabase;

import java.util.Scanner;

public class AddGrade {
    private GradeDatabase gradeDB = GradeDatabase.getGradeDB();
    String studentID;
    private double regularScore;
    private double midtermScore;
    private double finalScore;

    Scanner sc = new Scanner(System.in);

    public void addOrUpdate(){
        System.out.print("Nhập mã học sinh: ");
        studentID = sc.nextLine();
        System.out.print("Nhập điểm thường xuyên: ");
        regularScore = sc.nextDouble();
        System.out.print("Nhập điểm giữa kì: ");
        midtermScore = sc.nextDouble();
        System.out.print("Nhập điểm cuối kì: ");
        finalScore = sc.nextDouble();
        sc.nextLine();

        Grade newGrade = new Grade(studentID, regularScore, midtermScore, finalScore);
        gradeDB.addOrUpdateGrade(newGrade);

        System.out.println("Cập nhật điểm thành công!");
    }
}
