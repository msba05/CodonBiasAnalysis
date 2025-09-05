/**
 * This program conducts a codon bias analysis for the COVID-19 genome using my amino acid class
 * @Mariam Barry
 * 4-24-205
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;
import java.util.Scanner;
public class Main
{
    public static void main(String[] args) throws IOException {
        Scanner aaFile = new Scanner(new File("aminoAcidTable.csv")); //create a Scanner file for the Amino Acid
        ArrayList<AminoAcid> aminoList = new ArrayList<>(); //create an Arraylist for the amino Acids
        aaFile.nextLine(); //Skip the first line of headers in the AminoAcid table
        while (aaFile.hasNext())
        {
            String lineOne = aaFile.nextLine();
            String[] parts = lineOne.split(","); //split the csv on the commas
            String name = parts[0]; //Store the name found at sub 0 in name
            String oneLetter = parts[2]; //store the single letter found at sub 2 in oneLetter
            ArrayList<String> codon = new ArrayList<>(); // make ArrayList for codons
            for (int i = 3; i < parts.length; i++) //store the amino acids codons found at sub 3 and on into the ArrayList made above
            {
                codon.add(parts[i]);
            }
            AminoAcid amino = new AminoAcid(name, oneLetter, codon); //I'm making an instance of my Amino Acid class
            aminoList.add(amino);
        }
        aaFile.close();
        //This is the scanner file for the COVID19 sequence
        Scanner  covidFile = new Scanner(new File("covid19Sequence.csv"));
        ArrayList<String> covidCodons = new ArrayList<>();//Make an ArrayList of covid codons
        while(covidFile.hasNext()) //Since this one only had codons I didn't need to do a nextLine before the while loop
        {
            String line = covidFile.nextLine();
            String[] covidArr = line.split(",");
            for(String code :covidArr)
                covidCodons.add(code); //Add the covid codons to me ArrayList
        }
        //This is the print to file block
        PrintWriter outfile = new PrintWriter("codonBiasOutput");
        outfile.println("***** Codon Bias Analysis *****\n ");
        for(AminoAcid amino : aminoList)
        {
            outfile.println(amino.toString());
            ArrayList<String> codons = amino.getCodon();
            //This totalCodonCount is the variable used to get the codon percentages for each Amino Acid
            int totalCodonCount=0;
            for (String k : codons)
            {
                totalCodonCount += codonCounter(k,covidCodons); //This passes over the codons and ArrayList to the method + to totalCodonCount
            }
            for (String k : codons)
            {
                //This count variable is used to get the individual counts of the codons appearing in the Amino Acid
                int count = codonCounter(k,covidCodons);
                double codonPercentage = (((double) count/totalCodonCount)*100); //I cast my numerator to be a double,so I could get double percents
                outfile.printf(k + ":%4s %7.2f%%\n", codonCounter(k,covidCodons),codonPercentage);
            }
            double genomeEncoding =((double)totalCodonCount/covidCodons.size()*100); //I also cast my numerator to be a double,so I could get double percents
            outfile.printf("Overall Genome Encoding:%7.2f%%\n",genomeEncoding);
            outfile.println();
        }
        covidFile.close();
        outfile.close();
    }

    /**
     * This method uses a single amino acid codon and compares it to the ArrayList of covidCodons to determine if it is equal.
     * If it is equal the count variable initialized in the method will +1.
     * @param searchCodon is the amino acids codon being looped through in main and sent to the method
     * @param codonSeries is the ArrayList of covid codons
     * @return count how many times a codon appears
     */
    public static int codonCounter(String searchCodon, ArrayList<String> codonSeries)
    {
        int count=0;
        for(String i: codonSeries)
        {
            if(searchCodon.equals(i))
            {
                count++;
            }
        }
        return count;
    }
}
