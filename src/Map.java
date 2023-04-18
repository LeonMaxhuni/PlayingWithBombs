import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Map
{

    public static ArrayList<String> lineList = new ArrayList<String>();
    public int width, height;

    int blockSize = 64;

    Image image1 = new ImageIcon("textures//viceCityGround.png").getImage();
    Image image2 = new ImageIcon("textures//viceCityWall.png").getImage();
    Image image3 = new ImageIcon("textures//viceCityStatic.png").getImage();

    Image imageTextures1[] = {image1, image2, image3};

    File classic = new File("src//aaa.txt");

    long closeTimer;
    int closeX = 0;
    int closeY = 1;

    Map() throws IOException
    {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(classic));

        while (bufferedReader != null)
        {
            String line = bufferedReader.readLine();

            if (line == null)
            {
                bufferedReader.close();
                break;
            }
            if (!line.startsWith("#"))
            {
                lineList.add(line);
                width = Math.max(width, line.length());
            }
        }
        height = lineList.size();
        closeTimer = System.currentTimeMillis() + 100000;
    }
    
    public void drawMap(Graphics2D g2)
    {
        for(int y = 0; y < height ; y++)
        {

            String thisline = lineList.get(y);

            for(int x = 0; x < thisline.length(); x++)
            {
                char chch = thisline.charAt(x);

                if (chch == '1')
                {
                    g2.drawImage(imageTextures1[0], getTrueXY(x), getTrueXY(y), blockSize, blockSize, null);
                }
                if (chch == '2')
                {
                    g2.drawImage(imageTextures1[1], getTrueXY(x), getTrueXY(y), blockSize, blockSize, null);
                }
                if (chch == '3')
                {
                    g2.drawImage(imageTextures1[2], getTrueXY(x), getTrueXY(y), blockSize, blockSize, null);
                }
            }
        }
    }

    public static int getTrueXY(int x)
    {
        int y = x * 64;
        return y;
    }

    public static void destroyWalls(int bombX, int bombY, int bombRange)
    {

        String midTopLine = lineList.get(bombY-1);
        String midLine = lineList.get(bombY);
        String midBottomLine = lineList.get(bombY+1);

        if (midLine.charAt(bombX-1) == '2')
        {
            midLine = midLine.substring(0, bombX-1) + '1' + midLine.substring(bombX);
            Objects.addObject(bombX-1, bombY, 5);
        }
        else if(bombRange > 1 && bombX > 1 && midLine.charAt(bombX-2) == '2' && midLine.charAt(bombX-1) == '1')
        {
            midLine = midLine.substring(0, bombX-2) + '1' + midLine.substring(bombX-1);
            Objects.addObject(bombX-2, bombY, 5);
        }
        else if(bombRange > 2 && bombX > 2 && midLine.charAt(bombX-3) == '2' && midLine.charAt(bombX-2) == '1' && midLine.charAt(bombX-1) == '1')
        {
            midLine = midLine.substring(0, bombX-3) + '1' + midLine.substring(bombX-2);
            Objects.addObject(bombX-3, bombY, 5);
        }

        if (midLine.charAt(bombX+1) == '2')
        {
            midLine = midLine.substring(0, bombX+1) + '1' + midLine.substring(bombX+2);
            Objects.addObject(bombX+1, bombY, 5);
        }
        else if(bombRange > 1 && bombX < 13 && midLine.charAt(bombX+2) == '2' && midLine.charAt(bombX+1) == '1')
        {
            midLine = midLine.substring(0, bombX+2) + '1' + midLine.substring(bombX+3);
            Objects.addObject(bombX+2, bombY, 5);
        }
        else if(bombRange > 2 && bombX < 12 && midLine.charAt(bombX+3) == '2' && midLine.charAt(bombX+2) == '1' && midLine.charAt(bombX+1) == '1')
        {
            midLine = midLine.substring(0, bombX+3) + '1' + midLine.substring(bombX+4);
            Objects.addObject(bombX+3, bombY, 5);
        }

        lineList.set(bombY, midLine);

        //verticalRemoval

        if (midTopLine.charAt(bombX) == '2')
        {
            midTopLine = midTopLine.substring(0,bombX) + '1' + midTopLine.substring(bombX+1);
            lineList.set(bombY-1, midTopLine);
            Objects.addObject(bombX, bombY-1, 5);
        }
        else if (bombRange > 1 && bombY > 1)
        {
            String topLine = lineList.get(bombY-2);
            
            if (topLine.charAt(bombX) == '2' && midTopLine.charAt(bombX) == '1')
            {
                topLine = topLine.substring(0, bombX) + '1' + topLine.substring(bombX+1);
                lineList.set(bombY-2, topLine);
                Objects.addObject(bombX, bombY-2, 5);
            }
        }

        if (midBottomLine.charAt(bombX) == '2')
        {
            midBottomLine = midBottomLine.substring(0,bombX) + '1' + midBottomLine.substring(bombX+1);
            lineList.set(bombY+1, midBottomLine);
            Objects.addObject(bombX, bombY+1, 5);
        }
        else if (bombRange > 1 && bombY < 11)
        {
            String bottomLine = lineList.get(bombY+2);

            if (bottomLine.charAt(bombX) == '2' && midBottomLine.charAt(bombX) == '1')
            {
                bottomLine = bottomLine.substring(0, bombX) + '1' + bottomLine.substring(bombX+1);
                lineList.set(bombY+2, bottomLine);
                Objects.addObject(bombX, bombY+2, 5);
            }
        }
        generateSmoke(bombX, bombY, bombRange);
    }

    public static void generateSmoke(int bombX, int bombY, int bombRange)
    {

        String midTopLine = lineList.get(bombY-1);
        String midLine = lineList.get(bombY);
        String midBottomLine = lineList.get(bombY+1);

        Objects.addObject(bombX, bombY, 4);

        //hotizontalGenerator

        if (midLine.charAt(bombX-1) == '1')
        {
            Objects.addObject(bombX-1, bombY, 4);

            if(bombRange > 1 && midLine.charAt(bombX-2) == '1')
            {
                Objects.addObject(bombX-2, bombY, 4);
            }
        }

        if (midLine.charAt(bombX+1) == '1')
        {
            Objects.addObject(bombX+1, bombY, 4);

            if(bombRange > 1 && midLine.charAt(bombX+2) == '1')
            {
                Objects.addObject(bombX+2, bombY, 4);
            }
        }

        lineList.set(bombY, midLine);

        //verticalGenerator

        if (midTopLine.charAt(bombX) == '1')
        {
            Objects.addObject(bombX, bombY-1, 4);

            if(bombY > 1 && bombRange > 1)
            {
                String topLine = lineList.get(bombY-2);
                if(topLine.charAt(bombX) == '1')
                {
                    Objects.addObject(bombX, bombY-2, 4);
                }
            }
        }

        if (midBottomLine.charAt(bombX) == '1')
        {
            Objects.addObject(bombX, bombY+1, 4);

            if(bombY < 11 && bombRange > 1)
            {
                String bottomLine = lineList.get(bombY+2);

                if(bottomLine.charAt(bombX) == '1')
                {
                    Objects.addObject(bombX, bombY+2, 4);
                }
            }
        }
    }

    void startClosingMap()
    {
        if(closeTimer < System.currentTimeMillis() && !(closeX == 2 && closeY == 3))
        {
            closeTimer = System.currentTimeMillis() + 800;
            callSide();
        }
    }

    void callSide()
    {
        if(closeX + 1 >= closeY && closeX + closeY < 14)
        {
            closeX++;
            closeMap();
        }
        else if(closeX - 2 > closeY && closeX + closeY < 26)
        {
            closeY++;
            closeMap();
        }
        else if(closeX + closeY > 12)
        {
            closeX--;
            closeMap();
        }
        else if(closeX < closeY)
        {
            closeY--;
            closeMap();
        }
    }

    void closeMap()
    {
        String line = lineList.get(closeY);
        line = line.substring(0, closeX) + '3' + line.substring(closeX + 1);
        lineList.set(closeY, line);
    }

    public Boolean[][] getCollisionInfo(Player player)
    {
        Boolean[][] aroundInfo = new Boolean[3][3];
        String line;

        for(int row = 0; row < 3; row++)
        {
            line = lineList.get(player.blockPlayerY()-1+row);

            if(line.charAt(player.blockPlayerX()) != '1' && row != 1)
            {
                aroundInfo[1][row] = true;
                aroundInfo[0][row] = true;
                aroundInfo[2][row] = true;
            }
            else
            {
                aroundInfo[1][row] = false;
                if(line.charAt(player.blockPlayerX()-1) != '1')
                {
                    aroundInfo[0][row] = true;
                }
                else
                {
                    aroundInfo[0][row] = false;
                }
                
                if(line.charAt(player.blockPlayerX()+1) != '1')
                {
                    aroundInfo[2][row] = true;
                }
                else
                {
                    aroundInfo[2][row] = false;
                }
            }
        }
        /*for(int row = 0; row < 3; row++)
        {
            System.out.print(aroundInfo[0][row]);
            System.out.print(aroundInfo[1][row]);
            System.out.println(aroundInfo[2][row]);
        }
        */
        return aroundInfo;
    }
}
