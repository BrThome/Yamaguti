import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bomberman extends Entity {

	private boolean isDead;
	private Bomb[] bombs;
	private int bombPower;


	public int vPoints; // implementar métodos

	public Bomberman(int x, int y, int size) {
		super(x, y, size, false);
		isDead = false;
		bombPower = 1;
		vPoints = 0;
		bombs = new Bomb[1]; bombs[0] = null;
	}
	
	public Bomb[] getBombs() {
		return bombs;
	}
	
	public Bomb getBombs(int index) {
		return bombs[index];
	}
	

	public void destroyBomb(int index) {
		bombs[index] = null;
	}

	public void move(int dx, int dy) {
		setXY(getX() + dx, getY() + dy);
	}
	
	public boolean placeBomb() {
		for(int i = 0; i <= bombs.length; i++) {
			if(bombs[i] == null) {
				bombs[i] = new Bomb(getX(), getY(), size, System.nanoTime());
				System.out.println("B1 Placed Bomb");
				return true; // retorna true para verificar se renderizamos o grafico da bomba ou não
			} else {
				System.out.println("B1 No More Bombs"); // debug
				return false; // retorna false para verificar se renderizamos o grafico da bomba ou não
			}
		}
		return false;
	}
	
	@Override
	public void render(Graphics2D graphic) {
		graphic.fillRect(getX() + 1, getY() + 1, size - 2, size - 2);
		// mudar pra desenhar sprite
	}
}
