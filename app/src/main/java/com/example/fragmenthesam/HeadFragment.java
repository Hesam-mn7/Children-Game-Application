package com.example.fragmenthesam;

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
import androidx.fragment.app.Fragment;

import com.example.fragmenthesam.adapter.CustomAdapter;
import com.example.fragmenthesam.entity.Picture;

import java.util.ArrayList;

public class HeadFragment extends Fragment {

    public interface CallbackMyFragment
    {
        void onclick(int HeadPicID);
    }
    CallbackMyFragment listener;

    ListView listViewHead;
    ImageView up , down;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (context instanceof  CallbackMyFragment)
        {
            listener = (CallbackMyFragment) context;
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_head , null);

        up = view.findViewById(R.id.up);
        down = view.findViewById(R.id.down);

        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listViewHead.scrollListBy(-300);

            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listViewHead.scrollListBy(300);

            }
        });

        listViewHead = view.findViewById(R.id.listViewHead);
        ArrayList<Picture> data = getDatafromDataBase();
        CustomAdapter adapter = new CustomAdapter(getActivity(),data,R.layout.listview_custom);
        listViewHead.setAdapter(adapter);

        listViewHead.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Picture picHead = (Picture) parent.getItemAtPosition(position);
                if(listener!=null)
                {
                    listener.onclick(picHead.getPicID());
                }
            }
        });

        return view;
    }

    private ArrayList<Picture> getDatafromDataBase()
    {
        ArrayList<Picture> result = new ArrayList<>();

        result.add(new Picture(R.drawable.head1));
        result.add(new Picture(R.drawable.head2));
        result.add(new Picture(R.drawable.head3));
        result.add(new Picture(R.drawable.head4));
        result.add(new Picture(R.drawable.head5));
        result.add(new Picture(R.drawable.head6));
        result.add(new Picture(R.drawable.head7));
        result.add(new Picture(R.drawable.head8));

        return result;
    }
}
