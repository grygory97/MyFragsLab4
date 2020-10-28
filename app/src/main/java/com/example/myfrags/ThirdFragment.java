package com.example.myfrags;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.Observer;


public class ThirdFragment extends Fragment {

    private TextView text;
    private Button button;
    private FragsData fragsData;
    private Observer<Integer> numberObserver;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_third, container, false);
        text = (TextView) view.findViewById(R.id.current);
        button = (Button) view.findViewById(R.id.button_decrease);

        fragsData = new ViewModelProvider(requireActivity()).get(FragsData.class);

        numberObserver = new Observer<Integer>() {
            @Override
            public void onChanged(Integer newInteger) {

                text.setText(newInteger.toString());
            }
        };

        fragsData.counter.observe(getViewLifecycleOwner(), numberObserver);

        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {

                                          Integer i = fragsData.counter.getValue();
                                          fragsData.counter.setValue(--i);
                                      }
                                  }
        );


        return view;
    }
}