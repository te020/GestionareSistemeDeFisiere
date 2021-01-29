/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionarea.sist.de.fisiere;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 *
 * @author Ioana
 */
public class GestionareaSistDeFisiere {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.print("Select: \n LIST \n INFO \n CREATE_DIR \n COPY \n RENAME \n MOVE \n DELETE \n" );
              Scanner scan = new Scanner(System.in);
            String selection=scan.nextLine();
            selection=selection.toUpperCase();
     if (selection.equals("LIST") || selection.equals("INFO") || selection.equals("CREATE_DIR") || selection.equals("COPY") ||
             selection.equals("MOVE") || selection.equals("DELETE") || selection.equals("RENAME")) {
        
         System.out.println("good choice");
     }
     else
     {
         System.out.println("You must enter from the list");
     }
   switch (selection){
       case "CREATE_DIR": 
                System.out.print("Enter directory location: ");
                Scanner scanner=new Scanner(System.in);
                String location=scanner.nextLine();

                File testDirectory = new File(location);
                try {
                    if(!testDirectory.exists())
                       {
                        testDirectory.mkdir();
                        System.out.println("Created a directory called " + testDirectory.getName());
                       }
                    else
                       {
                        System.out.println("Directory called " + testDirectory.getName() + " already exists.");
                       }    
                } catch (Exception e) {
                    System.out.println("Couldn't create a directory called "
                    + testDirectory.getName());
                } 
            break;
       case "DELETE": 
                System.out.print("Enter folder location: ");
                Scanner input=new Scanner(System.in);
                String nameOfFolder=input.nextLine();
                File deleteDirectory = new File(nameOfFolder);
                try{
                     if(deleteDirectory.exists())
                    {   deleteDirectory.delete();
                        System.out.println("Deleted a directory called " + deleteDirectory.getName());
                    }
                     else
                    {
                         System.out.println(" Directory called " + deleteDirectory.getName()+" doesn't exist.");
                    }}
 
                catch(Exception e){
                System.out.println("Couldn't delete a directory called "+ deleteDirectory.getName());}
                    
             break;
     
      case "INFO":
                System.out.print("Enter folder location: ");
 Scanner inputinfo=new Scanner(System.in);
 String nameOfFolderInfo=inputinfo.nextLine();
  File InfoDirectory = new File(nameOfFolderInfo);
 if (InfoDirectory.exists()) {
System.out.println("Name= " + InfoDirectory.getName());
System.out.println("Path = " + InfoDirectory.getPath());
System.out.println("Lenght = " + InfoDirectory.length());

 
Instant instant = Instant.ofEpochMilli(InfoDirectory.lastModified());
            LocalDateTime dateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd. MMMM yyyy. HH:mm:ss");
             
            System.out.println("Last modified = " + dateTime.format(dateTimeFormatter));            

        }
    break;
    
      case "LIST":
          System.out.println("Directory location: ");
          Scanner scaner=new Scanner(System.in);
          String listLocation=scaner.nextLine();
          File path=new File(listLocation);
          if(!path.exists())
          {
          System.out.println("Directory does not exist!");
          }
          else{
              try{
                 if ( path.isDirectory()) {
            File[] elements = path.listFiles();
 
            for (int i = 0; i < elements.length; i++) {
                System.out.println(elements[i].getName());
            }
          } 
              }catch( Exception e){
                  System.out.println("Error");
              }
          }
          
     break;
      case "COPY":
          System.out.println("Directory/folder location: ");
          Scanner copyscanner=new Scanner(System.in);
          String source=copyscanner.nextLine();
          File srcDir=new File(source);
          
           System.out.println("Directory/folder new location: ");
          Scanner newscanner=new Scanner(System.in);
          String destination=newscanner.nextLine();
          File destDir =new File(destination);
          
    if(!srcDir.exists()){

           System.out.println("Directory does not exist.");
           
           System.exit(0);

        }else{

           try{
        	copyFolder(srcDir,destDir);
           }catch(IOException e){
        	e.printStackTrace();
        	
                System.exit(0);
           }
        }
    	
    	System.out.println("Done");
      
      break;
      case "RENAME":
          System.out.println("Directory/folder location: ");
          Scanner renscanner=new Scanner(System.in);
          String renSource=renscanner.nextLine();
          File dir=new File(renSource);
           if(!dir.exists()){

           System.out.println("Directory does not exist.");
           
           System.exit(0);

        }else{
               try{ 
                   System.out .println("Enter new name of directory(Only Name and Not Path).");
                     Scanner newscaner=new Scanner(System.in);
                    String newDirName = newscaner.nextLine();
                    
                File newDir = new File(dir.getParent() +"\\" + newDirName )   ;
                    dir.renameTo(newDir);
                  System.out.println("Done");

               }catch(Exception e){
                   System.out.println("Couldn't rename directory called "
                    + dir.getName());
                   
               }
               }
           break;
           }
          
   }
         
          
     
          
          
                   
          
          
         
    public static void copyFolder(File src, File dest)
    	throws IOException{
    	
    	if(src.isDirectory()){
    		
    		//if directory not exists, create it
    		if(!dest.exists()){
    		   dest.mkdir();
    		   System.out.println("Directory copied from " 
                              + src + "  to " + dest);
    		}
    		
    		//list all the directory contents
    		String files[] = src.list();
    		
    		for (String file : files) {
    		   //construct the src and dest file structure
    		   File srcFile = new File(src, file);
    		   File destFile = new File(dest, file);
    		   //recursive copy
    		   copyFolder(srcFile,destFile);
    		}
    	   
    	}else{
    		//if file, then copy it
    		//Use bytes stream to support all file types
    		InputStream in = new FileInputStream(src);
    	        OutputStream out = new FileOutputStream(dest); 
    	                     
    	        byte[] buffer = new byte[1024];
    	    
    	        int length;
    	        //copy the file content in bytes 
    	        while ((length = in.read(buffer)) > 0){
    	    	   out.write(buffer, 0, length);
    	        }
 
    	        in.close();
    	        out.close();
    	        System.out.println("File copied from " + src + " to " + dest);
    	}
    }
} 
   