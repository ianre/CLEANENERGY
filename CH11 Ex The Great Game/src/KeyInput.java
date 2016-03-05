
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class KeyInput extends KeyAdapter {

	private Handler handler;
	private Game game;
	private boolean[] keyDown = new boolean[4];

	public KeyInput(Central central) {
		this.handler = central.handler;
		this.game = central.game;
		keyDown[0] = false;
		keyDown[1] = false;
		keyDown[2] = false;
		keyDown[3] = false;

	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		System.out.println(key);
		
		if(game.gameState == STATE.MENU){
			if (key == KeyEvent.VK_W);
			
		} else if(handler.game.gameState == STATE.GAME){
			for (int i = 0; i < handler.object.size(); i++) {
				try {
					GameObject tempObject = handler.object.get(i);

					if (tempObject.getId() == ID.PLAYER) {

					}
					if (tempObject.getId() == ID.SENTINEL) {

					}
				} catch (NullPointerException ex) {
					System.err.println("Error int key input class");
				}

			}

			
		}

	
		if (key == KeyEvent.VK_ESCAPE)
			System.exit(0);
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);

			if (tempObject.getId() == ID.PLAYER) {
				// key events
				if (key == KeyEvent.VK_W)
					keyDown[0] = false;// tempObject.setVelY(0);
				if (key == KeyEvent.VK_S)
					keyDown[1] = false;// tempObject.setVelY(0);
				if (key == KeyEvent.VK_D)
					keyDown[2] = false;// tempObject.setVelX(0);
				if (key == KeyEvent.VK_A)
					keyDown[3] = false;// tempObject.setVelX(0);

				// verticalMOV
				// if(!keyDown[0] && !keyDown[1]) tempObject.setVelY(0);
				// horizontalMOV
				// if(!keyDown[2] && !keyDown[3]) tempObject.setVelX(0);

			}

		}

	}

}
