
import javax.swing.JFrame;


public class PlayerTest
{
   public static void main( String[] args )
   {
      Player application;

      if ( args.length == 0 )
         application = new Player( "127.0.0.1" );
      else
         application = new Player( args[ 0 ] );

      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      application.runClient();
   }
}





