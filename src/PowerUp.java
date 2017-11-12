public class PowerUp extends Entity{

	private String type;

	public PowerUp(int x, int y, int size, String type) {
		super(x, y, size, false);
		this.type = type;
	}

}
