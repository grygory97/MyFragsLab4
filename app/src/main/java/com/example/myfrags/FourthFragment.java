package com.example.myfrags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class FourthFragment extends Fragment {

    private FragsData fragsData;
    private Observer<Integer> numberObserver;
    private EditText edit;
    private TextWatcher textWatcher;
    private boolean turnOffWatcher;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_fourth, container, false);

        //1.
        edit = view.findViewById(R.id.editTextNumber);

        //2.
        fragsData = new ViewModelProvider(requireActivity()).get(FragsData.class);

        //3.
        numberObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer newInteger) {
                turnOffWatcher = true;
                edit.setText(newInteger.toString());
            }
        };

        //4.
        fragsData.counter.observe(getViewLifecycleOwner(), numberObserver);

        //5.
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                edit.setSelection(s.length());
                if (!turnOffWatcher) {
                    int i;
                    String zmienna = "0";
                    try {
                        zmienna = s.toString();
                    } catch (NumberFormatException e) {
                        i = fragsData.counter.getValue();
                    }
                    if (checkstatus(zmienna)) {
                        fragsData.counter.setValue(Integer.parseInt(zmienna));
                    }
                } else {
                    turnOffWatcher = !turnOffWatcher;
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
            /*
            tutaj w text watcherze zmienić bo za szybko odświerza się
            plus update tablicy frames odpowiednią metodą
             */
        };

        //6.
        edit.addTextChangedListener(textWatcher);

        return view;
    }

    public boolean checkstatus(String tmp) {
        try {
            Integer.parseInt(tmp);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}