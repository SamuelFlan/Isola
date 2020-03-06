// D�but du fichier Jeu.java
import java.util.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.JLayeredPane;
class Jeu
{
	static FenetreGraphique fenetre;
	

	public static void main(String[] args)
	{
		JOptionPane d = new JOptionPane(); // les textes figurant // sur les boutons
		String lesTextes[]={ "joueur VS joueur", "joueur VS IA"}; // indice du bouton qui a été // cliqué ou CLOSED_OPTION
		int retour = d.showOptionDialog(null, "Bienvenue sur le jeu ISOLA", "ISOLA",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,lesTextes,"joueur VS joueur");
		System.out.println(retour);
		if(retour == 1)
		{
			fenetre = new FenetreGraphique("Jeu");
			fenetre.zoneDessin.getgamest().setnP(true);
		}

		if(retour == 0)
		{
			fenetre = new FenetreGraphique("Jeu");
			fenetre.zoneDessin.getgamest().setnP(false);
		}

	}
}

// Fin du fichier Jeu.java
