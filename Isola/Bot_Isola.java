import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.lang.*;
import java.util.Random;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;




public class Bot_Isola
{
	public int IA_posX;
	public int IA_posY;
	public Plateau	plateau;
	public GameState gamest;

	//public int tab_dir[8];


	class Pos { public int X, Y;

	public Pos(int _X ,int _Y)
	{
		X = _X;
		Y= _Y;
	}

	; }

	public Bot_Isola(int x,int y ,Plateau p,GameState s)
	{

		IA_posX = x;
		IA_posY =y;
		plateau = p;
		gamest = s;
		//Player IA = new Player(IA_posX,IA_posY,'a');

		// HashMap<Integer,Pos> Hpos = new HashMap<>();
		// int casetab = 0;

		// for (int i=1; i<8; i++)
		// {
			// for (int j=1; j<8; j++)
			// {
				// Pos lapos = new Pos(i,j);
				// Hpos.put(casetab,lapos);
				// System.out.print((i-1)*7 + (j-1));
				// casetab = casetab+1;

			// }
		// }

		// System.out.println("Parcours de l'objet HashMap : ");
		// Set<Entry<Integer, Pos>> setHm = Hpos.entrySet();
		// Iterator<Entry<Integer, Pos>> it = setHm.iterator();
		// while(it.hasNext()){
         // Entry<Integer, Pos> e = it.next();
         // System.out.println(e.getKey() + " :  X= " + e.getValue().X+" , Y= "+e.getValue().Y);
      // }

	}

	//simule une validation de move
	boolean valideMove(int xb,int yb,int xn,int yn)
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


	public boolean isRetrait(int xr, int yr)				// Vérifie si le retrait de la case est valide
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

	//recherche et Move
	void RechercheAndMove()
	{
		//try 
		//{
			//Thread.sleep(1000);
		//} 
		//catch(InterruptedException e)
		//{
			 // this part is executed when an exception (in this example InterruptedException) occurs
		//}
		int x = IA_posX;
		int y = IA_posY;


			System.out.println("Position autour :");
			int x1= x-1;
			int x2= x+1;
			int y1= y-1;
			int y2= y+1;
			//les 8 possibiliter autour de l'IA
			System.out.println("x=" + x + ", y=" + y1);
			System.out.println("x=" + x + ", y=" + y2);
			System.out.println("x=" + x1 + ", y=" + y);
			System.out.println("x=" + x1 + ", y=" + y1);
			System.out.println("x=" + x1 + ", y=" + y2);
			System.out.println("x=" + x2 + ", y=" + y);
			System.out.println("x=" + x2 + ", y=" + y1);
			System.out.println("x=" + x2 + ", y=" + y2);
			Pos lapos1 = new Pos(x,y1);
			Pos lapos2 = new Pos(x,y2);
			Pos lapos3 = new Pos(x1,y);
			Pos lapos4 = new Pos(x1,y1);
			Pos lapos5 = new Pos(x1,y2);
			Pos lapos6 = new Pos(x2,y);
			Pos lapos7 = new Pos(x2,y1);
			Pos lapos8 = new Pos(x2,y2);

			Vector<Pos> dep = new Vector<Pos>();
			//System.out.println("Lapo x=" + lapos1.X + ", y=" +lapos1.Y);
			if(valideMove(IA_posX,IA_posY,lapos1.X,lapos1.Y))
			{
				dep.addElement(lapos1);
			}

			if(valideMove(IA_posX,IA_posY,lapos2.X,lapos2.Y))
			{
				dep.addElement(lapos2);
			}

			if(valideMove(IA_posX,IA_posY,lapos3.X,lapos3.Y))
			{
				dep.addElement(lapos3);
			}

			if(valideMove(IA_posX,IA_posY,lapos4.X,lapos4.Y))
			{
				dep.addElement(lapos4);
			}

			if(valideMove(IA_posX,IA_posY,lapos5.X,lapos5.Y))
			{
				dep.addElement(lapos5);
			}

			if(valideMove(IA_posX,IA_posY,lapos6.X,lapos6.Y))
			{
				dep.addElement(lapos6);
			}

			if(valideMove(IA_posX,IA_posY,lapos7.X,lapos7.Y))
			{
				dep.addElement(lapos7);
			}

			if(valideMove(IA_posX,IA_posY,lapos8.X,lapos8.Y))
			{
				dep.addElement(lapos8);
			}
			System.out.println("Position possible :");
			//
			for(int i=0;i<dep.size();i++)
			{

				System.out.println("x=" + dep.elementAt(i).X + ", y=" + dep.elementAt(i).Y);
			}

			System.out.println("Position choisi:");
			int n = (int)(Math.random()*dep.size());
			System.out.println("x=" + dep.elementAt(n).X + ", y=" + dep.elementAt(n).Y);

			int new_x=dep.elementAt(n).X;
			int new_y=dep.elementAt(n).Y;
			plateau.modifie(new_x,new_y, 3);
			plateau.modifie(x,y, 0);
			gamest.setxP2(new_x);
			gamest.setyP2(new_y);
			System.out.print("Deplacement j2 : position initiale - x="+x+", y="+y+". Nouvelle position : x="+new_x+", y= "+new_y);


	}

