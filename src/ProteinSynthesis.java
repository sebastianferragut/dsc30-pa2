/*
    Name: Sebastian Ferragut, David Tsukamoto
    PID:  A17263077, A17379000
 */

/**
 * Translates and transcribes DNA to RNA, to protein
 * from codon mapping
 * @author Sebastian Ferragut, David Tsukamoto
 * @since ${04-13-2023}
 */

import java.util.NoSuchElementException;

class ProteinSynthesis {
    private static final int CODON_ITERATOR = 2;

    /**
     * Creates an RNA by replacing each occurrence of ‘T’ with ‘U’,
     * saves the result in a queue and returns it.
     * @param dna String of capital letters to be transcribed
     * @return CharQueue with transcribed dna string
     * @throws IllegalArgumentException if the length of the input is not
     * divisible by 3
     */
    public CharQueue transcribeDNA(String dna) throws IllegalArgumentException {
        if (dna.length() % 3 != 0) {
            throw new IllegalArgumentException("DNA length must be divisible by 3");
        }
        CharQueue rnaTranscribed = new CharQueue(dna.length());
        for (int i = 0; i < dna.length(); i++) {
            char c = dna.charAt(i);
            if (c == 'T') {
                rnaTranscribed.enqueue('U');
            } else {
                rnaTranscribed.enqueue(c);
            }
        }
        return rnaTranscribed;
    }

    /**
     * Translates a given RNA to a protein. Starts the translation when codon “AUG”
     * is found and stops when:
     * The sequence is over or
     * One of the three stopping codons is read: “UAA”, “UAG”, or “UGA”
     * @param rna CharQueue transcribed CharQueue to be translated
     * @return CharQueue of transcribed rna,
     * if no “AUG” is found then return an empty queue.
     */
    public CharQueue translateRNA(CharQueue rna) {
        int codonCount = 0;
        String enqueuedCodons = "";
        boolean translate = false;
        String rnaString = rna.toString();
        String[] rnaArray = rnaString.substring(1, rnaString.length() - 1).split(", ");
        char[] rnaCharArray = new char[rnaArray.length];
        CharQueue protein = new CharQueue(rnaCharArray.length);
        for (int i = 0; i < rnaArray.length; i++) {
            rnaCharArray[i] = rnaArray[i].charAt(0);
        }

        //starts translating when AUG is found, ends when “UAA”, “UAG”, or “UGA” are read
        for (int i = 0; i < rnaCharArray.length - CODON_ITERATOR; i++) {
            String codon = "" + rnaCharArray[i] + rnaCharArray[i + 1]
                    + rnaCharArray[i + CODON_ITERATOR];
            if (codon.equals("AUG")) {
                translate = true;
            }
            if (translate) {
                protein.enqueue(CodonMap.getAminoAcid(codon));
                codonCount++;
                enqueuedCodons += CodonMap.getAminoAcid(codon);
                i += CODON_ITERATOR;
            }
            if (codon.equals("UAA") || codon.equals("UAG") || codon.equals("UGA")) {
                break;
            }

        }

        if (translate) {

            CharQueue returnProtein = new CharQueue(codonCount);
            for (int i = 0; i < enqueuedCodons.length(); i++) {
                returnProtein.enqueue(enqueuedCodons.charAt(i));
            }

            return returnProtein;
        } else {
            return new CharQueue(1);
        }


    }

//    public static void main(String[] args) {
//        ProteinSynthesis proteinSynthesis = new ProteinSynthesis();
//
//        String dna1 = "ATGATCTCGTAA";
//        CharQueue rna1 = proteinSynthesis.transcribeDNA(dna1);
//        System.out.println("Transcribed RNA for DNA " + dna1 + ": " + rna1.toString());
//        System.out.println("Translated RNA " + ": " + proteinSynthesis.translateRNA(rna1));
//
//        String dna2 = "CCCCTGTCATAA";
//        CharQueue rna2 = proteinSynthesis.transcribeDNA(dna2);
//        System.out.println("Transcribed RNA for DNA2 " + dna2 + ": " + rna2.toString());
//        System.out.println("Translated RNA " + ": " + proteinSynthesis.translateRNA(rna2));
//
//        String dna3 = "ATGCTATGT";
//        CharQueue rna3 = proteinSynthesis.transcribeDNA(dna3);
//        System.out.println("Transcribed RNA for DNA3 " + dna3 + ": " + rna3.toString());
//        System.out.println("Translated RNA " + ": " + proteinSynthesis.translateRNA(rna3));
//
//    }

}
