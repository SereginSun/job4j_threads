package ru.job4j.concurrent.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStorage {

    @GuardedBy("this")
    private final Map<Integer, User> users = new HashMap<>();

    public synchronized boolean add(User user) {
        return users.putIfAbsent(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
        return users.replace(user.getId(), user) != null;
    }

    public synchronized boolean delete(User user) {
        return users.remove(user.getId(), user);
    }

    public synchronized void transfer(int sourceId, int destId, int amount) {
        if (users.containsKey(sourceId) && users.containsKey(destId) && users.get(sourceId).getAmount() >= amount) {
            User first = users.get(sourceId);
            User second = users.get(destId);
            first.setAmount(first.getAmount() - amount);
            second.setAmount(second.getAmount() + amount);
        }
    }

    public synchronized User getUserById(int id) {
        return users.get(id);
    }
}
