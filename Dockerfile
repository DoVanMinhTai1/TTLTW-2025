# Sử dụng image Tomcat 10 chính thức với JDK 17
FROM tomcat:10.1-jdk17

# Xóa ứng dụng mặc định
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy file WAR vào thư mục webapps, đổi tên thành ROOT.war để truy cập tại /
COPY target/*.war /usr/local/tomcat/webapps/ROOT.war

# Mở cổng 8080
EXPOSE 8080

# Lệnh chạy Tomcat
CMD ["catalina.sh", "run"]
