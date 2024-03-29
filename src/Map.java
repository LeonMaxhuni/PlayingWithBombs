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
    Image image2 = new ImageIcon("textures//viceCityWall2.png").getImage();
    Image image3 = new ImageIcon("textures//viceCityStatic2.png").getImage();

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
        Powerups.generateRandomPowerup(7, 6, true);
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

    public static void destroyWalls(int bombX, int bombY, int bombRange, int bombColor)
    {

        String midTopLine = lineList.get(bombY-1);
        String midLine = lineList.get(bombY);
        String midBottomLine = lineList.get(bombY+1);

        if (midLine.charAt(bombX-1) == '2')
        {
            midLine = midLine.substring(0, bombX-1) + '1' + midLine.substring(bombX);
            Objects.addObject(bombX-1, bombY, 5, 1, bombColor);
        }
        else if(bombRange > 1 && bombX > 1 && midLine.charAt(bombX-2) == '2' && midLine.charAt(bombX-1) == '1')
        {
            midLine = midLine.substring(0, bombX-2) + '1' + midLine.substring(bombX-1);
            Objects.addObject(bombX-2, bombY, 5, 1, bombColor);
        }
        else if(bombRange > 2 && bombX > 2 && midLine.charAt(bombX-3) == '2' && midLine.charAt(bombX-2) == '1' && midLine.charAt(bombX-1) == '1')
        {
            midLine = midLine.substring(0, bombX-3) + '1' + midLine.substring(bombX-2);
            Objects.addObject(bombX-3, bombY, 5, 1, bombColor);
        }
        else if(bombRange > 3 && bombX > 3 && midLine.charAt(bombX-4) == '2' && midLine.charAt(bombX-3) == '1' && midLine.charAt(bombX-2) == '1' && midLine.charAt(bombX-1) == '1')
        {
            midLine = midLine.substring(0, bombX-4) + '1' + midLine.substring(bombX-3);
            Objects.addObject(bombX-4, bombY, 5, 1, bombColor);
        }
        else if(bombRange > 4 && bombX > 4 && midLine.charAt(bombX-5) == '2' && midLine.charAt(bombX-4) == '1' && midLine.charAt(bombX-3) == '1' && midLine.charAt(bombX-2) == '1' && midLine.charAt(bombX-1) == '1')
        {
            midLine = midLine.substring(0, bombX-5) + '1' + midLine.substring(bombX-4);
            Objects.addObject(bombX-5, bombY, 5, 1, bombColor);
        }
        else if(bombRange > 5 && bombX > 5 && midLine.charAt(bombX-6) == '2' && midLine.charAt(bombX-5) == '1' && midLine.charAt(bombX-4) == '1' && midLine.charAt(bombX-3) == '1' && midLine.charAt(bombX-2) == '1' && midLine.charAt(bombX-1) == '1')
        {
            midLine = midLine.substring(0, bombX-6) + '1' + midLine.substring(bombX-5);
            Objects.addObject(bombX-6, bombY, 5, 1, bombColor);
        }

        if (midLine.charAt(bombX+1) == '2')
        {
            midLine = midLine.substring(0, bombX+1) + '1' + midLine.substring(bombX+2);
            Objects.addObject(bombX+1, bombY, 5, 1, bombColor);
        }
        else if(bombRange > 1 && bombX < 13 && midLine.charAt(bombX+2) == '2' && midLine.charAt(bombX+1) == '1')
        {
            midLine = midLine.substring(0, bombX+2) + '1' + midLine.substring(bombX+3);
            Objects.addObject(bombX+2, bombY, 5, 1, bombColor);
        }
        else if(bombRange > 2 && bombX < 12 && midLine.charAt(bombX+3) == '2' && midLine.charAt(bombX+2) == '1' && midLine.charAt(bombX+1) == '1')
        {
            midLine = midLine.substring(0, bombX+3) + '1' + midLine.substring(bombX+4);
            Objects.addObject(bombX+3, bombY, 5, 1, bombColor);
        }
        else if(bombRange > 3 && bombX < 11 && midLine.charAt(bombX+4) == '2' && midLine.charAt(bombX+3) == '1' && midLine.charAt(bombX+2) == '1' && midLine.charAt(bombX+1) == '1')
        {
            midLine = midLine.substring(0, bombX+4) + '1' + midLine.substring(bombX+5);
            Objects.addObject(bombX+4, bombY, 5, 1, bombColor);
        }
        else if(bombRange > 4 && bombX < 10 && midLine.charAt(bombX+5) == '2' && midLine.charAt(bombX+4) == '1' && midLine.charAt(bombX+3) == '1' && midLine.charAt(bombX+2) == '1' && midLine.charAt(bombX+1) == '1')
        {
            midLine = midLine.substring(0, bombX+5) + '1' + midLine.substring(bombX+6);
            Objects.addObject(bombX+5, bombY, 5, 1, bombColor);
        }
        else if(bombRange > 5 && bombX < 9 && midLine.charAt(bombX+6) == '2' && midLine.charAt(bombX+5) == '1' && midLine.charAt(bombX+4) == '1' && midLine.charAt(bombX+3) == '1' && midLine.charAt(bombX+2) == '1' && midLine.charAt(bombX+1) == '1')
        {
            midLine = midLine.substring(0, bombX+6) + '1' + midLine.substring(bombX+7);
            Objects.addObject(bombX+6, bombY, 5, 1, bombColor);
        }

        lineList.set(bombY, midLine);

        //verticalRemoval

        if(midTopLine.charAt(bombX) == '2')
        {
            midTopLine = midTopLine.substring(0,bombX) + '1' + midTopLine.substring(bombX+1);
            lineList.set(bombY-1, midTopLine);
            Objects.addObject(bombX, bombY-1, 5, 2, bombColor);
        }
        else if(bombRange > 1 && bombY > 1)
        {
            String topLine = lineList.get(bombY-2);
            
            if(topLine.charAt(bombX) == '2' && midTopLine.charAt(bombX) == '1')
            {
                topLine = topLine.substring(0, bombX) + '1' + topLine.substring(bombX+1);
                lineList.set(bombY-2, topLine);
                Objects.addObject(bombX, bombY-2, 5, 2, bombColor);
            }
            else if(bombRange > 2 && bombY > 2)
            {
                String thirdTopLine = lineList.get(bombY-3);

                if(thirdTopLine.charAt(bombX) == '2' && topLine.charAt(bombX) == '1' && midTopLine.charAt(bombX) == '1')
                {
                    thirdTopLine = thirdTopLine.substring(0, bombX) + '1' + thirdTopLine.substring(bombX+1);
                    lineList.set(bombY-3, thirdTopLine);
                    Objects.addObject(bombX, bombY-3, 5, 2, bombColor);
                }
                else if(bombRange > 3 && bombY > 3)
                {
                    String fourthTopLine = lineList.get(bombY-4);

                    if(fourthTopLine.charAt(bombX) == '2' && thirdTopLine.charAt(bombX) == '1' && topLine.charAt(bombX) == '1' && midTopLine.charAt(bombX) == '1')
                    {
                        fourthTopLine = fourthTopLine.substring(0, bombX) + '1' + fourthTopLine.substring(bombX+1);
                        lineList.set(bombY-4, fourthTopLine);
                        Objects.addObject(bombX, bombY-4, 5, 2, bombColor);
                    }
                    else if(bombRange > 4 && bombY > 4)
                    {
                        String fifthTopLine = lineList.get(bombY-5);

                        if(fifthTopLine.charAt(bombX) == '2' && fourthTopLine.charAt(bombX) == '1' && thirdTopLine.charAt(bombX) == '1' && topLine.charAt(bombX) == '1' && midTopLine.charAt(bombX) == '1')
                        {
                            fifthTopLine = fifthTopLine.substring(0, bombX) + '1' + fifthTopLine.substring(bombX+1);
                            lineList.set(bombY-5, fifthTopLine);
                            Objects.addObject(bombX, bombY-5, 5, 2, bombColor);
                        }
                        else if(bombRange > 5 && bombY > 5)
                        {
                            String sixthTopLine = lineList.get(bombY-6);

                            if(sixthTopLine.charAt(bombX) == '2' && fifthTopLine.charAt(bombX) == '1' && fourthTopLine.charAt(bombX) == '1' && thirdTopLine.charAt(bombX) == '1' && topLine.charAt(bombX) == '1' && midTopLine.charAt(bombX) == '1')
                            {
                                sixthTopLine = sixthTopLine.substring(0, bombX) + '1' + sixthTopLine.substring(bombX+1);
                                lineList.set(bombY-6, sixthTopLine);
                                Objects.addObject(bombX, bombY-6, 5, 2, bombColor);
                            }
                        }
                    }
                }
            }
        }

        if (midBottomLine.charAt(bombX) == '2')
        {
            midBottomLine = midBottomLine.substring(0,bombX) + '1' + midBottomLine.substring(bombX+1);
            lineList.set(bombY+1, midBottomLine);
            Objects.addObject(bombX, bombY+1, 5, 2, bombColor);
        }
        else if(bombRange > 1 && bombY < 11)
        {
            String bottomLine = lineList.get(bombY+2);

            if(bottomLine.charAt(bombX) == '2' && midBottomLine.charAt(bombX) == '1')
            {
                bottomLine = bottomLine.substring(0, bombX) + '1' + bottomLine.substring(bombX+1);
                lineList.set(bombY+2, bottomLine);
                Objects.addObject(bombX, bombY+2, 5, 2, bombColor);
            }
            else if(bombRange > 2 && bombY < 10)
            {
                String thirdBottomLine = lineList.get(bombY+3);

                if(thirdBottomLine.charAt(bombX) == '2' && bottomLine.charAt(bombX) == '1' && midBottomLine.charAt(bombX) == '1')
                {
                    thirdBottomLine = thirdBottomLine.substring(0, bombX) + '1' + thirdBottomLine.substring(bombX+1);
                    lineList.set(bombY+3, thirdBottomLine);
                    Objects.addObject(bombX, bombY+3, 5, 2, bombColor);
                }
                else if(bombRange > 3 && bombY < 9)
                {
                    String fourthBottomLine = lineList.get(bombY+4);

                    if(fourthBottomLine.charAt(bombX) == '2' && thirdBottomLine.charAt(bombX) == '1' && bottomLine.charAt(bombX) == '1' && midBottomLine.charAt(bombX) == '1')
                    {
                        fourthBottomLine = fourthBottomLine.substring(0, bombX) + '1' + fourthBottomLine.substring(bombX+1);
                        lineList.set(bombY+4, fourthBottomLine);
                        Objects.addObject(bombX, bombY+4, 5, 2, bombColor);
                    }
                    else if(bombRange > 4 && bombY < 8)
                    {
                        String fifthBottomLine = lineList.get(bombY+5);

                        if(fifthBottomLine.charAt(bombX) == '2' && fourthBottomLine.charAt(bombX) == '1' && thirdBottomLine.charAt(bombX) == '1' && bottomLine.charAt(bombX) == '1' && midBottomLine.charAt(bombX) == '1')
                        {
                            fifthBottomLine = fifthBottomLine.substring(0, bombX) + '1' + fifthBottomLine.substring(bombX+1);
                            lineList.set(bombY+5, fifthBottomLine);
                            Objects.addObject(bombX, bombY+5, 5, 2, bombColor);
                        }
                        else if(bombRange > 5 && bombY < 7)
                        {
                            String sixthBottomLine = lineList.get(bombY+6);

                            if(sixthBottomLine.charAt(bombX) == '2' && fifthBottomLine.charAt(bombX) == '1' && fourthBottomLine.charAt(bombX) == '1' && thirdBottomLine.charAt(bombX) == '1' && bottomLine.charAt(bombX) == '1' && midBottomLine.charAt(bombX) == '1')
                            {
                                sixthBottomLine = sixthBottomLine.substring(0, bombX) + '1' + sixthBottomLine.substring(bombX+1);
                                lineList.set(bombY+6, sixthBottomLine);
                                Objects.addObject(bombX, bombY+6, 5, 2, bombColor);
                            }
                        }
                    }
                }
            }
        }
        generateSmoke(bombX, bombY, bombRange, bombColor);
    }

    public static void generateSmoke(int bombX, int bombY, int bombRange, int bombColor)
    {

        String midTopLine = lineList.get(bombY-1);
        String midLine = lineList.get(bombY);
        String midBottomLine = lineList.get(bombY+1);

        Objects.addObject(bombX, bombY, 4, 0, bombColor);

        //hotizontalGenerator

        if (midLine.charAt(bombX-1) == '1')
        {
            Objects.addObject(bombX-1, bombY, 4, 1, bombColor);

            if(Objects.getObject(bombX-1, bombY) == 5)
            {

            }
            else if(bombRange > 1 && midLine.charAt(bombX-2) == '1')
            {

                Objects.addObject(bombX-2, bombY, 4, 1, bombColor);

                if(Objects.getObject(bombX-2, bombY) == 5)
                {

                }
                else if(bombRange > 2 && midLine.charAt(bombX-3) == '1')
                {
                    Objects.addObject(bombX-3, bombY, 4, 1, bombColor);

                    if(Objects.getObject(bombX-3, bombY) == 5)
                    {

                    }
                    else if(bombRange > 3 && midLine.charAt(bombX-4) == '1')
                    {
                        Objects.addObject(bombX-4, bombY, 4, 1, bombColor);

                        if(Objects.getObject(bombX-4, bombY) == 5)
                        {

                        }
                        else if(bombRange > 4 && midLine.charAt(bombX-5) == '1')
                        {
                            Objects.addObject(bombX-5, bombY, 4, 1, bombColor);

                            if(Objects.getObject(bombX-5, bombY) == 5)
                            {

                            }
                            else if(bombRange > 5 && midLine.charAt(bombX-6) == '1')
                            {
                                Objects.addObject(bombX-6, bombY, 4, 1, bombColor);
                            }
                        }
                    }
                }
            }
        }

        if(midLine.charAt(bombX+1) == '1')
        {
            Objects.addObject(bombX+1, bombY, 4, 1, bombColor);

            if(Objects.getObject(bombX+1, bombY) == 5)
            {

            }
            else if(bombRange > 1 && midLine.charAt(bombX+2) == '1')
            {
                Objects.addObject(bombX+2, bombY, 4, 1, bombColor);

                if(Objects.getObject(bombX+2, bombY) == 5)
                {

                }
                else if(bombRange > 2 && midLine.charAt(bombX+3) == '1')
                {
                    Objects.addObject(bombX+3, bombY, 4, 1, bombColor);

                    if(Objects.getObject(bombX+3, bombY) == 5)
                    {

                    }
                    else if(bombRange > 3 && midLine.charAt(bombX+4) == '1')
                    {
                        Objects.addObject(bombX+4, bombY, 4, 1, bombColor);

                        if(Objects.getObject(bombX+4, bombY) == 5)
                        {

                        }
                        else if(bombRange > 4 && midLine.charAt(bombX+5) == '1')
                        {
                            Objects.addObject(bombX+5, bombY, 4, 1, bombColor);

                            if(Objects.getObject(bombX+5, bombY) == 5)
                            {

                            }
                            else if(bombRange > 5 && midLine.charAt(bombX+6) == '1')
                            {
                                Objects.addObject(bombX+6, bombY, 4, 1, bombColor);
                            }
                        }
                    }
                }
            }
        }

        //verticalGenerator

        if (midTopLine.charAt(bombX) == '1')
        {
            Objects.addObject(bombX, bombY-1, 4, 2, bombColor);

            if(bombY > 1 && bombRange > 1)
            {
                String topLine = lineList.get(bombY-2);

                if(Objects.getObject(bombX, bombY-1) == 5)
                {

                }
                else if(topLine.charAt(bombX) == '1')
                {
                    Objects.addObject(bombX, bombY-2, 4, 2, bombColor);

                    if(bombY > 2 && bombRange > 2)
                    {
                        String thirdTopline = lineList.get(bombY-3);

                        if(Objects.getObject(bombX, bombY-2) == 5)
                        {

                        }
                        else if(thirdTopline.charAt(bombX) == '1')
                        {
                            Objects.addObject(bombX, bombY-3, 4, 2, bombColor);

                            if(bombY > 3 && bombRange > 3)
                            {
                                String fourthTopLine = lineList.get(bombY-4);

                                if(Objects.getObject(bombX, bombY-3) == 5)
                                {

                                }
                                else if(fourthTopLine.charAt(bombX) == '1')
                                {
                                    Objects.addObject(bombX, bombY-4, 4, 2, bombColor);

                                    if(bombY > 4 && bombRange > 4)
                                    {
                                        String fifthTopLine = lineList.get(bombY-5);

                                        if(Objects.getObject(bombX, bombY-4) == 5)
                                        {

                                        }
                                        else if(fifthTopLine.charAt(bombX) == '1')
                                        {
                                            Objects.addObject(bombX, bombY-5, 4, 2, bombColor);

                                            if(bombY > 5 && bombRange > 5)
                                            {
                                                String sixthTopLine = lineList.get(bombY-6);

                                                if(Objects.getObject(bombX, bombY-5) == 5)
                                                {

                                                }
                                                else if(sixthTopLine.charAt(bombX) == '1')
                                                {
                                                    Objects.addObject(bombX, bombY-6, 4, 2, bombColor);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        if (midBottomLine.charAt(bombX) == '1')
        {
            Objects.addObject(bombX, bombY+1, 4, 2, bombColor);

            if(bombY < 11 && bombRange > 1)
            {
                String bottomLine = lineList.get(bombY+2);

                if(Objects.getObject(bombX, bombY+1) == 5)
                {

                }
                else if(bottomLine.charAt(bombX) == '1')
                {
                    Objects.addObject(bombX, bombY+2, 4, 2, bombColor);

                    if(bombY < 10 && bombRange > 2)
                    {
                        String thirdBottomline = lineList.get(bombY+3);

                        if(Objects.getObject(bombX, bombY+2) == 5)
                        {

                        }
                        else if(thirdBottomline.charAt(bombX) == '1')
                        {
                            Objects.addObject(bombX, bombY+3, 4, 2, bombColor);

                            if(bombY < 9 && bombRange > 3)
                            {
                                String fourthBottomLine = lineList.get(bombY+4);

                                if(Objects.getObject(bombX, bombY+3) == 5)
                                {

                                }
                                else if(fourthBottomLine.charAt(bombX) == '1')
                                {
                                    Objects.addObject(bombX, bombY+4, 4, 2, bombColor);

                                    if(bombY < 8 && bombRange > 4)
                                    {
                                        String fifthBottomLine = lineList.get(bombY+5);

                                        if(Objects.getObject(bombX, bombY+4) == 5)
                                        {

                                        }
                                        else if(fifthBottomLine.charAt(bombX) == '1')
                                        {
                                            Objects.addObject(bombX, bombY+5, 4, 2, bombColor);

                                            if(bombY < 7 && bombRange > 5)
                                            {
                                                String sixthBottomLine = lineList.get(bombY+6);

                                                if(Objects.getObject(bombX, bombY+5) == 5)
                                                {

                                                }
                                                else if(sixthBottomLine.charAt(bombX) == '1')
                                                {
                                                    Objects.addObject(bombX, bombY+6, 4, 2, bombColor);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
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