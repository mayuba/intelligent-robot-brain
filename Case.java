
public class Case {
	int x, y;

	public Case(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public boolean egal(Case c) {

		if (x >= c.x && x <= c.x + 40 && y >= c.y && y <= c.y + 40)
			return true;
		else
			return false;
	}

	public String toString() {
		return "(" + x + " " + y + ")";
	}
}
