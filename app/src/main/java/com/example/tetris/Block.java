package com.example.tetris;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

public class Block {
    public int[] b;
    public static int xx = 586;
    public static int type, direction;
    public Block(int type, int direction){
        this.type = type;
        this.direction = direction;
        this.b = shape[type - 1][direction - 1];
    }
    public static int[][][] shape = new int[][][]{
            //l
            {
                { 1, 1, 1, 1,
                  0, 0, 0, 0,
                  0, 0, 0, 0,
                  0, 0, 0, 0 },
                { 0, 1, 0, 0,
                  0, 1, 0, 0,
                  0, 1, 0, 0,
                  0, 1, 0, 0 },
                { 1, 1, 1, 1,
                  0, 0, 0, 0,
                  0, 0, 0, 0,
                  0, 0, 0, 0 },
                { 0, 1, 0, 0,
                  0, 1, 0, 0,
                  0, 1, 0, 0,
                  0, 1, 0, 0 }
            },
            //s
            {
                { 0, 1, 1, 0,
                  1, 1, 0, 0,
                  0, 0, 0, 0,
                  0, 0, 0, 0 },
                { 1, 0, 0, 0,
                  1, 1, 0, 0,
                  0, 1, 0, 0,
                  0, 0, 0, 0},
                { 0, 1, 1, 0,
                  1, 1, 0, 0,
                  0, 0, 0, 0,
                  0, 0, 0, 0, },
                { 1, 0, 0, 0,
                  1, 1, 0, 0,
                  0, 1, 0, 0,
                  0, 0, 0, 0}
            },
            //z
            {
                { 1, 1, 0, 0,
                  0, 1, 1, 0,
                  0, 0, 0, 0,
                  0, 0, 0, 0 },
                { 0, 1, 0, 0,
                  1, 1, 0, 0,
                  1, 0, 0, 0,
                  0, 0, 0, 0 },
                { 1, 1, 0, 0,
                  0, 1, 1, 0,
                  0, 0, 0, 0,
                  0, 0, 0, 0 },
                { 0, 1, 0, 0,
                  1, 1, 0, 0,
                  1, 0, 0, 0,
                  0, 0, 0, 0 },
            },
            //J
            {
                { 0, 1, 0, 0,
                  0, 1, 0, 0,
                  1, 1, 0, 0,
                  0, 0, 0, 0 },
                { 1, 0, 0, 0,
                  1, 1, 1, 0,
                  0, 0, 0, 0,
                  0, 0, 0, 0 },
                { 1, 1, 0, 0,
                  1, 0, 0, 0,
                  1, 0, 0, 0,
                  0, 0, 0, 0 },
                { 1, 1, 1, 0,
                  0, 0, 1, 0,
                  0, 0, 0, 0,
                  0, 0, 0, 0 }
            },
            //o
            {
                { 1, 1, 0, 0,
                  1, 1, 0, 0,
                  0, 0, 0, 0,
                  0, 0, 0, 0 },
                { 1, 1, 0, 0,
                  1, 1, 0, 0,
                  0, 0, 0, 0,
                  0, 0, 0, 0 },
                { 1, 1, 0, 0,
                  1, 1, 0, 0,
                  0, 0, 0, 0,
                  0, 0, 0, 0 },
                { 1, 1, 0, 0,
                  1, 1, 0, 0,
                  0, 0, 0, 0,
                  0, 0, 0, 0 }
            },
            //L
            {
                { 1, 0, 0, 0,
                  1, 0, 0, 0,
                  1, 1, 0, 0,
                  0, 0, 0, 0 },
                { 1, 1, 1, 0,
                  1, 0, 0, 0,
                  0, 0, 0, 0,
                  0, 0, 0, 0 },
                { 1, 1, 0, 0,
                  0, 1, 0, 0,
                  0, 1, 0, 0,
                  0, 0, 0, 0 },
                { 0, 0, 1, 0,
                  1, 1, 1, 0,
                  0, 0, 0, 0,
                  0, 0, 0, 0 }
            },
            //T
            {
                { 0, 1, 0, 0,
                  1, 1, 1, 0,
                  0, 0, 0, 0,
                  0, 0, 0, 0 },
                { 0, 1, 0, 0,
                  0, 1, 1, 0,
                  0, 1, 0, 0,
                  0, 0, 0, 0 },
                { 1, 1, 1, 0,
                  0, 1, 0, 0,
                  0, 0, 0, 0,
                  0, 0, 0, 0 },
                { 0, 1, 0, 0,
                  1, 1, 0, 0,
                  0, 1, 0, 0,
                  0, 0, 0, 0}
            }
    };

    public ArrayList<BlockUnit> get_units(int X, int Y){
        ArrayList<BlockUnit> res = new ArrayList<>();
        int x = X, y = Y;
        for (int i = 1; i <= this.b.length; ++i){
            if (this.b[i - 1] == 1){
                BlockUnit bu = new BlockUnit(x, y);
                res.add(bu);
            }
            x += BlockUnit.unit_size;
            if (i % 4 == 0){
                x = X;
                y += BlockUnit.unit_size;
            }
        }
        return res;
    }

}
