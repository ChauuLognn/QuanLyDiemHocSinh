import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        QuanLyThuVien heThong = new QuanLyThuVien();
        Scanner scanner = new Scanner(System.in);

        // --- Dữ liệu mẫu ---
        heThong.getQlDocGia().themDocGia(new DocGia("Nguyen Van A", 2000, "DG1"));
        heThong.getQlTaiLieu().themTaiLieu(new Sach("S1", "Lap Trinh Java", "NXB GD", 5, "Nam Long", 100));
        heThong.getQlTaiLieu().themTaiLieu(new Video("V1", "Hoc Python", "Youtube", 2, 120));

        while (true) {
            // Giữ nguyên kiểu hiển thị Menu gọn như code cũ của bạn
            System.out.println("\n=======================================================");
            System.out.println("1. Xem Tài Liệu | 2. Thêm Sách    | 3. Sửa Tài Liệu");
            System.out.println("4. Xem Độc Giả  | 5. Thêm Độc Giả | 6. Xóa Độc Giả");
            System.out.println("7. Mượn Sách    | 8. Trả Sách     | 9. Xóa Tài Liệu");
            System.out.print("=> Chọn chức năng: ");

            int chon = scanner.nextInt();
            scanner.nextLine(); // Xử lý trôi lệnh

            switch (chon) {
                // --- NHÓM TÀI LIỆU ---
                case 1:
                    heThong.getQlTaiLieu().hienThiKho();
                    break;

                case 2: // Thêm nhanh (Demo)
                    heThong.getQlTaiLieu().themTaiLieu(new Sach("S2", "Sách Mới", "NXB Trẻ", 10, "Tác Giả Mới", 200));
                    System.out.println("-> Đã thêm Sách S2 (Demo).");
                    break;

                case 3: // SỬA THÔNG TIN (Code mới cập nhật)
                    System.out.print("Nhập mã tài liệu cần sửa: ");
                    String maSua = scanner.nextLine();

                    // Tìm xem nó là cái gì trước
                    TaiLieu tlTimThay = heThong.getQlTaiLieu().timTaiLieu(maSua);

                    if (tlTimThay == null) {
                        System.out.println("Lỗi: Không tìm thấy mã tài liệu này!");
                    }
                    else if (tlTimThay instanceof Sach) {
                        System.out.println("--- ĐANG SỬA SÁCH: " + tlTimThay.getTenTaiLieu() + " ---");
                        System.out.print("Nhập Tên mới: "); String ten = scanner.nextLine();
                        System.out.print("Nhập NXB mới: "); String nxb = scanner.nextLine();
                        System.out.print("Nhập Số lượng mới: "); int sl = scanner.nextInt(); scanner.nextLine();
                        System.out.print("Nhập Tác giả mới: "); String tg = scanner.nextLine();
                        System.out.print("Nhập Số trang mới: "); int st = scanner.nextInt(); scanner.nextLine();

                        // Gọi hàm sửa Sách
                        if (heThong.getQlTaiLieu().suaSach(maSua, ten, nxb, sl, tg, st)) {
                            System.out.println("-> Cập nhật Sách thành công!");
                        }
                    }
                    else if (tlTimThay instanceof Video) {
                        System.out.println("--- ĐANG SỬA VIDEO ---");
                        System.out.print("Nhập Tên mới: "); String ten = scanner.nextLine();
                        System.out.print("Nhập NXB mới: "); String nxb = scanner.nextLine();
                        System.out.print("Nhập Số lượng mới: "); int sl = scanner.nextInt(); scanner.nextLine();
                        System.out.print("Nhập Thời lượng mới: "); int time = scanner.nextInt(); scanner.nextLine();

                        // Gọi hàm sửa Video
                        if (heThong.getQlTaiLieu().suaVideo(maSua, ten, nxb, sl, time)) {
                            System.out.println("-> Cập nhật Video thành công!");
                        }
                    }
                    break;

                // --- NHÓM ĐỘC GIẢ ---
                case 4: heThong.getQlDocGia().hienThiDS(); break;

                case 5:
                    heThong.getQlDocGia().themDocGia(new DocGia("B", 1999, "DG2"));
                    System.out.println("Đã thêm DG2 demo.");
                    break;

                case 6:
                    System.out.print("Mã ĐG xóa: ");
                    System.out.println(heThong.xoaDocGiaAnToan(scanner.nextLine()));
                    break;

                // --- NHÓM MƯỢN TRẢ ---
                case 7:
                    System.out.print("Nhập Mã ĐG: "); String dg = scanner.nextLine();
                    System.out.print("Nhập Mã Sách: "); String s = scanner.nextLine();
                    System.out.println(heThong.choMuon(dg, s));
                    break;

                case 8:
                    System.out.print("Nhập Mã Sách trả: ");
                    System.out.println(heThong.traSach(scanner.nextLine()));
                    break;

                // --- CHỨC NĂNG MỚI: XÓA TÀI LIỆU ---
                case 9:
                    System.out.print("Nhập Mã tài liệu cần xóa: ");
                    String maXoa = scanner.nextLine();
                    System.out.println(heThong.xoaTaiLieuAnToan(maXoa));
                    break;

                default:
                    System.out.println("Thoát chương trình.");
                    return;
            }
        }
    }
}