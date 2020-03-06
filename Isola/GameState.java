// ETAT DU JEU ISOLA

import java.util.*;
import javax.swing.*;
import java.awt.*;

public class GameState
{
	boolean PlayerTurn, PlayerNumber, nbPlayer;
	// PlayerTurn : tour du joueur. false = retirer case. true  =deplacer
	// PlayerNumber : tour de quel joueur? false = joueur 2. true = joueur 1
	// nbPlayer: nombre de joueur humain (2 ou 1). true = 1 joueur. false =2 joueurs.
	int xP1, yP1, xP2, yP2;

	GameState()
	{
		xP1 = 3;
		yP1 = 0;
		xP2 = 4;
		yP2 = 5;
		PlayerTurn = true;
		PlayerNumber = true;
		nbPlayer = true;
	}

	public int getxP1() {return xP1;}
	public int getyP1() {return yP1;}
	public int getxP2() {return xP2;}
	public int getyP2() {return yP2;}

	public boolean getPT()  {return PlayerTurn;}
	public boolean getPN()  {return PlayerNumber;}
	public boolean getnP()  {return nbPlayer;}

	public void setxP1(int _xP1) { xP1 = _xP1; }
	public void setyP1(int _yP1) { yP1 = _yP1; }
	public void setxP2(int _xP2) { xP2 = _xP2; }
	public void setyP2(int _yP2) { yP2 = _yP2; }

	public void setPT(boolean _PT) { PlayerTurn = _PT ;}
	public void setPN(boolean _PN) { PlayerNumber = _PN ;}
	public void setnP(boolean _nP) { nbPlayer = _nP ;}


}
