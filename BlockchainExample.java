import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
	
	public void addNewBlock(String msg){
		Integer in = blocks.size();
		String previousHash = getLastHash();
		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		
		hashBlock(msg, timeStamp, previousHash, in.toString());
	}
	

	public void getAllBlocks() {
		for(int i=0;i<blocks.size();i++){
		    System.out.println(blocks.get(i));
		} 
	}
	

	public String sha256(String password) {
        MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        md.update(password.getBytes());

        byte byteData[] = md.digest();

        //convert the byte to hex format method 1
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        
        return sb.toString();
	}
	
	
	public static void main(String[] args) {
		
		BlockchainExample be = new BlockchainExample();

		be.initBlockchain();
		be.addNewBlock("First new block");
		be.addNewBlock("Second new block");
		
		be.getAllBlocks();

	}

}
