import java.applet.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class AgentIntelligent extends Applet implements MouseListener, MouseMotionListener {
	// le variable de classe
	private static final int TAILLE = 40;
	int width, height;
	int mx, my;
	// coordnnner de la destination
	static int xDest = 1; //
	static int yDest = 1;
	// coordonner du depart
	static int xDepar = 1;
	static int yDepar = 1;
	// l'etat de clique
	boolean isButtonPressed = false;
	Graphics g;

	public void init() {
		setSize(new Dimension(500, 500));
		width = getSize().width;
		height = getSize().height;
		// setBackground( Color.black );

		mx = width / 2;
		my = height / 2;

		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
		System.out.println("Mouse at (" + mx + "," + my + ")");
		Case c = new Case(my, mx);
		for (int i = 0; i < sourispresse.length; i++) {
			for (int j = 0; j < sourispresse.length; j++) {
				if (sourispresse[j][i] != null) {

					if (c.egal(sourispresse[j][i])) {
						mx = sourispresse[j][i].y;
						my = sourispresse[j][i].x;
						xDest = j;
						yDest = i;

						isButtonPressed = true;
					}

				}
			}
		}
		repaint();
		e.consume();

	}

	public void mouseReleased(MouseEvent e) {
		isButtonPressed = false;
		// setBackground( Color.black );

		// repaint();
		e.consume();
	}

	public void mouseMoved(MouseEvent e) {
	}

	public void mouseDragged(MouseEvent e) {
	}

	public static final int[][] route = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 1, 1, 0, 1, 1, 1, 0, 1 }, { 1, 0, 0, 0, 0, 1, 1, 1, 0, 1 }, { 1, 0, 1, 1, 0, 0, 0, 0, 0, 1 },
			{ 1, 0, 0, 0, 0, 1, 1, 1, 0, 1 }, { 1, 0, 1, 1, 0, 0, 0, 0, 0, 1 }, { 1, 0, 1, 1, 0, 1, 1, 1, 0, 1 },
			{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	public static Case[][] sourispresse = new Case[route.length][route.length];
	public static int[][] cerveauAgent = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 },
			{ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };

	public static void supprime() {
		int[][] recent = { { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 }, { 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 1, 1, 0, 1, 1, 1, 0, 1 }, { 1, 0, 0, 0, 0, 1, 1, 1, 0, 1 }, { 1, 0, 1, 1, 0, 0, 0, 0, 0, 1 },
				{ 1, 0, 0, 0, 0, 1, 1, 1, 0, 1 }, { 1, 0, 1, 1, 0, 0, 0, 0, 0, 1 }, { 1, 0, 1, 1, 0, 1, 1, 1, 0, 1 },
				{ 1, 0, 0, 0, 0, 0, 0, 0, 0, 1 }, { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 } };
		for (int i = 0; i < route.length; i++) {
			for (int j = 0; j < route.length; j++) {
				route[i][j] = 0;

			}
		}
		for (int i = 0; i < route.length; i++) {
			for (int j = 0; j < route.length; j++) {
				route[i][j] = recent[i][j];
			}
		}
	}
