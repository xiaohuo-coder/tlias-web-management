package extendsDemo;

public class MathExam {
    public static void main(String[] args) {
        System.out.println(isPalindrome(122));
    }

    public static boolean isPalindrome(int x) {
        //把数字变成字符串
        String str = String.valueOf(x);
        //创建数组
        Character[] array = new Character[str.length()];
        //把每一位数字存进数组
        for (int i = 0; i<str.length(); i++) {
            array[i] = str.charAt(i);
        }
        //遍历数组
        for (int i = 0; i<str.length(); i++) {
            if (array[i] != (array[array.length-1-i])) {
                return false;
            }
        }
       return true;
    }
}
