public class Gatto {

    public Gatto() {}

    public String miagolare(int tipoMiagolio) {
        String miagolio = "Not Miaow";

        switch(tipoMiagolio) {
            case 1:
                miagolio = "Miaow";
                break;
            case 2:
                miagolio = "MIAOWWW";
                break;
        }

        return miagolio;
    }

}
