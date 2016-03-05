
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Random;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = -473349850293143017L;
	private Thread thread;
	private boolean running = false;
	private Random r;
	private Handler handler;
	private HUD hud;
	private Menu menu;
	private Central central;

	public static final int WIDTH = 1500, HEIGHT = WIDTH / 16 * 9;
	// Start at the menu
	public STATE gameState = STATE.MENU;

	public Game() {

		central = new Central(WIDTH, HEIGHT);
		central.setGame(this);
		hud = new HUD();
		handler = new Handler(central);
		menu = new Menu(central);
		// this.addKeyListener(new KeyInput(handler));
		// Important thing right here.
		this.addKeyListener(new KeyInput(central));
		new Window(WIDTH, HEIGHT, "WAR", central);

		r = new Random();
		if (gameState == STATE.GAME) {

		}
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;

	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
		}

	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames);
				frames = 0;
			}

		}
		stop();

	}

	private void tick() {
		handler.tick();
		if (gameState == STATE.GAME) {
			hud.tick();

			if (hud.HEALTH <= 0) {
				hud.HEALTH = 200;
				hud.setScore(0);
				hud.setLevel(1);
				gameState = STATE.END;
				handler.killEnemies();
			}
		} else if (gameState == STATE.MENU || gameState == STATE.END) {
			menu.tick();
		}

	}

	// Discalimer not present
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();

		g.setColor(new Color(20, 10, 28));
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);

		if (gameState == STATE.GAME) {
			hud.render(g);

		} else if (gameState == STATE.MENU || gameState == STATE.HELP || gameState == STATE.END) {
			menu.render(g);
		}

		g.dispose();
		bs.show();

	}

	public static void main(String[] args) throws Exception {
		// create fonts.
		new Game();
	}

	public Font getFont() {
		String currentDir = System.getProperty("user.dir");
		String path = currentDir + "\\royal-serif.ttf";
		Font f;
		try {
			f = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream(new File(path))).deriveFont(Font.PLAIN, 120);
			return f;
		} catch (FontFormatException | IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static int clamp(int var, int min, int max) {
		if (var >= max)
			return max;
		else if (var <= min)
			return min;
		else
			return var;

	}

	public static double clamp(double var, double min, double max) {
		if (var >= max)
			return max;
		else if (var <= min)
			return min;
		else
			return var;

	}

	public static float clamp(float var, float min, float max) {
		if (var >= max)
			return max;
		else if (var <= min)
			return min;
		else
			return var;

	}
	/*
	 * 
	 * public int colorT = 0;
	 * 
	 * public boolean gBack = false;
	 * 
	 * public Color getSPCColor() { if (!gBack) { colorT++; if (colorT > 254)
	 * gBack = true; } else { colorT--; if (colorT < 2) gBack = false; }
	 * 
	 * int min = colorT - 30; if (min < 1) { min = 1; }
	 * 
	 * return new Color(min + (int) (Math.random() * ((colorT - min) + 1)), (255
	 * - min) + (int) (Math.random() * (((255 - colorT) - (255 - min)) + 1)),
	 * ((int) min / 2) + (int) (Math.random() * ((((int) colorT / 2) - ((int)
	 * min / 2)) + 1))); }
	 */

}
