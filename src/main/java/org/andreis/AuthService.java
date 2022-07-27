package org.andreis;

import java.sql.Connection;

public interface AuthService {
    void start();
    Connection connection = null;

    String getNickByLoginPass(String login, String pass);

    void stop();
    void add(String name, String login, String pass);

}
