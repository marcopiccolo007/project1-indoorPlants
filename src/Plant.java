import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Plant {

    //atributty tridy
    private String name;
    private List<String> notes; //pokud ma poznamka v kolekci nest v sobe vice informaci nez jednoduchy text,
    //tak mozne vytvorit novou tridu Note v projektu
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    //konstruktory
    //1. konstruktor pro nastavení všech atributů
    public Plant(String name, List<String> notes, LocalDate planted, LocalDate watering, int frequencyOfWatering) {
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        this.frequencyOfWatering = frequencyOfWatering;
    }

    //2. kontruktor, který nastaví jako poznámku prázdný řetězec a datum zasazení i datum poslední zálivky nastaví na dnešní datum
    public Plant(String name, int frequencyOfWatering) {
        this.name = name;
        this.notes = new ArrayList<>();
        this.notes.add("");
        this.planted = LocalDate.now();
        this.watering = LocalDate.now();
        this.frequencyOfWatering = frequencyOfWatering;
    }
    //3. stejný konstruktor jako čílo 2. a navíc nastaví výchozí frekvenci zálivky na 7 dnů
    public Plant(String name) {
        this.name = name;
        this.notes = new ArrayList<>();
        this.notes.add("test poznamka");
        this.planted = LocalDate.now();
        this.watering = LocalDate.now();
        this.frequencyOfWatering = 7;
    }

    //pristupove metody (gettery a settery)

    public String getName () {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public void setNote(String note) {
        notes.add(note);
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) {
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) {
        this.frequencyOfWatering = frequencyOfWatering;
    }

    //pristupove metody (getWateringInfo)
    //Vrátí textovou informaci obsahující název květiny, datum poslední zálivky
    //a datum doporučené další zálivky.




    //pristupove metody (doWateringNow())
    //Nastaví datum poslední zálivky na dnešní den.



}