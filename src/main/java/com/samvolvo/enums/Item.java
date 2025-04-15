package com.samvolvo.enums;

public enum Item {
    //AGF
    APPEL(86, 1.00),
    BANNAAN(55, 0.50),
    BANNAANRFAIRTRADE(79, 0.95),
    BANNAANBIO(157, 1.12),
    MANDERIJN(65, 0.75),
    KOMKOMMER(30, 0.68),
    WATERMELOEN(120, 5.96),
    BABYWATERMELOEN(52, 3.48),
    KIWI(74, 1.24),
    MANGO(53, 2.44),
    ADVOCADO(54, 1.39),
    KIWIGOLD(752, 3.47),
    ANANAS(58, 7.32),
    AUBERGINE(36, 2.36),
    KNOLSELDER(22, 4.20),
    COURGETTE(35, 2.47),
    UI(616, 2.84),
    LENTEUI(33, 2.30),
    LIMOEN(566, 1.59),
    CITROEN(564, 4.22),
    PAPRIKA(7, 1.46),
    GEMBER(183, 0.65),
    ZOETEAARDAPPEL(285, 1.30),
    APPELGOLD(80, 1.23),

    // BakeOff
    PISTOLET(825, 0.35),
    KEIZERBROODJE(848, 0.42),
    DONUTCHOCO(827, 0.78),
    DONUTPINKY(835, 0.78),
    VIERKANTWIT(32, 1.49),
    VIERKANTBRUIN(37, 1.49),
    STOKBROODFRANS(867, 2.30),
    STOKBROODWIT(890, 1.60),
    HALFSTOKBROODWIT(779, 0.49),
    HALFSTOKBROODMEERGRANEN(748, 0.95),
    APPELFLAP(894, 1.25),
    MEXICANOBROODJE(235, 1.95),
    WORSTENBROODJE(141, 1.25),
    ACHTKOEK(850, 1.80),
    WALNOOTBROODJE(814, 1.48),
    TIJGERBROOD(880, 1.44),
    BOTERCROISSANT(859, 3.90),
    BACHETTAMETHAM(772, 2.29),
    LICHTMEERGRANEN(692, 1.89),
    BOERENBRUIN(888, 1.79),
    BOERENWIT(887, 1.79),
    MEERGRANENRUSTIEK(133, 2.49),
    DONKERMEERGRANEN(884, 1.89),
    VOLKORENBROOD(742, 2.19),
    BOTERSANDWICH(746, 0.40),
    CHOCOLADEBROODJE(795, 0.34),
    ROZIJNENKOEK(895, 0.80),
    PEKANOTENKOEK(793, 0.95),


    //Acties
    MARGUERITE(5509522, 0.99)
    ;

    private final int plu;
    private final double price;

    Item(int plu, double price){
        this.plu = plu;
        this.price = price;
    }

    public int getPlu(){
        return plu;
    }

    public double getPrice() {
        return price;
    }

    public static Item getItemByPlu(int plu){
        for (Item item : values()){
            if (item.getPlu() == plu){
                return item;
            }
        }
        return null;
    }
}
