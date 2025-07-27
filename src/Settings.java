public class Settings {

    public static final int PARSING_ROW_LENGTH = 5;
    //defaultni pocet prvku (na radku) pro parsovani dat ze souboru

    private static final String FILE_NAME_IN = "ressources/kvetiny_IN.txt";
    private static final String FILE_NAME_OUT = "ressources/kvetiny_OUT.txt";
    private static final String DELIMITER = "\t"; //// "\t" pro tabulátor
    private static final String FILE_HEADER = "name\tnotes\tplanted\twatering\tfrequency";

    public static String getFileNameIn() {
        return FILE_NAME_IN;
    }

    public static String getFileNameOut() {
        return FILE_NAME_OUT;
    }

    public static String getDelimiter() {
        return DELIMITER;
    }

    public static int getParsingRowLength() {
        return PARSING_ROW_LENGTH;
    }

    public static String getFileHeader() {
        return FILE_HEADER;
    }

}
