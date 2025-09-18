public class Game {
    Player[] players;
    int indexOfCurrentPlayer;
    Board board;
    Dice dice;
    public Game(Player[] players, Board board, Dice dice) {
        this.players = players;
        this.board = board;
        this.dice = dice;
    }
    public void play(){
        while(true){
            Player currentPlayer = players[indexOfCurrentPlayer];
            int diceRoll = dice.roll();
            int newPosition = currentPlayer.getPosition() + diceRoll;
            if(newPosition > board.getSize()){ 
                newPosition = currentPlayer.getPosition();
                currentPlayer.setPosition(newPosition); 
            }
            else{
                if(newPosition == board.getSize()){
                    System.out.println(currentPlayer.getName() + " wins!");
                    break;
                }
                else{
                    BoardEntity entity = board.getEntity(newPosition);
                    if(entity != null){
                        newPosition = entity.getEnd();
                    }
                    currentPlayer.setPosition(newPosition);
                    System.out.println(currentPlayer.getName() + " rolled a " + diceRoll + " and moved to position " + newPosition);
                }
            }
            indexOfCurrentPlayer = (indexOfCurrentPlayer + 1) % players.length;
        }
    }
}
