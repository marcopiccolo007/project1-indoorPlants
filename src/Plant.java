import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Plant {

    //atributty tridy
    private String name;
    private List notes = new ArrayList<>();
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    //konstruktory
    //1. jeden pro nastavení všech atributů
    //2. druhý nastaví jako poznámku prázdný řetězec a datum zasazení i datum poslední zálivky nastaví na dnešní datum
    //3. třetí nastaví totéž co druhý a navíc výchozí frekvenci zálivky na 7 dnů
    // ad 3. Uživatel tedy zadá pouze název rostliny


    //pristupove metody
    //UKOL - Vytvoř výchozí přístupové metody pro všechny atributy (?? asi mysleno jen getery a settery)
    //1. getWateringInfo()
    //ad 1. Vrátí textovou informaci obsahující název květiny, datum poslední zálivky a datum doporučené další zálivky.
    //2. doWateringNow()
    //ad 2. Nastaví datum poslední zálivky na dnešní den.


}
