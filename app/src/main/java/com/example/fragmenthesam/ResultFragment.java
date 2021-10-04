package com.example.fragmenthesam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {

    public static ResultFragment newInstance(int HeadPicID)
    {
        ResultFragment fragment = new ResultFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("HeadPicID",HeadPicID);
        fragment.setArguments(bundle);
        return fragment;
    }
    public static ResultFragment newInstanceBody(int BodyPicID)
    {
        ResultFragment fragment = new ResultFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("BodyPicID",BodyPicID);
        fragment.setArguments(bundle);
        return fragment;
    }
    public static ResultFragment newInstanceFoot(int FootPicID)
    {
        ResultFragment fragment = new ResultFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("FootPicID",FootPicID);
        fragment.setArguments(bundle);
        return fragment;
    }

    public void setParam(int HeadPicID)
    {
        HeadResult.setImageResource(HeadPicID);
    }

    public void setParamBody(int BodyPicID)
    {
        BodyResult.setImageResource(BodyPicID);
    }

    public void setParamFoot(int FootPicID)
    {
        FootResult.setImageResource(FootPicID);
    }

    ImageView HeadResult , BodyResult , FootResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result , null);

        HeadResult = view.findViewById(R.id.HeadResult);
        BodyResult = view.findViewById(R.id.BodyResult);
        FootResult = view.findViewById(R.id.FootResult);

//        if (getArguments() != null && getArguments().containsKey("HeadPicID") ){
//            int HeadPicID = getArguments().getInt("HeadPicID");
//            HeadResult.setImageResource(HeadPicID);
//        }
//
//        if (getArguments() != null && getArguments().containsKey("BodyPicID")){
//            int BodyPicID = getArguments().getInt("BodyPicID");
//            BodyResult.setImageResource(BodyPicID);
//        }
//        if (getArguments() != null && getArguments().containsKey("FootPicID")){
//            int FootPicID = getArguments().getInt("FootPicID");
//            FootResult.setImageResource(FootPicID);
//        }

        return  view;
    }


}
