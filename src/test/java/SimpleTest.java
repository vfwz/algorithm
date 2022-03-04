public class SimpleTest {

    public static void main(String[] args) {

        for (int i = 0; i < 128; i++) {
            char c = (char)i;
            System.out.println((short)c + ":"+c +":" + Character.getNumericValue(c));
        }

    }

}
