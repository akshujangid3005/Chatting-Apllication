import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.html.ImageView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;



public class Client  implements ActionListener {

    JTextField text;
    static JPanel a1;
    static Box vertical = Box.createVerticalBox();

    static JFrame f = new JFrame();

    static DataOutputStream dout;
        Client() {
            f. setLayout(null);

            JPanel p1 = new JPanel();
            p1.setBackground(new Color(7, 94, 84));
            p1.setBounds(0, 0, 450, 50);
            p1.setLayout(null);
            f.add(p1);


            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons//arrow.png "));
            Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
            ImageIcon i3 = new ImageIcon(i2);
            JLabel back = new JLabel(i3);
            back.setBounds(5, 15, 20, 20);
            p1.add(back);
            back.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent ae) {
                    System.exit(0);

                }
            });

            ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons//2.png "));
            Image i5 = i4.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
            ImageIcon i6 = new ImageIcon(i5);
            JLabel profile = new JLabel(i6);
            profile.setBounds(35, 5, 40, 40);
            p1.add(profile);

            ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons//calling.png "));
            Image i8 = i7.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
            ImageIcon i9 = new ImageIcon(i8);
            JLabel calling = new JLabel(i9);
            calling.setBounds(270, 10, 30, 30);
            p1.add(calling);

            ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons//12.png "));
            Image i11 = i10.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
            ImageIcon i12 = new ImageIcon(i11);
            JLabel vidocall = new JLabel(i12);
            vidocall.setBounds(320, 15, 20, 20);
            p1.add(vidocall);

            ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons//3.png "));
            Image i14 = i13.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
            ImageIcon i15 = new ImageIcon(i14);
            JLabel dot = new JLabel(i15);
            dot.setBounds(360, 15, 20, 20);
            p1.add(dot);

            JLabel name = new JLabel("Yogesh kumawat ");
            name.setBounds(80, 10, 100, 15);
            name.setForeground(Color.WHITE);
            name.setFont(new Font("SAN_SERTF", Font.BOLD, 13));
            p1.add(name);

            a1 = new JPanel();
            a1.setBounds(0, 47, 385, 490);
            f.add(a1);

            text = new JTextField();
            text.setBounds(1, 531, 305, 32);
            f.add(text);


            JButton send = new JButton("Send");
            send.setBounds(305, 531, 90, 32);
            send.setBackground(new Color(9, 94, 84));
            send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
            send.setForeground(Color.WHITE);
            send.addActionListener(this);
            f. add(send);

            f.setSize(400, 600);
            f.setLocation(900, 50);
            // setUndecorated(true)
            f. getContentPane().setBackground(Color.lightGray);
            f. setTitle("HELLO CHAT");
            f. setVisible(true);

        }

        public void actionPerformed(ActionEvent ae) {

          try {
              String out = text.getText();


            JPanel p2 = formateLable(out);


            a1.setLayout(new BorderLayout());
            JPanel right= new JPanel(new BorderLayout());
            right.add(p2,  BorderLayout.LINE_END);
            vertical.add(right);
            vertical.add(Box.createVerticalStrut(15));
            a1.add(vertical,BorderLayout.PAGE_START);

               dout.writeUTF(out);
            text.setText("");

            f. repaint();
            f. validate();
            f. invalidate();
        } catch (Exception e){
              e.printStackTrace();
          }
        }
        public static JPanel formateLable(String out) {
            JPanel panel= new JPanel();
            panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));


            JLabel output= new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");
            output.setFont(new Font("Times new roman", Font.PLAIN,16));
            output.setBackground(Color.gray);
            output.setOpaque(true);
            panel.add(output);
            Calendar cal= Calendar.getInstance();
            SimpleDateFormat sdf= new SimpleDateFormat("HH:mm");

            JLabel time= new JLabel();
            time.setText(sdf.format(cal.getTime()));
            panel.add(time);





            return panel;
        }



        public static void main(String[] args) {
            new Client();
            try {
                 Socket s = new Socket("127.0.0.1",3005);
                 DataInputStream din = new DataInputStream(s.getInputStream());
                 dout = new DataOutputStream(s.getOutputStream());

                while(true) {
                    a1.setLayout(new BorderLayout());
                    String msg = din.readUTF();
                    JPanel panel = formateLable(msg);

                    JPanel left = new JPanel(new BorderLayout());
                    left.add(panel, BorderLayout.LINE_START);
                    vertical.add(left);

                    vertical.add(Box.createVerticalStrut(15));
                    a1.add(vertical, BorderLayout.PAGE_START);

                    f.validate();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



}






