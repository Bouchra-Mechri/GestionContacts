package ma.tdi2.gestioncontacts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MonAdapter extends ArrayAdapter<Contact> {
    public MonAdapter( Context context, int resource) {
        super(context, resource);
    }

    @NonNull
    @Override
    //code ntsel bih 3an tari9 inflater
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v;
        LayoutInflater li = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = li.inflate(R.layout.monlayout,null); // null pas de parent

        Contact contact = getItem(position);
        TextView idContact = (TextView) v.findViewById(R.id.idContact);
        TextView nom = (TextView) v.findViewById(R.id.nom);
        TextView motDePass = (TextView) v.findViewById(R.id.motDePass);
        TextView email = (TextView) v.findViewById(R.id.email);

        idContact.setText(String.valueOf(contact.idContact));
        nom.setText(contact.Nom);
        motDePass.setText(contact.MotDePass);
        email.setText(contact.Email);
        return v;
    }
}
