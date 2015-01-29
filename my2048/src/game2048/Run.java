package game2048;

public class Run {
	public static void main(String[] args) {
		InnerData innerData = new InnerData();
		Frame game = new Frame(innerData);
		game.run();
		

	}
}
