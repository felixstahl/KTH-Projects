package q4;
import java.io.*;
import java.util.Optional;

/**
 *              README
 *       Author: Felix Ståhl
 *       Extra lab - Question 4
 *       Based on "Algorithms, 4th Edition" by Robert Sedgewick & Kevin Wayne
 * Implementation of a program that corrects errors from a file, writing to a new corrected file
 *
 * Time complexity should be avarage O(log n) since it is using binary search.
 * (not counting the import of words file)
 *
 */
public class Spelling {

    private static final String COMMON_ERRORS = "C:\\Users\\mr_fe\\Desktop\\Algo\\Algo2\\nya\\komplettering\\src\\q4\\words.txt";
    private static final int MISSPELLINGS_COUNT = 4285;
    private static String[] misspellings = new String[MISSPELLINGS_COUNT];
    private static int misspellingsCount = 0;

    public static void main(String[] args) {
        if(args.length == 1 && args[0].equals("test")){ // run test cases
            runTest();
        } else if(args.length == 1 && args[0].length() > 0){

            File words = new File(COMMON_ERRORS);

            String fileName = args[0];                  // input file with errors in it
            System.out.println("File to correct is: " + fileName);
            File file = new File(fileName);

            try {   // read all common errors and save in array
                Util.readWordsFromFile(words, (word) -> {  // all the lines and save the lines in "misspellings" array
                    if (isGoodWord(word)) { // do this on consumer, which in this case is the word
                        misspellings[misspellingsCount++] = word;
                    }
                });

                // read all my errors from my file
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                String line;

                File fixedFile = new File(makeFixedFileName(fileName));     // create a new file for corrected words
                fixedFile.createNewFile();
                BufferedWriter writer = new BufferedWriter(new FileWriter(fixedFile));
                StringBuilder sb = new StringBuilder();

                while ((line = reader.readLine()) != null) {        // for each line in my file, split into chars
                    String[] characters = line.split("");
                    for (int i = 0; i < characters.length; i++) {   // append each character to a string builder

                        if (Util.isLetterOrDigit(characters[i])) {
                            sb.append(characters[i]);
                            try {                                             // if next index is a space (not a letter)
                                if (!Util.isLetterOrDigit(characters[i + 1])) // write the correction of the word to
                                    writeCorrection(writer, sb);              // the file (if there is a correction)

                            } catch (IndexOutOfBoundsException ignored) {
                                writeCorrection(writer, sb);
                            }
                        }
                        else {
                            writer.write(characters[i]);    // if not letter or digit, just write it in file
                        }

                    }
                    writer.write(System.lineSeparator());   // new line
                }
                writer.close();
            } catch (Exception e) { e.printStackTrace(); }
        }
    }

    // write correction to file (gets part of String builder sent to it)
    private static void writeCorrection(BufferedWriter writer, StringBuilder builder) throws IOException {
        writer.write(getCorrection(builder.toString()));
        builder.setLength(0);   // set to 0 and continue with next words
    }

    // get correction for string (string builder)
    private static String getCorrection(String s) {
        return getOptionalCorrection(s).orElse(s);  // if value is present, return it. otherwise return object (String)
    }

    // make sure the line has a '-' (->)
    private static boolean isGoodWord(String word) {
        return word.contains("-");
    }

    // binary search algorithm for finding a correction.    Worst case O(log n)
    private static Optional<String> getOptionalCorrection(String word) {
        String correction = null;  // could be the same word, doesn't have to be a correction
        String misspelling;
        int low = 0;
        int high = misspellings.length; // length of array of corrections
                                        // we are looking for mid to be the same a low & high
        while (low <= high) {           // this keeps narrowing the scope (binary search)
            int mid = (low + high) / 2;
            misspelling = getMisspelling(misspellings[mid]);    // get the most middle word before '>' from the line
                                                                // from the file with common errors.
            if (less(misspelling, word)) {   // if 'database' misspelling is earlier alphabetically
                low = mid + 1;               // low moved forward to middle + 1 (at least first iteration)
            }

            else if (less(word, misspelling)) { // if my word is earlier alphabetically
                high = mid - 1;                 // high will be moved backward to middle - 1 (at least first iteration)
            }

            else if (word.compareToIgnoreCase(misspelling) == 0) {  // if words are equal, we have a match
                correction = getCorrectionFromIndex(mid);
                break;
            }
        }   // optional may or may not contain a non-null value.
        return Optional.ofNullable(correction);
    }

    // get the word before the ->, return the first substring after split at -
    private static String getMisspelling(String misspelling) {
        int arrowIndex = misspelling.indexOf("-");
        return misspelling.substring(0, arrowIndex);
    }

    private static String getCorrectionFromIndex(int index) {
        int arrowIndex = misspellings[index].indexOf("-");
        return misspellings[index].substring(arrowIndex + 2);
    }

    // compareToIgnoreCase() returns an integer negative 0 or positive, the amount of steps before or after a word comes
    // lexicographicly.
    // returns true or false depending on if compareToIgnoreCase() is negative or equal/positive
    private static boolean less(String misspellinWithoutArrow, String word) {
        //System.out.println(word);
        return misspellinWithoutArrow.compareToIgnoreCase(word) < 0;
    }

    // returns a string containing old file name + '_corrected' to it. corrected words will be stored in this file
    private static String makeFixedFileName(String fileName) {
        String originalName = fileName.replaceAll("\\.txt", "");
        return originalName + "_corrected.txt";
    }

    // test cases
    private static void runTest() {
        testMakeFixedFileName();
        testGetOptionalCorrection();
        testTrimMisspelling();
    }

    private static void testMakeFixedFileName() {
        String before = "before.txt";
        String expected = "before_corrected.txt";
        String actual = makeFixedFileName(before);

        Util.assertTrue(expected.equals(actual), "incorrect file name");
    }

    private static void testGetOptionalCorrection() {
        File commonErrors = new File(COMMON_ERRORS);
        try {
            Util.readWordsFromFile(commonErrors, (word) -> {
                if (isGoodWord(word)) {
                    misspellings[misspellingsCount++] = word;
                }
                //System.out.println(misspellingsCount);
            });
        } catch (IOException e) {

        }
        String misspelledWord = "currenly";
        String corrected = "currently";
        Optional<String> actual = getOptionalCorrection(misspelledWord);

        Util.assertTrue(actual.isPresent(), "word correction should have been present");
        Util.assertTrue(actual.get().equals(corrected), actual + " should have equaled " + corrected);

        String capitalisedMisspelledWord = "CuRrEnLy";
        actual = getOptionalCorrection(capitalisedMisspelledWord);

        Util.assertTrue(actual.isPresent(), "word correction should have been present");
        Util.assertTrue(actual.get().equals(corrected), actual + " should have equaled " + corrected);
    }

    private static void testTrimMisspelling() {
        String before = "elephant->tests";
        String expected = "elephant";
        String actual = getMisspelling(before);

        Util.assertTrue(actual.equals(expected), "misspelled string should be trimmed");
    }
}
