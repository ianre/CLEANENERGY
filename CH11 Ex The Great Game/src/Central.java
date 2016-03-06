

public class Central {
	public Handler handler;
	public Game game;
	public Menu menu;
	public HUD hud;
	public int w, h;
	

	
	public Central(int w, int h){
		this.w = w;
		this.h= h;
	}
	public Central(Handler handler, Game game, Menu menu, HUD hud ){
		this.handler = handler;
		this.game = game;
		this.menu = menu;
		this.hud = hud;
		
	}
	public double get100X(){
		return w*0.01;
	}
	public double get100Y(){
		return h*0.01;
	}

	public Handler getHandler() {
		return handler;
	}

	public Game getGame() {
		return game;
	}

	public Menu getMenu() {
		return menu;
	}

	public HUD getHud() {
		return hud;
	}

	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public void setHud(HUD hud) {
		this.hud = hud;
	}
	
	

}
