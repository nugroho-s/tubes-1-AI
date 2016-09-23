public class waktu {

    int jam;
    int menit;

    public int get_jam() {
        return jam;
    }

    public int get_menit() {
        return menit;
    }

    public void set_jam(int j) {
        jam = j;
    }

    public void set_menit(int m) {
        menit = m;
    }

    public static waktu konversikewaktu(String str) {
        String[] temp = str.split("\\.");
        waktu wk = new waktu();
        wk.jam = Integer.parseInt(temp[0]);
        wk.menit = Integer.parseInt(temp[1]);
        return wk;
    }

    public String toString() {
        return ((String.format("%02d", get_jam())) + "." + (String.format("%02d", get_menit())));
    }
}
