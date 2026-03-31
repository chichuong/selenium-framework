# Tuần 9 - Thực hành Kiểm thử Tự động (Automation Testing)

Dự án này bao gồm các bài tập và ví dụ thực hành về kiểm thử tự động (Automation Testing) sử dụng **Java**, **Selenium WebDriver** và **TestNG**. Dự án được chia thành 6 bài tập (từ `bai1` đến `bai6`), mỗi bài tập tập trung vào một khía cạnh cụ thể của việc xây dựng framework kiểm thử tự động.

## Cấu trúc dự án

Dự án bao gồm 6 module/thư mục tương ứng với 6 phần thực hành:

- **bai1**: Các bài test cơ bản với Selenium (Ví dụ: Đăng nhập).
- **bai2**: Triển khai và áp dụng **Page Object Model (POM)** trong kiểm thử (Ví dụ: Trang Inventory).
- **bai3**: Kiểm thử hướng dữ liệu (Data-Driven Testing) đọc test data từ file **Excel** sử dụng thư viện **Apache POI**.
- **bai4**: Xây dựng Java POJO (Models) và Factory Pattern để tạo dữ liệu kiểm thử (Test Data Factory).
- **bai5**: Xây dựng cấu trúc Framework tự động hóa với `BaseTest`, `BasePage` để tối ưu hóa việc quản lý và khởi tạo WebDriver.
- **bai6**: Xử lý các test case không ổn định (Flaky Tests) bằng cách áp dụng **RetryAnalyzer** của TestNG.

## Yêu cầu môi trường

Để cấu hình và chạy dự án này trên môi trường local, bạn cần cài đặt:

1. **Java Development Kit (JDK)**: Phiên bản 11 hoặc mới hơn.
2. **Apache Maven**: Để quản lý dependencies và build project.
3. **Trình duyệt web**: (Google Chrome, Firefox, v.v.).
4. **IDE**: IntelliJ IDEA (khuyến khích), Eclipse, hoặc VS Code có extension Java.

*(Lưu ý: Selenium phiên bản 4.6.0 trở lên sử dụng công cụ `Selenium Manager` nên bạn không cần phải tải và thiết lập biến môi trường thủ công cho các WebDriver driver file như ChromeDriver hay GeckoDriver).*

## Hướng dẫn chạy local

Mỗi thư mục từ `bai1` đến `bai6` là một Maven project độc lập. Để chạy hoặc chỉnh sửa mã cho một phần cụ thể, hãy làm theo các cách sau.

### Cách 1: Chạy bằng IDE (Tốt nhất cho Phát triển & Debug)

1. Mở **IntelliJ IDEA** (hoặc IDE yêu thích của bạn).
2. Mở thư mục bài bạn muốn chạy. Chọn **File > Open...** và chọn cụ thể thư mục chứa bài tập, ví dụ: `tuan9/bai3` (để cho IDE nhận diện root là chứa file `pom.xml`), hoặc mở nguyên thư mục `tuan9` và import từng `pom.xml` của các bài thành các Maven Module.
3. Đợi IDE tải các thư viện Maven và lập chỉ mục (sync).
4. Mở file thư mục `src/test/java` và tìm đến file test bạn muốn chạy.
5. Truy cập file test (VD: `FlakySimulationTest.java`), click vào biểu tượng mũi tên xanh lá cây bên lề dòng khai báo Class/Method chọn **Run** để khởi chạy test case.

### Cách 2: Chạy thông qua dòng lệnh bằng Maven (CLI)

1. Mở Terminal / PowerShell / Command Prompt.
2. Di chuyển ("**cd**") vào thư mục của bài cụ thể bạn muốn chạy (có chứa file `pom.xml`).
   ```bash
   cd d:\hoc\kiemthu\doc\tuan9\bai3
   ```
3. Chạy lệnh:
   ```bash
   mvn clean test
   ```
4. Maven sẽ tải plugins, libraries (lần đầu sẽ tốn chút thời gian) và sau đó tự động kích hoạt TestNG chạy toàn bộ test có trong thư mục test. Kết quả Pass/Fail sẽ in ra trên Console.

## Các công cụ/thư viện chính (Dependencies)

Các dự án trên đều sử dụng các thư viện phổ biến sau đã được định nghĩa bên trong file `pom.xml`:
- `org.seleniumhq.selenium : selenium-java`
- `org.testng : testng`
- `org.apache.poi : poi-ooxml` (Để xử lý file dữ liệu Excel)
- Logging `log4j` (Cho in the log)
