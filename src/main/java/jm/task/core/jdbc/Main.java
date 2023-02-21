package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Freddie", "Mercury", (byte) 45);
        userService.saveUser("John", "Deacon", (byte) 71);
        userService.saveUser("Roger", "Taylor", (byte) 73);
        userService.saveUser("Brian", "May", (byte) 75);
        userService.getAllUsers();
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
