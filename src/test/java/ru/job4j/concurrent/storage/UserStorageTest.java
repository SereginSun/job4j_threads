package ru.job4j.concurrent.storage;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStorageTest {

    @Test
    public void whenTransferIn2Threads() throws InterruptedException {
        User first = new User(1, 200);
        User second = new User(2, 300);
        UserStorage store = new UserStorage();
        store.add(first);
        store.add(second);
        Thread task1 = new Thread(() -> store.transfer(1, 2, 200));
        Thread task2 = new Thread(() -> {
            store.transfer(2, 1, 400);
            store.update(first);
            store.update(second);
        });
        task1.start();
        task2.start();
        task1.join();
        task2.join();
        assertThat(store.getUserById(1).getAmount(), is(400));
    }
}