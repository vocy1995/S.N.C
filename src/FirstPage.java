import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Iterator;

public class FirstPage extends JFrame{
   
   ImageIcon icon;

   static Connection con = null;
   static Statement state = null; // ���°�
   public String compare[];
   Scanner scan;      
   String b51;
   Image img = null;
   
   static int ID_num;

   MainPage insert = new MainPage();
   Media_MainPage insert2 = new Media_MainPage();
   
   Simpleclient client = new Simpleclient();
   public void FirstPage_run()
   {
      try {
         client.Client_connect();
       icon = new ImageIcon("img//view.jpg");

        // ��׶��� �̹��� ������ �޼ҵ忡 �̸����� Ŭ������ ����
        JPanel p = new JPanel() {
         public void paintComponent(Graphics g) {
          // Approach 1: Dispaly image at at full size
          g.drawImage(icon.getImage(), 0, 0, null);
          // Approach 2: Scale image to size of component
          // Dimension d = getSize();
          // g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
          // Approach 3: Fix the image position in the scroll pane
          // Point p = scrollPane.getViewport().getViewPosition();
          // g.drawImage(icon.getImage(), p.x, p.y, null);
          setOpaque(false);
          super.paintComponent(g);
         }
        };
        p.setLayout(null);
         getContentPane().add(p);
      
      //Scanner scan= new Scanner(System.in);
      //client_connect client1= new client_connect();
         //System.out.println("now, you can use translator!!");
        
      
        //�ؽ�Ʈ�ʵ� �����
      TextField b4 = new TextField();
      p.add(b4);
      //��ġ 
      b4.setBounds(111, 196, 200, 30);
      TextField b5 = new TextField();
      p.add(b5);
      b5.setEchoChar('*');
      b5.setBounds(111, 232, 200, 30);
      //��ư �����
      JButton b6 = new JButton("�α���");
      p.add(b6);
      //�۾�ü, �۾�����, �۾�ũ�� ����
      b6.setFont(new Font("�����ý��丮", Font.BOLD, 15));
      b6.setBounds(331, 196, 90, 30);
      JButton b7 = new JButton("ȸ������");
      p.add(b7);
      b7.setFont(new Font("�����ý��丮", Font.BOLD, 15));
      b7.setBounds(331, 232, 90, 30);
      

      //���ʻ�� �ΰ���
       Toolkit toolkit = Toolkit.getDefaultToolkit();
        Image imgg = toolkit.getImage("img//logo.jpg");
        setIconImage(imgg);
      
      //�� ��ư Ŭ�� �� �ش� ������ �̵� 
      b7.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {
            ProducePage f2= new ProducePage();
         }
      });
      
      b6.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e2) {
            try {
               //client.Client_connect();
               client.ClientRun("login");
            
               String[] id_data = new String[50];
               String[] password_data = new String[50];
                 //bw.newLine();
               
                 String receiveData = "";
                 receiveData = client.br.readLine();        // �����κ��� ������ ���� ����
                
                 System.out.println(receiveData);
                 String id_split[] = receiveData.split("###");
                 String login_id,login_password;
                 login_id=b4.getText().trim();
               login_password=b5.getText().trim();
               
               ID_num=Integer.parseInt(login_id);
               String computer_st = "244";
                     String nurse_st= "144";
                     
                     for(int a=0;a<id_split.length-1;a++) {      
                          if(login_id.equals(id_split[a])) {
                             if(login_password.equals(id_split[a+1])) {
                                insert2.main_user_id=ID_num;
                                insert.main_user_id=ID_num;
                                String com_token[] =login_id.split(computer_st);
                                String nurse_token[] =login_id.split(nurse_st);
                                if(com_token[0].equals(login_id)) { //ex 2013244094 Ʋ������ Ʋ���� nures�� �´��� ��
                                   if(!(nurse_token[0].equals(login_id))) {
                                      System.out.println("��ȣ�а��Դϴ�."); 
                                      Media_MainPage f4 = new Media_MainPage();
                                      f4.Media_MainPage_run();
                                   }
                                }
                                if(nurse_token[0].equals(login_id)) { //ex Ʋ������ Ʋ���� nures�� �´��� ��
                                   if(!(com_token[0].equals(login_id))) {
                                      System.out.println("��ǻ���а��Դϴ�");
                                       MainPage f3 = new MainPage();
                                       f3.MainPage_run();
                                   }
                                }                          
                             }
                          }
                       }
              //Ʋ����� ��� �޼��� �߰� �۾��ϱ�
             }catch(NullPointerException e){
                e.printStackTrace();
             }catch(Exception e){
                 e.printStackTrace();
             }
         }

      });
      //��ü���� gui������ ����
      try {
         UIManager.setLookAndFeel("com.jtattoo.plaf.texture.TextureLookAndFeel");
         JFrame.setDefaultLookAndFeelDecorated(true);
         } catch (Exception e) {}
      //guiũ�� ����
      setSize(466, 330);
      //Ŭ�ο��� ������ ȭ�� ���� ���ø����̼� ����
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      //gui â ����� r
      setLocationRelativeTo(null);
      //ũ������ X
      setResizable(false);
      //gui ���񺯰�
      setTitle("�α��� ȭ�� ");
      //gui ���̰��ϱ�
      setVisible(true);

   }catch(Exception e) {
      e.printStackTrace();
   }
   }
}