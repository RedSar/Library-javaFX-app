package ma.rsmi.bibleo.utils;

public enum Color {
        GREEN("#81ecec");
        private String hexCode;
    Color(String hexCode) {
        this.hexCode = hexCode;
    }

    public String getHexCode() {
        return hexCode;
    }


}
