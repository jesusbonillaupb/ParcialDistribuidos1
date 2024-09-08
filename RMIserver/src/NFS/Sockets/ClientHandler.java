/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package NFS.Sockets;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jesus
 */
public class ClientHandler extends Thread {

    private Socket socket;
    private PrintWriter out;
    private static List<PrintWriter> clientWriters = new ArrayList<>();

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

  
    
}

