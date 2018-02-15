import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class BlockchainExample {
	
	//list of hash values
	List<String> blocks = new ArrayList<String>();
	
	//initBlockchain method
	public void initBlockchain() {
		String msg = "Hello world";
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		String previousHash = "0";
		String index = "0";
		
		hashBlock(msg, timeStamp, previousHash, index);
	}
	
	public void hashBlock(String msg, String timestamp, String prevHash, String index) {
		
		String hash = "";
		Integer nonce = 0;
		
		while(!isHashValid(hash)) {
			String input = msg+timestamp+prevHash+index+nonce.toString();
			hash = sha256(input);
			nonce+=1;
		}
		System.out.println(nonce.toString());
		blocks.add(hash);	
	}
	
	public String getLastHash() {
		return blocks.get(blocks.size()-1);
	}
	
	public boolean isHashValid(String hash) {
		if (hash.startsWith("0000")) return true;
		else return false;
	}
	
	//addNewBLock
	
	//getAllBlocks
	
	//sha256
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//jak tutaj wywolac metody klasy powyzej???
		
		//initBlockchain
		//addNewBLock - First new block
		//addNewBlock - Second new block
		
		//getAllBlocks

	}

}
