public class King extends ConcretePiece {

    private final String type = "♔";

    public King(Player player,int index){
        super(player,index);
    }


    public String getType (){
        return this.type;
    }

    @Override
    public int getEatnums (){
        return 0;
    }

    @Override
    public String getName (){
        return "K7";
    }



}