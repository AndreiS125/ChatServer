package org.andreis;

import java.util.ArrayList;
import java.util.List;

public class BaseAuthService implements AuthService {
    private class Entry {
        private String login;
        private String pass;
        private String nick;
        public Entry(String login, String pass, String nick) {
            this.login = login;
            this.pass = pass;
            this.nick = nick;
        }
    }
    private List<Entry> entries;
    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");
    }
    @Override
    public void stop() {
        System.out.println("Сервис аутентификации остановлен");
    }
    public BaseAuthService() {
        entries = new ArrayList<>();
        entries.add(new Entry("l1", "p1", "Andrei"));
        entries.add(new Entry("l2", "p2", "Daun"));
        entries.add(new Entry("login3", "pass3", "nick3"));
    }
    @Override
    public String getNickByLoginPass(String login, String pass) {
        System.out.println("Поиск пользователя...");
        for (Entry o : entries) {
            if (o.login.equals(login) && o.pass.equals(pass)) {
                System.out.println("Пользователь найден...");
                return o.nick;
            }
        }

        return null;
    }
}
