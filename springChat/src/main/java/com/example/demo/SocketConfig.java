package com.example.demo;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;

@org.springframework.context.annotation.Configuration
public class SocketConfig {
	
	@Value("${socket-server.host}")
    private String host;

    @Value("${socket-server.port}")
    private Integer port;

    @Bean
    public SocketIOServer socketIOServer() {
        Configuration config = new Configuration();
        config.setHostname(host); // server hostname
        config.setPort(port); //
        return new SocketIOServer(config);
    }
}
