package model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TieBreak {
    private int firstPoints = 0;
    private int secondPoints = 0;
    private boolean isTieBreak = false;

    public boolean getIsTieBreak() {
        return isTieBreak;
    }
    public void reset(){
        firstPoints = 0;
        secondPoints = 0;
        isTieBreak = false;
    }
}
