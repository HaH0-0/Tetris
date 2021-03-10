package com.example.tetris;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CancellationSignal;
import android.util.Log;
import android.view.SurfaceHolder;

import java.util.ArrayList;
import java.util.Random;

public class DrawThread extends Thread {

    private SurfaceHolder surfaceHolder;

    Paint p = new Paint();
    Paint backgroundPaint = new Paint();
    Paint cube_paint = new Paint();
    Paint block_stroke_paint = new Paint();
    Paint block_fill_paint = new Paint();
    String line_color = "#B8B8B8";
    String background_color = "#242240";
    String frame_color = "#6D6E71";
    String block_fill_color = "#66ff66";
    String block_stroke_color = "#B8B8B8";
    private volatile boolean running = true;//флаг для остановки потока

    public DrawThread(Context context, SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        p.setColor(Color.parseColor(frame_color));
        backgroundPaint.setColor(Color.parseColor(background_color));
        p.setStyle(Paint.Style.FILL);
        backgroundPaint.setStyle(Paint.Style.FILL);
        cube_paint.setStyle(Paint.Style.FILL);
        cube_paint.setColor(Color.parseColor(line_color));
        block_stroke_paint.setStyle(Paint.Style.STROKE);
        block_stroke_paint.setColor(Color.parseColor(block_stroke_color));
        block_fill_paint.setStyle(Paint.Style.FILL);
        block_fill_paint.setColor(Color.parseColor(block_fill_color));
    }

    public void requestStop() {
        running = false;
    }

    private void Draw_Frame(Canvas c){
        c.drawRect(0, 0, c.getWidth(), c.getHeight(), backgroundPaint);
        c.drawRect(0, 160, c.getWidth(), 1500,  p);
        c.drawRect(100, 220, c.getWidth() - 120, 1440, backgroundPaint);
    }

    private void Draw_Cubes(Canvas c){
        for (int i = 220; i < 1440; i += 81){
            c.drawLine(100, i, c.getWidth() - 120, i, cube_paint);
        }

        for (int i = 100; i < c.getWidth() - 120; i += 81){
            c.drawLine(i, 220, i, 1440, cube_paint);
        }
    }

    private Block Get_Next_Block(){
        Random rand = new Random();
        int type = rand.nextInt(7);
        Block b = new Block(type + 1, 1);
        return b;
    }

    public void draw_blocks(Canvas c, Paint p_stroke, Paint p_fill, ArrayList<BlockUnit> b){
        for (BlockUnit bu: b){
            bu.create_unit(c, p_fill, p_stroke);
        }
    }


    @Override
    public void run() {
        while (running) {
            Log.d("r","runrunrun");
            Canvas canvas = surfaceHolder.lockCanvas();
            if (canvas != null) {
                try {
                    if (BlockUnit.GameOver(Content.All_bu)) {
                        Content.Bu_falling.clear();
                        Content.All_bu.clear();
                    }
                    if (Content.Bu_falling.size() == 0){
                        Content.Block_Tetris = Get_Next_Block();
                        for (BlockUnit bu: Content.Block_Tetris.get_units(Block.xx, BlockUnit.Top))
                            Content.Bu_falling.add(bu);
                    }
                    Draw_Frame(canvas);
                    Draw_Cubes(canvas);
                    draw_blocks(canvas, block_stroke_paint, block_fill_paint, Content.Bu_falling);
                    draw_blocks(canvas, block_stroke_paint, block_fill_paint, Content.All_bu);
                    if (BlockUnit.CanMoveDown(Content.Bu_falling, Content.All_bu)){
                        BlockUnit.MoveDown(Content.Bu_falling, Content.All_bu);
                    } else {
                        for (BlockUnit bu: Content.Bu_falling){
                            bu.y += BlockUnit.unit_size;
                            Content.All_bu.add(bu);
                        }
                        Content.Bu_falling.clear();
                        Content.Block_Tetris = Get_Next_Block();
                        for (BlockUnit bu: Content.Block_Tetris.get_units(Block.xx, BlockUnit.Top))
                            Content.Bu_falling.add(bu);
                    }

                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    for (int i = 0; i <= BlockUnit.max_y_amount; ++i){
                        if (BlockUnit.remove(Content.All_bu, i)){
                            for (BlockUnit bu: Content.All_bu){
                                if (bu.y < ((i - 1) * BlockUnit.unit_size + BlockUnit.Top))
                                    bu.y += BlockUnit.unit_size;
                            }
                        }
                    }
                } finally {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        }
    }
}