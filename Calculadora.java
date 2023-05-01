import java.util.Stack;

public class Calculadora {
  // método para converter de notação infixa para pós-fixa
  public String infixaParaPosFixa(String expressaoInfixa) {
    StringBuilder posFixa = new StringBuilder();
    Stack<Character> pilha = new Stack<>();

    for (char c : expressaoInfixa.toCharArray()) {
      if (Character.isDigit(c)) {
        posFixa.append(c);
      } else if (c == '(') {
        pilha.push(c);
      } else if (c == ')') {
        while (!pilha.isEmpty() && pilha.peek() != '(') {
          posFixa.append(pilha.pop());
        }
        if (!pilha.isEmpty() && pilha.peek() != '(') {
          throw new IllegalArgumentException("Expressão inválida.");
        } else {
          pilha.pop();
        }
      } else {
        while (!pilha.isEmpty() && precedenciaDeOperadores(c) <= precedenciaDeOperadores(pilha.peek())) {
          posFixa.append(pilha.pop());
        }
        pilha.push(c);
      }
    }

    while (!pilha.isEmpty()) {
      posFixa.append(pilha.pop());
    }

    return posFixa.toString();
  }

  // método para converter de notação infixa para pré-fixa
  public String infixaParaPreFixa(String expressaoInfixa) {
    StringBuilder preFixa = new StringBuilder();
    Stack<Character> pilha = new Stack<>();
    expressaoInfixa = inverterString(expressaoInfixa);

    for (char c : expressaoInfixa.toCharArray()) {
      if (c == '(') {
        preFixa.append(')');
      } else if (c == ')') {
        preFixa.append('(');
      } else if (Character.isDigit(c)) {
        preFixa.append(c);
      } else {
        while (!pilha.isEmpty() && precedenciaDeOperadores(c) < precedenciaDeOperadores(pilha.peek())) {
          preFixa.append(pilha.pop());
        }
        pilha.push(c);
      }
    }

    while (!pilha.isEmpty()) {
      preFixa.append(pilha.pop());
    }

    return inverterString(preFixa.toString());
  }

  // método para converter de notação pós-fixa para infixa
  public String posFixaParaInfixa(String expressaoPosfixa) {
    Stack<String> pilha = new Stack<>();

    for (char c : expressaoPosfixa.toCharArray()) {
      if (Character.isDigit(c)) {
        pilha.push(String.valueOf(c));
      } else {
        String operando2 = pilha.pop();
        String operando1 = pilha.pop();
        String expressao = "(" + operando1 + c + operando2 + ")";
        pilha.push(expressao);
      }
    }

    return pilha.pop();
  }

  // método para converter de notação pós-fixa para pré-fixa
  public String posFixaParaPreFixa(String expressaoPosfixa) {
    Stack<String> pilha = new Stack<>();

    for (char c : expressaoPosfixa.toCharArray()) {
      if (Character.isDigit(c)) {
        pilha.push(String.valueOf(c));
      } else {
        String operando2 = pilha.pop();
        String operando1 = pilha.pop();
        String expressao = c + operando1 + operando2;
        pilha.push(expressao);
      }
    }

    return pilha.pop();
  }

  // método para converter de notação pré-fixa para infixa
  public String preFixaParaInfixa(String expressaoPreFixa) {
    Stack<String> pilha = new Stack<>();
    expressaoPreFixa = inverterString(expressaoPreFixa);

    for (char c : expressaoPreFixa.toCharArray()) {
      if (Character.isDigit(c)) {
        pilha.push(String.valueOf(c));
      } else {
        String operando1 = pilha.pop();
        String operando2 = pilha.pop();
        String expressao = "(" + operando1 + c + operando2 + ")";
        pilha.push(expressao);
      }
    }

    return pilha.pop();
  }

  // método para converter de notação pré-fixa para pós-fixa
  public String preFixaParaPosFixa(String expressaoPreFixa) {
    Stack<String> pilha = new Stack<>();
    expressaoPreFixa = inverterString(expressaoPreFixa);

    for (char c : expressaoPreFixa.toCharArray()) {
      if (Character.isDigit(c)) {
        pilha.push(String.valueOf(c));
      } else {
        String operando1 = pilha.pop();
        String operando2 = pilha.pop();
        String expressao = operando1 + operando2 + c;
        pilha.push(expressao);
      }
    }

    return pilha.pop();
  }

  // método para calcular a expressão em notação infixa
  public double avaliarExpressaoInfixa(String expressaoInfixa) {
    String expressaoPosfixa = infixaParaPosFixa(expressaoInfixa);
    return avaliarExpressaoPosFixa(expressaoPosfixa);
  }

  // método para calcular a expressão em notação pós-fixa
  public double avaliarExpressaoPosFixa(String expressaoPosfixa) {
    Stack<Double> pilha = new Stack<>();

    for (char c : expressaoPosfixa.toCharArray()) {
      if (Character.isDigit(c)) {
        pilha.push(Double.valueOf(c - '0'));
      } else {
        double operando2 = pilha.pop();
        double operando1 = pilha.pop();

        switch (c) {
          case '+':
            pilha.push(soma(operando1, operando2));
            break;
          case '-':
            pilha.push(subtrai(operando1, operando2));
            break;
          case '*':
            pilha.push(multiplica(operando1, operando2));
            break;
          case '/':
            pilha.push(divide(operando1, operando2));
            break;
          default:
            throw new IllegalArgumentException("Operador inválido.");
        }
      }
    }

    return pilha.pop();
  }

  // método para calcular a expressão em notação pré-fixa
  public double avaliarExpressaoPreFixa(String expressaoPreFixa) {
    String expressaoPosfixa = preFixaParaPosFixa(expressaoPreFixa);
    return avaliarExpressaoPosFixa(expressaoPosfixa);
  }

    // métodos para realizar as operações matemáticas
    public double soma(double a, double b) {
      return a + b;
    }
  
    public double subtrai(double a, double b) {
      return a - b;
    }
  
    public double multiplica(double a, double b) {
      return a * b;
    }
  
    public double divide(double a, double b) {
      if (b == 0) {
        throw new ArithmeticException("Divisão por zero.");
      }
      return a / b;
    }

  // método auxiliar para inverter uma string
  private String inverterString(String str) {
    return new StringBuilder(str).reverse().toString();
  }

  // método auxiliar para retornar a precedência de um operador
  private int precedenciaDeOperadores(char operator) {
    switch (operator) {
      case '+':
      case '-':
        return 1;
      case '*':
      case '/':
        return 2;
      case '^':
        return 3;
      default:
        return -1;
    }
  }
}
