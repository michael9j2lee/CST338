interface BarcodeIO{
	
    public boolean scan(BarcodeImage bc);
    
    public boolean readText(String text);
    
    public boolean generateImageFromText();
    
    public boolean translateImageToText();
   
    public void displayTextToConsole();
    
    public void displayImageToConsole();
    
}


public class BarcodeImage implements Cloneable
{
// Exact internal dimenions of 2D data
   public static final int MAX_HEIGHT = 30;
   public static final int MAX_WIDTH = 65;
   private boolean[][] imageData; // 30 x 65
   public int a;
   //store image.  if smaller than max, instantiate memory but leave it white
   
   //Default Constructor
   BarcodeImage()
   {
      imageData = new boolean[MAX_HEIGHT][MAX_WIDTH];
      // instantiates a 2d array (heightxwidth and adds blanks (false)
      for(int i = 0; i < MAX_HEIGHT; i++)
      {
         for(int j = 0; j < MAX_WIDTH; j++)
         {
            imageData[i][j]= false;
         }
      }
   }
   
   //Constructor with 1D array of strings.  converts to 2d array of booleans
   // Save the actual code image to the far left of the array and rest set to false
   BarcodeImage(String[] strData)
   {
      this();  // Create all false 2D array 
      int strData_len = strData.length; // 16 for the arrays in main

      //int counter = 0; // counting characters

      if (checkSize(strData)) // if the array fits in internal array
      {
         for(int i = 0; i < strData_len; i++)   // iterating through each index of the passing array
         { 
            for (int j = 0; j < strData[i].length(); j++)
            {
               if (strData[i].charAt(j) == ' ')
               {
                  setPixel(i, j, false);
                  //System.out.println(counter + "| height : " + internal_height + " width : " + j  + " FALSE ");
                  //counter++;
               }
               else if (strData[i].charAt(j) == '*')
               {
                  setPixel(i, j, true);
                  //System.out.println(counter + "| height : " + internal_height + " width : " + j  + " TRUE");
                  //counter++;
               }
            }
         }
      }else
      {
         System.out.println("TOO BIG");
      }
      
      // displayToConsole();
   }
   
// insert value into the imageData 2d array.
   public boolean setPixel(int row, int col, boolean value)
   {
      if( row < MAX_HEIGHT && col < MAX_WIDTH)
      {
         this.imageData[row][col] = value;   // added this.
         return true;
      }
      else
      {
         return false;
      }
   }
   
   // returns the pixel at a given location
   public boolean getPixel(int row, int col) 
   {
      return this.imageData[row][col];
   }
   
 //debugging without the spine.
   public void displayToConsole() 
   {
      for(int i = 0; i < MAX_HEIGHT  ; i++)
      {
         for (int j = 0; j < MAX_WIDTH; j++)
         {
            if (getPixel(i, j))
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
   
   // TODO change the if conditional to more applicable one
   //Check to see if it is smaller, bigger or null is not
   private boolean checkSize(String[] data)
   {
      if(data.length > (30))  // Was origninally (30*65)
      {   
         System.out.println("Array too big. Array length bigger than 30");
         return false;
      }
      else
         return true;
   }
   
   public BarcodeImage clone()
   {
      BarcodeImage temp;
      
      try
      {
         temp = (BarcodeImage) super.clone();
         temp.imageData = this.imageData.clone();   // Deep cloning
      }
      catch (CloneNotSupportedException e)
      {
         System.out.println("Clone failed");
         temp = null;
      }
      
      return temp;
   }
}   