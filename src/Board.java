import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Board extends JPanel implements Runnable, KeyListener {

	public static final int ALTURA = 800;
	public static final int LARGURA = 640;
	private final int tamGrade = 20;
	
	private Graphics2D graphic;
	private BufferedImage image;

	private Thread thread;
	private boolean running;
	private long fps;
	private boolean paused;

	private Entity[][] map;

	private Bomberman[] bomberman = new Bomberman[2]; // Cria vetor
	private int[] dxb_A;
	private int[] dxy_B;
	private boolean[] movementB1 = {false, false, false, false }; // WASD Moviment: Cima, Esquerda, Baixo, Direita
	private boolean[] movementB2 = {false, false, false, false }; // SETAS Moviment: Cima, Esquerda, Baixo, Direita 

	public void Board() {
		setPreferredSize(new Dimension(LARGURA, ALTURA));
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
	}

	private void init() {

	} 	
	
	@Override
	public void addNotify() { // Método de JComponent da qual JPanel herda que é invocada quando o componente se torna "aparentado"
		super.addNotify();
		thread = new Thread(this);
		thread.start(); // inicia thread
	}

	public void run() {
		if(running) return; // se o jogo estiver rodando encerra o método
		
		init(); // se o jogo não estiver rodando o inicia
		long inicio; // tempo inicial
		long decorrido; // tempo atual
		long espera; // tempo de espera para alcançar a taxa de quadros por segundo desejada
		
		while(running) {
			inicio = System.nanoTime();
			update(); // atualiza movimentos, posições etc conforme ifs
			requestRender(); // desenha gráficos conforme novas posições
			decorrido = System.nanoTime() - inicio;
			espera = fps - decorrido / 1000000; // calcula taxa de quadros desejado por segundo
			if(espera > 0) {
				try {
					Thread.sleep(espera);
				} catch (Exception e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}

	public void update() {

	}

	public void render(Graphics2D graphic) {
		
	}

	private void setFPS(int fps) {
		this.fps = 1000 / fps;
	}
	
	public void requestRender() {
		render(graphic);
		Graphics g = getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
	}

	@Override
	public void keyPressed(KeyEvent k) {

	}

	@Override
	public void keyReleased(KeyEvent k) {

	}

	@Override
	public void keyTyped(KeyEvent k) {

	}

	public void inputController(KeyEvent k, boolean state) {
		int kcode = k.getKeyCode(); // obtem codigo numero da tecla
		// Movimento personagem 1
		if(kcode == KeyEvent.VK_W) movementB1[0] = state; // w bomber1
		if(kcode == KeyEvent.VK_A) movementB1[1] = state; // a bomber1
		if(kcode == KeyEvent.VK_S) movementB1[2] = state; // s bomber1
		if(kcode == KeyEvent.VK_D) movementB1[3] = state; // d bomber1
	}
}
