package com.polydefisv4.menu_principal;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.polydefisv4.R;
import com.polydefisv4.adapter.MenuPrincipalAdapter;
import com.polydefisv4.administration.AjoutAdministrateurFragment;
import com.polydefisv4.ajoutDefis.AjoutDefiFragment;
import com.polydefisv4.bdd.SQLManager;
import com.polydefisv4.bean.Etudiant;
import com.polydefisv4.classement.ClassementFragment;
import com.polydefisv4.listeDefis.ListeDefisFragment;
import com.polydefisv4.listeDefis.TypeUtilisation;
import com.polydefisv4.profil.ProfilFragment;


public class MenuPrincipalFragment extends Fragment implements
		OnItemClickListener {
	private Etudiant etudiant;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View rootView = inflater.inflate(R.layout.fragment_acceuil, container, false);
		getActivity().setTitle("Menu principal");
		SQLManager manager = new SQLManager(getActivity());
		
		etudiant = (Etudiant) getArguments().getSerializable("etudiant");

		TextView nomEtudiant = (TextView) rootView.findViewById(R.id.nomEtudiant);
		nomEtudiant.setText(etudiant.getNom()+" "+etudiant.getPrenom());
		
		TextView promo = (TextView) rootView.findViewById(R.id.promo);
		promo.setText(etudiant.getDepartement()+" "+etudiant.getAnneePromo());
		
		ArrayList<Etudiant> listeEtudiant = new ArrayList<Etudiant>();
		if(etudiant.getAnneePromo() == 3) {
			Etudiant temp = manager.getParrain(etudiant.getIdEtudiant());
			if(temp != null) {
				listeEtudiant.add(temp);
			}
		} else if (etudiant.getAnneePromo() == 4){
			ArrayList<Etudiant> temp = manager.getAllFilleul(etudiant); 
			if(temp != null) {
				listeEtudiant.addAll(temp);
			}
		}
		
		ListView listeParrainage = (ListView) rootView.findViewById(R.id.listeParrainage);
		listeParrainage.setAdapter(new ListeParrainageAdapter(this, listeEtudiant));
		
		GridView gridView = (GridView) rootView.findViewById(R.id.gridviewS);
		gridView.setAdapter(new MenuPrincipalAdapter(getActivity(), etudiant));
		gridView.setOnItemClickListener(this);
		
		return rootView;
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		Button boutonclique = (Button) view;

		Fragment fragment = null;
		Bundle bundle = new Bundle();

		if (boutonclique.getText().toString().equals(getString(R.string.liste_des_defis_a_realiser))) {
			fragment = new ListeDefisFragment();
			bundle.putSerializable("typeUtilisation", TypeUtilisation.VisualisationDefisARealiser);
		} else if (boutonclique.getText().toString().equals(getString(R.string.profil))) {
			fragment = new ProfilFragment();
		} else if (boutonclique.getText().toString().equals(getString(R.string.classement_des_3a))) {
			bundle.putInt("anneeClassement", 3);
			fragment = new ClassementFragment();
		} else if (boutonclique.getText().toString().equals(getString(R.string.classement_des_4a))) {
			bundle.putInt("anneeClassement", 4);
			fragment = new ClassementFragment();
		} else if (boutonclique.getText().toString().equals(getString(R.string.proposer_defi))) {
			fragment = new AjoutDefiFragment();
		} else if (boutonclique.getText().toString().equals(getString(R.string.valider_proposition_defis))) {
			fragment = new ListeDefisFragment();
			bundle.putSerializable("typeUtilisation", TypeUtilisation.AdministrationPropositionDefis);
		} else if (boutonclique.getText().toString().equals(getString(R.string.valider_defis_realise))) {
			fragment = new ListeDefisFragment();
			bundle.putSerializable("typeUtilisation", TypeUtilisation.AdministrationValidationPhoto);
		} else if (boutonclique.getText().toString().equals(getString(R.string.ajout_respo))) {
			fragment = new AjoutAdministrateurFragment();
		}
		
		if (fragment != null) {
			bundle.putSerializable("etudiant", etudiant);
			fragment.setArguments(bundle);

			FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			fragmentTransaction.replace(R.id.frame_container, fragment);
			fragmentTransaction.addToBackStack(null);
			fragmentTransaction.commit();
		} else {
			Log.e("AccueilActivity", "Error in creating fragment");
		}
	}
}