package main.redline.com.redline40;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import main.redline.com.redline40.RockPaperScissor.Type;

public class RockPaperScissorResult extends AppCompatActivity {
    ImageView playerPlayedTypeImage;
    ImageView computerPlayedTypeImage;
    TextView resultLabel;
    Type computerPlayedType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rock_papper_scissor_result);

        // Init images and text
        playerPlayedTypeImage = (ImageView) findViewById(R.id.playerPlayedType);
        computerPlayedTypeImage = (ImageView) findViewById(R.id.computerPlayedType);
        resultLabel = (TextView) findViewById(R.id.resultLabel);

        // Computer randomizes a play
        computerPlayedType = computerPlay();

        // Draw images
        drawImages();

        // Check who won
        boolean playerWon = didPlayerWin();

        // Draw label
        drawLabel(playerWon);
    }

    private Type computerPlay() {
        Random random = new Random();
        int randomNumber = random.nextInt(3);

        switch(randomNumber) {
            case 0:
                return Type.rock;
            case 1:
                return Type.paper;
            case 2:
                return Type.scissor;
        }

        return null;
    }

    private void drawImages() {
        // Player

        if(playerPlayedTypeImage != null) {

            BitmapDrawable drawable = (BitmapDrawable) playerPlayedTypeImage.getDrawable();

            if(drawable != null)
                drawable.getBitmap().recycle();
        }

        if(computerPlayedTypeImage != null) {
            BitmapDrawable drawable = (BitmapDrawable) computerPlayedTypeImage.getDrawable();

            if(drawable != null)
                drawable.getBitmap().recycle();
        }

        switch(RockPaperScissor.playedType) {
            case rock:
                playerPlayedTypeImage.setImageResource(R.drawable.rock);
                break;

            case paper:
                playerPlayedTypeImage.setImageResource(R.drawable.paper);
                break;

            case scissor:
                playerPlayedTypeImage.setImageResource(R.drawable.scissors);
                break;
        }

        // Computer
        switch(computerPlayedType) {
            case rock:
                computerPlayedTypeImage.setImageResource(R.drawable.rock);
                break;

            case paper:
                computerPlayedTypeImage.setImageResource(R.drawable.paper);
                break;

            case scissor:
                computerPlayedTypeImage.setImageResource(R.drawable.scissors);
                break;
        }
    }

    private boolean didPlayerWin() {
        if(RockPaperScissor.playedType.equals(Type.rock)) {
            switch(computerPlayedType) {
                case rock:
                    return false;

                case paper:
                    return false;

                case scissor:
                    return true;
            }
        }

        else if(RockPaperScissor.playedType.equals(Type.paper)) {
            switch(computerPlayedType) {
                case rock:
                    return true;

                case paper:
                    return false;

                case scissor:
                    return false;
            }
        }

        else if(RockPaperScissor.playedType.equals(Type.scissor)) {
            switch(computerPlayedType) {
                case rock:
                    return false;

                case paper:
                    return true;

                case scissor:
                    return false;
            }
        }

        return false;
    }

    private void drawLabel(boolean playerWon) {
        if(playerWon)
            resultLabel.setText("DU VANN!");
        else
            resultLabel.setText("DU FÖRLORADE!");
    }

}
