package lejos.pc.tools;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.io.*;
import lejos.pc.comm.*;

/**
 * Downloads  data from the DataLogger running on a MXT <br>
 * Uses Bluetooth  or USB<br>
 * To use BlueTooth, click "Use Bluetooth" before "Connect"<br>
 * When the status is "Connected", click "Start Download"<br>
 * If you wnat to resend, press any button except ESC on the NXT, then click 
 * "Start Download"<br>
 * You can run another download session, but you have to connect again. 
 * The data can be copied and pasted into a spread sheet for analysis & graphing <br>
 * status field shows messages 
 * @author Roger Glassey revised  06.15.2008
 *
 * 
 */
public class DataViewer extends JFrame implements ActionListener
{
	private static final long serialVersionUID = 4196717806576232148L;
	private JButton startButton = new JButton("Start download");
    private JButton connectButton = new JButton("Connect");
    private JToggleButton btButton = new JToggleButton("Use Bluetooth");
    private TextField statusField = new TextField(20);
    private TextField lengthField = new TextField(2);
    private TextField nameField = new TextField(10);
    private TextField addrField = new TextField(12);
    private int _recordCount = 0;
    private int _rowLength = 4;
    private String _nxt = "NXT";
    private boolean _useUSB = true;
    private DataInputStream dataIn = null;
    private OutputStream os = null;
    private NXTConnector con;
    private boolean _connected = false;
    /**
     * screen area to hold the downloaded data
     */
    private TextArea theLog;

    /**
     * Constructor builds GUI
     */
    public DataViewer()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("View output from NXJ Datalogger");
        setSize(550, 600);
        JPanel p1 = new JPanel();  //holds  button and text field

        p1.add(connectButton);
        connectButton.addActionListener(this);
        p1.add(btButton);
        btButton.addActionListener(this);
        p1.add(new JLabel(" Name "));
        p1.add(nameField);
        p1.add(new JLabel(" Addr "));
        p1.add(addrField);

        JPanel p2 = new JPanel();//  holds label and text field

        p2.add(startButton);
        p2.add(new JLabel("Row Length:"));
        p2.add(lengthField);
        lengthField.setText("2");
        startButton.addActionListener(this);

        p2.add(new JLabel("  Status:"));
        p2.add(statusField);

        JPanel panel = new JPanel();  // North area of the frame

        panel.setLayout(new GridLayout(2, 1));
        panel.add(p1);
        panel.add(p2);
        add(panel, BorderLayout.NORTH);
        theLog = new TextArea(40, 40); // Center area of the frame

        getContentPane().add(theLog, BorderLayout.CENTER);
        statusField.setText("using  USB");

    }

    /**
     * Required by action listener; only action is generated by the get Length button
     */
    public void actionPerformed(ActionEvent e)
    {
        if (e.getSource() == connectButton)
        {
            connect();
        }
        if (e.getSource() == startButton)
        {
            _rowLength = Integer.parseInt(lengthField.getText());
            _nxt = nameField.getText();
            _recordCount = 0;
            startDownload();
        }
        if (e.getSource() == btButton)
        {
            _useUSB = !_useUSB;
            if (_useUSB)
            {
                setMessage("using USB");
            } else
            {
                setMessage("using Bluetooth");
            }
        }
    }

    /**
     * Initialize the display Frame 
     */
    public static void main(String[] args)
    {
        DataViewer frame = new DataViewer();
        frame.setVisible(true);
    }

    private void connect()
    {
        System.out.println("connecting");
        String addr = addrField.getText();
        _nxt = nameField.getText();

        if (_useUSB)
        {
            setMessage("Connecting USB");
        } else
        {
            setMessage("Connecting BT");
        }
        System.out.println(" connecting to " + _nxt + " " + addr);
        con = new NXTConnector();
        con.addLogListener(new ToolsLogger());
        if (con.connectTo(_nxt, addr, (_useUSB ? NXTCommFactory.USB : NXTCommFactory.BLUETOOTH), false) != 0)
        {
            System.out.println(" Connection failed ");
            System.exit(1);
        }
        os = con.getOutputStream();
        dataIn = con.getDataIn();
        if (dataIn == null)
        {
            System.out.println(" NULL input stream ");
        } else
        {
            if (os == null)
            {
                System.out.println(" OS NULL");
            } else
            {
                _connected = true;
            }
        }
        String name = con.getNXTInfo().name;
        addr = con.getNXTInfo().deviceAddress;
        nameField.setText(name);
        addrField.setText(addr);
        setMessage("Connected " + name + " " + addr);

//    the NXT is waiting for an incoming byte before it starts transmitting 
    }

    private void startDownload()
    {
        if (!_connected) connect();
        int b = 15;
        try //handshake - ready to read data
        {
            os.write(b);
            os.flush();
        } catch (IOException e)
        {
            System.out.println(e + " handshake failed ");
        }
        float x = 0;
        try
        {
            int length = dataIn.readInt();
            setMessage(" reading length " + length);
            for (int i = 0; i < length; i++)
            {
                if (0 == _recordCount % _rowLength) theLog.append("\n");
                x = dataIn.readFloat();
                theLog.append(x + "\t ");
                _recordCount++;
            }
        } catch (IOException e)
        {
            System.out.println("read error " + e);
        }
    }

    /**
     *messages generated show in the status Field
     */
    public void setMessage(String s)
    {
        statusField.setText(s);
    }
}	
