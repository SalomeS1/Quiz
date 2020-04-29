package com.example.homeworkquiz_extended;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


import com.example.homeworkquiz_extended.data.Storage;
import com.example.homeworkquiz_extended.data.StorageBoardImpl;
import com.example.homeworkquiz_extended.data.User;
import com.example.homeworkquiz_extended.data.UserBoardStorage;

import java.util.ArrayList;
import java.util.List;

public class BoardActivity extends Activity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        ListView userList = (ListView) findViewById(R.id.users) ;
        UserArrayAdapter userArrayAdapter = new UserArrayAdapter(this, 0, new ArrayList<User>());
        userList.setAdapter(userArrayAdapter);

        Storage storage = new StorageBoardImpl();
        UserBoardStorage userBoard;
        Object storageAsObject = storage
                .getObj(this, StorageBoardImpl.BOARD, UserBoardStorage.class);
        if (storageAsObject != null) {
            userBoard = (UserBoardStorage) storageAsObject;
        } else {
            userBoard = new UserBoardStorage();
        }
        userArrayAdapter.addAll(userBoard.getUsers());
    }


    class UserArrayAdapter extends ArrayAdapter<User> {

        private Context mContext;

        public UserArrayAdapter(@NonNull Context context,
                                    int resource,
                                    @NonNull List<User> objects) {
            super(context, resource, objects);
            mContext = context;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            final int mPosition = position;
            User current = getItem(position);
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.view_user, parent, false);
            TextView score = view.findViewById(R.id.scoreView);
            TextView time = view.findViewById(R.id.time);
            if (current != null) {
                score.setText(current.getScore() );
                time.setText(current.getTime());
            }

            return view;
        }
    }
}
