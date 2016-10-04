/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bollettini;

import java.awt.Component;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.highgui.Highgui;
import org.opencv.imgproc.Imgproc;

/**
 *
 * @author Raffaele Francesco Mancino
 */
public class BullettinCompiler {
    private Mat bullettin;
    private MainWindow mainwindow;
    
    public BullettinCompiler()
    {        
        mainwindow = new MainWindow();
        mainwindow.init(this);
        mainwindow.setVisible(true);
        
        this.loadBase();
        /*String path = getClass().getResource("bollettino.jpg").toString();
        temp = Highgui.imread(path, Highgui.CV_LOAD_IMAGE_GRAYSCALE);*/
        //Size size = new Size(1887,575);
        //bullettin = new Mat();
        //Imgproc.resize(temp, bullettin, size);
    }
    
    public void loadBase()
    {
        bullettin = Highgui.imread("E:\\Programmazione\\Bollettini\\src\\assets\\bullettin.jpg", Highgui.CV_LOAD_IMAGE_GRAYSCALE);
    }
            
    public void show()
    {
        //resize to show
        Mat resize=new Mat();
        Size size=new Size(1100, 335);
        Imgproc.resize(bullettin, resize, size);
        
        //create image
        int type = BufferedImage.TYPE_BYTE_GRAY;
        int bufferSize = resize.channels()*resize.cols()*resize.rows();
        byte [] b = new byte[bufferSize];
        resize.get(0,0,b); // get all the pixels
        BufferedImage image = new BufferedImage(resize.cols(),resize.rows(), type);
        final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
        System.arraycopy(b, 0, targetPixels, 0, b.length);  
        
        ImageIcon icon=new ImageIcon(image);
        
        //create image and show
        View view = new View();
        view.init(this);
        view.setIcon(icon);
        view.setVisible(true);
        view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
    public void save()
    {
        JFileChooser file = new JFileChooser();
        if (file.showSaveDialog(mainwindow) == JFileChooser.APPROVE_OPTION)
        {
            String path = file.getSelectedFile().getAbsolutePath() + "."; //per non generare errore su substring
            path = path.substring(0,path.indexOf(".")) + ".jpg";
            Highgui.imwrite(path , bullettin);
        }
    }
    
    private void writeCC(String CC)
    {
        Point CC1 = new Point(415, 73);
        Point CC2 = new Point(890, 73);
        Point CC3 = new Point(1425 ,73);
        
        for(int i=0; i<12 && i<CC.length(); i++)
        {
            Core.putText(bullettin, String.valueOf(CC.charAt(CC.length() - i - 1)), CC1, Core.FONT_HERSHEY_COMPLEX, 0.8, new Scalar(0, 0, 0));
            CC1.x -= 25;
            
            Core.putText(bullettin, String.valueOf(CC.charAt(CC.length() - i - 1)), CC2, Core.FONT_HERSHEY_COMPLEX, 0.8, new Scalar(0, 0, 0));
            CC2.x -= 25;
            
            Core.putText(bullettin, String.valueOf(CC.charAt(CC.length() - i - 1)), CC3, Core.FONT_HERSHEY_COMPLEX, 0.8, new Scalar(0, 0, 0));
            CC3.x -= 25;
        }
    }
    
    private void writeE(String E, String EL)
    {
        E = E.replace('.', ',');
        String part[] = E.split(",");
        /*for(int i=0;i<part.length;i++)
        {
            System.out.println(part[i]);
        }*/
        
        Point E1 = new Point(315, 125);
        Point E2 = new Point(790, 125);
        Point E3 = new Point(1795, 73);
        
        for(int i=0; i<8 && i<part[0].length(); i++)
        {
            Core.putText(bullettin, String.valueOf(part[0].charAt(part[0].length() - i - 1)), E1, Core.FONT_HERSHEY_COMPLEX, 0.8, new Scalar(0, 0, 0));
            E1.x -= 25;
            
            Core.putText(bullettin, String.valueOf(part[0].charAt(part[0].length() - i - 1)), E2, Core.FONT_HERSHEY_COMPLEX, 0.8, new Scalar(0, 0, 0));
            E2.x -= 25;
            
            Core.putText(bullettin, String.valueOf(part[0].charAt(part[0].length() - i - 1)), E3, Core.FONT_HERSHEY_COMPLEX, 0.8, new Scalar(0, 0, 0));
            E3.x -= 25;
        }
        
        Point E12 = new Point(345, 125);
        Point E22 = new Point(820, 125);
        Point E32 = new Point(1825, 73);
        if(part.length==1)
        {
            String temp[]=new String[2];
            temp[0] = part[0];
            temp[1] = "00";
            part = temp;
        }else if(part[1].length()==1)
        {
            part[1] = part[1] + "0";
        }else if(part[1].length()>2)
        {
            String temp = String.valueOf(part[1].charAt(0)) + String.valueOf(part[1].charAt(1));
            part[1] = temp;
        }
            
        for(int i=0; i<2; i++)
        {
            Core.putText(bullettin, String.valueOf(part[1].charAt(i)), E12, Core.FONT_HERSHEY_COMPLEX, 0.8, new Scalar(0, 0, 0));
            E12.x += 25;
            
            Core.putText(bullettin, String.valueOf(part[1].charAt(i)), E22, Core.FONT_HERSHEY_COMPLEX, 0.8, new Scalar(0, 0, 0));
            E22.x += 25;
            
            Core.putText(bullettin, String.valueOf(part[1].charAt(i)), E32, Core.FONT_HERSHEY_COMPLEX, 0.8, new Scalar(0, 0, 0));
            E32.x += 25;
        }
        
        String temp = "";
        for (int i=0; i<EL.length(); i++)
        {
            if (EL.charAt(i)!='/')
            {
                temp += String.valueOf(EL.charAt(i));
            }else{
                i=EL.length();
            }
        }
        
        EL=temp + "/" + part[1];
        
        this.writeELetter(EL);
    }
    
    private void writeELetter(String ELetter)
    {
        
        Point L1 = new Point(80, 160);
        Point L2 = new Point(555, 160);
        Point L3 = new Point(1240, 112);
        Core.putText(bullettin, ELetter, L1, Core.FONT_HERSHEY_COMPLEX, 0.8, new Scalar(0, 0, 0));
        Core.putText(bullettin, ELetter, L2, Core.FONT_HERSHEY_COMPLEX, 0.8, new Scalar(0, 0, 0));
        Core.putText(bullettin, ELetter, L3, Core.FONT_HERSHEY_COMPLEX, 0.8, new Scalar(0, 0, 0));
    }
     
    public void compile(String data[])
    {
        this.writeCC(data[0]);
        this.writeE(data[1], data[2]);
        
    }
}
