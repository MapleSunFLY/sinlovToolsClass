package com.sinlov.toolsclass.security;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;
import java.util.Random;
import java.util.StringTokenizer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RSAFrame implements ActionListener {

	private JLabel time2;
	private JTextArea sendM;
	private JScrollPane panelSendM;
	private JTextArea decode;
	private JScrollPane panelDecode;
	private JTextArea encondSm;
	private JScrollPane panelEncondSm;
	private JTextField setD;
	private JTextField setE;
	private JTextField setN;
	private JTextField setQ;
	private JTextField setP;
	private JTextField numBits;
	private JFrame frame;
	private JButton b1, b2, b3;
	private RSA rsa = new RSA();
	private BigInteger p, q, e, n, d, ran;
	private Random rnd = new Random();
	private int numBit = 10;
	private long t1 , t2, t;
	private JLabel time;
	
	public void setMessage() {
		p = rsa.getPrimes(rnd);
		q = rsa.getPrimes(rnd);
		n = rsa.getN(p, q);
		ran = rsa.getRan(p, q);
		e = rsa.getE(ran);
		d = rsa.getKey(e,ran);
		
		setP.setText(p + "");
		setQ.setText(q + "");
		setN.setText(n + "");
		setE.setText(e + "");
		setD.setText(d + "");
	}
	
	public int getNumBits() {
		numBit = Integer.parseInt(numBits.getText());
		if(numBit<9)
			   JOptionPane.showMessageDialog(null, "请输入大于的整数");
		else rsa.getNumBit(numBit);
		return numBit;
	}
	
	public void actionPerformed(ActionEvent evt) {
		if(evt.getSource() == b1) {
			this.getNumBits();
			this.setMessage();
		} 
		
		if(evt.getSource() == b2) {
			t1 = System.currentTimeMillis();
			    byte[] arraySendM = sendM.getText().getBytes();
		        int numeroByte = (this.getNumBits()-1) / 8;   
		        byte[][] encodSendM = RSA.byteToEm(arraySendM,numeroByte);
		        BigInteger[] encodingM = RSA.encodeRSA(encodSendM, e, n);	        
		        encondSm.setText(""); 
		        int i; 
		        for (i=0; i < encodingM.length; i++) {
		        	encondSm.append(encodingM[i].toString());
		        	encondSm.append("  "); 
		        }
		        t2 = System.currentTimeMillis();
		        t = t2 - t1;
		        time.setText(t + "毫秒");
		    }
		
		if(evt.getSource() == b3) {
			try {
				t1 = System.currentTimeMillis();
	            String encondM = encondSm.getText();
	            /*分隔符字符用来分隔标记。标记是连续字符（不是分隔符）的最大序列*/
	            StringTokenizer StringEncM = new StringTokenizer(encondM, " ", false);
	            /*计算在生成异常之前可以调用此 tokenizer 的 nextToken 方法的次数*/
	            int numeroToken = StringEncM.countTokens();
	            BigInteger[] StringToByte = new BigInteger[numeroToken];
	            byte[] sendMessage;      
	            byte[][] encodeM;

	            for (int i = 0; i < numeroToken; i++) {
	            	/*返回此 string tokenizer 的字符串中的下一个标记*/
	                StringToByte[i] = new BigInteger(StringEncM.nextToken());
	            }
	            
	            encodeM = RSA.dencodeRSA(StringToByte, d, n);
	            
	            sendMessage = RSA.StringToByte(encodeM);
	            
	            String message = new String(sendMessage);
	            decode.setText(message);
	            t2 = System.currentTimeMillis();
	            t = t2 - t1;   
		        time2.setText(t + "毫秒");

	        }
	        catch(NumberFormatException e) {
	            JOptionPane.showMessageDialog(null, 
	            		"过大系统越界", 
	            		"Errore", JOptionPane.ERROR_MESSAGE);
	        }
		}
	}
	
	/**
	 * Launch the application
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			RSAFrame rf = new RSAFrame();
			rf.launchFrame();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the application
	 */
	public RSAFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setBounds(100, 100, 594, 511);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelSendM = new JScrollPane();
		panelSendM.setBounds(10, 224, 525, 39);
		frame.getContentPane().add(panelSendM);

	}
	
	public void launchFrame() {
		final JLabel label = new JLabel();
		label.setText("请你输入生成随机数的位数:");
		label.setBounds(10, 10, 165, 15);
		frame.getContentPane().add(label);

		b1 = new JButton();
		b1.setText("生成随数");
		b1.setBounds(383, 6, 99, 23);
		frame.getContentPane().add(b1);

		numBits = new JTextField();
		numBits.setBounds(181, 7, 181, 21);
		frame.getContentPane().add(numBits);

		final JLabel labelp = new JLabel();
		labelp.setText("随机数    P:");
		labelp.setBounds(10, 34, 73, 15);
		frame.getContentPane().add(labelp);

		setP = new JTextField();
		setP.setBounds(100, 31, 382, 21);
		frame.getContentPane().add(setP);

		final JLabel labelq = new JLabel();
		labelq.setText("随机数    Q:");
		labelq.setBounds(10, 61, 73, 15);
		frame.getContentPane().add(labelq);

		setQ = new JTextField();
		setQ.setBounds(100, 58, 382, 21);
		frame.getContentPane().add(setQ);

		final JLabel nPqLabel = new JLabel();
		nPqLabel.setText("  N = P*Q:");
		nPqLabel.setBounds(10, 94, 73, 15);
		frame.getContentPane().add(nPqLabel);

		setN = new JTextField();
		setN.setBounds(100, 91, 382, 21);
		frame.getContentPane().add(setN);

		final JLabel nPqLabel_1 = new JLabel();
		nPqLabel_1.setText("随机整数  e:");
		nPqLabel_1.setBounds(10, 125, 73, 15);
		frame.getContentPane().add(nPqLabel_1);

		final JLabel nPqLabel_2 = new JLabel();
		nPqLabel_2.setText("密钥      d:");
		nPqLabel_2.setBounds(10, 157, 73, 15);
		frame.getContentPane().add(nPqLabel_2);

		setE = new JTextField();
		setE.setBounds(100, 122, 382, 21);
		frame.getContentPane().add(setE);

		setD = new JTextField();
		setD.setBounds(100, 154, 382, 21);
		frame.getContentPane().add(setD);

		final JLabel label_1 = new JLabel();
		label_1.setText("要发送的明文     M:");
		label_1.setBounds(10, 198, 117, 15);
		frame.getContentPane().add(label_1);

		b2 = new JButton();
		b2.setText("加密明文");
		b2.setBounds(10, 269, 99, 23);
		frame.getContentPane().add(b2);

		panelEncondSm = new JScrollPane();
		panelEncondSm.setBounds(10, 302, 525, 39);
		frame.getContentPane().add(panelEncondSm);

		encondSm = new JTextArea();
		encondSm.setRows(4);
		encondSm.setLineWrap(true);
		encondSm.setColumns(50);
		panelEncondSm.setViewportView(encondSm);

		b3 = new JButton();
		b3.setText("解密");
		b3.setBounds(10, 348, 99, 23);
		frame.getContentPane().add(b3);

		panelDecode = new JScrollPane();
		panelDecode.setBounds(10, 377, 525, 39);
		frame.getContentPane().add(panelDecode);

		decode = new JTextArea();
		panelDecode.setViewportView(decode);
		decode.setRows(4);
		decode.setLineWrap(true);
		decode.setColumns(50);
		
		sendM = new JTextArea();
		sendM.setRows(4);
		sendM.setLineWrap(true);
		sendM.setColumns(50);
		panelSendM.setViewportView(sendM);
		
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		
		final JLabel label1 = new JLabel();
		label1.setText("加密所需时间：");
		label1.setBounds(10, 434, 99, 15);
		frame.getContentPane().add(label1);

		time = new JLabel();
		time.setBounds(120, 434, 117, 15);
		frame.getContentPane().add(time);
		
		final JLabel label2 = new JLabel();
		label2.setText("解密所需时间：");
		label2.setBounds(281, 434, 99, 15);
		frame.getContentPane().add(label2);

		time2 = new JLabel();
		time2.setBounds(383, 434, 117, 15);
		frame.getContentPane().add(time2);
		frame.setVisible(true);
	}
}
