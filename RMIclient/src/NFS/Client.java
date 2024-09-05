package NFS;

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
  

    
  
}
