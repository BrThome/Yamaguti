import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Bomb extends Entity {

	private long creationTime;
	private static BufferedImage bombGFX; // static para que seja instanciada esta variável apenas uma vez


	public Bomb(int x, int y, int size, long creationTime) {
		super(x, y, size, true);
		this.creationTime = creationTime;
		if(bombGFX == null ) {
			try {
				bombGFX = ImageIO.read(new File("Sprites\\Bomb.png"));
			} catch (IOException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	public boolean tryExploding() {
//		System.out.println("Time when Created: " + creationTime + " ### Time now: " + System.nanoTime() + "/" + (creationTime + 3000000));
		if(System.nanoTime() >= (creationTime + 3000000000f)) { // ver timer 3 segundos no momento
			System.out.println("B1 Bomb exploded");
			return true;
		}
		return false;
	}
	
	@Override
	public void render(Graphics2D graphic) {
		graphic.drawImage(bombGFX, new AffineTransform(1f,0f,0f,1f,getX(),getY()), null);
	}

}
