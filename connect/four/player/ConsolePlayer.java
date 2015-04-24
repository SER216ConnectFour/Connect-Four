
package connect.four.player;
import connect.four.board.ReadableBoard;
import connect.four.board.ReadWritableBoard;
import connect.four.Game;
import connect.four.ScoreChart;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;


public class ConsolePlayer implements Player, ScoreChart.Listener {
    String m_name;

    public ConsolePlayer(String name) {
        m_name = name;
    }

    @Override public String getName() {
        return m_name;
    }
    
    public void setName(String m_name){
            this.m_name = m_name;
    }

    @Override public void gameOver(Player winner, ScoreChart scores, ReadableBoard board) {
        System.out.println(m_name + (winner == this ? " won." : " lost."));
        dumpBoard(board);
        System.out.println(m_name + ": " + scores.getScore(this));
    }

    @Override public void performPlay(ReadWritableBoard board) {
        int width = board.getWidth();
        int height = board.getHeight();

        System.out.println("\n"+m_name+"'s turn!");
        dumpBoard(board);

        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        int x = 0;
        while (x < 1 || x > width) {
            try {
                System.out.print("Enter the column you want to play in: ");
                x = Integer.parseInt(stdin.readLine());
            } catch (IOException e) {
                // loop again.
            } catch (NumberFormatException e) {
                // loop again.
            }
        }

        board.play(x-1, this);
    }

    private void dumpBoard(ReadableBoard board) {
        int width = board.getWidth();
        int height = board.getHeight();

        System.out.println("@ is you, X is the other player, and O is empty.");
        for (int i = height-1; i != -1; --i) {
            for (int j = 0; j != width; ++j) {
                Player played = board.whoPlayed(j, i);
                System.out.print(
                    played == this ? "@" :
                    played == null ? "O" : "X"
                );
            }
            System.out.println();
     
        }
        System.out.println();
    }
}
