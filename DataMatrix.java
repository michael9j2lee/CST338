

public class DataMatrix implements BarcodeIO
{
   public static final char BLACK_CHAR = '*';
   public static final char WHITE_CHAR = ' ';
   private BarcodeImage image;
   private String text;
   private int actualWidth;
   private int actualHeight;
   
   DataMatrix()
   {
      image = new BarcodeImage();
      text = "";
      actualWidth = 0;
      actualHeight = 0;
   }
   
   DataMatrix(BarcodeImage img)
   {
      text = "";
      actualWidth = 0;
      actualHeight = 0;
      
      if(scan(img))   // Sets the image
      {
         System.out.println("Successfully scanned");
      }
      else
      {
         System.out.println("Unsucessful");
      }
      
      //image.displayToConsole();
      System.out.println();
   }
   
   DataMatrix(String txt)
   {
      image = new BarcodeImage();
      
      if(readText(txt)) // Sets text private variable
      {
         System.out.println("Text mutator successful.");
      }
      else
      {
         System.out.println("Text mutator unsuccessful.");
      }
      
      actualWidth = 0;
      actualHeight = 0;
   }
   
// Clone, cleanImage(), set actualWidth and actualHeight
   public boolean scan(BarcodeImage img)
   {
      if (img != null)
      {
         image = img.clone();
         image.displayToConsole();
         cleanImage();
         return true;
      }
      else
         return false;
   }
   
   private void cleanImage()
   {
      int point_column =0 ;  // index of the first left column
      int point_row = 0;   // index of the last bottom row

      for(int i = BarcodeImage.MAX_HEIGHT - 1; i >= 0; i--)
      {
         for(int j = 0; j < BarcodeImage.MAX_WIDTH; j++)
         {
            if (image.getPixel(i,j))
            {
               point_row = i;
               point_column = j;
               System.out.printf("true I: %d, J: %d \n", i, j);
               break;
            }
         }
         if(image.getPixel(i,point_column))  // the vertex of the signal image
            break;
      }
      System.out.println("Height away from the bottom : " + point_row);   // 12
      System.out.println("Width away from the bottom : " + point_column);  // 5
      moveImageToLowerLeft(point_row, point_column);
      image.displayToConsole();
   }
   
   private void moveImageToLowerLeft (int starting_row, int starting_column)
   {
      int row = BarcodeImage.MAX_HEIGHT-1;

      for(int i = starting_row; i >= 0; i--)
      {
         int column = 0;
         for(int j = starting_column; j < BarcodeImage.MAX_WIDTH ;j++)
         {
            image.setPixel(row, column , image.getPixel(i,j));
            image.setPixel(i, j, false);
            column++;
         }
         row--;
      }
   }
   
//  Text mutator
   public boolean readText(String text)
   {
      this.text = text;
      return true;
   }
   
// Accessor width
   public int getActualWidth()
   {
      return computeSignalWidth();
   }
   
// Accessor Height
   public int getActualHeight()
   {
      return computeSignalHeight();
   }
   
// Counting the width of the actual image
   private int computeSignalWidth()
   {
      int widthCount = 0;
      
      for (int i = 0; i < BarcodeImage.MAX_WIDTH; i++)
      {
         if(image.getPixel(BarcodeImage.MAX_HEIGHT-1,i) == true)
            widthCount++;
      }
      System.out.printf("Total Width %d%n", widthCount);
      this.actualWidth = widthCount;
      return widthCount;
   }
   
// Counting the height of the actual image
   private int computeSignalHeight()
   {
      int heightCount = 0;
      
      for (int i = 0; i < BarcodeImage.MAX_HEIGHT; i++)
      {
         if(image.getPixel(i, 0) == true)
            heightCount++;
      }
      System.out.printf("height count %d%n",heightCount);
      this.actualHeight = heightCount;
      return heightCount;
   }
   
   public boolean generateImageFromText()
   {
         return true;
   }
   public boolean translateImageToText()
   {
         return true;
   }
   public void displayTextToConsole() 
   {
      
   }
   public void displayImageToConsole()
   {
	   System.out.printf("%nactual height %d actual width %d%n", this.actualHeight, this.actualWidth);
	   for (int j = BarcodeImage.MAX_HEIGHT-this.actualHeight; j < BarcodeImage.MAX_HEIGHT; j++)
	   {
		   for (int i = 0 ; i < this.actualWidth-1; i++)
		   {
               //System.out.printf("true I: %d, J: %d %n", i, j);
			   if(this.image.getPixel(j, i))
			   {
				   System.out.print("*");
			   }
			   else
			   {
				   System.out.print(" ");
			   }
		   }
		   System.out.println();
	   }
   }
}