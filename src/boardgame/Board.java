package boardgame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.desktop.ScreenSleepEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.net.URL;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JFrame implements ActionListener {

	private URL UD = Paint.class.getResource("\\somfont\\upDow.wav");
	private URL Pong = Paint.class.getResource("\\somfont\\ppong.wav");
	private URL Fora = Paint.class.getResource("\\somfont\\fora.wav");
	private Font big = new Font("Arial", Font.BOLD, 70);
	private Grafico gr;
	private Timer time;
	private int bx = 50, by = 50, velx = 4, vely = 3, dir = 1, vida = 1;
	private int px = 10, py = 100, iax = 750, iay = 100, play0Score = 0, play1Score = 0;

	AudioClip ud = Applet.newAudioClip(UD);
	AudioClip pong = Applet.newAudioClip(Pong);
	AudioClip fora = Applet.newAudioClip(Fora);
	
	class Grafico extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			setBackground(Color.BLACK);

			// bar0
			Graphics2D bar0 = (Graphics2D) g;
			Graphics2D bar1 = (Graphics2D) g;
			Graphics2D bar2 = (Graphics2D) g;
			Graphics2D play0 = (Graphics2D) g;
			Graphics2D play1 = (Graphics2D) g;
			Graphics2D boll = (Graphics2D) g;
			Graphics2D scor = (Graphics2D) g;
			scor.setFont(big);
			if(vida > 0){
			bar0.setColor(Color.WHITE);
			bar1.setColor(Color.WHITE);
			bar2.setColor(Color.WHITE);
			play0.setColor(Color.WHITE);
			play1.setColor(Color.WHITE);
			boll.setColor(Color.WHITE);
			}else {
				py = by - 50;
				scor.drawString("PONG  JAVA",180, 220);
			}
			
			bar0.fill(new Rectangle2D.Double(40, 20, 700, 20));
			bar1.fill(new Rectangle2D.Double(40, 520, 700, 20));
			bar2.fill(new Rectangle2D.Double(400, 20, 5, 500));
			play0.fill(new Rectangle2D.Double(40, py, 20, 100));
			play1.fill(new Rectangle2D.Double(720, iay, 20, 100));
			boll.fill(new Rectangle2D.Double(bx, by, 15, 15));
			
			
			
			scor.setColor(Color.WHITE);
			scor.drawString(play0Score + "      " + play1Score, 310, 100);

		}

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Jogar();
	}

	@SuppressWarnings("deprecation")
	private void Jogar() {
		bx += velx;
		by += vely;

		if (bx > 750) {
			velx *= -1;
			play1Score++;
			pong.play();
		}
		if (bx < 60 && bx > 50 && by + 14 > py && by < py + 100) {
			velx *= -1;
			play0Score++;
			pong.play();
		}
		if (by > 500 || by <= 40) {
			vely *= -1;
			ud.play();
		}
		if (bx < -40) {
			bx = 300;
			velx = 3; // velocidade bolinha
			fora.play();
			vida--;
		}
		if (bx > 400) {
			if (by > iay + 50 && iay < 400) {
				iay += 3;
			}
			if (by < iay && iay > 40) {
				iay -= 3;
			}

		}

		gr.repaint();
		// melhora no movimento do jogador
		if (dir == 1 && py > 40) {
			py -= 3;
		} else if (dir == 2 && py < 420) {
			py += 3;
		}

	}

	public void control() {
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {

				if (arg0.getKeyCode() == 38 && py > 40) {
					// altera velocidade do jogador
					dir = 1;
				} else if (arg0.getKeyCode() == 40 && py < 420) {
					dir = 2;
				}

			}

			@Override
			public void keyPressed(KeyEvent arg0) {
				dir = 0;

			}
		});
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
		time = new Timer(2, this);
		time.start();

	}

}
