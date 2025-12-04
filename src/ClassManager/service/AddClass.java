package ClassManager.service;

import ClassManager.Classes;
import StudentManager.data.StudentDatabase;
import ClassManager.data.ClassDatabase;
import Exception.*;

import java.util.Scanner;

public class AddClass {
    private ClassDatabase classDB = ClassDatabase.getClassDB();
    private StudentDatabase studentDB = StudentDatabase.getStudentDB();
    Scanner sc = new Scanner(System.in);

    //Thêm lớp mới
    public void add(){
        String newClassName = "";
        String newClassID = "";
        boolean isValid = false;

        while (!isValid){
            System.out.print("Nhập tên lớp mới: ");
            newClassName = sc.nextLine();
            try {
                Validator.validateName(newClassName);
                isValid = true;
            } catch (InvalidNameException e){
                System.out.println("Lỗi: " + e.getMessage());
            }
        }
        isValid = false;

        while (!isValid) {
            System.out.print("Nhập mã lớp mới: ");
            newClassID = sc.nextLine();
            try {
                // Kiểm tra xem mã lớp đã tồn tại chưa (logic bổ sung)
                if (classDB.findClassByID(newClassID) != null) {
                    throw new InvalidIDException("Mã lớp đã tồn tại. Vui lòng nhập mã khác.");
                }

                Validator.validateID(newClassID);
                isValid = true;
            } catch (InvalidIDException e) {
                System.out.println("Lỗi: " + e.getMessage());
            }
        }

        // 3. Thêm lớp nếu cả hai thông tin đều hợp lệ
        Classes newClass = new Classes(newClassName, newClassID);
        classDB.addNewClass(newClass);
        System.out.println("Thêm lớp mới thành công!");
    }



}
