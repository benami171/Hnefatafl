import java.util.ArrayList;

public class Pawn extends ConcretePiece {

    private String Player1 =  "♙";
    private String Player2 =  "♟";
    private String type;
    private int eatAmount = 0;


    public Pawn (Player owner, int index){
        super(owner,index);
        if(owner.isPlayerOne()) this.type = "♙";
        this.type = "♟";
        this.type=owner.isPlayerOne()?Player1:Player2;
        eatAmount = 0;
    }





    @Override
    public String getType() {
        return type;
    }

    public void eat(){
        eatAmount++;
    }

    @Override
    public int getEatnums (){
        return eatAmount;
    }

    @Override
    public String getName(){
        String name = super.getOwner().isPlayerOne()?"D":"A";
        return name + super.getNumber();
    }


}
