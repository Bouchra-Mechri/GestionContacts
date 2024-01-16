package ma.tdi2.gestioncontacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UpdateContact extends AppCompatActivity {

    public GestionBDContact maBD;
    public EditText idContact, nom, motDePass, email;
    public Button update;
    public Intent intent;
    public Contact contact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_contact);

        maBD = new GestionBDContact(this);
        idContact = (EditText) findViewById(R.id.idContact);
        nom = (EditText) findViewById(R.id.nom);
        motDePass = (EditText) findViewById(R.id.motDePass);
        email = (EditText) findViewById(R.id.email);
        update = (Button)  findViewById(R.id.update);

        //affichage du contact selectionne dans listview
        intent = getIntent();
        int p = intent.getIntExtra("position",0);
        // getalcon tjibli les contact mel base w t7othom fi array liste
        // get p n5dh position
        //wala 3andi fi objet contact position eli selectionnitha
        contact = maBD.getAllContacts().get(p);


        //bch nafichih fi formulaire

        nom.setText(contact.Nom);
        motDePass.setText(contact.MotDePass);
        email.setText(contact.Email);



        //button modifier supposer user afficher les donnees mt3 contact selectionner w nzel ala button update
        // modification du contact
        //setonclick t5dm fonction eli fi wstha ala button
        //n5dh objet eli affichitou w n3mlou modifier w nsjlou
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contact.Nom = nom.getText().toString();
                contact.MotDePass = motDePass.getText().toString();
                contact.Email = email.getText().toString();
                maBD.updateContact(contact);

                // retour au mainactivity
                //creation nouvelle instance mt3 intent t3ml feha context w nom activity destination
                intent = new Intent(getApplicationContext(),MainActivity.class) ;
                startActivity(intent);
            }
        });













    } //Fin onCreate
}