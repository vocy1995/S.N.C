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
      
 
    //Ŭ���̾�Ʈ���� �������� ������ ���۰� ������ ���� Ŭ���� 
    public void ClientRun(String data){
             
        try{
        	
            os = socket.getOutputStream();
            osw = new OutputStreamWriter(os);
            bw = new BufferedWriter(osw);            //������ ������ ���� OutputStream
            
            is = socket.getInputStream();
    		isr = new InputStreamReader(is);
    		br = new BufferedReader(isr);        // �����κ��� Data�� �ޱ����� BufferedReader
    		
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
    		String ip = "175.112.165.79"; //�ܺ� ip
    		socket = new Socket("localhost",8000); //��Ʈ��ȣ 8000�� ip����
    	}catch(Exception e){
            e.printStackTrace();
        }
    }
}