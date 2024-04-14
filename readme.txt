

1. Tạo db mysql bằng XAMPP
2. Connect tới db MySQL
3. Chạy file db.sql
 ``` Execute db.sql
4. Tạo C:/var/webapp/images và giải nén tất cả hình từ file images.zip vào thư mục này.
5. Cấu hình Tomcat 9
    1. Download Tomcat 9
    2. Add Tomcat Server
    3. Vào Edit Configuration
    4. Add new configuration
    5. Chọn Tomcat Server Local
    6. Deployment → Add → Artifact → *:war exploded
    8. Application context: /
    9. Build lancher
       1. Add -> Build artifact -> *:war exploded
6. Run
    ``` http://localhost:8080/
```
