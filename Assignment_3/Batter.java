public class Batter {
	private String firstname, lastname;
	private int bats, hits, doubles, triples, homeruns;

	public Batter(String a, String b) {
		firstname = a;
		lastname = b;
		bats = 0;
		hits = 0;
		doubles = 0;
		triples = 0;
		homeruns = 0;

	}

	public void setBats(int n) {
		bats = n;
	}

	public void setHits(int n) {
		hits = n;
	}

	public void setDoubles(int n) {
		doubles = n;
	}

	public void setTriples(int n) {
		triples = n;
	}

	public void setHR(int n) {
		homeruns = n;
	}

	// The following method block consists of accessors to make formatting the
	// file output easier
	// -----------------------------------------//
	public String getFirst() {
		return firstname;
	}

	public String getLast() {
		return lastname;
	}

	public int getBats() {
		return bats;
	}

	public int getHits() {
		return hits;
	}

	public int getDoubles() {
		return doubles;
	}

	public int getTriples() {
		return triples;
	}

	public int getHR() {
		return homeruns;
	}

	// ------------------------------------------------------------//
	public double getAve() {
		return ((double) hits) / bats;
	}

	public String getName() {
		return (lastname + ", " + firstname);
	}

	public boolean equals(Batter b) {
		if ((b.firstname.equalsIgnoreCase(firstname))
				&& (b.lastname.equalsIgnoreCase(lastname)))
			return true;
		else
			return false;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder("");
		sb.append("Player: " + firstname + " " + lastname);
		sb.append("\n" + "  At Bats: " + bats);
		sb.append("\n" + "  Hits: " + hits);
		sb.append("\n" + "    Singles: "
				+ (hits - (doubles + triples + homeruns)));
		sb.append("\n" + "    Doubles: " + doubles);
		sb.append("\n" + "    Triples: " + triples);
		sb.append("\n" + "    Homeruns: " + homeruns);
		sb.append("\n" + "  Average: " + getAve());
		return sb.toString();
	}

}
