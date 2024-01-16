package ma.tdi2.gestioncontacts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddContact extends AppCompatActivity {


    public GestionBDContact maBD;
    public EditText idContact, nom, motDePass, email;
    public Button add;
    public Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);

// declarer un variable maBD ktbna feha instance mt3 classe bch ndiw lel les methodes eli fi wstha
        maBD = new GestionBDContact(this);
        idContact = (EditText) findViewById(R.id.idContact);
        nom = (EditText) findViewById(R.id.nom);
        motDePass = (EditText) findViewById(R.id.motDePass);
        email = (EditText) findViewById(R.id.email);
        add = (Button)  findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Contact cont = new Contact();
                //get text n5dh eli feha to string bch nrj3ou chaine
                cont.idContact = Integer.parseInt(idContact.getText().toString());
                cont.Nom = nom.getText().toString();
                cont.MotDePass = motDePass.getText().toString();
                cont.Email = email.getText().toString();
                maBD.insertContact(cont);

                //Retour a mainactivity
                intent = new Intent(getApplicationContext(),MainActivity.class) ;
                startActivity(intent);

            }
        });





    }     // Fin onCreate
}