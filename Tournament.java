public class Tournament{

    TreeNode _leaderboard;
    int numPlayers;

    public Tournament(){
	_leaderboard = new TreeNode();
	numPlayers = 4;
    }

    public Tournament( int participants ){
	numPlayers = participants;
    }

    public void createLB( Player x ){
	
    }

    public Player search( TreeNode current, Player x ){
	if (current.getValue() != x){
	    if (current.getLeft() == null && current.getRight() == null){
		return null;
	    }
	    else{
	    }
	}
	else{
	    return current.getValue();
	}
    }

    public void play(){
	System.out.println("Welcome to the UNO Tournament!");
    }
}
