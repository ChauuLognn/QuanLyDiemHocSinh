import java.util.HashMap;
import java.util.Map;

public class QuanLyThuVien {
    // Gọi 2 quản lý con vào đây
    private QuanLyTaiLieu qlTaiLieu;
    private QuanLyDocGia qlDocGia;

    // Map quản lý mượn trả (Mã Tài Liệu -> Mã Độc Giả)
    private Map<String, String> soMuonTra;

    public QuanLyThuVien() {
        this.qlTaiLieu = new QuanLyTaiLieu();
        this.qlDocGia = new QuanLyDocGia();
        this.soMuonTra = new HashMap<>();
    }

    // Getter để Main có thể truy cập vào các quản lý con nếu cần
    public QuanLyTaiLieu getQlTaiLieu() {
        return qlTaiLieu;
    }
    public QuanLyDocGia getQlDocGia() {
        return qlDocGia;
    }

    // --- NGHIỆP VỤ MƯỢN TRẢ (Cần phối hợp cả 2 bên) ---

    public String choMuon(String maDocGia, String maTaiLieu) {
        // 1. Check Độc giả
        if (qlDocGia.timDocGia(maDocGia) == null)
            return "Lỗi: Độc giả không tồn tại!";

        // 2. Check Tài liệu
        TaiLieu tl = qlTaiLieu.timTaiLieu(maTaiLieu);
        if (tl == null)
            return "Lỗi: Tài liệu không tồn tại!";

        if (tl.getSoLuong() <= 0)
            return "Lỗi: Hết sách!";

        // 3. Thực hiện mượn
        tl.setSoLuong(tl.getSoLuong() - 1);
        soMuonTra.put(maTaiLieu, maDocGia);
        return "Mượn thành công!";
    }

    public String traSach(String maTaiLieu) {
        if (!soMuonTra.containsKey(maTaiLieu))
            return "Lỗi: Sách này không có ai mượn!";

        TaiLieu tl = qlTaiLieu.timTaiLieu(maTaiLieu);
        if (tl != null) {
            tl.setSoLuong(tl.getSoLuong() + 1);
            String nguoiMuon = soMuonTra.remove(maTaiLieu); // Xóa khỏi sổ
            return "Đã nhận lại sách từ: " + nguoiMuon;
        }
        return "Lỗi dữ liệu!";
    }

    // --- Wrapper cho chức năng Xóa an toàn ---
    // Lý do viết ở đây: Để kiểm tra sổ mượn trả trước khi gọi lệnh xóa ở lớp con

    public String xoaTaiLieuAnToan(String ma) {
        if (soMuonTra.containsKey(ma))
            return "Đang có người mượn, không thể xóa!";

        if (qlTaiLieu.xoaTaiLieu(ma)) return "Xóa tài liệu thành công.";
        return "Không tìm thấy mã.";
    }

    public String xoaDocGiaAnToan(String ma) {
        if (soMuonTra.containsValue(ma))
            return "Độc giả đang giữ sách, bắt trả trước khi xóa!";

        if (qlDocGia.xoaDocGia(ma)) return "Xóa độc giả thành công.";
        return "Không tìm thấy mã.";
    }
}