public class DefaultWin implements IWinningStrategy {
    @Override
    public boolean isWinner(Player player, Board board) {
        return player.getPosition() == board.getSize();
    }
    
}
