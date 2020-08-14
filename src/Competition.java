import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.lang.String;

public class Competition extends JFrame {
   
   Simpleclient client = new Simpleclient();
   
   public void Competition_run(){
      
      String title[] = new String[50];
      
      try {
         client.Client_connect();
      JPanel p = new JPanel();
      getContentPane().add(p);
      p.setLayout(null);
         try{
            client.ClientRun("competition");
             
             String receiveData = "";
             receiveData = client.br.readLine();
             System.out.println("제목 받은것 : "+receiveData);   
             String Competition[] = receiveData.split("!!!");
             System.out.println("자른것 : "+Competition[0]);
             for(int count=0;count<Competition.length;count++) {
                title[count]=Competition[count];
                System.out.println("자른것 : "+Competition[count]);
                System.out.println("저장된 제목 : "+title[count]);
             }
            
         }catch(Exception e) {
            e.printStackTrace();
         }
      
      /*JLabel lblNewLabel = new JLabel("학과공지사항");
      lblNewLabel.setFont(new Font("돋움", Font.BOLD, 30));
      lblNewLabel.setBounds(177, 75, 232, 88);
      p.add(lblNewLabel);
      
      JLabel label_1 = new JLabel("기타공지사항");
      label_1.setFont(new Font("돋움", Font.BOLD, 30));
      label_1.setBounds(716, 75, 210, 88);
      p.add(label_1);*/
      
      JButton bt1 = new JButton(title[0]);
      p.add(bt1);
      bt1.setFont(new Font("메이플스토리", Font.BOLD, 35));
		bt1.setBounds(34, 40, 1200, 100);
      JButton j2 = new JButton(title[1]);
      p.add(j2);
      j2.setFont(new Font("메이플스토리", Font.BOLD, 35));
		j2.setBounds(34, 170, 1200, 100);
   
      JButton bt3 = new JButton(title[2]);
      bt3.setFont(new Font("메이플스토리", Font.BOLD, 35));
		bt3.setBounds(34, 300, 1200, 100);
      p.add(bt3);

      JButton bt2 = new JButton(title[3]);
      bt2.setFont(new Font("메이플스토리", Font.BOLD, 35));
		bt2.setBounds(34, 430, 1200, 100);
      p.add(bt2);

      JButton bt4 = new JButton(title[4]);
      bt4.setFont(new Font("메이플스토리", Font.BOLD, 35));
		bt4.setBounds(34, 560, 1200, 100);
      p.add(bt4);

      JButton bt5 = new JButton(title[5]);
      bt5.setFont(new Font("메이플스토리", Font.BOLD, 35));
		bt5.setBounds(34, 690, 1200, 100);
      p.add(bt5);
      
      JButton back = new JButton("뒤로가기");
      back.setFont(new Font("메이플스토리", Font.BOLD, 40));
      back.setBounds(1375,41,262,133);
      p.add(back);
   
      back.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            setVisible(false);
            dispose();
         }
      });
   
      j2.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e4) {
            Competition_Content t=new Competition_Content();
            t.Get_Content_Count=1;
            t.Competition_Content_run();
         }
      });
      bt1.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e3) {
            Competition_Content t=new Competition_Content();
            t.Get_Content_Count=0;
            t.Competition_Content_run();
         }

      });
      bt2.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e3) {
            Competition_Content t=new Competition_Content();
            t.Get_Content_Count=3;
            t.Competition_Content_run();
         }

      });
      bt3.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e3) {
            Competition_Content t=new Competition_Content();
            t.Get_Content_Count=2;
            t.Competition_Content_run();
         }

      });
      bt4.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e3) {
            Competition_Content t=new Competition_Content();
            t.Get_Content_Count=4;
            t.Competition_Content_run();
         }

      });
      bt5.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e3) {
            Competition_Content t=new Competition_Content();
            t.Get_Content_Count=5;
            t.Competition_Content_run();
         }

      });
   
      Toolkit toolkit = Toolkit.getDefaultToolkit();
	     Image imgg = toolkit.getImage("img//logo.jpg");
	     setIconImage(imgg);

      
      setSize(1700, 1000);

      setLocationRelativeTo(null);
      setResizable(false);
      setTitle("공모전");
      setVisible(true);
      }catch(Exception e) {
         e.printStackTrace();
      }
   }

}
