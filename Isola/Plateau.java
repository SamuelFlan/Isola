import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Plateau
{
	public int		nbx, nby;
	public int[][]	valeurs;
	private Image	crate0, crate1, pers1, pers2;

	public Plateau( int _nbx, int _nby )
	{
		nbx = _nbx;
		nby = _nby;

		valeurs = new int [nby][nbx];

		valeurs[0][3] = 2;
		valeurs[5][4] = 3;

		try {
			crate0 = ImageIO.read(new File("crate0.png"));
			crate1 = ImageIO.read(new File("crate1.png"));
			pers1 = ImageIO.read(new File("perso_1.png"));
			pers2 = ImageIO.read(new File("perso_2.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}


	public void affiche(Graphics g)
	{
		for( int y=0; y<nby; y++ )
		{
			for( int x=0; x<nbx; x++ )
			{
				if( valeurs[y][x] == 0 )//case vide
					g.drawImage(crate0, x*75, y*75, null);
				else if( valeurs[y][x] == 1)//case elimini
					g.drawImage(crate1, x*75, y*75, null);
				else if( valeurs[y][x] == 2)//blanc
					g.drawImage(pers1, x*75, y*75, null);
				else if( valeurs[y][x] == 3)//noir
					g.drawImage(pers2, x*75, y*75, null);
			}
		}
	}

	public void modifie( int x, int y, int val )
	{
		if( x>=0 && x<nbx && y>=0 && y<nby )
		{
			valeurs[y][x] = val;
		}
	}

	public int[][] getPlateauVals()
	{ return valeurs; }

	public int getPlateauSizeX()
	{ return nbx; }

	public int getPlateauSizeY()
	{ return nby; }
}
