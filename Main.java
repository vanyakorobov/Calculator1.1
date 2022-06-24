import java.util.Scanner;
import java.util.NoSuchElementException;

public class Main {
    public static String calc(String input) throws FormulaException
    {
        Scanner scanner = new Scanner(input);
        MathPart[] parts = new MathPart[3];

        try {
            parts[0] = new MathPart(scanner.next());
            parts[1] = new MathPart(scanner.next());
            parts[2] = new MathPart(scanner.next());
        }
        catch(NoSuchElementException e) {
            throw new FormulaException("Формула неверна");
        }

        int index = 0;

        try {
            while (true)
            {
                scanner.next();

                index++;
            }
        }
        catch(NoSuchElementException e) {

        }

        if (index != 0)
        {
            throw new FormulaException("Формула неверна");
        }

        /*
        Если первое / второе / третье значение ошибочно
        если первое значение имеет знак операции
        если второе значение не имеет знак операции
        если первое и третье значения не равны по типу
        сгенерировать ошибку и завершить работу
         */
        if (parts[0].type == TypesPart.NONE || parts[0].type == TypesPart.OPERATION ||
                parts[1].type != TypesPart.OPERATION || parts[2].type == TypesPart.NONE ||
                parts[0].type != parts[2].type) {

            throw new FormulaException("Формула неверна");
        }

        switch (parts[1].valueRaw)
        {
            case "+": parts[0].value += parts[2].value;
                break;
            case "-": parts[0].value -= parts[2].value;
                break;
            case "*": parts[0].value *= parts[2].value;
                break;
            case "/": parts[0].value /= parts[2].value;
                break;
        }

        return "Результат: " + parts[0].getValue();
    }

    public static void main(String[] args) {
        System.out.println("Введите формулу:");
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println(calc(scanner.nextLine()));
        }
        catch (FormulaException e)
        {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}