import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Board extends JPanel implements Runnable, KeyListener {

	public static final int ALTURA = 800;
	public static final int LARGURA = 640;
	private final int tamGrade = 40; // tamanho de cada espaço
	
	// Graphics
	private Graphics2D graphic;
	private BufferedImage image;
	
	private Thread thread;
	private boolean running;
	private long fps;
	private boolean paused;

	private Entity[][] map;

	private Bomberman[] bomberman = new Bomberman[2]; // Cria vetor
	private int[] dxy_B1 = {0, 0};
	private int[] dxy_B2 = {0, 0};
	private boolean[] movementB1 = {false, false, false, false, false }; // WASD Moviment: Cima, Esquerda, Baixo, Direita, Bomba
	private boolean[] movementB2 = {false, false, false, false, false }; // SETAS Moviment: Cima, Esquerda, Baixo, Direita, Bomba

	public Board() {
		setPreferredSize(new Dimension(LARGURA, ALTURA));
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
	}

	private void init() {
		image = new BufferedImage(LARGURA, ALTURA, BufferedImage.TYPE_INT_ARGB); // Cria fundo do jogo
		graphic = image.createGraphics();
		running = true; // começou a rodar o jogo	
		setupLevel();
		setFPS(15);
	} 	
	
	private void setupLevel() { // prepara o mapa da partida
		bomberman[0] = new Bomberman(40, 40, tamGrade);
		bomberman[1] = new Bomberman(0, 0, tamGrade);
		
		//implementar criação do mapa a partir de arquivos (Thomé)
	}
	
	@Override
	public void addNotify() { // Método de JComponent da qual JPanel herda que é invocada quando o componente se torna "aparentado"
		super.addNotify();
		thread = new Thread(this);
		thread.start(); // inicia thread
	}
	
	private void setFPS(int fps) {
		this.fps = 1000 / fps;
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
		
		// Checar timer da bomba
		for(int i = 0; i < bomberman[0].getBombs().length; i++) {
			if(bomberman[0].getBombs(i) != null) {
				if(bomberman[0].getBombs(i).tryExploding()) {
					bomberman[0].destroyBomb(i);
				}
				
			}
		}
		
		// Checar movimento do bomberman 1
		if(bomberman[0] != null) {
			if(movementB1[0] && dxy_B1[1] == 0) { // Movimento Vertical p/cima
				dxy_B1[0] = 0;
				dxy_B1[1] = -tamGrade;
				System.out.println("B1 Cima");
			}
			if(movementB1[1] && dxy_B1[0] == 0) { // Movimento Horizontal p/esquerda
				dxy_B1[0] = -tamGrade;
				dxy_B1[1] = 0;
				System.out.println("B1 Esquerda");
				
			}
			if(movementB1[2] && dxy_B1[1] == 0) { // Movimento Vertical p/cima
				dxy_B1[0] = 0;
				dxy_B1[1] = tamGrade;
				System.out.println("B1 Baixo");
			}
			if(movementB1[3] && dxy_B1[0] == 0) { // Movimento Horizontal p/esquerda
				dxy_B1[0] = tamGrade;
				dxy_B1[1] = 0;
				System.out.println("B1 Direita");
				
			}
			if(movementB1[4]) { // Coloca bomba
				bomberman[0].placeBomb();	
				System.out.println("B1 Bomba");
			}
			
			if(bomberman[0] != null) {
				bomberman[0].move(dxy_B1[0], dxy_B1[1]);
				dxy_B1[0] = 0;
				dxy_B1[1] = 0;
			}
		}
	}

	public void render(Graphics2D graphic) {
		graphic.clearRect(0, 0, LARGURA, ALTURA); // desenha background
		
		for(int i = 0; i < bomberman[0].getBombs().length; i++) {
			if(bomberman[0].getBombs(i) != null) {
				bomberman[0].getBombs(i).render(graphic);
			}
		}
		
		graphic.setColor(Color.WHITE);
		bomberman[0].render(graphic);
	}
	
	public void requestRender() {
		render(graphic);
		Graphics g = getGraphics();
		g.drawImage(image, 0, 0, null);
		g.dispose();
	}

	@Override
	public void keyPressed(KeyEvent k) {
		inputController(k, true);
	}

	@Override
	public void keyReleased(KeyEvent k) {
		inputController(k, false);
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
		if(kcode == KeyEvent.VK_C) movementB1[4] = state; // bomba bomber1
	}
}
