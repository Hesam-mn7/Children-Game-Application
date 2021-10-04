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

public class BodyFragment extends Fragment {

    public interface CallbackMyFragment2
    {
        void onclick2(int BodyPicID);
    }
    CallbackMyFragment2 listener;

    ListView listViewBody;
    ImageView up , down;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof CallbackMyFragment2)
        {
            listener = (CallbackMyFragment2) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_body,null);

        listViewBody = view.findViewById(R.id.listViewBody);
        up = view.findViewById(R.id.up);
        down = view.findViewById(R.id.down);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listViewBody.scrollListBy(-300);
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listViewBody.scrollListBy(300);
            }
        });

        ArrayList<Picture> data = getDatafromDataBase();
        CustomAdapter adapter = new CustomAdapter(getActivity(),data,R.layout.listview_custom);
        listViewBody.setAdapter(adapter);

        listViewBody.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Picture picBody = (Picture) parent.getItemAtPosition(position);
                if(listener!=null)
                {
                    listener.onclick2(picBody.getPicID());
                }
            }
        });

        return view;
    }

    private ArrayList<Picture> getDatafromDataBase()
    {
        ArrayList<Picture> result = new ArrayList<>();

        result.add(new Picture(R.drawable.body1));
        result.add(new Picture(R.drawable.body2));
        result.add(new Picture(R.drawable.body3));
        result.add(new Picture(R.drawable.body4));
        result.add(new Picture(R.drawable.body5));
        result.add(new Picture(R.drawable.body6));

        return result;
    }
}
