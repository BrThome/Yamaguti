import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("BombermanFX"); // Cria frame sob o nome XXX
		frame.setContentPane(new Board()); // O conteúdo dessa Frame se torna um PainelJogo (Painel de Jogo por herança é um JPanel)
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Determina que nada acontece ao apertar para fechar a janela
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent event) {
				// O que acontece ao fechar a janela
			}
		});
		frame.setResizable(false); // Torna o tamanho da janela inalterável
		frame.pack(); // Causa a janela a tomar o tamanho ideal para acomodar seus componentes
		frame.setPreferredSize(new Dimension(Board.LARGURA, Board.ALTURA)); // Muda o tamanho preferencial da janela para o mesmo do Painel de Jogo
		frame.setVisible(true); // Torna a Janela visível
	}

}
