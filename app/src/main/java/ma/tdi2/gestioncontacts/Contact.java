package ma.tdi2.gestioncontacts;

public class Contact {
    public int idContact;
    public String Nom;
    public String MotDePass;
    public String Email;

    public Contact(int idContact, String nom, String motDePass, String email) {
        this.idContact = idContact;
        this.Nom = nom;
        MotDePass = motDePass;
        Email = email;
    }

    public Contact() {
        this.idContact = 0;
        this.Nom = "";
        MotDePass = "";
        Email = "";
    }

    @Override
    public String toString() {
        return idContact + " " + Nom + " " + MotDePass + " " + Email;
    }
}
