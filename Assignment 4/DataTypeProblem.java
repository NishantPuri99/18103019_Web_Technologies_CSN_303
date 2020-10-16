public class DataTypeProblem {
    public static void main(String[] args) {
        int int_value = -1;
        System.out.println("Initial Integer Value: "+ int_value);
        byte byte_value = (byte)int_value;
        System.out.println("(Converted to) Byte Value: "+ byte_value);
        char char_value = (char)byte_value;
        System.out.println("(Converted to) Char Value: "+ char_value);
        int_value = (int)char_value;
        System.out.println("Final (Converted to) Integer Value: "+ int_value);       
    }
}
