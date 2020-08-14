import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
 
public class Simpleclient{
	Socket socket = null;
    OutputStream os = null;
    OutputStreamWriter osw =null;
    BufferedWriter bw = null;
    
    InputStream is =null;
    InputStreamReader isr = null;
    BufferedReader br = null;
      
 
    //클라이언트에서 서버로의 데이터 전송과 접속을 위한 클래스 
    public void ClientRun(String data){
             
        try{
        	
            os = socket.getOutputStream();
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);            //서버로 전송을 위한 OutputStream
            
            is = socket.getInputStream();
    		isr = new InputStreamReader(is);
    		br = new BufferedReader(isr);        // 서버로부터 Data를 받기위한 BufferedReader
    		
            bw.write(data);
    		bw.newLine();
    		bw.flush();
    		
    		
        }catch(Exception e){
            e.printStackTrace();
        }

    }
   
    public void close() {
    	try{
            bw.close();
            osw.close();
            os.close();
            br.close();
            isr.close();
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        } 
    }
    public void Client_connect(){
    	try {
    		String ip = "175.112.165.79"; //외부 ip
    		socket = new Socket("localhost",8000); //포트번호 8000과 ip설정
    	}catch(Exception e){
            e.printStackTrace();
        }
    }
}