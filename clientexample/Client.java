package clientexample;

import com.chattr.neonardo.chattr.CancerClient;

public class Client extends CancerClient {

	protected void onMessageIncoming(String msg) {
		System.out.println(msg);

	}


}
