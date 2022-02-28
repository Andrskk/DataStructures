import org.w3c.dom.ls.LSOutput;

import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Driver {
    public static void main(String[] args) throws FileNotFoundException
    {

        //Arrays of names and dates instantiating
        String [] pName = new String[50];
        Date [] pDOB = new Date[50];


        String abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random random=new Random();
        char letter;

        for (int i=0;i<50;i++)
        {
            String name="";
            for (int j=0;j<5;j++)
            {
                letter = abc.charAt(random.nextInt(abc.length()));
                name+=letter;
            }
            String day =Integer.toString(1+random.nextInt(30));
            String month =Integer.toString(1+random.nextInt(12));
            String year =Integer.toString(1930+random.nextInt(100));
            String date=day+"-"+month+"-"+year;
            pName[i]=name;
            pDOB[i]=new Date(date);
        }

        pName[0]="Linda";
        pName[1]="Sam";
        pName[2]="Roger";
        pName[3]="Alfred";
        pName[4]="Roberto";
        pName[5]="Melissa";
        pName[6]="Brian";
        pName[7]="Thomas";
        pName[8]="Leslie";
        pName[9]="Maria";

        pDOB[0]=new Date ("1-1-2003");
        pDOB[1]=new Date ("24-2-1940");
        pDOB[2]=new Date ("11-12-1995");
        pDOB[3]=new Date ("31-3-1980");
        pDOB[4]=new Date ("29-6-1950");
        pDOB[5]=new Date ("25-7-1945");
        pDOB[6]=new Date ("15-7-2002");
        pDOB[7]=new Date ("20-7-2004");
        pDOB[8]=new Date ("27-4-1990");
        pDOB[9]=new Date ("9-5-1941");

        //long startTime = System.nanoTime();

        //output stream object initializing to append lines into log file
        PrintStream pw = new PrintStream(new FileOutputStream("out.txt",true));
        //redirecting console output to the file
        System.setOut(pw);

        //function calling and output into the file
        System.out.println("Non seniors in an increasing order:");
        displayNonSeniorsIncreasingOrder(pDOB,pName,10,6); //Big-O(n)
        System.out.println("\nNumber of seniors: "+rearrangeParticipants(pDOB,pName,10)); //Big-O(n^2)
        System.out.println("\nSeniors in an increasing order:");
        displaySeniorsIncreasingOrder(pDOB,pName,4); //Big-O(n^2)
        System.out.println("\nAll participants in an increasing order:");
        displayIncreasingOrder(pDOB,pName,10,4); //Big-O(n)

        //close stream output
        pw.close();

        /*long stopTime = System.nanoTime();
        System.out.println((stopTime - startTime)/100000);*/
    }

    public static void sortAllAscending(Date arr[],String arr2[],int n)
    {
        // Base case
        if (n <= 1)
            return;

        // recursive call n-1 times
        sortAllAscending (arr, arr2, n-1);

        Date last = arr[n-1];
        String last2=arr2[n-1];
        int j = n-2;

        //Compare the last element of subarray, if it is smaller move it left
        while (j >= 0 && arr[j].compareTo(last)==1)
        {
            arr[j+1] = arr[j];
            arr2[j+1] = arr2[j];
            j--;
        }
        arr[j+1] = last;
        arr2[j+1] = last2;
    }
    public static void sortJuniors(Date arr[],String arr2[], int n)
    {
        //sort seniors first
        sortSeniors(arr, arr2, arr.length);
        // Base case
        if (n <= 1)
            return;

        // recursive call n-1 times
        sortJuniors(arr,arr2,n-1);

        Date last = arr[n-1];
        String last2=arr2[n-1];
        int j = n-2;

        //Compare the last element of subarray, if it is smaller and junior, move it left
        while (j >=0 && arr[j].compareTo(last)==-1&& !last.isSenior()&&!arr[j].isSenior() )
        {
                arr[j+1] = arr[j];
                arr2[j+1] = arr2[j];
                j--;
        }
        arr[j+1] = last;
        arr2[j+1] = last2;
    }

    public static void sortSeniors(Date arr[],String arr2[], int n)
    {
        // Base case
        if (n <= 1)
            return;

        // recursive call n-1 times
        sortSeniors (arr, arr2, n-1);


        Date last = arr[n-1];
        String last2=arr2[n-1];
        int j = n-2;

        //Compare the last element of subarray, if it is smaller move it left
        while (j >= 0 && arr[j].compareTo(last)==1 &&last.isSenior() )
        {
            arr[j+1] = arr[j];
            arr2[j+1] = arr2[j];
            j--;
        }
        arr[j+1] = last;
        arr2[j+1] = last2;
    }

    public static int rearrangeParticipants (Date arr[],String arr2[], int n)
    {
        //sort juniors and seniors first
        sortSeniors(arr,arr2, arr.length); //n times
        sortJuniors(arr,arr2, arr.length); //n times

        //count and return seniors
        if (n==0)
            return 0;

        else
        {
            if (arr[n-1].isSenior())
            {
                return 1+rearrangeParticipants(arr,arr2,n-1);   //n times
            }
            else
            {
                return rearrangeParticipants(arr,arr2,n-1);     //n times
            }
        }
    }

    public static void displaySeniorsIncreasingOrder (Date arr[],String arr2[], int n)
    {
        //sort seniors first
        rearrangeParticipants (arr, arr2, arr.length);
        // Base case
        if (n <= 1)
            return;

        // recursive call n-1 times
        displaySeniorsIncreasingOrder( arr, arr2,n-1 );

        int j = n-2;
        //display the name and DOB of senior participants in an increasing order based on their age.
        System.out.println(arr2[j]+" "+arr[j]);
        if (!(arr[j+2].isSenior()))System.out.println(arr2[j+1]+" "+arr[j+1]);
    }

    public static void displayNonSeniorsIncreasingOrder (Date arr[],String arr2[], int n, int juniors)
    {
        //sort all participants first
        sortAllAscending(arr,arr2,arr.length);
        // Base case
        if (n <= 1)
            return;

        // recursive call n-1 times
        displayNonSeniorsIncreasingOrder(arr,arr2,n-1,juniors);

        int j = n-2;

        //display the name and DOB of non senior participants in an increasing order based on their age.
        if (!arr[j].isSenior())System.out.println(arr2[j]+" "+arr[j]);
    }
    public static void displayIncreasingOrder(Date arr[],String arr2[], int n, int seniors)
    {
        //sort all participants first
        sortAllAscending(arr,arr2,arr.length);

        // Base case
        if (n <= 1)
            return;

        // recursive call n-1 times
        displayIncreasingOrder(arr,arr2,n-1,seniors);

        int j = n-2;

        //display the name and DOB of all participants in an increasing order based on their age.
        System.out.println(arr2[j]+" "+arr[j]);
        if (j==arr.length-2)System.out.println(arr2[j+1]+" "+arr[j+1]);
    }
}