package vn.edu.iuh.fit.bai1;

import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(String message);
}

// Subject interface
interface Subject {
    void attach(Observer observer);
    void detach(Observer observer);
    void notifyObservers();
}

// Concrete Subject (Stock)
class Stock implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String name;
    private double price;

    public Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update("📢 Cổ phiếu " + name + " đã thay đổi giá: " + price);
        }
    }
}

// Concrete Observer (Investor)
class Investor implements Observer {
    private String name;

    public Investor(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("👤 Nhà đầu tư " + name + " nhận thông báo: " + message);
    }
}

// Concrete Subject (Task)
class Task implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String name;
    private String status;

    public Task(String name, String status) {
        this.name = name;
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObservers();
    }

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update("📢 Công việc " + name + " đã chuyển sang trạng thái: " + status);
        }
    }
}

// Concrete Observer (Team Member)
class TeamMember implements Observer {
    private String name;

    public TeamMember(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("👤 Thành viên " + name + " nhận thông báo: " + message);
    }
}

// Main class để kiểm thử
public class ObserverPatternTest {
    public static void main(String[] args) {
        // Ứng dụng vào hệ thống cổ phiếu
        Stock stockA = new Stock("AAPL", 150.0);
        Investor investor1 = new Investor("Alice");
        Investor investor2 = new Investor("Bob");

        stockA.attach(investor1);
        stockA.attach(investor2);
        stockA.setPrice(155.0);

        System.out.println("\n-----------------\n");

        // Ứng dụng vào hệ thống quản lý công việc nhóm
        Task task1 = new Task("Thiết kế UI", "Chưa bắt đầu");
        TeamMember member1 = new TeamMember("David");
        TeamMember member2 = new TeamMember("Emma");

        task1.attach(member1);
        task1.attach(member2);
        task1.setStatus("Đang thực hiện");
    }
}

