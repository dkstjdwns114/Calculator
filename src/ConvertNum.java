public class ConvertNum {
    static int lastIndex;
    public static String conPow(String formula) { // 제곱을 숫자로 표현하기
        int index = 0;
        int digit1 = 0, digit2 = 0;
        double num1, num2;
        double result = 0;
        String f1 = "", f2 = "";
        String rep = "";

        if(formula.indexOf("^") != -1){
            index = formula.lastIndexOf("^");
            // ^ 앞에 숫자 찾아내기
            for(int i = index - 1; i >= 0; i--){
                if(48 <= formula.charAt(i) && formula.charAt(i) <= 57
                    || (formula.charAt(i) + "").equals(".")){
                    digit1 = index - i;
                    rep += formula.charAt(i);
                }else break;
            }
            rep = reverseString(rep);
            if(index - digit1 == index - 1){
                f1 = formula.charAt(index - 1) + "";
            } else{
                for(int i = index - digit1; i < index; i++){
                    f1 += formula.charAt(i);
                }
            }
            try {
                num1 = Double.parseDouble(f1);
            } catch (NumberFormatException e) { num1 = 0; }

            rep += "^";

            // ^ 뒤에 숫자 찾아내기
            for(int i = 1; i < formula.length() - index; i++){
                if(48 <= formula.charAt(index + i) && formula.charAt(index + i) <= 57
                        || (formula.charAt(index + i) + "").equals(".")){
                    digit2 = i;
                    rep += formula.charAt(index + i);
                }else break;
            }
            if(index + 1 == index + digit2){
                f2 = formula.charAt(index + 1) + "";
            } else {
                for(int i = index + 1; i <= index + digit2; i++){
                    f2 += formula.charAt(i);
                }
            }
            try {
                num2 = Double.parseDouble(f2);
            } catch (NumberFormatException e){ num2 = 0; }
            result = Math.pow(num1, num2);
            // ^ 있는 식을 숫자로 바꾸기
            formula = formula.replace(rep, Double.toString(result));
        }
        return formula;
    }

    public static String conSqrt(String formula){
        int index = 0;
        int digit1 = 0, digit2 = 0;
        double num1, num2;
        double result = 0;
        String f1 = "", f2 = "";
        String rep = "";

        if(formula.indexOf("v") != -1){
            index = formula.indexOf("v");
            // v 앞에 숫자 찾아내기
            for(int i = index - 1; i >= 0; i--){
                if(48 <= formula.charAt(i) && formula.charAt(i) <= 57
                        || (formula.charAt(i) + "").equals(".")){
                    digit1 = index - i;
                    rep += formula.charAt(i);
                }else break;
            }
            rep = reverseString(rep);
            if(index - digit1 == index - 1){
                f1 = formula.charAt(index - 1) + "";
            } else{
                for(int i = index - digit1; i < index; i++){
                    f1 += formula.charAt(i);
                }
            }
            try {
                num1 = Double.parseDouble(f1);
                if(num1 == 0) num1 = 1;
            } catch (NumberFormatException e) { num1 = 1; }

            rep += "v";

            // v 뒤에 숫자 찾아내기
            for(int i = 1; i < formula.length() - index; i++){
                if(48 <= formula.charAt(index + i) && formula.charAt(index + i) <= 57
                        || (formula.charAt(index + i) + "").equals(".")){
                    digit2 = i;
                    rep += formula.charAt(index + i);
                }else break;
            }
            if(index + 1 == index + digit2){
                f2 = formula.charAt(index + 1) + "";
            } else {
                for(int i = index + 1; i <= index + digit2; i++){
                    f2 += formula.charAt(i);
                }
            }
            try {
                num2 = Double.parseDouble(f2);
            } catch (NumberFormatException e){ num2 = 0; }
            result = num1 * Math.sqrt(num2);
            // v 있는 식을 숫자로 바꾸기
            formula = formula.replace(rep, Double.toString(result));
        }

        return formula;
    }

    public static String conFac(String formula){
        int index = 0;
        int digit = 0;
        int num;
        int result = 1;
        String f = "";
        String rep = "";

        if(formula.indexOf("!") != -1){
            index = formula.indexOf("!");
            // ! 앞에 숫자 찾아내기
            for(int i = index - 1; i >= 0; i--){
                if(48 <= formula.charAt(i) && formula.charAt(i) <= 57){
                    digit = index - i;
                    rep += formula.charAt(i);
                }else break;
            }
            rep = reverseString(rep);
            if(index - digit == index - 1){
                f = formula.charAt(index - 1) + "";
            } else{
                for(int i = index - digit; i < index; i++){
                    f += formula.charAt(i);
                }
            }
            try {
                num = Integer.parseInt(f);
            } catch (NumberFormatException e) { num = 0; }

            rep += "!";

            for(int i = 1; i <= num; i++){
                result *= i;
            }
            // ! 있는 식을 숫자로 바꾸기
            formula = formula.replace(rep, Double.toString(result));
        }
        return formula;
    }
    public static String conNeg(String formula) { // 음수 처리하기
        int index = 0;
        int digit = 0;
        double num;
        String result = "";
        String f = "-";
        String rep = "-";

        if(formula.indexOf("-", lastIndex) != -1){
            index = formula.indexOf("-", lastIndex);
            if(index == 0 || formula.charAt(index - 1) == '+' || formula.charAt(index - 1) == '-'
                || formula.charAt(index - 1) == '*' || formula.charAt(index - 1) == '/' || formula.charAt(index - 1) == '('){
                // - 뒤에 숫자 찾아내기
                for(int i = 1; i < formula.length() - index; i++){
                    if(48 <= formula.charAt(index + i) && formula.charAt(index + i) <= 57
                            || (formula.charAt(index + i) + "").equals(".")){
                        digit = i;
                        rep += formula.charAt(index + i);
                    }else break;
                }
                // 만약 한자리수면
                if(index + 1 == index + digit){
                    f += formula.charAt(index + 1) + "";
                } else {
                    for(int i = index + 1; i <= index + digit; i++){
                        f += formula.charAt(i);
                    }
                }
                try {
                    num = Double.parseDouble(f);
                } catch (NumberFormatException e){
                    num = 0;
                }
                num -= 1;
                result = "(1" + num + ")";
                // - 있는 식을 숫자로 바꾸기
                formula = formula.replace(rep, result);
            }
            lastIndex = index + result.length();
        }
        return formula;
    }

    public static String reverseString(String s){ // 문자열 뒤집어주는 함수
        return (new StringBuffer(s)).reverse().toString();
    }
}

