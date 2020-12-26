package boardgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Board extends JFrame {

	Grafico gr;

	class Grafico extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			setBackground(Color.BLACK);

			Graphics2D boll = (Graphics2D) g;
			boll.setColor(Color.WHITE);
			boll.fill(new Rectangle2D.Double(20, 20, 20, 20));

		}

	}

	public void Janela() {
		setTitle("Pong-Java");
		setSize(800, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		gr = new Grafico();
		add(gr);

	}

}
