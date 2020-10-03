import java.net.InetAddress;
import java.net.UnknownHostException;

public class Parser {
    public static void main(String[] args) {
        try {
            InetAddress inetAddress = InetAddress.getByName("77.88.55.80");
//            InetAddress inetAddress = InetAddress.getByName("87.250.250.242");
            String hostName = inetAddress.getHostName();
            System.out.println(inetAddress.getHostName());
            System.out.println(inetAddress.getCanonicalHostName());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
