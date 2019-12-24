package com.nemesiss.dev.ianime.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.EditText;
import com.nemesiss.dev.ianime.R;


public class LoginFragment extends Fragment {

    private View view;

//    private SharedPreferences pref;
//    private SharedPreferences.Editor editor;
//    private EditText accountEdit;
//    private EditText passwordedit;



    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        findView();

    }
    private void findView()
    {
//        accountEdit=getView().findViewById(R.id.account);
//        passwordedit=getView().findViewById(R.id.password);

    }


}
