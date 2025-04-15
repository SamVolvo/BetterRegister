package com.samvolvo.enums;

public enum Gebruiker {
    SAM(32, 413),
    EMANUELE(05, 000),
    WARRE(17, 000),
    BO(30, 000),
    HEIDI(02, 000),
    GANI(03, 000),
    DORIEN(12, 000),
    EMEL(06, 000),
    NANCY(20, 000),
    HANANE(13, 000),
    SANDER(04, 000),
    UTE(07, 000)
    ;

    private int id;
    private int code;

    Gebruiker(int id, int code){
        this.id = id;
        this.code = code;
    }

    public int getId(){
        return id;
    }

    public int getCode(){
        return code;
    }

    public static Gebruiker getGebruikerDoorId(int id){
        for (Gebruiker gebruiker : values()){
            if (gebruiker.getId() == id){
                return gebruiker;
            }
        }
        return null;
    }
}
