package vn.edu.iuh.fit.bai1;

import java.util.ArrayList;
import java.util.List;

// Component - Giao diện chung cho File và Directory
interface FileSystemEntity {
    String getName();
    void showInfo();
    default void add(FileSystemEntity entity) {
        throw new UnsupportedOperationException("Không thể thêm vào phần tử này");
    }
    default void remove(FileSystemEntity entity) {
        throw new UnsupportedOperationException("Không thể xóa phần tử này");
    }
}

// Leaf - Tập tin, không thể chứa thực thể con
class File implements FileSystemEntity {
    private String name;
    private String content;

    public File(String name, String content) {
        this.name = name;
        this.content = content;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void showInfo() {
        System.out.println("📄 File: " + name + " (Content: " + content + ")");
    }
}

// Composite - Thư mục có thể chứa tập tin hoặc thư mục con
class Directory implements FileSystemEntity {
    private String name;
    private List<FileSystemEntity> children = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void add(FileSystemEntity entity) {
        children.add(entity);
    }

    @Override
    public void remove(FileSystemEntity entity) {
        children.remove(entity);
    }

    @Override
    public void showInfo() {
        System.out.println("📂 Directory: " + name);
        for (FileSystemEntity entity : children) {
            entity.showInfo();
        }
    }
}

// Main - Kiểm thử hệ thống
public class FileSystemTest {
    public static void main(String[] args) {
        // Tạo tập tin
        File file1 = new File("Resume.pdf", "CV của tôi");
        File file2 = new File("Project.docx", "Dự án chi tiết");

        // Tạo thư mục
        Directory root = new Directory("Root");
        Directory documents = new Directory("Documents");
        Directory images = new Directory("Images");

        // Xây dựng cấu trúc cây
        documents.add(file1);
        documents.add(file2);
        root.add(documents);
        root.add(images);

        // Hiển thị thông tin cây thư mục
        root.showInfo();
    }
}
