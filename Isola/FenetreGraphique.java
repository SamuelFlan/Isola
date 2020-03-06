// Debut du fichier FenetreGraphique.java

import java.awt.*;
import javax.swing.*;


class FenetreGraphique extends JFrame
{
	ZoneDessin  zoneDessin;

	FenetreGraphique(String s)
	{ 
		super(s);
		setSize(700,490);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		zoneDessin = new ZoneDessin();
        setContentPane(zoneDessin);
        setVisible(true);
	}
}


// Fin du fichier FenetreGraphique.java
