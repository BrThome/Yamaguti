import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Entity {

	private int x;

	private int y;

	protected int size;

	private boolean solid;

	public Entity(int x, int y, int size, boolean solid) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.solid = solid;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setXY(int x, int y) {
		setX(x);
		setY(y);
	}

	public boolean isSolid() {
		return solid;
	}

	public void setSolid(boolean state) {
		this.solid = state;
	}

	public Rectangle form() {
		return new Rectangle ( x, y, size, size);
	}

	public boolean isColliding(Entity other) {
		if (other == this) return false;
		else return form().intersects(other.form());
	}

	public void render(Graphics2D graphic) {
		graphic.fillRect(x + 1, y + 1, size - 2, size - 2); // como será renderizado
		// mudar pra desenhar sprite
	}

}
