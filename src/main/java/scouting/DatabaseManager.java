package scouting;

public class DatabaseManager {
	private static final DatabaseManager instance = new DatabaseManager();

	private DatabaseManager() {

	}

	public static DatabaseManager get() {
		return instance;
	}

}
