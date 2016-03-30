package main.redline.com.redline40;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class RockPaperScissor extends AppCompatActivity {

    ImageButton rock, paper, scissor;
    public static Type playedType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock_paper_scissor);

        // Init buttons
        rock = (ImageButton) findViewById(R.id.rock);
        paper = (ImageButton) findViewById(R.id.paper);
        scissor = (ImageButton) findViewById(R.id.scissor);
        initButtons();

    }

    private void initButtons() {
        rock.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          playedType = Type.rock;
                                          Intent myIntent = new Intent(v.getContext(), RockPaperScissorResult.class);
                                          startActivity(myIntent);
                                      }
                                  }
        );

        paper.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          playedType = Type.paper;
                                          Intent myIntent = new Intent(v.getContext(), RockPaperScissorResult.class);
                                          startActivity(myIntent);
                                      }
                                  }
        );

        scissor.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          playedType = Type.scissor;
                                          Intent myIntent = new Intent(v.getContext(), RockPaperScissorResult.class);
                                          startActivity(myIntent);
                                      }
                                  }
        );

    }

    public enum Type {
        rock,
        paper,
        scissor
    }
}
