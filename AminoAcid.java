/**
 * This is my AminoAcid class which represents an aminoAcid using its name single letter abbreviation, and codons
 * @Mariam Barry
 * 4-24-2025
 */

import java.util.ArrayList;
public class AminoAcid
{
    private final String letter;
    private final String fullName;
    private final ArrayList<String> codon;
    /**
     * This is my parameterized constructor
     * @param fullName is the name of the Amino Acid
     * @param letter is the single letter abbreviation of the aminoAcid
     * @param codon is the codons associated with the aminoAcid
     */
    public AminoAcid(String fullName, String letter, ArrayList<String> codon)
    {
        this.fullName = fullName;
        this.letter = letter;
        this.codon = codon;
    }

    /**
     * This method gets the full name of the amino acid
     * @return name of amino acid
     */
    public String getFullName()
    {
        return fullName;
    }

    /**
     * This method gets the single letter abbreviation of the amino acid
     * @return letter of the amino acid
     */
    public String getLetter()
    {
        return letter;
    }

    /**
     * This method gets the ArrayList of codons for the amino acid
     * @return the ArrayList of codons
     */
    public ArrayList<String> getCodon()
    {
        return codon;
    }

    /**
     * This method strings together the objects name, letter abbrevtion, and Arraylist of codons in defualt toString
     * @return a string of objects
     */
    public String toString()
    {
        return getFullName()+"(" + getLetter()+"): "+ getCodon().toString();
    }
}
