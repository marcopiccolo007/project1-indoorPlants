import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Plant {

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd. MM. yyyy");

    //atributty tridy
    private String name;
    private List<String> notes; //pokud ma poznamka
    // v kolekci nest v sobe vice informaci nez jednoduchy text,
    //tak mozne vytvorit novou tridu Note v projektu
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    //konstruktory
    //1. konstruktor pro nastavení všech atributů
    public Plant(String name, List<String> notes, LocalDate planted,
                 LocalDate watering, int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering < 1) {
            throw new PlantException("Frekvence zálivky nesmí být nižší než 1 (zadal jsi : "+ frequencyOfWatering +")");
        } else if (watering.isBefore(planted)) {
            throw new PlantException("Datum zálivky nesmí být starší než datum zasazení ("
                    + planted.format(formatter) + "), zadal jsi: " + watering.format(formatter));
        }
        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        this.frequencyOfWatering = frequencyOfWatering;
    }

    //2. kontruktor, který nastaví jako poznámku prázdný řetězec
    // a datum zasazení i datum poslední zálivky nastaví na dnešní datum
    public Plant(String name, int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering < 1) {
            throw new PlantException("Frekvence zálivky nesmí být nižší než 1 (zadal jsi : " + frequencyOfWatering + ")");
        }
        this.name = name;
        //this.notes = new ArrayList<>();
        //this.notes.add("");
        this.notes = new ArrayList<>(List.of(""));
        this.planted = LocalDate.now();
        this.watering = LocalDate.now();
        this.frequencyOfWatering = frequencyOfWatering;
    }

    //3. stejný konstruktor jako číslo 2. plus nastaví výchozí frekvenci zálivky na 7 dnů
    //alternativni kratsi zapis konstruktoru
    public Plant(String name) throws PlantException {
        this(name, new ArrayList<>(List.of("")), LocalDate.now(), LocalDate.now(), 7);
    }

    //pristupove metody (region gettery a settery)
    public String getName() {
        return name;
    }

    public void setName(String name) {
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

    public void setWatering(LocalDate watering) throws PlantException {
        if (watering.isBefore(planted)) {
            throw new PlantException("Datum zálivky nesmí být starší než datum zasazení ("
                    + planted.format(formatter) + "), zadal jsi: " + watering.format(formatter));
        }
        this.watering = watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if (frequencyOfWatering < 1) {
            throw new PlantException("Frekvence zálivky nesmí být nižší než 1 (zadal jsi : " + frequencyOfWatering + ")");
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }

    //pristupove metody (region specialni metody)
    public String getWateringInfo() {
        //pouziti StringBuilderu a 'ceskeho' formatovani datumu
        LocalDate nextWatering = watering.plusDays(frequencyOfWatering);
        StringBuilder wateringInfo = new StringBuilder();
        wateringInfo.append("Název květiny: " + name + ", ");
        wateringInfo.append("Datum poslední zálivky: " + watering.format(formatter) + ", ");
        wateringInfo.append("Datum doporučené další zálivky: " + nextWatering.format(formatter));
        return wateringInfo.toString();
    }

    public void doWateringNow() {
        this.watering = LocalDate.now();
    }

}