package com.example.hp.connect3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int database[]={2,2,2,2,2,2,2,2,2};
    int player=0;
    boolean state=true;
    int winning_positions[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{6,4,2}};

    public void dropin(View view)
    {
        //0 for yellow
        // 1 for red
        // 2 for empty
        ImageView img=(ImageView) view;
        int tapped_position=Integer.parseInt(img.getTag().toString());
        if(database[tapped_position]==1||database[tapped_position]==0)
            Toast.makeText(this,"Cannot Replace An Already Occupied Place!!",Toast.LENGTH_SHORT).show();

        if(state==true&&database[tapped_position]==2)
        {
            img.setTranslationY(-1200);
            database[tapped_position] = player;

            if (player == 0)
            {
                img.setImageResource(R.drawable.yellow);
                player = 1;
            }
            else
                {
                img.setImageResource(R.drawable.red);
                player = 0;
            }
            img.animate().translationYBy(1200).rotation(720).setDuration(600);

            for (int i[] : winning_positions)
            {
                if (database[i[0]] == database[i[1]] && database[i[1]] == database[i[2]] && database[i[2]] == 0)
                {
                    Toast.makeText(this, "Yellow won the match!", Toast.LENGTH_SHORT).show();
                    state=false;
                }
                if (database[i[0]] == database[i[1]] && database[i[1]] == database[i[2]] && database[i[2]] == 1)
                {
                    Toast.makeText(this, "Red won the match!", Toast.LENGTH_SHORT).show();
                    state=false;
                }
            }
        }
        if(state==false)
        {
           Toast.makeText(this,"Start new game to play again!!",Toast.LENGTH_SHORT).show();
            Button b=(Button)findViewById(R.id.button);
            b.setVisibility(View.VISIBLE);
        }

    }

    public  void play_again(View view)
    {
        Button b=(Button)findViewById(R.id.button);
        b.setVisibility(View.INVISIBLE);
        GridLayout grid=findViewById(R.id.gridLayout);
        player=0;
        state=true;

        for(int i=0;i<grid.getChildCount();i++)
        {
            ImageView img=(ImageView)grid.getChildAt(i);
            img.setImageDrawable(null);
        }

        for(int i=0;i<database.length;i++)
            database[i]=2;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
