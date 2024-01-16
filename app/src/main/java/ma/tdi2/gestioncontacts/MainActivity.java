package ma.tdi2.gestioncontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import ma.tdi2.gestioncontacts.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    public GestionBDContact maBD;
    public ListView lv;
    public ArrayAdapter monadapter;
    public Intent intent;
    public Contact contact;

    public List<Integer> listIds;
    public int k;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



        maBD = new GestionBDContact(this);
       //acces lel listeview
        lv = (ListView) findViewById(R.id.lv);



    // afficher tous les contacts de la bd
        Afficher();

        listIds = new ArrayList<Integer>();




        // ki nrj3 l main activity naffichiw les contact eli 3ana fi base


        // memorisation de la position du contact selectionne
        //ki nenzl ala button modifier thzni l formulaire lzm ki nenzl ala modifier n7ot position fi intent eli hiya asln tkoun mwjouda fi variable
       // clique ala contact c'est un evenement
        //clique ala icon c'est un evenement

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                //acceder l checkbox t5dh view ki nenzl ala liste view les item eli fiha houma les instance mt3 monlayout
                //ki n3ml view. y3tini checkbox
                CheckBox cb = view.findViewById(R.id.cb);
                if(cb.isChecked())
                {
                    cb.setChecked(false);
                    listIds.remove(maBD.getAllContacts().get(position).idContact);
                }
                else
                {
                    cb.setChecked(true);
                    listIds.add(maBD.getAllContacts().get(position).idContact);

                }
                k = position;
            }
        });

    }  //Fin onCreate

  public void Afficher() {
        monadapter = new MonAdapter(getApplicationContext(),0); // 0 monlayout m3ndouch parent
        lv.setAdapter(monadapter);
        monadapter.addAll(maBD.getAllContacts());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement



        if (id == R.id.action_add) {
            intent = new Intent(getApplicationContext(),AddContact.class) ;
            startActivity(intent);
        }
         //button modifier
        //ki tenzl ala buton trj3 return true
        //putextra t5dh position mt3 index eli n7b n3mlou modifier
        if (id == R.id.action_update) {
            intent = new Intent(getApplicationContext(),UpdateContact.class) ;
            intent.putExtra("position",k);
            startActivity(intent);
        }
        if (id == R.id.action_delete)
        {

            for(int i:listIds)
            {

                maBD.deleteContact(i);
            }
          Afficher();
        }

//polymorphisme deux methode y5tlfou fi parametre

        if (id == R.id.action_exit) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }


}