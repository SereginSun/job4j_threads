package ru.job4j.concurrent;

import java.util.List;

public class ShareNotSafe {
    public static void main(String[] args) throws InterruptedException {
        UserCache cache = new UserCache();
        User firstUser = User.of("name");
        User secondUser = User.of("second name");
        cache.add(firstUser);
        cache.add(secondUser);
        Thread first = new Thread(
                () -> firstUser.setName("rename")
        );
        first.start();
        first.join();
        System.out.println(cache.findById(1).getName());
        List<User> list = cache.findAll();
        list.stream().map(User::getName).forEach(System.out::println);
    }
}
