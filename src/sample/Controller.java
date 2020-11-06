package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ArrayList;

class Person {
    String navn;
    String personnummer;

    // konstruktør for å initialisere attributtene til klassen
    public Person(String navn, String personnummer) {
        this.navn = navn;
        this.personnummer = personnummer;

    }
}
class Register{
    ArrayList<Person> arrayPerson = new ArrayList<>();
    public void registrerPerson(String navn, String personnummer) {
        /* sjekk om personnummeret finnes fra før
           dersom det ikke gjør det registrer det */
        Boolean finnes = false;
        Person person1 = new Person(navn, personnummer);
        for (Person person : arrayPerson) {

            if (person.personnummer.equals(personnummer)) {
                finnes = true;
            }
            if (!finnes && person.personnummer.equals(null)) {
                arrayPerson.add(person1);
            }
        }
    }
    public void slettPerson(String personnummer){
        /* løp igjennom arrayet for å finne personen,
           dersom den finnes, slett den */
        for(Person enperson : arrayPerson){
            if(enperson.personnummer.equals(personnummer)){
                arrayPerson.remove(enperson);
            }
        }
    }

    public boolean finnesFraFør(String personnummer){
        /* sjekk om personnummeret allerede ligger i arrayet
           dersom det gjør det returner true, ellers false */
        boolean finnes= false;
        for(Person enperson: arrayPerson){
            if(enperson.personnummer.equals(personnummer)){
                return true;
            }
        }
        return finnes;
    }

    public String visAllePersonene(){
        /* løp igjennom arrayet og formater først personnummer så navn på
           hver sin linje i en streng og returner denne. */
        String ut ="";
        for(Person enperson: arrayPerson){
            ut+= "Personnr. : "+enperson.personnummer+"\nnavn : "+enperson.navn;
        }
return ut;
        }
    }


public class Controller {

    @FXML
    private Label lblRegister;

    @FXML
    private TextField txtnavn;

    @FXML
    private TextField txtPersonnummer;

    @FXML
    void registrer(ActionEvent event) {
        String navn = txtnavn.getText();
        String personnr = txtPersonnummer.getText();
        // Person enperson = new Person(navn,personnr);
        Register newRig = new Register();
        if (newRig.finnesFraFør(personnr) == false) {
            newRig.registrerPerson(navn, personnr);
            lblRegister.setText(newRig.visAllePersonene());
        }
    }

    @FXML
    void slett(ActionEvent event) {
        String personnr = txtPersonnummer.getText();
        Register newRig = new Register();
        if (newRig.finnesFraFør(personnr) == true) {
            newRig.slettPerson(personnr);
            lblRegister.setText(newRig.visAllePersonene());
        }
    }
}