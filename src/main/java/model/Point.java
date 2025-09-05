package model;

import lombok.AllArgsConstructor;
import lombok.Getter;



@Getter
@AllArgsConstructor
public enum Point {
    ZERO ("0"), FIFTEEN ("15"), THIRTEEN ("30"), FORTY ("40"), ADVANCED ( "AD");

    private final String value;

    @Override
    public String toString(){
        return value;
    }


}
