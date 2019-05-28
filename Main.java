
public class Main 
{

	public static void main(String[] args) 
	{

	   // Total 752 Characters 16 x 47
      String[] image1 =
      {
         "                                               ",
         "                                               ",
         "                                               ",
         "     * * * * * * * * * * * * * * * * * * * * * ",
         "     *                                       * ",//128
         "     ****** **** ****** ******* ** *** *****   ",// 64
         "     *     *    ****************************** ",//32
         "     * **    * *        **  *    * * *   *     ",
         "     *   *    *  *****    *   * *   *  **  *** ",
         "     *  **     * *** **   **  *    **  ***  *  ",
         "     ***  * **   **  *   ****    *  *  ** * ** ",
         "     *****  ***  *  * *   ** ** **  *   * *    ",
         "     ***************************************** ",  
         "                                               ",
         "                                               ",
         "                                               "

      };      
      
      BarcodeImage bi = new BarcodeImage(image1);
      DataMatrix dm = new DataMatrix(bi);
      System.out.println("DisplayingImageToConsole");
      System.out.printf("height %d, width %d", dm.getActualHeight(), dm.getActualWidth());
      dm.displayImageToConsole();

	}

}
