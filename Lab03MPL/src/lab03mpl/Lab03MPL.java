/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab03mpl;

/**
 *
 * CSE423 Lab 03: Line Draw using Mid Point Line Algorithm
 * Name : Mehadi Hassan
 * ID :17101177 Sec : 05
 *
 */
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;

public class Lab03MPL implements GLEventListener {

    /**
     * Interface to the GLU library.
     */
    private GLU glu;

    /**
     * Take care of initialization here.
     *
     * @param gld
     */
    @Override
    public void init(GLAutoDrawable gld) {
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();

        gl.glClearColor(1.0f, 0.0f, 0.0f, 0.0f);
        gl.glViewport(-250, -150, 250, 150);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-250.0, 250.0, -150.0, 150.0);
    }

    /**
     * Take care of drawing here.
     *
     * @param drawable
     */
    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        /*
         * put your code here
         */

        //points should be in the same zone
        DrawMPL(gl, 10, 10, 60, 50);
        //DrawMPL(gl, 10, -10, 60, -50);
        DrawMPL(gl, 50, -20, 100, -70);
        DrawMPL(gl, -30, -10, -100, -40);
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
        //do nothing
    }

    public void displayChanged(GLAutoDrawable drawable, boolean modeChanged, boolean deviceChanged) {
        //do nothing
    }

    int dx, dy, b;

    private void DrawMPL(GL2 gl, int x1, int y1, int x2, int y2) {
        //write your own code
        //using dx and dy Findzone
        //Convet zone to 0 (x0, y0) and (x1 ,y1) separetly?
        //Then apply mid point to the new coordinates?

        gl.glPointSize(5.0f);
        gl.glColor3d(0, 1, 0);
        System.out.println("Given: " + x1 + ", " + y1 + " ," + x2 + ", " + y2);


        int zone = findZone(x1, y1, x2, y2);
        
        
        int convertedZones[] = convertZone0(zone, x1, y1, x2, y2);

        x1 = convertedZones[0];
        y1 = convertedZones[1];
        x2 = convertedZones[2];
        y2 = convertedZones[3];
        
        System.out.println("Zone 0 Converted: " + x1 + ", " + y1 + " ," + x2 + ", " + y2);

        int dxUpdated = convertedZones[2] - convertedZones[0];
        int dyUpdated = convertedZones[3] - convertedZones[1];

        int d = 2 * dyUpdated - dxUpdated;
        int nE = 2 * (dyUpdated - dxUpdated);
        int e = 2 * dyUpdated;

        int x = x1;
        int y = y1;
        
        
        
        while (x <= x2) {
            //System.out.println("A");
            gl.glBegin(GL2.GL_POINTS);
            gl.glVertex2d(x, y);
            gl.glEnd();
            
            //System.out.println("B");
            x++;
            if (d > 0) {
                y++;
                d = d + nE;
            }

            else {
                d = d + e;
            }

        }
    }

    //y=mx+b
    private int func(int x, float y) {
        return (int) (dy * x - y * dx + b * dx);
    }

    int findZone(int x1, int y1, int x2, int y2) {
        int zone = 0;
        dy = y2 - y1;
        dx = x2 - x1;
        
        
        if(Math.abs(dx) > Math.abs(dy)){
            if(dx > 0 && dy > 0){
                zone = 0;
            }
            
            else if(dx < 0 && dy > 0){
                zone = 3;
            }
            
            else if(dx < 0 && dy < 0){
                zone = 4;
            }
            
            else if(dx > 0 && dy < 0){
                zone = 7;
            }

        }
        
        else{
            if(dx > 0 && dy > 0){
                zone = 1;
            }
            
            else if(dx < 0 && dy > 0){
                zone = 2;
            }
            
            else if(dx < 0 && dy < 0){
                zone = 5;
            }
            
            else if(dx > 0 && dy < 0){
                zone = 6;
            }
        }
        System.out.println("Zone: "+ zone);
        return zone;
    }

    int[] convertZone0(int zone, int x1, int y1, int x2, int y2) {
        int[] convertedZones = new int[4];
        //0 = x1 , 1 = y1 , 2 = x2 , 3 = y2
        switch (zone) {
            case 0:
                convertedZones[0] = x1;
                convertedZones[1] = y1;
                convertedZones[2] = x2;
                convertedZones[3] = y2;
                break;
                
            case 1:
                convertedZones[0] = y1;
                convertedZones[1] = x1;
                convertedZones[2] = y2;
                convertedZones[3] = x2;
                break;
                
            case 2:
                convertedZones[0] = y1;
                convertedZones[1] = -1 * x1;
                convertedZones[2] = y2;
                convertedZones[3] = -1 * x2;
                break;
                
            case 3:
                convertedZones[0] = -1 * x1;
                convertedZones[1] = y1;
                convertedZones[2] = -1 * x2;
                convertedZones[3] = y2;
                break;
            case 4:
                convertedZones[0] = -1 * x1;
                convertedZones[1] = -1 * y1;
                convertedZones[2] = -1 * x2;
                convertedZones[3] = -1 * y2;
                break;
            case 5:
                convertedZones[0] = -1 * y1;
                convertedZones[1] = -1 * x1;
                convertedZones[2] = -1 * y2;
                convertedZones[3] = -1 * x2;
                break;
            case 6:
                convertedZones[0] = -1 * y1;
                convertedZones[1] =  x1;
                convertedZones[2] = -1 * y2;
                convertedZones[3] =  x2;
                break;
            case 7:
                convertedZones[0] = x1;
                convertedZones[1] = -1 * y1;
                convertedZones[2] = x2;
                convertedZones[3] = -1 * y2;
                break;
            default:
                break;
        }
        //System.out.println("C");
        return convertedZones;

    }

//    int convertX(int x, int y, int zone) {
//        int convertedX = 0;
//        return convertedX;
//    }
//
//    int convertY(int x, int y, int zone) {
//        int convertedY = 0;
//        return convertedY;
//    }

    public void dispose(GLAutoDrawable arg0) {
        //do nothing
    }

    public static void main(String[] args) {
        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas 
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        Lab03MPL l = new Lab03MPL();
        glcanvas.addGLEventListener(l);
        glcanvas.setSize(1000, 1000);
        //creating frame
        final JFrame frame = new JFrame("Line Draw using MPL");
        //adding canvas to frame
        frame.getContentPane().add(glcanvas);
        frame.setSize(frame.getContentPane().getPreferredSize());
        frame.setVisible(true);
    }//end of main
}//end of classimport javax.media.opengl.GL2;

