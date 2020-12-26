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

			//bar0
			Graphics2D bar0 = (Graphics2D) g;
			bar0.setColor(Color.WHITE);
			bar0.fill(new Rectangle2D.Double(40, 20, 700, 20));
			//bar1
			Graphics2D bar1 = (Graphics2D) g;
			bar1.setColor(Color.WHITE);
			bar1.fill(new Rectangle2D.Double(40, 520, 700, 20));
			//bar2
			Graphics2D bar2 = (Graphics2D) g;
			bar2.setColor(Color.WHITE);
			bar2.fill(new Rectangle2D.Double(400, 20, 5, 500));
			//play0
			Graphics2D play0 = (Graphics2D) g;
			play0.setColor(Color.WHITE);
			play0.fill(new Rectangle2D.Double(40, 20, 20, 100));
			//play1
			Graphics2D play1 = (Graphics2D) g;
			play1.setColor(Color.WHITE);
			play1.fill(new Rectangle2D.Double(720, 20, 20, 100));
			//bolinha
			Graphics2D boll = (Graphics2D) g;
			boll.setColor(Color.WHITE);
			boll.fill(new Rectangle2D.Double(20, 20, 15, 15));
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
