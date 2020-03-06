// Debut du fichier ZoneDessin.java
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.lang.*;
import java.util.concurrent.TimeUnit;


class ZoneDessin extends JPanel implements MouseListener
{
	private Plateau	plateau;
	private GameState gamest;

    ZoneDessin()
    {
        plateau = new Plateau(8,6);
		addMouseListener(this);
		gamest = new GameState();
    }

		public GameState getgamest() {return gamest;}

    public void paint(Graphics g)
    {
        plateau.affiche(g);
    }

	public void mousePressed(MouseEvent e)
	{
		int x = e.getX()/75;
		int y = e.getY()/75;

		if(( e.getModifiers() & InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK )
		{
			if(gamest.getPN() == true) // Tour du joueur 1
			{
				if (gamest.getPT() == false) // Si le joueur retire une case
				{
					if (isRemoveValid(x,y) == true)
					{
				    	plateau.modifie( x, y, 1 );
						System.out.println("enleve case j1. x=" + x + ", y=" + y);
						gamest.setPT(true);
						if (gamest.getnP() == false) // MODE 2 JOUEURS : ON MODIFIE LA VALEUR DE PLAYERNUMBER
						{ gamest.setPN(!(gamest.getPN())); }
						else // MODE UN JOUEUR : ON NE MODIFIE PAS LA VALEUR DE PLAYERNUMBER ET ON LANCE LES FONCTIONS DE L'IA
						{
							int botx = gamest.getxP2();
							int boty = gamest.getyP2();
							try 
							{
								Thread.sleep(1000);
							} 
							catch(InterruptedException eu)
							{}
							Bot_Isola IA = new Bot_Isola(botx,boty,plateau,gamest);
							IA.RechercheAndMove();
							IA.RechercheAndCase();
						}
						repaint();
					} else { System.out.println("Impossible d'enlever la case au x:"+x+", y:"+y+".");}
				} else { // si Le j1 se déplace
					int tmpx = gamest.getxP1();
					int tmpy = gamest.getyP1();
					if (isDeplacementValid(tmpx, tmpy, x, y) == true)
					{	plateau.modifie(x, y, 2);
						plateau.modifie(tmpx, tmpy, 0);
						gamest.setxP1(x);
						gamest.setyP1(y);
						System.out.print("Deplacement j1: position initiale - x="+tmpx+", y="+tmpy+". Nouvelle position : x="+x+", y= "+y);
						repaint();
						gamest.setPT(false);}
					else { System.out.println("Impossible de se déplacer sur cette case. position initiale - x="+tmpx+", y="+tmpy+". Nouvelle position : x="+x+", y= "+y);}
				}
				int quiper = gameOver(gamest.getxP1(), gamest.getyP1(), gamest.getxP2(), gamest.getyP2());
				if ( quiper != 0)
				{
					//APPEL AGAIN
					System.out.println("GAME OVER!") ; 
					
					again(quiper);
				}

				} else { // Tour du joueur 2
				if (gamest.getPT() == false) { // Si le joueur 2 retire une case
					if (isRemoveValid(x,y) == true) {
				    	plateau.modifie( x, y, 1 );
						System.out.println("enleve case j2.x=" + x + ", y=" + y);
						gamest.setPT(true);
						gamest.setPN(!(gamest.getPN()));
						repaint();
					} else { System.out.println("Impossible d'enlever la case au x:"+x+", y:"+y+".");}
				} else {// Si le joueur 2 se déplace
					int tmpx = gamest.getxP2();
					int tmpy = gamest.getyP2();
					if (isDeplacementValid(tmpx, tmpy, x, y) == true)
					{	plateau.modifie(x, y, 3);
						plateau.modifie(tmpx, tmpy, 0);
						gamest.setxP2(x);
						gamest.setyP2(y);
						System.out.print("Deplacement j2 : position initiale - x="+tmpx+", y="+tmpy+". Nouvelle position : x="+x+", y= "+y);
						repaint();
						gamest.setPT(false);
					} else { System.out.println("Impossible de se déplacer sur cette case. position initiale - x="+tmpx+", y="+tmpy+". Nouvelle position : x="+x+", y= "+y);}
				}
				int quiperd = gameOver(gamest.getxP1(), gamest.getyP1(), gamest.getxP2(), gamest.getyP2());
				if (quiperd != 0)
					{ 
				System.out.println("GAME OVER!") ; 
				
				again(quiperd); }
			}
		}


	}
	
	
	public void mouseReleased(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}



	public boolean isDeplacementValid(int xb, int yb, int xn, int yn) // Vérifie si le déplacement est valide.
	{
		int[][] ptemp = plateau.getPlateauVals();
		if (((xn >= 0) && (xn <= 7)) && ((yn >=0) && (yn <=5))) { // Teste si les coordonées pointées sont dans le plateau
			if (ptemp[yn][xn] == 0)  								// Teste si la case est vide
			{
				if ((Math.abs(xb-xn)<=1) && (Math.abs(yb-yn) <=1))  // Teste si l'écart entre la position initiale et la nvelle position est inf ou égale a 1 de différence.
					{ return true; }
			}
		}
		return false;
	}



	public boolean isRemoveValid(int xr, int yr)				// Vérifie si le retrait de la case est valide
	{
		int[][] ptemp = plateau.getPlateauVals();
		if (((xr >= 0) && (xr <= 7)) && ((yr >=0) && (yr <=5))) {		// Vérifie que la case pointées est bien dans le plateau
			if (ptemp[yr][xr] == 0)										// Vérifie si la case pointée est bien vide
				{ return true; }												// Retourne vrai si la case ets bein vide.
			else
				{ return false; }
		}
		else
			return false;
	}


	public int gameOver(int p1x, int p1y, int p2x, int p2y) // Renvoie 0 si pas de game over, 1 si j1 perdu, 2 si j2 perdu, 3 si égalité
	{
		int[][] ptemp = plateau.getPlateauVals();
		boolean noMovesP1 = true;
		boolean noMovesP2 = true;
		
		int nbErrP1 = 0;
		int nbErrP2 = 0;
		int p1xMIN, p1xMAX, p1yMIN, p1yMAX, p2xMIN, p2xMAX, p2yMIN, p2yMAX;
		if (p1x == 7) {p1xMAX = 7;} else {p1xMAX = p1x+1;};
		if (p1x == 0) {p1xMIN = 0;} else {p1xMIN = p1x-1;};
		if (p1y == 0) {p1yMIN = 0;} else {p1yMIN = p1y-1;};
		if (p1y == 5) {p1yMAX = 5;} else {p1yMAX = p1y+1;};
		for (int ia = p1xMIN; ia<= p1xMAX; ia++) {  // Vérifie autour des coordonnées du j1 si une case est libre
			for (int ja = p1yMIN; ja<= p1yMAX; ja++) {
				try {if (ptemp[ja][ia] == 0)
				{ noMovesP1 = false;} }// Si oui, alors il reste des moves a P1
			catch(ArrayIndexOutOfBoundsException e)
			{ System.out.println("Cet index serait il en dehors du tableau? x="+ia+", y="+ja); noMovesP1=false; nbErrP1 ++;
			}}
		}
		

		if (p2x == 7) {p2xMAX = 7;} else {p2xMAX = p2x+1;};
		if (p2x == 0) {p2xMIN = 0;} else {p2xMIN = p2x-1;};
		if (p2y == 0) {p2yMIN = 0;} else {p2yMIN = p2y-1;};
		if (p2y == 5) {p2yMAX = 5;} else {p2yMAX = p2y+1;};
		for (int i = p2xMIN; i<= p2xMAX; i++) {
			for (int j = p2yMIN; j<= p2yMAX; j++) {
				try {if (ptemp[j][i] == 0) 
						{ noMovesP2 = false;}
				}
				catch(ArrayIndexOutOfBoundsException e)
				{ 
					System.out.println("Cet index serait il en dehors du tableau? x="+i+", y="+j);
					noMovesP2 = false;
					nbErrP2++;
				}
			}
		}

		
		
		if ((noMovesP1 == false) && (noMovesP2 == false))
			{ return 0; }
		
		if ((noMovesP1 == true) && (noMovesP2 == true))
			{ return 3; }
		else if (noMovesP1 == true)
			{ return 1; }
		else if (noMovesP2 == true)
			{ return 2; }
		else
			{ return 0; }
	}
	
	public void again(int lose)
	{
		if(lose == 1)
		{
				
				System.out.println("GAME OVER!") ; 
						
				JOptionPane lu = new JOptionPane();
					Object[] tx ={ "Rejouer", "Quiter"};
					ImageIcon icon = new ImageIcon("");
					int retour = lu.showOptionDialog(null,
					"Fin de partie : Le pion NOIR a gagner  ", "ISOLA",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE
					,null,tx,"Rejouer ");
					
					if(retour == 0)
					{
						FenetreGraphique fenetre;
						fenetre = new FenetreGraphique("Jeu");
						fenetre.zoneDessin.getgamest().setnP(true);
					}
			
		}
		
		if(lose == 2)
		{
				
				System.out.println("GAME OVER!") ; 
						
				JOptionPane lu = new JOptionPane();
					Object[] tx ={ "Rejouer", "Quiter"};
					ImageIcon icon = new ImageIcon("");
					int retour = lu.showOptionDialog(null,
					"Fin de partie : Le pion BLANC a gagner  ", "ISOLA",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE
					,null,tx,"Rejouer ");
					
					if(retour == 0)
					{
						FenetreGraphique fenetre;
						fenetre = new FenetreGraphique("Jeu");
						fenetre.zoneDessin.getgamest().setnP(true);
					}
		}
		
		if(lose == 3)
		{
				
				System.out.println("GAME OVER!") ; 
						
				JOptionPane lu = new JOptionPane();
					Object[] tx ={ "Rejouer", "Quiter"};
					ImageIcon icon = new ImageIcon("");
					int retour = lu.showOptionDialog(null,
					"Fin de partie : Egaliter  ", "ISOLA",
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE
					,null,tx,"Rejouer ");
					
					if(retour == 0)
					{
						//Jeu p = new Jeu();
						FenetreGraphique fenetre;
						fenetre = new FenetreGraphique("Jeu");
						fenetre.zoneDessin.getgamest().setnP(true);
					}
		}
		
		
	}
	}


// Fin du fichier ZoneDessin.java
