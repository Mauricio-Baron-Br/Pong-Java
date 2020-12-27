package boardgame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JFrame implements ActionListener {

	private Grafico gr;
	private Timer time;
	private int bx = 50, by = 50, velx = 3, vely = 3, dir = 1, vida = 5;
	private int px = 10, py = 100, iax = 750, iay = 100, play0Score = 0, play1Score = 0;

	class Grafico extends JPanel {
		@Override
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			setBackground(Color.BLACK);

			Graphics2D bar0 = (Graphics2D) g;
			Graphics2D bar1 = (Graphics2D) g;
			Graphics2D bar2 = (Graphics2D) g;
			Graphics2D play0 = (Graphics2D) g;
			Graphics2D play1 = (Graphics2D) g;
			Graphics2D boll = (Graphics2D) g;
			Graphics2D scor = (Graphics2D) g;
			Graphics2D vidas = (Graphics2D) g;
			scor.setFont( new Font("Serif", Font.BOLD, 15) );			

			if (vida > 0) {
				bar0.setColor(Color.WHITE);
				bar1.setColor(Color.WHITE);
				bar2.setColor(Color.WHITE);
				play0.setColor(Color.WHITE);
				play1.setColor(Color.WHITE);
				boll.setColor(Color.WHITE);
				scor.setColor(Color.WHITE);
				vidas.setColor(Color.WHITE);
				scor.drawString(play0Score + "      " + play1Score, 383, 60);
				vidas.drawString("vidas: " + vida , 50, 15);
			} else {
				py = by - 50;
				scor.drawString("PONG  JAVA", 180, 220);
				play('g');
			}

			bar0.fill(new Rectangle2D.Double(40, 20, 700, 20));
			bar1.fill(new Rectangle2D.Double(40, 520, 700, 20));
			bar2.fill(new Rectangle2D.Double(400, 20, 5, 500));
			play0.fill(new Rectangle2D.Double(40, py, 20, 100));
			play1.fill(new Rectangle2D.Double(720, iay, 20, 100));
			boll.fill(new Rectangle2D.Double(bx, by, 15, 15));
			

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
		time = new Timer(2, this);
		time.start();

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		jogar();
	}

	public void jogar() {
		bx += velx;
		by += vely;

		if (bx > 705) {
			velx *= -1;
			play1Score++;
			play('p');

		}
		if (bx < 60 && bx > 50 && by > py && by < py + 100) {
			velx *= -1;
			play0Score++;
			play('p');

		}
		if (by > 500 || by <= 40) {
			vely *= -1;
			play('u');

		}
		if (bx < -40) {
			bx = 300;
			velx = 3; // velocidade bolinha
			vida--;
		}
		
		if (bx > 400) {
			if (by > iay + 50 && iay < 420) {
				iay += 3;
			}
			if (by < iay && iay > 40) {
				iay -= 3;
			}

		}
		// melhora no movimento do jogador
		if (dir == 1 && py > 40) {
			py -= 3;
		} else if (dir == 2 && py < 420) {
			py += 3;
		}
		gr.repaint();

	}

	public void play(char i) {
		ExecutaSom p = new ExecutaSom();
		switch (i) {
		case 'u':
			// audio up
			p.executaSom("util/upDow.wav");
			break;
		case 'p':
			// audio pong
			p.executaSom("util/pong.wav");
			break;
		case 'f':
			// audio fora
			p.executaSom("util/fora.wav");
			break;
		default:	
		}
	}

	public class ExecutaSom {
		public void executaSom(String caminho) {
			try {
				AudioInputStream audioInputStream = AudioSystem
						.getAudioInputStream(new File(caminho).getAbsoluteFile());
				Clip clip = AudioSystem.getClip();
				clip.open(audioInputStream);
				clip.start();
			} catch (Exception ex) {
				System.out.println("Erro ao executar SOM!");
				ex.printStackTrace();
			}
		}
	}

	public void control() {
		addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				dir = 0;
			}

			@Override
			public void keyPressed(KeyEvent arg0) {

				if (arg0.getKeyCode() == 38 && py > 40) {
					// altera velocidade do jogador
					dir = 1;
				} else if (arg0.getKeyCode() == 40 && py < 420) {
					dir = 2;
				}
			}
		});
	}

}
