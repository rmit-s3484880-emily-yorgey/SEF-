package helpers;

import static org.lwjgl.opengl.GL11.*;
import java.io.IOException;
import java.io.InputStream;

import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glTexCoord2f;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Translator {

	public static final int WIDTH = 1280, HEIGHT = 960;
	//private static final String GL_TEXTURE_2D = null;
	
	public static void BeginSession() {
		Display.setTitle("Strategic Game");
		//if something went wrong, stop and print the error
		try {
		   Display.setDisplayMode(new DisplayMode(1280,960));
		   Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		glMatrixMode(GL_PROJECTION); 
		glLoadIdentity();
		//dimensions for what the screen will be
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
	
	}


	


	//drawing cells
	public static void DrawQuad(float x, float y, float width, float height) {
		glBegin(GL_QUADS);
		//parameters: beginning and end point of line = coordinates for each cell
		glVertex2f(x, y); //TOP LEFT VERTICE
		glVertex2f(x + width, y); //TOP RIGHT VERTICE
		glVertex2f(x + width, y + height); //BOTTOM RIGHT VERTICE
		glVertex2f(x, y + height); //BOTTOM LEFT VERTICE
		glEnd();
		
	}
	
	public static void DrawQuadTex(Texture t, float x, float y, float width, float height) {
		//all cells built are bound to this type of texture
		t.bind();
		glTranslatef(x, y, 0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0);
		glTexCoord2f(1, 0);
		glVertex2f(width,0);
		glTexCoord2f(1, 1);
		glVertex2f(width, height);
		glTexCoord2f(0, 1);
		glVertex2f(0,height);
		glEnd();
		glLoadIdentity();
		
	}
	
	//to access resource folder of texture tiles
	public static Texture LoadTexture(String path, String fileType) {
		Texture tex = null;
		InputStream in = ResourceLoader.getResourceAsStream(path);
		try {
			tex = TextureLoader.getTexture(fileType, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tex;
		//take external files from program
		
		
	}
	
	
	
}
	
	
	

