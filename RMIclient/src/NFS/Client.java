package NFS;

import java.rmi.Naming;
import java.util.List;

public class Client {

  private String url;

  public Client(String ip, String port, String serviceName) {
    this.url = "rmi://" + ip + ":" + port + "/" + serviceName;
  }

  /*
  public List<Object[]> getNoticias(){
      try {
          RMINEWS nwsService= (RMINEWS) Naming.lookup(this.url);
          return nwsService.getNews();
      } catch (Exception e) {
          e.printStackTrace();
      }
      return null;
    }*/
  

    
  
}
