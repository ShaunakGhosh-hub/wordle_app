package com.example.wordle;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;


public class GameSix extends Fragment implements View.OnClickListener{

    private View binding;
    private TextView[][] fields = new TextView[6][6];
    private HashMap<Character, Button> buttons = new HashMap<Character, Button>();
    private int row = 0;
    private int column = 0;
    private Engine engine;
    private View popupView;
    private PopupWindow popupError;
    private View popupWinView;
    private PopupWindow popupWin;
    private View popupLoseView;
    private PopupWindow popupLose;
    private Boolean end = false;
    private final int size = 6;


    public GameSix() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        popupView = inflater.inflate(R.layout.pop_up, null);
        popupWinView = inflater.inflate(R.layout.popup_win, null);
        popupLoseView = inflater.inflate(R.layout.popup_lose, null);
        return inflater.inflate(R.layout.fragment_game_six, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        engine = new Engine(getContext(), size);

        binding = view;

        binding.findViewById(R.id.enter).setOnClickListener(this);
        binding.findViewById(R.id.delete).setOnClickListener(this);
        binding.findViewById(R.id.refresh).setOnClickListener(this);
        binding.findViewById(R.id.back).setOnClickListener(this);

        setButtons();
        setFields();
        row = 0;
        column = 0;
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        boolean focusable = true;
        popupError = new PopupWindow(popupView, width, height, focusable);
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupError.dismiss();
                return true;
            }
        });
        popupWin = new PopupWindow(popupWinView, width, height, focusable);
        popupWinView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWin.dismiss();
                return true;
            }
        });
        popupLose = new PopupWindow(popupLoseView, width, height, focusable);
        popupLoseView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupLose.dismiss();
                return true;
            }
        });

        while (engine.isLine()) {
            String word = engine.getLineWord();
            for (int i = 0; i < size; i++)
                fields[row][i].setText(word.substring(i, i+1).toUpperCase());
            markLine (engine.getLine());
            row += 1;
        }
        markLetters(engine.getAlphabet());
    }

    private void setButtons () {
        buttons.put('q', (Button) binding.findViewById(R.id.q));
        buttons.put('w', (Button) binding.findViewById(R.id.w));
        buttons.put('e', (Button) binding.findViewById(R.id.e));
        buttons.put('r', (Button) binding.findViewById(R.id.r));
        buttons.put('t', (Button) binding.findViewById(R.id.t));
        buttons.put('y', (Button) binding.findViewById(R.id.y));
        buttons.put('u', (Button) binding.findViewById(R.id.u));
        buttons.put('i', (Button) binding.findViewById(R.id.i));
        buttons.put('o', (Button) binding.findViewById(R.id.o));
        buttons.put('p', (Button) binding.findViewById(R.id.p));
        buttons.put('a', (Button) binding.findViewById(R.id.a));
        buttons.put('s', (Button) binding.findViewById(R.id.s));
        buttons.put('d', (Button) binding.findViewById(R.id.d));
        buttons.put('f', (Button) binding.findViewById(R.id.f));
        buttons.put('g', (Button) binding.findViewById(R.id.g));
        buttons.put('h', (Button) binding.findViewById(R.id.h));
        buttons.put('j', (Button) binding.findViewById(R.id.j));
        buttons.put('k', (Button) binding.findViewById(R.id.k));
        buttons.put('l', (Button) binding.findViewById(R.id.l));
        buttons.put('z', (Button) binding.findViewById(R.id.z));
        buttons.put('x', (Button) binding.findViewById(R.id.x));
        buttons.put('c', (Button) binding.findViewById(R.id.c));
        buttons.put('v', (Button) binding.findViewById(R.id.v));
        buttons.put('b', (Button) binding.findViewById(R.id.b));
        buttons.put('n', (Button) binding.findViewById(R.id.n));
        buttons.put('m', (Button) binding.findViewById(R.id.m));
        buttons.put('ą', (Button) binding.findViewById(R.id.ą));
        buttons.put('ć', (Button) binding.findViewById(R.id.ć));
        buttons.put('ę', (Button) binding.findViewById(R.id.ę));
        buttons.put('ł', (Button) binding.findViewById(R.id.ł));
        buttons.put('ó', (Button) binding.findViewById(R.id.ó));
        buttons.put('ś', (Button) binding.findViewById(R.id.ś));
        buttons.put('ń', (Button) binding.findViewById(R.id.ń));
        buttons.put('ż', (Button) binding.findViewById(R.id.ż));
        buttons.put('ź', (Button) binding.findViewById(R.id.ź));

        for (Button butt: buttons.values()) {
            butt.setOnClickListener(this);
        }

    }

    private void setFields () {
        fields[0][0] = (TextView) binding.findViewById(R.id.text1_1);
        fields[0][1] = (TextView) binding.findViewById(R.id.text1_2);
        fields[0][2] = (TextView) binding.findViewById(R.id.text1_3);
        fields[0][3] = (TextView) binding.findViewById(R.id.text1_4);
        fields[0][4] = (TextView) binding.findViewById(R.id.text1_5);
        fields[0][5] = (TextView) binding.findViewById(R.id.text1_6);

        fields[1][0] = (TextView) binding.findViewById(R.id.text2_1);
        fields[1][1] = (TextView) binding.findViewById(R.id.text2_2);
        fields[1][2] = (TextView) binding.findViewById(R.id.text2_3);
        fields[1][3] = (TextView) binding.findViewById(R.id.text2_4);
        fields[1][4] = (TextView) binding.findViewById(R.id.text2_5);
        fields[1][5] = (TextView) binding.findViewById(R.id.text2_6);

        fields[2][0] = (TextView) binding.findViewById(R.id.text3_1);
        fields[2][1] = (TextView) binding.findViewById(R.id.text3_2);
        fields[2][2] = (TextView) binding.findViewById(R.id.text3_3);
        fields[2][3] = (TextView) binding.findViewById(R.id.text3_4);
        fields[2][4] = (TextView) binding.findViewById(R.id.text3_5);
        fields[2][5] = (TextView) binding.findViewById(R.id.text3_6);

        fields[3][0] = (TextView) binding.findViewById(R.id.text4_1);
        fields[3][1] = (TextView) binding.findViewById(R.id.text4_2);
        fields[3][2] = (TextView) binding.findViewById(R.id.text4_3);
        fields[3][3] = (TextView) binding.findViewById(R.id.text4_4);
        fields[3][4] = (TextView) binding.findViewById(R.id.text4_5);
        fields[3][5] = (TextView) binding.findViewById(R.id.text4_6);

        fields[4][0] = (TextView) binding.findViewById(R.id.text5_1);
        fields[4][1] = (TextView) binding.findViewById(R.id.text5_2);
        fields[4][2] = (TextView) binding.findViewById(R.id.text5_3);
        fields[4][3] = (TextView) binding.findViewById(R.id.text5_4);
        fields[4][4] = (TextView) binding.findViewById(R.id.text5_5);
        fields[4][5] = (TextView) binding.findViewById(R.id.text5_6);

        fields[5][0] = (TextView) binding.findViewById(R.id.text6_1);
        fields[5][1] = (TextView) binding.findViewById(R.id.text6_2);
        fields[5][2] = (TextView) binding.findViewById(R.id.text6_3);
        fields[5][3] = (TextView) binding.findViewById(R.id.text6_4);
        fields[5][4] = (TextView) binding.findViewById(R.id.text6_5);
        fields[5][5] = (TextView) binding.findViewById(R.id.text6_6);
    }

    @Override
    public void onClick(View view) {
        String name = (String) getResources().getResourceEntryName(view.getId());
        if (name.equals("enter")) {
            if (row < 6 && column == size) {
                String word = "";
                for (int i = 0; i < size; i++)
                    word += fields[row][i].getText().toString();
                if (engine.loadWord(word.toLowerCase())) {
                    markLetters (engine.getAlphabet());
                    markLine (engine.getLine());
                    column = 0;
                    row += 1;
                }
                else {
                    popupError.showAtLocation(binding, Gravity.TOP, 0, 0);
                }
            }
            if (engine.checkWin()) {
                engine.endGame();
                popupWin.showAtLocation(binding, Gravity.CENTER, 0, 0);
                view.setClickable(false);
                end = true;
            }
            else if (row == 6) {
                engine.endGame();
                popupLose.showAtLocation(binding, Gravity.CENTER, 0, 0);
                view.setClickable(false);
                end = true;
            }
        }
        else if (name.equals("delete")) {
            if (row < 6 && column > 0) {
                column -= 1;
                fields[row][column].setText("");
            }
        }
        else if (name.equals("refresh")) {
            engine.endGame();
            NavHostFragment.findNavController(GameSix.this)
                    .navigate(R.id.action_gameSix_self);
        }
        else if (name.equals("back")) {
            NavHostFragment.findNavController(GameSix.this)
                    .navigate(R.id.action_gameSix_to_mainFragment);
        }
        else {
            if ((!end) && row < 6 && column < size) {
                fields[row][column].setText(name.toUpperCase());
                column += 1;
            }
        }
    }

    private void markLetters(HashMap<Character, Integer> map) {
        for(Map.Entry<Character, Integer> entry: map.entrySet()) {
            Character letter = entry.getKey();
            Integer value = entry.getValue();
            if (value == 1) {
                buttons.get(letter).setBackgroundColor(Color.parseColor("#333333"));
            }
            else if (value == 2) {
                buttons.get(letter).setBackgroundColor(Color.parseColor("#DBC500"));
            }
            else if (value == 3) {
                buttons.get(letter).setBackgroundColor(Color.parseColor("#339237"));
            }
        }
    }

    private void markLine(int[] line) {
        for (int i = 0; i < size; i++) {
            int value = line[i];
            if (value == 1) {
                fields[row][i].setBackground(getResources().getDrawable(R.drawable.black_field));
            }
            else if (value == 2) {
                fields[row][i].setBackground(getResources().getDrawable(R.drawable.yellow_field));
            }
            else if (value == 3) {
                fields[row][i].setBackground(getResources().getDrawable(R.drawable.green_field));
            }
        }
    }
}