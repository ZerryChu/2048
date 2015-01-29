package game2048;

import java.awt.Color;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

public class Operation {
	private InnerData data;

	public Operation(InnerData myData) {
		this.data = myData;
	}

	public void init(JLabel[][] text) {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 4; j++) {
				data.mat[i][j] = 0;
				text[i][j].setText("");
			}
	}

	public void randomPosNumber(JLabel[][] text) {
		Random randomPos = new Random();
		boolean b = true;
		int i, j;
		while (b) {
			i = randomPos.nextInt(4);
			j = randomPos.nextInt(4);
			if (data.mat[i][j] == 0) {
				data.mat[i][j] = 2;
				text[i][j].setText("2");
				update(i, j, text[i][j]);
				b = false;
			}
		}
	}

	public void update(int i, int j, JLabel text) {
		int number = data.mat[i][j];
		switch (number) {
		case 0:
			text.setBackground(Color.WHITE);
			text.setBorder(BorderFactory.createLineBorder(Color.GRAY));
			text.setText("");
			break;
		case 2:
			text.setBackground(Color.YELLOW);
			text.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
			text.setText("2");
			break;
		case 4:
			text.setBackground(Color.BLUE);
			text.setBorder(BorderFactory.createLineBorder(Color.BLUE));
			text.setText("4");
			break;
		case 8:
			text.setBackground(Color.GREEN);
			text.setBorder(BorderFactory.createLineBorder(Color.GREEN));
			text.setText("8");
			break;
		case 16:
			text.setBackground(Color.ORANGE);
			text.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
			text.setText("16");
			break;
		case 32:
			text.setBackground(Color.PINK);
			text.setBorder(BorderFactory.createLineBorder(Color.PINK));
			text.setText("32");
			break;
		case 64:
			text.setBackground(Color.LIGHT_GRAY);
			text.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
			text.setText("64");
			break;
		case 128:
			text.setBackground(Color.CYAN);
			text.setBorder(BorderFactory.createLineBorder(Color.CYAN));
			text.setText("128");
			break;
		case 256:
			text.setBackground(Color.RED);
			text.setBorder(BorderFactory.createLineBorder(Color.RED));
			text.setText("256");
			break;
		case 512:
			text.setBackground(Color.MAGENTA);
			text.setBorder(BorderFactory.createLineBorder(Color.MAGENTA));
			text.setText("512");
			break;
		case 1028:
			text.setBackground(Color.darkGray);
			text.setBorder(BorderFactory.createLineBorder(Color.darkGray));
			text.setText("1028");
			break;
		case 2048:
			text.setBackground(Color.BLACK);
			text.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			text.setText("2048");
			break;
		default:
			break;
		}
	};

	public boolean pressedUp(int i, int j, boolean z) {
		if (i >= 4)
			return z;
		int pos = i;
		while (pos > 0 && data.mat[pos - 1][j] == 0 && data.mat[pos][j] != 0) {
			z = true;
			data.mat[pos - 1][j] = data.mat[pos][j];
			data.mat[pos][j] = 0;
			pos--;
		}// 上移
		if (pos > 0 && data.mat[pos][j] == data.mat[pos - 1][j]
				&& data.mat[pos][j] != 0) {
			z = true;
			data.score += data.mat[pos][j];
			data.mat[pos][j] = 0;
			data.mat[pos - 1][j] *= 2;
			pos = i + 1;
			while (pos > 0 && pos < 4 && data.mat[pos - 1][j] == 0
					&& data.mat[pos][j] != 0) {
				data.mat[pos - 1][j] = data.mat[pos][j];
				data.mat[pos][j] = 0;
				pos--;
			}// 上移
			return pressedUp(i + 2, j, z);
		}// 与上合并
		else
			return pressedUp(i + 1, j, z);
	}

	public boolean pressedDown(int i, int j, boolean z) {
		if (i < 0)
			return z;
		int pos = i;
		while (pos < 3 && data.mat[pos + 1][j] == 0 && data.mat[pos][j] != 0) {
			z = true;
			data.mat[pos + 1][j] = data.mat[pos][j];
			data.mat[pos][j] = 0;
			pos++;
		}
		if (pos < 3 && data.mat[pos][j] == data.mat[pos + 1][j]
				&& data.mat[pos][j] != 0) {
			z = true;
			data.score += data.mat[pos][j];
			data.mat[pos][j] = 0;
			data.mat[pos + 1][j] *= 2;
			pos = i - 1;
			while (pos < 3 && pos >= 0 && data.mat[pos + 1][j] == 0
					&& data.mat[pos][j] != 0) {
				data.mat[pos + 1][j] = data.mat[pos][j];
				data.mat[pos][j] = 0;
				pos++;
			}
			return pressedDown(i - 2, j, z);
		} else
			return pressedDown(i - 1, j, z);
	}

	public boolean pressedRight(int i, int j, boolean z) {
		if (j < 0)
			return z;
		int pos = j;
		while (pos < 3 && data.mat[i][pos + 1] == 0 && data.mat[i][pos] != 0) {
			z = true;
			data.mat[i][pos + 1] = data.mat[i][pos];
			data.mat[pos][j] = 0;
			pos++;
		}
		if (pos < 3 && data.mat[i][pos] == data.mat[i][pos + 1]
				&& data.mat[i][pos] != 0) {
			z = true;
			data.score += data.mat[i][pos];
			data.mat[i][pos] = 0;
			data.mat[i][pos + 1] *= 2;
			pos = j - 1;
			while (pos < 3 && pos >= 0 && data.mat[i][pos + 1] == 0
					&& data.mat[i][pos] != 0) {
				data.mat[i][pos + 1] = data.mat[i][pos];
				data.mat[i][pos] = 0;
				pos++;
			}
			return pressedRight(i, j - 2, z);
		} else
			return pressedRight(i, j - 1, z);
	}

	public boolean pressedLeft(int i, int j, boolean z) {
		if (j >= 4)
			return z;
		int pos = j;
		while (pos > 0 && data.mat[i][pos - 1] == 0 && data.mat[i][pos] != 0) {
			z = true;
			data.mat[i][pos - 1] = data.mat[i][pos];
			data.mat[i][pos] = 0;
			pos--;
		}
		if (pos > 0 && data.mat[i][pos] == data.mat[i][pos - 1]
				&& data.mat[i][pos] != 0) {
			z = true;
			data.score += data.mat[i][pos];
			data.mat[i][pos] = 0;
			data.mat[i][pos - 1] *= 2;
			pos = j + 1;
			while (pos > 0 && pos < 4 && data.mat[i][pos - 1] == 0
					&& data.mat[i][pos] != 0) {
				data.mat[i][pos - 1] = data.mat[i][pos];
				data.mat[i][pos] = 0;
				pos--;
			}
			return pressedLeft(i, j + 2, z);
		} else
			return pressedLeft(i, j + 1, z);
	}

	boolean isOver() {
		for (int i = 1; i < 3; i++)
			for (int j = 1; j < 3; j++) {
				if (data.mat[i][j] == data.mat[i - 1][j]
						|| data.mat[i][j] == data.mat[i + 1][j]
						|| data.mat[i][j] == data.mat[i][j - 1]
						|| data.mat[i][j] == data.mat[i][j + 1])
					return false;// 修改
			}
		return true;
	}// 判断是否还能产生随机的2

}// 除去无效的方向操作
//左右操作debug