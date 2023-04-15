import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProteinSynthesisTest {

    @Test
    public void transcribeDNATest() {
        ProteinSynthesis proteinSynthesis = new ProteinSynthesis();
        String dna1 = "CTTTACTAAGCG";
        String dna2 = "CTGGGTCCTACT";
        String dna3 = "GCAGCGGGACTT";
        //dna4 is length 11
        String dna4 = "ACGTTGAGAGG";
        String dna5 = "";

        CharQueue rna1 = proteinSynthesis.transcribeDNA(dna1);
        CharQueue rna2 = proteinSynthesis.transcribeDNA(dna2);
        CharQueue rna3 = proteinSynthesis.transcribeDNA(dna3);

        Assertions.assertEquals("[C, U, U, U, A, C, U, A, A, G, C, G]", rna1.toString());
        Assertions.assertEquals("[C, U, G, G, G, U, C, C, U, A, C, U]", rna2.toString());
        Assertions.assertEquals("[G, C, A, G, C, G, G, G, A, C, U, U]", rna3.toString());

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CharQueue rna4 = proteinSynthesis.transcribeDNA(dna4);
        });

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CharQueue rna5 = proteinSynthesis.transcribeDNA(dna5);
        });
    }

    @Test
    public void translateRNATest() {
        ProteinSynthesis proteinSynthesis = new ProteinSynthesis();
        String dna1 = "CATGTTCGTTGG";
        String dna2 = "GCATGGTAGAATGAC";
        String dna3 = "CGACATGGCAATAAC";
        CharQueue rna1 = proteinSynthesis.transcribeDNA(dna1);
        CharQueue rna2 = proteinSynthesis.transcribeDNA(dna2);
        CharQueue rna3 = proteinSynthesis.transcribeDNA(dna3);

        CharQueue protein1 = proteinSynthesis.translateRNA(rna1);
        CharQueue protein2 = proteinSynthesis.translateRNA(rna2);
        CharQueue protein3 = proteinSynthesis.translateRNA(rna3);

        Assertions.assertEquals("[M, F, V]", protein1.toString());
        Assertions.assertEquals("[M, V, E, *]", protein2.toString());
        Assertions.assertEquals("[M, A, I]", protein3.toString());

    }
}