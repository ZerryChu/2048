package game2048;

import java.awt.Color;
import java.awt.Panel;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 2048Game made by JAVA
 * 
 * @author ZerryChu
 * 
 */
public class Frame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InnerData data;
	private JLabel[][] text;
	private Panel scorePanel;// 记分板
	private Panel mainPanel;
	private JLabel title;
	private JTextField scoreString;
	private JButton startButton; // 开始按钮
	private game2048.Operation operation;

	public Frame(InnerData myData) {
		super();
		this.data = myData;
		this.setTitle("2048Game");
		this.setSize(400, 500);
		operation = new game2048.Operation(myData);
		getContentPane().setLayout(null);

		mainPanel = new Panel();
		mainPanel.setBounds(14, 85, 360, 360);
		mainPanel.setBackground(new Color(255, 255, 255));
		mainPanel.setLayout(null);
		mainPanel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				do_keyPressed(e);
				for (int i = 0; i < 4; i++)
					for (int j = 0; j < 4; j++)
						operation.update(i, j, text[i][j]);
			}
		});// 监听键盘上下左右键

		scorePanel = new Panel();
		scorePanel.setBounds(10, 10, 364, 53);
		scorePanel.setForeground(Color.WHITE);
		scorePanel.setBackground(Color.WHITE);
		scorePanel.setLayout(null);

		startButton = new JButton();
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				data.score = 0;
				for (int i = 0; i < 4; i++)
					for (int j = 0; j < 4; j++) {
						data.mat[i][j] = 0;
						text[i][j].setText("");
						operation.update(i, j, text[i][j]);
					}
				operation.randomPosNumber(text);
				mainPanel.requestFocus();
				// ...
			}
		});
		startButton.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
		startButton.setSize(78, 41);
		startButton.setLocation(276, 4);
		startButton.setText("start");
		scorePanel.add(startButton);

		scoreString = new JTextField("得分：0");
		scoreString.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		scoreString.setBounds(103, 15, 93, 30);
		scoreString.setBackground(new Color(255, 255, 255));
		scoreString.setEditable(false);
		scoreString.setHorizontalAlignment(SwingConstants.LEFT);
		scoreString.setColumns(5);
		scorePanel.add(scoreString);

		title = new JLabel("2048");
		title.setBounds(0, -5, 93, 64);
		title.setForeground(Color.RED);
		title.setFont(new Font("华文琥珀", Font.BOLD, 26));
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setBackground(new Color(255, 255, 255));
		scorePanel.add(title);

		text = new JLabel[4][4];
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				text[i][j] = new JLabel();
				data.mat[i][j] = 0;
				text[i][j].setText("");
				text[i][j]
						.setBorder(BorderFactory.createLineBorder(Color.GRAY));
				text[i][j].setBackground(Color.GRAY);
				text[i][j].setBounds(90 * j + 5, 90 * i + 5, 80, 80);
				text[i][j].setFont(new Font("华文琥珀", Font.BOLD, 20));
				text[i][j].setHorizontalAlignment(SwingConstants.CENTER);
				text[i][j].setOpaque(true);
				mainPanel.add(text[i][j]);
			}
		getContentPane().add(scorePanel);
		getContentPane().add(mainPanel);

	}

	protected void do_keyPressed(final KeyEvent e) {
		int keyCode = e.getKeyCode();
		int time = 0;
		switch (keyCode) {
		case KeyEvent.VK_LEFT: {
			for (int i = 0; i < 4; i++) {
				if (!operation.pressedLeft(i, 1, false))
					time++;
			}
			break;
		}
		case KeyEvent.VK_RIGHT: {
			for (int i = 0; i < 4; i++) {
				if (!operation.pressedRight(i, 2, false))
					time++;
			}
			break;
		}
		case KeyEvent.VK_UP: {
			for (int j = 0; j < 4; j++) {
				if (!operation.pressedUp(1, j, false))
					time++;
			}
			break;
		}
		case KeyEvent.VK_DOWN: {
			for (int j = 0; j < 4; j++) {
				if (!operation.pressedDown(2, j, false))
					time++;
			}
			break;
		}
		default: {
			break;
		}
		}
		if (time != 4) {
			operation.randomPosNumber(text);
			String string = "得分：";
			string += data.score;
			scoreString.setText(string);
		} else {
			if (operation.isOver()) {
				operation.init(text);
				startButton.requestFocus();
			}
		}
		return;
	}

	public void run() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);

	}
}
// 此版本只能随机假如2