package com.example.tetris;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockUnit {
    public int x, y;
    public static int unit_size = 81, Left = 100, Right = 1072, Bottom = 1435, Top = 220;
    public static int max_y_amount = 15, max_x_amount = 12;
    public BlockUnit(int x, int y){
        this.x = x;
        this.y = y;
    }


    public static boolean CheckExist(int x, int y, ArrayList<BlockUnit> All_bu){
        for (BlockUnit bu: All_bu){
            if (Math.abs(x - bu.x) < unit_size && Math.abs(y - bu.y) < unit_size)
                return true;
        }
        return false;
    }

    public static boolean CanMoveLeft(ArrayList<BlockUnit> blocks, ArrayList<BlockUnit> All_bu){
        for (BlockUnit bu: blocks){
            if (bu.x - unit_size < Left)
                return false;
            if (CheckExist(bu.x - unit_size, bu.y, All_bu))
                return false;
        }
        return true;
    }

    public static boolean CanMoveRight(ArrayList<BlockUnit> blocks, ArrayList<BlockUnit> All_bu){
        for (BlockUnit bu: blocks){
            if (bu.x + 2 * unit_size > Right)
                return false;
            if (CheckExist(bu.x + unit_size, bu.y, All_bu))
                return false;
        }
        return true;
    }

    public static boolean CanMoveDown(ArrayList<BlockUnit> blocks, ArrayList<BlockUnit> All_bu){
        for (BlockUnit bu: blocks){
            if (bu.y + 2 * unit_size >= Bottom) {
                return false;
            }
            if (CheckExist(bu.x, bu.y + 2 * unit_size, All_bu)) {
                return false;
            }
        }
        return true;
    }

    public static boolean CanRotate(ArrayList<BlockUnit> blocks, ArrayList<BlockUnit> All_bu){
        for (BlockUnit bu: blocks){
            if (CheckExist(bu.x, bu.y, All_bu))
                return false;
        }
        return true;
    }

    public static void MoveDown(ArrayList<BlockUnit> blocks, ArrayList<BlockUnit> All_bu){
        if (CanMoveDown(blocks, All_bu)){
            for (BlockUnit bu: blocks){
                bu.y += unit_size;
            }
        }
    }

    public static void MoveLeft(ArrayList<BlockUnit> blocks, ArrayList<BlockUnit> All_bu){
        if (CanMoveLeft(blocks, All_bu)){
            for (BlockUnit bu: blocks){
                bu.x -= unit_size;
            }
        }
    }

    public static void MoveRight(ArrayList<BlockUnit> blocks, ArrayList<BlockUnit> All_bu){
        if (CanMoveRight(blocks, All_bu)){
            for (BlockUnit bu: blocks){
                bu.x += unit_size;
            }
        }
    }

    public static int find_left(ArrayList<BlockUnit> blocks){
        int res = Right;
        for (BlockUnit bu: blocks){
            if (bu.x < res)
                res = bu.x;
        }
        return res;
    }

    public static int find_top(ArrayList<BlockUnit> blocks){
        int res = Bottom;
        for (BlockUnit bu: blocks){
            if (bu.y < res)
                res = bu.y;
        }
        return res;
    }

    public static ArrayList<BlockUnit> Rotate(Block b, ArrayList<BlockUnit> blocks){
        int left = find_left(blocks), top = find_top(blocks);
        int direction;
        if (b.direction == 4)
            direction = 1;
        else
            direction = b.direction + 1;
        Block new_b = new Block(b.type, direction);
        return new_b.get_units(left, top);
    }


    public static boolean remove(ArrayList<BlockUnit> All_bu, int i){
        int count = 0;
        boolean ok = false;
        int y = Top + (i - 1) * unit_size;
        for (int k = 0; k < All_bu.size(); ++k){
            if (All_bu.get(k).y == y)
                count++;
        }
        if (count == max_x_amount){
            ok = true;
            for (int k = All_bu.size() - 1; k >= 0; --k){
                if (All_bu.get(k).y == y)
                    All_bu.remove(All_bu.get(k));
            }
        }
        return ok;
    }

    public static boolean GameOver(ArrayList<BlockUnit> All_bu){
        int x = Block.xx;
        for (int i = 0; i < 4; ++i){
            if (CheckExist(x, Top + unit_size, All_bu))
                return true;
            x += unit_size;
        }
        return false;
    }


    public void create_unit(Canvas c, Paint p_fill, Paint p_stroke){
        c.drawRect(this.x, this.y, this.x + unit_size, this.y + unit_size, p_fill);
        c.drawRect(this.x, this.y, this.x + unit_size, this.y + unit_size, p_stroke);
    }


}
