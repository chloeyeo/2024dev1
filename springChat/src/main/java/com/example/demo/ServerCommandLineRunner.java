package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.corundumstudio.socketio.SocketIOServer;

@Component
@Slf4j
@RequiredArgsConstructor
public class ServerCommandLineRunner implements CommandLineRunner {
	// this class starts Socket.IO server when spring boot application starts up
	// so that the socket.io server is initialised and ready to handle incoming
	// requests and events as soon as the app is running
	
	private final SocketIOServer server;
    @Override
    public void run(String... args) throws Exception {
        server.start();
    }
}
