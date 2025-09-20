public class OtherWin implements IWinningStrategy {
    @Override
    public boolean isWinner(Player player, Board board) {
        return player.getPosition() == board.getSize() || player.getPosition() > board.getSize();
    }
    
}
