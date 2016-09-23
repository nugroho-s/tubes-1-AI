class day {

    boolean hari[] = new boolean[6]; //hari dienumerasi sebagai indeks 1-5

    public String toString() {
        String os = "";
        boolean ada = false;
        for (int i = 1; i < 6; i++) {
            if (hari[i] == true) {
                if (ada) {
                    os = os.concat(",");
                }
                os = os.concat(Integer.toString(i));
                ada = true;
            }
        }
        return os;
    }

    public boolean isOnDay(int x) {
        return (hari[x]);
    }

    public day() {
        for (int i = 0; i < 6; i++) {
            hari[i] = false;
        }
    }

    public boolean get_hari_ke(int x) {
        return hari[x];
    }

    public void set_hari(int i, boolean s) {
        hari[i] = s;
    }
}
