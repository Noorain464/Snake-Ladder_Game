public class Game {
    Player[] players;
    int indexOfCurrentPlayer;
    Board board;
    Dice dice;
    IWinningStrategy winningStrategy;
    IStartingStrategy startingStrategy;
    public Game(GameBuilder b) {
        this.players = b.players;
        this.board = b.board;
        this.dice = b.dice;
        this.winningStrategy = b.winningStrategy;
        this.startingStrategy = b.startingStrategy;
        this.indexOfCurrentPlayer = 0;
    }
    public static class GameBuilder{
        Player[] players;
        Board board;
        Dice dice;
        IWinningStrategy winningStrategy;
        IStartingStrategy startingStrategy;
        public GameBuilder setPlayers(Player[] players){
            this.players = players;
            return this;
        }
        public GameBuilder setBoard(Board board){
            this.board = board;
            return this;
        }
        public GameBuilder setDice(Dice dice){
            this.dice = dice;
            return this;
        }
        public GameBuilder setWinningStrategy(IWinningStrategy winningStrategy){
            this.winningStrategy = winningStrategy;
            return this;
        }
        public GameBuilder setStartingStrategy(IStartingStrategy startingStrategy){
            this.startingStrategy = startingStrategy;
            return this;
        }
        public Game build(){
            return new Game(this);
        }
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