/**
 * permet de trouver le chemin
 * @param x
 * @param y
 * @param chemin sauvergarde la route
 * @param g
 * @return
 * @throws InterruptedException
 */
	public static boolean trouve(int x, int y, Stack<String> chemin, Graphics g) throws InterruptedException {

		// TROUVE LA DESTINATION
		if (destination(g, x, y))
			return true;

		// REVIENT DE NOS PAS
		if (route[x][y] == 1 || route[x][y] == 2)
			return false;
		// on signal la route
		route[x][y] = 2;
		if (route[x][y] == 2) {
			cerveauAgent[x][y] = 0; // fait connaitre la route a l'agent

		}
		;
		g.setColor(Color.green);
		g.fillRect(y * TAILLE, x * TAILLE, TAILLE, TAILLE);
		Thread.sleep(500);
		g.setColor(Color.white);
		g.fillRect(y * TAILLE, x * TAILLE, TAILLE, TAILLE);
		// On affiche l'état courant du parcours
		affiche(g);

		if (cerveauAgent[xDest][yDest] == 0) { //si l'agent connait la route
			System.err.println("connait");
			// ALLERVERS NORD EST
			if (xDepar >= xDest && yDepar <= yDest) {

				// Essayons vers le haut!
				if (trouve(x - 1, y, chemin, g)) {
					chemin.push("haut");// enregistre le mouvement
					return true;
				}
				// Essayons vers la droite!
				if (trouve(x, y + 1, chemin, g)) {
					chemin.push("droite");// enregistre le mouvement
					return true;
				}
				// Essayons vers la gauche!
				if (trouve(x, y - 1, chemin, g)) {
					chemin.push("gauche");// enregistre le mouvement
					return true;
				}

				// Essayons vers le bas!
				if (trouve(x + 1, y, chemin, g)) {
					chemin.push("bas");// enregistre le mouvement
					return true;
				}
				// ALLER VERS OUEST SUD
			} else if (xDepar <= xDest && yDepar <= yDest) {
				// Essayons vers la gauche!
				if (trouve(x, y - 1, chemin, g)) {
					chemin.push("gauche");// enregistre le mouvement
					return true;
				}
				// Essayons vers le bas!
				if (trouve(x + 1, y, chemin, g)) {
					chemin.push("bas");// enregistre le mouvement
					return true;
				}

				// Essayons vers la droite!
				if (trouve(x, y + 1, chemin, g)) {
					chemin.push("droite");// enregistre le mouvement
					return true;
				}
				// Essayons vers le haut!
				if (trouve(x - 1, y, chemin, g)) {
					chemin.push("haut");// enregistre le mouvement
					return true;
				}
				// ALLER VERS OUEST-NORD
			} else if (xDepar <= xDest && yDepar >= yDest) {
				// Essayons vers la gauche!
				if (trouve(x, y - 1, chemin, g)) {
					chemin.push("gauche");// enregistre le mouvement
					return true;
				}
				// Essayons vers le haut!
				if (trouve(x - 1, y, chemin, g)) {
					chemin.push("haut");// enregistre le mouvement
					return true;
				}
				// Essayons vers la droite!
				if (trouve(x, y + 1, chemin, g)) {
					chemin.push("droite");// enregistre le mouvement
					return true;
				}
				// Essayons vers le bas!
				if (trouve(x + 1, y, chemin, g)) {
					chemin.push("bas");// enregistre le mouvement
					return true;
				}
				// aller vers SUD-EST
			} else if (xDepar >= xDest && yDepar >= yDest) {
				// Essayons vers le
				// bas!
				if (trouve(x + 1, y, chemin, g)) {
					chemin.push("bas");// enregistre le mouvement
					return true;
				}

				// Essayons vers la gauche!
				if (trouve(x, y - 1, chemin, g)) {
					chemin.push("gauche");// enregistre le mouvement
					return true;
				}

				// Essayons vers la droite!
				if (trouve(x, y + 1, chemin, g)) {
					chemin.push("droite");// enregistre le mouvement
					return true;
				}
				// Essayons vers le
				// haut!
				// Si c'est bon, on enregistre le mouvement dans la solution
				if (trouve(x - 1, y, chemin, g)) {
					chemin.push("haut");
					return true;
				}

			}
		} else {
			System.err.println("ne connait pas");
			// Essayons vers le haut!
			if (trouve(x - 1, y, chemin, g)) {
				chemin.push("haut");// enregistre le mouvement
				return true;
			}
			// Essayons vers le bas!
			if (trouve(x + 1, y, chemin, g)) {
				chemin.push("bas");// enregistre le mouvement
				return true;
			}
			// Essayons vers la gauche!
			if (trouve(x, y - 1, chemin, g)) {
				chemin.push("gauche"); // enregistre le mouvement
				return true;
			}
			// Essayons vers la droite!
			if (trouve(x, y + 1, chemin, g)) {
				chemin.push("droite");// enregistre le mouvement
				return true;
			}
		} // rien n'a fonctionner on retourne en arriere
		g.setColor(Color.green);
		g.fillRect(y * TAILLE, x * TAILLE, TAILLE, TAILLE);
		Thread.sleep(500);
		g.setColor(Color.white);
		g.fillRect(y * TAILLE, x * TAILLE, TAILLE, TAILLE);
		route[x][y] = 0;

		affiche(g);
		return false;
	}

	/**
	 * Afiiche la carte
	 * 
	 * @param g
	 *            panel graphics
	 */
	public static void affiche(Graphics g) {
		for (int i = 0; i < route.length; i++) {
			for (int j = 0; j < route.length; j++) {
				g.setColor(Color.BLUE);
				g.fillRect(yDest * TAILLE, xDest * TAILLE, TAILLE, TAILLE);
				switch (route[i][j]) {
				case 0: // Le vide est blanc
					sourispresse[i][j] = new Case(i * TAILLE, j * TAILLE);

					g.setColor(Color.white);
					g.fillRect(j * TAILLE, i * TAILLE, TAILLE, TAILLE);

					break;
				case 1: // Les murs sont noirs
					g.setColor(Color.black);
					g.fillRect(j * TAILLE, i * TAILLE, TAILLE, TAILLE);
					break;

				}

			}
		}

	}

	/**
	 * Destination pose de question pour savoir si l'agent est arriver
	 * 
	 * @param g
	 *            paint panel
	 * @param x
	 *            coordonner x
	 * @param y
	 *            coordonne y
	 * @return return true s'il a trouver la route
	 */
	public static boolean destination(Graphics g, int x, int y) {

		if (x == xDest && y == yDest) {
			g.setColor(Color.green);
			g.fillRect(y * TAILLE, x * TAILLE, TAILLE, TAILLE);
			xDepar = xDest;
			yDepar = yDest;
			return true;
		} // TROUVE LA DESTINATION
		else
			return false;

	}

	public void paint(Graphics g) {

		Stack<String> chemin = new Stack<String>();
		affiche(g);

		if (isButtonPressed) {
			g.setColor(Color.BLUE);
			g.fillRect(mx, my, 40, 40);

			supprime();

			affiche(g); // affiche la carte
			try {

				if (trouve(xDepar, yDepar, chemin, g)) { // la methode quand on
															// trouve le chemin
					// Attention les mouvements sont récupérés « à l'envers »...
					System.out.println("Le chemine est " + chemin);
				} else {
					System.out.println("pas de chemin");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		System.out.println("-------etat de cerveau-------");
		for (int i = 0; i < cerveauAgent.length; i++) {
			for (int j = 0; j < cerveauAgent.length; j++) {
				System.out.print(cerveauAgent[i][j] + " ");
			}
			System.out.println();
		}

	}
}