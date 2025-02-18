/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package circle_drawing;
import java.util.*;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import javax.swing.JFrame;

/**
 *
 * @author Mehadi Hassan
 */
public class Circle_Drawing {
public static void main(String args[]) {

        //getting the capabilities object of GL2 profile
        final GLProfile profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities capabilities = new GLCapabilities(profile);
        // The canvas
        final GLCanvas glcanvas = new GLCanvas(capabilities);
        Circle_Drawing_Algorithm b = new Circle_Drawing_Algorithm();
        glcanvas.addGLEventListener(b);
        glcanvas.setSize(400, 400);
        //creating frame
        final JFrame frame = new JFrame("Circle by ©Sajjad Ahmed Niloy");
        //adding canvas to frame
        frame.add(glcanvas);
        frame.setSize(640, 480);
        frame.setVisible(true);
    }
}


class Circle_Drawing_Algorithm implements GLEventListener {
    private int radius, _x, _y;
    private GLU glu;

    public void init(GLAutoDrawable gld) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("enter radius : ");
        radius = scanner.nextInt();
        System.out.println("enter x : ");
        _x = scanner.nextInt();
        System.out.println("enter y : ");
        _y = scanner.nextInt();
//        radius = 60;
//        _x = 90;
//        _y = 80;
        scanner.close();
        GL2 gl = gld.getGL().getGL2();
        glu = new GLU();

        gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        gl.glViewport(-250, -150, 250, 150);
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluOrtho2D(-250.0, 250.0, -150.0, 150.0);
    }

    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT);
        gl.glPushMatrix();
        gl.glTranslated(_x, _y, 0);
        drawCircle(gl, radius);
    }

    private void drawCircle(GL2 gl, int r) {
        int x = r, y = 0, d = -4 * r + 5;
        draw8Way(gl, x, y);
        while (y <= x) {
            if (d < 0) {
                d = d + ((2 * y + 3) * 4);
                y++;
            } else {
                d += ((-2 * x + 2 * y + 5) * 4);
                x--;
                y++;
            }
            draw8Way(gl, x, y);
        }
    }

    private void draw8Way(GL2 gl, int x, int y) {
        gl.glBegin(GL2.GL_POINTS);
        gl.glVertex2d(x, y);
        gl.glVertex2d(y, x);

        gl.glVertex2d(-x, y);
        gl.glVertex2d(-y, x);

        gl.glVertex2d(-x, -y);
        gl.glVertex2d(-y, -x);


        gl.glVertex2d(x, -y);
        gl.glVertex2d(y, -x);
        gl.glEnd();

    }


    public void reshape(GLAutoDrawable drawable, int x, int y, int width,
                        int height) {
    }

    public void dispose(GLAutoDrawable arg0) {

    }
}