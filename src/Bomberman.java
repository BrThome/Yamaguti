public class Bomberman extends Entity {

	private boolean isDead;

	private int bombPower;

	public int vPoints; // implementar métodos

	public Bomberman(int x, int y, int size) {
		super(x, y, size, false);
		isDead = false;
		bombPower = 1;
		vPoints = 0;
	}

	public void move(int dx, int dy) {
		setX(getX() + dx);
		setX(getY() + dy);
	}

}
