import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import java.lang.String;
import javax.swing.border.*;
import java.io.StringReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;



public class New_board_Write extends JFrame {
   
   Simpleclient client = new Simpleclient();
   ImageIcon background,title,content;
   String title_get,content_get;
   joptionpane_test confirm = new joptionpane_test();
   private New_board_Write F = this;
   
   public void New_board_Write_Run(){
      
      try {
         
         background = new ImageIcon("background.jpg");
         JPanel p = new JPanel();
         getContentPane().add(p);
         p.setLayout(null);

         
         JButton New_Board = new JButton("�ۼ�");
         New_Board.setFont(new Font("�����ý��丮", Font.BOLD, 30));
         New_Board.setBounds(600, 780, 200, 100);
         p.add(New_Board);
         
         JButton back = new JButton("���");
         back.setFont(new Font("�����ý��丮", Font.BOLD, 30));
         back.setBounds(930, 780, 200, 100);
         p.add(back);
         

         
         //   Content.setBorderPainted(false);
         
         TextField TitleArea = new TextField();
         p.add(TitleArea);
         TitleArea.setBounds(100, 50, 1200,40);
         
         TextArea WriteArea = new TextArea();
         p.add(WriteArea);
         WriteArea.setBounds(100, 150, 1200, 500);
      //   ContentArea.setEditable(false); 
         
         
         New_Board.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e3) {
               try{       
                  String content_temp[] = new String[100];
                  StringReader result=new StringReader(WriteArea.getText());
                  BufferedReader br = new BufferedReader(result);
                  
                  client.Client_connect();
                  client.ClientRun("new_board_write");
                  
                  title_get=TitleArea.getText().trim(); //�ؽ�Ʈ �ʵ忡�� �ۼ��� �����͸� ������������ getText().trim();�� ����Ͽ����ϴ�
                  content_get=WriteArea.getText().trim();   //�ؽ�Ʈ �ʵ忡�� �ۼ��� �����͸� ������������ getText().trim();�� ����Ͽ����ϴ�               
                  String interval="@@@";
                  String content_interval="%n";
                           
                  String total,line,content_sum;
                  content_sum="";
                  int count=0;
                  
                  try {
                     while((line=br.readLine())!=null) {
                        System.out.println(line);
                        content_temp[count]=line;
                        count++;                     
                     }//���� �����͸� �迭�� ����
                  } catch (IOException e) {
                     e.printStackTrace();
                  }
                  for(int test1=0;test1<count;test1++) {
                     content_sum+=content_temp[test1]+content_interval;
                  }
                  total=title_get+interval+content_sum;
                  //�����͸� \n�������� ������ ��
                  confirm.joptionpane_test();
                  if(confirm.result==0) {
                     System.out.println(total);
                     client.ClientRun(total);
                     dispose();
                  }//Ȯ�� �޽����� ���ͼ� �ۼ��� ������ ����
                  dispose();
               }catch(NullPointerException e){
                  e.printStackTrace();
               }   
            }
            
         });

         back.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               setVisible(false);
               dispose();
            }
         });
         
         Toolkit toolkit = Toolkit.getDefaultToolkit();
	     Image imgg = toolkit.getImage("img//logo.jpg");
	     setIconImage(imgg);
	     
         setSize(1700, 1000);      
         setLocationRelativeTo(null);
         setResizable(false);
         setTitle("�����Խ���");
         setVisible(true);
         }catch(Exception e) {
            e.printStackTrace();
         }
      }   
}
