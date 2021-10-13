package vn.tea.app.utils;

public class Constants {
	public static final int DA_XOA = 1; // đã xóa
	public static final int CHUA_XOA = 0; // chưa xóa
	
	public static final int DINH_KEM_1_FILE = 0; // đính kèm 1 file
    public static final int DINH_KEM_NHIEU_FILE = 1; // đính kèm nhiều file
    
    // api doanh nghiep
    public static final String URL_DOANHNGHIEP = "http://dkkd.dpi.danang.gov.vn/ruiro-api/doanhnghieps";
    public static final String userNameDoanhNgiep = "Giaothongvantai";
    public static final String passWordDoanhNgiep = "Csdl-Tuvanthicong@GTVT202008";
    
    // api cong trinh 
    public static final String URL_TOKEN_CONGTRINH = "https://apiegov.danang.gov.vn/v1/auth/uaa/token";
    public static final String URL_CONGTRINH = "https://csdlgtvt.danang.gov.vn:8080/sgtvt/quanly/hoso/danhmuc/congtrinh";
    public static final String userNameCongTrinh = "test_client";
    public static final String passWordCongTrinh = "TestClient2@19";
    
    // Thang diem danh gia
    public static final String diem_Kem = "Kém";
    public static final String diem_TrungBinhKem = "Trung bình yếu";
    public static final String diem_TrungBinh = "Trung bình";
    public static final String diem_Kha = "Khá";
    public static final String diem_Tot = "Tốt";
    public static final String diem_RatTot = "Rất tốt";    
}