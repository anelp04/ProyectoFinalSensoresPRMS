package com.example.proyectosensores;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Infrarojo extends AppCompatActivity {

    private final static String CMD_TV_POWER =
            "0000 006d 0022 0003 00a9 00a8 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0040 0015 0015 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 003f 0015 0702 00a9 00a8 0015 0015 0015 0e6e";
    private final static String CMD_TV_SOURCE =
            "0000 006C 0000 0022 00AD 00AD 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0041 0016 0041 0016 0041 0016 0041 0016 06FB";
    private final static String CMD_TV_CH_NEXT =
            "0000 006d 0022 0003 00a9 00a8 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 003f 0015 003f 0015 0015 0015 0040 0015 003f 0015 003f 0015 0702 00a9 00a8 0015 0015 0015 0e6e";
    private final static String CMD_TV_CH_PREV =
            "0000 006d 0022 0003 00a9 00a8 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 0015 003f 0015 0015 0015 0015 0015 0015 0015 003f 0015 003f 0015 003f 0015 003f 0015 0015 0015 003f 0015 003f 0015 003f 0015 0702 00a9 00a8 0015 0015 0015 0e6e";
    private final static String CMD_TV_BACK =
            "0000 006C 0000 0022 00AD 00AD 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 06FB";


    private String TAG = "MainActivity";

    private final static String CMD_TV_LEFT =
            "0000 006C 0000 0022 00AD 00AD 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0016 0016 0016 0016 0041 0016 06FB";
    private final static String CMD_TV_RIGHT =
            "0000 006C 0000 0022 00AD 00AD 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0041 0016 06FB";
    private final static String CMD_TV_ENTER =
            "0000 006C 0000 0022 00AD 00AD 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0016 0041 0016 0016 0016 0041 0016 0041 0016 0016 0016 0041 0016 0041 0016 0041 0016 0016 0016 0041 0016 0016 0016 0016 0016 0041 0016 06FB";



    private final static String CMD_SB_POWER =
            "0000 006C 0000 0027 00AC 00AC 0013 0013 0013 0013 0013 0013 0013 0013 0013 003A 0013 003A 0013 0013 0013 0013 0013 003A 0013 003A 0013 003A 0013 003A 0013 0013 0013 0013 0013 0013 0013 0013 0013 00AC 0013 0013 0013 0013 0013 0013 0013 0013 0013 0013 0013 0013 0013 0013 0013 0013 0013 003A 0013 003A 0013 003A 0013 0013 0013 003A 0013 003A 0013 003A 0013 003A 0013 0013 0013 0013 0013 0013 0013 003A 0013 0856";
    private final static String CMD_SB_SOURCE =
            "0000 006C 0000 0027 00AC 00AC 0013 0013 0013 0013 0013 0013 0013 0013 0013 003A 0013 003A 0013 0013 0013 0013 0013 003A 0013 003A 0013 003A 0013 003A 0013 0013 0013 0013 0013 0013 0013 0013 0013 00AC 0013 0013 0013 0013 0013 0013 0013 0013 0013 003A 0013 0013 0013 003A 0013 0013 0013 003A 0013 003A 0013 003A 0013 0013 0013 0013 0013 003A 0013 0013 0013 003A 0013 0013 0013 0013 0013 0013 0013 003A 0013 0856";
    private final static String CMD_SB_VOLUP =
            "0000 006C 0000 0027 00AC 00AC 0013 0013 0013 0013 0013 0013 0013 0013 0013 003A 0013 003A 0013 0013 0013 0013 0013 003A 0013 003A 0013 003A 0013 003A 0013 0013 0013 0013 0013 0013 0013 0013 0013 00AC 0013 0013 0013 0013 0013 0013 0013 0013 0013 003A 0013 003A 0013 003A 0013 0013 0013 003A 0013 003A 0013 003A 0013 0013 0013 0013 0013 0013 0013 0013 0013 003A 0013 0013 0013 0013 0013 0013 0013 003A 0013 0856";
    private final static String CMD_SB_VOLDOWN =
            "0000 006C 0000 0027 00AC 00AC 0013 0013 0013 0013 0013 0013 0013 0013 0013 003A 0013 003A 0013 0013 0013 0013 0013 003A 0013 003A 0013 003A 0013 003A 0013 0013 0013 0013 0013 0013 0013 0013 0013 00AC 0013 0013 0013 0013 0013 0013 0013 0013 0013 0013 0013 0013 0013 0013 0013 003A 0013 003A 0013 003A 0013 003A 0013 0013 0013 003A 0013 003A 0013 003A 0013 0013 0013 0013 0013 0013 0013 0013 0013 003A 0013 0856";

    private ConsumerIrManager irManager;
    private Vibrator vibe;
    MediaPlayer mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infrarojo);
        irManager = (ConsumerIrManager) getSystemService(CONSUMER_IR_SERVICE);

        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        //mp = MediaPlayer.create(this, R.raw.beep);

        findViewById(R.id.tvpower).setOnClickListener(new ClickListener(hex2ir(CMD_TV_POWER)));
        findViewById(R.id.tvsource).setOnClickListener(new ClickListener(hex2ir(CMD_TV_SOURCE)));
        findViewById(R.id.tvchnext).setOnClickListener(new ClickListener(hex2ir(CMD_TV_CH_NEXT)));
        findViewById(R.id.tvchprev).setOnClickListener(new ClickListener(hex2ir(CMD_TV_CH_PREV)));
        findViewById(R.id.tvback).setOnClickListener(new ClickListener(hex2ir(CMD_TV_BACK)));
        findViewById(R.id.tvleft).setOnClickListener(new ClickListener(hex2ir(CMD_TV_LEFT)));
        findViewById(R.id.tvright).setOnClickListener(new ClickListener(hex2ir(CMD_TV_RIGHT)));
        findViewById(R.id.tvok).setOnClickListener(new ClickListener(hex2ir(CMD_TV_ENTER)));

        //findViewById(R.id.sbpower).setOnClickListener(new ClickListener(hex2ir(CMD_SB_POWER)));
        //findViewById(R.id.sbsource).setOnClickListener(new ClickListener(hex2ir(CMD_SB_SOURCE)));
        //findViewById(R.id.sbvoldown).setOnClickListener(new ClickListener(hex2ir(CMD_SB_VOLDOWN)));
        //findViewById(R.id.sbvolup).setOnClickListener(new ClickListener(hex2ir(CMD_SB_VOLUP)));
    }
    private IRCommand hex2ir(final String irData) {
        List<String> list = new ArrayList<String>(Arrays.asList(irData.split(" ")));
        list.remove(0); // dummy
        int frequency = Integer.parseInt(list.remove(0), 16); // frequency
        list.remove(0); // seq1
        list.remove(0); // seq2

        frequency = (int) (1000000 / (frequency * 0.241246));
        int pulses = 1000000 / frequency;
        int count;

        int[] pattern = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            count = Integer.parseInt(list.get(i), 16);
            pattern[i] = count * pulses;
        }

        return new IRCommand(frequency, pattern);
    }


    private class ClickListener implements View.OnClickListener {
        private final IRCommand cmd;

        public ClickListener(final IRCommand cmd) {
            this.cmd = cmd;
        }

        @Override
        public void onClick(final View view) {

            mp.start();


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibe.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                //deprecated in API 26
                vibe.vibrate(150);
            }
            try {
                android.util.Log.d("Remote", "frequency: " + cmd.freq);
                android.util.Log.d("Remote", "pattern: " + Arrays.toString(cmd.pattern));
                irManager.transmit(cmd.freq, cmd.pattern);
            } catch (Exception e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    private class IRCommand {
        private final int freq;
        private final int[] pattern;

        private IRCommand(int freq, int[] pattern) {
            this.freq = freq;
            this.pattern = pattern;
        }
    }
}