
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.Random;

public class Menu {

	private Game game;
	private Handler handler;
	private HUD hud;
	private Central central;
	private Random r = new Random();
	private int endProg = 0;
	private int selectX, selectY;
	private boolean overQuit = true;

	public Menu(Central central) {
		this.central = central;
	}
	public void init(){
		this.game = central.getGame();
		this.handler = central.getHandler();
		this.hud = central.getHud();
	}

	public void cursorUp(){
		overQuit = false;
		
	}
	
	public void cursorDown(){
		overQuit = true;
	}
	public void select(){
		if(overQuit){
			System.exit(0);
		} else {
			game.gameState = STATE.GAME;
		}
		
	}



	public void tick() {
	}

	public void render(Graphics g) {
		if (game.gameState == STATE.MENU) {
			Font font = new Font("Courier", 1, 22);
			g.setColor(new Color(240, 255, 230));
			drawStringP("war",1, 20,  g);
			drawStringP( "the card game",6, 40, g);
			drawStringP("start", 11, 60, g);
			drawStringP("quit", 16, 80, g);
			
			Graphics2D g2 = (Graphics2D) g;
			Stroke st = g2.getStroke();
			g2.setStroke(new BasicStroke(20f, 2, 2, 1f) );
			g2.setColor(game.getRandomColor(1.0f, 0.3f ));
			if(overQuit)drawRectP(15, 63, 30, 23, g2);
			else        drawRectP(11, 43, 40, 20, g2);
			//g2.drawRect((int) x, (int) y, (int) s, (int) s);
			g2.setStroke(st);
			g.setColor(game.getRandomColor(1.0f, 1.0f));
			
			
			

		} else if (game.gameState == STATE.END) {
			Font font = new Font("Courier", 1, 50);
			g.setColor(new Color(240, 0, 0));

			for (int i = 0; i < endProg; i++) {
				g.drawString("END", i - 100, 300);
				g.drawString("END", i - 50, 450);
				g.drawString("END", i, 600);
				g.drawString("END", i + 50, 750);
				g.setColor(new Color(240, 255, 246));
				g.drawString("TRY AGAIN", game.WIDTH - i, 675);
				g.setColor(new Color(240, 0, 0));
			}
			if (endProg < game.WIDTH / 2) {
				endProg += 15;
			} else {
				g.setColor(new Color(240, 255, 246));
				g.drawString("END", game.WIDTH / 2 - 100, 300);
				g.drawString("END", game.WIDTH / 2 - 50, 450);
				g.drawString("END", game.WIDTH / 2, 600);

				g.setColor(new Color(240, 0, 0));
				g.drawString("TRY AGAIN", game.WIDTH / 2, 675);
				g.setColor(new Color(240, 255, 246));
				g.drawString("END", game.WIDTH / 2 + 50, 750);
			}

			g.setColor(new Color(240, 0, 0));
			// g.drawString("END", 400, 380);
			// g.drawString("EXP "+hud.getScore(), 1000, 380);
			// g.drawString("LV "+hud.getLevel(), 1000, 460);
			// g.drawString("Try Again ", 400, 460);
			// System.out.println(hud.getLevel()+ hud.getScore());

			// g.setColor(Color.RED);
			// g.drawRect(0, 0, (int)(varX*100),(int)(varY * 100));

		} else if (game.gameState == STATE.HELP) {
			Font font = new Font("Courier", 1, 50);
			g.setColor(new Color(240, 255, 230));

		}

	}
	

	@Override
	public String toString() {
		return "Menu [game=" + game + ", handler=" + handler + ", hud=" + hud + ", central=" + central + ", r=" + r
				+ ", endProg=" + endProg + ", selectX=" + selectX + ", selectY=" + selectY + ", overQuit=" + overQuit
				+ "]";
	}

	public void drawRectP(int x, int y, int width, int height, Graphics g) {
		double varX = ((Game.WIDTH - 7) * 0.01);
		double varY = ((Game.HEIGHT - 30) * 0.01);
		g.drawRect((int) (x * varX), (int) (y * varY), (int) (width * varX), (int) (height * varY));

	}

	public void fillRectP(int x, int y, int width, int height, Graphics g) {
		double varX = ((Game.WIDTH - 7) * 0.01);
		double varY = ((Game.HEIGHT - 30) * 0.01);
		g.fillRect((int) (x * varX), (int) (y * varY), (int) (width * varX), (int) (height * varY));

	}
	public void drawStringP(String str, int x, int y, Graphics g){
		double varX = ((Game.WIDTH - 7) * 0.01);
		double varY = ((Game.HEIGHT - 30) * 0.01);
		g.drawString(str, (int) (x * varX), (int) (y * varY));
	}

}
