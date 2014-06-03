package com.polydefisv4.adapter;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.polydefisv4.R;
import com.polydefisv4.bean.Etudiant;

public class ClassementAdapter extends BaseAdapter{
	private List <Etudiant> listeEtudiant;
	private Fragment contexte;
	private ArrayList<Etudiant> etudiantAMettreEnSurbrillance;

	public ClassementAdapter(Fragment contexte, List<Etudiant> listeEtudiant, ArrayList<Etudiant> etudiantAMettreEnSurbrillance) {
		this.listeEtudiant = listeEtudiant;
		this.contexte = contexte;
		this.etudiantAMettreEnSurbrillance = etudiantAMettreEnSurbrillance;
	}

	@Override
	public int getCount() {
		return listeEtudiant.size();
	}

	@Override
	public Object getItem(int position) {
		return listeEtudiant.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LinearLayout layoutItem;
		if (convertView == null) {
			layoutItem =(LinearLayout) View.inflate(contexte.getActivity(), R.layout.item_classement, null);
		} else {
			layoutItem = (LinearLayout) convertView;
		}

		TextView nom = (TextView) layoutItem.findViewById(R.id.nomEtudiant);
		nom.setText(listeEtudiant.get(position).getNom() + " " + listeEtudiant.get(position).getPrenom());
		
		TextView nbPoint = (TextView) layoutItem.findViewById(R.id.nbPoint);
		nbPoint.setText(String.valueOf(listeEtudiant.get(position).getPoints()));
		
		if (etudiantAMettreEnSurbrillance.contains(listeEtudiant.get(position))) {
			layoutItem.setBackgroundColor(Color.CYAN);
		} 
		
		return layoutItem;
	}
}
