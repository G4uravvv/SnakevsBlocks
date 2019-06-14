import java.io.*;

public class SaveGame {

    public static boolean isSaveGameAvailable(){
        try {
            getSavedGame();
            return true;
        } catch (IOException | ClassNotFoundException e) {
            return false;
        }
    }

    public static LeaderBoard getSavedLeaderboard() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream("leaderboard.txt"));
        return (LeaderBoard) objectInputStream.readObject();
    }

    public static void saveLeaderBoard(LeaderBoard leaderBoard) throws IOException {
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(new FileOutputStream("leaderboard.txt"));
        objectOutputStream.writeObject(leaderBoard);
    }

    public static void saveGame(Game game) throws IOException {
        ObjectOutputStream objectInputStream=new ObjectOutputStream(new FileOutputStream("savedgame.txt"));
        objectInputStream.writeObject(game);
    }

    public static Game getSavedGame() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream=new ObjectInputStream(new FileInputStream("savedgame.txt"));
        return (Game) objectInputStream.readObject();
    }

    public static void deleteSavedGame() {
        new File("savedgame.txt").delete();
    }

    public static void deleteLeaderboard() {
        new File("leaderboard.txt").delete();
    }
}
