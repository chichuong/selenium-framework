## Cấu hình GitHub Pages với Allure Report

### Các bước bật GitHub Pages:

1. **Đi tới Settings của Repository**
   - Vào repository trên GitHub
   - Chọn tab **Settings** (dùng quyền Admin)

2. **Tìm mục Pages**
   - Trong menu bên trái, tìm **Pages** (phần "Code and automation")

3. **Cấu hình Source**
   - **Source**: Chọn "Deploy from a branch"
   - **Branch**: Chọn `gh-pages` (branch này sẽ được tự động tạo sau lần action đầu)
   - Chọn folder `/root` (mặc định)
   - Click **Save**

4. **Chờ deployment**
   - GitHub Pages sẽ bắt đầu deploy
   - Sau 1-2 phút, bạn sẽ thấy URL: `https://{username}.github.io/{repo}/`

### Workflow tự động:

- Mỗi khi code push lên `main` branch hoặc theo schedule (2AM Thứ 2-6):
  1. **Job `test`** - Chạy Selenium tests với matrix (Chrome + Firefox)
  2. Kết quả Allure được upload thành artifact
  3. **Job `publish-report`** - Download artifacts từ cả 2 browsers
  4. Tạo unified Allure Report
  5. Push lên branch `gh-pages` → GitHub Pages tự động update

### Xem Allure Report:

- URL: `https://{username}.github.io/{repo}/allure-report/`

### Troubleshooting:

- **Branch `gh-pages` không tồn tại?** 
  - Workflow cần chạy ít nhất 1 lần thành công để tạo branch
  - Hoặc tạo thủ công: `git checkout --orphan gh-pages && git rm -rf .`

- **Report không update?**
  - Kiểm tra Actions trong GitHub (có failed job không)
  - Đảm bảo Allure plugin đã cài trong `pom.xml` (xem bai6)

- **Permission denied?**
  - Cần repository token: GitHub sẽ tự động inject `GITHUB_TOKEN`
  - Không cần setup thêm secrets
