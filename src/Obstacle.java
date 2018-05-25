public class Obstacle extends Entity{

	private boolean isDestructible;

	public Obstacle(int x, int y, int size, boolean isDestructible) {
		super(x, y, size, true);
		this.isDestructible = isDestructible;
	}

}
