import java.io.IOException;
import java.text.DecimalFormat;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        String result = "";
        String confirm = "0123456789!+-*/()^v. ";
        Scanner sc = new Scanner(System.in);
        // 숫자에 콤마 찍기
        DecimalFormat dc = new DecimalFormat("###,###,###.00");
        ConvertNum convertNum = new ConvertNum();

        while(true){
            System.out.println("프로그램종료 : exit 입력");
            System.out.print("수식을 입력하세요 : ");
            String formula = sc.nextLine();

            if(formula.equals("exit")){
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            for(int i = 0; i < formula.length(); i++){
                if(confirm.indexOf(formula.charAt(i)) == -1) {
                    System.out.println("잘못된 수식을 입력하였습니다.");
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
            }
            formula = conNum(formula);
            System.out.println("^, v, !, 음수 정리 결과 : " + formula);

            // 중위표현식을 후위표현식으로
            PostfixConverter pc = new PostfixConverter(formula);
            System.out.print("후위표현식 : ");
            pc.printExpression();
            System.out.println();

            // 후위표현식 계산
            PostFixCalculator calc = new PostFixCalculator(pc.getPostfixAsList());
            try{
                System.out.println("후위표현식 계산 결과 : " + calc.result());
            } catch (NoSuchElementException e){
                System.out.println("잘못된 식을 입력하였습니다.");
                System.out.println("프로그램을 종료합니다.");
                return;
            }

            result = dc.format(calc.result());
            if(result.equals(".00")) result = "0.00";
            System.out.println("최종 결과 : " + result);
            System.out.println();
        }
    }
    public static String conNum(String formula){
        ConvertNum convertNum = new ConvertNum();
        formula = formula.replaceAll(" ", "");
        String token = "v^!-";
        int tokenCnt = 0;
        for(int i = 0; i < formula.length(); i++){
            if(token.indexOf(formula.charAt(i)) != -1)
                tokenCnt++;
        }

        for(int i = 0; i < tokenCnt; i++){ // 식의 제곱, 루트, 팩토리얼을 모두 숫자로 변환
            if(formula.length() > convertNum.lastIndex){
                formula = convertNum.conNeg(formula);
            }
            if(formula.contains("v")){
                formula = convertNum.conSqrt(formula);
            }
            if(formula.contains("!")){
                formula = convertNum.conFac(formula);
            }
            if(formula.contains("^")){
                formula = convertNum.conPow(formula);
            }
        }
        convertNum.lastIndex = 0;
        return formula;
    }
}


