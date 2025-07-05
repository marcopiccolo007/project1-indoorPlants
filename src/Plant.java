import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Plant {

    //atributty tridy
    private String name;
    private List<String> notes = new ArrayList<>();
    //pokud ma poznamka v kolekci nest v sobe vice informaci nez jednoduchy text,
    //tak mozne vytvorit novou tridu Note
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    //konstruktory
    //1. jeden pro nastavení všech atributů
    public Plant(String name, List notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        this.frequencyOfWatering = frequencyOfWatering;
    }
    //2. druhý nastaví jako poznámku prázdný řetězec a datum zasazení i datum poslední zálivky nastaví na dnešní datum
    public Plant (String name, int frequencyOfWatering) {
        this(name, notes.add(""), LocalDate.now(), LocalDate.now(), frequencyOfWatering);
    }
    //3. třetí nastaví totéž co druhý a navíc výchozí frekvenci zálivky na 7 dnů
    // ad 3. Uživatel tedy zadá pouze název rostliny
    public Plant (String name) {
        this(name, notes.add(""), LocalDate.now(), LocalDate.now(), 7);
    }

    //pristupove metody
    //UKOL - Vytvoř výchozí přístupové metody pro všechny atributy (?? asi mysleno jen getery a settery)
    //1. getWateringInfo()
    //ad 1. Vrátí textovou informaci obsahující název květiny, datum poslední zálivky a datum doporučené další zálivky.
    //2. doWateringNow()
    //ad 2. Nastaví datum poslední zálivky na dnešní den.


}
