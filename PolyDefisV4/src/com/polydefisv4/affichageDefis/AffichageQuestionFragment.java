package com.polydefisv4.affichageDefis;

import java.util.ArrayList;
import java.util.Collections;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.polydefisv4.R;
import com.polydefisv4.bean.defis.Quizz;

public class AffichageQuestionFragment extends Fragment implements OnClickListener {
	private int index;
	private boolean donnerPoints;

	private Quizz defis;

	private TextView question;
	private RadioGroup reponses;
	private RadioButton c1;
	private RadioButton c2;
	private RadioButton c3;
	private RadioButton c4;
	private Button boutonEnvoieReponsesQuizz;

	private ArrayList<String> monRandom = new ArrayList<String>();
	private View rootView;

	public AffichageQuestionFragment() {
		index = 0;
		donnerPoints = true;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_affichage_question, container, false);
		defis = (Quizz) getArguments().getSerializable("defis");

		question = (TextView) rootView.findViewById(R.id.question);
		reponses = (RadioGroup) rootView.findViewById(R.id.reponses);
		c1 = (RadioButton) rootView.findViewById(R.id.rep1);
		c2 = (RadioButton) rootView.findViewById(R.id.rep2);
		c3 = (RadioButton) rootView.findViewById(R.id.rep3);
		c4 = (RadioButton) rootView.findViewById(R.id.rep4);

		boutonEnvoieReponsesQuizz = (Button) rootView.findViewById(R.id.envoyer_rep);
		boutonEnvoieReponsesQuizz.setOnClickListener(this);

		melangerListe();

		return rootView;
	}

	public ArrayList<String> melanger(ArrayList<String> listeDepart) {
		ArrayList<String> nouvelle = new ArrayList<String>(listeDepart);
		Collections.shuffle(nouvelle);
		return nouvelle;
	}

	public void melangerListe() {
		monRandom.add(defis.getQuestions(index).getReponse());
		monRandom.add(defis.getQuestions(index).getReponse2());
		monRandom.add(defis.getQuestions(index).getReponse3());
		monRandom.add(defis.getQuestions(index).getReponse4());
		monRandom = melanger(monRandom);

		question.setText(defis.getQuestions(index).getQuestion());
		c1.setText(monRandom.get(1));
		c2.setText(monRandom.get(2));
		c3.setText(monRandom.get(3));
		c4.setText(monRandom.get(0));

		monRandom.clear();
	}

	@Override
	public void onClick(View arg0) {
		int selectedId = reponses.getCheckedRadioButtonId();
		if (selectedId == -1) {
			Toast.makeText(getActivity(), "Veuillez cochez une r�ponse", Toast.LENGTH_LONG).show();
		} else {
			RadioButton radioButton = (RadioButton) rootView.findViewById(selectedId);
			Log.e("", defis.getQuestions(index).getReponse());
			Log.e("", radioButton.getText().toString());

			if (!radioButton.getText().toString().equals(defis.getQuestions(index).getReponse())) {
				Log.e("", "Reponse fause");
				donnerPoints = false;
			}
			index++;
			reponses.check(-1);
			
			if (index < defis.getNbQuestions()) {
				melangerListe();
			}

			else {
				if (donnerPoints) {
					Toast.makeText(getActivity(), "Quizz termin� : " + defis.getNombrePoint()	+ " points attribu�s", Toast.LENGTH_LONG).show();
				} else {
					Toast.makeText(getActivity(), "Quizz termin� : Pas de points attribu�s", Toast.LENGTH_LONG).show();
				}
			}
		}
	}
}
