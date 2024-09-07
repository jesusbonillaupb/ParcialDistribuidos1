package NFS;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.IOException;
import NFS.interfaz.RMIUsers;
import java.rmi.Naming;
import java.util.List;

public class Client {

    private String url;

    public Client(String ip, String port, String serviceName) {
      this.url = "rmi://" + ip + ":" + port + "/" + serviceName;
    }

    public int loginUser(String name,String password){
          try {
            RMIUsers usrService= (RMIUsers) Naming.lookup(this.url);
            return usrService.loginUser(name, password);
          } catch (Exception e) {
            e.printStackTrace();
          }
          return 0;
    }
    public boolean RegisterUser(String name,String password,String rol){
          try {
            RMIUsers usrService= (RMIUsers) Naming.lookup(this.url);
            return usrService.RegisterUser(name, password,rol);
          } catch (Exception e) {
            e.printStackTrace();
          }
          return false;
    }
    public boolean deleteUser(int id){
        try {
          RMIUsers usrService= (RMIUsers) Naming.lookup(this.url);
          return usrService.DeleteUser(id);
        } catch (Exception e) {
          e.printStackTrace();
        }
        return false;
    }
    
    public boolean updateUser(int id,String name,String password,String rol){
        try {
          RMIUsers usrService= (RMIUsers) Naming.lookup(this.url);
          return usrService.UpdateUser(id,name, password,rol);
        } catch (Exception e) {
          e.printStackTrace();
        }
        return false;
    }
    public String getRol(int id){
          try {
            RMIUsers usrService= (RMIUsers) Naming.lookup(this.url);
            return usrService.getRole(id);
          } catch (Exception e) {
            e.printStackTrace();
          }
          return "error";
    }
    public List<Object[]> getUsuarios(){
      try {
          RMIUsers usrService= (RMIUsers) Naming.lookup(this.url);
          return usrService.getUsuarios();
      } catch (Exception e) {
          e.printStackTrace();
      }
      return null;
    }
}