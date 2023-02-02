package com.placeteam.backend;


import com.placeteam.backend.server.Server;

public class Main {
    public static void main(String[] args) {
      //  MainView mainView = new MainView();
      //  mainView.open();
Server server = new Server();
server.start();
    }
}