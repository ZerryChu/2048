package game2048;

public class InnerData {
	public int score;
	public int[][] mat;
	
	public InnerData() {
		score = 0;
		mat = new int[4][4];
		for(int i = 0;i < 4; i++)
			for(int j = 0;j < 4; j++)
				mat[i][j] = 0;
	}
}
