
public class CollisionDetection
{
    
    CollisionDetection()
    {

    }

    public static void checkDetectionLeft(Player player)
    {
        if(
            (player.collisionInfo[0][1] == true && player.playerX+8 == player.blockPlayerX()*64) ||
            (player.collisionInfo[0][0] == true && player.playerX+8 == player.blockPlayerX()*64 && player.playerY+8 < player.blockPlayerY()*64) ||
            (player.collisionInfo[0][2] == true && player.playerX+8 == player.blockPlayerX()*64 && player.playerY+56 > player.blockPlayerY()*64+64)
          )
        {
            player.Xspeed = 0;
        }
        else if
          (
            (player.collisionInfo[0][1] == true && player.playerX+4 < player.blockPlayerX()*64) ||
            (player.collisionInfo[0][0] == true && player.playerX+4 < player.blockPlayerX()*64 && player.playerY+8 < player.blockPlayerY()*64) ||
            (player.collisionInfo[0][2] == true && player.playerX+4 < player.blockPlayerX()*64 && player.playerY+56 > player.blockPlayerY()*64+64)
          )
        {
            player.playerX = player.playerX - (player.playerX % 4);
            player.Xspeed = 0;
        }
        else
        {
            player.Xspeed = player.currentSpeed;
        }
    }

    public static void checkDetectionRight(Player player)
    {
        if(
            (player.collisionInfo[2][1] == true && player.playerX+56 == player.blockPlayerX()*64+64) ||
            (player.collisionInfo[2][0] == true && player.playerX+56 == player.blockPlayerX()*64+64 && player.playerY+8 < player.blockPlayerY()*64) ||
            (player.collisionInfo[2][2] == true && player.playerX+56 == player.blockPlayerX()*64+64 && player.playerY+56 > player.blockPlayerY()*64+64)
          )
        {
            player.Xspeed = 0;
        }
        else if
          (
            (player.collisionInfo[2][1] == true && player.playerX+60 > player.blockPlayerX()*64+64) ||
            (player.collisionInfo[2][0] == true && player.playerX+60 > player.blockPlayerX()*64+64 && player.playerY+8 < player.blockPlayerY()*64) ||
            (player.collisionInfo[2][2] == true && player.playerX+60 > player.blockPlayerX()*64+64 && player.playerY+56 > player.blockPlayerY()*64+64)
          )
        {
            player.playerX = player.playerX - (player.playerX % 4) + 4;
            player.Xspeed = 0;
        }
        else
        {
            player.Xspeed = player.currentSpeed;
        }
    }

    public static void checkDetectionTop(Player player)
    {
        if(
            (player.collisionInfo[1][0] == true && player.playerY+8 == player.blockPlayerY()*64) ||
            (player.collisionInfo[0][0] == true && player.playerY+8 == player.blockPlayerY()*64 && player.playerX+8 < player.blockPlayerX()*64) ||
            (player.collisionInfo[2][0] == true && player.playerY+8 == player.blockPlayerY()*64 && player.playerX+56 > player.blockPlayerX()*64+64)
          )
        {
            player.Yspeed = 0;
        }
        else if
          (
            (player.collisionInfo[1][0] == true && player.playerY+4 < player.blockPlayerY()*64) ||
            (player.collisionInfo[0][0] == true && player.playerY+4 < player.blockPlayerY()*64 && player.playerX+8 < player.blockPlayerX()*64) ||
            (player.collisionInfo[2][0] == true && player.playerY+4 < player.blockPlayerY()*64 && player.playerX+56 > player.blockPlayerX()*64+64)
          )
        {
            player.playerY = player.playerY - (player.playerY % 4);
            player.Yspeed = 0;
        }
        else
        {
            player.Yspeed = player.currentSpeed;
        }
    }

    public static void checkDetectionBottom(Player player)
    {
        if(
            (player.collisionInfo[1][2] == true && player.playerY+56 == player.blockPlayerY()*64+64) ||
            (player.collisionInfo[0][2] == true && player.playerY+56 == player.blockPlayerY()*64+64 && player.playerX+8 < player.blockPlayerX()*64) ||
            (player.collisionInfo[2][2] == true && player.playerY+56 == player.blockPlayerY()*64+64 && player.playerX+56 > player.blockPlayerX()*64+64)
          )
        {
            player.Yspeed = 0;
        }
        else if
          (
            (player.collisionInfo[1][2] == true && player.playerY+60 > player.blockPlayerY()*64+64) ||
            (player.collisionInfo[0][2] == true && player.playerY+60 > player.blockPlayerY()*64+64 && player.playerX+8 < player.blockPlayerX()*64) ||
            (player.collisionInfo[2][2] == true && player.playerY+60 > player.blockPlayerY()*64+64 && player.playerX+56 > player.blockPlayerX()*64+64)
          )
        {
            player.playerY = player.playerY - (player.playerY % 4) + 4;
            player.Yspeed = 0;
        }
        else
        {
            player.Yspeed = player.currentSpeed;
        }
    }
}