	void RechercheAndCase()
	{
		//try 
		//{
			//Thread.sleep(1000);
		//} 
		//catch(InterruptedException e)
		//{
			 // this part is executed when an exception (in this example InterruptedException) occurs
		//}
		int x = gamest.getxP1();
		int y = gamest.getyP1();


			System.out.println("Position autour de l'ennemi :");
			int x1= x-1;
			int x2= x+1;
			int y1= y-1;
			int y2= y+1;

			System.out.println("x=" + x + ", y=" + y1);
			System.out.println("x=" + x + ", y=" + y2);
			System.out.println("x=" + x1 + ", y=" + y);
			System.out.println("x=" + x1 + ", y=" + y1);
			System.out.println("x=" + x1 + ", y=" + y2);
			System.out.println("x=" + x2 + ", y=" + y);
			System.out.println("x=" + x2 + ", y=" + y1);
			System.out.println("x=" + x2 + ", y=" + y2);
			Pos lapos1 = new Pos(x,y1);
			Pos lapos2 = new Pos(x,y2);
			Pos lapos3 = new Pos(x1,y);
			Pos lapos4 = new Pos(x1,y1);
			Pos lapos5 = new Pos(x1,y2);
			Pos lapos6 = new Pos(x2,y);
			Pos lapos7 = new Pos(x2,y1);
			Pos lapos8 = new Pos(x2,y2);

			Vector<Pos> dep = new Vector<Pos>();
			//System.out.println("Lapo x=" + lapos1.X + ", y=" +lapos1.Y);
			if(isRetrait(lapos1.X,lapos1.Y))
			{
				dep.addElement(lapos1);
			}

			if(isRetrait(lapos2.X,lapos2.Y))
			{
				dep.addElement(lapos2);
			}

			if(isRetrait(lapos3.X,lapos3.Y))
			{
				dep.addElement(lapos3);
			}

			if(isRetrait(lapos4.X,lapos4.Y))
			{
				dep.addElement(lapos4);
			}

			if(isRetrait(lapos5.X,lapos5.Y))
			{
				dep.addElement(lapos5);
			}

			if(isRetrait(lapos6.X,lapos6.Y))
			{
				dep.addElement(lapos6);
			}

			if(isRetrait(lapos7.X,lapos7.Y))
			{
				dep.addElement(lapos7);
			}

			if(isRetrait(lapos8.X,lapos8.Y))
			{
				dep.addElement(lapos8);
			}

			System.out.println("Retrait possible :");
			//
			for(int i=0;i<dep.size();i++)
			{

				System.out.println("x=" + dep.elementAt(i).X + ", y=" + dep.elementAt(i).Y);
			}

			System.out.println("Cible choisi:");
			int n = (int)(Math.random()*dep.size());
			System.out.println("x=" + dep.elementAt(n).X + ", y=" + dep.elementAt(n).Y);

			int x_c= dep.elementAt(n).X;
			int y_c= dep.elementAt(n).Y;

			plateau.modifie( x_c, y_c, 1 );
		System.out.println("enleve case j2.x=" + x_c + ", y=" + y_c);

	}




	/*
	void Move_IA()
	{
		removeLocations = new long[49];
		for (int i=1; i<8; i++)
		{
			for (int j=1; j<8; j++)
			{
				removeLocations[(i-1)*7 + (j-1)] = GameLogic.convertCoords(i,j);
				//System.out.print((i-1)*7 + (j-1));
				System.out.print(removeLocations[(i-1)*7 + (j-1)]+"\n");

			}
		}



	}*/




	public static void main(String[] args)
	{
		//Bot_Isola IA = new Bot_Isola();
		//IA.Recherche(5,7);
	}












}
