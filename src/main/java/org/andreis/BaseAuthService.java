package org.andreis;

import java.sql.*;
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
    private Connection connection;
    private static Statement stmt;
    private List<Entry> entries;
    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:javadb.db");
            stmt = connection.createStatement();
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS users (\n" +
                    " name TEXT," +
                    " login TEXT," +
                    " password TEXT" +
                    " );");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS chats (\n" +
                    " name TEXT," +
                    " users TEXT," +
                    " );");
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS messages (\n" +
                    " text TEXT," +
                    " chatname TEXT," +
                    " whotosend TEXT" +
                    " );");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void stop() {
        System.out.println("Сервис аутентификации остановлен");
        try {
            stmt.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public BaseAuthService() {
        try {
            start();
            stmt.executeUpdate("INSERT INTO users VALUES IF NOT EXISTS ('Andrei', 'Andrei', 'p1')");
            stmt.executeUpdate("INSERT INTO users VALUES IF NOT EXISTS ('Someone', 'l1', 'p1',)");
            stmt.executeUpdate("INSERT INTO users VALUES IF NOT EXISTS ('User', 'l2', 'p1',)");

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    @Override
    public String getNickByLoginPass(String login, String pass) {
        System.out.println("Поиск пользователя...");


        return get(login,pass);
    }
    @Override
    public void add(String name, String login, String pass) {
        try {

            stmt=connection.createStatement();
            stmt.executeUpdate("INSERT INTO users VALUES ('" + name +"', '"+login+ "', '"+pass+"')");
            stmt.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public String get(String login, String pass) {
        try {

            stmt=connection.createStatement();
            ResultSet resultSet = stmt.executeQuery("Select * from users WHERE login = '"+login+"' and password = '"+pass+"'");
            String answ=resultSet.getString("name");
            stmt.close();


            return answ;
        }
        catch(Exception e) {
            e.printStackTrace();
            return null;
        }
    }



    public void remove(String name) {
        try {

            stmt=connection.createStatement();
            stmt.executeUpdate("DELETE FROM regions WHERE name = '" + name + "'");
            stmt.close();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void update(String what, String namewho, String namerg) {
        try {

            stmt=connection.createStatement();
            String st="";
            if(what.equals("friends")){
                st=String.format("UPDATE %s SET %s = '%s' WHERE %s = '%s'",namewho+":", namerg);
            }
            stmt.executeUpdate(st);
            stmt.close();

        }
        catch(Exception e) {

        }
    }

}

