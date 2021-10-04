package com.example.fragmenthesam;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fragmenthesam.adapter.CustomAdapter;
import com.example.fragmenthesam.entity.Picture;

import java.util.ArrayList;

public class FootFragment extends Fragment {

    public interface CallbackMyFragmentFoot{
        void onclickFoot(int FootPicID);
    }
    CallbackMyFragmentFoot listener;

    ListView listViewFoot;
    ImageView up , down;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof CallbackMyFragmentFoot){
            listener = (CallbackMyFragmentFoot) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_foot,null);

        listViewFoot=view.findViewById(R.id.listViewFoot);
        up = view.findViewById(R.id.up);
        down = view.findViewById(R.id.down);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listViewFoot.scrollListBy(-150);
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listViewFoot.scrollListBy(150);
            }
        });


        ArrayList<Picture> data = getDatafromDataBase();

        CustomAdapter adapter = new CustomAdapter(getActivity(),data,R.layout.listview_custom);

        listViewFoot.setAdapter(adapter);

        listViewFoot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Picture picFoot = (Picture) parent.getItemAtPosition(position);

                if(listener!=null)
                {
                    listener.onclickFoot(picFoot.getPicID());
                }
            }
        });

        return view;
    }

    private ArrayList<Picture> getDatafromDataBase()
    {
        ArrayList<Picture> result = new ArrayList<>();

        result.add(new Picture(R.drawable.pa1));
        result.add(new Picture(R.drawable.pa2));
        result.add(new Picture(R.drawable.pa3));
        result.add(new Picture(R.drawable.pa4));

        return result;
    }
}
